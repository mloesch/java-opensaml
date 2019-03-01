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

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;

import java.time.Instant;

import javax.xml.namespace.QName;

import org.opensaml.core.xml.XMLObjectProviderBaseTestCase;
import org.opensaml.saml.common.xml.SAMLConstants;
import org.opensaml.saml.saml2.core.SubjectConfirmationData;

/**
 * Test case for creating, marshalling, and unmarshalling
 * {@link org.opensaml.saml.saml2.core.impl.SubjectConfirmationDataImpl}.
 */
public class SubjectConfirmationDataTest extends XMLObjectProviderBaseTestCase {

    /** Expected NotBefore value */
    private Instant expectedNotBefore;

    /** Expected NotOnOrAfter value */
    private Instant expectedNotOnOrAfter;

    /** Expected Recipient value */
    private String expectedRecipient;

    /** Expected InResponseTo value */
    private String expectedInResponseTo;

    /** Expected Address value */
    private String expectedAddress;

    /** Constructor */
    public SubjectConfirmationDataTest() {
        singleElementFile = "/org/opensaml/saml/saml2/core/impl/SubjectConfirmationData.xml";
        singleElementOptionalAttributesFile = "/org/opensaml/saml/saml2/core/impl/SubjectConfirmationDataOptionalAttributes.xml";
    }

    @BeforeMethod
    protected void setUp() throws Exception {
        expectedNotBefore = Instant.parse("1984-08-26T10:01:30.043Z");
        expectedNotOnOrAfter = Instant.parse("1984-08-26T10:11:30.043Z");
        expectedRecipient = "recipient";
        expectedInResponseTo = "inresponse";
        expectedAddress = "address";
    }

    /** {@inheritDoc} */
    @Test
    public void testSingleElementUnmarshall() {
        SubjectConfirmationData subjectCD = (SubjectConfirmationData) unmarshallElement(singleElementFile);

        Instant notBefore = subjectCD.getNotBefore();
        Assert.assertEquals(notBefore, expectedNotBefore, "NotBefore was " + notBefore + ", expected " + expectedNotBefore);
    }

    /** {@inheritDoc} */
    @Test
    public void testSingleElementOptionalAttributesUnmarshall() {
        SubjectConfirmationData subjectCD = (SubjectConfirmationData) unmarshallElement(singleElementOptionalAttributesFile);

        Instant notBefore = subjectCD.getNotBefore();
        Assert.assertEquals(notBefore, expectedNotBefore, "NotBefore was " + notBefore + ", expected " + expectedNotBefore);

        Instant notOnOrAfter = subjectCD.getNotOnOrAfter();
        Assert.assertEquals(notOnOrAfter, expectedNotOnOrAfter,
                "NotOnOrAfter was " + notOnOrAfter + ", expected " + expectedNotOnOrAfter);

        String recipient = subjectCD.getRecipient();
        Assert.assertEquals(recipient, expectedRecipient, "Recipient was " + recipient + ", expected " + expectedRecipient);

        String inResponseTo = subjectCD.getInResponseTo();
        Assert.assertEquals(inResponseTo, expectedInResponseTo,
                "InResponseTo was " + inResponseTo + ", expected " + expectedInResponseTo);

        String address = subjectCD.getAddress();
        Assert.assertEquals(address, expectedAddress, "Address was " + address + ", expected " + expectedAddress);
    }

    /** {@inheritDoc} */
    @Test
    public void testSingleElementMarshall() {
        QName qname = new QName(SAMLConstants.SAML20_NS, SubjectConfirmationData.DEFAULT_ELEMENT_LOCAL_NAME,
                SAMLConstants.SAML20_PREFIX);
        SubjectConfirmationData subjectCD = (SubjectConfirmationData) buildXMLObject(qname);

        subjectCD.setNotBefore(expectedNotBefore);
        assertXMLEquals(expectedDOM, subjectCD);
    }

    /** {@inheritDoc} */
    @Test
    public void testSingleElementOptionalAttributesMarshall() {
        QName qname = new QName(SAMLConstants.SAML20_NS, SubjectConfirmationData.DEFAULT_ELEMENT_LOCAL_NAME,
                SAMLConstants.SAML20_PREFIX);
        SubjectConfirmationData subjectCD = (SubjectConfirmationData) buildXMLObject(qname);

        subjectCD.setNotBefore(expectedNotBefore);
        subjectCD.setNotOnOrAfter(expectedNotOnOrAfter);
        subjectCD.setRecipient(expectedRecipient);
        subjectCD.setInResponseTo(expectedInResponseTo);
        subjectCD.setAddress(expectedAddress);

        assertXMLEquals(expectedOptionalAttributesDOM, subjectCD);
    }
}