package br.com.ezbar.app.json;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.ezbar.app.business.User;
import br.com.ezbar.framework.json.JsonInjector;
import br.com.ezbar.framework.json.JsonInjectorParser;

public class JsonInjectorParserUser extends JsonInjectorParser<User> {
    @Override
    protected User getValue(JsonInjector injector, String valueName, JSONObject value) throws JSONException {
        User user = new User();


        return user;
    }

    @Override
    public Class<User> getType() {
        return User.class;
    }
}
