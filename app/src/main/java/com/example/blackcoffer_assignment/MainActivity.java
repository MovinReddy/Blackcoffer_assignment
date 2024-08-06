package com.example.blackcoffer_assignment;



import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    selectedFragment = new ExploreFragment();
                } else if (id == R.id.nav_connections) {
                    selectedFragment = new ConnectionsFragment();
                } else if (id == R.id.nav_chat) {
                    selectedFragment = new ChatsFragment();
                } else if (id == R.id.nav_contacts) {
                    selectedFragment = new ContactsFragment();
                } else if (id == R.id.nav_groups) {
                    selectedFragment = new GroupsFragment();
                } else {
                    throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.slide_in_right, R.anim.slide_out_left);
                transaction.replace(R.id.fragment_container, selectedFragment);
                transaction.commit();

                return true;


            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.fragment_container, new ExploreFragment())
                    .commit();
        }
    }
}
