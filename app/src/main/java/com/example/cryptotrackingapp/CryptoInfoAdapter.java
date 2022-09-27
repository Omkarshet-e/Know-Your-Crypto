package com.example.cryptotrackingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CryptoInfoAdapter extends RecyclerView.Adapter<CryptoInfoAdapter.ViewHolder> {

    Context context;
    ArrayList<CryptoInfo> list;

    public CryptoInfoAdapter(Context context, ArrayList<CryptoInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.crypto_infocard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.symbol.setText(list.get(position).getSymbol());

        if(list.get(position).getIs_active().equals("true")){
            holder.is_active.setText("Active");
            holder.is_active.setTextColor(Color.GREEN);
        }else {
            holder.is_active.setText("Inactive");
            holder.is_active.setTextColor(Color.RED);
        }
        holder.cardAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AssetInfo.class);
                intent.putExtra("Query",list.get(holder.getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,symbol,is_active;
        CardView cardAsset;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.cardName);
            symbol = itemView.findViewById(R.id.cardSymbol);
            is_active = itemView.findViewById(R.id.cardActive);
            cardAsset = itemView.findViewById(R.id.cardAsset);
        }
    }
}
