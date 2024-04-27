package br.edu.fecap.senacagendareventos;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutSenha;
    private ImageView imageViewAcessar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutSenha = findViewById(R.id.textInputLayoutSenha);
        imageViewAcessar = findViewById(R.id.imageViewAcessar);
        ImageView btncolaborador = findViewById(R.id.imageViewColaborador);
        textView = findViewById(R.id.textViewConvidado);

        // Referenciar o TextInputLayout no layout XML
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayoutEmail);

        // Definir o ícone para o TextInputLayout
        textInputLayout.setStartIconDrawable(R.drawable.baseline_email_24);

        // Definir o estilo para outlined
        textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);

        // Referenciar o TextInputLayout no layout XML
        TextInputLayout  textInputLayout2 = findViewById(R.id.textInputLayoutSenha);

        // Definir o ícone para o TextInputLayout
        textInputLayout2.setStartIconDrawable(R.drawable.baseline_lock_24);

        // Definir o estilo para outlined
        textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);

        btncolaborador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tornar os TextInputLayouts visíveis
                textInputLayoutEmail.setVisibility(View.VISIBLE);
                textInputLayoutSenha.setVisibility(View.VISIBLE);

                // Tornar o ImageView2 visível
                imageViewAcessar.setVisibility(View.VISIBLE);

                // Esconder o TextView
                textView.setVisibility(View.GONE);
            }
        });

        // Adiciona um OnClickListener para o imageView2
        imageViewAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textInputLayoutEmail.getEditText().getText().toString().trim();
                String senha = textInputLayoutSenha.getEditText().getText().toString().trim();

                if (email.isEmpty() || senha.isEmpty()) {
                    // Exibir Toast informando que os campos não estão preenchidos
                    Toast.makeText(Login.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Realizar login
                    realizarLogin(email, senha);
                }
            }
        });

        // Adiciona um TextWatcher para o campo de email
        textInputLayoutEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // Verifica se ambos os campos de email e senha estão preenchidos e, em seguida, habilita ou desabilita o ImageView
                imageViewAcessar.setEnabled(!s.toString().trim().isEmpty() && !textInputLayoutSenha.getEditText().getText().toString().trim().isEmpty());
            }
        });

        // Adiciona um TextWatcher para o campo de senha
        textInputLayoutSenha.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // Verifica se ambos os campos de email e senha estão preenchidos e, em seguida, habilita ou desabilita o ImageView
                imageViewAcessar.setEnabled(!s.toString().trim().isEmpty() && !textInputLayoutEmail.getEditText().getText().toString().trim().isEmpty());
            }
        });
    }

    private void realizarLogin(String email, String senha) {
        String url = "https://64qzhc-3000.csb.app/login";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", email);
            jsonBody.put("senha", senha);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Trate a resposta do servidor aqui
                try {
                    String message = response.getString("message");
                    if (message.equals("Login bem-sucedido")) {
                        // Redirecionar para a classe HomeColaborador
                        Intent intent = new Intent(Login.this, HomeColaborador.class);
                        startActivity(intent);
                    } else {
                        // Exibir mensagem de erro
                        Toast.makeText(Login.this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Login.this, "Erro ao fazer login", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Exibir mensagem de erro
                Toast.makeText(Login.this, "Erro ao fazer login", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(request);
    }
}
