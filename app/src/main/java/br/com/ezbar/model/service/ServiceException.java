package br.com.ezbar.model.service;

public class ServiceException extends Exception {

    private Service service;
    private Exception exception;

    public ServiceException(Service service) {
        this.service = service;
    }

    public ServiceException(Service service, String message) {
        super(message);
        this.service = service;
    }

    public ServiceException(Service service, Exception e) {
        this.service = service;
        this.exception = e;
    }

    public Service getService() {
        return service;
    }

    public Exception getException() {
        return exception;
    }
}

