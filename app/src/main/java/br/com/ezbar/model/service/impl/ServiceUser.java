package br.com.ezbar.model.service.impl;

import java.util.HashMap;

import br.com.ezbar.model.business.User;
import br.com.ezbar.model.service.IServiceCallback;
import br.com.ezbar.model.service.Service;
import br.com.ezbar.model.service.ServiceException;
import br.com.ezbar.model.service.ServiceProtocol;
import br.com.ezbar.model.service.ServiceRequest;

public class ServiceUser extends Service<ServiceUser.IServiceUser, User> {

    protected ServiceUser(ServiceProtocol protocol, IServiceUser callback, User model) {
        super(protocol, ServiceRequest.RequestMethod.post, callback, model);
    }

    @Override
    protected void process(String data, User model) {

    }

    @Override
    protected void ready(IServiceUser listener, User model) {

    }

    @Override
    protected String getUrl() {
        return null;
    }

    public interface IServiceUser extends IServiceCallback {
        void onUserResult(User user);
    }
}
