package br.com.ezbar.framework.json;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonInjectorParserDouble extends JsonInjectorParser<Double> {

    @Override
    protected Double getValue(JsonInjector injector, String valueName, JSONObject value) throws JSONException {
        return value.getDouble(valueName);
    }

    @Override
    public Class<Double> getType() {
        return Double.class;
    }
}
