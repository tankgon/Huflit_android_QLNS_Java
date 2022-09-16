package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class SearchNhanVienActivity extends AppCompatActivity {

    EditText edtFilterNV;
    ImageView imgFilterNV;
    RecyclerView rvListFilterNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_nhan_vien);

        edtFilterNV = findViewById(R.id.edt_filterNV);
        imgFilterNV = findViewById(R.id.img_filterNV);

    }
}