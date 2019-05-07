package com.example.androidegitim.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.androidegitim.Database.SqlManager;
import com.example.androidegitim.Database.db_create;
import com.example.androidegitim.Entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserListManager {
    Context context;
    private SQLiteDatabase sqLiteDatabase;
    private db_create dbCreate;
    SqlManager sqlManager;
    UserEntity userEntity;

    public UserListManager(Context context) {
        try {
            this.context = context;
            dbCreate = new db_create(context, 1);
            this.sqlManager = new SqlManager(context);
            userEntity = new UserEntity();

        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public List<String> FillUserList() {
        List<String> userList = new ArrayList<>();
        try {
            Cursor cursor = sqlManager.ExecuteRawQuery("select * from User ", null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    userList.add(cursor.getString(cursor.getColumnIndex("UserName")) + "\n"
                            + cursor.getString(cursor.getColumnIndex("UserPassword")) + "\n"
                            + cursor.getString(cursor.getColumnIndex("Cinsiyet")) + "\n"
                            + cursor.getString(cursor.getColumnIndex("Telefon")) + "\n"
                            + cursor.getString(cursor.getColumnIndex("EventDate")) + "\n");


                }
            }

        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return userList;
    }
}
