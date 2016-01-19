package com.example.kanpilov.kos_test3;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by KAnpilov on 13.01.2016.
 */
public class Draw2D extends View {

    private Paint mPaint = new Paint();
    private Bitmap mBitmap;

    public Draw2D(Context context){
        super(context);

        Resources res = this.getResources();
        mBitmap = BitmapFactory.decodeResource(res, R.drawable.cat_bottom);
    }




    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(Color.WHITE);
        canvas.drawPaint(mPaint);

        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(250,250,25,mPaint);

        canvas.drawBitmap(mBitmap,120,940,mPaint);


    }
}
