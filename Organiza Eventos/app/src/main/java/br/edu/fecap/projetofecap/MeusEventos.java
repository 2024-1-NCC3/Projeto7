package br.edu.fecap.projetofecap;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class MeusEventos extends AppCompatActivity {

    private RecyclerView recyclerViewEventos;
    private EventoAdapter eventoAdapter;
    private List<Evento> eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_eventos);

        recyclerViewEventos = findViewById(R.id.recyclerViewEventos);
        recyclerViewEventos.setLayoutManager(new LinearLayoutManager(this));

        eventos = new ArrayList<>();
        eventoAdapter = new EventoAdapter(eventos);
        recyclerViewEventos.setAdapter(eventoAdapter);

        // Configurando o listener de clique para o adaptador de eventos
        eventoAdapter.setOnItemClickListener(new EventoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Obtendo o evento clicado
                Evento eventoClicado = eventos.get(position);
                // Exibindo os detalhes do evento em um AlertDialog
                exibirDetalhesEvento(eventoClicado);
            }
        });

        SearchView searchViewEventos = findViewById(R.id.searchViewEventos);
        searchViewEventos.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Este método é chamado quando o usuário pressiona o botão de envio na caixa de pesquisa
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Este método é chamado quando o texto na caixa de pesquisa muda
                pesquisarEventos(newText);
                return false;
            }
        });

        // URL da rota que lista os eventos no servidor
        String url = "https://jllm8c-4000.csb.app/listar-eventos";

        // Inicialize a fila de solicitações Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);

