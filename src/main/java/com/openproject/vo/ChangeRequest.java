package com.openproject.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "changeRequest")
@JsonInclude(Include.NON_NULL)
public class ChangeRequest {

    @JsonProperty("ID")
    private String ID;

    @JsonProperty("order")
    private ChangeOrder changeOrder;

    public ChangeRequest() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public ChangeOrder getChangeOrder() {
        return changeOrder;
    }

    public void setChangeOrder(ChangeOrder changeOrder) {
        this.changeOrder = changeOrder;
    }

}
