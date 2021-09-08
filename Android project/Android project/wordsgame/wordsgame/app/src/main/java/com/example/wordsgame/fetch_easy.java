package com.example.wordsgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class fetch_easy extends AppCompatActivity {
    ListView listView;
    Button btnAdd;
    ArrayList<EasyFetchModel> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_easy);
        btnAdd  = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fetch_easy.this,easy.class);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.fetchlistid);
        fetcheasyword();

    }
    public void fetcheasyword(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://nazysh.000webhostapp.com/fetchEasy.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("easy");

                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        EasyFetchModel easyFetchModel = new EasyFetchModel(object.getString("id"),object.getString("Easyword"));
                        arrayList.add(easyFetchModel);
                    }
                    customAdapter customAdapter = new customAdapter();
                    listView.setAdapter(customAdapter);
                }


                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(fetch_easy.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(fetch_easy.this);
        requestQueue.add(stringRequest);
    }


    public class customAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = getLayoutInflater().inflate(R.layout.easyitemview,null);
            TextView easywordTv;
            Button buttonUpdate,buttonDelete,buttonAdd;
            easywordTv = v.findViewById(R.id.easyitemid);
            easywordTv.setText(arrayList.get(position).getWords());



            buttonDelete  = v.findViewById(R.id.btnDelete);
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteWord(position);
                }
            });
            buttonUpdate  = v.findViewById(R.id.btnUpdate);
            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(fetch_easy.this,update_easy.class);
                   intent.putExtra("data",arrayList.get(position));
                    startActivity(intent);

                }
            });


            return v;

        }
    }

    public  void deleteWord(int position){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://nazysh.000webhostapp.com/DeleteEasy.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(fetch_easy.this,response.trim(),Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(fetch_easy.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("id",arrayList.get(position).getId());
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(fetch_easy.this);
        requestQueue.add(stringRequest);

    }
}