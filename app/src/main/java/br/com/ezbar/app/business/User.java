package br.com.ezbar.app.business;

import br.com.ezbar.framework.json.JsonAttribute;

public class User {

    @JsonAttribute(alias = "ID_USER")
    private int userId;
    @JsonAttribute(alias = "DS_EMAIL")
    private String email;
    @JsonAttribute(alias = "DS_FIRST_NAME")
    private String firstName;
    @JsonAttribute(alias = "DS_SECOND_NAME")
    private String secondName;
    @JsonAttribute(alias = "DS_BIRTHDAY")
    private String birthday;

    @JsonAttribute(alias = "CREDENTIALS")
    private LoginCredentials credentials;

    public User() {
    }

    public User(int userId, String email, String firstName, String secondName, String birthday) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
