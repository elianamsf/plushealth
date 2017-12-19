package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
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
        if (valido==true){
            //ajeitar aq verif no banco
            GuiUtil.myToast(this,"email e senha válido");
            Intent intent = new Intent( Login1Activity.this, MenuPaciente2.class);
            startActivity(intent);
            finish();
        }
    }
    public void tela1Cadastro(View view) {
        Intent intent = new Intent(Login1Activity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

}
