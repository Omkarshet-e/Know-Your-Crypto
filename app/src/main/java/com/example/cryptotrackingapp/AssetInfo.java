package com.example.cryptotrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class AssetInfo extends AppCompatActivity {

    ImageView img;
    TextView asset,description,descriptionTitle;
    ProgressBar imgProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_info);

        img = findViewById(R.id.imgAsset);
        asset = findViewById(R.id.txtAssetName);
        description = findViewById(R.id.txtAssetDescription);
        descriptionTitle = findViewById(R.id.txtAssetDescriptionTitle);
        imgProgress = findViewById(R.id.imgprogress);

        imgProgress.setVisibility(View.VISIBLE);

        String query = getIntent().getExtras().getString("Query");
        String url = "https://api.coinpaprika.com/v1/coins"+"/"+query;

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Picasso.get().load(response.getString("logo")).into(img);
                    asset.setText(response.getString("name"));
                    String des = response.getString("description");
                    Log.d("Descrip",des);
                    if(des.isEmpty()){
                        description.setText("No info available");
                    }else {
                        description.setText(des);
                    }
                    descriptionTitle.setText("Description");
                    imgProgress.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AssetInfo.this, "Error getting Asset Info", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
}