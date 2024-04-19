package com.example.practiceapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
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
    TextView tv4;
    ImageView hotels,flights,profile,imageView;
    public String user_name;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recentRecycler = findViewById(R.id.recent_recycler);
        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        tv4=findViewById(R.id.textView4);
        hotels=findViewById(R.id.button_hotels);
        flights=findViewById(R.id.button_flights);
        profile=findViewById(R.id.button_profile);
        imageView=findViewById(R.id.imageView);
        Bundle extras = getIntent().getExtras();
        user_name=extras.getString("User_Name");
        user = user_name;
        Toast.makeText(MainActivity.this,user_name,Toast.LENGTH_SHORT).show();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, ProfileActivity.class);
                i.putExtra("User_name",user_name);
                startActivity(i);

            }
        });
        List<RecentsData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentsData("Srinagar","India","From $200",R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Leh Ladakh","India","From $300",R.drawable.recentimage2));
        recentsDataList.add(new RecentsData("Andaman","India","From $200",R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Manali","India","From $300",R.drawable.recentimage2));
        recentsDataList.add(new RecentsData("Agra","India","From $200",R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Gulmarg","India","From $300",R.drawable.recentimage2));

        setRecentRecycler(recentsDataList);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));

        setTopPlacesRecycler(topPlacesDataList);

        recentsAdapter.getItemCount();

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

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Chat.class);
                startActivity(i);
            }
        });
    }

    private  void setRecentRecycler(List<RecentsData> recentsDataList){

        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList, new RecentsAdapter.ItemClickListener(){

            @Override
            public void onItemClick(RecentsData recentsDataList) {

                //Toast.makeText(MainActivity.this,recentsDataList.getPlaceName(),Toast.LENGTH_SHORT).show();
                if(recentsDataList.getPlaceName()=="Srinagar")
                {
                    Intent intent=new Intent(MainActivity.this, Srinagar.class);
                    //intent.putExtra("Place","Srinagar");
                    intent.putExtra("user",user_name);
                    startActivity(intent);
                }
                else if(recentsDataList.getPlaceName()=="Leh Ladakh")
                {
                    Intent intent=new Intent(MainActivity.this, Leh_Ladakh.class);
                    //intent.putExtra("Place","Leh_Ladakh");
                    intent.putExtra("user",user_name);

                    startActivity(intent);
                }
                else if(recentsDataList.getPlaceName()=="Andaman")
                {
                    Intent intent=new Intent(MainActivity.this, Andaman.class);
                  //  intent.putExtra("Place","Andaman");
                    intent.putExtra("user",user_name);

                    startActivity(intent);
                }
                else if(recentsDataList.getPlaceName()=="Manali")
                {
                    Intent intent=new Intent(MainActivity.this, Manali.class);
                   // intent.putExtra("Place","Manali");
                    intent.putExtra("user",user_name);

                    startActivity(intent);
                }
                else if(recentsDataList.getPlaceName()=="Agra")
                {
                    Intent intent=new Intent(MainActivity.this, Agra.class);
                    //intent.putExtra("Place","Agra");
                    intent.putExtra("user",user_name);

                    startActivity(intent);
                }
                else if(recentsDataList.getPlaceName()=="Gulmarg")
                {
                    Toast.makeText(MainActivity.this,user_name,Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this, Gulmarg.class);
                    intent.putExtra("user",user_name);


                    startActivity(intent);
                }
            }
        });
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