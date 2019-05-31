package com.example.javierconde.st;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Actualizar_Solicitud extends AppCompatActivity {
    TextView txtFecha, txtOrden;
    EditText txtDesc;
    private CheckBox cbx1, cbx2, cbx3, cbx4, cbx5, cbx6, cbx7;
    private RadioButton rbtnAlta, rbtnMedia, rbtnBaja;
    private String sDesc = "";
    private String sPrioridad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar__solicitud);
        txtFecha = findViewById(R.id.txtFecha);
        txtOrden = findViewById(R.id.txtNoOrden);
        txtDesc = findViewById(R.id.et_desc);
        cbx1 = findViewById(R.id.cbx1);
        cbx2 = findViewById(R.id.cbx2);
        cbx3 = findViewById(R.id.cbx3);
        cbx4 = findViewById(R.id.cbx4);
        cbx5 = findViewById(R.id.cbx5);
        cbx6 = findViewById(R.id.cbx6);
        cbx7 = findViewById(R.id.cbx7);
        rbtnAlta = findViewById(R.id.rbtnAlta);
        rbtnMedia = findViewById(R.id.rbtnMedia);
        rbtnBaja = findViewById(R.id.rbtnBaja);


        getIncomingIntent();
    }


    //************************************************** Tomar el intento **************************************************
    private void getIncomingIntent() {
        txtOrden.setText(getIntent().getStringExtra("orden"));
        txtFecha.setText(getIntent().getStringExtra("fecha"));
        txtDesc.setText(getIntent().getStringExtra("descripcion"));
        String sPrioridad = getIntent().getStringExtra("prioridad");
        if (sPrioridad.equals("Alta")) {
            rbtnAlta.setChecked(true);
        }
        if (sPrioridad.equals("Media")) {
            rbtnMedia.setChecked(true);
        }
        if (sPrioridad.equals("Baja")) {
            rbtnBaja.setChecked(true);
        }


        String string = getIntent().getStringExtra("problema");
        String[] parts = string.split("-");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals(" Panel ")) {
                cbx1.setChecked(true);
            }
            if (parts[i].equals(" Bateria ")) {
                cbx2.setChecked(true);
            }
            if (parts[i].equals(" Contra chapa ")) {
                cbx3.setChecked(true);
            }
            if (parts[i].equals(" Cerca electrica ")) {
                cbx4.setChecked(true);
            }
            if (parts[i].equals(" Cerebro ")) {
                cbx5.setChecked(true);
            }
            if (parts[i].equals(" Cableado ")) {
                cbx6.setChecked(true);
            }
            if (parts[i].equals(" Sensores ")) {
                cbx7.setChecked(true);
            }


        }
    }

    public void modificarReporte(View view) {



        if (cbx1.isChecked() || cbx2.isChecked() || cbx3.isChecked() || cbx4.isChecked() || cbx5.isChecked() || cbx6.isChecked() || cbx7.isChecked()) {
            sDesc += ". Fallos en: ";
        }

        if (cbx1.isChecked()) {
            sDesc += "- Panel ";
        }
        if (cbx2.isChecked()) {
            sDesc += "- Bateria ";
        }
        if (cbx3.isChecked()) {
            sDesc += "- Contra chapa ";
        }
        if (cbx4.isChecked()) {
            sDesc += "- Cerca electrica ";
        }
        if (cbx5.isChecked()) {
            sDesc += "- Cerebro ";
        }
        if (cbx6.isChecked()) {
            sDesc += "- Cableado ";
        }
        if (cbx7.isChecked()) {
            sDesc += "- Sensores ";
        }

        if (!rbtnAlta.isChecked() && !rbtnMedia.isChecked() && !rbtnBaja.isChecked()) {
            Toast.makeText(Actualizar_Solicitud.this, "Seleccione un nivel de prioridad", Toast.LENGTH_SHORT).show();
        } else {
            if (rbtnAlta.isChecked()) {
                sPrioridad = "alta";
            } else if (rbtnMedia.isChecked()) {
                sPrioridad = "media";
            } else if (rbtnBaja.isChecked()) {
                sPrioridad = "baja";
            }
            Toast.makeText(Actualizar_Solicitud.this, "REPORTE REALIZADO CON EXITO", Toast.LENGTH_LONG).show();
            // startActivity(inEstatus);

            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);

            final String sEstado = "pendiente";
            final Date dFecha = c.getTime();
            final String sPri = sPrioridad;
            final String sProblem = sDesc;
            final String sDescripcion = txtDesc.getText().toString();


            //Construir peticion HTTP
            RequestQueue queue = Volley.newRequestQueue(Actualizar_Solicitud.this);
            String url = "http://javier-conde101.hostingerapp.com/updateCliente.php";

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(Actualizar_Solicitud.this, "Actualizado con exito!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(Actualizar_Solicitud.this, "Error al actualizar", Toast.LENGTH_SHORT).show();
                }
            }) {
                //Preparar datos enviados con POST al servidor
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("orden", (String) txtOrden.getText());
                    map.put("fecha", txtFecha.getText().toString());
                    map.put("sdesc", sDescripcion);
                    map.put("prioridad", sPri);
                    map.put("problema", sProblem);


                    return map;
                }

            };
            queue.add(request);
            //Se realiza la peticion HTTP

        }
    }
}
