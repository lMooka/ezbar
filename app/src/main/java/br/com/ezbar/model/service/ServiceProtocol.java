package br.com.ezbar.model.service;

public class ServiceProtocol {
    private ServiceAuth serviceAuth;

    public ServiceProtocol(ServiceAuth serviceAuth) {
        this.serviceAuth = serviceAuth;
    }

    public ServiceAuth getServiceAuth() {
        return serviceAuth;
    }
}
