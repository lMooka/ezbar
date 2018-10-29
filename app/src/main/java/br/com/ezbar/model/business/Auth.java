package br.com.ezbar.model.business;

public class Auth {
    private String authToken;

    public Auth(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }
}
