package com.example.listexampleapi.Service;

import org.json.JSONException;
import org.json.JSONObject;

public interface JsonVolleyCallback {
    void onSuccess(JSONObject result) throws JSONException;
    void onError(String result) throws Exception;
}