package com.example.javierconde.st;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu_cliente extends AppCompatActivity {

    private TextView txtNumCliente, txtNomCliente, txtApePat, txtApeMat;
    private Button btnSalirCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);

        btnSalirCliente = findViewById(R.id.btnSalirCliente);

        txtNumCliente = findViewById(R.id.txtNumCliente);
        txtNomCliente = findViewById(R.id.txtNomCliente);
        txtApePat = findViewById(R.id.txtApCliente);
        txtApeMat = findViewById(R.id.txtAmCliente);

        btnSalirCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_cliente.this, Principal.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        int ident = intent.getIntExtra("ident",-1);
        String name = intent.getStringExtra("name");
        String ap = intent.getStringExtra("ap");
        String am = intent.getStringExtra("am");

        txtNumCliente.setText(ident + "");
        txtNomCliente.setText(name);
        txtApePat.setText(ap);
        txtApeMat.setText(am);


    }
    public void Estatus(View view) {
        Intent inEstatus;
        inEstatus =new Intent(this,Estatus_cliente.class);
        startActivity(inEstatus);

    }

    public void Solicitar(View view) {
        Intent inSolicitar;
        inSolicitar = new Intent(this,Solicitar_cliente.class);
        startActivity(inSolicitar);

    }
}