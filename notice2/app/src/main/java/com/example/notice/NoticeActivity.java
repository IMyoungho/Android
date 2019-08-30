package com.example.notice;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notice.Function.SslFunc;
import com.example.notice.App_DB.RestClient;
import com.example.notice.ListVO.ListVO;
import com.example.notice.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.widget.ListView;

import com.example.notice.App_DB.Adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {
    ListView listview;
    ListViewAdapter notice_adapter;

    protected void onCreate(Bundle savedInstanceState) { //화면 만들기 active_notice.xml 화면띄움
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice); // notice 공지사항 화면 띄우기
        init();
    }

    // SSL 통신 시 필요 : DB 추가 부분 ------------------------------------------------------------------------------------------------------------------------
    TextView[] textResponse = new TextView[4];
    String[] text_column = new String[4];
    RestClient restClient; //RestClient class를 이용해야함 필요 시 만들어서 사용


    private void init() {
        text_column[0] = "title";
        text_column[1] = "created_date";
        text_column[2] = "admin_id";
        text_column[3] = "view_count";

        getViews();
        restClient = RestClient.getInstance();
        restClient.parseDB("https://shinple.kr/app_db/notice_tbl.php");  // 불러오고 싶은 DB 테이블 설정!!
        setListeners();
    }


    private void getViews() {
        textResponse[0] = (TextView) findViewById(R.id.title_text);
        textResponse[1] = (TextView) findViewById(R.id.created_date_text);
        textResponse[2] = (TextView) findViewById(R.id.admin_id_text);
        textResponse[3] = (TextView) findViewById(R.id.view_count_text);
    }

    private void setListeners() {                                   // request에 따른 response 함수
        new Thread(new Runnable() {
            @Override
            public void run() {
                String response = restClient.postRequest(); // response 내용 저장
                setText(response, textResponse); //파씽 함수 고고
            }
        }).start();
    }

    private void setText(final String response, final TextView[] textRes) {
        NoticeActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listview = (ListView) findViewById(R.id.noticeListView);
                ArrayList<ListVO> noitceList = new ArrayList<ListVO>();
                notice_adapter = new ListViewAdapter();

                try {

                    JSONObject json = new JSONObject(response);
                    JSONArray arr = json.getJSONArray("result");
                    //임의의 리스트를 지정하여 값을 넣어주는 틀을 만듭니다.
                    List<String> title = new ArrayList<String>();
                    List<String> created_date = new ArrayList<String>();
                    List<String> admin_id = new ArrayList<String>();
                    List<String> view_count = new ArrayList<String>();

                    JSONObject json2;

                    for (int i = 0; i < arr.length(); i++) {
                        json2 = arr.getJSONObject(i);
                        String notice_title = json2.getString(text_column[0]);
                        String notice_created_date = json2.getString(text_column[1]);
                        String notice_admin_id = json2.getString(text_column[2]);
                        String notice_view_count = json2.getString(text_column[3]);

                        title.add(notice_title);
                        created_date.add(notice_created_date);
                        admin_id.add(notice_admin_id);
                        view_count.add(notice_view_count);

                    }

                    for (int i = 0; i < arr.length(); i++) {
                        notice_adapter.addVO(title.get(i), created_date.get(i), admin_id.get(i),view_count.get(i)); //갱신도 이루어짐
                    }
                    listview.setAdapter(notice_adapter); //adapter에 추가
                    //notice_adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // SSL 통신 시 필요  -------------------------------------------------------------------------------------------------------------------------------------


    public void onClick_find(View v) { //test 검색을 누르면 화면전환(이 부분은 검색 목록이 떠야함)
        Intent findIntent = new Intent(getApplicationContext(), FindActivity.class);
        startActivity(findIntent);  //find activity 실행
        finish();
    }

    public void onClick_notice_str(View v) { //공지사항을 누르면 공지사항 home으로 이동!!
        Intent notice_strIntent = new Intent(getApplicationContext(), NoticeActivity.class);
        startActivity(notice_strIntent); //notice activity 실행
        finish();
    }
}