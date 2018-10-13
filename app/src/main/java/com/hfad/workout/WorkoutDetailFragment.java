package com.hfad.workout;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//import android.app.FragmentTransaction;
import android.support.v4.app.FragmentTransaction;


public class WorkoutDetailFragment extends Fragment {

    private long workoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,   //This is the onCreateView() method. It's called when Android needs the fragment's layout
                             Bundle savedInstanceState) {

            if (savedInstanceState != null) {
                workoutId = savedInstanceState.getLong("workoutId");        //Set the value of the workoutId
            } else {                                                                       // We have put the below statements inside else because then it will only run when savedInstanceState is null,because the below lines were replacing the stopwatch values with 0 and it was causing problem when we rotate the device screen.
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();      // Now this means that a brand-new StopwatchFragment is only displayed when the activity is first created.
                StopwatchFragment stopwatchFragment = new StopwatchFragment();
                ft.replace(R.id.stopwatch_container, stopwatchFragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_workout_detail, container, false);        //R.layout.fragment_workout_detail tells Android which layout the fragment uses
        }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();      // The getView() method gets the fragment's root View. We can then use this to get references to the workout title and description text views.
        if(view!=null){
            TextView title = (TextView)view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int)workoutId];
            title.setText(workout.getName());
            TextView description = (TextView)view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {//Save the value of the workoutId in the savedInstanceState Bundle before the fragment gets destroyed. We're retrieving it in the onCreateView() method.
            super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong("workoutId",workoutId);
    }

    public void setWorkoutId(long id){      // This is a setter method for the workout ID. The activity will use this method to set the value of the workout ID
        this.workoutId = id;
    }

}
