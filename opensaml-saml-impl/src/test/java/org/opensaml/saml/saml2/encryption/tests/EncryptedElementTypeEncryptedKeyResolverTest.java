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

package org.opensaml.saml.saml2.encryption.tests;

import org.testng.annotations.Test;

import net.shibboleth.shared.collection.CollectionSupport;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Nonnull;

import org.opensaml.core.testing.XMLObjectBaseTestCase;
import org.opensaml.saml.saml2.core.EncryptedAssertion;
import org.opensaml.saml.saml2.encryption.EncryptedElementTypeEncryptedKeyResolver;
import org.opensaml.xmlsec.encryption.EncryptedData;
import org.opensaml.xmlsec.encryption.EncryptedKey;
import org.opensaml.xmlsec.encryption.support.EncryptedKeyResolver;

/**
 * Test the SAML EncryptedElementType encrypted key resolver, with keys as peers.
 */
public class EncryptedElementTypeEncryptedKeyResolverTest extends XMLObjectBaseTestCase {
    
    /** The resolver instance to be tested. */
    private EncryptedElementTypeEncryptedKeyResolver resolver;

    /** No recipients specified to resolver, one EncryptedKey in instance. */
    @Test
    public void  testSingleEKNoRecipients() {
        String filename = 
            "/org/opensaml/saml/saml2/encryption/EncryptedElementTypeEncryptedKeyResolverSingleNoRecipient.xml";
        final EncryptedAssertion encAssertion = (EncryptedAssertion) unmarshallElement(filename);
        assert encAssertion != null;
        
        Assert.assertNotNull(encAssertion.getEncryptedData());
        final EncryptedData encData = encAssertion.getEncryptedData();
        assert encData != null;
        
        List<EncryptedKey> allKeys = encAssertion.getEncryptedKeys();
        Assert.assertFalse(allKeys.isEmpty());
        
        resolver = new EncryptedElementTypeEncryptedKeyResolver();
        
        List<EncryptedKey> resolved = generateList(encData, resolver);
        Assert.assertEquals(resolved.size(), 1, "Incorrect number of resolved EncryptedKeys found");
        
        Assert.assertTrue(resolved.get(0) == allKeys.get(0), "Unexpected EncryptedKey instance found");
    }
    
    /** Multiple recipients specified to resolver, one EncryptedKey in instance with no recipient. */
    @Test
    public void  testSingleEKMultiRecipientWithImplicitMatch() {
        String filename = 
            "/org/opensaml/saml/saml2/encryption/EncryptedElementTypeEncryptedKeyResolverSingleNoRecipient.xml";
        final EncryptedAssertion encAssertion = (EncryptedAssertion) unmarshallElement(filename);
        assert encAssertion != null;
        
        Assert.assertNotNull(encAssertion.getEncryptedData());
        final EncryptedData encData = encAssertion.getEncryptedData();
        assert encData != null;
        
        List<EncryptedKey> allKeys = encAssertion.getEncryptedKeys();
        Assert.assertFalse(allKeys.isEmpty());
        
        resolver = new EncryptedElementTypeEncryptedKeyResolver(new HashSet<>(CollectionSupport.listOf("foo", "bar", "baz")));
        
        List<EncryptedKey> resolved = generateList(encData, resolver);
        Assert.assertEquals(resolved.size(), 1, "Incorrect number of resolved EncryptedKeys found");
        
        Assert.assertTrue(resolved.get(0) == allKeys.get(0), "Unexpected EncryptedKey instance found");
    }
    
    /** One recipient specified to resolver, one matching EncryptedKey in instance. */
    @Test
    public void  testSingleEKOneRecipientWithMatch() {
        String filename = 
            "/org/opensaml/saml/saml2/encryption/EncryptedElementTypeEncryptedKeyResolverSingleWithRecipient.xml";
        final EncryptedAssertion encAssertion = (EncryptedAssertion) unmarshallElement(filename);
        assert encAssertion != null;
        
        Assert.assertNotNull(encAssertion.getEncryptedData());
        final EncryptedData encData = encAssertion.getEncryptedData();
        assert encData != null;
        
        List<EncryptedKey> allKeys = encAssertion.getEncryptedKeys();
        Assert.assertFalse(allKeys.isEmpty());
        
        resolver = new EncryptedElementTypeEncryptedKeyResolver(CollectionSupport.singleton("foo"));
        
        List<EncryptedKey> resolved = generateList(encData, resolver);
        Assert.assertEquals(resolved.size(), 1, "Incorrect number of resolved EncryptedKeys found");
        
        Assert.assertTrue(resolved.get(0) == allKeys.get(0), "Unexpected EncryptedKey instance found");
    }
    
    /** One recipient specified to resolver, zero matching EncryptedKey in instance. */
    @Test
    public void  testSingleEKOneRecipientNoMatch() {
        String filename = 
            "/org/opensaml/saml/saml2/encryption/EncryptedElementTypeEncryptedKeyResolverSingleWithRecipient.xml";
        final EncryptedAssertion encAssertion = (EncryptedAssertion) unmarshallElement(filename);
        assert encAssertion != null;
        
        Assert.assertNotNull(encAssertion.getEncryptedData());
        final EncryptedData encData = encAssertion.getEncryptedData();
        assert encData != null;
        
        List<EncryptedKey> allKeys = encAssertion.getEncryptedKeys();
        Assert.assertFalse(allKeys.isEmpty());
        
        resolver = new EncryptedElementTypeEncryptedKeyResolver(CollectionSupport.singleton("bar"));
        
        List<EncryptedKey> resolved = generateList(encData, resolver);
        Assert.assertEquals(resolved.size(), 0, "Incorrect number of resolved EncryptedKeys found");
    }
    
