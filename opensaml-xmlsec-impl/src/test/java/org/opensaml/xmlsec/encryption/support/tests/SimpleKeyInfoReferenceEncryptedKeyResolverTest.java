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

package org.opensaml.xmlsec.encryption.support.tests;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.opensaml.core.testing.XMLObjectBaseTestCase;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.xmlsec.encryption.EncryptedData;
import org.opensaml.xmlsec.encryption.EncryptedKey;
import org.opensaml.xmlsec.encryption.support.EncryptedKeyResolver;
import org.opensaml.xmlsec.encryption.support.SimpleKeyInfoReferenceEncryptedKeyResolver;
import org.opensaml.xmlsec.mock.SignableSimpleXMLObject;
import org.opensaml.xmlsec.signature.KeyInfo;
import org.opensaml.xmlsec.signature.KeyInfoReference;
import org.testng.Assert;
import org.testng.annotations.Test;

import net.shibboleth.shared.collection.CollectionSupport;

/**
 * Test the encrypted key resolver which dereferences KeyInfoReferences.
 */
public class SimpleKeyInfoReferenceEncryptedKeyResolverTest extends XMLObjectBaseTestCase {
    
    /** The resolver instance to be tested. */
    private SimpleKeyInfoReferenceEncryptedKeyResolver resolver;
    
    /** No recipients specified to resolver, one EncryptedKey in instance. */
    @Test
    public void testSingleEKNoRecipient() {
        final String filename =  "/org/opensaml/xmlsec/encryption/support/SimpleKeyInfoReferenceEncryptedKeyResolverSingle.xml";
        final SignableSimpleXMLObject sxo =  (SignableSimpleXMLObject) unmarshallElement(filename);
        assert sxo != null;
        Assert.assertNotNull(sxo.getSimpleXMLObjects().get(0));
        Assert.assertNotNull(sxo.getSimpleXMLObjects().get(0).getEncryptedData());
        
        final EncryptedData encData = sxo.getSimpleXMLObjects().get(0).getEncryptedData();
        assert encData != null;
        final KeyInfo keyInfo = encData.getKeyInfo();
        assert keyInfo != null;
        Assert.assertFalse(keyInfo.getXMLObjects(KeyInfoReference.DEFAULT_ELEMENT_NAME).isEmpty());
        
        List<EncryptedKey> allKeys = getEncryptedKeys(sxo);
        Assert.assertFalse(allKeys.isEmpty());
        
        resolver = new SimpleKeyInfoReferenceEncryptedKeyResolver();
        
        List<EncryptedKey> resolved = generateList(encData, resolver);
        Assert.assertEquals(resolved.size(), 1, "Incorrect number of resolved EncryptedKeys found");
        
        Assert.assertTrue(resolved.get(0) == allKeys.get(0), "Unexpected EncryptedKey instance found");
    }
    
    /** One recipient specified to resolver, one EncryptedKey in instance. */
    @Test
    public void testSingleEKWithRecipient() {
        final String filename =  "/org/opensaml/xmlsec/encryption/support/SimpleKeyInfoReferenceEncryptedKeyResolverSingle.xml";
        final SignableSimpleXMLObject sxo =  (SignableSimpleXMLObject) unmarshallElement(filename);
        assert sxo != null;
        Assert.assertNotNull(sxo.getSimpleXMLObjects().get(0));
        Assert.assertNotNull(sxo.getSimpleXMLObjects().get(0).getEncryptedData());
        
        final EncryptedData encData = sxo.getSimpleXMLObjects().get(0).getEncryptedData();
        assert encData != null;
        final KeyInfo keyInfo = encData.getKeyInfo();
        assert keyInfo != null;
        Assert.assertFalse(keyInfo.getXMLObjects(KeyInfoReference.DEFAULT_ELEMENT_NAME).isEmpty());
        
        List<EncryptedKey> allKeys = getEncryptedKeys(sxo);
        Assert.assertFalse(allKeys.isEmpty());
        
        resolver = new SimpleKeyInfoReferenceEncryptedKeyResolver(CollectionSupport.singleton("foo"));
        
        List<EncryptedKey> resolved = generateList(encData, resolver);
        Assert.assertEquals(resolved.size(), 1, "Incorrect number of resolved EncryptedKeys found");
        
        Assert.assertTrue(resolved.get(0) == allKeys.get(0), "Unexpected EncryptedKey instance found");
    }
    
