package br.edu.fecap.senacagendareventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ListarEventos extends AppCompatActivity {

    private static final String TAG = "ListarEventosActivity";
    private ListView listViewEventos;
    private EventoAdapter eventoAdapter;
    private List<Evento> eventos;
    private int numeroEventosCarregados = 11; // Número inicial de eventos carregados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_eventos);

        listViewEventos = findViewById(R.id.listViewEventos);
        eventos = new ArrayList<>();
        eventoAdapter = new EventoAdapter(this, eventos);
        listViewEventos.setAdapter(eventoAdapter);

        carregarEventosIniciais();

        // Implemente a lógica para carregar os eventos restantes ao usar o scroll view
        listViewEventos.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                    // Chegou ao final da lista, carregar mais eventos
                    carregarEventosRestantes();
                }
            }
        });
    }

    private void carregarEventosIniciais() {
        String url = "https://64qzhc-3000.csb.app/listar-eventos?limit=" + numeroEventosCarregados;

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        eventos.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Evento evento = parseEvento(jsonObject);
                                eventos.add(evento);
                            } catch (JSONException e) {
                                Log.e(TAG, "Erro ao fazer parsing do JSON", e);
                            }
                        }
                        eventoAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Erro na requisição HTTP", error);
                    }
                }
        );

        queue.add(jsonArrayRequest);
    }

    private void carregarEventosRestantes() {
        // Incrementar o número de eventos carregados
        numeroEventosCarregados += 11;

        String url = "https://64qzhc-3000.csb.app/listar-eventos?limit=" + numeroEventosCarregados;

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        eventos.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Evento evento = parseEvento(jsonObject);
                                eventos.add(evento);
                            } catch (JSONException e) {
                                Log.e(TAG, "Erro ao fazer parsing do JSON", e);
                            }
                        }
                        eventoAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Erro na requisição HTTP", error);
                    }
                }
        );

        queue.add(jsonArrayRequest);
    }

    private Evento parseEvento(JSONObject jsonObject) throws JSONException {
        String codigoOferta = jsonObject.getString("codigo_oferta");
        String nomeEvento = jsonObject.getString("nome_evento");
        String data = jsonObject.getString("data");
        String tipo = jsonObject.getString("tipo");
        String descricao = jsonObject.getString("descricao");
        String categoria = jsonObject.getString("categoria");
        String publicoAlvo = jsonObject.getString("publico_alvo");
        String local = jsonObject.getString("local");

        return new Evento(codigoOferta, nomeEvento, data, tipo, descricao, categoria, publicoAlvo, local);
    }

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
}
