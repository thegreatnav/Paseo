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
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements RecentsAdapter.OnItemClickListener,TopPlacesAdapter.OnItemClickListener {

    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;
    TextView see_all_recentplaces,see_all_topplaces;
    ImageView hotels,flights,chats_icon,profpic;
    String user_name;
    EditText search_destinations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recentRecycler = findViewById(R.id.recent_recycler);
        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        see_all_recentplaces=findViewById(R.id.textView4);
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
        recentsDataList.add(new RecentsData("Srinagar","India","From $200","https://img.traveltriangle.com/attachments/pictures/864127/original/Shikara_ride.jpg"));
        recentsDataList.add(new RecentsData("Leh Ladakh","India","From $300","https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Shyok_river_Ladakh.jpg/250px-Shyok_river_Ladakh.jpg"));
        recentsDataList.add(new RecentsData("Andaman","India","From $200","https://www.scubadiving-phuket.com/wp-content/uploads/2017/04/Scuba-Diving-Phuket-Andaman-Sea-Islands-Thailand.jpg"));
        recentsDataList.add(new RecentsData("Manali","India","From $300","https://upload.wikimedia.org/wikipedia/commons/thumb/0/03/Manali_City.jpg/1200px-Manali_City.jpg"));
        recentsDataList.add(new RecentsData("Agra","India","From $200","https://cdn.britannica.com/25/155325-050-79CFFB62/Taj-Mahal-Agra-India.jpg"));
        recentsDataList.add(new RecentsData("Gulmarg","India","From $300","https://upload.wikimedia.org/wikipedia/commons/8/8e/Gulmarg_-_Jannat_on_Earth.jpg"));
        recentsDataList.add(new RecentsData("Munnar","Kerala","From $300",getString(R.string.munnar_image_url)));
        recentsDataList.add(new RecentsData("Gangtok","Sikkim","From $900",getString(R.string.sikkim_image_url)));
        recentsDataList.add(new RecentsData("Ahmedabad","Gujarat","From $500",getString(R.string.ahmedabad_image_url)));
        recentsDataList.add(new RecentsData("Chennai","Tamil Nadu","From $300",getString(R.string.chennai_image_url)));
        recentsDataList.add(new RecentsData("Bangalore","Karnataka","From $200",getString(R.string.bangalore_image_url)));
        recentsDataList.add(new RecentsData("New Delhi","NCR","From $700",getString(R.string.delhi_image_url)));

        setRecentRecycler(recentsDataList);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlacesData("Srinagar","India","From $200","https://img.traveltriangle.com/attachments/pictures/864127/original/Shikara_ride.jpg"));
        topPlacesDataList.add(new TopPlacesData("Leh Ladakh","India","From $300","https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Shyok_river_Ladakh.jpg/250px-Shyok_river_Ladakh.jpg"));
        topPlacesDataList.add(new TopPlacesData("Andaman","India","From $200","https://www.scubadiving-phuket.com/wp-content/uploads/2017/04/Scuba-Diving-Phuket-Andaman-Sea-Islands-Thailand.jpg"));
        topPlacesDataList.add(new TopPlacesData("Manali","India","From $300","https://upload.wikimedia.org/wikipedia/commons/thumb/0/03/Manali_City.jpg/1200px-Manali_City.jpg"));
        topPlacesDataList.add(new TopPlacesData("Agra","India","From $200","https://cdn.britannica.com/25/155325-050-79CFFB62/Taj-Mahal-Agra-India.jpg"));
        topPlacesDataList.add(new TopPlacesData("Gulmarg","India","From $300","https://upload.wikimedia.org/wikipedia/commons/8/8e/Gulmarg_-_Jannat_on_Earth.jpg"));
        topPlacesDataList.add(new TopPlacesData("New Delhi","NCR","From $700",getString(R.string.delhi_image_url)));
        topPlacesDataList.add(new TopPlacesData("Chennai","Tamil Nadu","From $300",getString(R.string.chennai_image_url)));
        topPlacesDataList.add(new TopPlacesData("Panjim","Goa","From $200 - $500",getString(R.string.goa_image_url)));
        topPlacesDataList.add(new TopPlacesData("Kolkata","West Bengal","From $500 - $800",getString(R.string.kolkata_image_url)));
        topPlacesDataList.add(new TopPlacesData("Manipal","Karnataka","From $100",getString(R.string.manipal_image_url)));

        setTopPlacesRecycler(topPlacesDataList);

        see_all_recentplaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Places.class);
                i.putExtra("User_name",user_name);
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
                    Intent i=new Intent(MainActivity.this,Places.class);
                    i.putExtra("User_name",user_name);
                    i.putExtra("query",search_destinations.getText().toString().trim().toLowerCase());
                    startActivity(i);
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
        recentsAdapter.setOnItemClickListener(new RecentsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecentsData item) {
                if(Objects.equals(item.placeName, "Srinagar")) {
                    // Handle item click here, for example, start a new activity
                    Intent intent = new Intent(MainActivity.this, Srinagar.class);
                    // Pass any data you need to the details activity
                    intent.putExtra("placeName", item.getPlaceName());
                    intent.putExtra("countryName", item.getCountryName());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageUrl", item.getImageUrl());
                    startActivity(intent);
                }
                else if(Objects.equals(item.placeName, "Leh Ladakh")) {
                    // Handle item click here, for example, start a new activity
                    Intent intent = new Intent(MainActivity.this, Leh_Ladakh.class);
                    // Pass any data you need to the details activity
                    intent.putExtra("placeName", item.getPlaceName());
                    intent.putExtra("countryName", item.getCountryName());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageUrl", item.getImageUrl());
                    startActivity(intent);
                }
                else if(Objects.equals(item.placeName, "Gulmarg")) {
                    // Handle item click here, for example, start a new activity
                    Intent intent = new Intent(MainActivity.this, Gulmarg.class);
                    // Pass any data you need to the details activity
                    intent.putExtra("placeName", item.getPlaceName());
                    intent.putExtra("countryName", item.getCountryName());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageUrl", item.getImageUrl());
                    startActivity(intent);
                }
                else if(Objects.equals(item.placeName, "Manali")) {
                    // Handle item click here, for example, start a new activity
                    Intent intent = new Intent(MainActivity.this, Manali.class);
                    // Pass any data you need to the details activity
                    intent.putExtra("placeName", item.getPlaceName());
                    intent.putExtra("countryName", item.getCountryName());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageUrl", item.getImageUrl());
                    startActivity(intent);
                }
                else if(Objects.equals(item.placeName, "Agra")) {
                    // Handle item click here, for example, start a new activity
                    Intent intent = new Intent(MainActivity.this, Agra.class);
                    // Pass any data you need to the details activity
                    intent.putExtra("placeName", item.getPlaceName());
                    intent.putExtra("countryName", item.getCountryName());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageUrl", item.getImageUrl());
                    startActivity(intent);
                }
                else if(Objects.equals(item.placeName, "Andaman")) {
                    // Handle item click here, for example, start a new activity
                    Intent intent = new Intent(MainActivity.this, Andaman.class);
                    // Pass any data you need to the details activity
                    intent.putExtra("placeName", item.getPlaceName());
                    intent.putExtra("countryName", item.getCountryName());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageUrl", item.getImageUrl());
                    startActivity(intent);
                }
                else {
                    // Handle item click here, for example, start a new activity
                    Intent intent = new Intent(MainActivity.this, Places.class);
                    // Pass any data you need to the details activity
                    intent.putExtra("placeName", item.getPlaceName());
                    intent.putExtra("countryName", item.getCountryName());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageUrl", item.getImageUrl());
                    startActivity(intent);
                }
            }
        });

        topPlacesAdapter.setOnItemClickListener(new TopPlacesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TopPlacesData item) {
                if(Objects.equals(item.placeName, "Srinagar")) {
                    // Handle item click here, for example, start a new activity
                    Intent intent = new Intent(MainActivity.this, Srinagar.class);
                    // Pass any data you need to the details activity
                    intent.putExtra("placeName", item.getPlaceName());
                    intent.putExtra("countryName", item.getCountryName());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageUrl", item.getImageUrl());
                    startActivity(intent);
                }
                else if(Objects.equals(item.placeName, "Leh Ladakh")) {
                    // Handle item click here, for example, start a new activity
                    Intent intent = new Intent(MainActivity.this, Leh_Ladakh.class);
                    // Pass any data you need to the details activity
                    intent.putExtra("placeName", item.getPlaceName());
                    intent.putExtra("countryName", item.getCountryName());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageUrl", item.getImageUrl());
                    startActivity(intent);
                }
                else if(Objects.equals(item.placeName, "Gulmarg")) {
                    // Handle item click here, for example, start a new activity
                    Intent intent = new Intent(MainActivity.this, Gulmarg.class);
                    // Pass any data you need to the details activity
                    intent.putExtra("placeName", item.getPlaceName());
                    intent.putExtra("countryName", item.getCountryName());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageUrl", item.getImageUrl());
                    startActivity(intent);
                }
                else if(Objects.equals(item.placeName, "Manali")) {
                    // Handle item click here, for example, start a new activity
                    Intent intent = new Intent(MainActivity.this, Manali.class);
                    // Pass any data you need to the details activity
                    intent.putExtra("placeName", item.getPlaceName());
                    intent.putExtra("countryName", item.getCountryName());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageUrl", item.getImageUrl());
                    startActivity(intent);
                }
                else if(Objects.equals(item.placeName, "Agra")) {
                    // Handle item click here, for example, start a new activity
                    Intent intent = new Intent(MainActivity.this, Agra.class);
                    // Pass any data you need to the details activity
                    intent.putExtra("placeName", item.getPlaceName());
                    intent.putExtra("countryName", item.getCountryName());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageUrl", item.getImageUrl());
                    startActivity(intent);
                }
                else if(Objects.equals(item.placeName, "Andaman")) {
                    // Handle item click here, for example, start a new activity
                    Intent intent = new Intent(MainActivity.this, Andaman.class);
                    // Pass any data you need to the details activity
                    intent.putExtra("placeName", item.getPlaceName());
                    intent.putExtra("countryName", item.getCountryName());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageUrl", item.getImageUrl());
                    startActivity(intent);
                }
                else {
                    // Handle item click here, for example, start a new activity
                    Intent intent = new Intent(MainActivity.this, Places.class);
                    // Pass any data you need to the details activity
                    intent.putExtra("placeName", item.getPlaceName());
                    intent.putExtra("countryName", item.getCountryName());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageUrl", item.getImageUrl());
                    startActivity(intent);
                }
            }
        });
    }

    private void setRecentRecycler(List<RecentsData> recentsDataList){
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

    @Override
    public void onItemClick(TopPlacesData item) {
        if(Objects.equals(item.placeName, "Srinagar")) {
            // Handle item click here, for example, start a new activity
            Intent intent = new Intent(MainActivity.this, Srinagar.class);
            // Pass any data you need to the details activity
            intent.putExtra("placeName", item.getPlaceName());
            intent.putExtra("countryName", item.getCountryName());
            intent.putExtra("price", item.getPrice());
            intent.putExtra("imageUrl", item.getImageUrl());
            startActivity(intent);
        }
        else if(Objects.equals(item.placeName, "Leh Ladakh")) {
            // Handle item click here, for example, start a new activity
            Intent intent = new Intent(MainActivity.this, Leh_Ladakh.class);
            // Pass any data you need to the details activity
            intent.putExtra("placeName", item.getPlaceName());
            intent.putExtra("countryName", item.getCountryName());
            intent.putExtra("price", item.getPrice());
            intent.putExtra("imageUrl", item.getImageUrl());
            startActivity(intent);
        }
        // Your logic for TopPlacesAdapter item click
    }

    @Override
    public void onItemClick(RecentsData item) {

        if(Objects.equals(item.placeName, "Srinagar")) {
            // Handle item click here, for example, start a new activity
            Intent intent = new Intent(MainActivity.this, Srinagar.class);
            // Pass any data you need to the details activity
            intent.putExtra("placeName", item.getPlaceName());
            intent.putExtra("countryName", item.getCountryName());
            intent.putExtra("price", item.getPrice());
            intent.putExtra("imageUrl", item.getImageUrl());
            startActivity(intent);
        }
        else if(Objects.equals(item.placeName, "Leh Ladakh")) {
            // Handle item click here, for example, start a new activity
            Intent intent = new Intent(MainActivity.this, Leh_Ladakh.class);
            // Pass any data you need to the details activity
            intent.putExtra("placeName", item.getPlaceName());
            intent.putExtra("countryName", item.getCountryName());
            intent.putExtra("price", item.getPrice());
            intent.putExtra("imageUrl", item.getImageUrl());
            startActivity(intent);
        }
    }
}