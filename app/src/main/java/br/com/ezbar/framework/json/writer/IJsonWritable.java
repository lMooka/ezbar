package br.com.ezbar.framework.json.writer;

import org.json.JSONObject;

public interface IJsonWritable {
    String getJsonName();
    JSONObject getJson();
}
