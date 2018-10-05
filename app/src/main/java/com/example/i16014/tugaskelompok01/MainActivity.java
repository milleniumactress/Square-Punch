package com.example.i16014.tugaskelompok01;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener{

    protected Button startBtn,finishBtn;
    protected TextView scoreTextView, timerTextView;
    protected ImageView imageView;
    protected Canvas mCanvas;
    protected GestureDetector mDetector;
    protected MyGestureListener listener;
    protected Paint paint;

    protected Presenter presenter;
    protected CountDownTimer ctTimer;
    protected long timeLeftMillis = 60000;

    protected boolean isStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.startBtn = this.findViewById(R.id.start_btn);
        this.finishBtn  = this.findViewById(R.id.finish_btn);
        this.imageView = this.findViewById(R.id.image_view);
        this.scoreTextView = this.findViewById(R.id.score_textView);
        this.timerTextView = this.findViewById(R.id.timer);

        this.startBtn.setOnClickListener(this);


        this.listener = new MyGestureListener();
        this.mDetector = new GestureDetector(this,this.listener);

        this.isStarted = false;

        this.presenter = new Presenter();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==this.startBtn.getId()){
            if(!this.isStarted){
                this.isStarted = true;
                Bitmap bitmap = Bitmap.createBitmap(this.imageView.getWidth(),this.imageView.getHeight(), Bitmap.Config.ARGB_8888);
                this.imageView.setImageBitmap(bitmap);
                this.mCanvas  = new Canvas(bitmap);
                this.paint = new Paint();
                paint.setColor(ResourcesCompat.getColor(getResources(),R.color.colorPrimaryDark,null));
                this.presenter.generateShape(this.imageView.getWidth(),this.imageView.getHeight());
                this.startCountDown();
            }
            else{
                this.stopCountDown();
                this.startCountDown();
                this.mCanvas.drawColor(Color.WHITE);
                this.presenter.generateShape(this.imageView.getWidth(),this.imageView.getHeight());
            }
            for(int i=0;i<this.presenter.shapeArr.shapesArray.size();i++){
                this.mCanvas.drawRect(this.presenter.shapeArr.shapesArray.get(i),this.paint);
            }
            Log.d("msg",this.presenter.getNoOfShapes()+"");
            this.presenter.clearShapes();
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
            presenter.addScore();
            scoreTextView.setText(presenter.score.recentScore+"");



            for(int i=0;i<presenter.shapeArr.getNoOfShapes();i++){
                Rect r = presenter.shapeArr.shapesArray.get(i);
                if(r.contains((int)e.getX()-200,(int)e.getY()-200)){
                    presenter.addScore();
                    scoreTextView.setText(presenter.score.recentScore+"");
                }
            }
            return false;
        }

    }

    public void startCountDown(){
        this.ctTimer = new CountDownTimer(this.timeLeftMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int mins = (int) millisUntilFinished / 60000;
                int secs = (int) millisUntilFinished / 1000;
                timerTextView.setText("0" + mins + " : " + secs);
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    public void stopCountDown(){
        this.ctTimer.cancel();
    }

}
