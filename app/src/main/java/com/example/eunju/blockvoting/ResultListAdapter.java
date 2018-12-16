package com.example.eunju.blockvoting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ResultListAdapter  extends RecyclerView.Adapter<ResultListAdapter.ViewHolder> {
    Context context;
    List<CandidateItem> items;
    int item_layout;
    int highest = 0;

    //생성자
    public ResultListAdapter(Context context, List<CandidateItem> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;

        ///////
        try {
            int count = 0;
            //Toast.makeText(context, this.items.size()+"", Toast.LENGTH_SHORT).show();
            //Toast.makeText(context, this.items.get(0).getScore()+"", Toast.LENGTH_SHORT).show();

            while (count < this.items.size()) {
                if (highest <= this.items.get(count).getScore()) {
                    highest = this.items.get(count).getScore();
                 }
                count++;
            }
        } catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(context, "??", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public ResultListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //match_parent
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);

        return new ResultListAdapter.ViewHolder(v);
    }

    //화면의 리스트 CardView에 값을 넣어주는 작업
    public void onBindViewHolder(ResultListAdapter.ViewHolder holder, int position) {
        final CandidateItem item = items.get(position);
        holder.resultImg.setImageBitmap(item.getImage());
        holder.resultName.setText(item.getCandidateName());
        holder.resultScore.setText("득표수: "+item.getScore());
        if(item.getScore() == highest) {
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.selected));
            holder.resultName.setTextColor(context.getResources().getColor(R.color.white));
            holder.resultScore.setTextColor(context.getResources().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    //리스트 아이템 구성 (화면)
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        ImageView resultImg;
        TextView resultName;
        TextView resultScore;

        public ViewHolder(View itemView) {
            super(itemView);
            cardview = (CardView)itemView.findViewById(R.id.result_item);
            resultImg = (ImageView) itemView.findViewById(R.id.result_img);
            resultName = (TextView) itemView.findViewById(R.id.result_name);
            resultScore = (TextView) itemView.findViewById(R.id.result_score);
        }
    }
}
