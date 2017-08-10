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

package org.opensaml.saml.saml2.metadata.impl;

import net.shibboleth.utilities.java.support.xml.AttributeSupport;
import net.shibboleth.utilities.java.support.xml.ElementSupport;
import net.shibboleth.utilities.java.support.xml.XMLConstants;

import org.opensaml.core.xml.LangBearing;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.MarshallingException;
import org.opensaml.saml.common.AbstractSAMLObjectMarshaller;
import org.opensaml.saml.saml2.metadata.LocalizedURI;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * A thread safe Marshaller for {@link LocalizedURI} objects.
 */
public class LocalizedURIMarshaller extends AbstractSAMLObjectMarshaller {

    /**
     * {@inheritDoc}
     */
    protected void marshallAttributes(final XMLObject samlObject, final Element domElement) throws MarshallingException {
        final LocalizedURI name = (LocalizedURI) samlObject;

        if (name.getXMLLang() != null) {
            final Attr attribute = AttributeSupport.constructAttribute(domElement.getOwnerDocument(), XMLConstants.XML_NS,
                    LangBearing.XML_LANG_ATTR_LOCAL_NAME, XMLConstants.XML_PREFIX);
            attribute.setValue(name.getXMLLang());
            domElement.setAttributeNodeNS(attribute);
        }
    }

    /** {@inheritDoc} */
    protected void marshallElementContent(final XMLObject samlObject, final Element domElement) throws MarshallingException {
        final LocalizedURI name = (LocalizedURI) samlObject;

        if (name.getValue() != null) {
            ElementSupport.appendTextContent(domElement, name.getValue());
        }
    }
}