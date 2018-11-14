package com.example.eunju.blockvoting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VoteListAdapter extends RecyclerView.Adapter<VoteListAdapter.ViewHolder> {
    Context context;
    List<VoteItem> items;
    int item_layout;

    //생성자
    public VoteListAdapter(Context context, List<VoteItem> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vote, null);
        return new ViewHolder(v);
    }

    //화면의 리스트 CardView에 값을 넣어주는 작업
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final VoteItem item = items.get(position);

        holder.votename.setText(item.getName());
        holder.vote_sdate.setText(item.getSdate());
        holder.vote_edate.setText(item.getEdate());

        //리스트 클릭
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getName(), Toast.LENGTH_SHORT).show();

                //날짜비교

                try {
                    Date _sdate= new SimpleDateFormat("yyyy-MM-dd").parse(item.getSdate());
                    Date _edate= new SimpleDateFormat("yyyy-MM-dd").parse(item.getEdate());
                    Date _curdate= new Date();

                    //Toast.makeText(context, new SimpleDateFormat("yyyy-MM-dd").format(_curCal), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context, new SimpleDateFormat("yyyy-MM-dd").format(_sCal), Toast.LENGTH_SHORT).show();
                    if(_curdate.compareTo(_sdate) >= 0 && _curdate.compareTo(_edate) <= 0)
                        {
                            Intent intent = new Intent(context, VotingActivity.class);
                            //변수 전달
                            //intent.putExtra("userID", userID);
                            intent.putExtra("voteName", item.getName());
                            intent.putExtra("voteContent", item.getContent());
                            intent.putExtra("voteSdate", item.getSdate());
                            intent.putExtra("voteEdate", item.getEdate());
                            context.startActivity(intent);
                            }
                    else if(_curdate.compareTo(_sdate) < 0)
                        Toast.makeText(context, "투표진행 예정", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context, "투표기간 종료", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    //리스트 아이템 구성 (화면)
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        TextView votename;
        TextView vote_sdate;
        TextView vote_edate;

        public ViewHolder(View itemView) {
            super(itemView);
            cardview = (CardView) itemView.findViewById(R.id.vote_item);
            votename = (TextView) itemView.findViewById(R.id.vote_name);
            vote_sdate = (TextView) itemView.findViewById(R.id.vote_sdate);
            vote_edate = (TextView) itemView.findViewById(R.id.vote_edate);
        }
    }
}