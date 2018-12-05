package com.example.eunju.blockvoting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VotingActivity extends AppCompatActivity {

    final int ITEM_SIZE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);
        //getSupportActionBar().setTitle("후보자 선택");


        ////////////////////////
        try {
            //MainActivity 에서 Intent로 변수 받기
            Intent intent = getIntent();
            final String userID = intent.getStringExtra("userID");
            int voteID = intent.getIntExtra("voteID", 0);
            String voteName = intent.getStringExtra("voteName");
            String voteContent = intent.getStringExtra("voteContent");
            String voteSdate = intent.getStringExtra("voteSdate");
            String voteEdate = intent.getStringExtra("voteEdate");

            JSONObject jsonObject = new JSONObject(intent.getStringExtra("candidateList"));
            JSONArray jsonArray = jsonObject.getJSONArray("candidateData");

            TextView date = findViewById(R.id.date);
            TextView title = findViewById(R.id.title);
            TextView content = findViewById(R.id.content);

            //elements Text설정 (선택 투표 정보)
            date.setText(voteSdate.concat(getString(R.string.date).concat(voteEdate)));
            title.setText(getString(R.string.title).concat(voteName));
            content.setText(getString(R.string.content).concat(voteContent));
            //content.setText(intent.getStringExtra("candidateList"));

            List<CandidateItem> items = new ArrayList<>();
            int count = 0;
            int candidateID;
            String candidateName, candidateInfo, encodedImg;
            Bitmap imageUrl;

            //ArrayList에 후보자 정보를 담은 객체 저장
            while (count < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(count);
                candidateID = object.getInt("candidateID");
                //score = object.getInt("score");
                candidateName = object.getString("candidateName");
                candidateInfo = object.getString("candidateInfo");
                //encodedImg = object.getString("img");

                //Base64_encode 된 String값을 다시 decode하여 Bitmap으로 변환시키는 과정
                //byte[] decodedByteArray = Base64.decode(encodedImg, Base64.DEFAULT);
                //Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);

                //items.add(new CandidateItem(userID, candidateNum, candidateName, candidateInfo, decodedBitmap, score));
                items.add(new CandidateItem(userID, voteID, candidateID, candidateName, candidateInfo));
                count++;
             }


            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.candidate_Recycler);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager); //... CandidateListAdapter에 구현

            //CallBack 설정 Step4
            CandidateListAdapter adapter = new CandidateListAdapter(VotingActivity.this, items, R.layout.activity_voting);
            adapter.setOnCallBackEvent(new CandidateListAdapter.CallBackListener() {
                @Override
                public void onReceivedEvent(final int voteID, final int candidateID, final String candidateName) {

                    //Button Click
                    Button voteButton = findViewById(R.id.vote_Button);
                    voteButton.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // TODO : click event
                            AlertDialog.Builder builder = new AlertDialog.Builder(VotingActivity.this);
                            builder.setTitle("투표");
                            builder.setMessage("정말로"+candidateName+"에게 투표하시겠습니까?");
                            builder.setPositiveButton("예",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(VotingActivity.this, "Send "+userID+", "+candidateID, Toast.LENGTH_LONG).show();
                                            Response.Listener<String> responseListener = new Response.Listener<String>() {

                                                @Override
                                                public void onResponse(String response) {
                                                    try {
                                                        Toast.makeText(VotingActivity.this,response, Toast.LENGTH_SHORT).show();
                                                    }
                                                     catch (Exception e) {
                                                        e.printStackTrace();
                                                        Toast.makeText(VotingActivity.this, "Exception", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            };

                                            Toast.makeText(VotingActivity.this, userID + voteID + candidateID, Toast.LENGTH_SHORT).show();
                                            VotingRequest votingRequest = new VotingRequest(userID, voteID, candidateID, responseListener);
                                            RequestQueue queue = Volley.newRequestQueue(VotingActivity.this);
                                            queue.add(votingRequest);

                                        }
                                    });
                            builder.setNegativeButton("아니오",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(VotingActivity.this, "취소", Toast.LENGTH_LONG).show();
                                        }
                                    });
                            builder.show();
                        }
                    });
                    Toast.makeText(VotingActivity.this, candidateName + candidateID, Toast.LENGTH_SHORT).show();
                }
            });
            recyclerView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(VotingActivity.this, "exception1", Toast.LENGTH_SHORT).show();
        }
    }

    /*//투표버튼을 누르면 최종으로 투표여부 확인
    public void onClickVote(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("투표");
        builder.setMessage("정말로 ???에게 투표하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(VotingActivity.this, "예를 선택했습니다.", Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(VotingActivity.this, "취소", Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }*/
}

        /*List<Item> items = new ArrayList<>();
        Item[] item = new Item[ITEM_SIZE];
        item[0] = new Item("#1");
        item[1] = new Item("#2");
        item[2] = new Item("#3");
        item[3] = new Item("#4");
        item[4] = new Item("#5");

        for (int i = 0; i < ITEM_SIZE; i++) {
            items.add(item[i]);
        }

        TextView date = findViewById(R.id.date1);
        TextView title = findViewById(R.id.title1);
        TextView content = findViewById(R.id.content1);

        //elements의 Text설정
        voteListData data = new voteListData();
        date.setText(data.getVote_sdate().concat(getString(R.string.date).concat(data.getVote_edate())));
        title.setText(getString(R.string.title).concat(data.getVote_title()));
        content.setText(getString(R.string.content).concat(data.getVote_content()));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new VoteListAdapter(getApplicationContext(), items, R.layout.activity_voting));
    }
}
}*/