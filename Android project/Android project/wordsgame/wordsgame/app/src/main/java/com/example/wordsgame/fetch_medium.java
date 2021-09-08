package com.example.wordsgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

public class fetch_medium extends AppCompatActivity {
    ListView listView;
    Button AddMediumButton;
    ArrayList<MediumFetchModel> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_medium);
        listView = findViewById(R.id.fetchMediumlist);
        fetchMedium();


        AddMediumButton = findViewById(R.id.Mediumbtnid);
        AddMediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fetch_medium.this,medium_level.class);
                startActivity(intent);
            }
        });




    }
    public void fetchMedium(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://nazysh.000webhostapp.com/fetchMedium.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("medium");

                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        MediumFetchModel mediumFetchModel = new MediumFetchModel(object.getString("id"),object.getString("mediumWord"));
                        arrayList.add(mediumFetchModel);
                    }
                    mycustomAdapter mycustomAdapter = new mycustomAdapter();
                    listView.setAdapter(mycustomAdapter);
                }


                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(fetch_medium.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(fetch_medium.this);
        requestQueue.add(stringRequest);
    }
    public class mycustomAdapter extends BaseAdapter {

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
            View v = getLayoutInflater().inflate(R.layout.mediumitemview,null);

            TextView mediumTV;
            mediumTV = v.findViewById(R.id.mediumitemid);
            mediumTV.setText(arrayList.get(position).getMediumWord());
            Button buttonMediumUpdate, buttonDeleteMedium;

            buttonMediumUpdate = v.findViewById(R.id.btnMediumUpdate);

            buttonMediumUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(fetch_medium.this,update_medium.class);
                      intent.putExtra("data",arrayList.get(position));
                    startActivity(intent);
                }
            });

            buttonDeleteMedium = v.findViewById(R.id.btnMediumDelete);
            buttonDeleteMedium.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteWord(position);
                }
            });

            return v;
        }
        public  void deleteWord(int position){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://nazysh.000webhostapp.com/deleteMedium.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(fetch_medium.this,response.trim(),Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(fetch_medium.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
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
            RequestQueue requestQueue = Volley.newRequestQueue(fetch_medium.this);
            requestQueue.add(stringRequest);
        }

    }
}