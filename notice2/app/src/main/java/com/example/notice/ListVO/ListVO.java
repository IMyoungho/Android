package com.example.notice.ListVO;

import android.graphics.drawable.Drawable;

/**
 * Created by YooJongHyeok on 2017-08-07.
 */

public class ListVO {
    String title;
    String created_date;
    String admin_id;
    String view_count;

    public ListVO() {
    }

    public ListVO(String title, String created_date, String admin_id, String view_count ){
        this.title = title;
        this.created_date = created_date;
        this.admin_id = admin_id;
        this.view_count = view_count;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getView_count() {
        return view_count;
    }

    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

}