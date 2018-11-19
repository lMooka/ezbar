package br.com.ezbar.model.service;

public class ServiceAuth {
    private String authToken;

    public ServiceAuth(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }
}
