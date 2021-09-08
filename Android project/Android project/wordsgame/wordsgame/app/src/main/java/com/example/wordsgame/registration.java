package com.example.wordsgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class registration extends AppCompatActivity {
    TextView txtsignup;
    EditText ed_username,ed_email,ed_password;
    String str_username,str_email,str_password;
    String url = "https://nazysh.000webhostapp.com/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ed_email = findViewById(R.id.ed_email);
        ed_username = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);
        txtsignup = findViewById(R.id.txtsignup);

        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registration.this, login.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void moveToLogin() {

        startActivity(new Intent(getApplicationContext(), login.class));
        finish();
    }

    public void Register(View view) {

        if(ed_username.getText().toString().equals("")){
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        }
        else if(ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{

            str_username = ed_username.getText().toString();
            str_email = ed_email.getText().toString();

            str_password = ed_password.getText().toString();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(registration.this,  response, Toast.LENGTH_SHORT);
                    moveToLogin( );

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(registration.this, error.getMessage().toString(), Toast.LENGTH_SHORT);

                }
            }

            ){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Uname","");
                    params.put("UserName",str_username);
                    params.put("Uemail",str_email);
                    params.put("Upass",str_password);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(registration.this);

            requestQueue.add(request);

        }
    }
}