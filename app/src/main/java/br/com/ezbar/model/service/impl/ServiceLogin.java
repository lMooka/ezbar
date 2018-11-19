package br.com.ezbar.model.service.impl;

import java.util.HashMap;

import br.com.ezbar.model.business.LoginCredentials;
import br.com.ezbar.model.service.IServiceCallback;
import br.com.ezbar.model.service.Service;
import br.com.ezbar.model.service.ServiceException;
import br.com.ezbar.model.service.ServiceProtocol;
import br.com.ezbar.model.service.ServiceRequest;

public class ServiceLogin extends Service<ServiceLogin.ILoginService, LoginCredentials> {

    public ServiceLogin(ServiceProtocol protocol, ILoginService callback, LoginCredentials model) {
        super(protocol, ServiceRequest.RequestMethod.post, callback, model);
    }

    @Override
    protected void process(String data, LoginCredentials model) {

    }

    @Override
    protected void ready(ILoginService listener, LoginCredentials model) {
        listener.loginResult(model);
    }

    @Override
    public String getUrl() {
        return "http://www.url.com.br/service/loginAuth.php?";
    }

    public ServiceLogin setEmail(String email) {
        getServiceProtocol().setParamValue("email", email);
        return this;
    }

    public ServiceLogin setPassword(String password) {
        getServiceProtocol().setParamValue("password", password);
        return this;
    }

    public interface ILoginService extends IServiceCallback {
        void loginResult(LoginCredentials credentials);
    }
}
