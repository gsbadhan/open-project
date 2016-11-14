package com.openproject.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "changeResponse")
@JsonInclude(Include.NON_NULL)
public class ChangeResponse {

    /**
     * Add fields as per response.
     */
}
