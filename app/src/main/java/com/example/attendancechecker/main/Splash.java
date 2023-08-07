package com.example.attendancechecker.main;//package com.example.attendancechecker.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.attendancechecker.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Thread thread = new Thread(){



            public void run(){
                try {
                    sleep(5000);
                }
                catch(Exception e){

                }
                finally{
                    Intent  intent = new Intent(Splash.this , AppBase.class);
                    startActivity(intent);
                    finish();
                }
            }
        };thread.start();
    }


}