package com.example.ruwet.miriptraveloka2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Start extends Activity {

    private static final String TAG = "Start";


    ViewPager viewPager;
//    SliderAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Log.d(TAG, "onCreate: starting.");

        TextView textView = (TextView) findViewById(R.id.login);
        Button buttonToRegister = (Button) findViewById(R.id.register);

        buttonToRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked Register");
                Intent intent = new Intent(Start.this, com.example.ruwet.miriptraveloka2.activity.RegisterActivity.class);
                startActivity(intent);

            }

        });

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked Login");
                Intent intent = new Intent(Start.this, com.example.ruwet.miriptraveloka2.activity.LoginActivity.class);
                startActivity(intent);

            }

        });



//        viewPager = (ViewPager) findViewById(R.id.slider_image);
//        adapter = new SliderAdapter(this);
//        viewPager.setAdapter(adapter);
//        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
//        String display = preferences.getString("password", "");
//        if(display!="") {
//            Intent accoutsIntent = new Intent(Start.this, UsersActivity.class);
//            Intent intent = new Intent(Start.this, com.example.ruwet.miriptraveloka2.activity.UsersActivity.class);
//            startActivity(intent);
//            finish();
//
//
//        }
    }
}
