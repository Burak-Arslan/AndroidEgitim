package com.example.androidegitim.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidegitim.Controller.LoginManager;
import com.example.androidegitim.R;

import es.dmoral.toasty.Toasty;

public class Login extends AppCompatActivity {

    TextView txtUyeOl;
    Button btnGirisYap;
    EditText edtKullaniciAdi;
    EditText edtSifre;
    LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        Events();

    }

    private void Events() {
        try {
            txtUyeOl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent carListOpen = new Intent(getApplicationContext(), SaveUser.class);
                        getApplicationContext().startActivity(carListOpen);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            btnGirisYap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String kullaniciVarMi = "";
                        kullaniciVarMi = loginManager.GirisYap(edtKullaniciAdi.getText().toString(), edtSifre.getText().toString());
                        if (kullaniciVarMi != null && kullaniciVarMi.equals("")) {
                            Intent userList = new Intent(getApplicationContext(), UserList.class);
                            getApplicationContext().startActivity(userList);
                            return;
                        } else {
                            Toasty.info(Login.this, "Kullanıcı Bulunamadı.\n Kayıt Syfasına Yönlendiriliyorsunuz.");
                            Intent saveUserOpened = new Intent(getApplicationContext(), SaveUser.class);
                            getApplicationContext().startActivity(saveUserOpened);
                        }
                    } catch (Exception ex) {
                        Toasty.error(getApplicationContext(), ex.getMessage());
                    }
                }
            });
        } catch (Exception ex) {
            Toasty.error(getApplicationContext(), ex.getMessage());
        }
    }

    private void Init() {
        try {
            loginManager = new LoginManager(getApplicationContext());
            txtUyeOl = findViewById(R.id.txtUyeOl);
            btnGirisYap = findViewById(R.id.btnGirisYap);
            edtKullaniciAdi = findViewById(R.id.edtKullaniciAdi);
            edtSifre = findViewById(R.id.edtSifre);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
