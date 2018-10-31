package br.com.ezbar.model.service;

public interface IServiceListener {
    void requestComplete(Service service);
    void requestError(Service service);
}
