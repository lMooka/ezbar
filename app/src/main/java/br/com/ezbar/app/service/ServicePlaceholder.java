package br.com.ezbar.app.service;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.ezbar.app.business.Placeholder;
import br.com.ezbar.framework.json.injector.JsonInjector;
import br.com.ezbar.framework.json.injector.PrimitiveJsonInjector;
import br.com.ezbar.framework.service.IServiceResponse;
import br.com.ezbar.framework.service.Service;
import br.com.ezbar.framework.service.ServiceRequest;

public class ServicePlaceholder extends Service<ServicePlaceholder.IServicePlaceholder> {

    private Placeholder placeholder;

    public ServicePlaceholder(IServicePlaceholder callback) {
        super(ServiceRequest.RequestMethod.get, callback);
    }

    @Override
    protected void process(JSONObject data) {
        placeholder = new Placeholder();

        JsonInjector injector = new PrimitiveJsonInjector();

        try {
            injector.inject(placeholder, data);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void ready(IServicePlaceholder callback) {
        callback.onPlaceholder(placeholder);
    }

    @Override
    protected String getUrl() {
        return "https://jsonplaceholder.typicode.com/posts/1";
    }

    public interface IServicePlaceholder extends IServiceResponse {
        void onPlaceholder(Placeholder placeholder);
    }
}
