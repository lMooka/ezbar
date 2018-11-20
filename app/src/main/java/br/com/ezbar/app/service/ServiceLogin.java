package br.com.ezbar.app.service;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.ezbar.app.business.LoginCredentials;
import br.com.ezbar.app.json.MyJsonInjector;
import br.com.ezbar.framework.service.IServiceCallback;
import br.com.ezbar.framework.service.Service;
import br.com.ezbar.framework.service.ServiceProtocol;
import br.com.ezbar.framework.service.ServiceRequest;

public class ServiceLogin extends Service<ServiceLogin.ILoginService, LoginCredentials> {

    public ServiceLogin(ServiceProtocol protocol, ILoginService callback, LoginCredentials model) {
        super(protocol, ServiceRequest.RequestMethod.post, callback, model);
    }

    @Override
    protected void process(String data, LoginCredentials model) {
        try {
            JSONObject json = new JSONObject(data);
            MyJsonInjector injector = new MyJsonInjector();
            injector.inject(model, json);
        } catch (JSONException | IllegalAccessException e) {
            e.printStackTrace();
        }
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
