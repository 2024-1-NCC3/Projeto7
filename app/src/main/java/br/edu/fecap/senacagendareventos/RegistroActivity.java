package br.edu.fecap.senacagendareventos;

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

public class RegistroActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextSenha;
    private Button buttonRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextSenha);
        buttonRegistrar = findViewById(R.id.buttonRegistrar);

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os dados do formulário
                String email = editTextEmail.getText().toString().trim();
                String senha = editTextSenha.getText().toString().trim();

                // Verificar se os campos estão vazios
                if (email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Enviar os dados para o servidor
                    inserirColaboradorNoServidor(email, senha);
                }
            }
        });
    }

    private void inserirColaboradorNoServidor(String email, String senha) {
        // URL do seu endpoint de inserção de colaboradores
        String url = "https://64qzhc-3000.csb.app/registro";

        // Cria um objeto JSON com os dados do novo colaborador
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("email", email);
            requestBody.put("senha", senha);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Cria uma requisição POST com o objeto JSON como corpo
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Resposta do servidor
                        Toast.makeText(RegistroActivity.this, "Colaborador inserido com sucesso", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Erro na requisição
                        Toast.makeText(RegistroActivity.this, "Erro ao inserir colaborador", Toast.LENGTH_SHORT).show();
                        Log.e("Erro", error.toString());
                    }
                });

        // Adiciona a requisição à fila de requisições do Volley
        Volley.newRequestQueue(this).add(request);
    }
}
