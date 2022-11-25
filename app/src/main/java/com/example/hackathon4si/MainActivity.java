package com.example.hackathon4si;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.hackathon4si.datasources.DadosApi;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

   private ArrayList<HashMap<String, String>> listaDados;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            listaDados = new DadosApi().execute(Config.urlApi).get();
        }catch (Exception e) {
            e.printStackTrace();
        }

        String texto = listaDados.get(0).get("nome");
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();

        ListAdapter adapter = new SimpleAdapter(
                this,
                listaDados,
                R.layout.listview_modelo,
                new String[] { "nome" },
                new int[] { R.id.txtNome }
        );

    }
}