package com.qingqing.test.config;

import com.qingqing.common.auth.domain.User;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.CompareUtil;
import com.qingqing.common.util.OrderIdShuffler;
import com.qingqing.common.util.QingqingFtpClientV2;
import com.qingqing.common.util.StringUtils;
import com.qingqing.common.util.UserIdShuffler;
import com.qingqing.common.util.encode.RSADecrypterCore;
import com.qingqing.common.util.encode.RSAEncrypterCore;
import com.qingqing.common.util.encode.TripleDESUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

/**
 * Created by yangzirong on 9/28/2017.
 */
@Configuration
public class FtpResourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(FtpResourceConfig.class);

    public final static String TRIPLE_DES_KEY = "shadowkey.tripledes";
    public final static String TRIPLE_DES_KEY_PLACEHOLDER = "${" + TRIPLE_DES_KEY + "}";

    public final static String USERID_TXT_INITIALIZATION = "userIdTxtInitialization";

    @Bean(initMethod = "connect", destroyMethod = "disconnect")
    public QingqingFtpClientV2 qingqingFtpClient(
            @Value("${ftp.host}") String host,
            @Value("${ftp.port}") Integer port,
            @Value("${ftp.username}") String username,
            @Value("${ftp.password}") String password
    ){
        if (StringUtils.isEmpty(host)) {
            throw new QingQingRuntimeException("ftp host not found");
        }

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new QingQingRuntimeException("ftp username or password host not found");
        }

        QingqingFtpClientV2 ftpClient;
        if (port == null) {
            ftpClient = new QingqingFtpClientV2(host, username, password);
        }else{
            ftpClient = new QingqingFtpClientV2(host, port, username, password);
        }

        return ftpClient;
    }

    @Bean
    public Boolean shadownKeyInitialization(@Value(TRIPLE_DES_KEY_PLACEHOLDER) String tridpleDesKey) throws Exception {
        if (StringUtils.isEmpty(tridpleDesKey)) {
            throw new QingQingRuntimeException("triple des key not found key:" + TRIPLE_DES_KEY);
        }
        TripleDESUtil.setEncryptKey(tridpleDesKey);
        String test = "test";
        String encrypt = TripleDESUtil.encrypt(test);
        String decrypt = TripleDESUtil.decrypt(encrypt);
        boolean initStatus = test.equals(decrypt);
        logger.info("triple des init status:" + initStatus);
        if (!initStatus) {
            throw new QingQingRuntimeException(tridpleDesKey + " file error");
        }
        return Boolean.TRUE;
    }

    @Bean(name = USERID_TXT_INITIALIZATION)
    @ConditionalOnBean(QingqingFtpClientV2.class)
    public Boolean userIdTxtInitialization(@Value("${ftp.userid.filepath}") String filePath,
                                           QingqingFtpClientV2 ftpClient) throws IOException {
        if (StringUtils.isEmpty(filePath)) {
            throw new QingQingRuntimeException("userid ftp filepath not found");
        }

        InputStream inputStream = ftpClient.downloadByFilePath(filePath);
        List<String> strings = IOUtils.readLines(inputStream);
        int[] seeds = new int[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            if (!com.qingqing.common.util.StringUtils.isEmpty(s)) {
                seeds[i] = Integer.parseInt(s);
            }
        }

        UserIdShuffler.initShuffleSeeds(seeds);
        //
        String s = UserIdShuffler.encodeUser(UserType.student, 22469L);
        User user = UserIdShuffler.decodeUser(s);

        boolean initStatus = (22469L == user.getUserId() && UserType.student.equals(user.getUserType()));
        if (!initStatus) {
            throw new QingQingRuntimeException(filePath + " file error");
        }
        logger.info("init userid shuffler status:{}", initStatus);
        return Boolean.TRUE;
    }


    @Bean
    @ConditionalOnBean(QingqingFtpClientV2.class)
    public Boolean orderIdTxtInitialization(@Value("${ftp.orderid.filepath}") String filePath,
                                           QingqingFtpClientV2 ftpClient) throws IOException {
        if (StringUtils.isEmpty(filePath)) {
            throw new QingQingRuntimeException("orderid ftp filepath not found");
        }

        InputStream inputStream = ftpClient.downloadByFilePath(filePath);
        List<String> strings = IOUtils.readLines(inputStream);
        int[] seeds = new int[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            if (!com.qingqing.common.util.StringUtils.isEmpty(s)) {
                seeds[i] = Integer.parseInt(s);
            }
        }

        OrderIdShuffler.initShuffleSeeds(seeds);
        //
        String s = OrderIdShuffler.encodeOrderId(1);
        Long orderId = OrderIdShuffler.decodeQingqingOrderId(s);

        boolean initStatus = CompareUtil.equals(orderId, 1L);
        if (!initStatus) {
            throw new QingQingRuntimeException(filePath + " file error");
        }
        logger.info("init orderId shuffler status:{}", initStatus);
        return Boolean.TRUE;
    }

    @Bean
    @ConditionalOnBean(QingqingFtpClientV2.class)
    public Boolean rsaKeyInitialization(QingqingFtpClientV2 ftpClient,
                                        @Value("${ftp.rsa.privatekey.filepath}") String privateKeyFilePath,
                                        @Value("${ftp.rsa.publickey.filepath}") String publicKeyFilePath) throws Exception{
        if (StringUtils.isEmpty(privateKeyFilePath)) {
            throw new QingQingRuntimeException("private key ftp filepath not found");
        }

        if (StringUtils.isEmpty(publicKeyFilePath)) {
            throw new QingQingRuntimeException("public key ftp filepath not found");
        }

        InputStream privateKeyInputStream = ftpClient.downloadByFilePath(privateKeyFilePath);
        InputStream publicKeyInputStream = ftpClient.downloadByFilePath(publicKeyFilePath);

        RSADecrypterCore.setPublicKey((PublicKey) new ObjectInputStream(publicKeyInputStream).readObject());
        RSAEncrypterCore.setPrivateKey((PrivateKey) new ObjectInputStream(privateKeyInputStream).readObject());

        String testSource = "test";
        String test = RSAEncrypterCore.getInstance().encrypt(testSource);
        String decrypt = RSADecrypterCore.getInstance().decrypt(test);
        boolean initStatus = testSource.equals(decrypt);
        logger.info("rsa init status: " + initStatus);
        if (!initStatus) {
            throw new QingQingRuntimeException(privateKeyFilePath + " or " + publicKeyFilePath + " +file error");
        }
        return Boolean.TRUE;
    }
}
