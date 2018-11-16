package com.app.anthony.pi4;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.cardViewPerfil).setOnClickListener(editarPerfil);
        findViewById(R.id.cardViewMapa).setOnClickListener(TelaMapa);
        findViewById(R.id.cardViewAvalicao).setOnClickListener(telaAvalicao);


    }

    private View.OnClickListener editarPerfil = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = "http://www.planalto.gov.br/ccivil_03/_Ato2011-2014/2014/Lei/L12977.htm";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    };

    private View.OnClickListener TelaMapa = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Mapa.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener telaAvalicao = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AvalicaoActivity.class);
            startActivity(intent);
        }
    };

}
