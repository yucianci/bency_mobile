package com.example.hackathon4si;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hackathon4si.datasources.DownloadImagem;

public class DetalhesActivity extends AppCompatActivity {

    TextView nome;
    ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        nome = findViewById(R.id.nome);
        imagem = findViewById(R.id.imagem);

        Intent dadosRecebidos = getIntent();
        if (dadosRecebidos != null) {

            Bundle params = dadosRecebidos.getExtras();
            if (params != null) {

                nome.setText(params.getString("nome"));

                new DownloadImagem(imagem).execute(params.getString("imagem"));
            }
        }
    }
}