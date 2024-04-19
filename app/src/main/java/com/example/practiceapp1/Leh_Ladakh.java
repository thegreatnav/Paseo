package com.example.practiceapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Leh_Ladakh extends AppCompatActivity {

    ImageView img;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leh_ladakh);
        img = findViewById(R.id.imageView11);
        Bundle extras = getIntent().getExtras();
        user=extras.getString("user");
        Toast.makeText(Leh_Ladakh.this,user,Toast.LENGTH_SHORT);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Leh_Ladakh.this, Review.class);
                intent.putExtra("Place","Leh_Ladakh");
                intent.putExtra("User",user);
                startActivity(intent);
            }
        });


    }
}