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

package org.opensaml.xmlsec.signature;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.security.credential.Credential;
import org.opensaml.xmlsec.signature.support.ContentReference;
import org.opensaml.xmlsec.signature.support.SignatureConstants;

import net.shibboleth.shared.annotation.constraint.NotEmpty;

/**
 * XMLObject representing an enveloped or detached XML Digital Signature, version 20020212, Signature element.
 */
public interface Signature extends XMLObject {

    /** Element local name. */
    @Nonnull @NotEmpty public static final String DEFAULT_ELEMENT_LOCAL_NAME = "Signature";

    /** Default element name. */
    @Nonnull public static final QName DEFAULT_ELEMENT_NAME =
            new QName(SignatureConstants.XMLSIG_NS, DEFAULT_ELEMENT_LOCAL_NAME, SignatureConstants.XMLSIG_PREFIX);

    /** Local name of the XSI type. */
    @Nonnull @NotEmpty public static final String TYPE_LOCAL_NAME = "SignatureType";

    /** QName of the XSI type. */
    @Nonnull public static final QName TYPE_NAME = new QName(SignatureConstants.XMLSIG_NS, TYPE_LOCAL_NAME, 
            SignatureConstants.XMLSIG_PREFIX);

    /**
     * Gets the canonicalization algorithm used to create the signature content.
     * 
     * @return the canonicalization algorithm used to create the signature content
     */
    @Nullable public String getCanonicalizationAlgorithm();

    /**
     * Sets the canonicalization algorithm used to create the signature content.
     * 
     * @param newAlgorithm the canonicalization algorithm used to create the signature content
     */
    public void setCanonicalizationAlgorithm(@Nullable final String newAlgorithm);

    /**
     * Gets the algorithm used to compute the signature.
     * 
     * @return the algorithm used to compute the signature
     */
    @Nullable public String getSignatureAlgorithm();

    /**
     * Sets the algorithm used to compute the signature.
     * 
     * @param newAlgorithm the algorithm used to compute the signature
     */
    public void setSignatureAlgorithm(@Nullable final String newAlgorithm);
    
    /**
     * Gets the HMAC output length value, optionally used when signing
     * with an HMAC signature algorithm.
     * 
     * @return the HMACOutputLength value
     */
    @Nullable public Integer getHMACOutputLength();
    
    /**
     * Sets the HMAC output length value, optionally used when signing
     * with an HMAC signature algorithm.
     * 
     * @param length the new HMACOutputLength value
     */
    public void setHMACOutputLength(@Nullable final Integer length);

    /**
     * Gets the signature signing credential.
     * 
     * @return the signature signing credential
     */
    @Nullable public Credential getSigningCredential();

    /**
     * Sets the signature signing credential.
     * 
     * @param newCredential the signature signing credential
     */
    public void setSigningCredential(@Nullable final Credential newCredential);

    /**
     * Gets the key info added to this signature.
     * 
     * @return the key info added to this signature
     */
    @Nullable public KeyInfo getKeyInfo();

    /**
     * Sets the key info added to this signature.
     * 
     * @param newKeyInfo the key info added to this signature
     */
    public void setKeyInfo(@Nullable final KeyInfo newKeyInfo);

    /**
     * Gets the list of signature content references.
     * 
     * @return the list of signature content references
     */
    @Nonnull public List<ContentReference> getContentReferences();
}