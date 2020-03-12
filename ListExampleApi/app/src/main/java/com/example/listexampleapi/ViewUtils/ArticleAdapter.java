package com.example.listexampleapi.ViewUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ArticleAdapter extends ArrayAdapter {
    // Atributos
    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;
    private static final String URL_BASE = "https://api.mercadolibre.com/sites/MLA";
    private static final String URL_JSON = "/search?q=";
    private String articleSearched = "dragon";
    private static final String TAG = "ArticleAdapter";
    List<Article> items;

    public ArticleAdapter(Context context) {
        super(context,0);

        // Crear nueva cola de peticiones
        requestQueue= Volley.newRequestQueue(context);
    }

    public void searchArticle(String article) {
        // Nueva petición JSONObject
        jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL_BASE + URL_JSON + article,
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

        // Añadir petición a la cola
        requestQueue.add(jsArrayRequest);
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public Article getItem(int position)
    {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Referencia del view procesado
        View listItemView;

        //Comprobando si el View no existe
        listItemView = (null == convertView ) ? layoutInflater.inflate(
                R.layout.post,
                parent,
                false) : convertView;

        // Obtener el item actual
        Article item = items.get(position);

        // Obtener Views
        TextView textoTitulo = (TextView) listItemView.findViewById(R.id.textoTitulo);
        TextView textoDescripcion = (TextView) listItemView.findViewById(R.id.textoDescripcion);
        final ImageView imagenPost = (ImageView) listItemView.findViewById(R.id.imagenPost);

        // Actualizar los Views
        textoTitulo.setText(item.getTitle());
        textoDescripcion.setText(item.getPrice());

        // Petición para obtener la imagen

        //        ImageRequest request = new ImageRequest("https://mla-s1-p.mlstatic.com/611884-MLA31115582295_062019-I.jpg",
        ImageRequest request = new ImageRequest(item.getImage(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imagenPost.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imagenPost.setImageResource(R.drawable.error);
                        Log.d(TAG, "Error en respuesta Bitmap: "+ error.getMessage());
                    }
                });

        // Añadir petición a la cola
        requestQueue.add(request);

        return listItemView;
    }

    public void setArticleSearched(String article) {
        this.articleSearched = article;
    }

    public String getArticleaSearched() {
        return this.articleSearched;
    }


    public List<Article> parseJson(JSONObject jsonObject){
        // Variables locales
        List<Article> articles = new ArrayList();
        JSONArray jsonArray= null;

        try {
            // Obtener el array del objeto
//            jsonArray = jsonObject.getJSONArray("pictures");
            jsonArray = jsonObject.getJSONArray("results");

            for(int i=0; i<jsonArray.length(); i++){
                try {
                    JSONObject objeto= jsonArray.getJSONObject(i);

                    Article article = new Article(
                            objeto.getString("title"),
                            objeto.getString("price"),
                            objeto.getString("thumbnail"));

                    articles.add(article);
                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return articles;
    }
}