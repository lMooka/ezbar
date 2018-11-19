package br.com.ezbar.app.business;

public class User {

    private int userId;
    private String email;
    private String firstName;
    private String secondName;
    private String birthday;

    public User(int userId, String email, String firstName, String secondName, String birthday) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
    }
}
