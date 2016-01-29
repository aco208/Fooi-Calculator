package com.example.aleksandro.fooicalculator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import android.view.inputmethod.EditorInfo;
import android.view.View.OnClickListener;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends Activity implements OnClickListener {

    private EditText rekeningBedragInput;
    private TextView procentView;
    private Button procentPlusButton;
    private Button procentMinButton;
    private TextView fooiView;
    private TextView totaalView;
    private double procent = 15;
    private TextView opmerkingView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rekeningBedragInput = (EditText) findViewById(R.id.rekeningBedrag);
        procentView = (TextView) findViewById(R.id.procent);
        procentPlusButton = (Button) findViewById(R.id.plusButton);
        procentMinButton = (Button) findViewById(R.id.minButton);
        fooiView = (TextView) findViewById(R.id.fooi);
        totaalView = (TextView) findViewById(R.id.totaal);
        opmerkingView = (TextView) findViewById(R.id.opmerking);

        SwagListener();
        procentMinButton.setOnClickListener(this);
        procentPlusButton.setOnClickListener(this);



    }

    private void SwagListener(){
        rekeningBedragInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Swagmanier();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void Swagmanier(){

        String rekeningBedragString = rekeningBedragInput.getText().toString();
        Double rekeningBedragDouble;

        if( rekeningBedragString.equals("")){
            rekeningBedragDouble = 0.0;
        }
        else{
            rekeningBedragDouble = Double.parseDouble(rekeningBedragString);
        }

        double fooi = rekeningBedragDouble * (procent/100);
        double totaal = rekeningBedragDouble + fooi;

        DecimalFormat df = new DecimalFormat("€ ###,###.00");
        fooiView.setText(df.format(fooi));
        totaalView.setText(df.format(totaal));
        procentView.setText("" + (int) procent + "%");

        if(procent >= 0 && procent <= 5){
            opmerkingView.setTextColor(Color.parseColor("#FF0000"));
            if(procent == 0){
                opmerkingView.setText("Alles was vreselijk!!");
            }
            else if(procent > 0 && procent <= 5){
                opmerkingView.setText("Het was slecht!");
            }
        }

        else if(procent >= 6 && procent < 15){
            opmerkingView.setTextColor(Color.parseColor("#FFA500"));
            if(procent >= 6 && procent < 10){
                opmerkingView.setText("Het was meh.");
            }
            else if(procent >= 10 && procent <15){
                opmerkingView.setText("Het was redelijk");
            }
        }

        else if(procent >= 15 && procent <= 30){
            opmerkingView.setTextColor(Color.parseColor("#00FF00"));
            if(procent >= 15 && procent < 20){
                opmerkingView.setText("Het was goed!");
            }
            else if(procent >= 20 && procent <=30){
                opmerkingView.setText("Het was geweldig!!!");
            }
        }

        else if(procent > 30 && procent < 80){
            opmerkingView.setTextColor(Color.parseColor("#ffd700"));
            opmerkingView.setText("Het was geweldig en je hebt geld!");

        }
        else{
            opmerkingView.setTextColor(Color.parseColor("#000000"));
            opmerkingView.setText("Je bent rijk!!!!");
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.minButton:
                procent--;
                Swagmanier();
                if(procent == 0){
                    procentMinButton.setEnabled(false);
                }
                procentPlusButton.setEnabled(true);
                break;
            case R.id.plusButton:
                procent++;
                Swagmanier();
                if(procent == 100){
                    procentPlusButton.setEnabled(false);
                }
                procentMinButton.setEnabled(true);
        }
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
}
