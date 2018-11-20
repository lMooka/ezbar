package br.com.ezbar.framework.json;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonInjectorParserLong extends JsonInjectorParser<Long> {

    @Override
    protected Long getValue(JsonInjector injector, String valueName, JSONObject value) throws JSONException {
        return value.getLong(valueName);
    }

    @Override
    public Class<Long> getType() {
        return Long.class;
    }
}
