package com.openproject.core;

import java.text.MessageFormat;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

public class HttpGatewayImpl implements HttpGateway<ResponseEntity<Response>> {

    @Inject
    private RestOperations restOperations;

    @Override
    public <B> ResponseEntity<Response> execute(HttpMethod type, HttpHeaders httpHeaders, String url, Object[] params,
                                                B body) {
        ResponseEntity<Response> response = callHttp(type, httpHeaders, url, params, body);
        return response;
    }

    private <B> ResponseEntity<Response> callHttp(HttpMethod type, HttpHeaders httpHeaders, String url, Object[] params,
                                                  B body) {
        url = formatURL(url, params);
        HttpEntity<B> httpEntity = null;
        if (body != null) {
            httpEntity = new HttpEntity<B>(body, httpHeaders);
        } else {
            httpEntity = new HttpEntity<B>(httpHeaders);
        }

        ResponseEntity<Response> response = restOperations.exchange(url, type, httpEntity, Response.class);
        return response;
    }

    private String formatURL(String url, Object[] params) {
        return MessageFormat.format(url, params);
    }

}
