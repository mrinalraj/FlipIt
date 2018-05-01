package com.mrinalraj.flipit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mrinalraj.flipit.Adapters.EasyLevelAdapter;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;
import java.util.Random;


public class EasyLevel extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView EasyLevelRecyclerView;
    public ArrayList<Integer> cards;
    public int CARDS[] = {
                R.drawable.card1,
                R.drawable.card2,
                R.drawable.card3,
                R.drawable.card4,
                R.drawable.card5,
                R.drawable.card6,
                R.drawable.card1,
                R.drawable.card2,
                R.drawable.card3,
                R.drawable.card4,
                R.drawable.card5,
                R.drawable.card6
    };
    EasyFlipView flippedCard;
    int pos, count;
    Bundle b;
    public ProgressBar pbar;


    public EasyLevel() {
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
        final View rootView =  inflater.inflate(R.layout.fragment_easy_level, container, false);

        EasyLevelRecyclerView = rootView.findViewById(R.id.easylevelview);
        b=new Bundle();
        pbar = rootView.findViewById(R.id.progressBarEasy);

        RecyclerView.LayoutManager lm = new GridLayoutManager(getContext(),3, LinearLayoutManager.VERTICAL,false);
        EasyLevelRecyclerView.setLayoutManager(lm);

        cards = new ArrayList<>();
        // TODO: card shuffle here
        shuffle(CARDS,12);
        //shuffle(CARDS,12);
        for (int card : CARDS){
            cards.add(card);
        }

        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        final Result r= new Result();

        new CountDownTimer(55000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                //((TextView) rootView.findViewById(R.id.easylevelcounter)).setText("Time : "+millisUntilFinished/1000);
                pbar.setProgress((int) (millisUntilFinished/1000)*100/(55000/1000));
                if (count == 12){
                    this.cancel();
                    this.onFinish();
                    b.putString("Data","win");
                    long time = 55 - (millisUntilFinished/1000);
                    b.putInt("Time", (int) time);
                }
            }

            @Override
            public void onFinish() {
                Toast.makeText(getContext(), "cards eliminated : "+count, Toast.LENGTH_SHORT).show();

                if (count < 12){
                    b.putString("Data","lost");
                    b.putInt("Time", 55);
                }

                r.setArguments(b);
                transaction.replace(R.id.layoutFragment,r);
                transaction.commit();
            }
        }.start();

        EasyLevelRecyclerView.setAdapter(new EasyLevelAdapter(cards));

        EasyLevelRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(final RecyclerView rv, MotionEvent e) {
                final View child = rv.findChildViewUnder(e.getX(),e.getY());
                if (child != null){

                    final int position = rv.getChildAdapterPosition(child);

                    if (flippedCard == null){
                        flippedCard = (EasyFlipView) child;
                        pos = position;
                    }

                    else{

                        if (pos == position){
                            flippedCard=null;
                        }
                        else{
                            if (cards.get(pos).equals(cards.get(position))){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        flippedCard.setVisibility(View.GONE);
                                        child.setVisibility(View.GONE);
                                        child.setEnabled(false);
                                        flippedCard.setEnabled(false);
                                        flippedCard=null;
                                        count+=2;

                                    }
                                },400);
                            }
                            else {
                                ((EasyFlipView) child).setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
                                    @Override
                                    public void onViewFlipCompleted(EasyFlipView easyFlipView, EasyFlipView.FlipState newCurrentSide) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                flippedCard.flipTheView();
                                                ((EasyFlipView) child).flipTheView();
                                                flippedCard = null;
                                                ((EasyFlipView) child).setOnFlipListener(null);
                                            }
                                        }, 100);
                                    }
                                });
                            }
                        }

                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
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
