package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddNhanVienActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imgAddAvata;
    EditText edtAddHoTen, edtAddEmail, edtAddSDT, edtAddNgaySinh, edtAddGioiTinh;
    Button btnAddNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nhan_vien);

        toolbar = findViewById(R.id.toolbarThongTinNV);
        imgAddAvata = findViewById(R.id.img_AddAvata);

        edtAddHoTen = findViewById(R.id.edtHoTen);
        edtAddGioiTinh = findViewById(R.id.edtAddGioiTinh);
        edtAddNgaySinh = findViewById(R.id.edtNgaySinh);
        edtAddEmail = findViewById(R.id.edtEmail);
        edtAddSDT = findViewById(R.id.edtSDT);

        btnAddNV = findViewById(R.id.btnUpdateNhanVien);
    }
}