package br.com.ezbar.framework.json;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

public abstract class JsonInjectorParser<T> {
    protected abstract T getValue(JsonInjector injector, String valueName, JSONObject value) throws JSONException;

    final void inject(JsonInjector injector, Object reference, Field field, String valueName, JSONObject value) throws IllegalAccessException, JSONException {
        field.set(reference, getValue(injector, valueName, value));
    }

    public abstract Class<T> getType();
}
