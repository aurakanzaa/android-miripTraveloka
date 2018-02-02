package com.example.ruwet.miriptraveloka2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.ruwet.miriptraveloka2.sql.databaseHelper;
public class EditProfile extends AppCompatActivity {

    private databaseHelper databaseHelper;
    private final AppCompatActivity activity = EditProfile.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        databaseHelper = new databaseHelper(activity);
        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        String email = preferences.getString("email", "");
        String username = databaseHelper.lihatUser("name", email);
        String hp = databaseHelper.lihatUser("hp", email);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        EditText nama = (EditText) findViewById(R.id.nama);
        EditText noHp = (EditText) findViewById(R.id.telepon);
        EditText emailText = (EditText) findViewById(R.id.email);
        Button edit = (Button) findViewById(R.id.btnprofil);

        emailText.setText(email);
        noHp.setText(hp);
        nama.setText(username);


        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("start", "onClick: Clicked Login");
                databaseHelper = new databaseHelper(activity);
                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                EditText nama = (EditText) findViewById(R.id.nama);
                EditText noHp = (EditText) findViewById(R.id.telepon);
                String email = preferences.getString("email", "");
                // System.out.println(email);

                databaseHelper.Update(email, nama.getText().toString());
                databaseHelper.UpdateHp(email, noHp.getText().toString());
                Intent intent = new Intent(EditProfile.this, MainActivity.class);
                startActivity(intent);

            }

        });


    }
}
