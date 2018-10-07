package com.example.eunju.blockvoting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        Button voteButton = (Button)findViewById(R.id.voteButton);

        //elements의 Text설정
        date.setText(vote_sdate.concat(getString(R.string.date).concat(vote_edate)));
        title.setText(getString(R.string.title).concat(vote_title));
        content.setText(getString(R.string.content).concat(vote_content));


    }

    public void onClickVote(View v)
    {

        //Layout Dialog로 띄우기
        LayoutInflater vi=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout certLayout=(LinearLayout)vi.inflate(R.layout.dialog_cert,null);

        //name, num 변수 가지기 / 추후 서버에서 비교 확인
        final EditText certName = certLayout.findViewById(R.id.certName);
        final EditText certNum = certLayout.findViewById(R.id.certNum);

        final AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setTitle(getString(R.string.cert_title));
        adb.setView(certLayout);
        //뒤로가기 불가
        adb.setCancelable(false);
        //확인버튼 작동
        adb.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //추후 데이터 쌍을 만들어 server에 전달, 비교 필요
                //String s1 = certName.getText().toString();
                //String s2 = certNum.getText().toString();
                //String re = s1.concat(s2);
                Toast.makeText(MainActivity.this,certName.getText().toString() + certNum.getText().toString(),Toast.LENGTH_SHORT).show();

                //추후 server에서 값이 확인될 경우에만 수행
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
        Toast.makeText(this,"Test", Toast.LENGTH_SHORT).show();

    }

}
