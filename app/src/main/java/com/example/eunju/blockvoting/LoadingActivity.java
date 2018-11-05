package com.example.eunju.blockvoting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Button Login = (Button) findViewById(R.id.login_Button);
    }

    public void onClickLogin(View v) {
        final EditText user_ID = findViewById(R.id.user_ID);
        final EditText user_Password = findViewById(R.id.user_Password);
        final String userID = user_ID.getText().toString();
        final String userPassword = user_Password.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(LoadingActivity.this, user_ID.getText().toString() + user_Password.getText().toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoadingActivity.this, response, Toast.LENGTH_LONG).show();
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        String userID = jsonResponse.getString("userID");
                        String userPassword = jsonResponse.getString("userPassword");

                        //server에서 값이 확인될 경우에만 수행/ 다음화면 전환, 변수 전달
                        Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                        //변수 전달
                        intent.putExtra("userID", userID);
                        intent.putExtra("userPassword", userPassword);
                        LoadingActivity.this.startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoadingActivity.this);
                        builder.setMessage("존재하지 않는 사용자입니다. 다시 시도해주세요.")
                                .setNegativeButton("다시시도", null)
                                .create()
                                .show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(LoadingActivity.this, "Exception", Toast.LENGTH_SHORT).show();
                }
            }
        };

        LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
        RequestQueue queue = Volley.newRequestQueue(LoadingActivity.this);
        queue.add(loginRequest);
    }
}