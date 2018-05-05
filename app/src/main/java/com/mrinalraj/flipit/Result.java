package com.mrinalraj.flipit;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class Result extends Fragment {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private int bestEasyScore , bestHardScore;

    public Result() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_result, container, false);
        KonfettiView konfettiView = rootView.findViewById(R.id.viewKonfetti);

        pref = getActivity().getSharedPreferences("HighScore",0);
        editor= pref.edit();

        bestEasyScore = pref.getInt(Constants.EASY_HIGH_KEY,22);
        bestHardScore = pref.getInt(Constants.HARD_HIGH_KEY,32);

        Bundle b=getArguments();
        if (b.getString("Data").equals("win")){
            konfettiView.build()
                    .addColors(Color.parseColor("#fce18a"), Color.parseColor("#ff726d"), Color.parseColor("#b48def"), Color.parseColor("#f4306d"))
                    .setDirection(0.0,359.0)
                    .setSpeed(4f,7f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000)
                    .addSizes(new Size(12,2f), new Size(16, 6f))
                    .addShapes(Shape.CIRCLE,Shape.RECT)
                    .setPosition(-50f,
                            konfettiView.getWidth() + 500f, -50f, -50f)
                    .stream(400,5000L);

            new SoundPlayer(getContext()).playSound("winner.mp3");

            if (Integer.valueOf(b.get("level").toString()) == Constants.LEVEL_EASY){
                if (Integer.valueOf(b.get("Time").toString()) < bestEasyScore){
                    editor.putInt(Constants.EASY_HIGH_KEY,Integer.valueOf(b.get("Time").toString())).apply();
                    ((TextView) rootView.findViewById(R.id.newhigh)).setText("New High Score!");
                }
            }
            else if (Integer.valueOf(b.get("level").toString()) == Constants.LEVEL_HARD){
                if (Integer.valueOf(b.get("Time").toString()) < bestHardScore){
                    editor.putInt(Constants.HARD_HIGH_KEY,Integer.valueOf(b.get("Time").toString())).apply();
                    ((TextView) rootView.findViewById(R.id.newhigh)).setText("New High Score!");
                }
            }

            ((TextView)rootView.findViewById(R.id.r1)).setText("N");
            ((TextView)rootView.findViewById(R.id.r2)).setText("I");
            ((TextView)rootView.findViewById(R.id.r3)).setText("C");
            ((TextView)rootView.findViewById(R.id.r4)).setText("E");
            ((TextView) rootView.findViewById(R.id.desc1)).setText("You won!");
            ((TextView) rootView.findViewById(R.id.desc2)).setText("Nice work, you can always improve your best time");
            ((TextView) rootView.findViewById(R.id.time)).setText("Your time : "+b.get("Time").toString());
        }
        else{
            ((TextView)rootView.findViewById(R.id.r1)).setText("L");
            ((TextView)rootView.findViewById(R.id.r2)).setText("O");
            ((TextView)rootView.findViewById(R.id.r3)).setText("S");
            ((TextView)rootView.findViewById(R.id.r4)).setText("T");
            ((TextView) rootView.findViewById(R.id.desc1)).setText("Nice  try, but you lost.");
            ((TextView) rootView.findViewById(R.id.desc2)).setText("Try improving the score to win");
            ((TextView) rootView.findViewById(R.id.time)).setText("Your time : "+b.get("Time").toString());
        }
        return rootView;
    }

}
