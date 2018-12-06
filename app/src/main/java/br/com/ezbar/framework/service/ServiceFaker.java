package br.com.ezbar.framework.service;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ServiceFaker {
    public static boolean isFakerEnabled = false;
    private static HashMap<String, JSONObject> fakeResponses = new HashMap<>();

    public static void addFakeResponse(String url, String json) throws JSONException {
        fakeResponses.put(url, new JSONObject(json));
    }

    public static void removeFakeResponse(String url) {
        fakeResponses.remove(url);
    }

    public static JSONObject get(String url) {
        if(fakeResponses.containsKey(url))
            return fakeResponses.get(url);
        else
            return null;
    }

    public static HashMap<String, JSONObject> getFakeObjects() {
        return fakeResponses;
    }
}
