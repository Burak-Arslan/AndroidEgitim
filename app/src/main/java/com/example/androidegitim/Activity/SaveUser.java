package com.example.androidegitim.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.androidegitim.Controller.SaveUserManager;
import com.example.androidegitim.R;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class SaveUser extends AppCompatActivity {


    EditText edtKullaniciAdi;
    EditText edtSifre;
    EditText edtTelefonNumarasi;
    Spinner spnCinsiyet;
    Button btnKayit;
    SaveUserManager saveUserManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_user);
        Init();
        FillSpinner();
        Events();
    }

    private void Events() {
        try {
            btnKayit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String kullaniciAdi = edtKullaniciAdi.getText().toString();
                        String sifre = edtSifre.getText().toString();
                        String telefonNumarasi = edtTelefonNumarasi.getText().toString();
                        String cinsiyet = spnCinsiyet.getSelectedItem().toString();
                        saveUserManager.UserInsert(kullaniciAdi, sifre, telefonNumarasi, cinsiyet);
                    } catch (Exception ex) {
                        Toasty.error(getApplicationContext(), ex.getMessage());
                    }
                }
            });
        } catch (Exception ex) {
            Toasty.error(getApplicationContext(), ex.getMessage());
        }
    }

    private void FillSpinner() {
        try {
            List<String> spinnerArray = new ArrayList<String>();
            spinnerArray.add("Erkek");
            spinnerArray.add("Kadın");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner sItems = findViewById(R.id.spnCinsiyet);
            sItems.setAdapter(adapter);
        } catch (Exception ex) {
            Toasty.error(getApplicationContext(), ex.getMessage());
        }
    }

    private void Init() {
        try {
            saveUserManager = new SaveUserManager(getApplicationContext());
            edtKullaniciAdi = findViewById(R.id.edtKullaniciAdi);
            edtSifre = findViewById(R.id.edtSifre);
            edtTelefonNumarasi = findViewById(R.id.edtTelefon);
            spnCinsiyet = findViewById(R.id.spnCinsiyet);
            btnKayit = findViewById(R.id.btnKayit);
        } catch (Exception ex) {
            Toasty.error(getApplicationContext(), ex.getMessage());
        }
    }
}
