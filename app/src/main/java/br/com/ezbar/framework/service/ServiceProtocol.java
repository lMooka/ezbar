package br.com.ezbar.framework.service;

import java.util.HashMap;

public class ServiceProtocol {
    private ServiceAuth serviceAuth;
    private HashMap<String, String> params;

    public ServiceProtocol(ServiceAuth serviceAuth) {
        this.serviceAuth = serviceAuth;
        params = new HashMap<>();
    }

    public ServiceAuth getServiceAuth() {
        return serviceAuth;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParamValue(String key, String value) {
        params.remove(key);
        params.put(key, value);
    }

    public String getParam(String key) {
        return params.get(key);
    }
}
