package br.com.ezbar.framework.service;

public class ServiceAuth {
    private String authToken;

    public ServiceAuth(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
