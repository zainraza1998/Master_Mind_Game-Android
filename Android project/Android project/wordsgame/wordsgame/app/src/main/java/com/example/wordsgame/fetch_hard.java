package com.example.wordsgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
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

public class fetch_hard extends AppCompatActivity {

    ListView listView;
    Button button;
    ArrayList<HardDataModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_hard);
        listView = findViewById(R.id.listHid);
        fetchHard();
        button = findViewById(R.id.btnAddid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fetch_hard.this,hard.class);
                startActivity(intent);
            }
        });

    }
     public void fetchHard() {


         StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://nazysh.000webhostapp.com/fetchHard.php", new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {
                 try {
                     JSONObject jsonObject = new JSONObject(response);
                     JSONArray jsonArray = jsonObject.getJSONArray("hard");
                     for(int i = 0; i < jsonArray.length(); i++){
                         JSONObject object = jsonArray.getJSONObject(i);
                         HardDataModel hardDataModel = new HardDataModel(object.getString("id"),object.getString("hardWord"));
                         arrayList.add(hardDataModel);
                     }
                     CustomAdapter customAdapter = new CustomAdapter();
                     listView.setAdapter(customAdapter);
                 }

                 catch

                 (JSONException e) {
                     e.printStackTrace();
                 }
             }

         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(fetch_hard.this, error.getLocalizedMessage(), Toast.LENGTH_LONG);
             }
         });
         RequestQueue requestQueue = Volley.newRequestQueue(fetch_hard.this);
         requestQueue.add(stringRequest);
     }
    public class CustomAdapter extends BaseAdapter{

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
            View v = getLayoutInflater().inflate(R.layout.itemviewh,null);
            TextView textView;
            Button btndelete, btnupdate;
            textView = v.findViewById(R.id.itemHardId);
            textView.setText(arrayList.get(position).getHardWord());

            btnupdate = v.findViewById(R.id.btnUpdate);
            btnupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(fetch_hard.this,updateHard.class);
                    intent.putExtra("data",arrayList.get(position));
                    startActivity(intent);
                }
            });
            btndelete = v.findViewById(R.id.btnDelete);
            btndelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteWord(position);
                }
            });



            return v;
        }
        public  void deleteWord(int position){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://nazysh.000webhostapp.com/deleteHard.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(fetch_hard.this,response.trim(),Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(fetch_hard.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
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
            RequestQueue requestQueue = Volley.newRequestQueue(fetch_hard.this);
            requestQueue.add(stringRequest);
        }
    }
}