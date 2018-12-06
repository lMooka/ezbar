package br.com.ezbar.framework.service;

import android.content.Context;

public interface IServiceResponse {
    void serviceError(ServiceException e);
    Context getApplicationContext();
}
