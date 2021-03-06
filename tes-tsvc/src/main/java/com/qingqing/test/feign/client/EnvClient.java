package com.qingqing.test.feign.client;

import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.exception.ErrorCodeInterface;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.test.bean.common.Env;
import com.qingqing.test.bean.config.ITestConfigNotify;
import com.qingqing.test.config.inteceptor.EnvHandlerInteceptor;
import com.qingqing.test.manager.UserIpManager;
import com.qingqing.test.manager.config.TestConfigManager;
import com.qingqing.test.spring.filter.IpFilter;
import feign.Client;
import feign.Request;
import feign.Request.Options;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPOutputStream;

import static feign.Util.CONTENT_ENCODING;
import static feign.Util.CONTENT_LENGTH;
import static feign.Util.ENCODING_DEFLATE;
import static feign.Util.ENCODING_GZIP;
import static java.lang.String.format;

/**
 * Created by zhujianxing on 2018/9/13.
 */
@Component
public class EnvClient implements Client {
    public static final String BEAN_NAME = "envClient";
    private static final Logger logger = LoggerFactory.getLogger(EnvClient.class);

    @Autowired
    private UserIpManager userIpManager;

    @Override
    public Response execute(Request request, Options options) throws IOException {
        final HttpURLConnection
                connection =
                (HttpURLConnection) new URL(formatUrl(request.url())).openConnection();
        connection.setConnectTimeout(options.connectTimeoutMillis());
        connection.setReadTimeout(options.readTimeoutMillis());
        connection.setAllowUserInteraction(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestMethod(request.method());

        Collection<String> contentEncodingValues = request.headers().get(CONTENT_ENCODING);
        boolean
                gzipEncodedRequest =
                contentEncodingValues != null && contentEncodingValues.contains(ENCODING_GZIP);
        boolean
                deflateEncodedRequest =
                contentEncodingValues != null && contentEncodingValues.contains(ENCODING_DEFLATE);

        boolean hasAcceptHeader = false;
        Integer contentLength = null;
        for (String field : request.headers().keySet()) {
            if (field.equalsIgnoreCase("Accept")) {
                hasAcceptHeader = true;
            }
            for (String value : request.headers().get(field)) {
                if (field.equals(CONTENT_LENGTH)) {
                    if (!gzipEncodedRequest && !deflateEncodedRequest) {
                        contentLength = Integer.valueOf(value);
                        connection.addRequestProperty(field, value);
                    }
                } else {
                    connection.addRequestProperty(field, value);
                }
            }
        }
        // Some servers choke on the default accept string.
        if (!hasAcceptHeader) {
            connection.addRequestProperty("Accept", "*/*");
        }

        if (request.body() != null && request.body().length > 0) {
            if (contentLength != null) {
                connection.setFixedLengthStreamingMode(contentLength);
            } else {
                connection.setChunkedStreamingMode(8196);
            }
            connection.setDoOutput(true);
            OutputStream out = connection.getOutputStream();
            if (gzipEncodedRequest) {
                out = new GZIPOutputStream(out);
            } else if (deflateEncodedRequest) {
                out = new DeflaterOutputStream(out);
            }
            try {
                out.write(request.body());
            } finally {
                try {
                    out.close();
                } catch (IOException suppressed) { // NOPMD
                }
            }
        }
        return convertResponse(connection).toBuilder().request(request).build();
    }

    Response convertResponse(HttpURLConnection connection) throws IOException {
        int status = connection.getResponseCode();
        String reason = connection.getResponseMessage();

        if (status < 0) {
            throw new IOException(format("Invalid status(%s) executing %s %s", status,
                    connection.getRequestMethod(), connection.getURL()));
        }

        Map<String, Collection<String>> headers = new LinkedHashMap<String, Collection<String>>();
        for (Map.Entry<String, List<String>> field : connection.getHeaderFields().entrySet()) {
            // response message
            if (field.getKey() != null) {
                headers.put(field.getKey(), field.getValue());
            }
        }

        Integer length = connection.getContentLength();
        if (length == -1) {
            length = null;
        }
        InputStream stream;
        if (status >= 400) {
            stream = connection.getErrorStream();
        } else {
            stream = connection.getInputStream();
        }
        return Response.builder()
                .status(status)
                .reason(reason)
                .headers(headers)
                .body(stream, length)
                .build();
    }

    private String formatUrl(String url) throws UnsupportedEncodingException {
        String host = getHost(url);
        url = URLDecoder.decode(url, "utf-8");

        String finalUrl = url.replace("{host}", host);
        String guid = EnvHandlerInteceptor.getParam(EnvHandlerInteceptor.GUID);
        if(guid == null){
            guid = "test-api-" + System.currentTimeMillis();
        }
        if(finalUrl.indexOf("guid=") == -1){
            if(finalUrl.indexOf("?") == -1){
                finalUrl = finalUrl + "?guid=" + guid;
            }else{
                finalUrl = finalUrl + "&guid=" + guid;
            }
        }

        String env = EnvHandlerInteceptor.getParam(EnvHandlerInteceptor.ENV);
        if("hjl".equals(env) && url.contains("passportsvc")){
            env = "fws";
        }
        finalUrl = finalUrl.replace("{env}", env == null? "":env);

        finalUrl = finalUrl.replace("cn//", "cn/");
        finalUrl = finalUrl.replace("{empty}", "");
        finalUrl = finalUrl.replace("http:///", "");

//        logger.info("finalUrl:" + finalUrl);
        return finalUrl;
    }

    protected String getHost(String url){
//        if(EnvHandlerInteceptor.isLocalDebug()){
//            return EnvHandlerInteceptor.getParam(EnvHandlerInteceptor.IP) + ":" + EnvHandlerInteceptor.getParam(EnvHandlerInteceptor.LOCAL_PORT);
//        }else{
            return getEnvHost(url);
//        }
    }

    private String getEnvHost(String url){
        String host = EnvHandlerInteceptor.getParam(EnvHandlerInteceptor.HOST);
        if(host != null){
            return host;
        }

        String envValue = EnvHandlerInteceptor.getParam(EnvHandlerInteceptor.ENV);
        if(envValue == null){
            return "";
        }

        Env env = Env.valueOf(envValue);
        switch (env){
            case fws:
            case dev:
            case tst:
            case pfm:
                return "gateway.{env}.idc.cedu.cn".replace("{env}", envValue);
            case pre:
            case prd:
                boolean isAdmin = userIpManager.isAdmin(IpFilter.getRequestUserIp());
                if(!isAdmin){
                    throw new ErrorCodeException(new ErrorCodeInterface() {
                        @Override
                        public Integer getErrorCode() {
                            return 10001;
                        }

                        @Override
                        public String getMsg() {
                            return "no permission";
                        }

                        @Override
                        public String getHintMessage() {
                            return "您未被允许使用该环境，请联系管理员";
                        }
                    });
                }
                return "gateway.{env}.idc.cedu.cn".replace("{env}", envValue);
            default:
                throw new QingQingRuntimeException("unknown env");
        }
    }
}
