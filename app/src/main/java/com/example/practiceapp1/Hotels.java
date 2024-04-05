package com.example.practiceapp1;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.practiceapp1.databinding.ActivityHotelsBinding;

public class Hotels extends AppCompatActivity {


    Button logoutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        logoutbtn=findViewById(R.id.back);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Hotels.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
