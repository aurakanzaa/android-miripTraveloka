package com.example.ruwet.miriptraveloka2.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ruwet.miriptraveloka2.sql.databaseHelper;

import java.util.Calendar;

public class transaksi extends AppCompatActivity {

    private databaseHelper databaseHelper;
    EditText t1,t2,t3;
    TextView idroom;
    private int mYearIni, mMonthIni, mDayIni, mYearIni2, mMonthIni2, mDayIni2, mYearIni3, mMonthIni3, mDayIni3;
    private int sYearIni, sMonthIni, sDayIni, sYearIni2, sMonthIni2, sDayIni2, sYearIni3, sMonthIni3, sDayIni3;
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();
    Calendar C1 = Calendar.getInstance();
    Calendar C2 = Calendar.getInstance();
    Button btn;
    protected Cursor cursor;
    private final AppCompatActivity activity = transaksi.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseHelper = new databaseHelper(activity);
        //SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        //String email = preferences.getString("email", "");
        //String ruangan = databaseHelper.lihatRuangan("nama", email);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        //EditText booking = (EditText) findViewById(R.id.idbooking);

        final TextView room = (TextView) findViewById(R.id.idroom);
        room.setText(databaseHelper.lihatRuangan("id_type",getIntent().getStringExtra("typeroom")));




        sMonthIni = C.get(Calendar.MONTH);
        sDayIni = C.get(Calendar.DAY_OF_MONTH);
        sYearIni = C.get(Calendar.YEAR);
        t1 = (EditText) findViewById(R.id.date);

        sMonthIni2 = C1.get(Calendar.MONTH);
        sDayIni2 = C1.get(Calendar.DAY_OF_MONTH);
        sYearIni2 = C1.get(Calendar.YEAR);
        t2 = (EditText) findViewById(R.id.date2);

        sMonthIni3 = C2.get(Calendar.MONTH);
        sDayIni3 = C2.get(Calendar.DAY_OF_MONTH);
        sYearIni3 = C2.get(Calendar.YEAR);
        t3 = (EditText) findViewById(R.id.date3);

        databaseHelper = new databaseHelper(transaksi.this);;
        final EditText date = (EditText)findViewById(R.id.date);
        final EditText date2 = (EditText)findViewById(R.id.date2);
        final EditText date3 = (EditText)findViewById(R.id.date3);
        Button btn = (Button) findViewById(R.id.button1);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(1);
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(2);
            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(3);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("start", "onClick: Clicked Login");

                SharedPreferences preferences = getSharedPreferences("MYPREFS",MODE_PRIVATE);
                String email = preferences.getString("email","");
                int user = databaseHelper.lihatUser2("ID",email);
                String ruangan = databaseHelper.lihatRuangan("id_type",getIntent().getStringExtra("typeroom"));

                // System.out.println(email);
                databaseHelper.InsertBooking(user,Integer.parseInt(ruangan),date.getText().toString(),date2.getText().toString(),date3.getText().toString());
                Intent intent = new Intent(transaksi.this, MainActivity.class);
                startActivity(intent);

            }

        });
    }
    private void input() {
        t1.setText((mMonthIni + 1) + "-" + mDayIni + "-" + mYearIni+" ");
    }
    private void input2() {
        t2.setText((mMonthIni2 + 1) + "-" + mDayIni2 + "-" + mYearIni2+" ");
    }
    private void input3() {
        t3.setText((mMonthIni3 + 1) + "-" + mDayIni3 + "-" + mYearIni3+" ");
    }


    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYearIni = year;
                    mMonthIni = monthOfYear;
                    mDayIni = dayOfMonth;
                    input();
                }};

    private DatePickerDialog.OnDateSetListener mDateSetListener2 =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYearIni2 = year;
                    mMonthIni2 = monthOfYear;
                    mDayIni2 = dayOfMonth;
                    input2();
                }};

    private DatePickerDialog.OnDateSetListener mDateSetListener3 =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYearIni3 = year;
                    mMonthIni3 = monthOfYear;
                    mDayIni3 = dayOfMonth;
                    input3();
                }};

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1:
                return new DatePickerDialog(this, mDateSetListener, sYearIni, sMonthIni, sDayIni);
            case 2:
                return new DatePickerDialog(this, mDateSetListener2, sYearIni2, sMonthIni2, sDayIni2);
            case 3:
                return new DatePickerDialog(this, mDateSetListener3, sYearIni3, sMonthIni3, sDayIni3);
        }
        return null;
    }


}
