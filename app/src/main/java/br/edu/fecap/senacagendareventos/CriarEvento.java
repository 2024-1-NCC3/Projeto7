package br.edu.fecap.senacagendareventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class CriarEvento extends AppCompatActivity implements EventManager.EventCreationListener {

    private ImageView imageView;
    private EditText editTextCodigoOferta, editTextCategoria, editTextPublicoAlvo, editTextLocal,
            editTextNomeEvento, editTextData, editTextTipo, editTextDescricao;
    private CheckBox checkboxBelezaEstetica, checkboxBemEstar, checkboxComunicacaoMarketing,
            checkboxDesignArtesArquitetura, checkboxDesenvolvimentoSocial, checkboxEducacao,
            checkboxGastronomiaAlimentacao, checkboxGestaoNegocios, checkboxIdiomas,
            checkboxMeioAmbienteSeguranca, checkboxModa, checkboxSaude, checkboxTecnologiaInformacao,
            checkboxTurismoHospitalidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_evento);

        imageView = findViewById(R.id.imageView4);
        editTextCodigoOferta = findViewById(R.id.textinputCodigo);
        editTextCategoria = findViewById(R.id.textinputCategoria);
        editTextPublicoAlvo = findViewById(R.id.textinputPublico);
        editTextLocal = findViewById(R.id.textinputLocal);

        editTextNomeEvento = findViewById(R.id.textinputEvento);
        editTextData = findViewById(R.id.textinputData);
        editTextTipo = findViewById(R.id.textinputTipo);
        editTextDescricao = findViewById(R.id.textinputDesc);

        checkboxBelezaEstetica = findViewById(R.id.checkBox1);
        checkboxBemEstar = findViewById(R.id.checkBox2);
        checkboxComunicacaoMarketing = findViewById(R.id.checkBox3);
        checkboxDesignArtesArquitetura = findViewById(R.id.checkBox4);
        checkboxDesenvolvimentoSocial = findViewById(R.id.checkBox5);
        checkboxEducacao = findViewById(R.id.checkBox6);
        checkboxGastronomiaAlimentacao = findViewById(R.id.checkBox7);
        checkboxGestaoNegocios = findViewById(R.id.checkBox8);
        checkboxIdiomas = findViewById(R.id.checkBox9);
        checkboxMeioAmbienteSeguranca = findViewById(R.id.checkBox10);
        checkboxModa = findViewById(R.id.checkBox11);
        checkboxSaude = findViewById(R.id.checkBox12);
        checkboxTecnologiaInformacao = findViewById(R.id.checkBox13);
        checkboxTurismoHospitalidade = findViewById(R.id.checkBox14);

        // Referenciar o TextInputLayout no layout XML
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayoutCodigo);

        // Definir o ícone para o TextInputLayout
        textInputLayout.setStartIconDrawable(R.drawable.baseline_person_24);

        // Definir o estilo para outlined
        textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);


        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        imageView.animate().scaleX(0.9f).scaleY(0.9f).setDuration(200).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        imageView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).start();
                        break;
                }
                return false;
            }
        });
    }

    public void onClickEnviar(View view) {
        if (camposEstaoPreenchidos()) {
            String codigoOferta = editTextCodigoOferta.getText().toString();
            String nomeEvento = editTextNomeEvento.getText().toString();
            String data = editTextData.getText().toString();
            String tipo = editTextTipo.getText().toString();
            String descricao = editTextDescricao.getText().toString();
            String categoria = editTextCategoria.getText().toString();
            String publicoAlvo = editTextPublicoAlvo.getText().toString();
            String local = editTextLocal.getText().toString();

            boolean belezaEstetica = checkboxBelezaEstetica.isChecked();
            boolean bemEstar = checkboxBemEstar.isChecked();
            boolean comunicacaoMarketing = checkboxComunicacaoMarketing.isChecked();
            boolean designArtesArquitetura = checkboxDesignArtesArquitetura.isChecked();
            boolean desenvolvimentoSocial = checkboxDesenvolvimentoSocial.isChecked();
            boolean educacao = checkboxEducacao.isChecked();
            boolean gastronomiaAlimentacao = checkboxGastronomiaAlimentacao.isChecked();
            boolean gestaoNegocios = checkboxGestaoNegocios.isChecked();
            boolean idiomas = checkboxIdiomas.isChecked();
            boolean meioAmbienteSeguranca = checkboxMeioAmbienteSeguranca.isChecked();
            boolean moda = checkboxModa.isChecked();
            boolean saude = checkboxSaude.isChecked();
            boolean tecnologiaInformacao = checkboxTecnologiaInformacao.isChecked();
            boolean turismoHospitalidade = checkboxTurismoHospitalidade.isChecked();

            EventManager.criarEvento(this, codigoOferta, nomeEvento, data, tipo, descricao, categoria, publicoAlvo, local,
                    belezaEstetica, bemEstar, comunicacaoMarketing, designArtesArquitetura, desenvolvimentoSocial,
                    educacao, gastronomiaAlimentacao, gestaoNegocios, idiomas, meioAmbienteSeguranca, moda,
                    saude, tecnologiaInformacao, turismoHospitalidade, this);

        } else {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean camposEstaoPreenchidos() {
        if (editTextCodigoOferta.getText().toString().isEmpty() ||
                editTextNomeEvento.getText().toString().isEmpty() ||
                editTextData.getText().toString().isEmpty() ||
                editTextTipo.getText().toString().isEmpty() ||
                editTextDescricao.getText().toString().isEmpty() ||
                editTextCategoria.getText().toString().isEmpty() ||
                editTextPublicoAlvo.getText().toString().isEmpty() ||
                editTextLocal.getText().toString().isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public void onEventCreated() {
        Toast.makeText(this, "Evento criado com sucesso!", Toast.LENGTH_SHORT).show();
        limparCampos();

    }

    @Override
    public void onEventCreationFailed(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
    private void limparCampos() {
        editTextCodigoOferta.getText().clear();
        editTextNomeEvento.getText().clear();
        editTextData.getText().clear();
        editTextTipo.getText().clear();
        editTextDescricao.getText().clear();
        editTextCategoria.getText().clear();
        editTextPublicoAlvo.getText().clear();
        editTextLocal.getText().clear();

        checkboxBelezaEstetica.setChecked(false);
        checkboxBemEstar.setChecked(false);
        checkboxComunicacaoMarketing.setChecked(false);
        checkboxDesignArtesArquitetura.setChecked(false);
        checkboxDesenvolvimentoSocial.setChecked(false);
        checkboxEducacao.setChecked(false);
        checkboxGastronomiaAlimentacao.setChecked(false);
        checkboxGestaoNegocios.setChecked(false);
        checkboxIdiomas.setChecked(false);
        checkboxMeioAmbienteSeguranca.setChecked(false);
        checkboxModa.setChecked(false);
        checkboxSaude.setChecked(false);
        checkboxTecnologiaInformacao.setChecked(false);
        checkboxTurismoHospitalidade.setChecked(false);
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
