package com.maui.monatech;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    Spinner sGender;
    Calendar myCalender = Calendar.getInstance();
    EditText myDate;
    Button btnRegistrasi;
    ImageView backHome;
    TextView toSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
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

        sGender = findViewById(R.id.s_gender);
        myDate = findViewById(R.id.edt_tglLahir);
        sGender = findViewById(R.id.s_gender);
        btnRegistrasi = findViewById(R.id.btn_registar);
        backHome = findViewById(R.id.btn_back_home);
        toSignIn = findViewById(R.id.tv_sign_in);

        // KODE YANG BERFUNGSI DALAM PEMBUATAN SPINNER (DROPDOWN JENIS KELAMIN)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.itam_gender,
                R.layout.spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sGender.setAdapter(adapter);

        // KODE YANG BERFUNGSI DALAM PEMBUATAN DATE PICKER (TANGGAL)
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR, year);
                myCalender.set(Calendar.MONTH, month);
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateDate();
            }
        };

        // MELAKUKAN AKSI MUNCUL PEMILIHAN TANGGAL KETIKA TOMBOL EDIT TEXT TANGGAL DI KLIK
        myDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this,date,myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // MELAKUKAN AKSI MUNCUL NOTIF KETIKA TOMBOL REGISTER DI KLIK
        btnRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Registrasi berhasil", Toast.LENGTH_LONG).show();
            }
        });

        // MELAKUKAN AKSI PINDAH HALAMAN KE HOME KETIKA TOMBOL DIKLIK
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buat Intent untuk berpindah ke SecondActivity
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });



        // MELAKUKAN AKSI PINDAH HALAMAN KE LOGIN KETIKA TOMBOL DIKLIK
        toSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    // KODE FUNGSI DALAM MENGATUR DESAIN PEMBUATAN FORMAT TANGGAL. CONTOHNYA BISA PAKAI 29/22/2024 atau 29-22-2024
    private void updateDate() {
        String myFormat = "MM/dd/yyyy"; // KODE DALAM MENGATUR DESAIN FORMAT NYA
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, new Locale("id", "ID"));
        myDate.setText(dateFormat.format(myCalender.getTime()));
    }

}