package com.example.javierconde.st;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ActualizaRequest extends StringRequest {

    private  static final String ACTUALIZA_REQUEST_URL= "http://javier-conde101.hostingerapp.com/update.php";
            //"https://javier-conde101.000webhostapp.com/update.php";
    private Map<String,String> params;
    public ActualizaRequest(String sEstado, Response.Listener<String> listener){
        super(Method.POST, ACTUALIZA_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("estado", sEstado);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
