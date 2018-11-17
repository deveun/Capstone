package com.example.eunju.blockvoting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Collection;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class CandidateListAdapter extends RecyclerView.Adapter<CandidateListAdapter.ViewHolder> {
    Context context;
    List<CandidateItem> items;
    int item_layout;
    View preView;
    //CallBack 설정 Step1
    private CallBackListener mCallBackListener;
    ///////

    //생성자
    public CandidateListAdapter(Context context, List<CandidateItem> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }
    //CallBack 설정 Step2
    public interface CallBackListener{
        void onReceivedEvent(int candidateNum, String candidateName);    }

    public void setOnCallBackEvent(CallBackListener listener){
        mCallBackListener = listener;    }
    ///////////////////////////////

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_candidate, null);
        return new ViewHolder(v);
    }

    //화면의 리스트 CardView에 값을 넣어주는 작업
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CandidateItem item = items.get(position);
        holder.candidateName.setText(item.getCandidateName());

        //리스트 클릭
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //Candidate Detail Dialog 생성
                LayoutInflater vi=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout linearLayout=(LinearLayout)vi.inflate(R.layout.dialog_detail,null);

                TextView candidateName = (TextView)view.findViewById(R.id.candidate_name_detail);
                TextView candidateDetail = (TextView)view.findViewById(R.id.candidate_detail);

                final AlertDialog.Builder adb=new AlertDialog.Builder(context);
                adb.setTitle(item.getCandidateName());
                adb.setView(linearLayout);
                //뒤로가기 불가
                //adb.setCancelable(false);

                //선택 버튼 클릭
                adb.setPositiveButton("선택", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (preView!=null) {
                            preView.setBackgroundColor(context.getResources().getColor(R.color.card));
                        }
                        view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        preView=view;
                        //CallBack 설정 Step3
                        mCallBackListener.onReceivedEvent(item.getCandidateNum(), item.getCandidateName());
                        ///////////
                        //Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
                    }});
                //취소 버튼 클릭
                adb.setNegativeButton("취소", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(context,"cancel",Toast.LENGTH_SHORT).show();
                    }
                });
                //실행
                adb.show();
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
        TextView candidateName;

        public ViewHolder(View itemView) {
            super(itemView);
            cardview = (CardView)itemView.findViewById(R.id.candidate_item);
            candidateName = (TextView) itemView.findViewById(R.id.candidatename);
        }
    }
}