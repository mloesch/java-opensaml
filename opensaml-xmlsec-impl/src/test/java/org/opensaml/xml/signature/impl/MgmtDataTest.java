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

package org.opensaml.xml.signature.impl;


import org.opensaml.core.xml.XMLObjectProviderBaseTestCase;
import org.opensaml.xmlsec.signature.MgmtData;

/**
 *
 */
public class MgmtDataTest extends XMLObjectProviderBaseTestCase {
    
    private String expectedStringContent;

    /**
     * Constructor
     *
     */
    public MgmtDataTest() {
        singleElementFile = "/data/org/opensaml/xml/signature/impl/MgmtData.xml";
        
    }

    /** {@inheritDoc} */
    protected void setUp() throws Exception {
        super.setUp();
        
        expectedStringContent = "someMgmtData";
    }

    /** {@inheritDoc} */
    public void testSingleElementUnmarshall() {
        MgmtData keyName = (MgmtData) unmarshallElement(singleElementFile);
        
        assertNotNull("MgmtData", keyName);
        assertEquals("MgmtData value", keyName.getValue(), expectedStringContent);
    }

    /** {@inheritDoc} */
    public void testSingleElementMarshall() {
        MgmtData keyName = (MgmtData) buildXMLObject(MgmtData.DEFAULT_ELEMENT_NAME);
        keyName.setValue(expectedStringContent);
        
        assertEquals(expectedDOM, keyName);
    }

}
