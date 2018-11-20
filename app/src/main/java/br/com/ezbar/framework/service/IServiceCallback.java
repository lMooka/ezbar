package br.com.ezbar.framework.service;

import android.content.Context;

public interface IServiceCallback {
    void serviceError(ServiceException e);
    Context getApplicationContext();
}
