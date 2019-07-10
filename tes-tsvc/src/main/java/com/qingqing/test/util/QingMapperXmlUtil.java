package com.qingqing.test.util;

import com.google.common.collect.Sets;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by zhujianxing on 2019/6/4.
 */
public class QingMapperXmlUtil {

    private static final Set<String> EXCLUDED_MAPPER_ID = Sets.newHashSet(
            "com.qingqing.api.dao.mybatis.data.fanta.FantaResultMapper.countPeepedByAnswerer",
            "com.qingqing.api.dao.mybatis.data.fanta.FantaInfoMapper.queryListByAnswererAndStatus",
            "com.qingqing.api.dao.mybatis.data.fanta.FantaInfoMapper.queryPopularListByStatus",
            "com.qingqing.api.dao.mybatis.data.fanta.FantaInfoMapper.queryLatestListByStatus",
            "com.qingqing.api.dao.mybatis.data.fanta.FantaInfoMapper.queryListByStatus",
            "com.qingqing.api.dao.mybatis.data.CommentInfoMapper.findByTypesAndUserComment",
            "com.qingqing.api.dao.mybatis.data.appraise.OrderCourseAppraisePhraseMapper.selectList",
            "com.qingqing.api.dao.mybatis.data.order.OrderCourseMapperV1.findAddGrowthValueListByRange",
            "com.qingqing.api.dao.mybatis.data.phrase.TeacherPhraseMapper.findTopPhrasesByStar",
            "com.qingqing.api.dao.mybatis.data.StudyTraceMapper.queryStudyTrace"
            );
    /**
     * @param args
     */
    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            SAXParserHandler handler = new SAXParserHandler();
            handler.addFilter(new XmlContentFilter() {
                @Override
                public void filter(String namespace, String methodName, String content) {
                    if(!content.contains("select")){
                        System.err.println("-----------select中不包含select------------");
                        System.err.println("问题mapper:" + namespace + "." + methodName);
                        System.err.println("语句：" + content);
                    }else if(content.contains(" join\n") || content.contains(" join ")){
                        String mapperId = namespace + "." + methodName;
                        if(!EXCLUDED_MAPPER_ID.contains(mapperId)){
                            System.err.println("-----------select中包含join------------");
                            System.err.println("问题mapper:" + mapperId);
                            System.err.println("语句：" + content);
                        }
                    }
                }

                @Override
                public boolean isSuit(MapperMethodType methodType) {
                    return methodType == MapperMethodType.SELECT;
                }
            });

//            handler.addFilter(new XmlContentFilter() {
//                @Override
//                public void filter(String namespace, String methodName, String content) {
//                    if(content.contains("select ")){
//                        System.err.println("-----------非select中包含select------------");
//                        System.err.println("问题mapper:" + namespace + "." + methodName);
//                        System.err.println("语句：" + content);
//                    }
//                }
//
//                @Override
//                public boolean isSuit(MapperMethodType methodType) {
//                    return methodType != MapperMethodType.SELECT;
//                }
//            });

            File dir = new File("F:\\work\\backup\\svc\\QQ\\src\\main\\java\\com\\qingqing\\api\\dao\\mybatis\\data");
            checkDir(dir, parser, handler);

//            File file = new File("F:\\work\\backup\\svc\\QQ\\src\\main\\java\\com\\qingqing\\api\\dao\\mybatis\\lecture\\LectureSeriesMapper.xml");
//            parser.parse(file, handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkDir(File dir, SAXParser parser, SAXParserHandler handler) throws IOException, SAXException {
        File[] xmlFiles = dir.listFiles();
        for (File xmlFile : xmlFiles) {
            if(xmlFile.isDirectory()){
                if("bi".equals(xmlFile.getName())){
                    continue;
                }
                checkDir(xmlFile, parser, handler);
            }else{
                if(xmlFile.getName().endsWith(".xml")){
                    parser.parse(xmlFile, handler);
                }
            }
        }
    }

    private static enum MapperMethodType{
        SELECT("select"), UPDATE("update"), INSERT("insert"), DELETED("delete");

        private String xmlEleName;

        MapperMethodType(String xmlEleName) {
            this.xmlEleName = xmlEleName;
        }

        public String getXmlEleName() {
            return xmlEleName;
        }
    }

    private static interface XmlContentFilter{
        void filter(String namespace, String methodName, String content);

        boolean isSuit(MapperMethodType methodType);
    }

    public static class SAXParserHandler extends DefaultHandler {
        private MapperMethodType mapperMethodType;
        private String methodId;
        private String mapperName;
        private StringBuilder content = new StringBuilder();

        private List<XmlContentFilter> filterList = new ArrayList<>();

        public void addFilter(XmlContentFilter filter){
            filterList.add(filter);
        }

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
            if(qName.equals("mapper")){
                mapperName = attributes.getValue("namespace");
            }else{
                boolean isCareEleEnd = false;
                for(MapperMethodType methodType : MapperMethodType.values()){
                    if(methodType.getXmlEleName().equals(qName.toLowerCase())){
                        isCareEleEnd = true;
                        mapperMethodType = methodType;
                        methodId = attributes.getValue("id");
                        break;
                    }
                }

                if(!isCareEleEnd && mapperMethodType!= null){
                    content.append("<" + qName);
                    for(int idx = 0; idx < attributes.getLength(); idx++){
                        content.append(" " + attributes.getLocalName(idx) + "=\"" + attributes.getValue(idx) + "\"");
                    }
                    content.append(">");
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            //调用DefaultHandler类的endElement方法
            super.endElement(uri, localName, qName);

            boolean isCareEleEnd = false;
            for(MapperMethodType methodType : MapperMethodType.values()){
                if(methodType.getXmlEleName().equals(qName.toLowerCase())){
                    isCareEleEnd = true;
                    break;
                }
            }

            if(isCareEleEnd){
                if(mapperMethodType != null){
                    for (XmlContentFilter xmlContentFilter : filterList) {
                        if(xmlContentFilter.isSuit(mapperMethodType)){
                            xmlContentFilter.filter(mapperName, methodId, content.toString());
                        }
                    }
                }

                mapperMethodType = null;
                methodId = null;
                content.delete(0, content.length());
            }else{
                if(mapperMethodType != null){
                    content.append("</" + qName + ">");
                }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            super.characters(ch, start, length);
            if(mapperMethodType != null){
                String value = new String(ch, start, length).toLowerCase();
                content.append(value);
            }
        }
    }
}
