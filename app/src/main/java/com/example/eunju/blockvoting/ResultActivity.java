package com.example.eunju.blockvoting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setTitle("투표 결과");

        ////////////////////////
        try {
            //VoteListAdapter 에서 Intent로 변수 받기
            Intent intent = getIntent();
            String userID = intent.getStringExtra("userID");
            int voteID = intent.getIntExtra("voteID", 0);
            String voteName = intent.getStringExtra("voteName");
            //String voteContent = intent.getStringExtra("voteContent");
            String voteSdate = intent.getStringExtra("voteSdate");
            String voteEdate = intent.getStringExtra("voteEdate");

            JSONObject jsonObject = new JSONObject(intent.getStringExtra("candidateList"));
            JSONArray jsonArray = jsonObject.getJSONArray("candidateData");

            TextView date = findViewById(R.id.result_date);
            TextView title = findViewById(R.id.result_title);
            TextView content = findViewById(R.id.result_content);

            //elements Text설정 (선택 투표 정보)
            date.setText(voteSdate.concat(getString(R.string.date).concat(voteEdate)));
            title.setText(getString(R.string.title).concat(voteName));
            //content.setText(getString(R.string.content).concat(voteContent));
            content.setText(intent.getStringExtra("result"));

            List<CandidateItem> items = new ArrayList<>();
            int count = 0;
            int candidateID, score;
            String candidateName, candidateInfo, encodedImg;

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

                items.add(new CandidateItem(userID, voteID, candidateID , candidateName, candidateInfo));
                //items.add(new CandidateItem(userID, candidateNum, candidateName, candidateInfo, decodedBitmap, score));
                count++;
            }

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.result_Recycler);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager); //... ResultListAdapter에 구현
            recyclerView.setAdapter(new ResultListAdapter(getApplicationContext(), items, R.layout.activity_result));

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ResultActivity.this, "exception1", Toast.LENGTH_SHORT).show();
        }

        //Button Click
        Button redoButton = findViewById(R.id.redo_Button);
        redoButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
