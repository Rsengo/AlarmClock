package com.example.ytgv8b.firsttry.Puzzles;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ytgv8b.firsttry.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.PreferenceHelper;
import Library.PuzzlesThings.PaintView;
import Library.PuzzlesThings.PuzzleFactory;
import Library.Signals.IRing;


/**
 * Сама головоломка в классах PaintView и Paint
 *
 * при ее выполнении возвращается на предыдущее активити(можно это убрать)
 * метод для получения факта завершения - getIsFinished()
 */


public class DisplayConnectActivity extends PuzzleActivity {

    private  ImageView[] _imageViewLeft;
    private  ImageView[] _imageViewRight;
    private boolean _isFinished = false;

    private ArrayList<ArrayList<Point>> _arrayPoint = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_connect);
//        Intent intent = getIntent();
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();
        for(int i = 0; i<4; i++)
        {
            ArrayList<Point> a = new ArrayList<>();
            _arrayPoint.add(a);
        }
        TextView textView = (TextView) findViewById(R.id.textViewTime);
        textView.setText(get_sTime());
        final TextView textViewName = (TextView) findViewById(R.id.textViewName);
        textViewName.setText(_alarmName);

        _imageViewLeft = new ImageView[4];
        _imageViewRight = new ImageView[4];
        _imageViewLeft[0] = (ImageView) findViewById(R.id.imageViewL);
        _imageViewLeft[1] = (ImageView) findViewById(R.id.imageViewL2);
        _imageViewLeft[2] = (ImageView) findViewById(R.id.imageViewL3);
        _imageViewLeft[3] = (ImageView) findViewById(R.id.imageViewL4);
        _imageViewRight[0] = (ImageView) findViewById(R.id.imageViewR);
        _imageViewRight[1] = (ImageView) findViewById(R.id.imageViewR2);
        _imageViewRight[2] = (ImageView) findViewById(R.id.imageViewR3);
        _imageViewRight[3] = (ImageView) findViewById(R.id.imageViewR4);


        ConstraintLayout constraintLayoutConnect = (ConstraintLayout) findViewById(R.id.constraintLayoutConnectButtons);
        //расставить рандомно правые
       /* ImageSetter imageSetter = new ImageSetter(this);
        //
        imageSetter.setTextView(textViewName);
        //
        constraintLayoutConnect.addView(imageSetter);
        imageSetter.setImageViewR(_imageViewRight);*/

        ViewTreeObserver vto =  _imageViewRight[0].getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
                    _imageViewRight[0].getViewTreeObserver().removeGlobalOnLayoutListener(this);
                else
                    _imageViewRight[0].getViewTreeObserver().removeOnGlobalLayoutListener(this);

                createTask();
            }
        });

        Resources res = getResources();
        int h = res.getDisplayMetrics().heightPixels;
        int w = res.getDisplayMetrics().widthPixels;

        PaintView paintView = new PaintView(this, Color.BLUE, 1000, 5, w, h);

        paintView.setTextView((TextView) findViewById(R.id.textViewState));
        paintView.setimageViewL(_imageViewLeft);
        paintView.setimageViewR(_imageViewRight);

        constraintLayoutConnect.addView(paintView);



    }

    private String get_sTime()
    {
        String alarmTime = "";
        long t = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        alarmTime = format.format(t);
        return alarmTime;
    }
    public void addPoint(Point point, int ind) {
        _arrayPoint.get(ind).add(point);
    }

    public ArrayList getPoints(int ind) {
        return _arrayPoint.get(ind);
    }
    public void deletePoints(int ind)
    {
        _arrayPoint.get(ind).clear();
    }

    public ArrayList getAllPoints()
    {
        return _arrayPoint;
    }

    public void createTask()
    {
        float[] coordX = new float[4];
        float[] coordY = new float[4];
        int id = 0;
        //get possible values
        for(int i = 0; i<4; i++)
        {
            coordX[i] = _imageViewRight[i].getX();
            coordY[i] = _imageViewRight[i].getY();
        }
        //set random values
        int[] used = new int[4];
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i<4; i++)
        {
            int r = random.nextInt(4);
            while(used[r] != 0)
            {
                r++;
                if(r>=4)
                {
                    r = 0;
                }
            }
            used[r] = 1;
            _imageViewRight[i].setX(coordX[r]);
            _imageViewRight[i].setY(coordY[r]);
        }

    }

    public void setFinished()
    {
        _isFinished = true;
        finish();
        /***
         * если не надо, чтобы закрывалось тут активити, то надо убрать finish();
         */
    }

}
