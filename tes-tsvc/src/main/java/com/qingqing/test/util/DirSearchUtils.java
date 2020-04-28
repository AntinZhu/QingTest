package com.qingqing.test.util;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by zhujianxing on 2019/7/4.
 */
public class DirSearchUtils {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        File dir = new File("F:\\work\\hf");
        DirSearchUtils.checkDir(dir, new FileHandler() {
            @Override
            public void handle(File file) throws IOException {
                if(file.getName().indexOf(".java") > 0 || file.getName().indexOf(".xml") > 0){
                    InputStream in = null;
                    try{
                        in = new FileInputStream(file);
                        List<String> lines =  QingFileUtils.readLines(in);
                        for (String line : lines) {
                            if(line.contains("vocation")){
                                System.out.println(file.getAbsolutePath());
                                break;
                            }
                        }
                    }finally {
                        if(in != null){
                            try{
                                in.close();
                            }catch(Exception e){
                                // ignore
                            }
                        }
                    }

                }
            }

            @Override
            public void doAfterFileChecked(File dir) {

            }
        });
    }

    public static void checkDir(File dir, FileHandler fileHandler) throws IOException {
        File[] xmlFiles = dir.listFiles();
            for (File xmlFile : xmlFiles) {
                if(xmlFile.isDirectory()){
                    checkDir(xmlFile, fileHandler);
                }else{
                    fileHandler.handle(xmlFile);
                }
            }

        fileHandler.doAfterFileChecked(dir);
    }

    public static interface FileHandler {

        void handle(File file) throws IOException;

        void doAfterFileChecked(File dir);
    }
}
