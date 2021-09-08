package com.example.wordsgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

public class update_medium extends AppCompatActivity {
    EditText updateMediumTV;
    Button updateMediumbtn;
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medium);
        updateMediumTV = findViewById(R.id.MediumUpEt);
        updateMediumbtn = findViewById(R.id.MediumUpdatebtn);

        Intent intent = getIntent();
        MediumFetchModel mediumFetchModel = (MediumFetchModel)intent.getSerializableExtra("data");

        updateMediumTV.setText(mediumFetchModel.getMediumWord());
        id = mediumFetchModel.getId();
        updateMediumbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updaterecord();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(update_medium.this, fetch_medium.class);
                        startActivity(intent);
                        finish();
                    }
                },3000);

            }
        });
    }
    public  void updaterecord(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://nazysh.000webhostapp.com/updateMedium.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(update_medium.this,response.trim(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(update_medium.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("id",id);
                map.put("Mword", updateMediumTV.getText().toString());

                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(update_medium.this);
        requestQueue.add(stringRequest);

    }
}