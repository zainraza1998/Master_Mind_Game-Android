package com.example.wordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main_conent extends AppCompatActivity {
    TextView tv_info , tv_word, tv_score, timer_tv;
    EditText et_guess;
    Button b_check, b_new;

    int limit = 1;
    int key = 0;

    Handler handler = new Handler();

    int score = 0;
    int i = 1;
    Random r;
    String url = "";

    String currentWord;

    ArrayList<String> array = new ArrayList<>();

    int time = 0;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateTimer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_conent);
        Intent intent = getIntent();
         key = intent.getIntExtra("key",0);

        if(key == 0)
        {
            url = "https://nazysh.000webhostapp.com/fetchEasy.php";
            time = 60;
        }
        else if (key == 1)
        {
           url = "";
            time = 40;
        }
        else
        {
           url = "";
            time = 20;
        }

        fetchWords();
        timer_tv = (TextView) findViewById(R.id.timer_tv);
        tv_info = (TextView) findViewById(R.id.tv_info);
        tv_word = (TextView) findViewById(R.id.tv_words);
        et_guess = (EditText) findViewById(R.id.et_guess);
        b_check = (Button) findViewById(R.id.b_check);
        b_new = (Button) findViewById(R.id.b_new);
        tv_score = findViewById(R.id.score_tv);
        tv_score.setText("Score: 0");


        r = new Random();


        b_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_guess.getText().toString().equalsIgnoreCase(currentWord)){
                    tv_info.setText("Awesome!");

                    score = score + 10;
                    i = i + 1;

                } else
                {
                    i--;
                    score = score - 10;
                    tv_info.setText("Score"+score);
                }

                b_check.setEnabled(false);
                b_new.setEnabled(true);
                tv_score.setText("Score: "+score);
            }
        });

        b_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (limit >= 5)
                {

                }
                else
                {
                    newGame();
                    limit++;
                }
            }
        });
    }
public void insertscore(){

}

    private void fetchWords()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("easy");

                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        array.add(object.getString("Easyword"));
                    }

                    newGame();
                    updateTimer();

                }


                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main_conent.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }



    public void updateTimer()
    {
        if(time < 0)
        {
            handler.removeCallbacks(runnable);
            this.finish();
            //alert
//            AlertDialog alertDialog = new AlertDialog(getApplicationContext());
        }
        else {
            time--;
            timer_tv.setText("Timer: 00:" + time + "");
            handler.postDelayed(runnable, 1000);
        }
    }

    //shuffle algoritm
    private String shuffleWord(String word){
        List<String> letters = Arrays.asList(word.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        for (String letter : letters){
            shuffled += letter;
        }
        return shuffled;
    }

    private void  newGame(){
        // get random words form the dictionary
        currentWord = array.get(r.nextInt(array.size()));
        // show the shuffle words
        tv_word.setText(shuffleWord(currentWord));
        //clear the text field
        et_guess.setText("");
        //switch button
        b_new.setEnabled(false);

        b_check.setEnabled(true);
    }
}