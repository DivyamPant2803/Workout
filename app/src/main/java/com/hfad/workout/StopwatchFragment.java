package com.hfad.workout;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class StopwatchFragment extends Fragment implements View.OnClickListener {           //View.OnClickListener turns the fragment into an OnClickListener, since we can't use android:ONClick in the fragment layout as it only works for activities.

    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;
        @Override
        public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

            if (savedInstanceState != null) {
                seconds = savedInstanceState.getInt("seconds");
                running = savedInstanceState.getBoolean("running");
                wasRunning = savedInstanceState.getBoolean("wasRunning");
                if (wasRunning) {
                    running = true;
                }
            }
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        runTimer(layout);

        Button startButton = (Button) layout.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);                                   // This attaches a listener to each of the Buttons.
        Button stopButton = (Button) layout.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        Button resetButton = (Button) layout.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);

        return layout;
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(wasRunning){
            running = true;
        }
    }

    @Override
    public void onClick(View v) {               // v is the View the user clicked on.
        switch (v.getId()){                     // Check which View was clicked.
            case R.id.start_button:
                onClickStart(v);                // If the Start button was clicked, call the onClickStart() method.
                break;
            case R.id.stop_button:
                onClickStop(v);                 // If the Stop button was clicked, call the onClickStop() method.
                break;
            case R.id.reset_button:
                onClickReset(v);                // If the Reset button was clicked, call the onClickReset() method.
                break;
        }
    }

    public void onClickStart(View view){
        running =true;
    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }

    private void runTimer(View view){
        final TextView timeView = (TextView) view.findViewById(R.id.time_view);
        final Handler handler = new Handler();      // Putting the code in a Handler means it can run in the background thread.
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);                             // Display the number of the seconds that have passed in the stopwatch.
                if(running){
                    seconds++;                                      // If the stopwatch is running, increment the number of seconds.
                }
                handler.postDelayed(this,1000);         // Run the handler code every second.
            }
        });
    }
}
