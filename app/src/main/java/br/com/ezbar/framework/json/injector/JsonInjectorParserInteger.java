package br.com.ezbar.framework.json.injector;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonInjectorParserInteger extends JsonInjectorParser<Integer> {

    @Override
    protected Integer getValue(JsonInjector injector, String valueName, JSONObject value) throws JSONException {
        return value.getInt(valueName);
    }

    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }
}
