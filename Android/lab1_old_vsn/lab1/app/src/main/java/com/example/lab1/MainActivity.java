package com.example.lab1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
    private final static String TAG = "MainActivity";
    private ImageView imageView;
    private Animation animation1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView3);
        animation1 = AnimationUtils.loadAnimation(this, R.anim.mytrans);
        setAnimation(imageView);
        start();
        //alternativeStart();

    }

    public void start()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if(!isChangingConfigurations())
                {
                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                    finish();
                }
            }
        }, 3000);
    }
    public void alternativeStart()
    {
        Thread thread = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(3000);
                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                    finish();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
    public void setAnimation(View view)
    {
        imageView.startAnimation(animation1);
    }
}
