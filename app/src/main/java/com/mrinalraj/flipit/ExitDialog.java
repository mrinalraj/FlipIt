package com.mrinalraj.flipit;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ExitDialog extends Dialog implements View.OnClickListener {

    Activity c;
    public Button exit;
    public ExitDialog(Activity context) {
        super(context);
        this.c = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.exit_dialog);
        exit = findViewById(R.id.exitbtn);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
