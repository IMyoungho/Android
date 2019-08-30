package com.example.notice.Function;


import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import com.example.notice.MainActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.notice.ListVO.ListVO;
import com.example.notice.App_DB.Adapter.ListViewAdapter;
import android.widget.ListView;
import com.example.notice.R;
import androidx.core.content.ContextCompat;



public class SslFunc {
    // JSON 데이터를 파싱합니다.(가져온 DB 정보 중 필요한 부분 파씽)
    // URLConnector로부터 받은 String이 JSON 문자열이기 때문입니다.
    public static String ParseJSON(String target, TextView[] textRes, String[] column){
        try {
            JSONObject json = new JSONObject(target);
            JSONArray arr = json.getJSONArray("result");
            String[] Title = new String[arr.length()];
            String[] Created_date = new String[arr.length()];
            String[] Admin_id = new String[arr.length()];
            String[] View_count = new String[arr.length()];

            System.out.println(arr);
            System.out.println("이것이 길이다 ="+arr.length());

            for(int i = 0; i < arr.length(); i++){
                try{
                    JSONObject jobj = arr.getJSONObject(i);
                    Title[i]=jobj.getString(column[0]);
                    Created_date[i]=jobj.getString(column[1]);
                    Admin_id[i]=jobj.getString(column[2]);
                    View_count[i]=jobj.getString(column[3]);
                }
                catch(JSONException e){
                }
            }
            for(int j=0; j<arr.length(); j++)
            {
                System.out.println(Title[j]);
                System.out.println(Created_date[j]);
                System.out.println(Admin_id[j]);
                System.out.println(View_count[j]);
            }
            return "";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
