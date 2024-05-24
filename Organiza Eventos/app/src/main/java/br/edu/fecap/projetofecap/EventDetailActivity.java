package br.edu.fecap.projetofecap;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EventDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        TextView nomeEvento = findViewById(R.id.nomeEvento);
        TextView nomeColaborador = findViewById(R.id.nomeColaborador);
        TextView dataEvento = findViewById(R.id.dataEvento);
        TextView horaEvento = findViewById(R.id.horaEvento);

        // Pegar dados do Intent
        int id = getIntent().getIntExtra("id", -1);
        String nomeEventoStr = getIntent().getStringExtra("nomeEvento");
        String nomeColaboradorStr = getIntent().getStringExtra("nomeColaborador");
        String dataEventoStr = getIntent().getStringExtra("dataEvento");
        String horaEventoStr = getIntent().getStringExtra("horaEvento");

        nomeEvento.setText(nomeEventoStr);
        nomeColaborador.setText(nomeColaboradorStr);
        dataEvento.setText(dataEventoStr);
        horaEvento.setText(horaEventoStr);
    }
}
