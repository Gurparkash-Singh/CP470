package com.example.androidassignments;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    // Class variables
    private ListView chatListView;
    private EditText messageEditText;
    private Button sendButton;
    private ArrayList<String> chatMessages;
    private ChatAdapter messageAdapter;
    private ChatDatabaseHelper dbHelper;
    private SQLiteDatabase dbWrite;
    private SQLiteDatabase dbRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        // Set up the Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Chat");
        }

        // Initialize variables using findViewById
        chatListView = findViewById(R.id.chatListView);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);

        // Initialize chat messages list and set up the adapter
        chatMessages = new ArrayList<>();
        messageAdapter = new ChatAdapter(this);
        chatListView.setAdapter(messageAdapter);

        // Open Database
        dbHelper = new ChatDatabaseHelper(this);
        dbRead = dbHelper.getReadableDatabase();
        dbWrite = dbHelper.getWritableDatabase();
        String select = "SELECT * FROM " + ChatDatabaseHelper.TABLE_NAME + ";";

        // Read DB
        Cursor cursor = dbWrite.query(
                ChatDatabaseHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        Log.i("ChatWindow", "Cursor’s  column count = " + cursor.getColumnCount());

        for (int i = 0; i < cursor.getColumnCount(); i++)
        {
            String logString = cursor.getColumnName(i);
            Log.i("ChatWindow", logString);
        }

        while(cursor.moveToNext())
        {
            int index = cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE);

            String message = "No message";
            if (index > 0)
            {
                message = cursor.getString(index);
            }

            String logString = "SQL MESSAGE:" + message;
            Log.i("ChatWindow", logString);

            chatMessages.add(message);
        }

        // Close Cursor
        cursor.close();


        // Set onClickListener for the Send button
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText field
                String message = messageEditText.getText().toString().trim();
                if (!message.isEmpty()) {
                    // Add message to the chat messages list
                    ContentValues values = new ContentValues();
                    values.put(ChatDatabaseHelper.KEY_MESSAGE, message);
                    dbWrite.insert(ChatDatabaseHelper.TABLE_NAME, null, values);

                    chatMessages.add(message);
                    messageAdapter.notifyDataSetChanged();

                    // Clear the EditText field
                    messageEditText.setText("");
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // End the current activity and return to the parent activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String lastMessage;

    public boolean sendMessage(String message) {
        if (message == null || message.isEmpty()) {
            return false;
        }
        // Logic to send the message
        lastMessage = message;
        return true;
    }

    public void receiveMessage(String message) {
        // Logic to handle incoming messages
        lastMessage = message;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    // Inner class ChatAdapter extending ArrayAdapter<String>
    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        @Override
        public int getCount() {
            return chatMessages.size();
        }

        @Override
        public String getItem(int position) {
            return chatMessages.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;

            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }

            // Get the TextView which holds the string message
            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getItem(position));

            return result;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}