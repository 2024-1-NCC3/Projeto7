package br.edu.fecap.projetofecap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class Home extends AppCompatActivity {

    private TextView nomeEventoTextView;
    private TextView dataHoraSelecionadaTextView;
    private TextView saudacaoTextView;
    private RequestQueue requestQueue;
    private String urlProximoEvento = "https://jllm8c-4000.csb.app/proximoEvento/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nomeEventoTextView = findViewById(R.id.nomeEventoTextView);
        dataHoraSelecionadaTextView = findViewById(R.id.textViewDiaHora);
        saudacaoTextView = findViewById(R.id.textViewNomeColaborador);
        requestQueue = Volley.newRequestQueue(this);

        exibirSaudacaoColaborador();
        obterProximoEvento();
    }

    private void exibirSaudacaoColaborador() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String nomeColaborador = sharedPreferences.getString("nomeColaborador", "");
        saudacaoTextView.setText("Olá, " + nomeColaborador);
    }

    private void obterProximoEvento() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int idColaborador = sharedPreferences.getInt("colaborador_id", -1);

        if (idColaborador == -1) {
            Log.e("HomeActivity", "ID do colaborador não encontrado na SharedPreferences");
            return;
        }

        String urlProximoEventoComId = urlProximoEvento + idColaborador;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                urlProximoEventoComId,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.has("nome_evento") && response.has("data_selecionada") && response.has("hora_selecionada")) {
                                String nomeEvento = response.getString("nome_evento");
                                String dataSelecionada = response.getString("data_selecionada");
                                String horaSelecionada = response.getString("hora_selecionada");
                                String dataHoraSelecionada = dataSelecionada + " " + horaSelecionada;

                                nomeEventoTextView.setText(nomeEvento);
                                dataHoraSelecionadaTextView.setText(dataHoraSelecionada);
                            } else {
                                nomeEventoTextView.setText("Dados inválidos");
                                dataHoraSelecionadaTextView.setText("Dados inválidos");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            nomeEventoTextView.setText("Erro ao processar os dados");
                            dataHoraSelecionadaTextView.setText("Erro ao processar os dados");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        nomeEventoTextView.setText("Erro na rede ou servidor");
                        dataHoraSelecionadaTextView.setText("Erro na rede ou servidor");
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }


    public void logout(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}
