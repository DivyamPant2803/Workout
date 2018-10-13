package com.hfad.workout;


import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WorkoutListFragment extends ListFragment {

    static interface WorkoutListListener{
        void itemClicked(long id);
    };

    private WorkoutListListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] names = new String[Workout.workouts.length];

        for(int i=0;i<names.length;i++){
            names[i] = Workout.workouts[i].getName();   // Create a String array of the workout names
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(                        // Create an array adapter
                inflater.getContext(),android.R.layout.simple_list_item_1,names);       // inflater.getContext() gets the context from the layout inflater

        setListAdapter(adapter);       // Bind the array adapter to the list view

        return super.onCreateView(inflater,container,savedInstanceState);   // Calling the superclass onCreateView() method gives you the default layout for the ListFragment.
    }

    @Override
    public void onAttach(Activity activity) {           //This is called when the fragment gets attached to the activity
        super.onAttach(activity);
        this.listener = (WorkoutListListener)activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if(listener != null){
            listener.itemClicked(id);       // Tell the listener when an item in the ListView is clicked.
        }
    }
}
