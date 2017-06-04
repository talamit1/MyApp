package com.example.talam.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by talam on 31/05/2017.
 */

public class MainActivity extends Activity implements View.OnClickListener {

    private Button spelling;
    private Button changer;
    private RelativeLayout rl;
    private int currBack;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        changer = (Button) findViewById(R.id.changer);
        spelling = (Button) findViewById(R.id.spell_button);
        rl = (RelativeLayout) findViewById(R.id.main_activity);
        currBack = 0;
        changer.setOnClickListener(this);
        spelling.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if (v == changer && currBack == 0) {
                rl.setBackgroundResource(R.drawable.background2);
                currBack = 1;
                return;}
        if (v == changer && currBack == 1) {
                rl.setBackgroundResource(R.drawable.background);
                currBack = 0;
                return;}
        if(v==spelling)
            startActivity(new Intent(this,SpellingActivity.class));

    }
}