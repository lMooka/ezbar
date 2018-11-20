package br.com.ezbar.app.service;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.com.ezbar.app.business.LoginCredentials;
import br.com.ezbar.app.json.MyJsonInjector;
import br.com.ezbar.framework.json.writer.JsonCache;
import br.com.ezbar.framework.persistence.Persist;
import br.com.ezbar.framework.service.IServiceCallback;
import br.com.ezbar.framework.service.Service;
import br.com.ezbar.framework.service.ServiceProtocol;
import br.com.ezbar.framework.service.ServiceRequest;

public class ServiceLogin extends Service<ServiceLogin.ILoginService, LoginCredentials> {

    public ServiceLogin(ServiceProtocol protocol, ILoginService callback, LoginCredentials model) {
        super(protocol, ServiceRequest.RequestMethod.post, callback, model);
    }

    @Override
    protected boolean before() {
        JsonCache cache = new JsonCache("ServiceLogin", new Persist(getCallback().getContext()));

        try {
            JSONObject json = cache.get(getServiceProtocol().getParam("email"));

            if(json == null)
                return true;

            done(json);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    protected void process(JSONObject data, LoginCredentials model) {
        try {
            MyJsonInjector injector = new MyJsonInjector();
            injector.inject(model, data);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
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
