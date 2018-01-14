package com.example.ytgv8b.firsttry.Puzzles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.ytgv8b.firsttry.R;
import com.triggertrap.seekarc.SeekArc;

import java.text.SimpleDateFormat;

public class DefaultPuzzleActivity extends PuzzleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_puzzle);

        TextView textView = findViewById(R.id.textViewTime2);
        textView.setText(get_sTime());
        TextView textViewName = findViewById(R.id.textViewName2);
        textViewName.setText(_alarmName);

        SeekArc seekArc = findViewById(R.id.seekArc);

        seekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {

            }

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
                if (seekArc.getProgress() >= 100)
                {
                    finish();
                }
            }
        });

        Button button = findViewById(R.id.button4);

        button.setOnClickListener(view -> {
            ring.postpound(this);
            finish();
        });
    }

    private String get_sTime()
    {
        String alarmTime = "";
        long t = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        alarmTime = format.format(t);
        return alarmTime;
    }

}
