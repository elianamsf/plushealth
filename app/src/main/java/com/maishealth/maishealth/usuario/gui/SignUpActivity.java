package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.infra.Mask;
import com.maishealth.maishealth.usuario.dominio.EnumEstados;
import com.maishealth.maishealth.usuario.dominio.EnumTipoSangue;
import com.maishealth.maishealth.usuario.negocio.Servicos;
import com.maishealth.maishealth.usuario.negocio.ValidaCadastro;

public class SignUpActivity extends AppCompatActivity {
    private EditText edtEmail, edtSenha, edtNome, edtCpf, edtNasc, edtCrm, edtEspec;
    private TextView edtRegiao;
    private Spinner spinnerSexo, spinnerTipoSangue, spinnerRegiao;
    private final String[] listaSexo = {"Feminino", "Masculino"};
    private final String[] listaEstados = EnumEstados.enumEstadosLista();
    private final String[] listaTipoSangue = EnumTipoSangue.enumTipoSangueLista();
    private Switch swUsuario;

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
        edtEspec = findViewById(R.id.edtEspec3);
        swUsuario = findViewById(R.id.swUsuario);
        edtCrm = findViewById(R.id.edtCRM3);
        edtCrm.addTextChangedListener(Mask.insert("###.###.###.#", edtCrm));
        edtRegiao = findViewById(R.id.textView7);


        // Checa se o switch usuário é medico ou paciente
        // para setar visibilidade dos campos do cadastro do médico.
        swUsuario.setChecked(false);
        spinnerRegiao = findViewById(R.id.SpnRegiao3);
        spinnerRegiao.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaEstados));
        spinnerRegiao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        swUsuario.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    swUsuario.setText(R.string.switch_medico);
                    edtCrm.setVisibility(View.VISIBLE);
                    edtRegiao.setVisibility(View.VISIBLE);
                    spinnerRegiao.setVisibility(View.VISIBLE);
                    edtEspec.setVisibility(View.VISIBLE);

                } else {
                    swUsuario.setText(R.string.switch_paciente);
                    edtCrm.setVisibility(View.INVISIBLE);
                    edtRegiao.setVisibility(View.INVISIBLE);
                    spinnerRegiao.setVisibility(View.INVISIBLE);
                    edtEspec.setVisibility(View.INVISIBLE);
                }
            }
        });

        //ArrayAdapter é usado para preparar a lista que será usada no Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaSexo);

        spinnerSexo = findViewById(R.id.spnSexo3);
        spinnerSexo.setAdapter(adapter);

        //Metodo para quando um elemento do Spinner é selecionado()
        spinnerSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaTipoSangue);
        spinnerTipoSangue = findViewById(R.id.spnTipoSangue3);
        spinnerTipoSangue.setAdapter(adapter2);
        spinnerTipoSangue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void onClickCadastrar(View view){
        String email            = edtEmail.getText().toString();
        String senha            = edtSenha.getText().toString();
        String nome             = edtNome.getText().toString();
        String sexo             = (String) spinnerSexo.getSelectedItem();
        String cpf              = edtCpf.getText().toString();
        String dataNasc         = edtNasc.getText().toString();
        String tipoSangue       = (String) spinnerTipoSangue.getSelectedItem();
        String crm              = edtCrm.getText().toString();
        String estado           = (String) spinnerRegiao.getSelectedItem();
        String especialidade    = edtEspec.getText().toString();

        ValidaCadastro validaCadastro = new ValidaCadastro();
        boolean valido = true;

        if(validaCadastro.isCampoVazio(especialidade) && swUsuario.isChecked()){
            edtEspec.requestFocus();
            edtEspec.setError("Especialidade inválida.");
            valido = false;
        }

        if(!validaCadastro.isCrmValido(crm) && swUsuario.isChecked()){
            edtCrm.requestFocus();
            edtCrm.setError("CRM inválido.");
            valido = false;
        }

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

        if(valido && !swUsuario.isChecked()){
            Servicos servicos = new Servicos(getApplicationContext());
            try{
                servicos.cadastrarPaciente(email, senha, nome, sexo, dataNasc, cpf, tipoSangue);
                Intent intent = new Intent(this, Login1Activity.class);
                startActivity(intent);
                finish();
                GuiUtil.myToast(this, "Paciente cadastrado com sucesso");
            } catch (Exception e) {
                GuiUtil.myToast(this, e);
            }
        } else if(valido && swUsuario.isChecked()) {
            Servicos servicos = new Servicos(getApplicationContext());
            try{
                servicos.cadastrarMedico(email, senha, nome, sexo, dataNasc, cpf, tipoSangue, crm, estado, especialidade);
                Intent intent = new Intent(this, Login1Activity.class);
                startActivity(intent);
                finish();
                GuiUtil.myToast(this, "Médico cadastrado com sucesso");
            } catch (Exception e) {
                GuiUtil.myToast(this, e);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.mudarTela(Login1Activity.class);
    }

    public void voltarTelaLogin(View view){
        this.mudarTela(Login1Activity.class);
    }

    public void mudarTela(Class tela){
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }
}