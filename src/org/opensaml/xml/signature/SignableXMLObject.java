/*
 * Copyright [2005] [University Corporation for Advanced Internet Development, Inc.]
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

package org.opensaml.xml.signature;

import org.opensaml.xml.DOMCachingXMLObject;

/**
 * An XMLObject whose DOM representation can be digitally signed.
 */
public interface SignableXMLObject extends DOMCachingXMLObject {
        
    /**
     * Checks to see if the element has been signed.
     * 
     * @return true if this element is signed, false if not
     */
    public boolean isSigned();
    
    /**
     * Gets the Signature XMLObject.
     * 
     * @return the Signature XMLObject
     */
    public Signature getSignature();
    
    /**
     * Sets the Signature XMLObject.
     * 
     * @param newSignature the Signature XMLObject
     */
    public void setSignature(Signature newSignature);
}