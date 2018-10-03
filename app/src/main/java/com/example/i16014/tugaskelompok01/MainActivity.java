package com.example.i16014.tugaskelompok01;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener{

    protected Button startBtn,finishBtn;
    protected TextView scoreTextView;
    protected ImageView imageView;
    protected Canvas mCanvas;
    protected GestureDetector mDetector;
    protected MyGestureListener listener;
    protected Paint paint;

    protected Presenter presenter;

    protected boolean isStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.startBtn = this.findViewById(R.id.start_btn);
        this.finishBtn  = this.findViewById(R.id.finish_btn);
        this.imageView = this.findViewById(R.id.image_view);
        this.scoreTextView = this.findViewById(R.id.score_textView);

        this.startBtn.setOnClickListener(this);


        this.listener = new MyGestureListener();
        this.mDetector = new GestureDetector(this,this.listener);

        this.isStarted = false;

        this.presenter = new Presenter();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==this.startBtn.getId()){
            if(!isStarted){
                Bitmap bitmap = Bitmap.createBitmap(this.imageView.getWidth(),this.imageView.getHeight(), Bitmap.Config.ARGB_8888);
                this.imageView.setImageBitmap(bitmap);
                this.mCanvas  = new Canvas(bitmap);
                this.paint = new Paint();
                paint.setColor(ResourcesCompat.getColor(getResources(),R.color.colorPrimaryDark,null));
                this.presenter.generateShape(this.imageView.getWidth(),this.imageView.getHeight());
                Rect r = new Rect(200,150,250,200);
                }
            else{
                this.presenter.generateShape(this.imageView.getWidth(),this.imageView.getHeight());
            }
            for(int i=0;i<this.presenter.shapeArr.shapesArray.size();i++){
                this.mCanvas.drawRect(this.presenter.shapeArr.shapesArray.get(i),this.paint);
            }
            this.imageView.invalidate();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.mDetector.onTouchEvent(motionEvent);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            for(int i=0;i<presenter.shapeArr.shapesArray.size();i++){
                Rect r = presenter.shapeArr.shapesArray.get(i);
                if(r.contains((int)e.getX(),(int)e.getY())){
                    presenter.addScore();
                    scoreTextView.setText(presenter.score.recentScore);
                }
            }
            return false;
        }

    }

}
