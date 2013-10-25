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

package org.opensaml.storage.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Nonnull;

import net.shibboleth.utilities.java.support.component.ComponentInitializationException;
import net.shibboleth.utilities.java.support.net.HttpServletRequestResponseContext;
import net.shibboleth.utilities.java.support.net.ThreadLocalHttpServletRequestProxy;
import net.shibboleth.utilities.java.support.security.DataSealer;
import net.shibboleth.utilities.java.support.security.DataSealerTest;

import org.junit.Assert;
import org.opensaml.storage.StorageRecord;
import org.opensaml.storage.StorageService;
import org.opensaml.storage.StorageServiceTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test of {@link JSONRequestScopedStorageService} implementation.
 */
public class JSONRequestScopedStorageServiceTest extends StorageServiceTest {

    String keyStorePath;

    /**
     * Copy the JKS from the classpath into the filesystem and get the name.
     * @throws ComponentInitializationException 
     * 
     * @throws IOException
     */
    @BeforeClass public void setUp() throws ComponentInitializationException {
        try {
            File out = File.createTempFile("testDataSeal", "file");

            final InputStream inStream = DataSealerTest.class.getResourceAsStream(
                    "/org/opensaml/storage/impl/SealerKeyStore.jks");
    
            keyStorePath = out.getAbsolutePath();
    
            final OutputStream outStream = new FileOutputStream(out, false);
    
            final byte buffer[] = new byte[1024];
    
            final int bytesRead = inStream.read(buffer);
            outStream.write(buffer, 0, bytesRead);
            outStream.close();
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }

        super.setUp();
    }
    
    /** {@inheritDoc} */
    @Nonnull protected StorageService getStorageService() {
        JSONRequestScopedStorageService ss = new JSONRequestScopedStorageService();
        ss.setCleanupInterval(0);

        DataSealer sealer = new DataSealer();
        sealer.setCipherKeyAlias("secret");
        sealer.setCipherKeyPassword("kpassword");

        sealer.setKeystorePassword("password");
        sealer.setKeystorePath(keyStorePath);

        try {
            sealer.initialize();
        } catch (ComponentInitializationException e) {
            Assert.fail(e.getMessage());
        }

        ss.setDataSealer(sealer);
        ss.setServletRequest(new ThreadLocalHttpServletRequestProxy());
        return ss;
    }

    /** {@inheritDoc} */
    protected void threadInit() {
        super.threadInit();
        HttpServletRequestResponseContext.loadCurrent(new MockHttpServletRequest(), new MockHttpServletResponse());
    }
    
    @Test public void invalidConfig() {
        JSONRequestScopedStorageService ss = new JSONRequestScopedStorageService();
        
        try {
            ss.initialize();
            Assert.fail();
        } catch (ComponentInitializationException e) {
            
        }
    }
    
    @Test(threadPoolSize = 1, invocationCount = 1,  timeOut = 10000)
    public void loadSave() throws IOException {
        threadInit();
        
        JSONRequestScopedStorageService ss = (JSONRequestScopedStorageService) shared;
        
        ss.load(null);
        Assert.assertFalse(ss.isDirty());
        
        String context = Long.toString(random.nextLong());
        
        for (int i = 1; i <= 10; i++) {
            ss.create(context, Integer.toString(i), Integer.toString(i + 1), System.currentTimeMillis() + 300000);
        }
        
        Assert.assertTrue(ss.isDirty());
        String pickled = ((JSONRequestScopedStorageService) shared).save();
        ss.load(null);
        
        for (int i = 1; i <= 10; i++) {
            Assert.assertNull(ss.read(context, Integer.toString(i)));
        }
        
        ss.load(pickled);
        Assert.assertFalse(ss.isDirty());
        for (int i = 1; i <= 10; i++) {
            StorageRecord record = ss.read(context, Integer.toString(i));
            Assert.assertNotNull(record);
            Assert.assertEquals(record.getValue(), Integer.toString(i + 1));
            Assert.assertTrue(record.getExpiration() < System.currentTimeMillis() + 300000);
        }
    }
}