package com.maui.monatech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    TextView toSignUp;
    ImageView backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // BERFUNGSI MEMBERI BATAS TAMPILAN ANATARA STATUS BAR DAN NAVIGATION BAR
        View decorView = getWindow().getDecorView();
        decorView.setOnApplyWindowInsetsListener((view, insets) -> {
            int insetTop = insets.getSystemWindowInsetTop();
            int insetBottom = insets.getSystemWindowInsetBottom();
            view.setPadding(0, insetTop, 0, insetBottom);
            return insets.consumeSystemWindowInsets();
        });

        btnLogin = findViewById(R.id.btn_login);
        toSignUp = findViewById(R.id.tv_sign_up);

        // MELAKUKAN AKSI MUNCUL NOTIF KETIKA TOMBOL LOGIN DI KLIK
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Login berhasil", Toast.LENGTH_LONG).show();
            }
        });

        // MELAKUKAN AKSI PINDAH HALAMAN KE REGISTER KETIKA TOMBOL DIKLIK
        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        backHome = findViewById(R.id.btn_back_home);

        // MELAKUKAN AKSI PINDAH HALAMAN KE HOME KETIKA TOMBOL DIKLIK
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}