package com.openproject.common;

public class Validation {

    public static boolean isValidCompanyID(String companyId) {
        if (companyId == null || companyId.isEmpty())
            return false;
        return true;
    }

    public static boolean isValidUserID(String userId) {
        if (userId == null || userId.isEmpty())
            return false;
        return true;
    }

    public static boolean isValidSubscriptionID(String subscriptionId) {
        if (subscriptionId == null || subscriptionId.isEmpty())
            return false;
        return true;
    }

}
