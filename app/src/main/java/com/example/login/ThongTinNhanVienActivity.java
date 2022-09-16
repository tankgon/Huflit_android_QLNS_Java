package com.example.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.login.Model.Nhanvien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThongTinNhanVienActivity extends AppCompatActivity {
    EditText edtHoTen, edtGioiTinh, edtNgaySinh, edtPhongBan, edtChucVu, edtEmail, edtSDT, edtDiaChi;
    Button btnUpdate, btnDelete;
    Toolbar toolbar;

//    FirebaseDatabase fDatabase;
//    DatabaseReference dThongbao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_nhan_vien);

        edtHoTen = findViewById(R.id.edtHoTen);
        edtGioiTinh = findViewById(R.id.edtGioiTinh);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtPhongBan = findViewById(R.id.edtPhongBanlNV);
        edtChucVu = findViewById(R.id.edtChucVuNV);
        edtEmail = findViewById(R.id.edtEmailNV);
        edtSDT = findViewById(R.id.edtSDT);
        edtDiaChi = findViewById(R.id.edtDiaChi);

        btnUpdate = findViewById(R.id.btnUpdateNhanVien);
//        btnDelete = findViewById(R.id.btnXoaNhanVien);
        toolbar = findViewById(R.id.toolbarThongTinNV);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("nhanvien");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Nhanvien nhanvien = snapshot.getValue(Nhanvien.class);
                edtHoTen.setText(nhanvien.getName());
                edtGioiTinh.setText(nhanvien.getGioitinh());
                edtNgaySinh.setText(nhanvien.getNamsinh());

                edtPhongBan.setText(nhanvien.getPhongban());
                edtChucVu.setText(nhanvien.getChucvu());

                edtEmail.setText(nhanvien.getEmail());
                edtSDT.setText(nhanvien.getSdt());
                edtDiaChi.setText(nhanvien.getDiachi());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}