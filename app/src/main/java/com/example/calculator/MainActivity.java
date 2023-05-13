package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText display;
    private EditText resultDisplay;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        display = findViewById(R.id.editTextValue);
        resultDisplay = findViewById(R.id.resultDisplay);
        display.setShowSoftInputOnFocus(false);
        /*
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.enter_in_the_value).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
        */

    }
    private void fill(String s){
        String str = display.getText().toString();
        // to get the cursor position
        int cursorPos = display.getSelectionStart();
        // String to the left of cursor
        String leftStr = str.substring(0,cursorPos);
        // String to the right of cursor
        String rightStr = str.substring(cursorPos);
        //set the text of editText field
        if(getString(R.string.enter_in_the_value).equals(display.getText().toString())){
            display.setText(s);
            display.setSelection(cursorPos + 1);
        }else{
            display.setText(String.format("%s%s%s",leftStr,s,rightStr));
            display.setSelection(cursorPos + 1);
        }


    }
    public void zeroButtonClick(View view){
        fill("0");
    }
    public void oneButtonClick(View view){

        fill("1");
    }
    public void twoButtonClick(View view){

        fill("2");
    }
    public void threeButtonClick(View view){

        fill("3");
    }
    public void fourButtonClick(View view){
        fill("4");
    }
    public void fiveButtonClick(View view){
        fill("5");
    }
    public void sixButtonClick(View view){
        fill("6");
    }
    public void sevenButtonClick(View view){
        fill("7");
    }
    public void eightButtonClick(View view){
        fill("8");
    }
    public void nineButtonClick(View view){
        fill("9");
    }
    public void dotButtonClick(View view){
        fill(".");
    }
    public void clearButtonClick(View view){
        display.setText("0");
        resultDisplay.setText("");
    }
    public void backButtonClick(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if(cursorPos != 0 && textLen!=0){
            SpannableStringBuilder sel =(SpannableStringBuilder) display.getText();
            sel.replace(cursorPos-1,cursorPos,"");
            display.setText(sel);
            display.setSelection(cursorPos-1);
        }
        /*
        int cursorPos = display.getSelectionStart();
        cursorPos-=1;
        String String = display.getText().toString();
        display.setText("");
        String subString = String.substring(0,cursorPos);
        fill(subString);

         */
    }

    public void percentButtonClick(View view){
        fill("%");
    }
    public void multiplyButtonClick(View view){
        fill("*");
    }
    public void divideButtonClick(View view){
        fill("/");
    }
    public void addButtonClick(View view){
        fill("+");
    }
    public void subtractButtonClick(View view) {
        fill("-");
    }
    public void parButtonClick(View view){
        int cursor = display.getSelectionStart();
        int oPar = 0;
        int cPar = 0;
        int len = display.getText().length();
        for(int i  = 0; i < cursor; i++){
            if(display.getText().toString().charAt(i) == '('){
                oPar += 1;
            }
            if(display.getText().toString().charAt(i) == ')') {
                cPar += 1;
            }
        }
        if(oPar == cPar || display.getText().toString().charAt(len-1) == '('){
            fill("(");
        }
        else if(cPar<oPar && display.getText().toString().charAt(len-1)!= '('){
            fill(")");
        }
        display.setSelection(cursor+1);
    }
    public void equalButtonClick(View view){

        String ex = display.getText().toString();
        Expression exp = new Expression(ex);

        String result = String.valueOf(exp.calculate());
        if(result.equals("NaN")) {
            resultDisplay.setText(R.string.error);
        }else{
            resultDisplay.setText(result);
        }


    }

}