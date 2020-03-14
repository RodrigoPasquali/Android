package com.example.listexampleapi.View.ViewUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.R;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter {
    private RequestQueue requestQueue;
    private static final String TAG = "ArticleAdapter";
    private List<Article> items;
    private Context context;

    public ArticleAdapter(Context context) {
        super(context,0);

        this.context = context;
        requestQueue= Volley.newRequestQueue(context);
    }

    public void setItems(List<Article> list){
        items = list;
        notifyDataSetChanged();
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

        View listItemView;
        listItemView = (null == convertView ) ? layoutInflater.inflate(R.layout.post, parent, false) : convertView;

        final Article item = items.get(position);

        TextView textoTitulo = (TextView) listItemView.findViewById(R.id.textoTitulo);
        TextView textoDescripcion = (TextView) listItemView.findViewById(R.id.textoDescripcion);
        final ImageView imageArticle = (ImageView) listItemView.findViewById(R.id.imagenPost);

        textoTitulo.setText(item.getTitle());
        textoDescripcion.setText(item.getPrice());

        ImageRequest request = new ImageRequest(item.getUrlImage(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        item.setImage(bitmap);
                        imageArticle.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imageArticle.setImageResource(R.drawable.error);
                        Log.d(TAG, "Error en respuesta Bitmap: "+ error.getMessage());
                    }
                });

        requestQueue.add(request);

        //        RequestService.getInstance(context).getImage(item, item.getUrlImage(), new ImageVolleyCallback() {
//            @Override
//            public void onImageSuccess(Bitmap bitmap) {
//                imageArticle.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
//                        R.drawable.error));
//            }
//
//            @Override
//            public void onImageFail() {
//                imageArticle.setImageBitmap(item.getImage());
//
//            }
//        });

//
//        if(item.getImage() == null){
//            imageArticle.setImageBitmap(BitmapFactory.decodeResource(this.context.getResources(),
//                    R.drawable.error));
//        } else {
//            imageArticle.setImageBitmap(item.getImage());
//        }

        return listItemView;
    }
}