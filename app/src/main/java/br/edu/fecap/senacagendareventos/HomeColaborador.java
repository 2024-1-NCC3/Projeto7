package br.edu.fecap.senacagendareventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeColaborador extends AppCompatActivity {

    private ImageView imageViewCriarEventos,imageViewMeusEventos, imageViewGaleria,imageViewConvidar,
            imageViewConvites,imageViewSobreEvento;

    private TextView textViewUltimoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_colaborador);

        // Declaração dos ImageViews
        imageViewCriarEventos = findViewById(R.id.imageViewCriarEventos);
        imageViewMeusEventos = findViewById(R.id.imageViewMeusEventos);
        imageViewGaleria = findViewById(R.id.imageViewGaleria);
        imageViewConvidar = findViewById(R.id.imageViewConvidar);
        imageViewConvites = findViewById(R.id.imageViewConvites);
        imageViewSobreEvento = findViewById(R.id.imageViewSobreEvento);

        // Configuração do onTouchListener para aplicar a animação de escala
        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Aplica a animação de escala para reduzir o tamanho quando o usuário toca no ImageView
                        v.animate().scaleX(0.9f).scaleY(0.9f).setDuration(200).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        // Aplica a animação de retorno para restaurar o tamanho original quando o usuário levanta o dedo
                        v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).start();
                        break;
                }
                return false;
            }
        };

        // Aplica o onTouchListener para cada ImageView
        imageViewCriarEventos.setOnTouchListener(onTouchListener);
        imageViewMeusEventos.setOnTouchListener(onTouchListener);
        imageViewGaleria.setOnTouchListener(onTouchListener);
        imageViewConvidar.setOnTouchListener(onTouchListener);
        imageViewConvites.setOnTouchListener(onTouchListener);
        imageViewSobreEvento.setOnTouchListener(onTouchListener);

        textViewUltimoEvento = findViewById(R.id.textViewUltimoEvento);

        // Faz uma requisição GET para obter o último evento
        String url = "https://64qzhc-3000.csb.app/ultimo-evento";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Obtém o nome do último evento do JSON de resposta
                            String ultimoEvento = response.getString("ultimo_evento");
                            // Preenche o TextView com o nome do último evento
                            textViewUltimoEvento.setText(ultimoEvento);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(HomeColaborador.this, "Erro ao processar resposta do servidor", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Erro", error.toString());
                        Toast.makeText(HomeColaborador.this, "Erro ao obter último evento do servidor", Toast.LENGTH_SHORT).show();
                    }
                });

        // Adiciona a requisição à fila de requisições do Volley
        Volley.newRequestQueue(this).add(request);
    }


    //Método para abrir as telas
    public void abrirTela(Class<?> tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
    }
    //Chamando o método
    public void TelaHome(View view) {
        abrirTela(HomeColaborador.class);
    }

    public void TelaCriarEventos(View view) {
        abrirTela(CriarEvento.class);
    }

    public void TelaMeusEventos(View view) {
        abrirTela(ListarEventos.class);
    }

    public void TelaGaleria(View view) {
        abrirTela(Galeria.class);
    }

    public void TelaConvidar(View view) {
        abrirTela(Convidar.class);
    }

    public void TelaConvites(View view) {
        abrirTela(Convites.class);
    }

}