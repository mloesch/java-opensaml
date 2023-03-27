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

package org.opensaml.saml.saml1.core;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;

import org.opensaml.saml.common.SAMLObject;
import org.opensaml.saml.common.xml.SAMLConstants;

import net.shibboleth.shared.annotation.constraint.NotEmpty;

/**
 * This interface defines how the object representing a SAML 1 <code> Status</code> element behaves.
 */
public interface Status extends SAMLObject {

    /** Element name, no namespace. */
    @Nonnull @NotEmpty static final String DEFAULT_ELEMENT_LOCAL_NAME = "Status";
    
    /** Default element name. */
    @Nonnull static final QName DEFAULT_ELEMENT_NAME =
            new QName(SAMLConstants.SAML10P_NS, DEFAULT_ELEMENT_LOCAL_NAME, SAMLConstants.SAML1P_PREFIX);
    
    /** Local name of the XSI type. */
    @Nonnull @NotEmpty static final String TYPE_LOCAL_NAME = "StatusType"; 
        
    /** QName of the XSI type. */
    @Nonnull static final QName TYPE_NAME =
            new QName(SAMLConstants.SAML10P_NS, TYPE_LOCAL_NAME, SAMLConstants.SAML1P_PREFIX);

    /**
     * Get the object representing the <code>StatusMessage</code> (element).
     * 
     * @return object representing the <code>StatusMessage</code> (element)
     */
    @Nullable StatusMessage getStatusMessage();

    /**
     * Set the object representing the <code>StatusMessage</code> (element).
     * 
     * @param statusMessage object representing the <code>StatusMessage</code> (element)
     */
    void setStatusMessage(@Nullable final StatusMessage statusMessage);

    /**
     * Get the object representing the <code>StatusCode</code> (element).
     * 
     * @return object representing the <code>StatusCode</code> (element)
     */
    @Nullable StatusCode getStatusCode();

    /**
     * Set the object representing the <code>StatusCode</code> (element).
     * 
     * @param statusCode object representing the <code>StatusCode</code> (element)
     */
    void setStatusCode(@Nullable final StatusCode statusCode);

    /**
     * Get the object representing the <code>StatusDetail</code> (element).
     * 
     * @return object representing the <code>StatusDetail</code> (element)
     */
    @Nullable StatusDetail getStatusDetail();

    /**
     * Set the object representing the <code>StatusDetail</code> (element).
     * 
     * @param statusDetail object representing the <code>StatusDetail</code> (element)
     */
    void setStatusDetail(@Nullable final StatusDetail statusDetail);
}
