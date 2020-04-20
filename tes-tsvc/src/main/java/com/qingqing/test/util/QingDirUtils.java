package com.qingqing.test.util;

import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

/**
 * Created by zhujianxing on 2020/4/20.
 */
public class QingDirUtils {

    private static final Logger logger = LoggerFactory.getLogger(QingDirUtils.class);

    public static void deleteDir(String dirPath) throws IOException {
        File dir = new File(dirPath);
        DirSearchUtils.checkDir(dir, new DirSearchUtils.FileHandler() {
            @Override
            public void handle(File file) throws IOException {
                boolean result = file.delete();
//                logger.info("delete file:{} result:{}", file.getAbsoluteFile(), result);
            }

            @Override
            public void doAfterFileChecked(File dir) {
                boolean result = dir.delete();
//                logger.info("delete dir:{} result:{}", dir.getAbsoluteFile(), result);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        String path = "D:\\_1\\work\\Github\\QingTest\\tes-tsvc\\target\\test-svc-0.0.1-SNAPSHOT.jar";
        try {
            JarFile jarFile = new JarFile(new File(path));
            Enumeration<JarEntry> es = jarFile.entries();
            while (es.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) es.nextElement();
                String name = jarEntry.getName();
                 if(name.contains("classes/templates/project/")){
                     System.out.println(name);
                 }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
