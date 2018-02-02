package com.example.ruwet.miriptraveloka2.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

//import com.example.ruwet.miriptraveloka2.R;

public class MainActivity extends AppCompatActivity {

//    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment homeFragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, homeFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    DashboardFragment dashboardFragment = new DashboardFragment();
                    FragmentTransaction fragmentDashTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentDashTransaction.replace(R.id.content, dashboardFragment);
                    fragmentDashTransaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    NotificationFragment notificationFragment = new NotificationFragment();
                    FragmentTransaction fragmentNotifTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentNotifTransaction.replace(R.id.content, notificationFragment);
                    fragmentNotifTransaction.commit();
                    return true;
//                case R.id.navigation_notifications:
//                    NotificationFragment notificationFragment = new NotificationFragment();
//                    FragmentTransaction fragmentNotifTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentNotifTransaction.replace(R.id.content, notificationFragment);
//                    fragmentNotifTransaction.commit();
//                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, homeFragment);
        fragmentTransaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
