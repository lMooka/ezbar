package br.com.ezbar.model.service;

import java.util.HashMap;

import br.com.ezbar.model.business.Auth;
import br.com.ezbar.model.business.LoginCredentials;

public class LoginService extends Service {

    private LoginCredentials loginCredentials;

    public LoginService(LoginCredentials auth) {
        super(new Auth("AuthRequest"));
        this.loginCredentials = auth;
    }

    @Override
    public void request() {
        ServiceRequest service = new ServiceRequest(ServiceRequest.RequestMethod.post, this);
        service.execute();
    }

    @Override
    void done(String data) {

    }

    @Override
    void error(String error) {

    }

    @Override
    public HashMap<String, String> getRequestParams() {
        HashMap<String, String> params = new HashMap<>();

        params.put("username", loginCredentials.getUsername());
        params.put("password", loginCredentials.getPassword());

        return params;
    }

    @Override
    public String getUrl() {
        return "http://www.ezbar.com.br/service/loginAuth.php?";
    }
}
