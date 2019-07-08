package com.qingqing.test.util;

import java.io.File;

/**
 * Created by zhujianxing on 2019/7/4.
 */
public class DirSearchUtils {

    public static void checkDir(File dir, FileHandler fileHandler) {
        File[] xmlFiles = dir.listFiles();
        for (File xmlFile : xmlFiles) {
            if(xmlFile.isDirectory()){
                checkDir(xmlFile, fileHandler);
            }else{
                fileHandler.handle(xmlFile);
            }
        }
    }

    public static interface FileHandler {

        void handle(File file);
    }
}
