package br.com.ezbar.framework.json;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonInjectorParserBoolean extends JsonInjectorParser<Boolean> {

    @Override
    protected Boolean getValue(JsonInjector injector, String valueName, JSONObject value) throws JSONException {
        return value.getBoolean(valueName);
    }

    @Override
    public Class<Boolean> getType() {
        return Boolean.class;
    }
}
