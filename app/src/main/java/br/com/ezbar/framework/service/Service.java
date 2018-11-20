package br.com.ezbar.framework.service;
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
        new ServiceRequest<>(requestMethod, this).execute();
        return (R)this;
    }

    final void done(String data) {
        process(data, model);
        ready(callback, model);
    }

    protected abstract void process(String data, M model);
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

    private C getCallback() {
        return callback;
    }
}
