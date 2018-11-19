package br.com.ezbar.framework.service;

import br.com.ezbar.app.business.LoginCredentials;
import br.com.ezbar.app.service.ServiceLogin;

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