    /** One recipient specified to resolver, three EncryptedKeys in instance, two KeyInfoReference references. */
    @Test
    public void testMultiEKWithOneRecipient() {
        final String filename =  "/org/opensaml/xmlsec/encryption/support/SimpleKeyInfoReferenceEncryptedKeyResolverMultiple.xml";
        final SignableSimpleXMLObject sxo =  (SignableSimpleXMLObject) unmarshallElement(filename);
        assert sxo != null;
        Assert.assertNotNull(sxo.getSimpleXMLObjects().get(0));
        Assert.assertNotNull(sxo.getSimpleXMLObjects().get(0).getEncryptedData());
        
        final EncryptedData encData = sxo.getSimpleXMLObjects().get(0).getEncryptedData();
        assert encData != null;
        final KeyInfo keyInfo = encData.getKeyInfo();
        assert keyInfo != null;
        Assert.assertFalse(keyInfo.getKeyInfoReferences().isEmpty());
        
        List<EncryptedKey> allKeys = getEncryptedKeys(sxo);
        Assert.assertFalse(allKeys.isEmpty());
        
        resolver = new SimpleKeyInfoReferenceEncryptedKeyResolver(CollectionSupport.singleton("foo"));
        
        List<EncryptedKey> resolved = generateList(encData, resolver);
        Assert.assertEquals(resolved.size(), 1, "Incorrect number of resolved EncryptedKeys found");
        
        Assert.assertTrue(resolved.get(0) == allKeys.get(0), "Unexpected EncryptedKey instance found");
    }
    
    /**
     * Two recipients specified to resolver, three EncryptedKeys in instance, 
     * two RetrievalMethod references.
     */
    @Test
    public void testMultiEKWithTwoRecipients() {
        final String filename =  "/org/opensaml/xmlsec/encryption/support/SimpleKeyInfoReferenceEncryptedKeyResolverMultiple.xml";
        final SignableSimpleXMLObject sxo =  (SignableSimpleXMLObject) unmarshallElement(filename);
        assert sxo != null;
        Assert.assertNotNull(sxo.getSimpleXMLObjects().get(0));
        Assert.assertNotNull(sxo.getSimpleXMLObjects().get(0).getEncryptedData());
        
        final EncryptedData encData = sxo.getSimpleXMLObjects().get(0).getEncryptedData();
        assert encData != null;
        final KeyInfo keyInfo = encData.getKeyInfo();
        assert keyInfo != null;
        Assert.assertFalse(keyInfo.getKeyInfoReferences().isEmpty());
        
        List<EncryptedKey> allKeys = getEncryptedKeys(sxo);
        Assert.assertFalse(allKeys.isEmpty());
        
        resolver = new SimpleKeyInfoReferenceEncryptedKeyResolver(CollectionSupport.setOf("foo", "baz"));
        
        List<EncryptedKey> resolved = generateList(encData, resolver);
        Assert.assertEquals(resolved.size(), 2, "Incorrect number of resolved EncryptedKeys found");
        
        Assert.assertTrue(resolved.get(0) == allKeys.get(0), "Unexpected EncryptedKey instance found");
        Assert.assertTrue(resolved.get(1) == allKeys.get(2), "Unexpected EncryptedKey instance found");
    }
    
    /**
     * Extract all the EncryptedKey's from the SimpleXMLObject.
     * 
     * @param sxo the mock object to process
     * @return a list of EncryptedKey elements
     */
    @Nonnull private List<EncryptedKey> getEncryptedKeys(@Nonnull final SignableSimpleXMLObject sxo) {
        List<EncryptedKey> allKeys = new ArrayList<>();
        for (XMLObject xmlObject : sxo.getUnknownXMLObjects()) {
           if (xmlObject instanceof KeyInfo)  {
               allKeys.addAll(((KeyInfo) xmlObject).getEncryptedKeys());
           }
        }
        return allKeys;
    }

    /**
     * Resolve EncryptedKeys and put them in an ordered list.
     * 
     * @param encData the EncryptedData context
     * @param ekResolver the resolver to test
     * @return list of resolved EncryptedKeys
     */
    @Nonnull private List<EncryptedKey> generateList(@Nonnull final EncryptedData encData,
            @Nonnull final EncryptedKeyResolver ekResolver) {
        List<EncryptedKey> resolved = new ArrayList<>();
        for (EncryptedKey encKey : ekResolver.resolve(encData)) {
            resolved.add(encKey);
        }
        return resolved;
    }

}