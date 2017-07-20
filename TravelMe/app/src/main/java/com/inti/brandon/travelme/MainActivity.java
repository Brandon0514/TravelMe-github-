package com.inti.brandon.travelme;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import static android.R.attr.fragment;

public class MainActivity extends AppCompatActivity {
    private boolean doubleBacktoExit = false;

    int pagenumber;
    FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment fragment = new Fragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.rss:
                    fragmentManager.beginTransaction().replace(R.id.content, new DiscoverFragment()).commit();
                    pagenumber =0;
                    return true;
                case R.id.map:
                    fragmentManager.beginTransaction().replace(R.id.content, new MapFragment()).commit();
                    pagenumber =1;
                    return true;
                case R.id.favorite:
                    fragmentManager.beginTransaction().replace(R.id.content, new FavoriteFragment()).commit();
                    pagenumber =2;
                    return true;
                case R.id.weather:
                    fragmentManager.beginTransaction().replace(R.id.content, new WeatherFragment()).commit();
                    pagenumber =3;
                    return true;
                case R.id.profile:
                    fragmentManager.beginTransaction().replace(R.id.content, new ProfileFragment()).commit();
                    pagenumber =4;
                    return true;
            }
            return false;
        }

    };

    public void restore() {
        switch (pagenumber) {
            case 0:
                fragment = new DiscoverFragment();
                fragmentManager.beginTransaction().replace(R.id.content,new DiscoverFragment()).commit();
                break;
            case 1:
                fragmentManager.beginTransaction().replace(R.id.content, new MapFragment()).commit();
                break;
            case 2:
                fragmentManager.beginTransaction().replace(R.id.content, new FavoriteFragment()).commit();
                break;
            case 3:
                fragmentManager.beginTransaction().replace(R.id.content, new WeatherFragment()).commit();
                break;
            case 4:
                fragmentManager.beginTransaction().replace(R.id.content, new ProfileFragment()).commit();
                break;
        }


    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("PageNumber",pagenumber);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager.beginTransaction().replace(R.id.content, new DiscoverFragment()).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            pagenumber = 0;
            restore();
        }
        else{
            pagenumber = savedInstanceState.getInt("PageNumber");
            restore();
        }

    }


    @Override
    public void onBackPressed(){
        if(doubleBacktoExit){
            super.onBackPressed();
            return;
        }

        this.doubleBacktoExit = true;
        Toast.makeText(this , "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBacktoExit = false;
            }
        },2000);
    }

}
