package com.mrinalraj.flipit;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class Result extends Fragment {

    private OnFragmentInteractionListener mListener;

    public Result() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_result, container, false);
        KonfettiView konfettiView = rootView.findViewById(R.id.viewKonfetti);

        Bundle b=getArguments();
        if (b.getString("Data").equals("win")){
            konfettiView.build()
                    .addColors(Color.YELLOW,Color.RED,Color.MAGENTA,Color.blue(8),Color.green(12))
                    .setDirection(0.0,359.0)
                    .setSpeed(4f,7f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000)
                    .addSizes(new Size(12,2f), new Size(16, 6f))
                    .addShapes(Shape.CIRCLE,Shape.RECT)
                    .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                    .stream(400,500L);

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


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
