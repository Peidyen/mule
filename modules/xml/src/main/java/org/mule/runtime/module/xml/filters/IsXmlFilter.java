/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.xml.filters;

import org.mule.runtime.core.api.Event;
import org.mule.runtime.core.api.message.InternalMessage;
import org.mule.runtime.core.api.routing.filter.Filter;
import org.mule.runtime.core.util.xmlsecurity.XMLSecureFactories;
import org.mule.runtime.module.xml.util.XMLUtils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * <code>IsXmlFilter</code> accepts a String or byte[] if its contents are valid (well-formed) XML.
 */
// @ThreadSafe
public class IsXmlFilter implements Filter {

  private final XMLInputFactory factory = XMLSecureFactories.createDefault().getXMLInputFactory();

  // TODO: add validation against a DTD, see MULE-1055

  public IsXmlFilter() {
    super();
  }

  @Override
  public boolean accept(InternalMessage obj, Event.Builder builder) {
    return accept(obj.getPayload().getValue());
  }

  private boolean accept(Object obj) {
    XMLStreamReader parser = null;
    try {
      parser = XMLUtils.toXMLStreamReader(factory, obj);
      if (parser == null) {
        return false;
      }

      while (parser.next() != XMLStreamConstants.END_DOCUMENT) {
        // meep meep!
      }

      return true;
    } catch (XMLStreamException ex) {
      return false;
    } finally {
      if (parser != null) {
        try {
          parser.close();
        } catch (XMLStreamException ignored) {
          // bummer
        }
      }
    }
  }

}
