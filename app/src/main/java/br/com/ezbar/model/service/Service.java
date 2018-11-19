package br.com.ezbar.model.service;

import java.util.HashMap;

public abstract class Service<L extends IServiceCallback, M> {

    private ServiceProtocol protocol;
    private ServiceRequest.RequestMethod requestMethod;

    protected Service(ServiceProtocol protocol, ServiceRequest.RequestMethod requestMethod) {
        this.protocol = protocol;
        this.requestMethod = requestMethod;
    }

    public final void request(L listener, M model) {
        new ServiceRequest<>(requestMethod, this, listener, model).execute();
    }

    void requestDone(String data, L listener, M model) {
        processResult(data, model);
        ready(listener, model);
    }

    protected abstract void processResult(String data, M model);
    protected abstract void ready(L listener, M model);
    protected abstract void requestError(String error, L listener);

    protected abstract HashMap<String, String> getRequestParams();
    protected abstract String getUrl();

    public ServiceProtocol getServiceProtocol() {
        return protocol;
    }
}
