package br.edu.fecap.projetofecap;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistroColaboradores extends AppCompatActivity {

    private EditText editTextNome, editTextEmail, editTextSenha, editTextCargo;
    private Button buttonRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_colaboradores);

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextSenha);
        editTextCargo = findViewById(R.id.editTextCargo);
        buttonRegistrar = findViewById(R.id.buttonRegistrar);

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarColaborador();
            }
        });
    }

    private void registrarColaborador() {
        String nome = editTextNome.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();
        String cargo = editTextCargo.getText().toString().trim();

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("nome", nome);
            requestBody.put("email", email);
            requestBody.put("senha", senha);
            requestBody.put("cargo", cargo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "https://jllm8c-4000.csb.app/registro-colaboradores";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int id = response.getInt("id");
                            Log.d("RegistroColaboradores", "Colaborador registrado com sucesso. ID: " + id);
                            exibirMensagem("Colaborador registrado com sucesso");
                            limparCampos(); // Limpar campos após o registro bem-sucedido
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("RegistroColaboradores", "Erro ao registrar colaborador: " + error.toString());
                        exibirMensagem("Erro ao registrar colaborador");
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void limparCampos() {
        editTextNome.setText("");
        editTextEmail.setText("");
        editTextSenha.setText("");
        editTextCargo.setText("");
    }

    private void exibirMensagem(String mensagem) {
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
    }
}
