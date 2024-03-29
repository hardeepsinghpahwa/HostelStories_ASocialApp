package com.example.sqlliteproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class Home extends AppCompatActivity implements Feed.OnFragmentInteractionListener,Profile.OnFragmentInteractionListener{

    BottomNavigationView bottomNavigationView;
    boolean doubleBackToExitPressedOnce = false;
    Fragment fragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragment=new Feed();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        ServiceManager serviceManager = new ServiceManager(getApplicationContext());
        if (!serviceManager.isNetworkAvailable()) {
            startActivity(new Intent(Home.this,NoInternet.class));
        }

        bottomNavigationView=findViewById(R.id.bottomnavbar);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.feed:
                        fragment=new Feed();
                        loadfragment(fragment);
                        return true;

                    case R.id.profile:
                        fragment=new Profile();
                        loadfragment(fragment);
                        return true;
                }
                return false;
            }
        });

    }

    void loadfragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fadein,R.anim.fadeout);
        fragmentTransaction.replace(R.id.layout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
