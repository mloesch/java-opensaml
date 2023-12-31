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

package org.opensaml.xmlsec.encryption.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.opensaml.xmlsec.encryption.EncryptedData;
import org.opensaml.xmlsec.encryption.EncryptedKey;
import org.opensaml.xmlsec.signature.KeyInfo;

import net.shibboleth.shared.collection.CollectionSupport;
import net.shibboleth.shared.logic.Constraint;

/**
 * Implementation of {@link EncryptedKeyResolver} which finds {@link EncryptedKey} elements
 * within the {@link org.opensaml.xmlsec.signature.KeyInfo} of the {@link EncryptedData} context.
 */
public class InlineEncryptedKeyResolver extends AbstractEncryptedKeyResolver {
    
    /** Constructor. */
    public InlineEncryptedKeyResolver() {
        
    }

    /** 
     * Constructor. 
     * 
     * @param recipients the set of recipients
     */
    public InlineEncryptedKeyResolver(@Nullable final Set<String> recipients) {
        super(recipients);
    }

    /** 
     * Constructor. 
     * 
     * @param recipient the recipient
     */
    public InlineEncryptedKeyResolver(@Nullable final String recipient) {
        this(recipient != null ? CollectionSupport.singleton(recipient) : null);
    }

    /** {@inheritDoc} */
    @Nonnull public Iterable<EncryptedKey> resolve(@Nonnull final EncryptedData encryptedData) {
        Constraint.isNotNull(encryptedData, "EncryptedData cannot be null");
        
        final List<EncryptedKey> resolvedEncKeys = new ArrayList<>();
        
        final KeyInfo keyInfo = encryptedData.getKeyInfo();
        if (keyInfo == null) {
            return resolvedEncKeys;
        }
        
        for (final EncryptedKey encKey : keyInfo.getEncryptedKeys()) {
            if (matchRecipient(encKey.getRecipient())) {
                resolvedEncKeys.add(encKey);
            }
        }
        
        return resolvedEncKeys;
    }

}