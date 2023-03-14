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

import javax.annotation.Nonnull;
import javax.xml.namespace.QName;

import org.opensaml.xmlsec.signature.support.SignatureConstants;

import net.shibboleth.shared.annotation.constraint.NotEmpty;

/**
 * XMLObject representing XML Digital Signature, version 20020212, P element.
 */
public interface P extends CryptoBinary {

    /** Element local name. */
    @Nonnull @NotEmpty public static final String DEFAULT_ELEMENT_LOCAL_NAME = "P";

    /** Default element name. */
    @Nonnull public static final QName DEFAULT_ELEMENT_NAME = new QName(SignatureConstants.XMLSIG_NS,
            DEFAULT_ELEMENT_LOCAL_NAME, SignatureConstants.XMLSIG_PREFIX);

}