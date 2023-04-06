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

/**
 * 
 */

package org.opensaml.saml.saml2.metadata.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.util.XMLObjectChildrenList;
import org.opensaml.saml.saml2.metadata.AssertionIDRequestService;
import org.opensaml.saml.saml2.metadata.AuthzService;
import org.opensaml.saml.saml2.metadata.Endpoint;
import org.opensaml.saml.saml2.metadata.NameIDFormat;
import org.opensaml.saml.saml2.metadata.PDPDescriptor;

import net.shibboleth.shared.annotation.constraint.Live;
import net.shibboleth.shared.annotation.constraint.NotLive;
import net.shibboleth.shared.annotation.constraint.Unmodifiable;
import net.shibboleth.shared.collection.CollectionSupport;

/**
 * Concrete implementation of {@link PDPDescriptor}.
 */
public class PDPDescriptorImpl extends RoleDescriptorImpl implements PDPDescriptor {

    /** AuthzService children. */
    @Nonnull private final XMLObjectChildrenList<AuthzService> authzServices;

    /** AssertionIDRequestService children. */
    @Nonnull private final XMLObjectChildrenList<AssertionIDRequestService> assertionIDRequestServices;

    /** NameIDFormat children. */
    @Nonnull private final XMLObjectChildrenList<NameIDFormat> nameIDFormats;

    /**
     * Constructor.
     * 
     * @param namespaceURI the namespace the element is in
     * @param elementLocalName the local name of the XML element this Object represents
     * @param namespacePrefix the prefix for the given namespace
     */
    protected PDPDescriptorImpl(@Nullable final String namespaceURI, @Nonnull final String elementLocalName,
            @Nullable final String namespacePrefix) {
        super(namespaceURI, elementLocalName, namespacePrefix);
        authzServices = new XMLObjectChildrenList<>(this);
        assertionIDRequestServices = new XMLObjectChildrenList<>(this);
        nameIDFormats = new XMLObjectChildrenList<>(this);
    }

    /** {@inheritDoc} */
    @Nonnull @Live public List<AuthzService> getAuthzServices() {
        return authzServices;
    }

    /** {@inheritDoc} */
    @Nonnull @Live public List<AssertionIDRequestService> getAssertionIDRequestServices() {
        return assertionIDRequestServices;
    }

    /** {@inheritDoc} */
    @Nonnull @Live public List<NameIDFormat> getNameIDFormats() {
        return nameIDFormats;
    }
    
    /** {@inheritDoc} */
    @Nonnull @NotLive @Unmodifiable public List<Endpoint> getEndpoints() {
        final List<Endpoint> endpoints = new ArrayList<>();
        endpoints.addAll(authzServices);
        endpoints.addAll(assertionIDRequestServices);
        return CollectionSupport.copyToList(endpoints);
    }
    
    /** {@inheritDoc} */
    @Nonnull @NotLive @Unmodifiable public List<Endpoint> getEndpoints(@Nonnull final QName type) {
        if (type.equals(AuthzService.DEFAULT_ELEMENT_NAME)) {
            return CollectionSupport.copyToList(authzServices);
        } else if(type.equals(AssertionIDRequestService.DEFAULT_ELEMENT_NAME)) {
            return CollectionSupport.copyToList(assertionIDRequestServices);
        }
        
        return CollectionSupport.emptyList();
    }

    /** {@inheritDoc} */
    @Override
    @Nullable @NotLive @Unmodifiable public List<XMLObject> getOrderedChildren() {
        final ArrayList<XMLObject> children = new ArrayList<>();

        final List<XMLObject> parentChildren = super.getOrderedChildren();
        if (parentChildren != null) {
            children.addAll(parentChildren);
        }

        children.addAll(authzServices);
        children.addAll(assertionIDRequestServices);
        children.addAll(nameIDFormats);

        return CollectionSupport.copyToList(children);
    }

}