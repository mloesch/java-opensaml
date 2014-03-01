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

package org.opensaml.saml.saml1.profile.impl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.opensaml.profile.ProfileException;
import org.opensaml.profile.action.AbstractConditionalProfileAction;
import org.opensaml.profile.action.ActionSupport;
import org.opensaml.profile.action.EventIds;
import org.opensaml.profile.context.ProfileRequestContext;
import org.opensaml.profile.context.navigate.OutboundMessageContextLookup;

import net.shibboleth.utilities.java.support.annotation.Duration;
import net.shibboleth.utilities.java.support.annotation.constraint.NonNegative;
import net.shibboleth.utilities.java.support.component.ComponentSupport;
import net.shibboleth.utilities.java.support.logic.Constraint;

import org.joda.time.DateTime;
import org.opensaml.messaging.context.navigate.MessageLookup;
import org.opensaml.saml.saml1.core.Assertion;
import org.opensaml.saml.saml1.core.Conditions;
import org.opensaml.saml.saml1.core.Response;
import org.opensaml.saml.saml1.profile.SAML1ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.base.Functions;

/**
 * Action that adds the <code>NotBefore</code> attribute to every {@link Assertion} in a {@link Response}
 * message. If the containing {@link Conditions} is not present, it will be created.
 * 
 * @event {@link EventIds#PROCEED_EVENT_ID}
 * @event {@link EventIds#INVALID_MSG_CTX}
 */
public class AddNotOnOrAfterConditionToAssertions extends AbstractConditionalProfileAction {

    /** Class logger. */
    @Nonnull private final Logger log = LoggerFactory.getLogger(AddNotOnOrAfterConditionToAssertions.class);

    /** Strategy used to locate the {@link Response} to operate on. */
    @Nonnull private Function<ProfileRequestContext,Response> responseLookupStrategy;
    
    /** Strategy to obtain assertion lifetime policy. */
    @Nullable private Function<ProfileRequestContext,Long> assertionLifetimeStrategy;
    
    /** Default lifetime to use to establish timestamp. */
    @Duration @NonNegative private long defaultAssertionLifetime;
    
    /** Response to modify. */
    @Nullable private Response response;

    /** Constructor. */
    public AddNotOnOrAfterConditionToAssertions() {
        responseLookupStrategy =
                Functions.compose(new MessageLookup<>(Response.class), new OutboundMessageContextLookup());
        
        defaultAssertionLifetime = 5 * 60 * 1000;
    }

    /**
     * Set the strategy used to locate the {@link Response} to operate on.
     * 
     * @param strategy strategy used to locate the {@link Response} to operate on
     */
    public synchronized void setResponseLookupStrategy(
            @Nonnull final Function<ProfileRequestContext, Response> strategy) {
        ComponentSupport.ifInitializedThrowUnmodifiabledComponentException(this);

        responseLookupStrategy = Constraint.isNotNull(strategy, "Response lookup strategy cannot be null");
    }
    
    /**
     * Set strategy function to obtain assertion lifetime.
     * 
     * @param strategy strategy function
     */
    public synchronized void setAssertionLifetimeStrategy(
            @Nullable final Function<ProfileRequestContext,Long> strategy) {
        ComponentSupport.ifInitializedThrowUnmodifiabledComponentException(this);
        
        assertionLifetimeStrategy = strategy;
    }
    
    /**
     * Set the default assertion lifetime in milliseconds.
     * 
     * @param lifetime  default lifetime in milliseconds
     */
    public synchronized void setDefaultAssertionLifetime(@Duration @NonNegative long lifetime) {
        ComponentSupport.ifInitializedThrowUnmodifiabledComponentException(this);
        
        defaultAssertionLifetime = Constraint.isGreaterThanOrEqual(0, lifetime,
                "Default assertion lifetime must be greater than or equal to 0");
    }
    
    /** {@inheritDoc} */
    @Override
    protected boolean doPreExecute(@Nonnull final ProfileRequestContext profileRequestContext) throws ProfileException {
        log.debug("{} Attempting to add NotOnOrAfter condition to every Assertion in outgoing Response",
                getLogPrefix());

        response = responseLookupStrategy.apply(profileRequestContext);
        if (response == null) {
            log.debug("{} No SAML response located in current profile request context", getLogPrefix());
            ActionSupport.buildEvent(profileRequestContext, EventIds.INVALID_MSG_CTX);
            return false;
        } else if (response.getAssertions().isEmpty()) {
            log.debug("{} No assertions in response message, nothing to do", getLogPrefix());
            return false;
        }
        
        return super.doPreExecute(profileRequestContext);
    }
    
    /** {@inheritDoc} */
    @Override
    protected void doExecute(@Nonnull final ProfileRequestContext profileRequestContext) throws ProfileException {

        final Long lifetime = assertionLifetimeStrategy != null ?
                assertionLifetimeStrategy.apply(profileRequestContext) : null;
        if (lifetime == null) {
            log.debug("{} No assertion lifetime supplied, using default", getLogPrefix());
        }

        final DateTime expiration = new DateTime(response.getIssueInstant()).plus(
                lifetime != null ? lifetime : defaultAssertionLifetime);
        for (final Assertion assertion : response.getAssertions()) {
            final Conditions conditions = SAML1ActionSupport.addConditionsToAssertion(this, assertion);
            log.debug("{} Added NotOnOrAfter condition, indicating an expiration of {}, to Assertion {}",
                    new Object[] {getLogPrefix(), expiration, assertion.getID()});
            conditions.setNotOnOrAfter(expiration);
        }
    }

}