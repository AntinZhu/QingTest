package com.qingqing.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
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
}
