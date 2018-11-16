package com.app.anthony.pi4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.app.anthony.pi4.br.edu.cairu.modelo.Avalicao;

public class MostrarConteudoLista extends AppCompatActivity {

    private TextView lblMostarConteudoListaNome,lblMostarConteudoListaqualificacacao,
                     lblMostarConteudoListaDescricao;
    private Button btnMostarCotenudoListaVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_conteudo_lista);
        inicializarComponentes();
    }


    private void inicializarComponentes(){

        lblMostarConteudoListaNome = findViewById(R.id.lblMostarConteudoListaNome);
        lblMostarConteudoListaqualificacacao = findViewById(R.id.lblMostarConteudoListaqualificacacao);
        lblMostarConteudoListaDescricao = findViewById(R.id.lblMostarConteudoListaDescricao);
        btnMostarCotenudoListaVoltar = findViewById(R.id.btnMostarCotenudoListaVoltar);
        btnMostarCotenudoListaVoltar.setOnClickListener(voltaTela);
        Avalicao avalicao = (Avalicao) getIntent().getExtras().get("avalicao");
        /*----------------------------------------------------------------------------------------*/
        lblMostarConteudoListaNome.setText(avalicao.getNome().toString());
        lblMostarConteudoListaqualificacacao.setText(avalicao.getQualificacao().toString());
        lblMostarConteudoListaDescricao.setText(avalicao.getDescricao().toString());
    }

    private View.OnClickListener voltaTela = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MostrarConteudoLista.this, AvalicaoActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
