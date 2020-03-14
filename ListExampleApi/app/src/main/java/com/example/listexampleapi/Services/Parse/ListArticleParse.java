package com.example.listexampleapi.Services.Parse;

import android.util.Log;

import com.example.listexampleapi.Model.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListArticleParse {
    private static final String TAG = "ListArticleParse";

    public ListArticleParse() {
    }

    public List<Article> parseObject(JSONObject jsonObject){
        List<Article> articles = new ArrayList();
        JSONArray jsonArray = null;

        try {
//            jsonArray = jsonObject.getJSONArray("pictures");
            jsonArray = jsonObject.getJSONArray("results");

            for(int i=0; i<jsonArray.length(); i++){
                try {
                    JSONObject objeto = jsonArray.getJSONObject(i);

                    Article article = new Article(
                            objeto.getString("id"),
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