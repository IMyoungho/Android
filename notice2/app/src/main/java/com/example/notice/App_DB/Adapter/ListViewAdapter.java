package com.example.notice.App_DB.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.notice.ListVO.ListVO;
import com.example.notice.R;
import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListVO> listVO = new ArrayList<ListVO>();

    public ListViewAdapter() {

    }

    @Override
    public int getCount() {
        return listVO.size();
    }

    // ** 이 부분에서 리스트뷰에 데이터를 넣어줌 **
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //postion = ListView의 위치      /   첫번째면 position = 0
        final int pos = position;
        final Context context = parent.getContext();


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.notice_custom, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title_text);
        TextView created_date = (TextView) convertView.findViewById(R.id.created_date_text);
        TextView admin_id= (TextView) convertView.findViewById(R.id.admin_id_text);
        TextView view_count = (TextView) convertView.findViewById(R.id.view_count_text);
        ListVO listViewItem = listVO.get(position);


        // 아이템 내 각 위젯에 데이터 반영
        title.setText(listViewItem.getTitle());
        created_date.setText(listViewItem.getCreated_date());
        admin_id.setText(listViewItem.getAdmin_id());
        view_count.setText(listViewItem.getView_count());


        //리스트뷰 클릭 이벤트
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, (pos + 1) + "번째 리스트가 클릭되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public Object getItem(int position) {
        return listVO.get(position);
    }

    // 데이터값 넣어줌
    public void addVO(String title, String created_date, String admin_id, String view_count) {
        ListVO item = new ListVO();
        item.setTitle(title);
        item.setCreated_date(created_date);
        item.setAdmin_id(admin_id);
        item.setView_count(view_count);
        listVO.add(item);
    }
}