package br.com.ezbar.framework.json.injector;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;

import br.com.ezbar.framework.json.JsonAttribute;

public class JsonInjector {

    private HashMap<Class<?>, JsonInjectorParser> parsers;

    public JsonInjector() {
        this.parsers = new HashMap<>();
    }

    public final void addParser(JsonInjectorParser parser) {
        parsers.put(parser.getType(), parser);
    }

    public final void inject(Object objToInject, JSONObject json) throws JSONException, IllegalAccessException {
        for(Field field : objToInject.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(JsonAttribute.class)) {
                field.setAccessible(true);
                String alias = field.getAnnotation(JsonAttribute.class).alias();
                JsonInjectorParser parser = parsers.get(field.getType());

                if(parser != null) {
                    parser.inject(
                            this,
                            objToInject,
                            field,
                            alias.equals("") ? field.getName() : alias,
                            json
                    );
                }
            }
        }
    }
}
