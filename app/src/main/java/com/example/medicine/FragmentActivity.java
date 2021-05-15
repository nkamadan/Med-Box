package com.example.medicine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentActivity extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        //openFragment(new MedicinesFragment());
        Intent i = getIntent();
        username = i.getStringExtra("username");

        Bundle bundle2 = new Bundle();
        bundle2.putString("username", username);
        // set Fragmentclass Arguments
        Log.println(Log.ERROR, "Fragment Activity1 : ", username);
        MedicinesFragment fragobj3 = new MedicinesFragment();
        fragobj3.setArguments(bundle2);
        openFragment(fragobj3);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:

                        Bundle bundle1 = new Bundle();
                        bundle1.putString("username", username);
                        // set Fragmentclass Arguments
                        Log.println(Log.ERROR, "Fragment Activity2 : ", username);
                        MedicinesFragment fragobj1 = new MedicinesFragment();
                        fragobj1.setArguments(bundle1);
                        openFragment(fragobj1);
                        return true;

                    case R.id.trend:

                        //getSupportFragmentManager().beginTransaction().add(AddFragment.newInstance(username,"username"),"MyFragment").commit();
                        Bundle bundle = new Bundle();
                        bundle.putString("username", username);
                        // set Fragmentclass Arguments
                        AddFragment fragobj2 = new AddFragment();
                        fragobj2.setArguments(bundle);
                        openFragment(fragobj2);
                        return true;

                    case R.id.account:
                        openFragment(new BluetoothFragment());
                        return true;


                }


                return false;
            }
        });

    }

    void openFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

}