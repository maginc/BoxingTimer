package com.magins.boxingtimer;
import android.content.Context;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ragaz on 12/19/2016.
 */

public class FirstFragment extends Fragment {
    int currentInterval = 0;
    boolean checkRun = false;
    TextView intervalCounter;
    TextView startStop;
    WorkoutCountDownTimer timer;
    RestCountDownTimer limer;

    @Override
    public void onPause() {
        super.onPause();
        if(checkRun) {
            timer.cancel();
            limer.cancel();
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container,
                false);

        intervalCounter = (TextView) rootView.findViewById(R.id.textIntervalCounter);
        startStop = (TextView) rootView.findViewById(R.id.counter);

        startStop.setText("Start");


        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);

                final int workout = sharedPreferences.getInt("workout",180000);
                final int rest = sharedPreferences.getInt("rest",60000);
                if(!checkRun) {

                    timer = new WorkoutCountDownTimer(workout, 1000);
                    limer = new RestCountDownTimer(rest, 1000);


                    checkRun = true;

                    timer.start();
                }
                else{
                    timer.cancel();
                    limer.cancel();
                    checkRun = false;
                    currentInterval=0;

                }
                startStop.setText("Start");

            }
        });

        return rootView;
    }


    /**    ////////////////////////////////////////////////////////////Timer//////////////////////////////////////////////////////////*/
    public class WorkoutCountDownTimer extends CountDownTimer {


        public WorkoutCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }



        @Override
        public void onTick(long millisUntilFinished) {

            long seconds = (millisUntilFinished/1000)%60;
            long minutes = (millisUntilFinished/(1000*60)%60);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);
            int interval = sharedPreferences.getInt("interval",3);



            intervalCounter.setText((currentInterval+1)+"/"+interval);

            startStop.setText(""+String.format("%02d:%02d", minutes,seconds));

            startStop.setTextColor(Color.parseColor("#212121"));


        }

        @Override
        public void onFinish() {

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);

            int interval = sharedPreferences.getInt("interval",3);

            MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.sound);
            mp.start();

            if(currentInterval<(interval-1)) {

                currentInterval++;
                limer.start();


            }
            else{
                startStop.setText("Done");
                // binding.button.setText("Start!");
                currentInterval=0;
                checkRun = false;
            }
        }
    }


    public class RestCountDownTimer extends CountDownTimer {

        public RestCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            long seconds = (millisUntilFinished/1000)%60;
            long minutes = (millisUntilFinished/(1000*60)%60);


            startStop.setText(""+String.format("%02d:%02d", minutes,seconds));
            startStop.setTextColor(Color.parseColor("#99cc00"));


        }

        @Override
        public void onFinish() {
            MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.sound);
            mp.start();
            timer.start();

        }
    }



}
