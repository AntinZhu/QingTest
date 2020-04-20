package com.qingqing.test.util;

import com.qingqing.test.util.DirSearchUtils.FileHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by zhujianxing on 2019/7/4.
 */
public class PomUtils {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        final SAXParser parser = factory.newSAXParser();

        File dir = new File("D:\\all-project");
        DirSearchUtils.checkDir(dir, new FileHandler() {
            @Override
            public void handle(File file) {
                if("pom.xml".equals(file.getName())){
                    SAXParserHandler handler = new SAXParserHandler();
                    try {
                        parser.parse(file, handler);
                        if(handler.finalResult){
                            System.out.println(file.getAbsolutePath() + ":" + handler.finalResultContent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void doAfterFileChecked(File dir) {

            }
        });
    }

    public static class SAXParserHandler extends DefaultHandler {
        private boolean isInArtifactIdTag = false;
        private boolean  isInDependencyTag = false;
        private boolean isInResultDependencyTag = false;

        private boolean finalResult = false;
        private String finalResultContent = null;

        private StringBuilder content = new StringBuilder();
        /**
         * 用来标识解析开始
         */
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
        }

        /**
         * 用来标识解析结束
         */
        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        /**
         * 解析xml元素
         */
        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            //调用DefaultHandler类的startElement方法
            super.startElement(uri, localName, qName, attributes);

            if(!isInDependencyTag){
                isInDependencyTag = "dependency".equals(qName);
                content.delete(0, content.length());
            }

            if(isInDependencyTag){
                content.append("<" + qName);
                for(int idx = 0; idx < attributes.getLength(); idx++){
                    content.append(" " + attributes.getLocalName(idx) + "=\"" + attributes.getValue(idx) + "\"");
                }
                content.append(">");
            }

            isInArtifactIdTag = "artifactId".equals(qName);
        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            //调用DefaultHandler类的endElement方法
            super.endElement(uri, localName, qName);

            if(isInDependencyTag){
                content.append("</" + qName + ">");

                if("dependency".equals(qName)){
                    isInDependencyTag = false;

                    if(isInResultDependencyTag){
                        finalResultContent = content.toString();
                    }

                    isInResultDependencyTag = false;
                }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            super.characters(ch, start, length);

            String value = new String(ch, start, length).toLowerCase().trim();
            if (isInArtifactIdTag && "fastjson".equals(value)) {
                finalResult = true;
                isInResultDependencyTag = true;
            }

            if (isInDependencyTag) {
                content.append(value);
            }
        }
    }
}
