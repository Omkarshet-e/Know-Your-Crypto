package com.example.cryptotrackingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public String url = "https://api.coinpaprika.com/v1/coins";

    RecyclerView recyclerView;
    ProgressBar progressBar;


    ArrayList<CryptoInfo> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = findViewById(R.id.progressBar);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                for(int i=0;i<response.length();i++){
                        JSONObject obj = response.getJSONObject(i);
                        String name = obj.getString("name");
                        String id = obj.getString("id");
                        String symbol = obj.getString("symbol");
                        String is_active = obj.getString("is_active");

                        list.add(new CryptoInfo(name,symbol,is_active,id));


                }
                    showNote();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "ABe yaar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error Loading", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);


    }
    public void showNote(){

        progressBar.setVisibility(View.GONE);
        CryptoInfoAdapter adapter = new CryptoInfoAdapter(this,list);
        recyclerView.setAdapter(adapter);

    }
}