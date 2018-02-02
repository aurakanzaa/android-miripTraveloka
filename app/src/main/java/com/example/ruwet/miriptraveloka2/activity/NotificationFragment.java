package com.example.ruwet.miriptraveloka2.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruwet.miriptraveloka2.activity.EditProfile;
import com.example.ruwet.miriptraveloka2.activity.R;
import com.example.ruwet.miriptraveloka2.activity.Booking;
import com.example.ruwet.miriptraveloka2.activity.Start;
import com.example.ruwet.miriptraveloka2.sql.databaseHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.example.ruwet.miriptraveloka2.activity.R.id.booking;
import static com.example.ruwet.miriptraveloka2.activity.R.id.em;
import static com.example.ruwet.miriptraveloka2.activity.R.id.nama;

//import com.example.ruwet.miriptraveloka2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private databaseHelper databaseHelper;
    private static final int CAMERA_REQUEST = 1888;
    final int REQUEST_CODE_GALLERY = 999;
    ImageButton image;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        databaseHelper = new databaseHelper(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);
        Button logout = (Button) rootView.findViewById(R.id.btnlogout);
        //ImageView setting = (ImageView) rootView.findViewById(R.id.settings);
        TextView textView = (TextView) rootView.findViewById(R.id.nama);
        TextView alamat = (TextView)rootView.findViewById(em);
        image = (ImageButton) rootView.findViewById(R.id.foto);
        final SharedPreferences preferences =this.getActivity().getSharedPreferences("MYPREFS",MODE_PRIVATE);
        String email = preferences.getString("email", "");
        String nama = databaseHelper.lihatUser("name",email);
        String em = databaseHelper.lihatUser("email",email);
        textView.setText(nama);
        alamat.setText(em);
        byte[] foto = databaseHelper.lihatFoto(email);
        if(foto != null) {
            Bitmap bm = BitmapFactory.decodeByteArray(foto, 0, foto.length);
            image.setImageBitmap(bm);
        }

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                preferences.edit().remove("email").commit();
                preferences.edit().remove("password").commit();
                Log.d(TAG, "onClick: Clicked Register");
                Intent intent = new Intent(getActivity(), Start.class);
                startActivity(intent);

            }

        });

        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final CharSequence[] dialogitem = {"Camera", "Gallery"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                                break;
                            case 1 :
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(intent, REQUEST_CODE_GALLERY);
                        }
                    }
                });
                builder.create().show();

            }

        });



        Button btn = (Button) rootView.findViewById(R.id.btnedit);
            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfile.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    public void takeImageFromCamera(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CAMERA_REQUEST &&resultCode == Activity.RESULT_OK) {
            final SharedPreferences preferences =this.getActivity().getSharedPreferences("MYPREFS",MODE_PRIVATE);
            String email = preferences.getString("email", "");
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            image.setImageBitmap(imageBitmap);
            databaseHelper.UpdateFoto(email,imageViewToByte(image));

        }

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == Activity.RESULT_OK && data != null) {
            final SharedPreferences preferences =this.getActivity().getSharedPreferences("MYPREFS",MODE_PRIVATE);
            String email = preferences.getString("email", "");
            try {

                Uri uri = data.getData();


                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                bitmap = getResizedBitmap(bitmap, 250);
                image.setImageBitmap(bitmap);
                databaseHelper.UpdateFoto(email,imageViewToByte(image));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

}
