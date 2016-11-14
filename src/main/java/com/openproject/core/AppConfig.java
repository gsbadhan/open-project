package com.openproject.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
    @Value("${fresh.charging.url}")
    private String freshChargingUrl;

    @Value("${update.charging.url}")
    private String updateChargingUrl;

    @Value("${deactivate.charging.url}")
    private String deactivateChargingUrl;

    public String getFreshChargingUrl() {
        return freshChargingUrl;
    }

    public void setFreshChargingUrl(String freshChargingUrl) {
        this.freshChargingUrl = freshChargingUrl;
    }

    public String getUpdateChargingUrl() {
        return updateChargingUrl;
    }

    public void setUpdateChargingUrl(String updateChargingUrl) {
        this.updateChargingUrl = updateChargingUrl;
    }

    public String getDeactivateChargingUrl() {
        return deactivateChargingUrl;
    }

    public void setDeactivateChargingUrl(String deactivateChargingUrl) {
        this.deactivateChargingUrl = deactivateChargingUrl;
    }

}
