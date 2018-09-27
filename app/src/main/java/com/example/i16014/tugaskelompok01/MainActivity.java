package com.example.i16014.tugaskelompok01;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener{

    protected Button startBtn,finishBtn;
    protected ImageView imageView;
    protected Canvas mCanvas;
    protected GestureDetector mDetector;
    protected MyGestureListener listener;
    protected Paint paint;

    protected boolean isStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.startBtn = this.findViewById(R.id.startBtn);
        this.finishBtn  = this.findViewById(R.id.finishBtn);
        this.imageView = this.findViewById(R.id.imageView);

        this.isStarted = false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==this.startBtn.getId()){
            if(!isStarted){
                Bitmap bitmap = Bitmap.createBitmap(this.imageView.getWidth(),this.imageView.getHeight(), Bitmap.Config.ARGB_8888);
                this.imageView.setImageBitmap(bitmap);
                this.mCanvas  = new Canvas(bitmap);
                this.paint = new Paint();
            }
        }
    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }
        }

}
