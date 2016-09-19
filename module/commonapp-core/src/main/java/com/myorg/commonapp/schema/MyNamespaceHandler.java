package com.myorg.commonapp.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by huyan on 15/12/8.
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("people",new PeopleBeanDefinitorParse());
    }
}
