package com.example.wordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    TextView txtsignup;
    EditText ed_email,ed_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        txtsignup = findViewById(R.id.txtsignup);

        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, registration.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void Login(View view) {
        if(ed_email.equals("admin@gmail.com") && ed_password.equals("12345"))
        {

            Intent intent = new Intent(login.this, admindaishboard.class);
            startActivity(intent);
            return;
        }
        if(ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Login Click", Toast.LENGTH_SHORT).show();
            moveToRegistration();
        }
    }

    public void moveToRegistration() {
        startActivity(new Intent(getApplicationContext(),menu.class));
        finish();
    }
}