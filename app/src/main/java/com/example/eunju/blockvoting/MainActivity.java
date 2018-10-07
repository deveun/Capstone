package com.example.eunju.blockvoting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String vote_title = "테스트투표";
    String vote_sdate = "2018.10.10";
    String vote_edate = "2018.11.10";
    String vote_content = "내용설명";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //추후 String 변수값은 서버에서 받아오기.
        //activity_main.xml 의 elements 연결
        TextView date = findViewById(R.id.date);
        TextView title = findViewById(R.id.title);
        TextView content = findViewById(R.id.content);
        //추후 투표여부, 기간에 따라 버튼 바꾸기
        Button voteButton = (Button)findViewById(R.id.voteButton);

        //elements의 Text설정
        date.setText(vote_sdate.concat(getString(R.string.date).concat(vote_edate)));
        title.setText(getString(R.string.title).concat(vote_title));
        content.setText(getString(R.string.content).concat(vote_content));


    }

    public void onClickVote(View v)
    {
        Toast.makeText(this,"BUTTON CLICK", Toast.LENGTH_SHORT).show();

        //Layout Dialog로 띄우기
        LayoutInflater vi=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout certLayout=(LinearLayout)vi.inflate(R.layout.dialog_cert,null);

        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setTitle("본인인증");
        adb.setView(certLayout);
        /*
        adb.setNeutralButton("확인", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_SHORT).show();


            }
        }).show();
        */
        adb.show();
        Toast.makeText(this,"Test", Toast.LENGTH_SHORT).show();

    }
}
