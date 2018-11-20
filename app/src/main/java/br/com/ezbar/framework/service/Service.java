package br.com.ezbar.framework.service;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Service<C extends IServiceCallback, M> {

    private ServiceProtocol protocol;
    private ServiceRequest.RequestMethod requestMethod;
    private M model;
    private C callback;

    protected Service(ServiceProtocol protocol, ServiceRequest.RequestMethod requestMethod, C callback, M model) {
        this.protocol = protocol;
        this.requestMethod = requestMethod;
        this.callback = callback;
        this.model = model;
    }

    public final <R extends Service<C, M>> R go() {
        if(before())
            new ServiceRequest<>(requestMethod, this).execute();

        return (R)this;
    }

    final void done(String data) {
        try {
            process(new JSONObject(data), model);
            ready(callback, model);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected final void done(JSONObject data) {
        process(data, model);
        ready(callback, model);
    }

    protected boolean before() {
        return true;
    }
    protected abstract void process(JSONObject data, M model);
    protected abstract void ready(C listener, M model);
    void error(ServiceException e) {
        getCallback().serviceError(e);
    }

    protected abstract String getUrl();

    protected final ServiceProtocol getServiceProtocol() {
        return protocol;
    }

    public final M getModel() {
        return model;
    }

    protected C getCallback() {
        return callback;
    }
}
