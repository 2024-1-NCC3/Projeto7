package br.edu.fecap.projetofecap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar as views
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextSenha = findViewById(R.id.editTextTextPassword);
        buttonLogin = findViewById(R.id.button);

        // Configurar o clique do botão de login
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os valores dos campos de email e senha
                String email = editTextEmail.getText().toString().trim();
                String senha = editTextSenha.getText().toString().trim();

                // Verificar se os campos não estão vazios
                if (email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(Login.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Realizar a solicitação de login
                fazerLogin(email, senha);
            }
        });
    }

    private void fazerLogin(String email, String senha) {
        String url = "https://jllm8c-4000.csb.app/login";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", email);
            jsonBody.put("senha", senha);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Dentro do onResponse após receber a resposta do servidor
                        try {
                            String token = response.getString("token");
                            String nomeEvento = response.getString("nomeEvento");
                            String dataSelecionada = response.getString("dataSelecionada");
                            String horaSelecionada = response.getString("horaSelecionada");
                            int idColaborador = response.getInt("colaborador_id");

                            // Salvar o token, nome, email e ID do colaborador localmente
                            SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("token", token);
                            editor.putString("nome_evento", nomeEvento);
                            editor.putString("data_selecionada", dataSelecionada);
                            editor.putString("hora_selecionada", horaSelecionada);
                            editor.putInt("colaborador_id", idColaborador);
                            editor.apply();

                            // Redirecionar para a tela inicial (home)
                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);
                            finish(); // Encerrar a atividade de login
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this, "Erro ao processar os dados de login.", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Notificar o usuário sobre o erro
                        if (error.networkResponse != null && error.networkResponse.statusCode == 401) {
                            // Erro de autenticação
                            Toast.makeText(Login.this, "Email ou senha incorretos.", Toast.LENGTH_SHORT).show();
                        } else {
                            // Outro tipo de erro (ex: erro de rede)
                            Toast.makeText(Login.this, "Erro de rede ou servidor. Tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // Adicione a solicitação à fila de solicitações Volley
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}
