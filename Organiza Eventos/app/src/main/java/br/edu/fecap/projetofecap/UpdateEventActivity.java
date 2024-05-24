package br.edu.fecap.projetofecap;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateEventActivity extends AppCompatActivity {

    private EditText editTextIdEvento,editTextCodigo, editTextNomeEvento, editTextTipo, editTextDesc, editTextCategoria, editTextPublico, editTextLocal;
    private CheckBox checkBoxBelezaEstetica, checkboxBemEstar, checkboxComunicacaoMarketing, checkboxDesignArtesArquitetura, checkboxDesenvolvimentoSocial,
            checkboxEducacao, checkboxGastronomiaAlimentacao, checkboxGestaoNegocios, checkboxIdiomas, checkboxMeioAmbienteSeguranca,
            checkboxModa, checkboxSaude, checkboxTecnologiaInformacao, checkboxTurismoHospitalidade;
    private Button buttonSelecionarData, buttonSelecionarHora, buttonSalvar;

    private ProgressDialog progressDialog;
    private Calendar calendar;
    private SimpleDateFormat dateFormat, timeFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);

        editTextCodigo = findViewById(R.id.editTextCodigo);
        editTextNomeEvento = findViewById(R.id.editTextNomeEvento);
        editTextTipo = findViewById(R.id.editTextTipo);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextCategoria = findViewById(R.id.editTextCategoria);
        editTextPublico = findViewById(R.id.editTextPublico);
        editTextLocal = findViewById(R.id.editTextLocal);

        checkBoxBelezaEstetica = findViewById(R.id.checkBoxBelezaEstetica);
        checkboxBemEstar = findViewById(R.id.checkboxBemEstar);
        checkboxComunicacaoMarketing = findViewById(R.id.checkboxComunicacaoMarketing);
        checkboxDesignArtesArquitetura = findViewById(R.id.checkboxDesignArtesArquitetura);
        checkboxDesenvolvimentoSocial = findViewById(R.id.checkboxDesenvolvimentoSocial);
        checkboxEducacao = findViewById(R.id.checkboxEducacao);
        checkboxGastronomiaAlimentacao = findViewById(R.id.checkboxGastronomiaAlimentacao);
        checkboxGestaoNegocios = findViewById(R.id.checkboxGestaoNegocios);
        checkboxIdiomas = findViewById(R.id.checkboxIdiomas);
        checkboxMeioAmbienteSeguranca = findViewById(R.id.checkboxMeioAmbienteSeguranca);
        checkboxModa = findViewById(R.id.checkboxModa);
        checkboxSaude = findViewById(R.id.checkboxSaude);
        checkboxTecnologiaInformacao = findViewById(R.id.checkboxTecnologiaInformacao);
        checkboxTurismoHospitalidade = findViewById(R.id.checkboxTurismoHospitalidade);

        buttonSelecionarData = findViewById(R.id.buttonSelecionarData);
        buttonSelecionarHora = findViewById(R.id.buttonSelecionarHora);
        buttonSalvar = findViewById(R.id.buttonSalvar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Atualizando evento...");

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        buttonSelecionarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateEventActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                updateDate();
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        buttonSelecionarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateEventActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                updateTime();
                            }
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true);
                timePickerDialog.show();
            }
        });

        // Recuperando referência para o EditText do ID do evento
        editTextIdEvento = findViewById(R.id.editTextIdEvento);

        // Recuperando o ID do evento da Intent
        String eventoId = getIntent().getStringExtra("evento_id");

        // Verificando se o ID do evento foi passado
        if (eventoId != null) {
            // Preenchendo o campo com o ID do evento
            editTextIdEvento.setText(eventoId);
        }

        // Recuperando os extras da Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Preenchendo os campos com as informações do evento
            editTextCodigo.setText(extras.getString("codigo_oferta"));
            editTextNomeEvento.setText(extras.getString("nome_evento"));
            editTextTipo.setText(extras.getString("tipo"));
            editTextDesc.setText(extras.getString("descricao"));
            editTextCategoria.setText(extras.getString("categoria"));
            editTextPublico.setText(extras.getString("publico_alvo"));
            editTextLocal.setText(extras.getString("local"));
            // Preencha os outros campos conforme necessário
        }

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarEvento();
            }
        });
    }

    private void updateDate() {
        buttonSelecionarData.setText(dateFormat.format(calendar.getTime()));
    }

    private void updateTime() {
        buttonSelecionarHora.setText(timeFormat.format(calendar.getTime()));
    }

    private void atualizarEvento() {
        progressDialog.show();

        String url = "https://jllm8c-4000.csb.app/atualizarEvento";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("codigo_oferta", editTextCodigo.getText().toString());
            jsonObject.put("nome_evento", editTextNomeEvento.getText().toString());
            jsonObject.put("tipo", editTextTipo.getText().toString());
            jsonObject.put("descricao", editTextDesc.getText().toString());
            jsonObject.put("categoria", editTextCategoria.getText().toString());
            jsonObject.put("publico_alvo", editTextPublico.getText().toString());
            jsonObject.put("local", editTextLocal.getText().toString());
            jsonObject.put("beleza_estetica", checkBoxBelezaEstetica.isChecked());
            jsonObject.put("bem_estar", checkboxBemEstar.isChecked());
            jsonObject.put("comunicacao_marketing", checkboxComunicacaoMarketing.isChecked());
            jsonObject.put("design_artes_arquitetura", checkboxDesignArtesArquitetura.isChecked());
            jsonObject.put("desenvolvimento_social", checkboxDesenvolvimentoSocial.isChecked());
            jsonObject.put("educacao", checkboxEducacao.isChecked());
            jsonObject.put("gastronomia_alimentacao", checkboxGastronomiaAlimentacao.isChecked());
            jsonObject.put("gestao_negocios", checkboxGestaoNegocios.isChecked());
            jsonObject.put("idiomas", checkboxIdiomas.isChecked());
            jsonObject.put("meio_ambiente_seguranca", checkboxMeioAmbienteSeguranca.isChecked());
            jsonObject.put("moda", checkboxModa.isChecked());
            jsonObject.put("saude", checkboxSaude.isChecked());
            jsonObject.put("tecnologia_informacao", checkboxTecnologiaInformacao.isChecked());
            jsonObject.put("turismo_hospitalidade", checkboxTurismoHospitalidade.isChecked());
            jsonObject.put("data_selecionada", dateFormat.format(calendar.getTime()));
            jsonObject.put("hora_selecionada", timeFormat.format(calendar.getTime()));
            jsonObject.put("id", editTextIdEvento.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
            progressDialog.dismiss();
            return;
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        try {
                            String message = response.getString("message");
                            Toast.makeText(UpdateEventActivity.this, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UpdateEventActivity.this, "Erro ao atualizar evento", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(UpdateEventActivity.this, "Erro ao atualizar evento: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    public void mudarTela(View view) {
        int id = view.getId();
        Intent intent;

        switch (id) {
            case R.id.imageViewVoltar2:
                intent = new Intent(this, MeusEventos.class);
                startActivity(intent);
                break;
        }
    }
}
