package com.example.snowman.madminute;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class LetsPlay extends ActionBarActivity {
    public static final String PREFS = "Ariel&Anthony";
    public final static String theScore = "LetsPlay.java";
    final int delay = 60000;
    @Override
    protected void onStart() {
        super.onStart();
        final EditText etAnswer = (EditText) findViewById(R.id.etAnswer);

        final int topNumbersList[] = getRandom();
        final int botNumbersList[] = getRandom();
        final int ans[] = new int[100];
        ans[0] = updateTextViews(topNumbersList[0], botNumbersList[0]);

        etAnswer.addTextChangedListener(new TextWatcher() {
            int i =0;
            int score = 0;
            int tries = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Integer.parseInt(s.toString()) == (topNumbersList[i]+botNumbersList[i])){

                    i++;
                    updateTextViews(topNumbersList[i], botNumbersList[i]);
                    etAnswer.removeTextChangedListener(this);
                    etAnswer.setText("");
                    etAnswer.addTextChangedListener(this);
                    score++;





                } else {
                    i++;
                    tries++;

                    updateTextViews(topNumbersList[i], botNumbersList[i]);
                    etAnswer.removeTextChangedListener(this);
                    etAnswer.setText("");
                    etAnswer.addTextChangedListener(this);



                }

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        Intent intent = new Intent(LetsPlay.this, Results.class);
                        intent.putExtra("Score", score);
                        startActivity(intent);
                    }
                }, delay);
            }

        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_play);

    }











    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lets_play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int[] getRandom(){
        Random random = new Random();
        int ans[] = new int[100];
        for (int i = 0; i<100; i++){
            ans[i] = random.nextInt(5);
        }
        //return increment insted?
        return ans;
    }

    public int updateTextViews(int top, int bot) {
        TextView tvTopAnswer = (TextView) findViewById(R.id.tvTopAnswer);
        TextView tvBotAnswer = (TextView) findViewById(R.id.tvBottomNumber);

        int UTVans = top + bot;

        tvTopAnswer.setText(Integer.toString(top));
        tvBotAnswer.setText(Integer.toString(bot));


        return UTVans;
    }

    
}
