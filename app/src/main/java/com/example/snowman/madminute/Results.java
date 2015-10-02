package com.example.snowman.madminute;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class Results extends AppCompatActivity {


    public static final String HighScore = "TIP";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        SharedPreferences sharePref = getSharedPreferences("TIP", 0);
        SharedPreferences.Editor edit = sharePref.edit();



        Intent intent = getIntent();
        int myScore = intent.getIntExtra("Score", 0);

        int highScore = sharePref.getInt("TIP", 0);

        if (myScore > highScore){
            edit.putInt("TIP",myScore);
            edit.commit();
        }

        TextView tvMyScore = (TextView)findViewById(R.id.myScore);
        tvMyScore.setText(Integer.toString(myScore));

        TextView tvHighScore = (TextView)findViewById(R.id.firstPlace);
        tvHighScore.setText(Integer.toString(highScore));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
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
    public void restart(View view){
        Intent intent = new Intent(this,LetsPlay.class);
        startActivity(intent);
    }
}
