package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.infra.Mask;
import com.maishealth.maishealth.usuario.negocio.ValidaCadastro;

public class SignUpActivity extends AppCompatActivity {
    private AutoCompleteTextView edtEmail, edtSenha, edtNome;
    private EditText edtCpf, edtNasc;
    private Spinner spinner;
    private String[] listaSexo = {"Feminino", "Masculino", "Outros"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtEmail = findViewById(R.id.edtEmail3);
        edtSenha = findViewById(R.id.edtSenha3);
        edtNome = findViewById(R.id.edtNome3);
        edtCpf = findViewById(R.id.edtCpf3);
        edtCpf.addTextChangedListener(Mask.insert("###.###.###-##", edtCpf));
        edtNasc = findViewById(R.id.edtNasc3);
        edtNasc.addTextChangedListener(Mask.insert("##/##/####", edtNasc));

        //ArrayAdapter é usado para preparar a lista que será usada no Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaSexo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner)findViewById(R.id.spnSexo3);
        spinner.setAdapter(adapter);

        //Metodo para quando um elemento do Spinner é selecionado()
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void validarCadatro(View view){
        String email    = edtEmail.getText().toString();
        String senha    = edtSenha.getText().toString();
        String nome     = edtNome.getText().toString();
        String cpf      = edtCpf.getText().toString();
        String dataNasc = edtNasc.getText().toString();

        ValidaCadastro validaCadastro = new ValidaCadastro();
        boolean valido = true;

        if(!validaCadastro.isDataNascimento(dataNasc)){
            edtNasc.requestFocus();
            edtNasc.setError("Data Inválida.");
            valido = false;
        }

        if(!validaCadastro.isCpfValida(cpf)){
            edtCpf.requestFocus();
            edtCpf.setError("CPF inválido.");
            valido = false;
        }

        if(validaCadastro.isCampoVazio(nome)){
            edtNome.requestFocus();
            edtNome.setError("Nome inválido.");
            valido = false;
        }

        if(!validaCadastro.isSenhaValida(senha)){
            edtSenha.requestFocus();
            edtSenha.setError("Senha deve ter 6 ou mais caracteres.");
            valido = false;
        }

        if(!validaCadastro.isEmail(email)){
            edtEmail.requestFocus();
            edtEmail.setError("Email inválido");
            valido = false;
        }

        if(valido){
            GuiUtil.myToast(this, "Cadastro realizado com sucesso");
        }
    }

    public void voltarTelaLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}