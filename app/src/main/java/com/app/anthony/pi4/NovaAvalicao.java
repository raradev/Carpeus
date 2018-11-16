package com.app.anthony.pi4;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.anthony.pi4.br.edu.cairu.modelo.Avalicao;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class NovaAvalicao extends AppCompatActivity {

    private EditText txtAvalicaoNome;
    private Spinner comBoxQualificao;
    private EditText txtDescricao;
    private Button btnAvalicaoSalvar;
    private Avalicao avalicao;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
   // private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_avalicao);
        inicializarComponentes();
        inicializarFirebase();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(NovaAvalicao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void inicializarComponentes(){
        txtAvalicaoNome = findViewById(R.id.txtAvalicaoNome);
        comBoxQualificao = findViewById(R.id.comBoxQualificao);
        txtDescricao = findViewById(R.id.txtDescricao);
        btnAvalicaoSalvar = findViewById(R.id.btnAvalicaoSalvar);
        btnAvalicaoSalvar.setOnClickListener(salvarAvalicao);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.comBoxQualificao, android.R.layout.simple_spinner_item);
        comBoxQualificao.setAdapter(adapter);
        avalicao = new Avalicao();
    }

    private View.OnClickListener salvarAvalicao = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            validaCampos();
        }
    };

    public void validaCampos() {
        boolean res = false;
        String nome = txtAvalicaoNome.getText().toString();
        String descricao = txtDescricao.getText().toString();
        String qualificacao = comBoxQualificao.getSelectedItem().toString();

        if (res = isCamposVazio(nome)) {
            txtAvalicaoNome.requestFocus();
        } else if (res = isCamposVazio(descricao)) {
            txtDescricao.requestFocus();
        }
        if (res) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Há Campos Invávidos ou em Branco");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        } else {
            avalicao.setId(UUID.randomUUID().toString());
            avalicao.setNome(nome);
            avalicao.setQualificacao(qualificacao);
            avalicao.setDescricao(descricao);
            databaseReference.child("avalicao").child(avalicao.getId()).setValue(avalicao);
            Toast.makeText(this, "Avalição Salvar com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
    private boolean isCamposVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty()); // Trim() ver setem espaço no campo
        return resultado;
    }
}
