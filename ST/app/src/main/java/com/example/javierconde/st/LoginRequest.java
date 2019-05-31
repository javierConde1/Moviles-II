package com.example.javierconde.st;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL= "http://javier-conde101.hostingerapp.com/sesion.php";
           // "https://javier-conde101.000webhostapp.com/sesion.php";
    private Map<String,String> params;
    public LoginRequest(String username, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL,  listener, null);
        params = new HashMap<>();

        params.put("user", username);
        params.put("pass", password);

    }
    @Override
    public Map<String, String> getParams(){return params;}
}
