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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarSenha extends AppCompatActivity {

    private EditText txtRecuperarSenhaEmail;
    private Button btnResetarSenha;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recuperar_senha);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        txtRecuperarSenhaEmail = findViewById(R.id.txtRecuperarSenhaEmail);
        btnResetarSenha = findViewById(R.id.btnResetarSenha);
        btnResetarSenha.setOnClickListener(resetar);

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }

    private View.OnClickListener resetar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email = txtRecuperarSenhaEmail.getText().toString().trim();
            boolean res = false;
            if (res = !isEmailValido(email)) {
                txtRecuperarSenhaEmail.requestFocus();
            }
            if (res) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(RecuperarSenha.this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Há Campos Invávidos ou em Branco");
                dlg.setNeutralButton("OK", null);
                dlg.show();
            } else {
                resetarSenha(email);
            }
        }
    };

    private void resetarSenha(String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(RecuperarSenha.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RecuperarSenha.this, "Um email foi encaminhado para resetar a senha.", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(RecuperarSenha.this, "Erro ao encaminhar email.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean isEmailValido(String email) { // Validação de email
        boolean resultado = (!isCamposVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;

    }

    private boolean isCamposVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty()); // Trim() ver setem espaço no campo
        return resultado;
    }
}
