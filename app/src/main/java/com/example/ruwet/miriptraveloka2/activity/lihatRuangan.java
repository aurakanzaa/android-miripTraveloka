package com.example.ruwet.miriptraveloka2.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruwet.miriptraveloka2.sql.databaseHelper;

public class lihatRuangan extends AppCompatActivity {

    protected Cursor cursor;
    databaseHelper dbHelper;
    Button ton2;
    TextView text1, text2, text3, text4, text5;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_ruangan);

        dbHelper = new databaseHelper(this);
        text1 = (TextView) findViewById(R.id.platNomorTxt);
        text2 = (TextView) findViewById(R.id.namaTxt);
        text3 = (TextView) findViewById(R.id.brandTxt);
        text4 = (TextView) findViewById(R.id.tipeTxt);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        image = (ImageView) findViewById(R.id.img);





        cursor = db.rawQuery("SELECT * FROM kendaraan WHERE plat = '" +
                getIntent().getStringExtra("plat") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);

            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());

            Bitmap bm = BitmapFactory.decodeByteArray(cursor.getBlob(4), 0 ,cursor.getBlob(4).length);
            image.setImageBitmap(bm);

        }
        ton2 = (Button) findViewById(R.id.button2);
        ton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
