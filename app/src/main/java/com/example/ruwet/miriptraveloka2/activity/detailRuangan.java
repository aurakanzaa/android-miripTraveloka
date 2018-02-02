package com.example.ruwet.miriptraveloka2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ruwet.miriptraveloka2.sql.databaseHelper;

import static com.example.ruwet.miriptraveloka2.activity.R.id.addressTxt;
import static com.example.ruwet.miriptraveloka2.activity.R.id.img;
import static com.example.ruwet.miriptraveloka2.activity.R.id.workshop;

public class detailRuangan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseHelper  dh = new databaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ruangan);
        TextView Textnama = (TextView) findViewById(R.id.workshop);
        TextView Textalamat = (TextView) findViewById(R.id.addressTxt);
        TextView Textharga = (TextView) findViewById(R.id.openDayTxt);
        LinearLayout image = (LinearLayout) findViewById(img);
        Button button = (Button)findViewById(R.id.booking);
        TextView myText = (TextView) findViewById(R.id.Ruangan);
        myText.setText(getIntent().getStringExtra("ruangan "));
        final String nama = getIntent().getStringExtra("ruangan");
        String img = dh.lihatRuangan("foto",nama);
        String fasilitas = dh.lihatRuangan("fasilitas",nama);
        String harga = dh.lihatRuangan("harga",nama);
        image.setBackgroundResource(Integer.parseInt(img));
        Textalamat.setText(fasilitas);
        Textharga.setText(harga);
        Textnama.setText(nama);



        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detailRuangan.this,transaksi.class );

                intent.putExtra("typeroom",nama);
                startActivity(intent);

            }

        });
    }
}
