package com.openproject.core;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public interface HttpGateway<RET> {

    <B> RET execute(HttpMethod type, HttpHeaders HTTP_HEADERS, String url, Object[] params, B body);
}
