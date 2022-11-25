package com.example.hackathon4si.datasources;

import android.os.AsyncTask;

import com.example.hackathon4si.Config;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class DadosApi extends AsyncTask<String, Void, ArrayList<HashMap<String, String>>> {

    @Override
    protected ArrayList<HashMap<String, String>> doInBackground(String... strings) {
        ArrayList<HashMap<String, String>> listaDados = new ArrayList<>();

        try {
            String link = strings[0];

            URL url = new URL(link);
            URLConnection connection = url.openConnection();

            InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStream);

            String dados = "";
            String linha;
            while ((linha = reader.readLine()) != null){
                dados += linha;
            }

            JSONObject json = new JSONObject(dados);
            JSONArray lista = new JSONArray(json.getString("results"));

            for (int i = 0; i < lista.length(); i++){
                JSONObject item = (JSONObject)lista.get(i);

                HashMap<String, String> mapaDados = new HashMap<>();
                mapaDados.put("nome", item.getString("name"));
                mapaDados.put("url", item.getString("url"));
                mapaDados.put("imagem", Config.linkImagem + (i + 1) + ".svg");

                listaDados.add(mapaDados);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listaDados;
    }
}
