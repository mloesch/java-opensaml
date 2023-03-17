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

package org.opensaml.saml.ext.saml2mdrpi;

import java.util.List;

import javax.annotation.Nonnull;
import javax.xml.namespace.QName;

import org.opensaml.saml.common.SAMLObject;
import org.opensaml.saml.common.xml.SAMLConstants;

import net.shibboleth.shared.annotation.constraint.NotEmpty;

/**
 * Representation of the <code>&lt;mdrpi:PublicationPath&gt;</code> element.
 *
 * @see <a
 * href="http://docs.oasis-open.org/security/saml/Post2.0/saml-metadata-rpi/v1.0/">http://docs.oasis-open.org/security
 * /saml/Post2.0/saml-metadata-rpi/v1.0/</a>
 */
public interface PublicationPath extends SAMLObject {

    /** Name of the element inside the Extensions. */
    @Nonnull @NotEmpty public static final String DEFAULT_ELEMENT_LOCAL_NAME = "PublicationPath";

    /** Default element name. */
    @Nonnull public static final QName DEFAULT_ELEMENT_NAME = new QName(SAMLConstants.SAML20MDRPI_NS, DEFAULT_ELEMENT_LOCAL_NAME,
            SAMLConstants.SAML20MDRPI_PREFIX);

    /** Local name of the XSI type. */
    @Nonnull @NotEmpty public static final String TYPE_LOCAL_NAME = "PublicationPathType";

    /** QName of the XSI type. */
    @Nonnull public static final QName TYPE_NAME =
            new QName(SAMLConstants.SAML20MDRPI_NS, TYPE_LOCAL_NAME, SAMLConstants.SAML20MDRPI_PREFIX);

    /**
     * Get the {@link Publication}s.
     * 
     * @return the list of publications
     */
    public List<Publication> getPublications();

}
