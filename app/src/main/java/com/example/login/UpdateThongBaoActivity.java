package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class UpdateThongBaoActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView edtTitalUpdate, edtDesUpdate;
    Button btnCancel, btnSave;

    FirebaseDatabase fDatabase;
    FirebaseAuth fAuth;
    String notiKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_thong_bao);

        toolbar = findViewById(R.id.toolbarUpdateNoti);
        edtTitalUpdate = findViewById(R.id.edtTitalDetailUpdate);
        edtDesUpdate = findViewById(R.id.edtDesDetailUpdate);

        btnCancel = findViewById(R.id.btnCancelNotification);
        btnSave = findViewById(R.id.btnSuaNotification);

        toolbar.setTitle("Cập nhật thông báo");

        fDatabase = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();
        notiKey = fAuth.getCurrentUser().getUid();

        this.fDatabase.getReference().child("thongbao").child(fAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                Intent intent = getIntent();
//                Thongbao thongbao = (Thongbao) intent.getSerializableExtra("thongbao1");
                String name = intent.getStringExtra("name");
                String content = intent.getStringExtra("content");
                edtTitalUpdate.setText(name);
                edtDesUpdate.setText(content);
            }

            public void onCancelled(DatabaseError error) {
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateThongBaoActivity.this, DetailNotificationActivity.class);
                startActivity(intent);

                Toast.makeText(UpdateThongBaoActivity.this, "OK", Toast.LENGTH_SHORT).show();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update(edtTitalUpdate.getText().toString(), edtDesUpdate.getText().toString());

//                Intent intent = new Intent(UpdateThongBaoActivity.this, DetailNotificationActivity.class);
//                startActivity(intent);
//
//                Toast.makeText(UpdateThongBaoActivity.this, "OK", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Update(String name, String content) {
        fDatabase = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();
        DatabaseReference reference = fDatabase.getReference();

        Map<String, Object> thongbao = new HashMap<>();
        thongbao.put("name", name);
        thongbao.put("content", content);

        reference.child("thongbao").child(fAuth.getUid()).setValue(thongbao)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UpdateThongBaoActivity.this, "Cập nhật thành công",
                                Toast.LENGTH_SHORT).show();
                        EditText tital = findViewById(R.id.edtTitalDetailUpdate);
                        tital.setText(name);
                        EditText des = findViewById(R.id.edtDesDetailUpdate);
                        des.setText(content);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateThongBaoActivity.this, e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}