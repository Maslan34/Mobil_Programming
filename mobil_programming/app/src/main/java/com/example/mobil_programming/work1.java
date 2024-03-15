package com.example.mobil_programming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

enum operation{
    plus,minus,divide,multiply
}
public class work1 extends AppCompatActivity {

    EditText etv;
    String consoleString = "";
    int firstOperand;
    int secondOperand;

    int console_result;
    operation result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work1);
    }


    public void do_numeric(View v){
        int numericId = v.getId();


        etv = findViewById(R.id.etv_console);

        if(R.id.btn_numeric_one == numericId){
            consoleString=consoleString+"1";
            etv.setText(consoleString);
        }else if (R.id.btn_numeric_two == numericId) {
            consoleString=consoleString+"2";
            etv.setText(consoleString);
        }else if (R.id.btn_numeric_three == numericId) {
            consoleString=consoleString+"3";
            etv.setText(consoleString);
        }else if (R.id.btn_numeric_four == numericId) {
            consoleString=consoleString+"4";
            etv.setText(consoleString);
        }else if (R.id.btn_numeric_five == numericId) {
            consoleString=consoleString+"5";
            etv.setText(consoleString);
        }else if (R.id.btn_numeric_six == numericId) {
            consoleString=consoleString+"6";
            etv.setText(consoleString);
        }else if (R.id.btn_numeric_seven == numericId) {
            consoleString=consoleString+"7";
            etv.setText(consoleString);
        }else if (R.id.btn_numeric_eight == numericId) {
            consoleString=consoleString+"8";
            etv.setText(consoleString);
        }else if (R.id.btn_numeric_nine == numericId) {
            consoleString=consoleString+"9";
            etv.setText(consoleString);
        }else if (R.id.btn_numeric_zero == numericId) {
            consoleString=consoleString+"0";
            etv.setText(consoleString);
        }

    }

    public void do_operation(View v){

        int operationId = v.getId();
        etv = findViewById(R.id.etv_console);
        etv.setText("");

        if(R.id.btn_operation_divide == operationId){
            this.result=operation.divide;
            this.firstOperand = Integer.parseInt(consoleString);
            this.consoleString ="";
        }else if (R.id.btn_operation_multiply == operationId) {
            this.result=operation.multiply;
            this.firstOperand = Integer.parseInt(consoleString);
            this.consoleString ="";
        }else if (R.id.btn_operation_minus == operationId) {
            this.result=operation.minus;
            this.firstOperand = Integer.parseInt(consoleString);
            this.consoleString ="";
        }else if (R.id.btn_operation_plus == operationId) {
            this.result=operation.plus;
            this.firstOperand = Integer.parseInt(consoleString);
            this.consoleString ="";
        }else if (R.id.btn_equal == operationId) {

            this.secondOperand = Integer.parseInt(consoleString);

            switch (result){
                case plus:
                    console_result=firstOperand+secondOperand;
                    break;
                case minus:
                    console_result=firstOperand-secondOperand;
                    break;
                case divide:
                    console_result=firstOperand/secondOperand;
                    break;
                case multiply:
                    console_result=firstOperand*secondOperand;
                    break;
            }

            etv.setText(Integer.toString(console_result));
            consoleString="";
            console_result=0;
        }
        else{
            System.out.println("Warning Calculator!");
        }

    }
}