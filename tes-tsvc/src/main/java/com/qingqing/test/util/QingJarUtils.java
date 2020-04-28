package com.qingqing.test.util;

import com.qingqing.common.exception.QingQingRuntimeException;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by zhujianxing on 2020/4/21.
 */
public class QingJarUtils {

    public static final String readFromJar(String jarPath, String filePath){
        // 标准输入流
        InputStream in = null;
        try{
            URL url = new URL("jar:" + jarPath + "!/" + filePath);
            in = url.openStream();
            byte[] buf = new byte[in.available()];
            in.read(buf);

            return new String(buf, "utf-8");
        }catch (Exception e){
            throw new QingQingRuntimeException("read from jar fail, filePath:" + filePath, e);
        }finally {
            if(in  != null){
                try{
                    in.close();
                }catch(Exception e){
                    // ignore
                }
            }
        }
    }
}
