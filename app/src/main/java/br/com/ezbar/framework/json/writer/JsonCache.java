package br.com.ezbar.framework.json.writer;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import br.com.ezbar.framework.persistence.file.Persist;

public class JsonCache {

    private String storageName;
    private Persist persist;

    public JsonCache(String storageName, Persist persist) {
        this.storageName = storageName;
        this.persist = persist;
    }

    public JSONObject get(String writableName) throws IOException, JSONException {
        JSONObject storage = new JSONObject(persist.readInternalStorage(storageName));

        if(storage.has(writableName)) {
            return storage.getJSONObject(writableName);
        }

        return null;
    }

    public void add(IJsonWritable writable) throws IOException, JSONException {
        JSONObject storage = new JSONObject(persist.readInternalStorage(storageName));

        if (storage.has(writable.getJsonName()))
            storage.remove(writable.getJsonName());

        storage.put(writable.getJsonName(), writable.getJson());
        persist.writeInternalStorage(storageName, storage.toString());
    }

    public void remove(IJsonWritable writable) throws IOException, JSONException {
        JSONObject storage = new JSONObject(persist.readInternalStorage(storageName));

        if(storage.has(writable.getJsonName()))
            storage.remove(writable.getJsonName());
    }
}
