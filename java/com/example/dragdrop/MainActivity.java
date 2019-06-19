package com.example.dragdrop;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mainLayout;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView basketball;


    private int xDelta;
    private int yDelta;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (RelativeLayout) findViewById(R.id.main);
        image1 =findViewById(R.id.imageView1);
        image2 =findViewById(R.id.imageView2);
        image3 =findViewById(R.id.imageView3);
        image4 =findViewById(R.id.imageView4);
        basketball =findViewById(R.id.imagebasket);


        image1.setOnTouchListener(onTouchListener());
        image2.setOnTouchListener(onTouchListener());
        image3.setOnTouchListener(onTouchListener());
        image4.setOnTouchListener(onTouchListener());
        basketball.setOnTouchListener(onTouchListener());
    }

    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                dragable(view,event);  //Method for multiple dragable imageview by passing the view and event

                mainLayout.invalidate();
                return true;
            }
        };
    }

    public void dragable(View view,MotionEvent event){

        //Method for dragging imageview

        final int x = (int) event.getRawX();
        final int y = (int) event.getRawY();

        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                        view.getLayoutParams();

                xDelta = x - lParams.leftMargin;
                yDelta = y - lParams.topMargin;
                break;

            case MotionEvent.ACTION_UP:
//                        Toast.makeText(MainActivity.this,
//                                "new location!", Toast.LENGTH_SHORT)
//                                .show();
                break;

            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                layoutParams.leftMargin = x - xDelta;
                layoutParams.topMargin = y - yDelta;
                layoutParams.rightMargin = xDelta - x;
                layoutParams.bottomMargin = yDelta - y;
                view.setLayoutParams(layoutParams);
                break;
        }
    }

}