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

import javax.annotation.Nonnull;

import org.opensaml.xmlsec.encryption.EncryptedData;
import org.opensaml.xmlsec.encryption.EncryptedKey;

/**
 * Component which performs validation of encrypted types prior to decryption.
 */
public interface PreDecryptionValidator {
    
    /**
     * Validate an instance of {@link EncryptedData}.
     * 
     * @param encryptedData the target to validate
     * 
     * @throws PreDecryptionValidationException if the target fails validation
     */
    void validate(@Nonnull final EncryptedData encryptedData) throws PreDecryptionValidationException;

    /**
     * Validate an instance of {@link EncryptedKey}.
     * 
     * @param encryptedKey the target to validate
     * 
     * @throws PreDecryptionValidationException if the target fails validation
     */
    void validate(@Nonnull final EncryptedKey encryptedKey) throws PreDecryptionValidationException;

}