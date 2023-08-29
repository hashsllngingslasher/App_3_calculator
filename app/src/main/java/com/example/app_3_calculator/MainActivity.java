package com.example.app_3_calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private double firstVar, secondVar;
    private boolean isOperationClick;
    private String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        setTv_Result(buttonText);
        if (buttonText.equals("AC")) {
            textView.setText("0");
            firstVar = 0;
        }
    }

    public void setTv_Result(String numbers) {
        if (textView.getText().toString().equals("0") || isOperationClick) {
            textView.setText(numbers);
        } else {
            textView.append(numbers);
        }
        isOperationClick = false;
    }

    public void onOperationClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "%":
                calculatePercentage();
                break;
            case "+/-":
                negateNumber();
                break;
            case "+":
            case "-":
            case "X":
            case "/":
                setOperation(buttonText);
                break;
            case "=":
                calculateResult();
                break;
        }
    }

    private void calculatePercentage() {
        firstVar = Double.parseDouble(textView.getText().toString());
        double result = firstVar / 100;
        textView.setText(new DecimalFormat("##.#######").format(result));
        isOperationClick = true;
        operation = "/";
    }

    private void negateNumber() {
        firstVar = Double.parseDouble(textView.getText().toString());
        double result = firstVar * -1;
        textView.setText(new DecimalFormat("##.#######").format(result));
        isOperationClick = true;
        operation = "+-";
    }

    private void setOperation(String op) {
        setFirstVar();
        isOperationClick = true;
        operation = op;
    }

    private void calculateResult() {
        setSecondVar();
        double results = 0;

        switch (operation) {
            case "+":
                results = firstVar + secondVar;
                break;
            case "-":
                results = firstVar - secondVar;
                break;
            case "X":
                results = firstVar * secondVar;
                break;
            case "/":
                results = firstVar / secondVar;
                break;
        }

        textView.setText(Double.toString(results));
    }

    private void setFirstVar() {
        firstVar = Double.parseDouble(textView.getText().toString());
    }

    private void setSecondVar() {
        secondVar = Double.parseDouble(textView.getText().toString());
    }
}
