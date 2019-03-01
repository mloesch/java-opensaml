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

package org.opensaml.saml.ext.saml2mdattr.impl;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.time.Instant;

import org.opensaml.core.xml.XMLObjectProviderBaseTestCase;
import org.opensaml.saml.ext.saml2mdattr.EntityAttributes;
import org.opensaml.saml.saml2.core.Assertion;
import org.opensaml.saml.saml2.core.Attribute;

/** Unit test for {@link EntityAttributes}. */
public class EntityAttributesTest extends XMLObjectProviderBaseTestCase {

    /** Constructor. */
    public EntityAttributesTest() {
        singleElementFile = "/org/opensaml/saml/ext/saml2mdattr/EntityAttributes.xml";
        childElementsFile = "/org/opensaml/saml/ext/saml2mdattr/EntityAttributesChildElements.xml";
    }

    /** {@inheritDoc} */
    @Test
    public void testSingleElementUnmarshall() {
        EntityAttributes attributes = (EntityAttributes) unmarshallElement(singleElementFile);
        Assert.assertNotNull(attributes);
        Assert.assertTrue(attributes.getAssertions().isEmpty());
        Assert.assertTrue(attributes.getAttributes().isEmpty());
    }

    /** {@inheritDoc} */
    @Test
    public void testChildElementsUnmarshall() {
        EntityAttributes attributes = (EntityAttributes) unmarshallElement(childElementsFile);
        Assert.assertNotNull(attributes);

        Assert.assertEquals(attributes.getAssertions().size(), 2);
        Assert.assertEquals(attributes.getAttributes().size(), 3);
    }

    /** {@inheritDoc} */
    @Test
    public void testSingleElementMarshall() {
        EntityAttributes attributes = (EntityAttributes) buildXMLObject(EntityAttributes.DEFAULT_ELEMENT_NAME);

        assertXMLEquals(expectedDOM, attributes);
    }

    /** {@inheritDoc} */
    @Test
    public void testChildElementsMarshall() {
        Assertion assertion1 = (Assertion) buildXMLObject(Assertion.DEFAULT_ELEMENT_NAME);
        assertion1.setIssueInstant(Instant.parse("1984-08-26T10:01:30Z"));
        Assertion assertion2 = (Assertion) buildXMLObject(Assertion.DEFAULT_ELEMENT_NAME);
        assertion2.setIssueInstant(Instant.parse("1984-08-26T10:01:30Z"));
        
        Attribute attrib1 = (Attribute) buildXMLObject(Attribute.DEFAULT_ELEMENT_NAME);
        attrib1.setName("attrib1");
        Attribute attrib2 = (Attribute) buildXMLObject(Attribute.DEFAULT_ELEMENT_NAME);
        attrib2.setName("attrib2");
        Attribute attrib3 = (Attribute) buildXMLObject(Attribute.DEFAULT_ELEMENT_NAME);
        attrib3.setName("attrib3");
        
        EntityAttributes attributes = (EntityAttributes) buildXMLObject(EntityAttributes.DEFAULT_ELEMENT_NAME);
        attributes.getAssertions().add(assertion1);
        attributes.getAttributes().add(attrib1);
        attributes.getAssertions().add(assertion2);
        attributes.getAttributes().add(attrib2);
        attributes.getAttributes().add(attrib3);

        Assert.assertEquals(attributes.getOrderedChildren().size(), 5);
        assertXMLEquals(expectedChildElementsDOM, attributes);
    }
}