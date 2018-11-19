package br.com.ezbar.app.service;

import br.com.ezbar.app.business.User;
import br.com.ezbar.framework.service.IServiceCallback;
import br.com.ezbar.framework.service.Service;
import br.com.ezbar.framework.service.ServiceProtocol;
import br.com.ezbar.framework.service.ServiceRequest;

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
