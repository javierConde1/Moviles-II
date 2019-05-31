package com.example.javierconde.st;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Principal extends AppCompatActivity {
    private EditText TV_usuario, TV_contraseña;
private Button Btn_iniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        getSupportActionBar().hide();

        TV_usuario = findViewById(R.id.TV_usuario);
        TV_contraseña = findViewById(R.id.TV_contraseña);
        Btn_iniciar = findViewById(R.id.Btn_iniciar);

        TV_usuario.setText("");
        TV_contraseña.setText("");


        Btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TV_usuario.getText().toString().isEmpty()){
                    Toast.makeText(Principal.this, "DEBES INGRESAR USUARIO", Toast.LENGTH_SHORT).show();
                }else if(TV_contraseña.getText().toString().isEmpty()){
                    Toast.makeText(Principal.this, "DEBES INGRESAR LA CONTRASEÑA", Toast.LENGTH_SHORT).show();
                }else{
                final String username= TV_usuario.getText().toString();
                final String password= TV_contraseña.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                String privilegios= jsonResponse.getString("privilegios");
                                Intent iPriv;
                                switch (privilegios){
                                    case "monitoreo":
                                        iPriv=new Intent(Principal.this,Menu_monitoreo.class);
                                        startActivity(iPriv);
                                        break;
                                    case "cliente":
                                        iPriv=new Intent(Principal.this,Menu_cliente.class);
                                        startActivity(iPriv);
                                        int identC = jsonResponse.getInt("ident");
                                        String nameC= jsonResponse.getString("name");
                                        String apC = jsonResponse.getString("ap");
                                        String amC = jsonResponse.getString("am");

                                        Intent intentC = new Intent(Principal.this, Menu_cliente.class);

                                        intentC.putExtra("ident", identC);
                                        intentC.putExtra("name", nameC);
                                        intentC.putExtra("ap", apC);
                                        intentC.putExtra("am", amC);

                                        Principal.this.startActivity(intentC);
                                        break;
                                    case "tecnico":
                                        iPriv=new Intent(Principal.this,Menu_tecnico.class);
                                        startActivity(iPriv);
                                        int identT = jsonResponse.getInt("ident");
                                        String nameT= jsonResponse.getString("name");
                                        String apT = jsonResponse.getString("ap");
                                        String amT = jsonResponse.getString("am");

                                        Intent intentT = new Intent(Principal.this, Menu_tecnico.class);

                                        intentT.putExtra("ident", identT);
                                        intentT.putExtra("name", nameT);
                                        intentT.putExtra("ap", apT);
                                        intentT.putExtra("am", amT);

                                        Principal.this.startActivity(intentT);
                                        break;
                                }



                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Principal.this);
                                builder.setMessage("ERROR EN LOGEO")
                                        .setNegativeButton("RETRY", null)
                                        .create().show();
                                TV_usuario.setText("");
                                TV_contraseña.setText("");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest= new LoginRequest(username,password, responseListener);

                RequestQueue queue = Volley.newRequestQueue(Principal.this);
                queue.add(loginRequest);

            }}
        });

    }



}
