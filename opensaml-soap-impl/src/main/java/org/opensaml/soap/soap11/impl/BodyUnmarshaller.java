/*
 * Licensed to the University Corporation for Advanced Internet Development,
 * Inc. (UCAID) under one or more contributor license agreements.  See the
 * NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The UCAID licenses this file to You under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.soap.soap11.impl;

import javax.xml.namespace.QName;

import net.shibboleth.utilities.java.support.xml.QNameSupport;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.AbstractXMLObjectUnmarshaller;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.soap.soap11.Body;
import org.w3c.dom.Attr;

/**
 * A thread-safe unmarshaller for {@link org.opensaml.soap.soap11.Body}s.
 */
public class BodyUnmarshaller extends AbstractXMLObjectUnmarshaller {

    /** {@inheritDoc} */
    protected void processChildElement(final XMLObject parentXMLObject, final XMLObject childXMLObject)
            throws UnmarshallingException {
        final Body body = (Body) parentXMLObject;
        body.getUnknownXMLObjects().add(childXMLObject);
    }

    /** {@inheritDoc} */
    protected void processAttribute(final XMLObject xmlObject, final Attr attribute) throws UnmarshallingException {
        final Body body = (Body) xmlObject;
        final QName attribQName = QNameSupport.constructQName(attribute.getNamespaceURI(), attribute.getLocalName(),
                attribute.getPrefix());
        if (attribute.isId()) {
            body.getUnknownAttributes().registerID(attribQName);
        }
        body.getUnknownAttributes().put(attribQName, attribute.getValue());
    }

    /** {@inheritDoc} */
    protected void processElementContent(final XMLObject xmlObject, final String elementContent) {
        // do nothing, no child content
    }
}