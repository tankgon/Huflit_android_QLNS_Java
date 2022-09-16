package com.example.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.login.Model.Thongbao;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DetailNotificationActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtTitalDeatil, txtDesDetail;
    Button btnUpdate;

    FirebaseDatabase fDatabase;
    FirebaseAuth fAuth;
    String notiKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notification);

        toolbar = findViewById(R.id.toolbarUpdateNoti);
        txtTitalDeatil = findViewById(R.id.txtTitalDetalUpdate);
        txtDesDetail = findViewById(R.id.edtTitalDetailUpdate);
        btnUpdate = findViewById(R.id.btnSuaNotification);

        toolbar.setTitle("Chi tiết thông báo");

        fDatabase = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();
        notiKey = fAuth.getCurrentUser().getUid();

        this.fDatabase.getReference().child("thongbao").child(fAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                Intent intent = getIntent();
                Thongbao thongbao = (Thongbao) intent.getSerializableExtra("thongbao");
//                Thongbao thongBao = snapshot.getValue(Thongbao.class);
                txtTitalDeatil.setText(thongbao.getName());
                txtDesDetail.setText(thongbao.getContent());
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DetailNotificationActivity.this, UpdateThongBaoActivity.class);
                        intent.putExtra("name", txtTitalDeatil.getText().toString());
                        intent.putExtra("content", txtDesDetail.getText().toString());
                        startActivity(intent);

                        Toast.makeText(DetailNotificationActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            public void onCancelled(DatabaseError error) {
            }
        });
    }

}