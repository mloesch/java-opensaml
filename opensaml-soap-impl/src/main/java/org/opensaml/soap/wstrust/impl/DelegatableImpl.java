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

package org.opensaml.soap.wstrust.impl;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.schema.XSBooleanValue;
import org.opensaml.soap.wstrust.Delegatable;

/**
 * DelegatableImpl.
 * 
 */
public class DelegatableImpl extends AbstractWSTrustObject implements Delegatable {

    /** Default value. */
    @Nonnull private static final Boolean DEFAULT_VALUE = Boolean.FALSE;

    /** The wst:Forwardable content. */
    @Nullable private XSBooleanValue value;

    /**
     * Constructor. Default value is <code>FALSE</code>.
     * 
     * @param namespaceURI The namespace of the element
     * @param elementLocalName The local name of the element
     * @param namespacePrefix The namespace prefix of the element
     */
    public DelegatableImpl(@Nullable final String namespaceURI, @Nonnull final String elementLocalName,
            @Nullable final String namespacePrefix) {
        super(namespaceURI, elementLocalName, namespacePrefix);
        value = new XSBooleanValue(DEFAULT_VALUE, false);
    }

    /** {@inheritDoc} */
    @Nullable public XSBooleanValue getValue() {
        return value;
    }

    /** {@inheritDoc} */
    public void setValue(@Nullable final XSBooleanValue newValue) {
        if (newValue != null) {
            value = prepareForAssignment(value, newValue);
        } else {
            value = prepareForAssignment(value, new XSBooleanValue(DEFAULT_VALUE, false));
        }
    }

    /** {@inheritDoc} */
    @Nullable public List<XMLObject> getOrderedChildren() {
        return null;
    }
}
