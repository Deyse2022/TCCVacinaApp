package deyse.souza.appvacina.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

import deyse.souza.appvacina.R;
import deyse.souza.appvacina.api.AppUtil;
import deyse.souza.appvacina.config.ConfiguracaoFirebase;


public class Splash extends AppCompatActivity {

    int tempoDeEspera = 1000 * 3;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        trocarTela();
    }

    private void trocarTela() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signOut();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (autenticacao.getCurrentUser() == null) {

                    Intent trocarDeTela = new Intent(Splash.this, Login.class);
                    startActivity(trocarDeTela);
                    finish();
                } else if (autenticacao.getCurrentUser() != null) {
                    Intent trocarDeTela = new Intent(Splash.this, MainPessoa.class);
                    startActivity(trocarDeTela);

                }

            }
        }, tempoDeEspera);
    }
}
