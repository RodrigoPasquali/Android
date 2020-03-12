package com.example.listexampleapi.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.R;
import com.example.listexampleapi.ViewUtils.ArticleAdapter;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArticleAdapter adapter;
    private EditText edSearch;
    private ImageView btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLayoutViews();

        initListView();

        onItemTouch();

        onButtonSearchTouch();
    }

    private void getLayoutViews() {
        listView = (ListView) findViewById(R.id.listView);
        edSearch = findViewById(R.id.edSearch);
        btnSearch = findViewById(R.id.btnSearch);
    }

    private void initListView(){
        adapter = new ArticleAdapter(this);
        listView.setAdapter(adapter);
    }

    private void onItemTouch(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article listItem = (Article) adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
                intent.putExtra("title", listItem.getTitle());
                intent.putExtra("price", listItem.getPrice());
                intent.putExtra("image", listItem.getImage());
                startActivity(intent);
            }
        });
    }

    public void onButtonSearchTouch(){
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String article = edSearch.getText().toString();
                if(article.equals("")) {
                    Toast.makeText(MainActivity.this,
                            "Por favor introducir articulo a buscar",
                            Toast.LENGTH_LONG).show();
                } else {
//                    adapter.setArticleSearched(search);
                    adapter.searchArticle(article);

                    Toast.makeText(MainActivity.this,
                            "Buscando: " + edSearch.getText(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
}
