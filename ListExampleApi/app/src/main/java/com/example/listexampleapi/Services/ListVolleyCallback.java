package com.example.listexampleapi.Services;

import org.json.JSONException;
import org.json.JSONObject;

public interface ListVolleyCallback {
    void onSuccess(JSONObject result) throws JSONException;
    void onError(String result) throws Exception;
}