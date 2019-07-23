package com.qingqing.test.util;

import com.qingqing.test.util.DirSearchUtils.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujianxing on 2019/1/21.
 */
public class QingFileUtils {
    private static final Logger logger = LoggerFactory.getLogger(QingFileUtils.class);

    public static final List<String> readLines(InputStream in){
        List<String> resultList = new ArrayList<>(50);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line = null;
        try {
            while((line = reader.readLine()) != null){
                resultList.add(line);
            }
        } catch (IOException e) {
            logger.error("read file error", e);
        }

        return resultList;
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        final SAXParser parser = factory.newSAXParser();

        File dir = new File("D:\\all-project");
        DirSearchUtils.checkDir(dir, new FileHandler() {
            @Override
            public void handle(File file) {
                if("common.properties".equals(file.getName())){
                    try {
                        List<String> lines = readLines(new FileInputStream(file));
                        for (String line : lines) {
                            if(line.contains("mongo")){
                                System.out.println(file.getAbsolutePath() + ": has mongodb config");
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
