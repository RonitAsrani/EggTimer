package com.example.asrani.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity

{
    //MediaPlayer mp;
    Button button;
    TextView textview;
    SeekBar seekbar;
    public void updatetimer( int secondsleft)
    {
        int minutes =(int) secondsleft/60;
        int seconds = secondsleft - minutes * 60;
        // at the end of the timer, it appears to be as 10:0, but we need 10:00, hence the below code
        String secondsString = Integer.toString(seconds);

        if (secondsString == "0")
        {
            secondsString = "00";
        }
        textview.setText(Integer.toString(minutes) + ":" + Integer.toString(seconds));
    }


    public void controltimer(View view)
    {
// button = (Button) findViewById(R.id.button);

         textview = (TextView) findViewById(R.id.timer);
         seekbar = (SeekBar) findViewById(R.id.seekBar) ;
       int value = seekbar.getProgress();
       // String str = textview.getText().toString();
        new CountDownTimer(Long.valueOf(value) * 1000, 1000)
        //new CountDownTimer(Long.parseLong(seekbar.getProgress())
        //new CountDownTimer(Long.parseLong(str) * 1000,1000)
        {
            public void onTick(long millisUntilFinished) {
                updatetimer((int)millisUntilFinished/1000);
                button = (Button) findViewById(R.id.button);
                button.setEnabled(false);
            }

            public void onFinish() {
                button.setEnabled(true);
                textview.setText("0:00");
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                mp.start();

            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar);
         textview = (TextView) findViewById(R.id.timer);
        //set the time in seconds
        seekbar.setMax(600);
        seekbar.setProgress(30);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updatetimer(progress);


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
