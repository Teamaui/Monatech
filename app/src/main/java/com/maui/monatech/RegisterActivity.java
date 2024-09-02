package com.maui.monatech;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.itam_gender,
                R.layout.spinner_item
        );

        // Mengatur layout dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Menghubungkan adapter ke spinner
        sGender.setAdapter(adapter);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR, year);
                myCalender.set(Calendar.MONTH, month);
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateDate();
            }
        };

        myDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this,date,myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Registrasi berhasil", Toast.LENGTH_LONG).show();
            }
        });

        // Temukan tombol berdasarkan ID-nya
        ImageView backHome = findViewById(R.id.backHome);

        // Setel OnClickListener untuk tombol
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buat Intent untuk berpindah ke SecondActivity
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        TextView toSignIn = findViewById(R.id.tv_signIn);

        // Setel OnClickListener untuk tombol
        toSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buat Intent untuk berpindah ke SecondActivity
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateDate() {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, new Locale("id", "ID"));
        myDate.setText(dateFormat.format(myCalender.getTime()));
    }

}