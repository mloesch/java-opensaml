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

package org.opensaml.xml.encryption.impl;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.xmlsec.encryption.EncryptionProperties;
import org.opensaml.xmlsec.encryption.EncryptionProperty;
import org.w3c.dom.Attr;

/**
 * A thread-safe Unmarshaller for {@link org.opensaml.xmlsec.encryption.EncryptionProperties} objects.
 */
public class EncryptionPropertiesUnmarshaller extends AbstractXMLEncryptionUnmarshaller {

    /** {@inheritDoc} */
    protected void processAttribute(XMLObject xmlObject, Attr attribute) throws UnmarshallingException {
        EncryptionProperties ep = (EncryptionProperties) xmlObject;

        if (attribute.getLocalName().equals(EncryptionProperties.ID_ATTRIB_NAME)) {
            ep.setID(attribute.getValue());
            attribute.getOwnerElement().setIdAttributeNode(attribute, true);
        } else {
            super.processAttribute(xmlObject, attribute);
        }
    }

    /** {@inheritDoc} */
    protected void processChildElement(XMLObject parentXMLObject, XMLObject childXMLObject)
            throws UnmarshallingException {
        EncryptionProperties ep = (EncryptionProperties) parentXMLObject;

        if (childXMLObject instanceof EncryptionProperty) {
            ep.getEncryptionProperties().add((EncryptionProperty) childXMLObject);
        } else {
            super.processChildElement(parentXMLObject, childXMLObject);
        }
    }

}
