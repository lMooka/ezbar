package br.com.ezbar.app.business;

import br.com.ezbar.framework.json.injector.JsonAttribute;

public class Placeholder {

    @JsonAttribute(alias = "userId")
    private int userId;
    @JsonAttribute(alias = "id")
    private int id;
    @JsonAttribute(alias = "title")
    private String title;
    @JsonAttribute(alias = "body")
    private String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
