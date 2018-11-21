package com.example.eunju.blockvoting;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("로그인");

        Button Login = (Button) findViewById(R.id.login_Button);
    }

    public void onClickLogin(View v) throws UnsupportedEncodingException {

        final EditText user_ID = findViewById(R.id.user_ID);
        final EditText user_Password = findViewById(R.id.user_Password);
        final String userID = user_ID.getText().toString();
        final String userPassword = user_Password.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    //Toast.makeText(LoginActivity.this, user_ID.getText().toString() + user_Password.getText().toString(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        int userNum = jsonResponse.getInt("userNum");
                        String userID = jsonResponse.getString("userID");

                        //화면전환 전에 리스트 정보 받아오기
                        String voteList = new BackgroundTask().execute().get();
                        //Toast.makeText(LoginActivity.this, voteList, Toast.LENGTH_SHORT).show();
                        //server에서 값이 확인될 경우에만 수행/ 다음화면 전환, 변수 전달
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        //변수 전달
                        intent.putExtra("userNum", userNum);
                        intent.putExtra("userID", userID);
                        intent.putExtra("voteList", voteList);
                        LoginActivity.this.startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("존재하지 않는 사용자입니다. 다시 시도해주세요.")
                                .setNegativeButton("다시시도", null)
                                .create()
                                .show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Exception", Toast.LENGTH_SHORT).show();
                }
            }
        };

        LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginRequest);
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://deveun.dothome.co.kr/Vote.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                //publishProgress();
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            //Toast.makeText(LoginActivity.this, voteList, Toast.LENGTH_SHORT).show();
            super.onProgressUpdate(values);
        }
        /*@Override
        public void onPostExecute(String result) {

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            //변수 전달
            intent.putExtra("voteList", result);
            LoginActivity.this.startActivity(intent);
        }*/

    }
}