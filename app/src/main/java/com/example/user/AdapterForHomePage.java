package com.example.user;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterForHomePage extends RecyclerView.Adapter<AdapterForHomePage.MyViewHolder> {

    ArrayList<DataSetHomePage> dataList;
    Context context;

    public AdapterForHomePage(ArrayList<DataSetHomePage> data, Context context) {
        this.dataList = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_page_card_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataSetHomePage data = dataList.get(position);
        holder.imageView.setImageResource(data.getImage());
        holder.textView.setText(data.getText());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0) {
                    Intent intent = new Intent(context, RewardsPage.class);
                    context.startActivity(intent);
                }

                // Page is not created yet
                if(position == 1) {
                    //Intent intent = new Intent(context, OffersPage.class);
                    //context.startActivity(intent);
                }

                if(position == 2) {
                    Intent intent = new Intent(context, Order.class);
                    context.startActivity(intent);
                }
            }
        });

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0) {
                    Intent intent = new Intent(context, RewardsPage.class);
                    context.startActivity(intent);
                }

                // Page is not created yet
                if(position == 1) {
                    //Intent intent = new Intent(context, OffersPage.class);
                    //context.startActivity(intent);
                }

                if(position == 2) {
                    Intent intent = new Intent(context, Order.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
            textView = itemView.findViewById(R.id.textInCard);
            cardView = itemView.findViewById(R.id.menuOptions);
        }
    }
}