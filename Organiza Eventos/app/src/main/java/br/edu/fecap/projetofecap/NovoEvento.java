package br.edu.fecap.projetofecap;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NovoEvento extends AppCompatActivity {


    private EditText editTextCodigo;
    private EditText editTextNomeEvento;
    private EditText editTextTipo;
    private EditText editTextDesc;
    private EditText editTextCategoria;
    private EditText editTextPublico;
    private EditText editTextLocal;
    private TextView textViewDataSelecionada;
    private TextView textViewHoraSelecionada;

    private CheckBox checkBoxBelezaEstetica;
    private CheckBox checkBoxBemEstar;
    private CheckBox checkBoxComunicacaoMarketing;
    private CheckBox checkBoxDesignArtesArquitetura;
    private CheckBox checkBoxDesenvolvimentoSocial;
    private CheckBox checkBoxEducacao;
    private CheckBox checkBoxGastronomiaAlimentacao;
    private CheckBox checkBoxGestaoNegocios;
    private CheckBox checkBoxIdiomas;
    private CheckBox checkBoxMeioAmbienteSeguranca;
    private CheckBox checkBoxModa;
    private CheckBox checkBoxSaude;
    private CheckBox checkBoxTecnologiaInformacao;
    private CheckBox checkBoxTurismoHospitalidade;

    private RequestQueue requestQueue;
    private String url = "https://jllm8c-4000.csb.app/eventos" +
            "";

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_evento);

        editTextCodigo = findViewById(R.id.editTextCodigo);
        editTextNomeEvento = findViewById(R.id.editTextNomeEvento);
        editTextTipo = findViewById(R.id.editTextTipo);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextCategoria = findViewById(R.id.editTextCategoria);
        editTextPublico = findViewById(R.id.editTextPublico);
        editTextLocal = findViewById(R.id.editTextLocal);
        textViewDataSelecionada = findViewById(R.id.textViewDataSelecionada);
        textViewHoraSelecionada = findViewById(R.id.textViewHoraSelecionada);

        checkBoxBelezaEstetica = findViewById(R.id.checkBoxBelezaEstetica);
        checkBoxBemEstar = findViewById(R.id.checkboxBemEstar);
        checkBoxComunicacaoMarketing = findViewById(R.id.checkboxComunicacaoMarketing);
        checkBoxDesignArtesArquitetura = findViewById(R.id.checkboxDesignArtesArquitetura);
        checkBoxDesenvolvimentoSocial = findViewById(R.id.checkboxDesenvolvimentoSocial);
        checkBoxEducacao = findViewById(R.id.checkboxEducacao);
        checkBoxGastronomiaAlimentacao = findViewById(R.id.checkboxGastronomiaAlimentacao);
        checkBoxGestaoNegocios = findViewById(R.id.checkboxGestaoNegocios);
        checkBoxIdiomas = findViewById(R.id.checkboxIdiomas);
        checkBoxMeioAmbienteSeguranca = findViewById(R.id.checkboxMeioAmbienteSeguranca);
        checkBoxModa = findViewById(R.id.checkboxModa);
        checkBoxSaude = findViewById(R.id.checkboxSaude);
        checkBoxTecnologiaInformacao = findViewById(R.id.checkboxTecnologiaInformacao);
        checkBoxTurismoHospitalidade = findViewById(R.id.checkboxTurismoHospitalidade);

        requestQueue = Volley.newRequestQueue(this);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        // Exibir a data atual no campo de data
        textViewDataSelecionada.setText(dateFormat.format(calendar.getTime()));
        // Exibir a hora atual no campo de hora
        textViewHoraSelecionada.setText(timeFormat.format(calendar.getTime()));

        // Encontre o botão pelo ID
        Button buttonCriarEvento = findViewById(R.id.buttonSalvar);

        // Defina um ouvinte de clique para o botão
        buttonCriarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chame o método criarEvento quando o botão for clicado
                criarEvento(v);
            }
        });
    }

    public void selecionarData(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        textViewDataSelecionada.setText(dateFormat.format(calendar.getTime())); // Corrigido para textViewDataSelecionada
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void selecionarHora(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        textViewHoraSelecionada.setText(timeFormat.format(calendar.getTime()));
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }


    public void criarEvento(View view) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("codigo_oferta", editTextCodigo.getText().toString());
            jsonObject.put("nome_evento", editTextNomeEvento.getText().toString());
            jsonObject.put("tipo", editTextTipo.getText().toString());
            jsonObject.put("descricao", editTextDesc.getText().toString());
            jsonObject.put("categoria", editTextCategoria.getText().toString());
            jsonObject.put("publico_alvo", editTextPublico.getText().toString());
            jsonObject.put("local", editTextLocal.getText().toString());
            jsonObject.put("data_selecionada", textViewDataSelecionada.getText().toString());
            jsonObject.put("hora_selecionada", textViewHoraSelecionada.getText().toString());

            jsonObject.put("beleza_estetica", checkBoxBelezaEstetica.isChecked());
            jsonObject.put("bem_estar", checkBoxBemEstar.isChecked());
            jsonObject.put("comunicacao_marketing", checkBoxComunicacaoMarketing.isChecked());
            jsonObject.put("design_artes_arquitetura", checkBoxDesignArtesArquitetura.isChecked());
            jsonObject.put("desenvolvimento_social", checkBoxDesenvolvimentoSocial.isChecked());
            jsonObject.put("educacao", checkBoxEducacao.isChecked());
            jsonObject.put("gastronomia_alimentacao", checkBoxGastronomiaAlimentacao.isChecked());
            jsonObject.put("gestao_negocios", checkBoxGestaoNegocios.isChecked());
            jsonObject.put("idiomas", checkBoxIdiomas.isChecked());
            jsonObject.put("meio_ambiente_seguranca", checkBoxMeioAmbienteSeguranca.isChecked());
            jsonObject.put("moda", checkBoxModa.isChecked());
            jsonObject.put("saude", checkBoxSaude.isChecked());
            jsonObject.put("tecnologia_informacao", checkBoxTecnologiaInformacao.isChecked());
            jsonObject.put("turismo_hospitalidade", checkBoxTurismoHospitalidade.isChecked());

            // Recuperar o token do SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
            String authToken = sharedPreferences.getString("token", "");

            // Criar uma solicitação personalizada para adicionar o token ao cabeçalho
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Manipular a resposta do servidor
                            Toast.makeText(NovoEvento.this, "Evento criado com sucesso", Toast.LENGTH_SHORT).show();
                            limparCampos(); // Limpar os campos após o sucesso
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Manipular erro de resposta
                    Toast.makeText(NovoEvento.this, "Erro ao criar evento: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    // Incluir o token JWT no cabeçalho de autorização
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer " + authToken);
                    return headers;
                }
            };

            // Adicionar a solicitação à fila de solicitações Volley
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void mudarTela2(View view) {
        int id = view.getId();
        Intent intent;

        switch (id) {
            case R.id.imageViewHome2:
                intent = new Intent(this, Home.class);
                startActivity(intent);
                break;
            case R.id.imageViewMeusEventos2:
                intent = new Intent(this, MeusEventos.class);
                startActivity(intent);
                break;
        }
    }

    private void limparCampos() {
        editTextCodigo.setText("");
        editTextNomeEvento.setText("");
        editTextTipo.setText("");
        editTextDesc.setText("");
        editTextCategoria.setText("");
        editTextPublico.setText("");
        editTextLocal.setText("");
        textViewDataSelecionada.setText(dateFormat.format(calendar.getTime()));
        textViewHoraSelecionada.setText(timeFormat.format(calendar.getTime()));

        checkBoxBelezaEstetica.setChecked(false);
        checkBoxBemEstar.setChecked(false);
        checkBoxComunicacaoMarketing.setChecked(false);
        checkBoxDesignArtesArquitetura.setChecked(false);
        checkBoxDesenvolvimentoSocial.setChecked(false);
        checkBoxEducacao.setChecked(false);
        checkBoxGastronomiaAlimentacao.setChecked(false);
        checkBoxGestaoNegocios.setChecked(false);
        checkBoxIdiomas.setChecked(false);
        checkBoxMeioAmbienteSeguranca.setChecked(false);
        checkBoxModa.setChecked(false);
        checkBoxSaude.setChecked(false);
        checkBoxTecnologiaInformacao.setChecked(false);
        checkBoxTurismoHospitalidade.setChecked(false);
    }


}
