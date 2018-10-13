package com.hfad.workout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.WorkoutListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
        View fragmentContainer = findViewById(R.id.fragment_container);         // Get a reference to the frame layout that contains WorkoutDetail Fragment. This will exist if the app is being run on a large screen.
        if (fragmentContainer != null) {
            WorkoutDetailFragment details = new WorkoutDetailFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();   // Start the fragment transaction
            details.setWorkoutId(id);
            ft.replace(R.id.fragment_container, details);   // Replace the fragment
            ft.addToBackStack(null);                        // Add the replaced fragment to the back stack
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);       // Get the new and old fragments to fade in and out.
            ft.commit();        // Apply the changes.
        }
        else{
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int)id);              //If the frame layout isn't there, the app must be running on a device with a smaller screen. Start DetailActivity, passing it the ID of the workout.
            startActivity(intent);
        }
    }
}
