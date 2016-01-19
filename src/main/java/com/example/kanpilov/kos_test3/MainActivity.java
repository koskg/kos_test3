package com.example.kanpilov.kos_test3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

 public class MainActivity extends Activity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
//         Draw2D draw2D = new Draw2D(this);
//         setContentView(draw2D);
         setContentView(new draw4D(this));
     }

     class draw4D extends View{
         Paint p, p2;


         float x = 100;
         float y = 100;
         int side = 50;

         float x2 = 250;
         float y2= 300;
         int sideX2 = 350;
         int sideY2 = 150;

         RectF rectf;
         Path path;

         float[][] mass1 = new float[sideX2][sideY2];

         boolean drag = false;
         boolean drag2 = false;
         float dragX = 0;
         float dragY = 0;


         public draw4D(Context context){
             super(context);

             p = new Paint();
             p.setARGB(255, 0, 153, 204);

             p2  = new Paint();
             p2.setARGB(255, 51, 204, 102);

//             rectf = new RectF(x2, y2, x2+side2+200, y2+side2);

             path = new Path();

         }

         protected void onDraw (Canvas canvas){
             canvas.drawARGB(80, 102, 204, 255);
//             canvas.drawRect(x, y, x + side, y + side, p);


             path.reset();
             path.addRect(x2, y2, x2 + sideX2, y2 + sideY2, Path.Direction.CW);

             canvas.drawPath(path, p2);
             canvas.drawCircle(x, y, side, p);


         }

         @Override
         public boolean onTouchEvent(MotionEvent event){
             float evX = event.getX();
             float evY = event.getY();

             double z;

             z= Math.sqrt( Math.pow((x - evX),2)+Math.pow((y - evY),2));

             switch (event.getAction()) {
                 // касание началось
                 case MotionEvent.ACTION_DOWN:
                     // если касание было начато в пределах квадрата

                     if(evX >= x2 && evX <= x2+sideX2 && evY >= y2  && evY <= y2+sideY2){
                         // включаем режим перетаскивания
                         drag2 = true;
                         // разница между левым верхним углом квадрата и точкой касания
                         dragX = evX - x2;
                         dragY = evY - y2;
                     }

                     if (z<=side) {

                         drag = true;

                         dragX = evX - x;
                         dragY = evY - y;
                     }
                     break;
                 // тащим
                 case MotionEvent.ACTION_MOVE:
                     // если режим перетаскивания включен
                     if (drag) {
                         // определеяем новые координаты для рисования
                         x = evX - dragX;
                         y = evY - dragY;
                         // перерисовываем экран
                         invalidate();

                     }
                     if (drag2) {
                         // определеяем новые координаты для рисования
                         x2 = evX - dragX;
                         y2 = evY - dragY;

                         // перерисовываем экран
                         invalidate();
//                         if(x >= x2 && x <= (x2+side2+200) && y >= y2 && y <= (y2 + side2)){
//                             drag = false;
//                             drag2 = false;
//                             break;
 //                        }
                     }
                     break;
                 // касание завершено
                 case MotionEvent.ACTION_UP:
                     // выключаем режим перетаскивания
                     drag = false;
                     drag2 = false;
                     break;
             }
             return true;
         }
     }


 }



