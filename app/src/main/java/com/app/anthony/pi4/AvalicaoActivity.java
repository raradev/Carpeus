package com.app.anthony.pi4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.app.anthony.pi4.adapter.AvalicaoAdapter;
import com.app.anthony.pi4.br.edu.cairu.database.Conexao;
import com.app.anthony.pi4.br.edu.cairu.modelo.Avalicao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AvalicaoActivity extends AppCompatActivity {

    private ListView listViewAvalicao;
    private Button btnAvalicaoNovo;
    private DatabaseReference firebase;
    private ArrayList<Avalicao>listaDeAvalicao;
    private ArrayAdapter<Avalicao> adapter;
    private Avalicao avalicao;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avalicao);
        inicializarComponentes();

    }
    private void inicializarComponentes(){
        listaDeAvalicao = new ArrayList<>();
        listViewAvalicao = findViewById(R.id.listViewAvalicao);
        btnAvalicaoNovo = findViewById(R.id.btnAvalicaoNovo);
        btnAvalicaoNovo.setOnClickListener(novaAvalicao);

        adapter = new AvalicaoAdapter(this, listaDeAvalicao);
        listViewAvalicao.setAdapter(adapter);
        listViewAvalicao.setOnItemClickListener(mostarInformacao);
        firebase = Conexao.getFirebase().child("avalicao");
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaDeAvalicao.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()){  // Percorrendo a tabela no firebase
                    Avalicao avalicaoNova = dados.getValue(Avalicao.class);
                    listaDeAvalicao.add(avalicaoNova); // add na lista
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
    }

    private View.OnClickListener novaAvalicao = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AvalicaoActivity.this, NovaAvalicao.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListener);
    }
    @Override
    protected void onStart() {
        super.onStart();

        firebase.addValueEventListener(valueEventListener);

    }

    // Evento na liste View para mostrar informações na outra tela
    private AdapterView.OnItemClickListener mostarInformacao = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(AvalicaoActivity.this, MostrarConteudoLista.class);

            intent.putExtra("avalicao",listaDeAvalicao.get(position));
            startActivity(intent);
            finish();


        }
    };



}
