package br.com.ezbar.app.json;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.ezbar.app.business.UserAuthentication;
import br.com.ezbar.framework.json.injector.JsonInjector;
import br.com.ezbar.framework.json.injector.JsonInjectorParser;

public class JsonInjectorParserUserAuthentication extends JsonInjectorParser<UserAuthentication> {
    @Override
    protected UserAuthentication getValue(JsonInjector injector, String valueName, JSONObject value) throws JSONException {
        return null;
    }

    @Override
    public Class<UserAuthentication> getType() {
        return UserAuthentication.class;
    }
}
