package io.github.jungminan.assignment1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;
import java.util.StringTokenizer;

import androidx.appcompat.app.AppCompatActivity;

public class Calculator extends AppCompatActivity {
    EditText CalcEt;
    TextView ResultEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        CalcEt = findViewById(R.id.EditCalc);
        ResultEt = findViewById(R.id.TextResult);
    }


    public void Clicked(View view) {
        Button button= (Button) view;
        String clickValue = button.getText().toString();

        if (clickValue.equals("=")) {
            ResultEt.setText( Calc (CalcEt.getText().toString() ) );
            CalcEt.setText("");
        } else {
            CalcEt.setText(CalcEt.getText().toString() + clickValue);
        }
    }

    private String Calc(String formulaStr){
        StringTokenizer st_num  = new StringTokenizer(formulaStr,"+-%X ");
        StringTokenizer st_oper = new StringTokenizer(formulaStr,"1234567890 ");

        Stack<Integer> valueStack = new Stack <Integer>();
        valueStack.push(Integer.parseInt(st_num.nextToken()));
        while(st_num.hasMoreTokens()){
            String operator = st_oper.nextToken();
            String num = st_num.nextToken();
            int a;

            if ("X".equals(operator)){
                a = valueStack.pop();
                valueStack.push( a * Integer.parseInt(num) );
            }
            else if ("%".equals(operator)){
                a = valueStack.pop();
                valueStack.push( a / Integer.parseInt(num) );
            }
            else if ("+".equals(operator)){
                valueStack.push(Integer.parseInt(num));
            }
            else if ("-".equals(operator)){
                valueStack.push(-1 * (Integer.parseInt(num)));
            }
        }

        int tot = 0;
        while(!valueStack.isEmpty()){
            tot += valueStack.pop();
        }

        return Integer.toString(tot);
    }


}
