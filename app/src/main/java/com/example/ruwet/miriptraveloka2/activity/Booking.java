package com.example.ruwet.miriptraveloka2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.ruwet.miriptraveloka2.sql.databaseHelper;

public class Booking extends AppCompatActivity {

    String[] daftar;
    String[] daftar2;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    databaseHelper dbcenter;
    public static Booking ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ma = this;
        dbcenter = new databaseHelper(this);
        RefreshList();
    }
    public void RefreshList(){
        SharedPreferences preferences = getSharedPreferences("MYPREFS",MODE_PRIVATE);
        String email = preferences.getString("email","");
        String username = dbcenter.lihatUser("name",email);
        int id = dbcenter.lihatUser2("ID",email);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM booking where ID ='" +id + "'" ,null);
        daftar = new String[cursor.getCount()];
        daftar2 = new String[cursor.getCount()];


        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(0).toString();
//            daftar[cc] = cursor.getString(2).toString();

        }
        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3){
                final String selection = daftar [arg2];
                //final String selection2 = daftar2 [arg2];
                final CharSequence[] dialogitem = {"Detail Booking","Cancel Booking"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Booking.this);
                builder.setTitle("Pilihan:");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item){
                            case 0:
                                Intent i = new Intent(getApplicationContext(),
                                        DetailBooking.class);
                                i.putExtra("booking", selection);
                                startActivity(i);
                                break;

                            case 1:
                                SQLiteDatabase db = dbcenter.getReadableDatabase();
                                db.execSQL("delete from booking where id_booking = '"+selection+"' ");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
        Toast.makeText(getApplicationContext(), "Data Refreshed",
        Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
