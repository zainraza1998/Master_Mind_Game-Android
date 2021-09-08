package com.example.wordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
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
import java.util.zip.Inflater;

public class FetchUsers extends AppCompatActivity {
    ListView listView ;
    ArrayList<Userfetchmodel> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_users);
        listView = findViewById(R.id.userfetchid);
        fetch();
    }
    
    public  class CustomAdapter extends BaseAdapter{

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
           View v = getLayoutInflater().inflate(R.layout.useritem,null);
            TextView nameTV, emailTV,userTV,passTV;
            nameTV = v.findViewById(R.id.nameid);
            emailTV = v.findViewById(R.id.emailId);
            userTV = v.findViewById(R.id.userNameId);
            passTV = v.findViewById(R.id.passId);

            nameTV.setText(arrayList.get(position).getName());
            emailTV.setText(arrayList.get(position).getEmail());
            userTV.setText(arrayList.get(position).getUserName());
            passTV.setText(arrayList.get(position).getPass());
            return v;

        }
    }

    public void fetch(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://nazysh.000webhostapp.com/fetch_data.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("users");

                    for (int i = 0; i<jsonArray.length();i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        Userfetchmodel userfetchmodel = new Userfetchmodel(object.getString("id"),object.getString("name"),object.getString("email"),object.getString("userName"),object.getString("pass"));
                        arrayList.add(userfetchmodel);
                    }

                    CustomAdapter customAdapter = new CustomAdapter();
                    listView.setAdapter(customAdapter);

                }

                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FetchUsers.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(FetchUsers.this);
        requestQueue.add(stringRequest);
    }}