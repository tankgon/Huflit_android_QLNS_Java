package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    ImageButton imgBack, imgGoogle, imgFacebook;
    TextView txtForgotPassword, txtSignUp;
    EditText edtEmail, edtPassword;
    Button btnLogin;

    FirebaseAuth fAuth;


    ActivityResultLauncher<Intent> mActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        String email = intent.getStringExtra("email");
                        edtEmail.setText(email);
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        imgBack = findViewById(R.id.imgBack);
        imgGoogle = findViewById(R.id.imgGoogle);
        imgFacebook = findViewById(R.id.imgFacebook);

        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        txtSignUp = findViewById(R.id.txtSignUp);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        fAuth = FirebaseAuth.getInstance();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(vie ->{
            String email = edtEmail.getText().toString();


            if (email.isEmpty()) {
                edtEmail.setError("Not null!");
                return;
            } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                edtEmail.setError("Peovide valid email");
                return;
            }
            String password = edtPassword.getText().toString();
            if (password.isEmpty()) {
                edtPassword.setError("Not null!");
                return;
            } else if (edtPassword.getText().toString().length() < 6) {
                edtPassword.setError("Minimum 6 number");
                return;
            }


            fAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(SignInActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignInActivity.this, "Account does not exist", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                mActivityLauncher.launch(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        String email = edtEmail.getText().toString();
        if (fAuth.getCurrentUser() != null && TextUtils.isEmpty(email)){
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}