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

public class Estatus_tecnico extends AppCompatActivity {
    TextView txtPrueba;
    Intent inMostrar;
    Intent inSalir;
    private String url = "http://javier-conde101.hostingerapp.com/Login.php";
    //  "https://javier-conde101.000webhostapp.com/Login.php";
    private RecyclerView mList;
    private DividerItemDecoration dividerItemDecoration;
    private LinearLayoutManager linearLayoutManager;
    private List<Listado_tecnico> cultivoList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatus_cliente);
        mList = findViewById(R.id.main_list);
        cultivoList = new ArrayList<>();
        adapter = new Listado_tecnico_adapter(getApplicationContext(), cultivoList, new Listado_tecnico_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Listado_tecnico list, int position) {
                Intent intent = new Intent(Estatus_tecnico.this, Info_recycler_tecnico.class);
                intent.putExtra("orden",list.getNo_ordenTec());
                intent.putExtra("estado",list.getEstadoTec());
                intent.putExtra("fecha",list.getFechaTec());
                intent.putExtra("prioridad",list.getPrioridadTec());
                intent.putExtra("domicilio", list.getDomicilioTec());
                intent.putExtra("fechaprog",list.getsFechaprog());
                intent.putExtra("problema", list.getsProblema());
                intent.putExtra("sdec", list.getsDesc());
                intent.putExtra("cliente",list.getNo_cleinte());
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

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando ...");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Listado_tecnico listado_cliente = new Listado_tecnico();
                                listado_cliente.setNo_ordenTec(jsonObject.getString("orden"));
                                listado_cliente.setEstadoTec(jsonObject.getString("estado"));
                                listado_cliente.setFechaTec(jsonObject.getString("fecha"));
                                listado_cliente.setPrioridadTec(jsonObject.getString("prioridad"));
                                listado_cliente.setDomicilioTec(jsonObject.getString("domicilio"));
                                listado_cliente.setsFechaprog(jsonObject.getString("fechaprog"));
                                listado_cliente.setsProblema(jsonObject.getString("problema"));
                                listado_cliente.setsDesc(jsonObject.getString("sdesc"));
                                listado_cliente.setNo_cleinte(jsonObject.getString("idcliente"));

                                cultivoList.add(listado_cliente);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                            }
                        }
                        adapter.notifyDataSetChanged();
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