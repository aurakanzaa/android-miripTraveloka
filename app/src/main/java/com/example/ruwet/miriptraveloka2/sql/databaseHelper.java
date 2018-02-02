package com.example.ruwet.miriptraveloka2.sql;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.example.ruwet.miriptraveloka2.activity.R;
import com.example.ruwet.miriptraveloka2.model.DaftarRuangan;
import com.example.ruwet.miriptraveloka2.model.user;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by aura on 10/01/18.
 */
public class databaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pesantiketdong.db";
    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_NAME = "name";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_HP = "hp";
    private static final String COLUMN_USER_PASSWORD = "password";
    private SQLiteDatabase db;
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table typeroom (id_type INTEGER PRIMARY KEY AUTOINCREMENT, nama text null, foto blob null, harga integer null, fasilitas text null);";
        Log.d("Data", "onCreate" + sql);
        db.execSQL(sql);
            int foto = R.drawable.ka;
            int foto2 = R.drawable.kb;
            int foto3 = R.drawable.kc;
        sql = "INSERT INTO typeroom(nama, foto, harga,fasilitas) VALUES ('DELUXE',"+foto+",300000, 'AC | Room Service | Free Wifi | Free Parking');";
        db.execSQL(sql);
        String sql6 =  "INSERT INTO typeroom(nama, foto, harga,fasilitas) VALUES ('LUXURY',"+foto2+",450000, 'AC | Room Service | Free Wifi | Free Parking | Breakfast | Smimming Pool');";
        db.execSQL(sql6);
        String sql7 =  "INSERT INTO typeroom(nama, foto, harga, fasilitas) VALUES ('DIAMOND',"+foto3+",500000, 'AC | Room Service | Free Wifi | Free Parking | Breakfast | Smimming Pool | SPA ');";
        db.execSQL(sql7);



        String sql2 = "create table service (id_service INTEGER PRIMARY KEY AUTOINCREMENT, service text null, charge integer null, gambar blob null );";
        Log.d("Data", "onCreate"+ sql2);
        db.execSQL(sql2);
            int servis = R.drawable.sa;
            int servis2 = R.drawable.sb;
        sql2 = "INSERT INTO service(service, charge, gambar) VALUES ('laundry',50000,"+servis+");";
        db.execSQL(sql2);
        String sql8 = "INSERT INTO service(service, charge, gambar) VALUES ('breakfast',80000,"+servis2+");";
        db.execSQL(sql8);


        String sql3 = "create table user(ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT null, email TEXT null,hp text null, password TEXT null, foto blob null);";
        Log.d("Data", "onCreate" +sql3);
        db.execSQL(sql3);

//        sql3 = "INSERT INTO user(name, email, hp, password, foto) VALUES ('admin', 'admin@gmail.com', '321433', 'malang', 'admin', 'admin');";
//        db.execSQL(sql3);


        String sql4 = "create table room (id_room integer primary key autoincrement, id_type integer null, id_service integer null, foreign key(id_type) references typeroom(id_type),foreign key(id_service) references service(id_service));";
        Log.d("Data", "onCreate"+ sql3);
        db.execSQL(sql4);

        String sql5 = "create table booking (id_booking integer primary key autoincrement, ID integer null, id_room integer null, booking_date string null, checkin string null, checkout string null, foreign key(ID) references user(ID),foreign key(id_room) references room(id_room));";
        Log.d("Data", "onCreate"+ sql5);
        db.execSQL(sql5);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }
    public void addUser (user user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getNama());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put("name", user.getNama());
        values.put("hp", user.getTelp());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        //values.put(null, user.getFoto());
        db.insert(TABLE_USER, null, values);
        db.close();
    }
    public boolean checkUser (String email){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount>0){
            return true;
        }
        return false;
    }
    public boolean checkUser(String email, String password){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " +  COLUMN_USER_PASSWORD+ " = ?";
        String[] selectionArgs = { email, password};
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount > 0){
            return true;
        }
        return false;
    }

    public void Update(String email, String username) {

        ContentValues values = new ContentValues();
        values.put("name", username);
        String[] args = new String[]{email};
        db.update(TABLE_USER, values, "email=?", args);
    }

    public void UpdateHp(String email, String username) {

        ContentValues values = new ContentValues();
        values.put("hp", username);
        String[] args = new String[]{email};
        db.update(TABLE_USER, values, "email=?", args);
    }
    public void UpdateFoto(String email, byte[] foto) {

        ContentValues values = new ContentValues();
        values.put("foto", foto);
        String[] args = new String[]{email};
        db.update(TABLE_USER, values, "email=?", args);
    }

    public String lihatRuangan(String column, String nama) {
        String[] args = new String[]{nama};
        String query = "SELECT "+ column + " FROM typeroom WHERE nama = ? "  ;
        Cursor  cursor = db.rawQuery(query,args);

        cursor.moveToFirst();
        String a = cursor.getString(cursor.getColumnIndex(column));
        return a;

    }

    public String lihatBooking(String column, String idbooking) {
        String[] args = new String[]{idbooking};
        String query = "SELECT "+ column + " FROM booking WHERE id_booking = ? "  ;
        //String query = "SELECT kendaraan.tipe FROM kendaraan join user on kendaraan.id_user = user.ID where user.name = ?";
        //String query = "SELECT id_booking, ID, typeroom.nama, booking_date, checkin, checkout from booking join room on booking.id_room = room.id_room join typeroom on typeroom.id_type = room.id_type where typeroom.nama = ? ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  cursor = db.rawQuery(query,args);

        cursor.moveToFirst();
        String a = cursor.getString(cursor.getColumnIndex(column));
        return a;

    }

    public String lihatUser(String column, String email) {
        String[] args = new String[]{email};
        String query = "SELECT "+ column + " FROM user WHERE email = ? "  ;
        Cursor  cursor = db.rawQuery(query,args);

        cursor.moveToFirst();
        String a = cursor.getString(cursor.getColumnIndex(column));
        return a;
    }

    public int lihatUser2(String column, String email) {
        String[] args = new String[]{email};
        String query = "SELECT "+ column + " FROM user WHERE email = ? "  ;
        Cursor  cursor = db.rawQuery(query,args);

        cursor.moveToFirst();
        int a = cursor.getInt(cursor.getColumnIndex(column));
        return a;
    }



    public void InsertBooking(int value,int value6, String value2, String value3,String value4) {
        ContentValues values = new ContentValues();
        values.put("ID", value);
        values.put("id_room", value6);
        values.put("booking_date", value2);
        values.put("checkin", value3);
        values.put("checkout", value4);
        db.insert("booking",null, values);

    }

    public ArrayList<DaftarRuangan> getSemuaRuangan(){
        ArrayList<DaftarRuangan> ruanganList = new ArrayList<>();
        String selectQuery = "SELECT * FROM typeroom";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                DaftarRuangan dr = new
                        DaftarRuangan(cursor.getString(1),
                         cursor.getInt(3));
                ruanganList.add(dr);
            } while (cursor.moveToNext());
        }
        return ruanganList;
    }

    public byte[] lihatFoto(String email) {
        String[] args = new String[]{email};
        String query = "SELECT foto FROM user WHERE email = ? "  ;
        Cursor  cursor = db.rawQuery(query,args);

        cursor.moveToFirst();
        byte[] a = cursor.getBlob(cursor.getColumnIndex("foto"));
        return a;
    }
}