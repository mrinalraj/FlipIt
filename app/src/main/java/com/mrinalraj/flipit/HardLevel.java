package com.mrinalraj.flipit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrinalraj.flipit.Adapters.EasyLevelAdapter;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;
import java.util.Random;


public class HardLevel extends Fragment {

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
    EasyFlipView flippedCard;
    private Bundle b;
    public long RemainingTime;
    public boolean isPaused, isCancelled;
    private SharedPreferences pref;
    int pos, count, bestScore;

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


    public void fragmentTransaction(Bundle b){
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        final Result r= new Result();
        r.setArguments(b);
        transaction.replace(R.id.layoutFragment, r);
        transaction.commit();
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_hard_level, container, false);

        HardLevelRecyclerView = view.findViewById(R.id.hardlevelview);
        b=new Bundle();
        b.putInt("level",Constants.LEVEL_HARD);
        
        pref = getActivity().getSharedPreferences(Constants.PREF_NAME,0);
        bestScore = pref.getInt(Constants.HARD_HIGH_KEY, (int) (Constants.HARD_TIME/Constants.TIMER_INTERVAL));

        ((TextView) view.findViewById(R.id.bestHard)).append(bestScore+"");
        

        RecyclerView.LayoutManager lm= new GridLayoutManager(getContext(),4, LinearLayoutManager.VERTICAL,false);
        HardLevelRecyclerView.setLayoutManager(lm);

        cards = new ArrayList<>();

        //card shuffle here
        shuffle(CARDS,Constants.HARD_NO_OF_CARDS);
        shuffle(CARDS,Constants.HARD_NO_OF_CARDS);
        for (int card : CARDS){
            cards.add(card);
        }
        HardLevelRecyclerView.setAdapter(new EasyLevelAdapter(cards));
        
        isPaused = false;
        isCancelled = false;
        

        new CountDownTimer(Constants.HARD_TIME,Constants.TIMER_INTERVAL){
            @Override
            public void onTick(long millisUntilFinished) {
                if (isPaused || isCancelled){
                    cancel();
                }
                else {
                    ((TextView) view.findViewById(R.id.hardlevelcounter)).setText("Time : " + millisUntilFinished / Constants.TIMER_INTERVAL);
                    RemainingTime = millisUntilFinished;
                    if (count == Constants.HARD_NO_OF_CARDS) {
                        b.putString("Data", "win");
                        long time = (Constants.HARD_TIME - millisUntilFinished)/ Constants.TIMER_INTERVAL;
                        b.putInt("Time", (int) time);
                        cancel();
                        this.onFinish();
                    }
                }
            }

            @Override
            public void onFinish() {
                if (count < Constants.HARD_NO_OF_CARDS) {
                    b.putString("Data", "lost");
                    b.putInt("Time", (int) (Constants.HARD_TIME/Constants.TIMER_INTERVAL));
                }
                fragmentTransaction(b);
            }
        }.start();

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP){
                    isPaused = true;
                    AlertDialog.Builder pause = new AlertDialog.Builder(getContext());
                    pause.setTitle("Game paused");
                    pause.setCancelable(false);
                    pause.setMessage("Do you want to quit ?");
                    pause.setPositiveButton("Resume", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            isPaused = true;
                            new CountDownTimer(RemainingTime,Constants.TIMER_INTERVAL){
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    if (isPaused || isCancelled){
                                        cancel();
                                    }
                                    else {
                                        ((TextView) view.findViewById(R.id.hardlevelcounter)).setText("Time : " + millisUntilFinished / Constants.TIMER_INTERVAL);
                                        RemainingTime = millisUntilFinished;
                                        if (count == Constants.HARD_NO_OF_CARDS) {
                                            b.putString("Data", "win");
                                            long time = (Constants.HARD_TIME - millisUntilFinished)/ Constants.TIMER_INTERVAL;
                                            b.putInt("Time", (int) time);
                                            cancel();
                                            this.onFinish();
                                        }
                                    }
                                }

                                @Override
                                public void onFinish() {
                                    if (count < Constants.HARD_NO_OF_CARDS) {
                                        b.putString("Data", "lost");
                                        b.putInt("Time", (int) (Constants.HARD_TIME/Constants.TIMER_INTERVAL));
                                    }
                                    fragmentTransaction(b);
                                }
                            }.start();
                        }
                    });
                    pause.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            isCancelled =true;
                            getFragmentManager().popBackStack();
                        }
                    });
                    pause.show();
                    return true;
                }
                return false;
            }
        });

        HardLevelRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

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
                                ((EasyFlipView) child).setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
                                    @Override
                                    public void onViewFlipCompleted(EasyFlipView easyFlipView, EasyFlipView.FlipState newCurrentSide) {
                                        for (int i = 0; i < HardLevelRecyclerView.getChildCount(); i++) {
                                            EasyFlipView child1 = (EasyFlipView) HardLevelRecyclerView.getChildAt(i);
                                            child1.setEnabled(false);
                                        }
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                flippedCard.setVisibility(View.GONE);
                                                child.setVisibility(View.GONE);
                                                child.setEnabled(false);
                                                flippedCard.setEnabled(false);
                                                flippedCard=null;
                                                count+=2;

                                                for (int i = 0; i < HardLevelRecyclerView.getChildCount(); i++) {
                                                    EasyFlipView child1 = (EasyFlipView) HardLevelRecyclerView.getChildAt(i);
                                                    child1.setEnabled(true);
                                                }
                                                ((EasyFlipView) child).setOnFlipListener(null);
                                            }
                                        },200);
                                    }
                                });
                            }
                            else {
                                ((EasyFlipView) child).setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
                                    @Override
                                    public void onViewFlipCompleted(EasyFlipView easyFlipView, EasyFlipView.FlipState newCurrentSide) {
                                        for (int i = 0; i < HardLevelRecyclerView.getChildCount(); i++) {
                                            EasyFlipView child1 = (EasyFlipView) HardLevelRecyclerView.getChildAt(i);
                                            child1.setEnabled(false);
                                        }
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                flippedCard.flipTheView();
                                                ((EasyFlipView) child).flipTheView();
                                                flippedCard = null;
                                                ((EasyFlipView) child).setOnFlipListener(null);

                                                for (int i = 0; i < HardLevelRecyclerView.getChildCount(); i++) {
                                                    EasyFlipView child1 = (EasyFlipView) HardLevelRecyclerView.getChildAt(i);
                                                    child1.setEnabled(true);
                                                }
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
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {}
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
        });

        return view;
    }


}
