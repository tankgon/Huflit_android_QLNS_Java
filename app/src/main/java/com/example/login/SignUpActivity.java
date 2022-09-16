package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    ImageButton imgBack, imgGoogle, imgFacebook;
    TextView txtSignIn;
    EditText edtUsername, edtEmail, edtPassword;
    Button btnSignUp;

    String userID;
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance();

        imgBack = findViewById(R.id.imgBack);
        imgGoogle = findViewById(R.id.imgGoogle);
        imgFacebook = findViewById(R.id.imgFacebook);

        txtSignIn = findViewById(R.id.txtSignIn);

        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        setTitle("Sign Up");

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(vie ->{
            String username = edtUsername.getText().toString();
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();

            if (email.isEmpty()) {
                edtEmail.setError("Not null!");
                return;
            }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                edtEmail.setError("Peovide valid email");
                return;
            }

            if (username.isEmpty()) {
                edtUsername.setError("Not null!");
                return;
            }

            if (password.isEmpty()) {
                edtPassword.setError("Not null!");
                return;
            } else if (edtPassword.getText().toString().length() < 6) {
                edtPassword.setError("Minimum 6 number");
                return;
            }



            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        userID = fAuth.getCurrentUser().getUid();
                        DatabaseReference databaseReference = fDatabase.getReference();

                        Map<String,Object> user = new HashMap<>();
                        user.put("username",username);
                        user.put("email",email);

                        databaseReference.child("users").child(userID).setValue(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                        intent.putExtra("email", email);
                                        setResult(Activity.RESULT_OK, intent);
                                        Toast.makeText(SignUpActivity.this, "OKE, Successful account", Toast.LENGTH_SHORT).show();
                                        finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUpActivity.this, "Account already exists", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpActivity.this, "Account creation failed", Toast.LENGTH_SHORT).show();

                    }
                });
        });
    }
}