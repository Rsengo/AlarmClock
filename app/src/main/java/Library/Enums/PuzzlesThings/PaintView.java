package Library.Enums.PuzzlesThings;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ytgv8b.firsttry.DisplayConnectActivity;
import com.example.ytgv8b.firsttry.R;

/**
 * Created by Татьяна on 07.01.2018.
 */

public class PaintView extends View{


        private int linecolor, alpha, lwidth;
        private int WIDTH;
        private int HEIGHT;
        private DisplayConnectActivity act;
        private TextView textView;
        private ImageView[] imageViewL;
        private ImageView[] imageViewR;
        final private int[] _colors = {Color.rgb(163,5,5), Color.rgb(5,163,15), Color.rgb(17,13,158), Color.rgb(84,27,98) };
        private boolean[] _isFinished = {false, false, false, false};
        private int _ind;
        private int _viewsNumb;

        private boolean _isFirst = true;
        private boolean _allowed = false;
        private boolean _isFinishedALL = false;/*головоломка решена*/
        private float _xButton, _yButton, _widthButton, _heightButton;
        private final float _EPS = (float) 20;



        public void setTextView(TextView textView) {
            this.textView = textView;
        }

        public PaintView(DisplayConnectActivity act, int linecolor, int alpha, int lwidth, int width, int height) {
            super(act);
            this.act = act;
            this.linecolor = linecolor;
            this.alpha = alpha;
            this.WIDTH = width;
            this.HEIGHT = height;
            this.lwidth = lwidth;
        }

        public PaintView(Context context) {
            super(context);
        }



        public void getIButtonLCoord(int ind)
        {
            _xButton = imageViewL[ind].getX();
            _yButton = imageViewL[ind].getY();
            _widthButton = imageViewL[ind].getWidth();
            _heightButton = imageViewL[ind].getHeight();
        }
        public void getIButtonRCoord(int ind)
        {
            _xButton = imageViewR[ind].getX();
            _yButton = imageViewR[ind].getY();
            _widthButton = imageViewR[ind].getWidth();
            _heightButton = imageViewR[ind].getHeight();
        }



        @Override
        public boolean onTouchEvent(MotionEvent e) {

            if((_isFirst) && (!(_isFinishedALL)))
            {
                float curX = e.getX();
                float curY = e.getY();
                for(int i = 0; i<_viewsNumb; i++)
                {
                    getIButtonLCoord(i);

                    if((((_xButton-_EPS) <=curX) && (curX <= (_xButton + _widthButton + _EPS))) && (((_yButton - _EPS)<=curY) && (curY<=(_yButton+_heightButton+_EPS)))) {
                        if (!_isFinished[i]) {
                            setLinecolor(_colors[i]);
                            _isFirst = false;
                            _allowed = true;
                            _ind = i;
                            break;
                        }
                    }
                }
            }
            if(_allowed){
                int action = e.getAction();
                if (action == MotionEvent.ACTION_UP) {


                    _allowed = false;
                    _isFirst = true;
                    getIButtonRCoord(_ind);
                    float curX = e.getX();
                    float curY = e.getY();

                    if((!((((_xButton-_EPS) <=curX) && (curX <= (_xButton + _widthButton + _EPS))) && (((_yButton - _EPS)<=curY) && (curY<=(_yButton+_heightButton+_EPS))))) && (!_isFinished[_ind]))
                    {
                        act.deletePoints(_ind);
                        postInvalidate();
                    }
                    act.addPoint(null, _ind);

                } else if (action == MotionEvent.ACTION_MOVE || action == MotionEvent.ACTION_DOWN) {
                    Point point = null;
                    if(action == MotionEvent.ACTION_MOVE )
                    {
                        getIButtonRCoord(_ind);
                        float curX = e.getX();
                        float curY = e.getY();
                        if(((((_xButton-_EPS) <=curX) && (curX <= (_xButton + _widthButton + _EPS))) && (((_yButton - _EPS)<=curY) && (curY<=(_yButton+_heightButton+_EPS))))) {
                            point = new Point(Math.round(_xButton + (_widthButton / 2)), Math.round(_yButton + (_heightButton / 2)), linecolor, alpha, lwidth);
                            _isFinished[_ind] = true;
                            changeImages(_ind);

                            if (_isFinished[0] && _isFinished[1] && _isFinished[2] && _isFinished[3]) {
                                _isFinishedALL = true;
                            /*!!!ГОЛОВОЛОМКА РЕШЕНА!!!*/
                                textView.setText("Верно!");
                                act.setFinished();
                            }
                            act.addPoint(point, _ind);
                            act.addPoint(null, _ind);
                        }
                        else
                        {
                            getIButtonLCoord(_ind);
                            if((!((((_xButton - _EPS) <=curX) && (curX <= (_xButton + _widthButton + _EPS))) && (((_yButton - _EPS)<=curY) && (curY<=(_yButton+_heightButton + _EPS))))) && (!_isFinished[_ind]))
                            {
                                point = new Point(Math.round(e.getX()), Math.round(e.getY()), linecolor, alpha, lwidth);
                                act.addPoint(point, _ind);
                            }
                            else
                            {
                                point = new Point(Math.round(_xButton+(_widthButton/2)), Math.round(_yButton + (_heightButton/2)), linecolor, alpha, lwidth);
                                act.addPoint(point, _ind);
                            }
                        }
                    }
                    else
                    {
                        getIButtonLCoord(_ind);
                        point = new Point(Math.round(_xButton+(_widthButton/2)), Math.round(_yButton + (_heightButton/2)), linecolor, alpha, lwidth);
                        act.addPoint(point, _ind);
                    }
                    postInvalidate();

                }
            }

            return true;
        }

