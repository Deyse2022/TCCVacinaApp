package deyse.souza.appvacina.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import deyse.souza.appvacina.R;

public class Splash extends AppCompatActivity {

    int tempoDeEspera = 1000 * 3;


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

}