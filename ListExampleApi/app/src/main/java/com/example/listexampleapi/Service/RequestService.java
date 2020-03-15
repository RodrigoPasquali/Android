package com.example.listexampleapi.Service;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.listexampleapi.Model.Article;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestService {
    private static RequestService instance;
    private RequestQueue requestQueue;
    private Context context;
    private JsonObjectRequest jsArrayRequest;
    private static final String URL_ARTICLE = "/items/";
    private static final String TAG = "RequestService";
    private static final String URL_BASE = "https://api.mercadolibre.com";
    private static final String URL_JSON = "/sites/MLA/search?q=";

    private RequestService(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public static synchronized RequestService getInstance(Context context) {
        if (instance == null) {
            instance = new RequestService(context);
        }
        return instance;
    }

    public void getSearchArticles(String articleId, final JsonVolleyCallback callback){
        this.jsArrayRequest = new JsonObjectRequest(Request.Method.GET, URL_BASE + URL_JSON + articleId, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String err = null;
                        if (error instanceof com.android.volley.NoConnectionError){
                            err = "No internet Access!";
                        }
                        try {
                            if(err != "null") {
                                callback.onError(err);
                            }
                            else {
                                callback.onError(error.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());
                    }
                }
        );

        this.requestQueue.add(jsArrayRequest);
    }

    public void getArticle(String articleId, final JsonVolleyCallback callback){
        this.jsArrayRequest = new JsonObjectRequest(Request.Method.GET, URL_BASE + URL_ARTICLE + articleId, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String err = null;
                        if (error instanceof com.android.volley.NoConnectionError){
                            err = "No internet Access!";
                        }
                        try {
                            if(err != "null") {
                                callback.onError(err);
                            }
                            else {
                                callback.onError(error.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());
                    }
                }
        );

        this.requestQueue.add(jsArrayRequest);
    }

    public void getImage(final Article article, String imageUrl, final ImageVolleyCallback imageVolleyCallback) {
        ImageRequest request = new ImageRequest(imageUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageVolleyCallback.onImageSuccess(bitmap);

                        // imagenPost.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imageVolleyCallback.onImageFail();
                        Log.d(TAG, "Error en respuesta Bitmap: "+ error.getMessage());
                    }
                }
        );

        this.requestQueue.add(request);
    }
}
