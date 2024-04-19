package com.example.practiceapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Gulmarg extends AppCompatActivity {
    ImageView img;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gulmarg);
        img = findViewById(R.id.imageView11);
        Bundle extras = getIntent().getExtras();
        user=extras.getString("user");
        Toast.makeText(Gulmarg.this,user,Toast.LENGTH_SHORT);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Gulmarg.this, Review.class);
                intent.putExtra("Place","Gulmarg");
                intent.putExtra("User",user);
                startActivity(intent);
            }
        });


    }
}