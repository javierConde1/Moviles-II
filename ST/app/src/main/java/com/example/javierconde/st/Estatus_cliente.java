package com.example.javierconde.st;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Estatus_cliente extends AppCompatActivity {
    private TextView txtPrueba;
    private Intent inMostrar;
    private Intent inSalir;
    private String url = "http://javier-conde101.hostingerapp.com/Login2.php";
          //  "https://javier-conde101.000webhostapp.com/Login.php";
    private RecyclerView mList;
    private DividerItemDecoration dividerItemDecoration;
    private LinearLayoutManager linearLayoutManager;
    private List<Listado_cliente> clientesList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatus_cliente);
        mList = findViewById(R.id.main_list);
        clientesList = new ArrayList<>();
        adapter = new Listado_adapter(getApplicationContext(), clientesList, new Listado_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Listado_cliente list, int position) {
               Intent intent = new Intent(Estatus_cliente.this, Info_recycler_cliente.class);
               intent.putExtra("orden",list.getNo_orden());
               intent.putExtra("estado",list.getEstado());
               intent.putExtra("fecha",list.getFecha());
               intent.putExtra("prioridad",list.getPrioridad());
                intent.putExtra("fechaprog",list.getFechaProg());
                intent.putExtra("problema",list.getProblema());
                intent.putExtra("tecnico",list.getTecnico());
                intent.putExtra("sDesc",list.getsDesc());

               startActivity(intent);
               finish();
            }
        });
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(),
                linearLayoutManager.getOrientation());
        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);
        getData();

    }

    private void getData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando ...");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i) ;
                                Listado_cliente listado_cliente = new Listado_cliente();
                                listado_cliente.setNo_orden(jsonObject.getInt("orden"));
                                listado_cliente.setEstado(jsonObject.getString("estado"));
                                listado_cliente.setFecha(jsonObject.getString("fecha"));
                                listado_cliente.setPrioridad(jsonObject.getString("prioridad"));
                                listado_cliente.setDomicilio(jsonObject.getString("domicilio"));
                                listado_cliente.setFechaProg(jsonObject.getString("fechaprog"));
                                listado_cliente.setProblema(jsonObject.getString("problema"));
                                listado_cliente.setTecnico(jsonObject.getString("idtec"));
                                listado_cliente.setsDesc(jsonObject.getString("sdesc"));
                                clientesList.add(listado_cliente);
                            }
                            catch (JSONException e){
                                e.printStackTrace();
                                progressDialog.dismiss();
                            }
                        }adapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", error.toString());
                        progressDialog.dismiss();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public void Salir(View view) {
        finish();
    }


}