package com.openproject.controller;

import static com.google.common.base.Preconditions.checkArgument;
import static com.openproject.common.Validation.isValidCompanyID;
import static com.openproject.common.Validation.isValidSubscriptionID;
import static com.openproject.common.Validation.isValidUserID;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.openproject.exception.JAXRSUtil;
import com.openproject.services.AppDirectBillingHandler;
import com.openproject.vo.ChangeRequest;
import com.openproject.vo.OrderRequest;

@Service("subscriptionServiceImpl")
public class SubscriptionServiceImpl implements SubscriptionService {
    private HttpHeaders httpHeaders;

    @Inject
    private AppDirectBillingHandler billingHandler;

    @Override
    public Response newSubscription(String companyId, String userId, OrderRequest order) {
        checkArgument(isValidCompanyID(companyId), Status.BAD_REQUEST);
        companyId = companyId.trim();
        checkArgument(isValidUserID(userId), Status.BAD_REQUEST);
        userId = userId.trim();

        Optional<?> optional = billingHandler.doBilling(companyId, userId, order);
        return JAXRSUtil.returnIffound(optional.get());
    }

    @Override
    public Response updateSubscription(String companyId, String userId, String subscriptionId,
                                       ChangeRequest changeRequest) {
        checkArgument(isValidCompanyID(companyId), Status.BAD_REQUEST);
        companyId = companyId.trim();
        checkArgument(isValidUserID(userId), Status.BAD_REQUEST);
        userId = userId.trim();
        checkArgument(isValidSubscriptionID(subscriptionId), Status.BAD_REQUEST);
        subscriptionId = subscriptionId.trim();

        Optional<?> optional = billingHandler.changeBilling(companyId, userId, subscriptionId, changeRequest);

        return JAXRSUtil.returnIffound(optional.get());
    }

    @Override
    public Response cancelSubscription(String subscriptionId) {
        checkArgument(isValidSubscriptionID(subscriptionId), Status.BAD_REQUEST);
        subscriptionId = subscriptionId.trim();
        Optional<?> optional = billingHandler.deactivate(subscriptionId);
        return JAXRSUtil.returnIffound(optional.get());
    }

    /**
     * 
     * @param httpHeaders
     */
    @Context
    public void setHttpHeaders(final HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

}
