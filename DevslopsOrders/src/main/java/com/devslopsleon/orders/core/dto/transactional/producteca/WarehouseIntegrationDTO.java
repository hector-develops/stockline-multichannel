package com.devslopsleon.orders.core.dto.transactional.producteca;

import java.io.Serializable;

public class WarehouseIntegrationDTO implements Serializable {

    private String App;
    private String Status;
    private String IntegrationId;

    public String getApp() {
        return App;
    }

    public void setApp(String app) {
        App = app;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getIntegrationId() {
        return IntegrationId;
    }

    public void setIntegrationId(String integrationId) {
        IntegrationId = integrationId;
    }
}
