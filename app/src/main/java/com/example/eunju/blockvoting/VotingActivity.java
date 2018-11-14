package com.example.eunju.blockvoting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VotingActivity extends AppCompatActivity {

   final int ITEM_SIZE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

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