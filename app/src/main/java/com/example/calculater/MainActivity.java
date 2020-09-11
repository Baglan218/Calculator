package com.example.calculater;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_result, tv_zapis;

    Button btn4, btn7, btn8, btn9,btn1,btn2,btn3,btn6,btn5,btn0;
    Button  btnMinus, btnPlus, btnPlusMinus;
    Button btnBack, btnCalc, btnTochka,btnDev,btnX;

    String previousNumber, secondNumber;
    String operation = "";
    String allZapis = "";
    boolean znak = false;
    boolean doubleClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initActions();

    }

    public void initViews() {
        tv_result = findViewById(R.id.tv_result);
        tv_zapis = findViewById(R.id.tv_zapis);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btnX = findViewById(R.id.btn_x);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn9 = findViewById(R.id.btn_9);
        btn0 = findViewById(R.id.btn_0);
        btnPlusMinus = findViewById(R.id.btn_plus_minus);

        btn4 = findViewById(R.id.btn_4);
        btnCalc = findViewById(R.id.btn_equal);
        btnMinus = findViewById(R.id.btn_minus);
        btnPlus = findViewById(R.id.btn_pius);
        btnBack = findViewById(R.id.btn_del);
        btnTochka = findViewById(R.id.btn_dot);
        btnDev= findViewById(R.id.btn_dev);
        btnX = findViewById(R.id.btn_x);
    }

    public void initActions() {

        onClickButton(btn7, "7");
        onClickButton(btn8, "8");
        onClickButton(btn9, "9");
        onClickButton(btn6, "6");
        onClickButton(btn5, "5");
        onClickButton(btn4, "4");
        onClickButton(btn3, "3");
        onClickButton(btn2, "2");
        onClickButton(btn1, "1");
        onClickButton(btn0, "0");
        onClickButton(btnPlusMinus, "+-");
        onClickButton(btnBack, "back");
        onClickButton(btnTochka, "tochka");

        onClickOperationButton(btnPlus, "+");
        onClickOperationButton(btnMinus, "-");
        onClickOperationButton(btnDev, "/");
        onClickOperationButton(btnX, "*");
        onClickButtonCalc();
    }

    public void onClickOperationButton(Button btn, final String curOperation) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                previousNumber = tv_result.getText().toString();

                if(!previousNumber.equals("0")) {
                    operation = curOperation;
                    tv_result.setText("0");

                    allZapis += previousNumber + " " + operation;
                    tv_zapis.setText(allZapis);
                }
            }
        });
    }

    public void onClickButtonCalc() {
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                secondNumber = tv_result.getText().toString();
                allZapis += secondNumber;
                int result = 0;
                double result2 = 0;

                switch (operation){
                    case "+":

                        if(doubleClicked){
                            result2 = Double.parseDouble(previousNumber) + Double.parseDouble(secondNumber);
                            tv_result.setText(""+result2);
                        }else{
                            result = Integer.parseInt(previousNumber) + Integer.parseInt(secondNumber);
                            tv_result.setText(""+result);
                        }

                        break;

                    case "-":
                        result = Integer.parseInt(previousNumber) - Integer.parseInt(secondNumber);

                        break;
                    case "*":
                        result = Integer.parseInt(previousNumber) * Integer.parseInt(secondNumber);

                        break;
                    case "/":
                        result = Integer.parseInt(previousNumber) / Integer.parseInt(secondNumber);

                        break;

                }

                tv_zapis.setText(allZapis);
            }
        });
    }


    public void onClickButton(Button btn, final String text) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String res = tv_result.getText().toString(); // 0

                if(text.equals("+-") && !res.equals("0")){
                    znak = !znak;

                    if(znak) res = "-"+res;
                    else res = res.substring(1, res.length()); // -123

                }else if(text.equals("back")){
                    if(!res.equals("0")) {
                        if (res.length() == 1) res = "0";
                        else res = res.substring(0, res.length() - 1);
                    }

                }else if(text.equals("tochka")){
                    res = res + "."; // 123.3 Double
                    doubleClicked = true;
                }

                else if (res.equals("0"))  res = text; // 7
                else res = res + text; // res = res + text; res = 78

                tv_result.setText(res); // 78
            }
        });

    }




}
