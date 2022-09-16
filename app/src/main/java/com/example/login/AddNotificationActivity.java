package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddNotificationActivity extends AppCompatActivity {

    TextInputEditText txtInputTitalNoti, txtInputDesNoti;
    Button btnOK;

    String notiKey;
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance();

        txtInputTitalNoti = findViewById(R.id.txt_InputTitalNotification);
        txtInputDesNoti = findViewById(R.id.txt_InputDesNotification);

        btnOK = findViewById(R.id.btn_OkAddNotification);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tital = txtInputTitalNoti.getText().toString();
                String des = txtInputDesNoti.getText().toString();

                if (tital.isEmpty()) {
                    txtInputTitalNoti.setError("Not null!");
                    return;
                }
                if (des.isEmpty()) {
                    txtInputDesNoti.setError("Not null!");
                    return;
                }

                notiKey = fAuth.getCurrentUser().getUid();
                DatabaseReference databaseReference = fDatabase.getReference();

                Map<String, Object> thongbao = new HashMap<>();
                thongbao.put("name", tital);
                thongbao.put("content", des);
                databaseReference.child("thongbao").child(notiKey).setValue(thongbao)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void aVoid) {
                                Intent intent = new Intent(AddNotificationActivity.this, NotificationFragment.class);
                                setResult(Activity.RESULT_OK, intent);
                                Toast.makeText(AddNotificationActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddNotificationActivity.this, "Không thành công", Toast.LENGTH_SHORT).show();
                            }
                        });

            }

        });
    }
}