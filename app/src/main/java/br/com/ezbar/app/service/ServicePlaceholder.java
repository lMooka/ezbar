package br.com.ezbar.app.service;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.ezbar.app.business.Placeholder;
import br.com.ezbar.framework.service.IServiceCallback;
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

        try {
            placeholder.setUserId(data.getInt("userId"));
            placeholder.setId(data.getInt("id"));
            placeholder.setTitle(data.getString("title"));
            placeholder.setBody(data.getString("body"));
        } catch (JSONException e) {
            placeholder = null;
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

    public interface IServicePlaceholder extends IServiceCallback {
        void onPlaceholder(Placeholder placeholder);
    }
}
