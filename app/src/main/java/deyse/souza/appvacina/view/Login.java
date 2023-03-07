package deyse.souza.appvacina.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import deyse.souza.appvacina.R;
import deyse.souza.appvacina.api.AppUtil;
import deyse.souza.appvacina.controller.UsuarioController;
import deyse.souza.appvacina.model.Usuario;

public class Login extends AppCompatActivity {

    Usuario usuario;

    TextView txtRecuperarSenha, txtLer;
    EditText editEmail, editSenha;
    CheckBox ckLembrar;
    Button btnAcessar, btnCadastro;

    boolean isFormularioOk, isLembrarSenha;

    UsuarioController controller;

    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initFormulario();

        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFormularioOk = validarFormulario()) {

                    if (validarDadosUsuario()) {
                        Intent intent =
                                new Intent(Login.this, Main.class);
                        startActivity(intent);
                        finish();
                        return;

                    } else {
                        Toast.makeText(getApplicationContext(), "Verifique os dados", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnCadastro = findViewById(R.id.btnCadastro);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iTelaCadastro = new Intent(Login.this, CadastroUsuario.class);
                startActivity(iTelaCadastro);
            }
        });


        txtRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Carregando tela de S",
                        Toast.LENGTH_LONG).show();
            }
        });

        txtLer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Carregando tela de P ",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validarFormulario() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editEmail.getText().toString())) {
            editEmail.setError("Campo inválido!");
            editEmail.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(editSenha.getText().toString())) {
            editSenha.setError("Campo inválido!");
            editSenha.requestFocus();
            retorno = false;
        }

        return retorno;
    }

    private void initFormulario() {

        txtRecuperarSenha = findViewById(R.id.txtRecuperarSenha);
        txtLer = findViewById(R.id.txtLer);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        ckLembrar = findViewById(R.id.ckLembrar);
        btnAcessar = findViewById(R.id.btnAcessar);
        btnCadastro = findViewById(R.id.btnCadastro);

        isFormularioOk = false;



        usuario = new Usuario();

  //      controller = new UsuarioController(getApplicationContext());


 //       controller.incluir(usuario);
  //      controller.alterar(usuario);
  //      controller.deletar(usuario);
 //       List<Usuario> usuarios = controller.listar();

    }

   public void lembrarSenha(View view) {

        isLembrarSenha = ckLembrar.isChecked();

   }

    public boolean validarDadosUsuario() {

        return true;
    }

    private void salvarSharedPreferences(){

       preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

    }

    private void restaurarSharedPreferences(){

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    }
}