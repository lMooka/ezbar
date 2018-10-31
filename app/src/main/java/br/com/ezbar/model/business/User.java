package br.com.ezbar.model.business;

public class User {

    private Auth auth;
    private int userId;
    private String email;
    private String firstName;
    private String secondName;
    private String birthday;

    public User(Auth auth, int userId, String email, String firstName, String secondName, String birthday) {
        this.auth = auth;
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
    }
}
