package com.example.practiceapp1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practiceapp1.DetailsActivity;
import com.example.practiceapp1.SearchPlacesData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchPlacesViewHolder> {

    Context context;
    List<SearchPlacesData> SearchPlacesDataList;

    public SearchResultAdapter(Context context, List<SearchPlacesData> SearchPlacesDataList) {
        this.context = context;
        this.SearchPlacesDataList = SearchPlacesDataList;
    }

    public interface OnItemClickListener {
        void onItemClick(SearchPlacesData item);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SearchPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_result_item, parent, false);
        return new SearchPlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchPlacesViewHolder holder, int position) {
        SearchPlacesData currentItem = SearchPlacesDataList.get(position);
        holder.countryName.setText(currentItem.getCountryName());
        holder.placeName.setText(currentItem.getPlaceName());
        holder.price.setText(currentItem.getPrice());
        Picasso.get().load(currentItem.getImageUrl()).into(holder.placeImage);
    }

    @Override
    public int getItemCount() {
        return SearchPlacesDataList.size();
    }

    public class SearchPlacesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView placeImage;
        TextView placeName, countryName, price;

        public SearchPlacesViewHolder(@NonNull View itemView) {
            super(itemView);
            placeImage = itemView.findViewById(R.id.place_image);
            placeName = itemView.findViewById(R.id.place_name);
            countryName = itemView.findViewById(R.id.country_name);
            price = itemView.findViewById(R.id.price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && listener != null) {
                listener.onItemClick(SearchPlacesDataList.get(position));
            }
        }
    }
}
