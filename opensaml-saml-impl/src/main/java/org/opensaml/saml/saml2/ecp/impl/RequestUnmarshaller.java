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

package org.opensaml.saml.saml2.ecp.impl;

import javax.xml.namespace.QName;

import net.shibboleth.utilities.java.support.xml.QNameSupport;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.core.xml.schema.XSBooleanValue;
import org.opensaml.saml.common.AbstractSAMLObjectUnmarshaller;
import org.opensaml.saml.saml2.core.IDPList;
import org.opensaml.saml.saml2.core.Issuer;
import org.opensaml.saml.saml2.ecp.Request;
import org.w3c.dom.Attr;

/**
 * Unmarshaller for instances of {@link Request}.
 */
public class RequestUnmarshaller extends AbstractSAMLObjectUnmarshaller {

    /** {@inheritDoc} */
    protected void processAttribute(final XMLObject samlObject, final Attr attribute) throws UnmarshallingException {
        final Request request = (Request) samlObject;
        
        final QName attrName = QNameSupport.getNodeQName(attribute);
        if (Request.SOAP11_MUST_UNDERSTAND_ATTR_NAME.equals(attrName)) {
            request.setSOAP11MustUnderstand(XSBooleanValue.valueOf(attribute.getValue()));
        } else if (Request.SOAP11_ACTOR_ATTR_NAME.equals(attrName)) {
            request.setSOAP11Actor(attribute.getValue()); 
        } else if (Request.IS_PASSIVE_NAME_ATTRIB_NAME.equals(attribute.getLocalName())) {
            request.setPassive(XSBooleanValue.valueOf(attribute.getValue()));
        } else if (Request.PROVIDER_NAME_ATTRIB_NAME.equals(attribute.getLocalName())) {
            request.setProviderName(attribute.getValue());
        } else {
            super.processAttribute(samlObject, attribute);
        }
    }

    /** {@inheritDoc} */
    protected void processChildElement(final XMLObject parentSAMLObject, final XMLObject childSAMLObject)
            throws UnmarshallingException {
        final Request request = (Request) parentSAMLObject;
        
        if (childSAMLObject instanceof Issuer) {
            request.setIssuer((Issuer) childSAMLObject);
        } else if (childSAMLObject instanceof IDPList) {
            request.setIDPList((IDPList) childSAMLObject);
        } else {
            super.processChildElement(parentSAMLObject, childSAMLObject);
        }
    }
    

}
