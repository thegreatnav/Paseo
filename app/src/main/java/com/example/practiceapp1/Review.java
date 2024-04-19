package com.example.practiceapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Review extends AppCompatActivity {

    EditText ed;
    FirebaseAuth fauth;
    Button submit;

    FirebaseDatabase db;
    DatabaseReference reference;
    String user_name,place,review;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        submit=findViewById(R.id.button2);
        ed=findViewById(R.id.editText2);
        Bundle extras = getIntent().getExtras();
        user_name= extras.getString("User");
        place= extras.getString("Place");
        String modifiedString = user_name.replace(".com", "");
      // Toast.makeText(Review.this,user_name,Toast.LENGTH_SHORT).show();
        Toast.makeText(Review.this,place,Toast.LENGTH_SHORT).show();

       // FirebaseUser fuser=fauth.getCurrentUser();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review = ed.getText().toString().trim();
                db = FirebaseDatabase.getInstance();
                reference = db.getReference(place);
                Toast.makeText(getApplicationContext(),review,Toast.LENGTH_SHORT).show();

                reference.child(modifiedString).setValue(review).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"DATA ADDED",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }
}