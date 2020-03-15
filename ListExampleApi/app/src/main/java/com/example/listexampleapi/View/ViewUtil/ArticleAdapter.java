package com.example.listexampleapi.View.ViewUtil;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.R;
import com.example.listexampleapi.View.Interface.ArticleListView;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter implements ArticleListView {
    private RequestQueue requestQueue;
    private static final String TAG = "ArticleAdapter";
    private List<Article> items;
    private Context context;

    public ArticleAdapter(Context context) {
        super(context,0);

        this.context = context;
        this.requestQueue= Volley.newRequestQueue(context);
    }

    public void setItems(List<Article> list){
        this.items = list;
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
        listItemView = (null == convertView ) ? layoutInflater.inflate(R.layout.item_article, parent, false) : convertView;

        final Article item = items.get(position);

        TextView tvTitle = listItemView.findViewById(R.id.tvTitle);
        TextView tvPrice = listItemView.findViewById(R.id.tvPrice);
        final ImageView imageArticle =  listItemView.findViewById(R.id.ivImage);

        tvTitle.setText(item.getTitle());
        tvPrice.setText(item.getPrice());

        if(item.getItemImage() == null){
            imageArticle.setImageBitmap(BitmapFactory.decodeResource(this.context.getResources(),
                    R.drawable.error));
        } else {
            imageArticle.setImageBitmap(item.getItemImage());
        }

        return listItemView;
    }

    @Override
    public void showResult(List<Article> articleList) {

    }

    @Override
    public void invalidOperation() {

    }
}