package com.example.wordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class options extends AppCompatActivity {
    Button easy, medium, hard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        easy = findViewById(R.id.easy);
        medium = findViewById(R.id.medium);
        hard = findViewById(R.id.hard);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(options.this, Main_conent.class);
                intent.putExtra("key" , 0);
                startActivity(intent);
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(options.this, Main_conent.class);
                intent.putExtra("key" , 1);
                startActivity(intent);
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(options.this, Main_conent.class);
                intent.putExtra("key" , 2);
                startActivity(intent);
            }
        });
    }

}