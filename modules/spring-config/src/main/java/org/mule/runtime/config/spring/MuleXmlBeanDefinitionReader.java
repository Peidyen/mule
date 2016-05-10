/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.runtime.config.spring;

import org.mule.runtime.config.spring.dsl.spring.BeanDefinitionFactory;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.BeanDefinitionDocumentReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.w3c.dom.Document;

public class MuleXmlBeanDefinitionReader extends XmlBeanDefinitionReader implements BeanDefinitionDocumentReader
{

    private MuleBeanDefinitionDocumentReader beanDefinitionDocumentReader;

    /**
     * Create new XmlBeanDefinitionReader for the given bean factory.
     *
     * @param registry the BeanFactory to load bean definitions into,
     *                 in the form of a BeanDefinitionRegistry
     */
    public MuleXmlBeanDefinitionReader(BeanDefinitionRegistry registry, BeanDefinitionFactory beanDefinitionFactory)
    {
        super(registry);
        beanDefinitionDocumentReader = new MuleBeanDefinitionDocumentReader(beanDefinitionFactory);
//        beanDefinitionDocumentReader.setBeanDefinitionFactory();

    }

    @Override
    protected BeanDefinitionDocumentReader createBeanDefinitionDocumentReader()
    {
        return beanDefinitionDocumentReader;
    }

    @Override
    public void registerBeanDefinitions(Document doc, XmlReaderContext readerContext) throws BeanDefinitionStoreException
    {
        beanDefinitionDocumentReader.registerBeanDefinitions(doc, readerContext);
    }
}