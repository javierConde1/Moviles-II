package com.example.javierconde.st;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class Solicitar_cliente extends AppCompatActivity {
    private CheckBox cbx1, cbx2, cbx3, cbx4, cbx5, cbx6, cbx7;
    private EditText et_desc;
    private Button btnSalir_cliente;
    private RadioButton rbtnAlta, rbtnMedia, rbtnBaja;
    Intent inEstatus;

    private String sDesc = "";
    private String sPrioridad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_cliente);

        cbx1 = findViewById(R.id.cbx1);
        cbx2 = findViewById(R.id.cbx2);
        cbx3 = findViewById(R.id.cbx3);
        cbx4 = findViewById(R.id.cbx4);
        cbx5 = findViewById(R.id.cbx5);
        cbx6 = findViewById(R.id.cbx6);
        cbx7 = findViewById(R.id.cbx7);
        et_desc = findViewById(R.id.et_desc);
        btnSalir_cliente = findViewById(R.id.btnSalir_cliente);
        rbtnAlta = findViewById(R.id.rbtnAlta);
        rbtnMedia = findViewById(R.id.rbtnMedia);
        rbtnBaja = findViewById(R.id.rbtnBaja);

        inEstatus = new Intent(this, Menu_cliente.class);

        btnSalir_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void solicitar(View view) {

        //sDesc += et_desc.getText();

        if(cbx1.isChecked() || cbx2.isChecked() || cbx3.isChecked() || cbx4.isChecked() || cbx5.isChecked() || cbx6.isChecked() || cbx7.isChecked()){
            sDesc += ". Fallos en: ";
        }

        if(cbx1.isChecked()){
            sDesc += "- Panel ";
        }
        if(cbx2.isChecked()){
            sDesc += "- Bateria ";
        }
        if(cbx3.isChecked()){
            sDesc += "- Contra chapa ";
        }
        if(cbx4.isChecked()){
            sDesc += "- Cerca electrica ";
        }
        if(cbx5.isChecked()){
            sDesc += "- Cerebro ";
        }
        if(cbx6.isChecked()){
            sDesc += "- Cableado ";
        }
        if(cbx7.isChecked()){
            sDesc += "- Sensores ";
        }

        if(!rbtnAlta.isChecked() && !rbtnMedia.isChecked() && !rbtnBaja.isChecked()){
            Toast.makeText(Solicitar_cliente.this, "Seleccione un nivel de prioridad", Toast.LENGTH_SHORT).show();
        }else{
            if(rbtnAlta.isChecked()){
               sPrioridad = "alta";
            }else if(rbtnMedia.isChecked()){
                sPrioridad = "media";
            }else if(rbtnBaja.isChecked()){
                sPrioridad = "baja";
            }
            Toast.makeText(Solicitar_cliente.this, "REPORTE REALIZADO CON EXITO", Toast.LENGTH_LONG).show();
           // startActivity(inEstatus);

            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);

            final String sEstado= "pendiente";
            final Date dFecha = c.getTime();
            final String sPri = sPrioridad;
            final String sProblem = sDesc;
            final String sDescripcion = et_desc.getText().toString();

            Response.Listener<String> respoListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if(success){
                            //Intent intent = new Intent(Solicitar_cliente.this, Menu_cliente.class);
                           // Solicitar_cliente.this.startActivity(intent);
                            finish();
                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(Solicitar_cliente.this);
                            builder.setMessage("Error en insertar solicitud").setNegativeButton("Retry",null)
                                    .create().show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            OrdenRequest ordenRequest = new OrdenRequest(sEstado, dFecha, sPri, sProblem, sDescripcion,respoListener);
            RequestQueue queue = Volley.newRequestQueue(Solicitar_cliente.this);
            queue.add(ordenRequest);
            finish();

        }


        //EN LA VARIABLE sDesc ESTA GUARDADO LO QUE SE PONDRA EN EL REGISTRO DESCRIPCION
        //EN LA VARIABLE sPrioridad ESTA GUARDADO LO QUE SE PONDRA EN EL REGISTRO PRIORIDAD

    }

}
