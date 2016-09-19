package com.sejelli.voucher.interfaces;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by aibano on 9/10/2016.
 */
@Component
@ConfigurationProperties(prefix = "interface")
public class InterfaceProperties {
    private String token;
    private Mode mode;
    private long organizationId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }
}