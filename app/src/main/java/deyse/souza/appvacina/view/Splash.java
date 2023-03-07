package deyse.souza.appvacina.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import deyse.souza.appvacina.R;
import deyse.souza.appvacina.api.AppUtil;


public class Splash extends AppCompatActivity {

    int tempoDeEspera = 1000 * 3;

 //   private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        trocarTela();
    }

    private void trocarTela() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent trocarDeTela = new Intent(Splash.this, Login.class);
                startActivity(trocarDeTela);
                finish();


            }
        }, tempoDeEspera);


    }

 //   private void salvarSharedPreferences(){

  //      preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
  //      SharedPreferences.Editor dados = preferences.edit();

 //   }

 //   private void restaurarSharedPreferences(){

 //      preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

 //   }

}