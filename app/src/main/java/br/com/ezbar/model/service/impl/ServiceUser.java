package br.com.ezbar.model.service.impl;

import java.util.HashMap;

import br.com.ezbar.model.business.User;
import br.com.ezbar.model.service.IServiceCallback;
import br.com.ezbar.model.service.Service;
import br.com.ezbar.model.service.ServiceProtocol;
import br.com.ezbar.model.service.ServiceRequest;

public class ServiceUser extends Service<ServiceUser.IServiceUser, User> {

    protected ServiceUser(ServiceProtocol protocol, ServiceRequest.RequestMethod requestMethod) {
        super(protocol, requestMethod);
    }

    @Override
    protected void processResult(String data, User model) {

    }

    @Override
    protected void ready(IServiceUser listener, User model) {
        listener.onUserResult(model);
    }

    @Override
    protected void requestError(String error, IServiceUser listener) {

    }

    @Override
    protected HashMap<String, String> getRequestParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    public interface IServiceUser extends IServiceCallback {
        void onUserResult(User user);
    }
}
