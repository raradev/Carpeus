package com.app.anthony.pi4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.app.anthony.pi4.br.edu.cairu.database.Conexao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginCliente extends AppCompatActivity {

    private EditText txtClienteLoginEmail, txtClienteLoginSenha;
    private Button btnLoginCliente, btnClienteNovoLogin;
    private TextView lblEsqueceminhaSenha;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_cliente);
        inicializarComponentes();
        limparCampos();
    }

    private void inicializarComponentes() {
        txtClienteLoginEmail = findViewById(R.id.txtClienteLoginEmail);
        txtClienteLoginSenha = findViewById(R.id.txtClienteLoginSenha);

        btnClienteNovoLogin = findViewById(R.id.btnClienteNovoLogin);
        btnClienteNovoLogin.setOnClickListener(novoCliente);

        btnLoginCliente = findViewById(R.id.btnLoginCliente);
        btnLoginCliente.setOnClickListener(logar);

        lblEsqueceminhaSenha = findViewById(R.id.lblEsqueceminhaSenha);
        lblEsqueceminhaSenha.setOnClickListener(recuperarSenha);

    }

    private View.OnClickListener recuperarSenha = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginCliente.this, RecuperarSenha.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener novoCliente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginCliente.this, NovoCliente.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener logar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean res = false;

            String email = txtClienteLoginEmail.getText().toString().trim();
            String senha = txtClienteLoginSenha.getText().toString().trim();
            if (res = !isEmailValido(email)) {
                txtClienteLoginEmail.requestFocus();

            } else if (res = isCamposVazio(senha)) {
                txtClienteLoginSenha.requestFocus();
            }
            if (res) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(LoginCliente.this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Há Campos Invávidos ou em Branco");
                dlg.setNeutralButton("OK", null);
                dlg.show();
            } else {
                realizarLogin(email, senha);
            }
        }


    };

    public void realizarLogin(String email, String senha) {
        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(LoginCliente.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LoginCliente.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginCliente.this, "Erro! Email ou senha icorretos.", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(LoginCliente.this, "Erro! Email ou senha icorretos." + e, Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        FirebaseUser currentUser = auth.getCurrentUser();
    }



    private boolean isCamposVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty()); // Trim() ver setem espaço no campo
        return resultado;
    }

    private boolean isEmailValido(String email) { // Validação de email
        boolean resultado = (!isCamposVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;

    }

    public void limparCampos(){
        txtClienteLoginEmail.setText("");
        txtClienteLoginSenha.setText("");
    }


}
