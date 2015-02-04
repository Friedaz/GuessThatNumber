package com.maltech.guessthatnumber;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity {
    private Random ran = new Random();
    private int value;
    private int point;
    private boolean lostGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        value = ran.nextInt(100)+1;
        point = 100;
        lostGame = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void guessNumber(View view) {
        Button b =(Button) findViewById(R.id.guessButton);
        TextView tv = (TextView) findViewById(R.id.text);
        EditText et = (EditText) findViewById(R.id.guess);
        TextView tvpoint = (TextView) findViewById(R.id.point);
        String guess;

    if(et.getText().toString().isEmpty()){
         tv.setText("Type in a number between 1-100 below to guess!");
    }else{
        if (point > 0){
            if (Integer.parseInt(et.getText().toString())> value){
                guess = et.getText().toString();
                tv.setText("Too high!"+"\nYour last guess was "+guess);
                point = point - 2;
                tvpoint.setText("You have "+point+" points!");

        } else if (Integer.parseInt(et.getText().toString()) < value){
                guess = et.getText().toString();
                tv.setText("Too low!"+"\nYour last guess was "+guess);
                point = point - 2;
                tvpoint.setText("You have "+point+" points!");

        } else if(Integer.parseInt(et.getText().toString()) == value) {
                guess = et.getText().toString();
                tv.setText("That's it!\nYou guessed it. It was " + value);
                point = point + 10;
                tvpoint.setText("You have " + point + " points!");

                 }
        }else if (point <= 0){
                tv.setText("You lose.");
                et.setText("");
                b.setActivated(false);
                lostGame = true;
        }
        et.setText("");

      }
    }

    public void startAgain(View view) {
        Button b = (Button) findViewById(R.id.guessButton);
        TextView tv = (TextView) findViewById(R.id.text);
        value = ran.nextInt(100)+1;
        tv.setText("I've chosen another number between 1-100!");

        b.setActivated(true);
        if (lostGame){
            point = 100;
            lostGame = false;
        }
    }
}
