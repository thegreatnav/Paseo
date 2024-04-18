package com.example.practiceapp1;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatHolder extends RecyclerView.ViewHolder {
    TextView textViewName;
    TextView textViewMessage;

    LinearLayout l1;

    public ChatHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewMessage = itemView.findViewById(R.id.textViewMessage);
        l1=itemView.findViewById(R.id.l1);
    }
}

