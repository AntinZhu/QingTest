package com.qingqing.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by zhujianxing on 2019/11/13.
 */
@Controller
@RequestMapping("v1/download")
public class DownloadController {

    @RequestMapping("/cross")
    public @ResponseBody
    void decodePhone(HttpServletResponse response) throws IOException {
        String fileName = "cross-domain-tool.zip";
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

        OutputStream out = null; //输出流
        InputStream inputStream = null;
        try {
            inputStream = DownloadController.class.getResourceAsStream("/cross-admin.zip");
            out = response.getOutputStream();
            byte[] buf = new byte[1024];
            int readLen = -1;
            while ((readLen = inputStream.read(buf)) != -1){
                out.write(buf, 0, readLen);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch(Exception e){
                    // ignore
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = DownloadController.class.getResourceAsStream("/cross-admin.zip");
        System.out.println("111:" + inputStream.available());
    }
}
