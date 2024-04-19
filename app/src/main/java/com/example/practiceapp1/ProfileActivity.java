package com.example.practiceapp1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.practiceapp1.databinding.ActivityProfileBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    Button logoutbtn;
    TextView dname,dphone,demail;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logoutbtn=findViewById(R.id.logout);
        dname=findViewById(R.id.textView19);
        dphone=findViewById(R.id.textView20);
        demail=findViewById(R.id.textView21);
        Bundle extras = getIntent().getExtras();
        String user_name=extras.getString("User_name");
        String names = user_name.replace(".com", "");
        readDb(names);


        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProfileActivity.this, Login.class);
              //  i.putExtra("Place","Gulmarg");
                startActivity(i);
            }
        });

    }
    private void  readDb(String name)
    {
        reference= FirebaseDatabase.getInstance().getReference("User");
        reference.child(name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists())
                    {
                        DataSnapshot dataSnapshot = task.getResult();
                        Toast.makeText(ProfileActivity.this, "User Exist", Toast.LENGTH_SHORT).show();
                        String Fullname,Phone,Email;

                        //User userinput = new User (Fullname,Phone,email,Password);
                        Fullname=String.valueOf(dataSnapshot.child("fullname").getValue());
                        Phone=String.valueOf(dataSnapshot.child("phone").getValue());
                        Email=String.valueOf(dataSnapshot.child("email").getValue());
                        dname.setText(Fullname);
                        demail.setText(Email);
                        dphone.setText(Phone);

                    }
                    else {
                        Toast.makeText(ProfileActivity.this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    Toast.makeText(ProfileActivity.this, "Failure", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

}