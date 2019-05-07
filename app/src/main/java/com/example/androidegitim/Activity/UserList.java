package com.example.androidegitim.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidegitim.Controller.UserListManager;
import com.example.androidegitim.Database.SqlManager;
import com.example.androidegitim.Database.db_create;
import com.example.androidegitim.Entity.UserEntity;
import com.example.androidegitim.R;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class UserList extends AppCompatActivity {


    ListView listUserList;
    List<String> userList;
    private db_create dbCreate;
    SqlManager sqlManager;
    UserListManager userListManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        Init();
        FillList();
    }

    private void FillList() {
        try {
            userList = new ArrayList<String>();

            userList = userListManager.FillUserList();


            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    userList );

            listUserList.setAdapter(arrayAdapter);
        } catch (Exception ex) {
            Toasty.error(getApplicationContext(), ex.getMessage());
        }
    }

    private void Init() {
        try {
            userListManager = new UserListManager(getApplicationContext());
            listUserList = findViewById(R.id.listUserList);
        } catch (Exception ex) {
            Toasty.error(getApplicationContext(), ex.getMessage());
        }
    }
}
