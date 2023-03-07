package deyse.souza.appvacina.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import deyse.souza.appvacina.R;
import deyse.souza.appvacina.api.AppUtil;
import deyse.souza.appvacina.controller.UsuarioController;
import deyse.souza.appvacina.model.Usuario;

public class CadastroUsuario extends AppCompatActivity {

    Button btnCadastro;
    EditText editNome, editEmail, editSenhaA, editSenhaB, editCnes;

    TextView txtCnes;

    CheckBox ckTermo;

    RadioButton radioButtonP, radioButtonI;
    RadioGroup radioGroupTipo;

    Spinner spinnerEstado, spinnerMunicipio;
    boolean isFormularioOK;
    Usuario novoUsuario;
    UsuarioController controller;
    private SharedPreferences preferences;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        initFormulario();
        carregarDadosSpinner();


        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isFormularioOK = validarFormulario()) {


                    novoUsuario.setNome(editNome.getText().toString());
                    novoUsuario.setEmail(editEmail.getText().toString());
                    novoUsuario.setSenha(editSenhaA.getText().toString());
                    novoUsuario.setTipoperfil(String.valueOf(radioGroupTipo.getCheckedRadioButtonId()));
                    novoUsuario.setCnes(editCnes.getText().toString());
                    novoUsuario.setEstado(spinnerEstado.getSelectedItem().toString());
                    novoUsuario.setMunicipio(spinnerMunicipio.getSelectedItem().toString());

                    salvarSharedPreferences();

                    controller.incluir(novoUsuario);

                    Intent iTelaPessoa = new Intent(CadastroUsuario.this, Login.class);
                    startActivity(iTelaPessoa);

                }
            }
        });

    }


    private void initFormulario() {
        btnCadastro = findViewById(R.id.btnCadastro);
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenhaA = findViewById(R.id.editSenhaA);
        editSenhaB = findViewById(R.id.editSenhaB);
        ckTermo = findViewById(R.id.ckTermo);
        radioButtonP = findViewById(R.id.radioButtonP);
        radioButtonI = findViewById(R.id.radioButtonI);
        radioGroupTipo = findViewById(R.id.radioGroupTipo);
        spinnerEstado = findViewById(R.id.spinnerEstado);
        spinnerMunicipio = findViewById(R.id.spinnerMunicipio);
        txtCnes = findViewById(R.id.txtCnes);
        editCnes = findViewById(R.id.editCnes);

        txtCnes.setVisibility(View.GONE);
        editCnes.setVisibility(View.GONE);

        radiobutton();

        controller = new UsuarioController(this);

        isFormularioOK = false;

        novoUsuario = new Usuario();

        controller = new UsuarioController(this);


    }

    public void vallidarTermo(View view) {

        if (!ckTermo.isChecked()) {
            Toast.makeText(getApplicationContext(),
                    "É necessário aceitar os termos de uso para continuar o cadastro!",
                    Toast.LENGTH_LONG).show();
        }

    }

    public boolean validarsenha() {
        boolean retorno = false;

        int senhaA, senhaB;

        senhaA = Integer.parseInt(editSenhaA.getText().toString());
        senhaB = Integer.parseInt(editSenhaB.getText().toString());

        retorno = (senhaA == senhaB);

        return retorno;
    }

    public void radiobutton() {

        radioGroupTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.radioButtonP) {
                    txtCnes.setVisibility(View.GONE);
                    editCnes.setVisibility(View.GONE);


                } else if (i == R.id.radioButtonI) {
                    txtCnes.setVisibility(View.VISIBLE);
                    editCnes.setVisibility(View.VISIBLE);


                }
            }

        });


    }

    private void carregarDadosSpinner() {
        String[] estados = new String[]{
                "SC", "PR", "RS"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);


        spinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                solicitaMunicipios();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void solicitaMunicipios() {

        int estado = spinnerEstado.getSelectedItemPosition();

        if (estado == 0) {

            String[] municipiosSC = new String[]{
                    "Ilhota", "Gaspar", "Blumenau"
            };
            ArrayAdapter<String> adapterSC = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, municipiosSC);
            adapterSC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerMunicipio.setAdapter(adapterSC);

        } else if (estado == 1) {
            String[] municipiosPR = new String[]{
                    "Antonina", "Bom Sucesso"
            };
            ArrayAdapter<String> adapterPR = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, municipiosPR);
            adapterPR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerMunicipio.setAdapter(adapterPR);
        } else if (estado == 2) {
            String[] municipiosRS = new String[]{
                    "Porto Alegre", "Canoas"
            };
            ArrayAdapter<String> adapterRS = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, municipiosRS);
            adapterRS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerMunicipio.setAdapter(adapterRS);
        }
    }

    public boolean validarFormulario() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editNome.getText().toString())) {
            editNome.setError("Campo inválido");
            editNome.requestFocus();
            isFormularioOK = false;
        }
        if (TextUtils.isEmpty(editEmail.getText().toString())) {
            editEmail.setError("Campo inválido");
            editEmail.requestFocus();
            isFormularioOK = false;
        }
        if (TextUtils.isEmpty(editSenhaA.getText().toString())) {
            editSenhaA.setError("Campo inválido");
            editSenhaA.requestFocus();
            isFormularioOK = false;
        }
        if (TextUtils.isEmpty(editSenhaB.getText().toString())) {
            editSenhaB.setError("Campo inválido");
            editSenhaB.requestFocus();
            isFormularioOK = false;
        }

        if (!ckTermo.isChecked()) {
            isFormularioOK = false;
        }

        if (isFormularioOK) {

            if (!validarsenha()) {
                editSenhaA.setError("Campo inválido");
                editSenhaB.setError("Campo inválido");
                editSenhaA.requestFocus();

                Toast.makeText(getApplicationContext(),
                        "As senhas digitadas não conferem!",
                        Toast.LENGTH_LONG).show();
            }
        }
        return retorno;
    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putString("Nome", novoUsuario.getNome());
        dados.putString("Email", novoUsuario.getEmail());
        dados.putString("Senha", novoUsuario.getSenha());
        dados.putString("TipoPerfil", novoUsuario.getTipoperfil());
        dados.putString("CNES", novoUsuario.getCnes());
        dados.putString("Estado", novoUsuario.getEstado());
        dados.putString("Municipio", novoUsuario.getMunicipio());
        dados.apply();
    }


}
