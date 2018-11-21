package br.com.ezbar.app.business;

public class UserAuthentication {
    private String token;
    private String creationDate;
    private int validTime;

    public UserAuthentication() {
    }

    public UserAuthentication(String token, String creationDate, int validTime) {
        this.token = token;
        this.creationDate = creationDate;
        this.validTime = validTime;
    }

    public boolean isValid() {
        return true;
    }

    public String getToken() {
        return token;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public int getValidTime() {
        return validTime;
    }
}
