package com.openproject.services;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.openproject.core.AppConfig;
import com.openproject.core.HttpGateway;
import com.openproject.vo.ChangeRequest;
import com.openproject.vo.ChangeResponse;
import com.openproject.vo.OrderRequest;
import com.openproject.vo.OrderResponse;

@Service
public class AppDirectBillingHandler {
    @Inject
    private HttpGateway<ResponseEntity<Response>> httpGateway;
    @Inject
    private AppConfig appConfig;

    public Optional<?> doBilling(String companyId, String userId, OrderRequest order) {

        HttpHeaders httpHeaders = createHttpHeader();

        String billingUrl = appConfig.getFreshChargingUrl();
        ResponseEntity<Response> gwResponse = httpGateway.<OrderRequest> execute(HttpMethod.POST, httpHeaders,
                billingUrl, new Object[] { companyId, userId }, order);

        OrderResponse orderResponse = null;
        if (gwResponse.getStatusCode() == HttpStatus.CREATED) {
            orderResponse = (OrderResponse) gwResponse.getBody().getEntity();
            return Optional.of(orderResponse);
        } else {
            return parseResponseStatus(gwResponse);
        }
    }

    public Optional<?> deactivate(String subscriptionId) {
        HttpHeaders httpHeaders = createHttpHeader();
        String deactivateUrl = appConfig.getDeactivateChargingUrl();

        ResponseEntity<Response> gwResponse = httpGateway.<OrderRequest> execute(HttpMethod.DELETE, httpHeaders,
                deactivateUrl, new Object[] { subscriptionId }, null);

        return parseResponseStatus(gwResponse);
    }

    public Optional<?> changeBilling(String companyId, String userId, String subscriptionId,
                                     ChangeRequest changeRequest) {

        HttpHeaders httpHeaders = createHttpHeader();

        String billingUrl = appConfig.getUpdateChargingUrl();
        ResponseEntity<Response> gwResponse = httpGateway.<ChangeRequest> execute(HttpMethod.PUT, httpHeaders,
                billingUrl, new Object[] { companyId, userId }, changeRequest);

        ChangeResponse changeResponse = null;
        if (gwResponse.getStatusCode() == HttpStatus.CREATED) {
            changeResponse = (ChangeResponse) gwResponse.getBody().getEntity();
            return Optional.of(changeResponse);
        } else {
            return parseResponseStatus(gwResponse);
        }

    }

    private Optional<?> parseResponseStatus(ResponseEntity<Response> gwResponse) {
        if (gwResponse.getStatusCode() != HttpStatus.NO_CONTENT) {
            return Optional.of(Status.NO_CONTENT);
        } else if (gwResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return Optional.of(Status.BAD_REQUEST);
        } else if (gwResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            return Optional.of(Status.NOT_FOUND);
        } else if (gwResponse.getStatusCode() == HttpStatus.CONFLICT) {
            return Optional.of(Status.CONFLICT);
        } else {
            return Optional.of(Status.EXPECTATION_FAILED);
        }
    }

    /**
     * create custom HTTP-header for AppDirect-GW and add Autherization token for OAUTH.
     * 
     * @return
     */
    private HttpHeaders createHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        return httpHeaders;
    }

}
