package com.example.wordsgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class admindaishboard extends AppCompatActivity {
    CardView easyword, mediumword, hardword, score, users, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindaishboard);
        easyword = findViewById(R.id.easywords);
        mediumword = findViewById(R.id.mediumwords);
        hardword = findViewById(R.id.hardwords);
        score = findViewById(R.id.score);
        users = findViewById(R.id.users);
        logout = findViewById(R.id.logout);
   users.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(admindaishboard.this , FetchUsers.class);
        startActivity(intent);

    }
});
        easyword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admindaishboard.this , fetch_easy.class);
                startActivity(intent);
            }
        });
        mediumword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admindaishboard.this ,fetch_medium.class);
                startActivity(intent);
            }
        });
        hardword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admindaishboard.this ,fetch_hard.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(admindaishboard.this);
                builder.setMessage("Ary You sure your want to exit!")
                        .setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                admindaishboard.super.onBackPressed();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        });


    }
    @Override
    public void onBackPressed() {
    }
    }
