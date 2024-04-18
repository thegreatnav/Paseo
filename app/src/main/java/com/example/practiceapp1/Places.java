package com.example.practiceapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Places extends AppCompatActivity {

    Button back_button;
    String user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        back_button=findViewById(R.id.back_button);
        Bundle extras = getIntent().getExtras();
        user_name=extras.getString("User_name");
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Places.this,MainActivity.class);
                i.putExtra("User_name",user_name);
                startActivity(i);
            }
        });
    }
}