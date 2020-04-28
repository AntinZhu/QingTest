package com.qingqing.test.project;

import freemarker.cache.StringTemplateLoader;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhujianxing on 2020/4/21.
 */
public class MyStringTemplateLoader extends StringTemplateLoader {

    private Set<String> templateKeySet = new HashSet<>();

    @Override
    public void putTemplate(String name, String templateContent) {
        super.putTemplate(name, templateContent);
        templateKeySet.add(name);
    }

    @Override
    public boolean removeTemplate(String name) {
        templateKeySet.remove(name);
        return super.removeTemplate(name);
    }

    public Set<String> getAllTemplateKey(){
        return new HashSet<>(templateKeySet);
    }
}
