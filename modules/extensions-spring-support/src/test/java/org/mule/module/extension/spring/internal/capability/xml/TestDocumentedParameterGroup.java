/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.module.extension.spring.internal.capability.xml;

import org.mule.extension.annotation.api.Parameter;

public class TestDocumentedParameterGroup
{

    /**
     * Group parameter 1
     */
    @Parameter
    private String value1;

    /**
     * Group parameter 2
     */
    @Parameter
    private String value2;
}