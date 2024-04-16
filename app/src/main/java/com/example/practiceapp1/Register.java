package com.example.practiceapp1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG="TAG";
    EditText mFullname,mEmail,mPassword,mPhone;
    Button mRegisterBn;
    TextView mloginbtn;
    ProgressBar mprogressBar;

    FirebaseFirestore fstore;
    String userId;
    FirebaseAuth fauth;

    FirebaseDatabase db;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFullname=findViewById(R.id.fullname);
        mEmail=findViewById(R.id.email);
        mPhone=findViewById(R.id.number);
        mPassword=findViewById(R.id.password);
        mRegisterBn=findViewById(R.id.register);
        mloginbtn=findViewById(R.id.createText);
        mprogressBar=findViewById(R.id.progressbar);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

       /* if(fauth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }*/

        mRegisterBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=mEmail.getText().toString().trim();
                final String Password=mPassword.getText().toString().trim();
                final String Fullname=mFullname.getText().toString().trim();
                final String Phone=mPhone.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(Password))
                {
                    mPassword.setError("Password is required");
                    return;
                }
                if(TextUtils.isEmpty(Fullname))
                {
                    mFullname.setError("Fullname is required");
                    return;
                }
                if(TextUtils.isEmpty(Phone))
                {
                    mEmail.setError("Phone Number is required");
                    return;
                }
                if(Password.length()<8)
                {
                    mPassword.setError("Password must contain minimum of 8 characters");
                    return;
                }
                mprogressBar.setVisibility(View.VISIBLE);
                fauth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser fuser=fauth.getCurrentUser();
                            String em=email;
                            String modifiedString = em.replace(".com", "");
                            User userinput = new User (Fullname,Phone,email,Password);
                            db = FirebaseDatabase.getInstance();
                            reference = db.getReference("User");
                            reference.child(modifiedString).setValue(userinput).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getApplicationContext(),"DATA ADDED",Toast.LENGTH_SHORT).show();

                                }
                            });

                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(),"Register Succesfull",Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"OnFAilure Email not sent ");
                                }
                            });
                            Toast.makeText(getApplicationContext(),"User Created",Toast.LENGTH_SHORT).show();
                            userId=fauth.getCurrentUser().getUid();
                            DocumentReference documentReference =fstore.collection("user").document(userId);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",Fullname);
                            user.put("email",email);
                            user.put("Phone",Phone);
                            user.put("Password",Password);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onSuccess: User profile is created :");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onFailure: User profile not created :");

                                }
                            });
                            mPassword.setText("");
                            mEmail.setText("");
                            mFullname.setText("");
                            mPhone.setText("");
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            intent.putExtra("User_Name",email);
                            startActivity(intent);


                        }
                        else
                        {
                            Toast.makeText(Register.this,"Error" ,Toast.LENGTH_SHORT).show();
                            mprogressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        mloginbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}