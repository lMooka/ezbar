package br.com.ezbar.framework.json;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonInjectorParserFloat extends JsonInjectorParser<Float> {

    @Override
    protected Float getValue(JsonInjector injector, String valueName, JSONObject value) throws JSONException {
        return (float)value.getDouble(valueName);
    }

    @Override
    public Class<Float> getType() {
        return Float.class;
    }
}
