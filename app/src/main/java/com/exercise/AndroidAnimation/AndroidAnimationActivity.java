package com.exercise.AndroidAnimation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class AndroidAnimationActivity extends Activity {
    AnimationDrawable myAnimationDrawable;

    Timer timer;
    MyTimerTask myTimerTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ImageView myAnimation = (ImageView)findViewById(R.id.myanimation);
        myAnimationDrawable
                = (AnimationDrawable)myAnimation.getDrawable();

        myAnimation.post(
                new Runnable(){

                    @Override
                    public void run() {
                        myAnimationDrawable.start();
                    }
                });

        //Calculate the total duration
        int duration = 0;
        for(int i = 0; i < myAnimationDrawable.getNumberOfFrames(); i++){
            duration += myAnimationDrawable.getDuration(i);
        }

        timer = new Timer();
        myTimerTask = new MyTimerTask();
        timer.schedule(myTimerTask, duration);
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            timer.cancel();
   /*
   runOnUiThread(new Runnable(){
    @Override
    public void run() {
     Toast.makeText(getApplicationContext(),
       "Animation Stopped",
       Toast.LENGTH_SHORT).show();
    }});
   */
            Intent intent = new Intent(
                    AndroidAnimationActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}