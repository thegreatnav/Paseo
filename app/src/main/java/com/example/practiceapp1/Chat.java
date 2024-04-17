package com.example.practiceapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.ProgressBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Chat extends AppCompatActivity {
    DatabaseReference messagesRef;
    MessageAdapter messageAdapter;
    RecyclerView messageRecyclerView;
    Button backbtn,send_button;
    TextView editTextMessage;
    private String receiver_name = "nitishkumar@gmail.com",sender_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        backbtn=findViewById(R.id.back);
        send_button=findViewById(R.id.buttonSend);
        editTextMessage=findViewById(R.id.editTextMessage);
        messageRecyclerView=findViewById(R.id.messageRecyclerView);

        messagesRef = FirebaseDatabase.getInstance().getReference().child("chats");

        messagesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Message> messageList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Deserialize each message and add it to the messageList
                    String text = snapshot.child("text").getValue(String.class);
                    String senderId = snapshot.child("senderId").getValue(String.class);
                    String time = snapshot.child("time").getValue(String.class);
                    String receiverId = snapshot.child("receiverId").getValue(String.class);

                    // Create a Message object using the retrieved values
                    Message message = new Message(text, receiverId, senderId, time);

                    // Add the message to the list
                    messageList.add(message);
                    Log.d("ChatActivity", "Message list size: " + messageList.size());
                }
                // Initialize MessageAdapter with retrieved messages
                messageAdapter = new MessageAdapter(messageList, receiver_name);
                messageRecyclerView.setLayoutManager(new LinearLayoutManager(Chat.this));
                messageRecyclerView.setAdapter(messageAdapter);
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle potential errors here
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
            sender_name=extras.getString("User_name");
        else
            sender_name="navneethsanthosh03@gmail.com";

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Chat.this, MainActivity.class);
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
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            String formattedTime = sdf.format(new Date(timestamp));
            Message message = new Message(messageText,receiver_name,sender_name, formattedTime);
            messagesRef.push().setValue(message); // Send the message to Firebase
            editTextMessage.setText(""); // Clear the input field after sending
        }
    }

}
