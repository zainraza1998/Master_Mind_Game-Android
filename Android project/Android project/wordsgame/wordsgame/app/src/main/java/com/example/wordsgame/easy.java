package com.example.wordsgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class easy extends AppCompatActivity {
    EditText easyword;
    Button Insertbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);
        easyword = findViewById(R.id.Et_easywords);
        Insertbtn = findViewById(R.id.b_save_easy);

        Insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word =  easyword.getText().toString();
                if (word.equals("")){
                    easyword.setError("Required");
                }
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://nazysh.000webhostapp.com/InsertEasy.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(easy.this,response.trim(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(easy.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map map = new HashMap();
                        map.put("Easyword",word);
                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(easy.this);
                requestQueue.add(stringRequest);


            }
        });
    }
}