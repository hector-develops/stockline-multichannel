package com.devslopsleon.orders.core.dto.transactional.producteca;


import java.io.Serializable;

public class ProfileDTO implements Serializable {

    private String App;
    private String IntegrationId;
    private String Nickname;

    public String getApp() {
        return App;
    }

    public void setApp(String app) {
        App = app;
    }

    public String getIntegrationId() {
        return IntegrationId;
    }

    public void setIntegrationId(String integrationId) {
        IntegrationId = integrationId;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }
}
