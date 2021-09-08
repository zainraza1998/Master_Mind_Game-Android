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

public class medium_level extends AppCompatActivity {
    EditText MediumET;
    Button AddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_level);
        MediumET = findViewById(R.id.MediumWord);
        AddButton = findViewById(R.id.MediumAddbtn);


        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word =  MediumET.getText().toString();
                if (word.equals("")){
                    MediumET.setError("Required");
                }
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://nazysh.000webhostapp.com/insertMedium.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(medium_level.this,response.trim(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(medium_level.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map map = new HashMap();
                        map.put("mediumWord",word);
                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(medium_level.this);
                requestQueue.add(stringRequest);


            }
        });
    }
}