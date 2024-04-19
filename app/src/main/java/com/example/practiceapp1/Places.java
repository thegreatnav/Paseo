package com.example.practiceapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Places extends AppCompatActivity implements SearchResultAdapter.OnItemClickListener{

    Button back_button;
    String user_name,query;
    RecyclerView recycler1;
    SearchResultAdapter searchResultAdapter,filteredAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        back_button=findViewById(R.id.back_button);
        recycler1=findViewById(R.id.recyclerview1);

        List<SearchPlacesData> SearchDataList = new ArrayList<>();
        SearchDataList.add(new SearchPlacesData("Munnar","Kerala","From $300",getString(R.string.munnar_image_url)));
        SearchDataList.add(new SearchPlacesData("Gangtok","Sikkim","From $900",getString(R.string.sikkim_image_url)));
        SearchDataList.add(new SearchPlacesData("Ahmedabad","Gujarat","From $500",getString(R.string.ahmedabad_image_url)));
        SearchDataList.add(new SearchPlacesData("Chennai","Tamil Nadu","From $300",getString(R.string.chennai_image_url)));
        SearchDataList.add(new SearchPlacesData("Bangalore","Karnataka","From $200",getString(R.string.bangalore_image_url)));
        SearchDataList.add(new SearchPlacesData("New Delhi","NCR","From $700",getString(R.string.delhi_image_url)));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycler1.setLayoutManager(layoutManager);
        searchResultAdapter = new SearchResultAdapter(this, SearchDataList);
        recycler1.setAdapter(searchResultAdapter);


        Bundle extras = getIntent().getExtras();
        user_name=extras.getString("User_name");
        query = extras.getString("query");

        if (query != null) {
            List<SearchPlacesData> filteredList = new ArrayList<>();
            for (SearchPlacesData data : SearchDataList) {
                if (data.getPlaceName().toLowerCase().contains(query) || data.getCountryName().toLowerCase().contains(query)) {
                    filteredList.add(data);
                }
            }
            filteredAdapter = new SearchResultAdapter(this, filteredList);
            recycler1.setAdapter(filteredAdapter);
            filteredAdapter.setOnItemClickListener(this);
            // Query exists, perform search and update UI
            Toast.makeText(this, "Query provided : "+query, Toast.LENGTH_SHORT).show();
        } else {
            searchResultAdapter = new SearchResultAdapter(this, SearchDataList);
            recycler1.setAdapter(searchResultAdapter);
            searchResultAdapter.setOnItemClickListener(this);
            // Query does not exist, handle accordingly (e.g., show an error message)
            Toast.makeText(this, "No search query provided", Toast.LENGTH_SHORT).show();
        }
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Places.this,MainActivity.class);
                i.putExtra("User_name",user_name);
                startActivity(i);
            }
        });


    }
    @Override
    public void onItemClick(SearchPlacesData item) {
        // Handle item click here, for example, start a new activity
        Intent intent = new Intent(Places.this, DetailsActivity.class);
        // Pass any data you need to the details activity
        intent.putExtra("placeName", item.getPlaceName());
        intent.putExtra("countryName", item.getCountryName());
        intent.putExtra("price", item.getPrice());
        intent.putExtra("imageUrl", item.getImageUrl());
        startActivity(intent);
    }
}