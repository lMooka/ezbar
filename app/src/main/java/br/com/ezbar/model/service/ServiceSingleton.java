package br.com.ezbar.model.service;

import br.com.ezbar.model.business.LoginCredentials;
import br.com.ezbar.model.service.impl.LoginService;

public class ServiceSingleton {
    private static final ServiceSingleton ourInstance = new ServiceSingleton();

    public static ServiceSingleton getInstance() {
        return ourInstance;
    }

    private ServiceSingleton() {
    }

    public LoginService getLoginService(){
        return null;
    }

    public LoginCredentials getLoginCredentials() {
        return null;
    }
}
