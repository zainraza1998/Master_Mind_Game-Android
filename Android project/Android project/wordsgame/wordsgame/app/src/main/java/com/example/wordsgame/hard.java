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

public class hard extends AppCompatActivity {
    EditText hardET;
    Button hardAddbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);
        hardET = findViewById(R.id.Et_hard);
        hardAddbtn = findViewById(R.id.hardbtn);

        hardAddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String words =  hardET.getText().toString();
                if (words.equals("")){
                    hardET.setError("Required");
                }
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://nazysh.000webhostapp.com/insertHard.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(hard.this,response.trim(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(hard.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map map = new HashMap();
                        map.put("mediumWord",words);
                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(hard.this);
                requestQueue.add(stringRequest);

            }
        });

    }
}