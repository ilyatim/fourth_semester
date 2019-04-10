package com.example.lab2;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private ViewPager viewPager;
    private static boolean isPagePressed = false;
    private static int pagePosition;
    public static final String TAG = "MainActivity";
    DataAdapter dataAdapter;
    PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById();

        if(isPagePressed)
        {
            viewPager.setCurrentItem(pagePosition);
            viewPager.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        }
        dataAdapter = new DataAdapter(this);
        pagerAdapter = new PageAdapter(this);

        viewPager.setAdapter(pagerAdapter);

        setListener();

        recyclerView.setAdapter(dataAdapter);

    }

    private void findViewById()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void setListener()
    {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int i, float v, int i1)
            {}

            @Override
            public void onPageSelected(int i)
            {
                setTitle(DataClass.civilizations.get(i).getName());
            }

            @Override
            public void onPageScrollStateChanged(int i)
            {}
        });
        dataAdapter.setOnClickListener(new DataAdapter.ClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                pagePosition = position;
                viewPager.setCurrentItem(position);
                recyclerView.setVisibility(View.GONE);
                viewPager.setVisibility(View.VISIBLE);
                setTitle(DataClass.civilizations.get(position).getName());
                isPagePressed = true;
            }
        });
    }

    private void setTitle(String title)
    {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onBackPressed()
    {
        if(!recyclerView.isShown())
        {
            recyclerView.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.GONE);
            setTitle("Technology");
            isPagePressed = false;
        }
        else
        {
            finish();
        }
    }
}
