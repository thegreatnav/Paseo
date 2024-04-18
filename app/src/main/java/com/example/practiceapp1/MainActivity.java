package com.example.practiceapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.inputmethod.EditorInfo;
import android.widget.*;

import com.example.practiceapp1.RecentsAdapter;
import com.example.practiceapp1.TopPlacesAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practiceapp1.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;
    TextView tv4,see_all_topplaces;
    ImageView hotels,flights,chats_icon,profpic;
    String user_name;
    EditText search_destinations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recentRecycler = findViewById(R.id.recent_recycler);
        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        tv4=findViewById(R.id.textView4);
        hotels=findViewById(R.id.button_hotels);
        flights=findViewById(R.id.button_flights);
        chats_icon=findViewById(R.id.button_profile);
        profpic=findViewById(R.id.imageView);
        see_all_topplaces=findViewById(R.id.textView22);
        search_destinations=findViewById(R.id.search_destinations);
        Bundle extras = getIntent().getExtras();
        user_name=extras.getString("User_Name");
        Toast.makeText(MainActivity.this,user_name,Toast.LENGTH_SHORT).show();
        profpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, ProfileActivity.class);
                i.putExtra("User_name",user_name);
                startActivity(i);

            }
        });
        List<RecentsData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentsData("Munnar","Kerala","From $300",getString(R.string.munnar_image_url)));
        recentsDataList.add(new RecentsData("Gangtok","Sikkim","From $900",getString(R.string.sikkim_image_url)));
        recentsDataList.add(new RecentsData("Ahmedabad","Gujarat","From $500",getString(R.string.ahmedabad_image_url)));
        recentsDataList.add(new RecentsData("Chennai","Tamil Nadu","From $300",getString(R.string.chennai_image_url)));
        recentsDataList.add(new RecentsData("Bangalore","Karnataka","From $200",getString(R.string.bangalore_image_url)));
        recentsDataList.add(new RecentsData("New Delhi","NCR","From $700",getString(R.string.delhi_image_url)));

        setRecentRecycler(recentsDataList);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlacesData("New Delhi","NCR","From $700",getString(R.string.delhi_image_url)));
        topPlacesDataList.add(new TopPlacesData("Chennai","Tamil Nadu","From $300",getString(R.string.chennai_image_url)));
        topPlacesDataList.add(new TopPlacesData("Panjim","Goa","From $200 - $500",getString(R.string.goa_image_url)));
        topPlacesDataList.add(new TopPlacesData("Kolkata","West Bengal","From $500 - $800",getString(R.string.kolkata_image_url)));
        topPlacesDataList.add(new TopPlacesData("Manipal","Karnataka","From $100",getString(R.string.manipal_image_url)));

        setTopPlacesRecycler(topPlacesDataList);

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(i);
            }
        });

        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Hotels.class);
                startActivity(i);
            }
        });

        flights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Flights.class);
                startActivity(i);
            }
        });

        chats_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Chat.class);
                i.putExtra("User_name",user_name);
                startActivity(i);
            }
        });

        search_destinations.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Your action when Enter key is pressed
                    Toast.makeText(getApplicationContext(),"You are searching for : "+search_destinations.getText().toString(),Toast.LENGTH_SHORT).show();
                    return true; // Consume the event
                }
                return false; // Return false to let the system handle other keys
            }
        });

        see_all_topplaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Places.class);
                i.putExtra("User_name",user_name);
                startActivity(i);
            }
        });
    }

    private  void setRecentRecycler(List<RecentsData> recentsDataList){
        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);
    }

    private  void setTopPlacesRecycler(List<TopPlacesData> topPlacesDataList){

        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);

    }
}