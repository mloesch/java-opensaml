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

package org.opensaml.xmlsec.encryption;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.xmlsec.encryption.support.EncryptionConstants;

import net.shibboleth.shared.annotation.constraint.NotEmpty;

/**
 * XMLObject representing XML Encryption, version 1.1, AlgorithmIdentifierType complex type.
 */
public interface AlgorithmIdentifierType extends XMLObject {
    
    /** Local name of the XSI type. */
    @Nonnull @NotEmpty public static final String TYPE_LOCAL_NAME = "AlgorithmIdentifierType";

    /** QName of the XSI type. */
    @Nonnull public static final QName TYPE_NAME = new QName(EncryptionConstants.XMLENC11_NS, TYPE_LOCAL_NAME,
            EncryptionConstants.XMLENC11_PREFIX);
    
    /** Algorithm attribute name. */
    @Nonnull @NotEmpty public static final String ALGORITHM_ATTRIB_NAME = "Algorithm";

    /**
     * Gets the algorithm URI attribute.
     * 
     * @return the Algorithm attribute URI attribute string
     */
    @Nullable public String getAlgorithm();

    /**
     * Sets the algorithm URI attribute.
     * 
     * @param newAlgorithm the new Algorithm URI attribute string
     */
    public void setAlgorithm(@Nullable final String newAlgorithm);
    
    /**
     * Gets the Parameters child element.
     * 
     * @return the Parameters child element
     */
    @Nullable public XMLObject getParameters();

    /**
     * Sets the Parameters child element.
     * 
     * @param newParameters the new Parameters child element
     */
    public void setParameters(@Nullable final XMLObject newParameters);

}
