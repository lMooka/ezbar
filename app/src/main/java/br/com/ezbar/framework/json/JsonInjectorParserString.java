package br.com.ezbar.framework.json;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonInjectorParserString extends JsonInjectorParser<String> {

    @Override
    protected String getValue(JsonInjector injector, String valueName, JSONObject value) throws JSONException {
        return value.getString(valueName);
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
