package com.example.androidegitim.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.androidegitim.Database.SqlManager;
import com.example.androidegitim.Database.db_create;

public class LoginManager {
    Context context;
    private SQLiteDatabase sqLiteDatabase;
    private db_create dbCreate;
    SqlManager sqlManager;

    public LoginManager(Context context) {
        try {
            this.context = context;
            dbCreate = new db_create(context, 1);
            this.sqlManager = new SqlManager(context);

        } catch (Exception ex) {
        }
    }

    public String GirisYap(String userName, String password) {
        String value = "";
        sqLiteDatabase = dbCreate.getWritableDatabase();
        try {
            Cursor cursor = sqlManager.ExecuteRawQuery("select * from User where UserName = '" + userName + "' and UserPassword = '" + password + "'", null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    value = cursor.getString(cursor.getColumnIndex("UserName"));
                    break;
                }
            }
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return value;
    }
}
