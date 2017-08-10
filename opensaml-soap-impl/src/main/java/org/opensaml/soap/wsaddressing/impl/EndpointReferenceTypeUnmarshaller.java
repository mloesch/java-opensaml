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

package org.opensaml.soap.wsaddressing.impl;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.core.xml.util.XMLObjectSupport;
import org.opensaml.soap.wsaddressing.Address;
import org.opensaml.soap.wsaddressing.EndpointReferenceType;
import org.opensaml.soap.wsaddressing.Metadata;
import org.opensaml.soap.wsaddressing.ReferenceParameters;
import org.w3c.dom.Attr;

/**
 * Abstract unmarshaller for the element of type {@link EndpointReferenceType}.
 * 
 */
public class EndpointReferenceTypeUnmarshaller extends AbstractWSAddressingObjectUnmarshaller {

    /** {@inheritDoc} */
    protected void processChildElement(final XMLObject parentXMLObject, final XMLObject childXMLObject)
        throws UnmarshallingException {
        final EndpointReferenceType epr = (EndpointReferenceType) parentXMLObject;
        if (childXMLObject instanceof Address) {
            epr.setAddress((Address) childXMLObject);
        } else if (childXMLObject instanceof Metadata) {
            epr.setMetadata((Metadata) childXMLObject);
        } else if (childXMLObject instanceof ReferenceParameters) {
            epr.setReferenceParameters((ReferenceParameters) childXMLObject);
        } else {
            epr.getUnknownXMLObjects().add(childXMLObject);
        }
    }

    /** {@inheritDoc} */
    protected void processAttribute(final XMLObject xmlObject, final Attr attribute) throws UnmarshallingException {
        final EndpointReferenceType epr = (EndpointReferenceType) xmlObject;
        XMLObjectSupport.unmarshallToAttributeMap(epr.getUnknownAttributes(), attribute);
    }

}
