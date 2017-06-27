package com.example.android.oilcal;

import android.icu.text.DecimalFormat;
import android.support.annotation.StringDef;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public TextView totalTime;
    public EditText hours;
    public double result;
    public Button add;
    public Button eval;
    public EditText oil;
    public EditText mins;
    public Button checkhistory;
    public String his;
    public String thours;
    public String tmins;
    public String toil;
    public Button reset;
    public TextView answer;
    public TextView history;
    public double h =0;
    public double quant;
    public double tempresult;
    public int f=0;
    public double m = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=0.00;
        checkhistory = (Button) findViewById(R.id.checkhistory);
        totalTime = (TextView) findViewById(R.id.totalTime);
       // history  = (TextView) findViewById(R.id.history);
        add = (Button) findViewById(R.id.add);
        eval = (Button) findViewById(R.id.eval);
        his = "";
        answer = (TextView) findViewById(R.id.answer);
        reset = (Button) findViewById(R.id.reset);
        hours = (EditText) findViewById(R.id.hours);
        mins = (EditText) findViewById(R.id.mins);
        oil = (EditText) findViewById(R.id.oil);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String thours = hours.getText().toString();
                String tmins = mins.getText().toString();
                if(thours.matches("") || tmins.matches("")) {
                    Toast.makeText(MainActivity.this, "Enter valid value.", Toast.LENGTH_SHORT).show();
                    hours.setText("");
                    mins.setText("");
                } else {
                    h = Double.valueOf(hours.getText().toString());
                    m = Double.valueOf(mins.getText().toString());
                    double temp = 60.00;

                    int r =check(m);
                    if(r==1) {
                        f=1;
                        result = result + h + (m/temp);
                        String display = String.format("%.2f",result);
                        his = his+'\n'+(hours.getText().toString())+" hrs "+(mins.getText().toString())+" mins";
                        //String t = hours.getText().toString();
                       // history.setText(his);
                        //totalTime.setText(t);
                        totalTime.setText(display);

                        hours.setText("");
                        mins.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Insert a valid minutes range (0 to 60)", Toast.LENGTH_SHORT).show();
                        mins.setText("");
                    }
                }

            }
        });
        TextView.OnEditorActionListener exampleListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEND) {
                    h = Double.valueOf(hours.getText().toString());
                    m = Double.valueOf(mins.getText().toString());
                    double temp = 60.00;

                    int r =check(m);
                    if(r==1) {
                        f=1;
                        result = result + h + (m/temp);
                        String display = String.format("%.2f",result);
                        his = his+'\n'+(hours.getText().toString())+" hrs "+(mins.getText().toString())+" mins";
                        //String t = hours.getText().toString();
                        // history.setText(his);
                        //totalTime.setText(t);
                        totalTime.setText(display);

                        hours.setText("");
                        mins.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Insert a valid minutes range (0 to 60)", Toast.LENGTH_SHORT).show();
                        mins.setText("");
                    }
                }
                return true;
            }
        };
        mins.setOnEditorActionListener(exampleListener);
        TextView.OnEditorActionListener oilListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEND) {
                     thours = hours.getText().toString();
                     tmins = mins.getText().toString();
                     toil = oil.getText().toString();
                    if((thours.matches("") || tmins.matches("") || toil.matches(""))&&f==0){
                        Toast.makeText(MainActivity.this, "Enter value to evaluate", Toast.LENGTH_SHORT).show();
                    }
                    else {
                         quant = Double.valueOf(oil.getText().toString());

                         tempresult = quant / result;
                        String display = String.format("%.2f",tempresult);
                        answer.setText(display);
                    }
                }
                InputMethodManager imm = (InputMethodManager)getSystemService(MainActivity.this.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(oil.getWindowToken(), 0);
                return true;
            }
        };
        oil.setOnEditorActionListener(oilListener);
        checkhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Check History").setMessage(his).show();


            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hours.setText("");
                mins.setText("");
                answer.setText("Final Answer");
                his="";
               // history.setText("History");
                result = 0.00;
                totalTime.setText("Total Time");
                oil.setText("");
                f=0;


            }
        });

        hours.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(hours.getText().toString().length()==2) {
                        mins.requestFocus();
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        eval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thours = hours.getText().toString();
                String tmins = mins.getText().toString();
                String toil = oil.getText().toString();
                if((thours.matches("") || tmins.matches("") || toil.matches(""))&&f==0){
                    Toast.makeText(MainActivity.this, "Enter value to evaluate", Toast.LENGTH_SHORT).show();
                }
                else {
                    double quant = Double.valueOf(oil.getText().toString());

                    double tempresult = quant / result;
                    String display = String.format("%.2f",tempresult);
                    answer.setText(display);
                }
            }
        });
    }

    private int check(double m) {
        if(m>=0.00 && m<=60.00) {
            return 1;
        } else {
            return 0;
        }
     }

}
