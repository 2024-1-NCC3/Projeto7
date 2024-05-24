package br.edu.fecap.projetofecap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.view.LayoutInflater;

import android.widget.EditText;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Perfil extends AppCompatActivity {

    private TextView textViewNomeColab;
    private TextView textViewEmailColab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Inicializar os TextViews
        textViewNomeColab = findViewById(R.id.textViewNomeColab);
        textViewEmailColab = findViewById(R.id.textViewEmailColab);

        // Exibir o nome e o email do colaborador
        exibirInformacoesColaborador();

        // Encontrar o ConstraintLayout pelo identificador
        View constraintLayoutEditarSenha = findViewById(R.id.constraintLayoutEditarSenha);

        // Definir um listener de clique para o ConstraintLayout
        constraintLayoutEditarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDialogEditarSenha();
            }
        });
    }

    private void abrirDialogEditarSenha() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_editar_senha, null);
        builder.setView(dialogView);

        EditText editTextSenhaAtual = dialogView.findViewById(R.id.editTextSenhaAtual);
        EditText editTextNovaSenha = dialogView.findViewById(R.id.editTextNovaSenha);
        EditText editTextConfirmarSenha = dialogView.findViewById(R.id.editTextConfirmarSenha);

        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String senhaAtual = editTextSenhaAtual.getText().toString().trim();
                String novaSenha = editTextNovaSenha.getText().toString().trim();
                String confirmarSenha = editTextConfirmarSenha.getText().toString().trim();

                // Validar se os campos estão preenchidos e se a nova senha e a confirmação são iguais
                if (senhaAtual.isEmpty() || novaSenha.isEmpty() || confirmarSenha.isEmpty()) {
                    Toast.makeText(Perfil.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!novaSenha.equals(confirmarSenha)) {
                    Toast.makeText(Perfil.this, "A nova senha e a confirmação não correspondem.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Enviar solicitação Volley para o servidor
                editarSenha(senhaAtual, novaSenha);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // Método para exibir o nome e o email do colaborador
    private void exibirInformacoesColaborador() {
        // Obter o nome e o email do colaborador da SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String nomeColaborador = sharedPreferences.getString("nomeColaborador", "");
        String emailColaborador = sharedPreferences.getString("emailColaborador", "");

        // Exibir o nome e o email do colaborador nos TextViews correspondentes
        textViewNomeColab.setText(nomeColaborador);
        textViewEmailColab.setText(emailColaborador);
    }


    public void mudarTela(View view) {
        int id = view.getId();
        Intent intent;

        switch (id) {
            case R.id.imageViewVoltar:
                intent = new Intent(this, Home.class);
                startActivity(intent);
                break;
        }
    }

    private void editarSenha(String senhaAtual, String novaSenha) {
        // Recuperar o token JWT de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String authToken = sharedPreferences.getString("token", "");

        // URL do endpoint de atualização de senha
        String url = "https://jllm8c-4000.csb.app/atualizarSenha";

        // Construir o corpo da solicitação em formato JSON
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("senhaAtual", senhaAtual);
            jsonBody.put("novaSenha", novaSenha);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Enviar a solicitação Volley
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Tratar a resposta do servidor
                        Toast.makeText(Perfil.this, "Senha atualizada com sucesso.", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Tratar erros de comunicação com o servidor
                        Toast.makeText(Perfil.this, "Erro ao atualizar a senha. Por favor, tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // Adicionar o token de autenticação ao cabeçalho da solicitação
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + authToken); // Adicione o token JWT recuperado
                return headers;
            }
        };

        // Adicionar a solicitação à fila de solicitações Volley
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
    // Método para mudar de tela conforme o ícone clicado
    public void mudarTela3(View view) {
        int id = view.getId();
        Intent intent;

        switch (id) {
            case R.id.imageViewHome:
                intent = new Intent(this, Home.class);
                startActivity(intent);
                break;
            case R.id.imageViewNovoEvento:
                intent = new Intent(this, NovoEvento.class);
                startActivity(intent);
                break;
            case R.id.imageViewMeusEventos:
                intent = new Intent(this, MeusEventos.class);
                startActivity(intent);
                break;
        }
    }

}