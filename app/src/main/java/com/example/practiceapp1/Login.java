package com.example.practiceapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;





public class Login extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoginbtn,createbtn;
    ProgressBar progressBar;
    FirebaseAuth fauth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail=findViewById(R.id.Email);
        mPassword=findViewById(R.id.password);
        mLoginbtn=findViewById(R.id.loginbtn);
        createbtn=findViewById(R.id.signupbtn);
        progressBar=findViewById(R.id.progressbar);
        fauth=FirebaseAuth.getInstance();

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });


        mLoginbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString().trim();
                String Password=mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(Password))
                {
                    mPassword.setError("Password is Required");
                    return;
                }
                if(Password.length()<8)
                {
                    mPassword.setError("Password must contain minimum 8 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                fauth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            mEmail.setText("");
                            mPassword.setText("");
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG);
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            intent.putExtra("User_Name",email);
                            startActivity(intent);
                        }

                        else {
                            String error = task.getException().toString().trim();

                            Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


            }
        });


    }
}