package br.com.ezbar.framework.service;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Service<C extends IServiceCallback> {

    private ServiceProtocol protocol;
    private ServiceRequest.RequestMethod requestMethod;
    private C callback;

    protected Service(ServiceRequest.RequestMethod requestMethod, C callback) {
        this.requestMethod = requestMethod;
        this.callback = callback;
        this.protocol = new ServiceProtocol(new ServiceAuth(""));
    }

    public final <R extends Service<C>> R run() {
        try {
            if(before())
                new ServiceRequest<>(requestMethod, this).execute();
        } catch (ServiceException e) {
            e.printStackTrace();
            error(e);
        }

        return (R)this;
    }

    final void done(String data) {
        try {
            if(data == null || data.equals(""))
                error(new ServiceException(this, "A resposta do web service não está em formato json."));
            else
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
        e.printStackTrace();
        getCallback().serviceError(e);
    }

    protected abstract String getUrl();

    protected final ServiceProtocol getServiceProtocol() {
        return protocol;
    }

    protected C getCallback() {
        return callback;
    }

    public final <R extends Service<C>> R setAuthToken(String authToken) {
        this.protocol.getServiceAuth().setAuthToken(authToken);
        return (R)this;
    }
}
