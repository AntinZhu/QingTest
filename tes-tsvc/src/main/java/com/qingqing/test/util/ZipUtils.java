package com.qingqing.test.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by zhujianxing on 2020/4/20.
 */
public class ZipUtils {

    public static byte[] createZip(String srcSource) throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        //将目标文件打包成zip导出
        File file = new File(srcSource);
        zip(zip, file,"");
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    public static void zip(ZipOutputStream zip, File file, String dir) throws Exception {
        //如果当前的是文件夹，则进行进一步处理
        if (file.isDirectory()) {
//            System.out.println("dir------------" + file.getAbsolutePath());
            //得到文件列表信息
            File[] files = file.listFiles();
            //将文件夹添加到下一级打包目录
            if(dir.length() > 0){
                zip.putNextEntry(new ZipEntry(dir + File.separator));
            }
            dir = dir.length() == 0 ? "" : dir + File.separator;
            //循环将文件夹中的文件打包
            for (int i = 0; i < files.length; i++) {
                zip(zip, files[i], dir + files[i].getName());         //递归处理
            }
        } else {   //当前的是文件，打包处理
//            System.out.println("file------------" + file.getAbsolutePath());
            //文件输入流
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(dir);
            zip.putNextEntry(entry);
            zip.write(FileUtils.readFileToByteArray(file));
            IOUtils.closeQuietly(bis);
            zip.flush();
            zip.closeEntry();
        }
    }
}
