package com.example.ytgv8b.firsttry.Puzzles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ytgv8b.firsttry.R;

import java.text.SimpleDateFormat;
import java.util.Random;

import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.PreferenceHelper;
import Library.PuzzlesThings.PuzzleFactory;
import Library.Signals.IRing;

/**
 * при выполнении головоломкивозвращается на предыдущее активити (можно это убрать)
 * метод для получения факта завершения - getIsFinished()
 */

public class DisplayCalculateActivity extends PuzzleActivity {

    private int _ans;

    private final int[] _dans = {15, 1, -1, 2, -5, 10, -10, 111};
    private final char[] _operation = {'+', '-', '*', '/'};

    private boolean _isFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_calculate);
        TextView textView = (TextView) findViewById(R.id.textViewTime);
        textView.setText(get_sTime());
        TextView textViewName = (TextView) findViewById(R.id.textViewName);
        textViewName.setText(_alarmName);
        createTask();
        createAnswers();

        //ActionBar actionBar = getSupportActionBar();
       // actionBar.hide();

    }

    private String get_sTime()
    {
        String alarmTime = "";
        long t = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        alarmTime = format.format(t);
        return alarmTime;
    }

    private void createTask()
    {
        TextView textView = (TextView) findViewById(R.id.textViewTask);
        Random random = new Random(System.currentTimeMillis());
        int a = 0, b = 0;

        int r = random.nextInt(4);
        switch (r)
        {
            case 0:
                a = random.nextInt(100) + 1; //исключаем 0
                b = random.nextInt(100) + 1;
                _ans = a+b;
                break;
            case 1:
                a = random.nextInt(100) + 1; //исключаем 0
                b = random.nextInt(100) + 1;
                if(b>a)//чтобы результат был положительным
                {

                    int c = a;
                    a = b;
                    b = c;
                }
                _ans = a-b;
                break;
            case 2:
                a = random.nextInt(50) + 1; //исключаем 0
                b = random.nextInt(12) + 1;
                _ans = a*b;
                break;
            case 3:
                a = random.nextInt(1000) + 1; //исключаем 0
                b = random.nextInt(50) + 1;
                if(b>a)
                {

                    int c = a;
                    a = b;
                    b = c;
                }
                int cur = a/b;
                //гарантия, что число делится без остатка
                a = cur*b;
                _ans = a/b;
                break;
        }

        textView.setText(a+" " + _operation[r] + " " +b+" = ");
    }

    private void createAnswers()
    {
        boolean[] used = new boolean[8];
        boolean[] usedCount = new boolean[4];
        int[] answ = {_ans, _ans, _ans, _ans};
        Random random = new Random(System.currentTimeMillis());
        int count = 0;
        while(count<3)
        {
            int r = random.nextInt(8);
            if(!used[r])
            {
                used[r] = true;
                int id = random.nextInt(4);
                if(!usedCount[id])
                {
                    usedCount[id] = true;
                    if(_dans[r]==111)
                    {
                        boolean suit = false;
                        while(!suit)
                        {
                            answ[id] = _ans-(_ans%10);
                            int k = 0;
                            for(int i = 0; i<4; i++) {
                                if (answ[i] == answ[id])
                                {
                                    k++;
                                }
                            }
                            if(k>1)
                            {
                                answ[id]++;
                            }
                            else
                            {
                                suit = true;
                            }
                        }

                    }
                    else
                    {
                        if(_ans +_dans[r]<0)
                        {
                            continue;
                        }
                        answ[id] = _ans +_dans[r];

                    }
                    count++;

                }

            }
        }


        RadioButton radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        RadioButton radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        RadioButton radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        radioButton1.setText(String.valueOf(answ[0]));
        radioButton2.setText(String.valueOf(answ[1]));
        radioButton3.setText(String.valueOf(answ[2]));
        radioButton4.setText(String.valueOf(answ[3]));
    }


    public void checkResult(View view) {
       /* EditText editText = (EditText) findViewById(R.id.editTextAnswer);
        String userAns = String.valueOf(editText.getText());
        TextView textView = (TextView) findViewById(R.id.textViewResult);
        String s = String.valueOf(_ans);
        if(String.valueOf(_ans).equals(userAns))
        {
            textView.setText("Right!");
        }
        else
        {
            textView.setText("Wrong.");
        }
*/
    }

    public void onRadioButtonClicked(View view) {
        // если переключатель отмечен
        boolean checked = ((RadioButton) view).isChecked();
        TextView textResult = (TextView) findViewById(R.id.textViewResult);
        RadioButton radioButton = null;
        // Получаем нажатый переключатель
        switch(view.getId()) {
            case R.id.radioButton1:
                radioButton = (RadioButton) findViewById(R.id.radioButton1);
                break;
            case R.id.radioButton2:
                radioButton = (RadioButton) findViewById(R.id.radioButton2);
                break;
            case R.id.radioButton3:
                radioButton = (RadioButton) findViewById(R.id.radioButton3);
                break;
            case R.id.radioButton4:
                radioButton = (RadioButton) findViewById(R.id.radioButton4);
                break;

        }
        String ans = String.valueOf(_ans);
        try
        {
            if (radioButton.getText().equals(ans)){
                textResult.setText("Верно!");
                _isFinished = true;
                finish();
                /***
                 * если не надо, чтобы закрывалось тут активити, то надо убрать finish();
                 */
            }
            else
            {
                textResult.setText("Неверно. Попробуй еще");
            }
        }
        catch (Exception e)
        {
            Log.e("Exception", "CalcActivity");
        }


    }
}
