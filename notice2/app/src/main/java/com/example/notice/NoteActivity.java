//package com.example.notice;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.SearchView;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.w3c.dom.Text;
//
//public class NoteActivity extends AppCompatActivity {
//    SearchView schnoteStr;
//    Button button;
//    TextView editText;
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_note); // note 강의노트 화면 띄우기
//        schnoteStr = (SearchView)findViewById(R.id.find_text); //search 문자열을 입력할 수 있는 공간
//
//        editText = (TextView)findViewById(R.id.dbtext);
//        button = (Button) findViewById(R.id.dbbtn);
//
//        // 버튼에 클릭 리스너를 달았습니다. 클릭 시 URLConnector가 작동할 것입니다.
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                URLConnector url = new URLConnector();
//                url.start();
//                try {
//                    url.join();
//                }
//                catch(Exception e){
//                    e.printStackTrace();
//                }
//                String result = url.getTemp();
//                System.out.println(ParseJSON(result));
//                ParseJSON(result);
//            }
//        });
//    }
//    // JSON 데이터를 파싱합니다.
//    // URLConnector로부터 받은 String이 JSON 문자열이기 때문입니다.
//    public String ParseJSON(String target){
//
//        try {
//            JSONObject json = new JSONObject(target);
//
//            JSONArray arr = json.getJSONArray("result");
//            for(int i = 0; i < arr.length(); i++){
//                JSONObject json2 = arr.getJSONObject(i);
//                editText.setText(json2.getString("title"));
//            }
//            return "";
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//    public void onClick_find(View v){ //test 검색을 누르면 화면전환(이 부분은 검색 목록이 떠야함)
//        Intent findIntent = new Intent(getApplicationContext(), FindActivity.class);
//        startActivity(findIntent);  //find activity 실행
//        finish();
//    }
//
//    public void onClick_note_str(View v){ //강의노트를 누르면 공지사항 강의노트 home으로 이동!!
//        Intent note_strIntent = new Intent(getApplicationContext(),NoteActivity.class);
//        startActivity(note_strIntent); //note activity 실행
//        finish();
//    }
//}
