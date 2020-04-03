package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    TextView textView;
    SeekBar seekBar;
    Boolean counterisActive=false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void resetTimer()
    {
        textView.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO!!!");
        counterisActive=false;

    }
    public void buttonClicked(View view)
    {

        if(counterisActive)
        {
resetTimer();

        }
        else
        {
            counterisActive=true;
            seekBar.setEnabled(false);
            goButton.setText("Stop!!!");


           countDownTimer=new CountDownTimer(seekBar.getProgress()*1000 +100,1000)
            {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int)millisUntilFinished/1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.hindithankyou);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }

    }

    public void updateTimer(int progress)
    {
        int minutes=progress/60;
        int seconds=progress-(minutes*60);

        String secondString=Integer.toString(seconds);
        if(seconds <= 9)
        {
            secondString="0"+secondString;
        }

        textView.setText(Integer.toString(minutes)+":"+ secondString);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.seekBar);
        textView=findViewById(R.id.textView);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        goButton=findViewById(R.id.button3);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
