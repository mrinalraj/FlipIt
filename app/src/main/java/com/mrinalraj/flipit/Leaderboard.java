package com.mrinalraj.flipit;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Leaderboard extends Fragment {

    public Leaderboard() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_leaderboard, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences(Constants.PREF_NAME,0);
        ((TextView) rootView.findViewById(R.id.easylead)).append(""+preferences.getInt(Constants.EASY_HIGH_KEY, (int) (Constants.EASY_TIME/Constants.TIMER_INTERVAL)));
        ((TextView) rootView.findViewById(R.id.hardlead)).append(""+preferences.getInt(Constants.HARD_HIGH_KEY, (int) (Constants.HARD_TIME/Constants.TIMER_INTERVAL)));
        return rootView;
    }

}
