package com.example.listexampleapi.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.listexampleapi.Model.Post;
import com.example.listexampleapi.R;
import com.example.listexampleapi.ViewUtils.PostAdapter;

public class ArticleListActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener instancia de la lista
        listView = (ListView) findViewById(R.id.listView);

        // Crear y setear adaptador
        initListView();

        onItemTouch();
    }

    private void initListView(){
        adapter = new PostAdapter(this);
        listView.setAdapter(adapter);
    }

    private void onItemTouch(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post listItem = (Post) adapter.getItem(position);
                Intent intent = new Intent(ArticleListActivity.this, ArticleActivity.class);
                intent.putExtra("title", listItem.getTitle());
                intent.putExtra("price", listItem.getPrice());
                intent.putExtra("image", listItem.getImage());
                startActivity(intent);
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



/*
    Button button;
    TextView tvText;

    // Instantiate the RequestQueue.
    RequestQueue queue = Volley.newRequestQueue(this);
    String url ="http://www.google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayoutViews();

        actionButton();
    }

    private void initLayoutViews() {
        button = findViewById(R.id.button);
        tvText = findViewById(R.id.tvText);
    }

    private void sendRequest(){
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        tvText.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvText.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        this.queue.add(stringRequest);
    }

    private void actionButton(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });
    }
}
 */
