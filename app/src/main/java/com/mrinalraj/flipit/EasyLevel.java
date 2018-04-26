package com.mrinalraj.flipit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrinalraj.flipit.Adapters.EasyLevelAdapter;

import java.util.ArrayList;


public class EasyLevel extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView EasyLevelRecyclerView;
    public ArrayList<Integer> cards;

    public EasyLevel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_easy_level, container, false);

        EasyLevelRecyclerView = rootView.findViewById(R.id.easylevelview);

        RecyclerView.LayoutManager lm = new GridLayoutManager(getContext(),3, LinearLayoutManager.VERTICAL,false);

        EasyLevelRecyclerView.setLayoutManager(lm);

        cards = new ArrayList<>();

        //card shuffle here

        cards.add(R.drawable.card2);
        cards.add(R.drawable.card3);
        cards.add(R.drawable.card2);
        cards.add(R.drawable.card4);
        cards.add(R.drawable.card4);
        cards.add(R.drawable.card3);
        cards.add(R.drawable.card2);
        cards.add(R.drawable.card3);
        cards.add(R.drawable.card2);
        cards.add(R.drawable.card4);
        cards.add(R.drawable.card4);
        cards.add(R.drawable.card3);

        EasyLevelRecyclerView.setAdapter(new EasyLevelAdapter(cards));
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
