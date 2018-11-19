package br.com.ezbar.model.service;

import br.com.ezbar.model.business.LoginCredentials;
import br.com.ezbar.model.service.impl.ServiceLogin;

public class ServiceSingleton {
    private static final ServiceSingleton ourInstance = new ServiceSingleton();

    public static ServiceSingleton getInstance() {
        return ourInstance;
    }

    private ServiceSingleton() {
    }

    public ServiceLogin getLoginService(){
        return null;
    }

    public LoginCredentials getLoginCredentials() {
        return null;
    }
}
