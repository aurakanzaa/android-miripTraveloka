package com.example.ruwet.miriptraveloka2.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ruwet.miriptraveloka2.sql.databaseHelper;

public class DetailBooking extends AppCompatActivity {

    private databaseHelper databaseHelper;
    private final AppCompatActivity activity = DetailBooking.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseHelper = new databaseHelper(activity);
        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        String email = preferences.getString("email", "");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_booking);

        final TextView book = (TextView) findViewById(R.id.textView1);
        book.setText(databaseHelper.lihatBooking("id_booking",getIntent().getStringExtra("booking")));

        final TextView room = (TextView) findViewById(R.id.textView2);
        room.setText(databaseHelper.lihatBooking("id_room",getIntent().getStringExtra("booking")));

        final TextView user = (TextView) findViewById(R.id.textView3);
        user.setText(databaseHelper.lihatBooking("ID",getIntent().getStringExtra("booking")));

        final TextView date = (TextView) findViewById(R.id.textView4);
        date.setText(databaseHelper.lihatBooking("booking_date",getIntent().getStringExtra("booking")));

        final TextView date2 = (TextView) findViewById(R.id.textView5);
        date2.setText(databaseHelper.lihatBooking("checkin",getIntent().getStringExtra("booking")));

        final TextView date3 = (TextView) findViewById(R.id.textView6);
        date3.setText(databaseHelper.lihatBooking("checkout",getIntent().getStringExtra("booking")));

    }
}
