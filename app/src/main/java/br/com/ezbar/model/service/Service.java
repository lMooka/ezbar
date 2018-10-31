package br.com.ezbar.model.service;

import java.util.HashMap;

import br.com.ezbar.model.business.Auth;

public abstract class Service {

    private Auth auth;
    private IServiceListener listener;

    public Service(IServiceListener listener, Auth auth) {
        this.listener = listener;
        this.auth = auth;
    }

    public abstract void request();
    abstract void done(String data);
    abstract void error(String error);

    abstract HashMap<String, String> getRequestParams();
    abstract String getUrl();

    public Auth getAuth() {
        return auth;
    }
}
