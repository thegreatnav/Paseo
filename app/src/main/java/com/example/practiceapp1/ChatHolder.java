package com.example.practiceapp1;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatHolder extends RecyclerView.ViewHolder {
    TextView textViewName;
    TextView textViewMessage;
    TextView textViewUid;

    public ChatHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewMessage = itemView.findViewById(R.id.textViewMessage);
        textViewUid = itemView.findViewById(R.id.textViewUid);
    }
}

