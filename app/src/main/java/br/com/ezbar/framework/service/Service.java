package br.com.ezbar.framework.service;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Service<C extends IServiceCallback> {

    private ServiceProtocol protocol;
    private ServiceRequest.RequestMethod requestMethod;
    private C callback;

    protected Service(ServiceProtocol protocol, ServiceRequest.RequestMethod requestMethod, C callback) {
        this.protocol = protocol;
        this.requestMethod = requestMethod;
        this.callback = callback;
    }

    public final <R extends Service<C>> R go() throws ServiceException {
        if(before())
            new ServiceRequest<>(requestMethod, this).execute();

        return (R)this;
    }

    final void done(String data) {
        try {
            done(new JSONObject(data));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected final void done(JSONObject json) {
        process(json);
        ready(callback);
    }

    protected boolean before() throws ServiceException {
        return true;
    }
    protected abstract void process(JSONObject data);
    protected abstract void ready(C callback);
    void error(ServiceException e) {
        getCallback().serviceError(e);
    }

    protected abstract String getUrl();

    protected final ServiceProtocol getServiceProtocol() {
        return protocol;
    }

    protected C getCallback() {
        return callback;
    }
}
