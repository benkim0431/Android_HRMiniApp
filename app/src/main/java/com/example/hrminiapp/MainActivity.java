package com.example.hrminiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    Button btnNext, btnSkip;
    ViewsSliderAdapter mAdapter;
    Intent intent1;
    int[] layouts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init()
    {
        // assign slider screens on array
        layouts = new int[]{
                R.layout.slide_screen_1,
                R.layout.slide_screen_2,
                R.layout.slide_screen_3
        };
        viewPager2 = (ViewPager2) findViewById(R.id.view_pager);

        mAdapter = new ViewsSliderAdapter(layouts);
        viewPager2.setAdapter(mAdapter);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnSkip = (Button) findViewById(R.id.btn_skip);
//        viewPager2.registerOnPageChangeCallback(pageChangeCallback);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchLoginScreen();
            }
        });

        // When click submit button viwepager's current screen will be changed.
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if(current < layouts.length){
                    //move to next screen
                    viewPager2.setCurrentItem(current);
                    if(current == layouts.length-1)
                    {
                        btnNext.setText(R.string.cont);
                    }
                }else {
                    launchLoginScreen();
                }
            }
            private int getItem(int i){
                return viewPager2.getCurrentItem() + i;
            }


        });

    }
    private void launchLoginScreen(){
        intent1 = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent1);
    }
}