        @Override
        protected void onDraw(Canvas c) {

            Point curr = null;
            //c.drawARGB(33, 56, 255, 130);
            int arr_size = act.getAllPoints().size();
            for(int j = 0; j<arr_size; j++)
            {
                int act_size = act.getPoints(j).size();
                for(int i = 0; i< act_size; i++)
                {
                    Point data = (Point) act.getPoints(j).get(i);
                    if (curr != null && data != null) {
                        Paint paint = new Paint();
                        paint.setColor(data.getColor());
                        paint.setAlpha(1000);
                        paint.setAntiAlias(true);
                        paint.setStrokeWidth(10);

                        c.drawLine(curr.getX(), curr.getY(), data.getX(), data.getY(), paint);

                    }
                    curr = data;
                }
                curr = null;
            }


        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            this.setMeasuredDimension(WIDTH, HEIGHT);
        }

        public void setLinecolor(int linecolor) {
            this.linecolor = linecolor;
        }



        public void setimageViewL(ImageView[] imageViewL) {
            this.imageViewL = imageViewL;
            _viewsNumb = imageViewL.length;
        }

        public void setimageViewR(ImageView[] imageViewR) {
            this.imageViewR = imageViewR;
        }

        private void  changeImages(int ind)
        {
            switch(_ind)
            {
                case 0:
                    imageViewL[_ind].setImageResource(R.drawable.imagebutton11);
                    imageViewR[_ind].setImageResource(R.drawable.imagebutton11);
                    break;
                case 1:
                    imageViewL[_ind].setImageResource(R.drawable.imagebutton22);
                    imageViewR[_ind].setImageResource(R.drawable.imagebutton22);
                    break;
                case 2:
                    imageViewL[_ind].setImageResource(R.drawable.imagebutton33);
                    imageViewR[_ind].setImageResource(R.drawable.imagebutton33);
                    break;
                case 3:
                    imageViewL[_ind].setImageResource(R.drawable.imagebutton44);
                    imageViewR[_ind].setImageResource(R.drawable.imagebutton44);
                    break;
            }


        }

        public boolean getIsFinished()
        {
            return _isFinishedALL;
        }

}
