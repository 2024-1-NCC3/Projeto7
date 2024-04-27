package br.edu.fecap.senacagendareventos;
import android.content.Context;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class EventManager {

    public interface EventCreationListener {
        void onEventCreated();
        void onEventCreationFailed(String errorMessage);
    }

    public static void criarEvento(Context context, String codigoOferta, String nomeEvento, String data,
                                   String tipo, String descricao, String categoria, String publicoAlvo,
                                   String local, boolean belezaEstetica, boolean bemEstar,
                                   boolean comunicacaoMarketing, boolean designArtesArquitetura,
                                   boolean desenvolvimentoSocial, boolean educacao,
                                   boolean gastronomiaAlimentacao, boolean gestaoNegocios,
                                   boolean idiomas, boolean meioAmbienteSeguranca,
                                   boolean moda, boolean saude, boolean tecnologiaInformacao,
                                   boolean turismoHospitalidade, EventCreationListener listener) {
        String url = "https://64qzhc-3000.csb.app/eventos";

        JSONObject params = new JSONObject();
        try {
            params.put("codigo_oferta", codigoOferta);
            params.put("nome_evento", nomeEvento);
            params.put("data", data);
            params.put("tipo", tipo);
            params.put("descricao", descricao);
            params.put("categoria", categoria);
            params.put("publico_alvo", publicoAlvo);
            params.put("local", local);
            params.put("beleza_estetica", belezaEstetica ? 1 : 0);
            params.put("bem_estar", bemEstar ? 1 : 0);
            params.put("comunicacao_marketing", comunicacaoMarketing ? 1 : 0);
            params.put("design_artes_arquitetura", designArtesArquitetura ? 1 : 0);
            params.put("desenvolvimento_social", desenvolvimentoSocial ? 1 : 0);
            params.put("educacao", educacao ? 1 : 0);
            params.put("gastronomia_alimentacao", gastronomiaAlimentacao ? 1 : 0);
            params.put("gestao_negocios", gestaoNegocios ? 1 : 0);
            params.put("idiomas", idiomas ? 1 : 0);
            params.put("meio_ambiente_seguranca", meioAmbienteSeguranca ? 1 : 0);
            params.put("moda", moda ? 1 : 0);
            params.put("saude", saude ? 1 : 0);
            params.put("tecnologia_informacao", tecnologiaInformacao ? 1 : 0);
            params.put("turismo_hospitalidade", turismoHospitalidade ? 1 : 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Chamando o método onEventCreated do listener
                        listener.onEventCreated();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Chamando o método onEventCreationFailed do listener com a mensagem de erro
                listener.onEventCreationFailed("Erro ao criar evento: " + error.getMessage());
            }
        });

        // Adicionando a requisição à fila de requisições
        Volley.newRequestQueue(context).add(request);
    }
}
