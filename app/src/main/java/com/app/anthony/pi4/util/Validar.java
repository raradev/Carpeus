package com.app.anthony.pi4.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


import com.github.rtoshiro.util.format.SimpleMaskFormatter;


import com.app.anthony.pi4.NovoCliente;
import com.app.anthony.pi4.R;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class Validar extends AppCompatActivity {

    ValidarCnpj validarCnpj;
    private EditText editText;
    private ImageButton imageButton;
    private Button btnVoltar;
    private NovoCliente novoCliente = new NovoCliente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_cliente);

        editText = findViewById(R.id.txtNovoClienteCpf);
        //Criando mascara para o campo cnpj
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(editText, smf);
        editText.addTextChangedListener(mtw);
        //fim da mascara

    }
}
