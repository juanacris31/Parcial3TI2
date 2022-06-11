package com.example.parcial3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
   private  TextView  txtRes;
   private RequestQueue mQueue;
    TextView txtUser, txtId;
    Button btnEnviar, btnEnviar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUser = findViewById(R.id.txtUser);
        txtId = findViewById(R.id.txtId);
        btnEnviar = findViewById(R.id.btnEnviar);

        txtRes = findViewById(R.id.txtRes);
        Button btnEnviar2 = findViewById(R.id.btnEnviar2);
        mQueue = Volley.newRequestQueue(this);
        btnEnviar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }

            private void jsonParse() {
                String url = "https://graph.instagram.com/me/media?fields=id,caption&access_token=IGQVJXdWVtaTlzcTFmeC1FUGZACTHJXLW4wQU9ab3VhMWRJbWxnd0JPZA3lhaUwybGZATMnRZAWkc0SHJEVE9TSGY0T1J1dmxRT19aZAm9fZA3YzeUhWRzZAWUC1GYVlmTXRNQzZAvUFNpVkdQNEg3VTBIXzRoVwZDZD";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                        JSONArray dataArray = response.getJSONArray("data");
                        for (int i = 0; i<dataArray.length();i++){
                            JSONObject data = dataArray.getJSONObject(i);
                            String id = data.getString("id");
                            String caption = data.getString("caption");
                            txtRes.append(id+","+ caption + "\n\n");

                        }
                    }catch (JSONException e){
                            e.printStackTrace();;
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                   error.printStackTrace();
                    }
                });
                mQueue.add(request);

            }
        });
btnEnviar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Leer();
    }

});
    }
    private  void  Leer(){
        String url= "https://graph.instagram.com/me?fields=id,username&access_token=IGQVJXdWVtaTlzcTFmeC1FUGZACTHJXLW4wQU9ab3VhMWRJbWxnd0JPZA3lhaUwybGZATMnRZAWkc0SHJEVE9TSGY0T1J1dmxRT19aZAm9fZA3YzeUhWRzZAWUC1GYVlmTXRNQzZAvUFNpVkdQNEg3VTBIXzRoVwZDZD";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                JSONObject jsonObject= new JSONObject(response);

              txtId.setText(jsonObject.getString("id"));
              txtUser.setText(jsonObject.getString("username"));

                }catch (JSONException e){
e.printStackTrace();
                }}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(postRequest);
    }
    /*
    private  void  enviar() {
        String url = "https://graph.instagram.com/me/media?fields=id,caption&access_token=IGQVJXdWVtaTlzcTFmeC1FUGZACTHJXLW4wQU9ab3VhMWRJbWxnd0JPZA3lhaUwybGZATMnRZAWkc0SHJEVE9TSGY0T1J1dmxRT19aZAm9fZA3YzeUhWRzZAWUC1GYVlmTXRNQzZAvUFNpVkdQNEg3VTBIXzRoVwZDZD";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(MainActivity.this, "RESULTADO= " + response, Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(postRequest);
    }*/
}