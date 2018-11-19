package br.com.ezbar.model.service;

public class ServiceException extends Exception {

    private Service service;

    public ServiceException(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }
}

