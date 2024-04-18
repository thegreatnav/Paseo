package com.example.practiceapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Chat extends AppCompatActivity {
    DatabaseReference messagesRef;
    RecyclerView messageRecyclerView;
    Button backbtn, send_button;
    TextView editTextMessage;
    private String receiver_name = "nitishkumar@gmail.com", sender_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        backbtn = findViewById(R.id.back);
        send_button = findViewById(R.id.buttonSend);
        editTextMessage = findViewById(R.id.editTextMessage);
        messageRecyclerView = findViewById(R.id.messageRecyclerView);

        messagesRef = FirebaseDatabase.getInstance().getReference().child("chats");

        // Setting up RecyclerView
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Query to retrieve last 50 messages
        Query query = messagesRef.limitToLast(50);

        // FirebaseRecyclerOptions
        FirebaseRecyclerOptions<Message> options =
                new FirebaseRecyclerOptions.Builder<Message>()
                        .setQuery(query, Message.class)
                        .build();

        // FirebaseRecyclerAdapter
        FirebaseRecyclerAdapter<Message, ChatHolder> adapter =
                new FirebaseRecyclerAdapter<Message, ChatHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ChatHolder holder, int position, @NonNull Message model) {
                        holder.textViewName.setText(model.getTime());
                        holder.textViewUid.setText(model.getSenderId());

                        // Check if the message is sent or received and set the appropriate text view
                        if (model.getSenderId().equals(sender_name)) {
                            holder.textViewMessage.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.sent_message_bg_color)); // Set background color for sent messages
                            holder.textViewMessage.setText(model.getText());
                        } else {
                            holder.textViewMessage.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.received_message_bg_color)); // Set background color for received messages
                            holder.textViewMessage.setText(model.getText());
                        }
                    }

                    @NonNull
                    @Override
                    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
                        return new ChatHolder(view);
                    }
                };

        // Set adapter to RecyclerView
        messageRecyclerView.setAdapter(adapter);

        // Start listening for data
        adapter.startListening();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Chat.this, MainActivity.class);
                startActivity(i);
            }
        });

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String messageText = editTextMessage.getText().toString().trim();
        if (!messageText.isEmpty()) {
            // Create a message object with the sender's name and the other user's ID
            long timestamp = System.currentTimeMillis();
            // Format the timestamp to HH:mm format
            String formattedTime = android.text.format.DateFormat.format("hh:mm a", timestamp).toString();
            Message message = new Message(messageText, receiver_name, sender_name, formattedTime);
            messagesRef.push().setValue(message); // Send the message to Firebase
            editTextMessage.setText(""); // Clear the input field after sending
        }
    }
}
