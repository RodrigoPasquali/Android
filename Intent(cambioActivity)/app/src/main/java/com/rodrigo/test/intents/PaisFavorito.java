package com.rodrigo.test.intents;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PaisFavorito extends AppCompatActivity {
    private SwipeRefreshLayout srPaises;
    private ListView lvPaises;
    private ArrayList listaPaises = new ArrayList();
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais_favorito);

        getElementosLayout();
        crearLista(listaPaises);
    }

    private void getElementosLayout(){
        srPaises = findViewById(R.id.srPaises);
        lvPaises = findViewById(R.id.lvPaises);
    }

    private void crearLista(ArrayList arrayList){
        arrayList.add("Argentina");
        arrayList.add("Brasil");
        arrayList.add("Mexico");
        arrayList.add("Estados Unidos");
        arrayList.add("Alemania");
        arrayList.add("Rusia");
        arrayList.add("Francia");
        arrayList.add("Inglaterra");
        arrayList.add("España");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        lvPaises.setAdapter(arrayAdapter);

        funcionamientoSwipeRefresh();
    }

    private void funcionamientoSwipeRefresh(){
        srPaises.setColorSchemeResources(R.color.colorWhite);
        srPaises.setProgressBackgroundColorSchemeResource(R.color.colorPrimary);
        srPaises.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mezclarLista(listaPaises);
                srPaises.setRefreshing(false);
            }
        });
    }

    private void mezclarLista(List lista){
        Collections.shuffle(lista, new Random(System.currentTimeMillis()));
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lvPaises.setAdapter(adapter);
    }
}
