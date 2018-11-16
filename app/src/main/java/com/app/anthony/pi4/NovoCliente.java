package com.app.anthony.pi4;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.anthony.pi4.br.edu.cairu.database.Conexao;
import com.app.anthony.pi4.br.edu.cairu.modelo.Cliente;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class NovoCliente extends AppCompatActivity {

    EditText txtNovoClienteNome, txtNovoClienteCpf, txtNovoClienteEmail, txtNovoClienteTelefone,
            txtNovoClienteSenha;
    Button btnNovoClienteSalvar;
    Cliente cliente;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_cliente);
        cliente = new Cliente();
        inicializarComponentes();
    }

    private void inicializarComponentes() {

        txtNovoClienteNome = findViewById(R.id.txtNovoClienteNome);
        txtNovoClienteCpf = findViewById(R.id.txtNovoClienteCpf);
        txtNovoClienteEmail = findViewById(R.id.txtNovoClienteEmail);
        txtNovoClienteTelefone = findViewById(R.id.txtNovoClienteTelefone);
        txtNovoClienteSenha = findViewById(R.id.txtNovoClienteSenha);

        //Criando mascara para o campo cnpj
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(txtNovoClienteCpf, smf);
        txtNovoClienteCpf.addTextChangedListener(mtw);
        //fim da mascara

        btnNovoClienteSalvar = findViewById(R.id.btnNovoClienteSalvar);
        btnNovoClienteSalvar.setOnClickListener(salvar);
        inicializarFirebase();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(NovoCliente.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void validaCampos() {
        boolean res = false;
        String nome = txtNovoClienteNome.getText().toString();
        String cpf = txtNovoClienteCpf.getText().toString();
        String email = txtNovoClienteEmail.getText().toString().trim();
        String telefone = txtNovoClienteTelefone.getText().toString();
        String senha = txtNovoClienteSenha.getText().toString().trim();

        if (res = isCamposVazio(nome)) {
            txtNovoClienteNome.requestFocus();
        } else if (res = isCamposVazio(cpf)) {
            txtNovoClienteCpf.requestFocus();
        } else if (res = !isEmailValido(email)) {
            txtNovoClienteEmail.requestFocus();
        } else if (res = isCamposVazio(telefone)) {
            txtNovoClienteTelefone.requestFocus();
        } else if (res = isCamposVazio(senha)) {
            txtNovoClienteSenha.requestFocus();
        }
        if (res) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Há Campos Invávidos ou em Branco");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        } else {

            cliente.setId(UUID.randomUUID().toString());
            cliente.setNome(nome);
            cliente.setCpf(cpf);
            cliente.setEmail(email);
            cliente.setSenha(senha);
            cliente.setTelefone(telefone);
            criarUsuario(email, senha);

        }

    }


    private void criarUsuario(String email, String senha) {
        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(NovoCliente.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(NovoCliente.this, "Cadastro feito com sucesso!", Toast.LENGTH_LONG).show();
                            databaseReference.child("cliente").child(cliente.getId()).setValue(cliente);
                            limparCampos();
                            finish();
                        } else {
                            Toast.makeText(NovoCliente.this, "Erro ao cadastrar!" + task, Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    private boolean isCamposVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty()); // Trim() ver setem espaço no campo
        return resultado;
    }

    private boolean isEmailValido(String email) { // Validação de email
        boolean resultado = (!isCamposVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }

    private void limparCampos() {
        txtNovoClienteNome.setText("");
        txtNovoClienteCpf.setText("");
        txtNovoClienteEmail.setText("");
        txtNovoClienteTelefone.setText("");
        txtNovoClienteSenha.setText("");

    }

    View.OnClickListener salvar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            validaCampos();
        }
    };
}