// Faça uma solicitação GET para obter os eventos do servidor
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Processar a resposta JSON
                        eventos.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonEvento = response.getJSONObject(i);
                                String id = jsonEvento.getString("id"); // Obtendo o ID do evento
                                String codigoOferta = jsonEvento.getString("codigo_oferta");
                                String nome = jsonEvento.getString("nome_evento");
                                String tipo = jsonEvento.getString("tipo");
                                String descricao = jsonEvento.getString("descricao");
                                String categoria = jsonEvento.getString("categoria");
                                String publicoAlvo = jsonEvento.getString("publico_alvo");
                                String local = jsonEvento.getString("local");
                                String data = jsonEvento.getString("data_selecionada");
                                String hora = jsonEvento.getString("hora_selecionada");

                                // Crie um objeto Evento com os dados recebidos do servidor
                                Evento evento = new Evento(id, codigoOferta, nome, tipo, descricao, categoria, publicoAlvo, local, data, hora,
                                        false, false, false, false, false, false, false, false, false, false, false, false, false, false);


                                // Adicione o evento à lista de eventos
                                eventos.add(evento);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // Notifique o adapter sobre as mudanças nos dados
                        eventoAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Tratar erros de conexão ou de servidor
                Toast.makeText(MeusEventos.this, "Erro ao obter eventos do servidor", Toast.LENGTH_SHORT).show();
            }
        });


        // Adicione a solicitação à fila de solicitações Volley
        requestQueue.add(jsonArrayRequest);
    }

    // Método para exibir os detalhes do evento em um AlertDialog
    private void exibirDetalhesEvento(Evento evento) {
        // Inflar o layout personalizado
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_event_detalhes, null);

        // Referências para os TextViews no layout personalizado
        TextView tvCodigoOferta = dialogView.findViewById(R.id.tvCodigoOferta);
        TextView tvNome = dialogView.findViewById(R.id.tvNome);
        TextView tvTipo = dialogView.findViewById(R.id.tvTipo);
        TextView tvDescricao = dialogView.findViewById(R.id.tvDescricao);
        TextView tvCategoria = dialogView.findViewById(R.id.tvCategoria);
        TextView tvPublicoAlvo = dialogView.findViewById(R.id.tvPublicoAlvo);
        TextView tvLocal = dialogView.findViewById(R.id.tvLocal);
        TextView tvData = dialogView.findViewById(R.id.tvData);
        TextView tvHora = dialogView.findViewById(R.id.tvHora);

        TextView tvBelezaEstetica = dialogView.findViewById(R.id.tvBelezaEstetica);
        TextView tvBemEstar = dialogView.findViewById(R.id.tvBemEstar);
        TextView tvComunicacaoMarketing = dialogView.findViewById(R.id.tvComunicacaoMarketing);
        TextView tvDesignArtesArquitetura = dialogView.findViewById(R.id.tvDesignArtesArquitetura);
        TextView tvDesenvolvimentoSocial = dialogView.findViewById(R.id.tvDesenvolvimentoSocial);
        TextView tvEducacao = dialogView.findViewById(R.id.tvEducacao);
        TextView tvGastronomiaAlimentacao = dialogView.findViewById(R.id.tvGastronomiaAlimentacao);
        TextView tvGestaoNegocios = dialogView.findViewById(R.id.tvGestaoNegocios);
        TextView tvIdiomas = dialogView.findViewById(R.id.tvIdiomas);
        TextView tvMeioAmbienteSeguranca = dialogView.findViewById(R.id.tvMeioAmbienteSeguranca);
        TextView tvModa = dialogView.findViewById(R.id.tvModa);
        TextView tvSaude = dialogView.findViewById(R.id.tvSaude);
        TextView tvTecnologiaInformacao = dialogView.findViewById(R.id.tvTecnologiaInformacao);
        TextView tvTurismoHospitalidade = dialogView.findViewById(R.id.tvTurismoHospitalidade);

        // Definindo o texto para cada TextView
        tvCodigoOferta.setText("Código da Oferta: " + evento.getCodigoOferta());
        tvNome.setText("Nome: " + evento.getNome());
        tvTipo.setText("Tipo: " + evento.getTipo());
        tvDescricao.setText("Descrição: " + evento.getDescricao());
        tvCategoria.setText("Categoria: " + evento.getCategoria());
        tvPublicoAlvo.setText("Público Alvo: " + evento.getPublicoAlvo());
        tvLocal.setText("Local: " + evento.getLocal());
        tvData.setText("Data: " + evento.getData());
        tvHora.setText("Hora: " + evento.getHora());

        tvBelezaEstetica.setText("Beleza e Estética: " + (evento.isBelezaEstetica() ? "Sim" : "Não"));
        tvBemEstar.setText("Bem-Estar: " + (evento.isBemEstar() ? "Sim" : "Não"));
        tvComunicacaoMarketing.setText("Comunicação e Marketing: " + (evento.isComunicacaoMarketing() ? "Sim" : "Não"));
        tvDesignArtesArquitetura.setText("Design, Artes e Arquitetura: " + (evento.isDesignArtesArquitetura() ? "Sim" : "Não"));
        tvDesenvolvimentoSocial.setText("Desenvolvimento Social: " + (evento.isDesenvolvimentoSocial() ? "Sim" : "Não"));
        tvEducacao.setText("Educação: " + (evento.isEducacao() ? "Sim" : "Não"));
        tvGastronomiaAlimentacao.setText("Gastronomia e Alimentação: " + (evento.isGastronomiaAlimentacao() ? "Sim" : "Não"));
        tvGestaoNegocios.setText("Gestão e Negócios: " + (evento.isGestaoNegocios() ? "Sim" : "Não"));
        tvIdiomas.setText("Idiomas: " + (evento.isIdiomas() ? "Sim" : "Não"));
        tvMeioAmbienteSeguranca.setText("Meio Ambiente e Segurança: " + (evento.isMeioAmbienteSeguranca() ? "Sim" : "Não"));
        tvModa.setText("Moda: " + (evento.isModa() ? "Sim" : "Não"));
        tvSaude.setText("Saúde: " + (evento.isSaude() ? "Sim" : "Não"));
        tvTecnologiaInformacao.setText("Tecnologia da Informação: " + (evento.isTecnologiaInformacao() ? "Sim" : "Não"));
        tvTurismoHospitalidade.setText("Turismo e Hospitalidade: " + (evento.isTurismoHospitalidade() ? "Sim" : "Não"));


        // Definindo o estilo para o título
        TextView title = new TextView(this);
        title.setText("Detalhes do Evento");
        title.setPadding(10, 40, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextSize(20);
        title.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_semibold));
        title.setTextColor(Color.rgb(0,74,141));

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialogTheme);
        builder.setCustomTitle(title);
        builder.setView(dialogView);

        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Este listener está vazio, apenas fecha o diálogo
            }
        });

        builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Criando a intent para a atividade de atualização
                Intent intent = new Intent(MeusEventos.this, UpdateEventActivity.class);

                // Passando as informações do evento como extras
                intent.putExtra("evento_id", evento.getId());
                intent.putExtra("codigo_oferta", evento.getCodigoOferta());
                intent.putExtra("nome_evento", evento.getNome());
                intent.putExtra("tipo", evento.getTipo());
                intent.putExtra("descricao", evento.getDescricao());
                intent.putExtra("categoria", evento.getCategoria());
                intent.putExtra("publico_alvo", evento.getPublicoAlvo());
                intent.putExtra("local", evento.getLocal());
                intent.putExtra("data_selecionada", evento.getData());
                intent.putExtra("hora_selecionada", evento.getHora());
                // Adicione mais extras conforme necessário

                // Iniciando a atividade de atualização
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // Personalizar os botões após o diálogo ser mostrado
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);

        if (negativeButton != null && positiveButton != null) {
            negativeButton.setTextColor(Color.rgb(0,74,141));
            negativeButton.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_medium));
            positiveButton.setTextColor(Color.rgb(0,74,141));
            positiveButton.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_medium));
        }
    }



    private void pesquisarEventos(String query) {
        List<Evento> eventosFiltrados = new ArrayList<>();
        for (Evento evento : eventos) {
            if (evento.getNome().toLowerCase().contains(query.toLowerCase())) {
                eventosFiltrados.add(evento);
            }
        }
        eventoAdapter.updateEventos(eventosFiltrados);
        eventoAdapter.notifyDataSetChanged();
    }

    public void mudarTela(View view) {
        int id = view.getId();
        Intent intent;

        switch (id) {
            case R.id.imageViewHome3:
                intent = new Intent(this, Home.class);
                startActivity(intent);
                break;
            case R.id.imageViewNovoEvento3:
                intent = new Intent(this, NovoEvento.class);
                startActivity(intent);
                break;
            case R.id.imageViewMeusEventos3:
                // Nenhuma ação necessária, pois já está na tela de Meus Eventos
                break;
            // Adicione mais casos conforme necessário
        }
    }
}
