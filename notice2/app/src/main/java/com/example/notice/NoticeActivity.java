package com.example.notice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View.OnClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

public class NoticeActivity extends AppCompatActivity {
    TextView textResponse;
    Button btnPostRequest;
    RestClient restClient;
    protected void onCreate(Bundle savedInstanceState){ //화면 만들기 active_notice.xml 화면띄움
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice); // notice 공지사항 화면 띄우기
        init();
    }

    private void init() {
        getViews();
        setListeners();
        restClient = RestClient.getInstance();
        restClient.parseDB("https://shinple.kr/app_db/notice_tbl.php");  // 불러오고 싶은 DB 테이블 설정!!
    }

    private void getViews() {
        btnPostRequest = (Button) findViewById(R.id.btnPostRequest);
        textResponse = (TextView) findViewById(R.id.textResponse);
    }

    private void setListeners() {                               // 버튼 안눌러도 바로 받아오게 만들기!!! **********
        btnPostRequest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String response = restClient.postRequest();
                        //setText("HTTPS Post Response:-\n" + response);
                        setText(response); //이 부분부터 파씽 고고
                    }
                }).start();
            }
        });
    }

    private void setText(final String response) {
        NoticeActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //textResponse.setText(response);
                ParseJSON(response);
            }
        });
    }

    public void onClick_find(View v){ //test 검색을 누르면 화면전환(이 부분은 검색 목록이 떠야함)
        Intent findIntent = new Intent(getApplicationContext(), FindActivity.class);
        startActivity(findIntent);  //find activity 실행
        finish();
    }

    public void onClick_notice_str(View v){ //공지사항을 누르면 공지사항 home으로 이동!!
        Intent notice_strIntent = new Intent(getApplicationContext(),NoticeActivity.class);
        startActivity(notice_strIntent); //notice activity 실행
        finish();
    }


    // JSON 데이터를 파싱합니다.(가져온 DB 정보 중 필요한 부분 파씽)
    // URLConnector로부터 받은 String이 JSON 문자열이기 때문입니다.
    public String ParseJSON(String target){

        try {
            JSONObject json = new JSONObject(target);
            JSONArray arr = json.getJSONArray("result");
            for(int i = 0; i < arr.length(); i++){
                JSONObject json2 = arr.getJSONObject(i);
//                textResponse.setText(json2.getString("admin_id"));
                textResponse.setText(json2.getString("title"));
//                textResponse.setText(json2.getString("created_date"));
//                textResponse.setText(json2.getString("n_id"));
            }
            return "";
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
