package com.mrinalraj.flipit;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Home extends AppCompatActivity implements Start.OnFragmentInteractionListener, EasyLevel.OnFragmentInteractionListener, HardLevel.OnFragmentInteractionListener, Result.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layoutFragment, new Start());
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.layoutFragment);

        if (currentFragment instanceof EasyLevel){

        }
        else if (currentFragment instanceof HardLevel){

        }
        else if (currentFragment instanceof Start){
            ExitDialog dialog = new ExitDialog(this);
            dialog.show();
        }
        else if(currentFragment instanceof Result){
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            super.onBackPressed();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
