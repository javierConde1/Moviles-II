package com.example.javierconde.st;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu_tecnico extends AppCompatActivity {
     TextView txtNumTecnico, txtNomTecnico, txtApePat, txtApeMat;
    private Button btnSalirTecnico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tecnico);
        btnSalirTecnico = findViewById(R.id.btnSalirTecnico);

        txtNumTecnico = findViewById(R.id.txtNumTecnico);
        txtNomTecnico = findViewById(R.id.txtNomTecnico);
        txtApePat = findViewById(R.id.txtApePat);
        txtApeMat = findViewById(R.id.txtApeMat);

        btnSalirTecnico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_tecnico.this, Principal.class);
                startActivity(intent);

            }
        });


        Intent intent = getIntent();
        int ident = intent.getIntExtra("ident",-1);
        String name = intent.getStringExtra("name");
        String ap = intent.getStringExtra("ap");
        String am = intent.getStringExtra("am");

        txtNumTecnico.setText(ident + "");
        txtNomTecnico.setText(name);
        txtApePat.setText(ap);
        txtApeMat.setText(am);
    }
    public void Estatus(View view) {
        Intent inEstatus;
        inEstatus =new Intent(this,Estatus_tecnico.class);
        startActivity(inEstatus);

    }


}
