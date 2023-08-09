package com.example.hrminiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    TextView txtWelcome;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navView;
    SharedPreferences sharedPref1;
    BottomNavigationView bottomNavView;
    PowerBroadcastReceiver receiver = new PowerBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    // When app starts, Register Broadcast Receiver to receive broadcast from Android
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        registerReceiver(receiver, filter);
    }

    // When app ends, unregister Broadcast Receiver to receive broadcast from Android
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    private void init()
    {
        // Get Widgets using id for each Widgets and assign it into matched Widgets variable
        // Widgets are already on heap, so I can access them by the same method.
        txtWelcome = (TextView) findViewById(R.id.txtWelcome);
        navView = (NavigationView) findViewById(R.id.nav_view);
        bottomNavView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // receive data from another context as K-V format by SharedPreference
        sharedPref1 = getSharedPreferences("login_details", Context.MODE_PRIVATE);
        txtWelcome.setText("Welcome " + sharedPref1.getString("USER_ID",null) + " !");

        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.nav_open, R.string.nav_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SetNavigationDrawer();
        SetBottomNavigation();
    }

    // Setting bottom navigation bar
    private void SetBottomNavigation(){
        bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment frag=null;

                switch (item.getItemId()){
                    case R.id.nav_bottom_search:
                        //open search fragment
                        break;
                    case R.id.nav_bottom_account:
                        //open account fragment
                        break;
                    case R.id.nav_bottom_profile:
                        frag=new ProfileFragment();
                        break;
                }
                if(frag != null)
                {
                    FragmentTransaction frgTrans = getSupportFragmentManager().beginTransaction();
                    frgTrans.replace(R.id.frame, frag);
                    frgTrans.commit();
                    return true;
                }
                return false;
            }
        });
    }

    // Setting menus for drawer.
    private void SetNavigationDrawer()
    {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment frag = null;
                int itemId = item.getItemId();
                if(itemId == R.id.nav_add_emp)
                {
                    frag=new AddNewEmpFragment();
                }
                else if(itemId==R.id.nav_delete_emp)
                {
                    frag = new DeleteEmpFragment();
                }
                else if(itemId==R.id.nav_list_emp)
                {
                    frag = new ListNewEmpFragment();
                }
                else if(itemId==R.id.nav_update_emp)
                {

                }
                
                if(frag != null)
                {
                    FragmentTransaction frgTrans = getSupportFragmentManager().beginTransaction();
                    frgTrans.replace(R.id.frame, frag);
                    frgTrans.commit();
                    mDrawerLayout.closeDrawers();
                    return true;
                }

                return false;
            }
        });
    }

    // When the hamburger button pushed, drawer appear.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}