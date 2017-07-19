package com.magins.boxingtimer;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by ragaz on 12/19/2016.
 */

public class SecondFragment extends Fragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_second, container,
                false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("setting",Context.MODE_PRIVATE);


        final TextView textWorkout = (TextView)rootView.findViewById(R.id.textWorkout);
        final TextView textRest = (TextView)rootView.findViewById(R.id.textRest);
        final TextView textIntervals = (TextView)rootView.findViewById(R.id.textIntervals);

        Button buttonDecreaseWorkout = (Button) rootView.findViewById(R.id.buttonDecreaseWorkout);
        Button buttonIncreaseWorkout = (Button)rootView.findViewById(R.id.buttonIncreaseWorkout);
        Button buttonIncreaseRest = (Button)rootView.findViewById(R.id.buttonIncreaseRest);
        Button buttonDecreaseRest = (Button)rootView.findViewById(R.id.buttonDecreaseRest);
        Button buttonIncreaseIntervals = (Button)rootView.findViewById(R.id.buttonIncreaseIntervals);
        Button buttonDecreaseIntervals = (Button)rootView.findViewById(R.id.buttonDecreaseIntervals);



        final int workoutValue = sharedPreferences.getInt("workout",180000);
        long seconds = (workoutValue/1000)%60;
        long minutes = (workoutValue/(1000*60)%60);
        textWorkout.setText(""+String.format("%02d:%02d", minutes,seconds));



        final int restText = sharedPreferences.getInt("rest",60000);
        long second = (restText/1000)%60;
        long minute = (restText/(1000*60)%60);
        textRest.setText(""+String.format("%02d:%02d", minute,second));

        final int intervalTex = sharedPreferences.getInt("interval",3);

        textIntervals.setText(""+intervalTex);

/**        /////////////////////////////////////////Button Decrease Workout time//////////////////////////////////////////*/

        buttonDecreaseWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                int time = sharedPreferences.getInt("workout",30000);

                if (time>=20000) {
                    time = time - 10000;

                    long seconds = (time / 1000) % 60;
                    long minutes = (time / (1000 * 60) % 60);
                    textWorkout.setText(""+String.format("%02d:%02d", minutes,seconds));

                    editor.putInt("workout", time);
                    editor.apply();
                }

                else {
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "Can not be zero!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                }
            }
        });

/** //////////////////////////////////////////////Button Increase workout time///////////////////////////////////////////////////*/


        buttonIncreaseWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                int time = sharedPreferences.getInt("workout",30000);


                time = time + 10000;

                long seconds = (time / 1000) % 60;
                long minutes = (time / (1000 * 60) % 60);
                textWorkout.setText(""+String.format("%02d:%02d", minutes,seconds));

                editor.putInt("workout", time);
                editor.apply();

            }
        });
/** ///////////////////////////////////////////Button Increase rest////////////////////////////////////////////////////////////*/

        buttonIncreaseRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                int time = sharedPreferences.getInt("rest",30000);


                time = time + 10000;

                long seconds = (time / 1000) % 60;
                long minutes = (time / (1000 * 60) % 60);
                textRest.setText(""+String.format("%02d:%02d", minutes,seconds));

                editor.putInt("rest", time);
                editor.apply();

            }
        });


/**        ///////////////////////////////////////////Button Decrease rest///////////////////////////////////////////////////////*/
        buttonDecreaseRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int time = sharedPreferences.getInt("rest",30000);

                if (time>=20000) {
                    time = time - 10000;

                    long seconds = (time / 1000) % 60;
                    long minutes = (time / (1000 * 60) % 60);
                    textRest.setText(""+String.format("%02d:%02d", minutes,seconds));

                    editor.putInt("rest", time);
                    editor.apply();
                }

                else {
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "Can not be zero!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }

        });

/**        //////////////////////////////////////////Button increase intervals////////////////////////////////////////////////////////*/
        buttonIncreaseIntervals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                int time = sharedPreferences.getInt("interval",3);

                time = time+1;
                textIntervals.setText(""+time);

                editor.putInt("interval",time);
                editor.apply();
            }
        });


/**        ////////////////////////////////////////Button decrease interval count////////////////////////////////////////////////////*/

        buttonDecreaseIntervals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                int time = sharedPreferences.getInt("interval",3);

                if (time>1) {

                    time = time - 1;
                    textIntervals.setText("" + time);

                    editor.putInt("interval", time);
                    editor.apply();
                }

                else{

                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "Can not be zero!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
            }
        });





        return rootView;
    }
}