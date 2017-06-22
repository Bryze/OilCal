package com.example.android.oilcal;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public TextView totalTime;
    public EditText hours;
    public double result;
    public Button add;
    public Button eval;
    public EditText oil;
    public EditText mins;
    public String his;
    public TextView answer;
    public TextView history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=0.00;
        totalTime = (TextView) findViewById(R.id.totalTime);
        history  = (TextView) findViewById(R.id.history);
        add = (Button) findViewById(R.id.add);
        eval = (Button) findViewById(R.id.eval);
        his = "";
        answer = (TextView) findViewById(R.id.answer);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hours = (EditText) findViewById(R.id.hours);
                mins = (EditText) findViewById(R.id.mins);

                double h = Double.valueOf(hours.getText().toString());
                  double m = Double.valueOf(mins.getText().toString());
                double temp = 60.00;
                result = result + h + (m/temp);
                his = his+'\n'+(hours.getText().toString())+" hrs "+(mins.getText().toString())+" mins";
                //String t = hours.getText().toString();
                history.setText(his);
                //totalTime.setText(t);
                totalTime.setText(String.valueOf(result));

                hours.setText("");
                mins.setText("");
            }
        });

        eval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oil = (EditText) findViewById(R.id.oil);

                double quant = Double.valueOf(oil.getText().toString());

                double tempresult = quant/result;

                answer.setText(String.valueOf(tempresult));
            }
        });
    }

}
