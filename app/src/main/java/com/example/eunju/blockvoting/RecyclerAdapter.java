package com.example.eunju.blockvoting;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<Item> items;
    int item_layout;

    public RecyclerAdapter(Context context, List<Item> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }

    //화면의 리스트 아이템에 LIst값을 넣어주는 작업
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = items.get(position);

        holder.candidate.setText(item.Select());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.Select(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    //리스트 아이템 구성 (화면)
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView candidate;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            candidate = (TextView) itemView.findViewById(R.id.itemname);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}