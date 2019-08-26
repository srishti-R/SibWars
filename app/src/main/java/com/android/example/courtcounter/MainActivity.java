package com.android.example.courtcounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    double scoreTeamA=0;
    double scoreTeamB=0;
    TextView scoreView;
    TextView scoreViewB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(this);
       pref.registerOnSharedPreferenceChangeListener(this);

        scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreViewB = (TextView) findViewById(R.id.team_b_score);
    }

    @Override
    protected void onDestroy() {
        SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(this);
        pref.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();


    }

    public void displayForTeamA(double score) {



        scoreView.setText(String.valueOf(score));

    }
    public void displayForTeamB(double score) {




        scoreViewB.setText(String.valueOf(score));
    }
    public void plus3(View view){
        scoreTeamA=scoreTeamA+3;
        displayForTeamA(scoreTeamA);

    }
    public void plus2(View view){
        scoreTeamA=scoreTeamA+2;
        displayForTeamA(scoreTeamA);
    }
    public void plus1(View view){
        scoreTeamA=scoreTeamA+1;

        displayForTeamA(scoreTeamA);
    }
    public void plusHalf(View view){
        scoreTeamA=scoreTeamA+0.5;

        displayForTeamA(scoreTeamA);
    }

    public void plus3B(View view){
        scoreTeamB=scoreTeamB+3;
        displayForTeamB(scoreTeamB);

    }
    public void plus2B(View view){
        scoreTeamB=scoreTeamB+2;
        displayForTeamB(scoreTeamB);

    }
    public void plus1B(View view){
        scoreTeamB=scoreTeamB+1;
        displayForTeamB(scoreTeamB);

    }
    public void plusHalfB(View view){
        scoreTeamB=scoreTeamB+0.5;
        displayForTeamB(scoreTeamB);

    }

    public void Reset(View v){
        scoreTeamA=0;
        scoreTeamB=0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }
    public void writeSharedPrefA(double scoreTeamA){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong("scoreA", Double.doubleToRawLongBits(scoreTeamA));
        editor.commit();
    }
    public void writeSharedPrefB(double scoreTeamB){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong("scoreB", Double.doubleToRawLongBits(scoreTeamB));
        editor.commit();
    }
   public void save(View view){
      String scoreA= scoreView.getText().toString() ;
      writeSharedPrefA(Double.parseDouble(scoreA));
      String scoreB=scoreViewB.getText().toString();
      writeSharedPrefB(Double.parseDouble(scoreB));
   }
   public void resetToPrevious(View view){
       Button button=(Button)findViewById(R.id.reset_prev);
       SharedPreferences sp=getPreferences(Context.MODE_PRIVATE);
       Long value;
       if(sp.contains("scoreA")){
            value=sp.getLong("scoreA", 0);
            double value2=Double.longBitsToDouble(value);
           String value1=String.valueOf(value2);
           scoreView.setText(value1);
       }if(sp.contains("scoreB")){
           value=sp.getLong("scoreB", 0);
           double value2=Double.longBitsToDouble(value);
           String value1=String.valueOf(value2);
           scoreViewB.setText(value1);

       }


   }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    Long i=sharedPreferences.getLong(key, Double.doubleToLongBits(0));
    double o=Double.longBitsToDouble(i);

    if(key.equals("scoreA")){
        writeSharedPrefA(o);

    }if(key.equals("scoreB")){
        writeSharedPrefB(o);


        }

    }
}
