package com.example.androidegitim.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.androidegitim.Database.db_create;

public class SaveUserManager {
    Context context;
    private SQLiteDatabase sqLiteDatabase;
    private db_create dbCreate;

    public SaveUserManager(Context context) {
        try {
            this.context = context;
            dbCreate = new db_create(context, 1);
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void UserInsert(String kullaniciAdi, String sifre, String telefonNumarasi, String cinsiyet) {
        try {
            sqLiteDatabase = dbCreate.getWritableDatabase();
            ContentValues cv1 = new ContentValues();
            cv1.put("UserName", kullaniciAdi);
            cv1.put("UserPassword", sifre);
            cv1.put("Cinsiyet", cinsiyet);
            cv1.put("Telefon", telefonNumarasi);
            sqLiteDatabase.insertOrThrow("User", null, cv1);
            Toast.makeText(context, "Kullanıcı Kayıt Edildi", Toast.LENGTH_SHORT).show();

        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (sqLiteDatabase != null)
                dbCreate.close();
        }
    }
}
