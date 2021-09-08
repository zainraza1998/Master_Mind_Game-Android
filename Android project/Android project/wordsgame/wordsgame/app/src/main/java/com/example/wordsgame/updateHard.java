package com.example.wordsgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
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

public class updateHard extends AppCompatActivity {
    EditText updateET;
    Button buttonupdate;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hard);
        updateET = findViewById(R.id.updateHardid);
        buttonupdate = findViewById(R.id.updateHard);

        Intent intent = getIntent();
        HardDataModel hardDataModel = (HardDataModel) intent.getSerializableExtra("data");

        updateET.setText(hardDataModel.getHardWord());
        id = hardDataModel.getId();

        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updaterecord();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(updateHard.this, fetch_hard.class);
                        startActivity(intent);
                        finish();
                    }
                },3000);

            }
        });
    }
    public  void updaterecord(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://nazysh.000webhostapp.com/updateHard.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(updateHard.this,response.trim(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(updateHard.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("id",id);
                map.put("Mword", updateET.getText().toString());

                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(updateHard.this);
        requestQueue.add(stringRequest);

    }
}