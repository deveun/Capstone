package com.example.eunju.blockvoting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //final int ITEM_SIZE = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///////////////////////////////////////////////////////////////////////
        try {
            Intent intent = getIntent();
            int userNum = intent.getIntExtra("userNum",0);
            String userID = intent.getStringExtra("userID");
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("voteList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");

            int count = 0;
            int voteNum;
            String voteName, voteContent, voteSdate, voteEdate;
            List<VoteItem> items = new ArrayList<>();

            //ArrayList에 투표정보를 담은 객체 저장
            while(count<jsonArray.length())
            {
                JSONObject object = jsonArray.getJSONObject(count);
                voteNum= object.getInt("voteNum");
                voteName= object.getString("voteName");
                voteContent=object.getString("voteContent");
                voteSdate= object.getString("voteSdate");
                voteEdate= object.getString("voteEdate");

                items.add(new VoteItem(userNum,voteNum,voteName,voteContent,voteSdate,voteEdate));
                count++;
            }

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.vote_Recycler);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(new VoteListAdapter(getApplicationContext(), items, R.layout.activity_main));

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "exception", Toast.LENGTH_SHORT).show();
        }
        //////////////////////////////////////////////////////////////////////
        /*
            List<VoteItem> items = new ArrayList<>();
            VoteItem[] item = new VoteItem[ITEM_SIZE];
            item[0] = new VoteItem(voteList, "2018-10-30", "2018-11-30");
            item[1] = new VoteItem(userID, "2018-10-30", "2018-11-30");
            item[2] = new VoteItem(userPassword, "2018-10-30", "2018-11-30");
            item[3] = new VoteItem("#4", "2018-10-30", "2018-11-30");
            item[4] = new VoteItem("#5", "2018-10-30", "2018-11-30");

            for (int i = 0; i < ITEM_SIZE; i++) {
                items.add(item[i]);
            }

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.vote_Recycler);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(new VoteListAdapter(getApplicationContext(), items, R.layout.activity_main));
     */   }

}


/*
        //추후 String 변수값은 서버에서 받아오기.
        voteListData data= new voteListData("2018.10.10", "2018.11.10", "테스트 투표", "내용 설명");

        //activity_main.xml 의 elements 연결
        TextView date = findViewById(R.id.date);
        TextView title = findViewById(R.id.title);
        TextView content = findViewById(R.id.content);
        Button voteButton = (Button)findViewById(R.id.startButton);

        //elements의 Text설정
        date.setText(data.getVote_sdate().concat(getString(R.string.date).concat(data.getVote_edate())));
        title.setText(getString(R.string.title).concat(data.getVote_title()));
        content.setText(getString(R.string.content).concat(data.getVote_content()));
    }

    public void onClickVote(View v)
    {
        //Layout Dialog로 띄우기
        LayoutInflater vi=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout certLayout=(LinearLayout)vi.inflate(R.layout.dialog_cert,null);

        //사용자 이름 및 번호(입력)
        final EditText certName = certLayout.findViewById(R.id.certName);
        final EditText certNum = certLayout.findViewById(R.id.certNum);

        final AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setTitle(getString(R.string.cert_title));
        adb.setView(certLayout);
        //뒤로가기 불가
        adb.setCancelable(false);

        //(확인버튼) 서버에 name, num값 전달하여 본인인증
        adb.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this,certName.getText().toString() + certNum.getText().toString(),Toast.LENGTH_SHORT).show();


                //추후 server에서 값이 확인될 경우에만 수행/ 다음화면 전환, 변수 전달
                Intent intent= new Intent(MainActivity.this, VotingActivity.class);
                startActivity(intent);
            }
        });

        //취소버튼 작동
        adb.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"cancel",Toast.LENGTH_SHORT).show();
            }
        });
        adb.show();

    }

    }*/

