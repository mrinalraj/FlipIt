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
import java.util.Random;


public class HardLevel extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView HardLevelRecyclerView;
    public ArrayList<Integer> cards;
    public int CARDS[] = {
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6,
            R.drawable.card7,
            R.drawable.card8,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6,
            R.drawable.card7,
            R.drawable.card8
    };

    public HardLevel() {
        // Required empty public constructor
    }

    public void shuffle(int cards[], int n){
        Random random = new Random();

        for (int i=0;i<n;i++){
            int r = random.nextInt(n-i);

            int temp = cards[r];
            cards[r] = cards[i];
            cards[i] = temp;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hard_level, container, false);

        HardLevelRecyclerView = view.findViewById(R.id.hardlevelview);
        RecyclerView.LayoutManager lm= new GridLayoutManager(getContext(),4, LinearLayoutManager.VERTICAL,false);
        HardLevelRecyclerView.setLayoutManager(lm);

        cards = new ArrayList<>();

        //card shuffle here
        shuffle(CARDS,16);
        shuffle(CARDS,16);
        for (int card : CARDS){
            cards.add(card);
        }
        HardLevelRecyclerView.setAdapter(new EasyLevelAdapter(cards));

        return view;
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
