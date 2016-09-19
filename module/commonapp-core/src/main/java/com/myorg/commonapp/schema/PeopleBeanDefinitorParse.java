package com.myorg.commonapp.schema;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * Created by huyan on 15/12/8.
 */
public class PeopleBeanDefinitorParse extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return People.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String name = element.getAttribute("name");
        int age = Integer.parseInt(element.getAttribute("age"));

        builder.addPropertyValue("name", name);
        builder.addPropertyValue("age", age);
    }
}
