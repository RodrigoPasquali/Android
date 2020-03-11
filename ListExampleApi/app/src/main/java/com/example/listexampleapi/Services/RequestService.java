package com.example.listexampleapi.Services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.listexampleapi.Model.Post;

import org.json.JSONObject;

import java.util.List;

public class RequestService {
    private RequestQueue requestQueue;
    private List<Post> items;
    private Context context;

    public RequestService(Context context) {
        context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

 /*
    public void getItems(){
        // Nueva petición JSONObject
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL_BASE + URL_JSON,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        items = parseJson(response);
                        notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                    }
                }
        );
    }
  */
}
