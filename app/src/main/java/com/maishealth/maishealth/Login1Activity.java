package com.maishealth.maishealth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.maishealth.maishealth.usuario.negocio.ValidaLogin;

public class Login1Activity extends AppCompatActivity {
    private EditText edtEmailLogin, edtSenhaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        edtEmailLogin = findViewById(R.id.emailx);
        edtSenhaLogin = findViewById(R.id.senhax);
    }

    public void validarLogin(View view) {
        String email = edtEmailLogin.getText().toString();
        String senha = edtSenhaLogin.getText().toString();

        ValidaLogin validaLogin = new ValidaLogin();
        boolean valido = true;

        if (!validaLogin.isSenhaValida(senha)) {
            edtSenhaLogin.requestFocus();
            edtSenhaLogin.setError("Senha deve ter 6 ou mais caracteres.");
            valido = false;
        }

        if (!validaLogin.isEmail(email)) {
            edtEmailLogin.requestFocus();
            edtEmailLogin.setError("Email inválido");
            valido = false;
        }
    }
}
