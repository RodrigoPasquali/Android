package com.example.listexampleapi.Services;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.listexampleapi.Model.Post;
import com.example.listexampleapi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestService {
    private RequestQueue requestQueue;
    private List<Post> items;
    private Context context;
    JsonObjectRequest jsArrayRequest;
    private static final String URL_BASE = "https://api.mercadolibre.com/sites/MLA";
    private static final String URL_JSON = "/search?q=camiseta";
    private static final String TAG = "PostAdapter";

    public RequestService(Context context) {
        context = context;
        this.requestQueue = Volley.newRequestQueue(context);

    // esto lo tendria que hacer cuando clickeen el boton buscar
        getRequest();

        // Añadir petición a la cola
        requestQueue.add(jsArrayRequest);
    }

//    DEBERIA TENER UN PARAMETRO CON LA PALABRA A BUSCAR
//    DEBERIA DEVOLVER EL STRING CON EL JSON
    public void getRequest(){
        // Nueva petición JSONObject
        jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL_BASE + URL_JSON,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        items = parseJson(response);
//                        notifyDataSetChanged();
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

    public void getImage(String imageUrl, Response.Listener<Bitmap> listener, Response.ErrorListener errorListener) {
        ImageRequest request = new ImageRequest(imageUrl, listener, 0, 0, null,null, errorListener);
        // Añadir petición a la cola
        requestQueue.add(request);
    }

    public List<Post> parseJson(JSONObject jsonObject){
        // Variables locales
        List<Post> posts = new ArrayList();
        JSONArray jsonArray= null;

        try {
            // Obtener el array del objeto
//            jsonArray = jsonObject.getJSONArray("pictures");
            jsonArray = jsonObject.getJSONArray("results");

            for(int i = 0; i < jsonArray.length(); i++){
                try {
                    JSONObject objeto = jsonArray.getJSONObject(i);
                    String a = objeto.toString();

                    Post post = new Post(
                            objeto.getString("title"),
                            objeto.getString("price"),
                            objeto.getString("thumbnail"));

                    posts.add(post);
                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return posts;
    }
}
