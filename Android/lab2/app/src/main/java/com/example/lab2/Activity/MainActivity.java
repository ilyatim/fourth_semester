package com.example.lab2.Activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lab2.Adapter.DataAdapter;
import com.example.lab2.DataClass;
import com.example.lab2.InternetConnection;
import com.example.lab2.Adapter.PageAdapter;
import com.example.lab2.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;  //переменная типа recycleView
    private ViewPager viewPager;    //переменная типа viewPager
    private static boolean isPagePressed = false;   //проверка нажат ли элемент viewPager
    private static int pagePosition;    //текущая страница в viewPager
    private DataAdapter dataAdapter;    //адаптер для recycle view
    private PagerAdapter pagerAdapter;  //адаптер для view pager

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById();

        if(isPagePressed)   //если пользователель открыл конкретную технологию, recycle view - исчезает, view pager - появляется
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

    private void findViewById() //поиск элементов по их id
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void setListener()  //прикрепление listenerОВ
    {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int i, float v, int i1)
            {}

            @Override
            public void onPageSelected(int i)
            {
                setTitle(DataClass.get(i).getName());
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
                /*if(InternetConnection.isOnline(MainActivity.this))    //если отсутсвует интернет, нельзя открыть view pager
                {*/
                    pagePosition = position;
                    viewPager.setCurrentItem(position);
                    recyclerView.setVisibility(View.GONE);
                    viewPager.setVisibility(View.VISIBLE);
                    setTitle(DataClass.get(position).getName());
                    isPagePressed = true;
                /*}
                else
                {
                    InternetConnection.showToast(MainActivity.this);    //вывести сообщение об отсутсвие интернета
                }*/
            }
        });
    }

    private void setTitle(String title) //изменение навзвания в action bar
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
        if(!recyclerView.isShown()) //если открыт view pager, возвращается к recycle view, в ином случае, закрывается программа
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
