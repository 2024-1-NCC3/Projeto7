package br.edu.fecap.projetofecap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;


public class Loading extends AppCompatActivity {

    // Tempo de espera simulado em milissegundos
    private static final int LOADING_TIME = 1000; // 1 segundo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // Adiciona um atraso simulado antes de ir para a próxima Activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Inicie a próxima Activity
                Intent intent = new Intent(Loading.this, Login.class);
                startActivity(intent);

                // Finalize esta Activity para que o usuário não possa voltar para ela
                finish();
            }
        }, LOADING_TIME);
    }
}
