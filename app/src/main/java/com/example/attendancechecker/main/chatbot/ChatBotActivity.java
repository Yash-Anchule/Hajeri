package com.example.attendancechecker.main.chatbot;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import com.example.attendancechecker.R;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Locale;

public class ChatBotActivity extends AppCompatActivity {

    private EditText userMsgEdt;
    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";
    //creating a variable for our volley request queue.
    private RequestQueue mRequestQueue;
    //creating a variable for array list and adapter class.
    private ArrayList<MessageModal> messageModalArrayList;
    private MessageRVAdapter messageRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        //on below line we are initializing all our views.
        //creating variables for our widgets in xml file.
        RecyclerView chatsRV = findViewById(R.id.idRVChats);
        ImageButton sendMsgIB = findViewById(R.id.idIBSend);
        userMsgEdt = findViewById(R.id.idEdtMessage);
        //below line is to initialize our request queue.
        mRequestQueue = Volley.newRequestQueue(ChatBotActivity.this);
        mRequestQueue.getCache().clear();
        //creating a new array list
        messageModalArrayList = new ArrayList<>();
        //adding on click listener for send message button.
        sendMsgIB.setOnClickListener(v -> {
            //checking if the message entered by user is empty or not.
            if (userMsgEdt.getText().toString().isEmpty()) {
                //if the edit text is empty display a toast message.
                Toast.makeText(ChatBotActivity.this, "Please enter your message..", Toast.LENGTH_SHORT).show();
                return;
            }
            //calling a method to send message to our bot to get response.
            String msg=userMsgEdt.getText().toString().toLowerCase(Locale.ROOT);
            sendMessage(msg);
            if(msg.contains("profile")&& !msg.equals("how to create profile")){
                messageModalArrayList.add(new MessageModal("Did you mean : How to create profile, if yes, type 'how to create profile'", BOT_KEY));
            }
            //below line we are setting text in our edit text as empty
            userMsgEdt.setText("");

        });

        //on below line we are initialiing our adapter class and passing our array lit to it.
        messageRVAdapter = new MessageRVAdapter(messageModalArrayList, this);
        //below line we are creating a variable for our linear layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatBotActivity.this, RecyclerView.VERTICAL, false);
        //below line is to set layout manager to our recycler view.
        chatsRV.setLayoutManager(linearLayoutManager);
        //below line we are setting adapter to our recycler view.
        chatsRV.setAdapter(messageRVAdapter);
    }

    private void sendMessage(String userMsg) {
        //below line is to pass message to our array list which is entered by the user.
        messageModalArrayList.add(new MessageModal(userMsg, USER_KEY));
        messageRVAdapter.notifyDataSetChanged();
        //url for our brain
        //make sure to add mshape for uid.
//        String url = "http://api.brainshop.ai/get?bid=170912&key=gbtb2CnOL7nHgwOB&uid=[uid]&msg=" + userMsg;
        String url = "https://run.mocky.io/v3/560c65d4-17ea-4f23-8a9c-551ba24c734c";
//        System.out.println(url);
        //creating a variable for our request queue.
        RequestQueue queue = Volley.newRequestQueue(ChatBotActivity.this);
        //on below line we are making a json object request for a get request and passing our url .
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                //in on response method we are extracting data from json response and adding this response to our array list.
                String botResponse = response.getString("cnt");
//                String botResponse = response.getString(userMsg);
                System.out.println(botResponse);
                messageModalArrayList.add(new MessageModal(botResponse, BOT_KEY));
                //notifying our adapter as data changed.
                messageRVAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
                //handling error response from bot.
//                messageModalArrayList.add(new MessageModal("No response", BOT_KEY));
//                messageRVAdapter.notifyDataSetChanged();

            }

        },
                error -> {
            //error handling.
            messageModalArrayList.add(new MessageModal("Sorry no response found", BOT_KEY));
            Toast.makeText(ChatBotActivity.this, "No response from the bot..", Toast.LENGTH_SHORT).show();
        }
        );

        //at last adding json object request to our queue.
        queue.add(jsonObjectRequest);
    }
}