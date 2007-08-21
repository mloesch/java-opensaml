/*
 * Copyright [2007] [University Corporation for Advanced Internet Development, Inc.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.saml2.binding;

import org.opensaml.common.SAMLObject;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.binding.artifact.AbstractSAML2Artifact;

/**
 * Extensions to the base SAML message context that carries artifact related information.
 * 
 * @param <InboundMessageType> type of inbound SAML message
 * @param <OutboundMessageType> type of outbound SAML message
 * @param <NameIdentifierType> type of name identifier used for subjects
 */
public interface SAML2ArtifactMessageContext<InboundMessageType extends SAMLObject, OutboundMessageType extends SAMLObject, NameIdentifierType extends SAMLObject>
        extends SAMLMessageContext<InboundMessageType, OutboundMessageType, NameIdentifierType> {

    /**
     * Gets the artifact to be resolved.
     * 
     * @return artifact to be resolved
     */
    public AbstractSAML2Artifact getArtifact();

    /**
     * Sets the artifact to be resolved.
     * 
     * @param artifact artifact to be resolved
     */
    public void setArtifact(AbstractSAML2Artifact artifact);
}