    /** No recipients specified to resolver. */
    @Test
    public void  testMultiEKNoRecipients() {
        String filename = "/org/opensaml/saml/saml2/encryption/EncryptedElementTypeEncryptedKeyResolverMultiple.xml";
        final EncryptedAssertion encAssertion = (EncryptedAssertion) unmarshallElement(filename);
        assert encAssertion != null;
        
        Assert.assertNotNull(encAssertion.getEncryptedData());
        final EncryptedData encData = encAssertion.getEncryptedData();
        assert encData != null;
        
        List<EncryptedKey> allKeys = encAssertion.getEncryptedKeys();
        Assert.assertFalse(allKeys.isEmpty());
        
        resolver = new EncryptedElementTypeEncryptedKeyResolver();
        
        List<EncryptedKey> resolved = generateList(encData, resolver);
        Assert.assertEquals(resolved.size(), 4, "Incorrect number of resolved EncryptedKeys found");
        
        Assert.assertTrue(resolved.get(0) == allKeys.get(0), "Unexpected EncryptedKey instance found");
        Assert.assertTrue(resolved.get(1) == allKeys.get(1), "Unexpected EncryptedKey instance found");
        Assert.assertTrue(resolved.get(2) == allKeys.get(2), "Unexpected EncryptedKey instance found");
        Assert.assertTrue(resolved.get(3) == allKeys.get(3), "Unexpected EncryptedKey instance found");
    }
    
    /** One recipient specified to resolver, one matching and one recipient-less 
     *  EncryptedKey in instance. */
    @Test
    public void  testMultiEKOneRecipientWithMatch() {
        String filename = "/org/opensaml/saml/saml2/encryption/EncryptedElementTypeEncryptedKeyResolverMultiple.xml";
        final EncryptedAssertion encAssertion = (EncryptedAssertion) unmarshallElement(filename);
        assert encAssertion != null;
        
        Assert.assertNotNull(encAssertion.getEncryptedData());
        final EncryptedData encData = encAssertion.getEncryptedData();
        assert encData != null;
        
        List<EncryptedKey> allKeys = encAssertion.getEncryptedKeys();
        Assert.assertFalse(allKeys.isEmpty());
        
        resolver = new EncryptedElementTypeEncryptedKeyResolver(CollectionSupport.singleton("foo"));
        
        List<EncryptedKey> resolved = generateList(encData, resolver);
        Assert.assertEquals(resolved.size(), 2, "Incorrect number of resolved EncryptedKeys found");
        
        Assert.assertTrue(resolved.get(0) == allKeys.get(0), "Unexpected EncryptedKey instance found");
        Assert.assertTrue(resolved.get(1) == allKeys.get(2), "Unexpected EncryptedKey instance found");
    }
    
    /** Multi recipient specified to resolver, several matching EncryptedKey in instance. */
    @Test
    public void  testMultiEKOneRecipientWithMatches() {
        String filename = "/org/opensaml/saml/saml2/encryption/EncryptedElementTypeEncryptedKeyResolverMultiple.xml";
        final EncryptedAssertion encAssertion = (EncryptedAssertion) unmarshallElement(filename);
        assert encAssertion != null;
        
        Assert.assertNotNull(encAssertion.getEncryptedData());
        final EncryptedData encData = encAssertion.getEncryptedData();
        assert encData != null;
        
        List<EncryptedKey> allKeys = encAssertion.getEncryptedKeys();
        Assert.assertFalse(allKeys.isEmpty());
        
        resolver = new EncryptedElementTypeEncryptedKeyResolver(new HashSet<>(CollectionSupport.listOf("foo", "baz")));
        
        List<EncryptedKey> resolved = generateList(encData, resolver);
        Assert.assertEquals(resolved.size(), 3, "Incorrect number of resolved EncryptedKeys found");
        
        Assert.assertTrue(resolved.get(0) == allKeys.get(0), "Unexpected EncryptedKey instance found");
        Assert.assertTrue(resolved.get(1) == allKeys.get(2), "Unexpected EncryptedKey instance found");
        Assert.assertTrue(resolved.get(2) == allKeys.get(3), "Unexpected EncryptedKey instance found");
    }
    
    /**
     * Resolve EncryptedKeys and put them in an ordered list.
     * 
     * @param encData the EncryptedData context
     * @param ekResolver the resolver to test
     * @return list of resolved EncryptedKeys
     */
    @Nonnull private List<EncryptedKey> generateList(@Nonnull final EncryptedData encData, @Nonnull final EncryptedKeyResolver ekResolver) {
        List<EncryptedKey> resolved = new ArrayList<>();
        for (EncryptedKey encKey : ekResolver.resolve(encData)) {
            resolved.add(encKey);
        }
        return resolved;
    }

}
