package br.edu.fecap.senacagendareventos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class LoadingActivity extends AppCompatActivity {

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
                Intent intent = new Intent(LoadingActivity.this, Login.class);
                startActivity(intent);

                // Finalize esta Activity para que o usuário não possa voltar para ela
                finish();
            }
        }, LOADING_TIME);
    }
}
