package br.com.ezbar.model.service.impl;

import java.util.HashMap;

import br.com.ezbar.model.business.LoginCredentials;
import br.com.ezbar.model.service.IServiceCallback;
import br.com.ezbar.model.service.Service;
import br.com.ezbar.model.service.ServiceProtocol;
import br.com.ezbar.model.service.ServiceRequest;

public class LoginService extends Service<LoginService.ILoginService, LoginCredentials> {

    protected LoginService(ServiceProtocol protocol) {
        super(protocol, ServiceRequest.RequestMethod.post);
    }

    @Override
    protected void processResult(String data, LoginCredentials model) {
        // data é o json
        // passa os dados o json para a model
    }

    @Override
    protected void ready(ILoginService listener, LoginCredentials model) {
        listener.loginResult(model);
    }

    @Override
    protected void requestError(String error, ILoginService listener) {

    }

    @Override
    public HashMap<String, String> getRequestParams() {
        HashMap<String, String> params = new HashMap<>();

        //params.put("username", loginCredentials.getUsername());
        //params.put("password", loginCredentials.getPassword());

        return params;
    }

    @Override
    public String getUrl() {
        return "http://www.url.com.br/service/loginAuth.php?";
    }

    public interface ILoginService extends IServiceCallback {
        void loginResult(LoginCredentials credentials);
    }
}
