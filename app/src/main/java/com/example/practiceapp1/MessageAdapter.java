package com.example.practiceapp1;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<Message> messageList;
    private String currentUserId;
    private Context context;
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    public MessageAdapter(List<Message> messageList, String currentUserId, Context context) {
        this.messageList = messageList;
        this.currentUserId = currentUserId;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = layoutInflater.inflate(R.layout.sent_message_item, parent, false);
            return new SentMessageHolder(view);
        } else {
            view = layoutInflater.inflate(R.layout.recieved_message_item, parent, false);
            return new ReceivedMessageHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messageList.get(position);
        if (holder.getItemViewType() == VIEW_TYPE_MESSAGE_SENT) {
            SentMessageHolder sentMessageHolder = (SentMessageHolder) holder;
            sentMessageHolder.messageText.setText(message.getText());
        } else {
            ReceivedMessageHolder receivedMessageHolder = (ReceivedMessageHolder) holder;
            receivedMessageHolder.messageText.setText(message.getText());
        }
    }

    @Override
    public int getItemCount()
    {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        Message message = messageList.get(position);
        if (message.getSenderId() != null && message.getSenderId().equals(currentUserId)) {
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    static class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText;

        SentMessageHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.textViewSentMessage);
        }
    }

    static class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.textViewReceivedMessage);
        }
    }
}
