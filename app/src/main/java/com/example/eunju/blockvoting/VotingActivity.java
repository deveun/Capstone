package com.example.eunju.blockvoting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VotingActivity extends AppCompatActivity {

   final int ITEM_SIZE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        ////////////////////////
        try {
            Intent intent = getIntent();
            int userNum = intent.getIntExtra("userNum", 0);
            int voteNum = intent.getIntExtra("voteNum", 0);
            String voteName = intent.getStringExtra("voteName");
            String voteContent = intent.getStringExtra("voteContent");
            String voteSdate = intent.getStringExtra("voteSdate");
            String voteEdate = intent.getStringExtra("voteEdate");;

            JSONObject jsonObject = new JSONObject(intent.getStringExtra("candidateList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");

            TextView date = findViewById(R.id.date);
            TextView title = findViewById(R.id.title);
            TextView content = findViewById(R.id.content);

            //elements Text설정
            date.setText(voteSdate.concat(getString(R.string.date).concat(voteEdate)));
            title.setText(getString(R.string.title).concat(voteName));
            content.setText(getString(R.string.content).concat(voteContent));

            List<CandidateItem> items = new ArrayList<>();
            int count = 0;
            int candidateNum;
            String candidateName;

            //ArrayList에 후보자 정보를 담은 객체 저장
            while(count<jsonArray.length())
            {
                JSONObject object = jsonArray.getJSONObject(count);
                candidateNum= object.getInt("candidateNum");
                candidateName= object.getString("candidateName");
                items.add(new CandidateItem(candidateNum, candidateName));
                count++;
            }

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.candidate_Recycler);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(new CandidateListAdapter(getApplicationContext(), items, R.layout.activity_voting));

        } catch (Exception e) {
        e.printStackTrace();
        Toast.makeText(VotingActivity.this, "exception", Toast.LENGTH_SHORT).show();
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
*/}}