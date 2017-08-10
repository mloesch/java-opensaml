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

package org.opensaml.saml.saml2.core.impl;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.saml.common.AbstractSAMLObjectUnmarshaller;
import org.opensaml.saml.saml2.core.BaseID;
import org.opensaml.saml.saml2.core.EncryptedID;
import org.opensaml.saml.saml2.core.NameID;
import org.opensaml.saml.saml2.core.Subject;
import org.opensaml.saml.saml2.core.SubjectConfirmation;

/**
 * A thread-safe Unmarshaller for {@link org.opensaml.saml.saml2.core.Subject} objects.
 */
public class SubjectUnmarshaller extends AbstractSAMLObjectUnmarshaller {

    /** {@inheritDoc} */
    protected void processChildElement(final XMLObject parentObject, final XMLObject childObject) throws UnmarshallingException {
        final Subject subject = (Subject) parentObject;

        if (childObject instanceof BaseID) {
            subject.setBaseID((BaseID) childObject);
        } else if (childObject instanceof NameID) {
            subject.setNameID((NameID) childObject);
        } else if (childObject instanceof EncryptedID) {
            subject.setEncryptedID((EncryptedID) childObject);
        } else if (childObject instanceof SubjectConfirmation) {
            subject.getSubjectConfirmations().add((SubjectConfirmation) childObject);
        } else {
            super.processChildElement(parentObject, childObject);
        }
    }
}