package com.example.attendancechecker.main;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendancechecker.R;
import com.example.attendancechecker.main.chatbot.ChatBotActivity;
import com.example.attendancechecker.main.components.About;
import com.example.attendancechecker.main.components.GridAdapter;
import com.example.attendancechecker.main.components.SettingsActivity;
import com.example.attendancechecker.main.database.DatabaseHandler;
import com.example.attendancechecker.main.notes.NoteCreate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class AppBase extends AppCompatActivity {

    public static ArrayList<String> divisions;
    public static DatabaseHandler handler;
    public static Activity activity;
    ArrayList<String> basicFields;
    GridAdapter adapter;
    GridView gridView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mai_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        basicFields = new ArrayList<>();
        handler = new DatabaseHandler(this);
        activity = this;

        Objects.requireNonNull(getSupportActionBar()).show();
        divisions = new ArrayList<>();
        divisions.add("A COMPUTER SCIENCE");
        divisions.add("B COMPUTER SCIENCE");
        divisions.add("C COMPUTER SCIENCE");
        divisions.add("D COMPUTER SCIENCE");
        gridView = (GridView) findViewById(R.id.grid);
        basicFields.add("ATTENDANCE");
        basicFields.add("SCHEDULER");
        basicFields.add("NOTES");
        basicFields.add("PROFILE");
        adapter = new GridAdapter(this, basicFields);
        gridView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_chat_bot);
        assert fab != null;
        fab.setOnClickListener(view -> {
            Intent launchIntent = new Intent(activity, ChatBotActivity.class);
            startActivity(launchIntent);
        });
    }

    public void loadSettings(MenuItem item) {
        Intent launchIntent = new Intent(this, SettingsActivity.class);
        startActivity(launchIntent);
    }

    public void loadAbout(MenuItem item) {
        Intent launchIntent = new Intent(this, About.class);
        startActivity(launchIntent);
    }
}
