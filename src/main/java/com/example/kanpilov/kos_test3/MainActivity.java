package com.example.kanpilov.kos_test3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
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

    class Figure1 {
        float fX;
        float fY;
    }

    class Kvadrat extends Figure1{

        float sideX;
        float sideY;
        float fX2;
        float fY2;

        public Kvadrat(float x, float y, float sx, float sy){
            fX=x;
            fY=y;
            sideX=sx;
            sideY=sy;
            fX2 = x + sx;
            fY2 = y + sy;
        }
    }
     class Circle extends Figure1{
         float fRadius;

         public Circle(float x, float y, float r){
             fX=x;
             fY=y;
             fRadius=r;
         }
     }

     class draw4D extends View{

         Paint p, p2;

         Circle krug = new Circle(400,500,50);
         Kvadrat kvad = new Kvadrat(100,100,200,100);


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

         }

         protected void onDraw (Canvas canvas){
             canvas.drawARGB(80, 102, 204, 255);

             canvas.drawRect(kvad.fX, kvad.fY, kvad.fX2, kvad.fY2, p2);

             canvas.drawCircle(krug.fX, krug.fY, krug.fRadius,p);

         }

         @Override
         public boolean onTouchEvent(MotionEvent event){
             float evX = event.getX();
             float evY = event.getY();

             double z;

             z= Math.sqrt( Math.pow((krug.fX - evX),2)+Math.pow((krug.fY - evY),2));

             switch (event.getAction()) {
                 // касание началось
                 case MotionEvent.ACTION_DOWN:
                     // если касание было начато в пределах квадрата

                     if (z<=krug.fRadius) {
                         drag = true;

                         dragX = evX - krug.fX;
                         dragY = evY - krug.fY;
                     }

                     if(evX >= kvad.fX && evX <= kvad.fX2 && evY >= kvad.fY  && evY <= kvad.fY2){
                         // включаем режим перетаскивания
                         drag2 = true;
                         // разница между левым верхним углом квадрата и точкой касания
                         dragX = evX - kvad.fX;
                         dragY = evY - kvad.fY;
                     }

                     break;
                 // тащим
                 case MotionEvent.ACTION_MOVE:
                     // если режим перетаскивания включен
                     if (drag) {
                         // определеяем новые координаты для рисования
                         krug.fX = evX - dragX;
                         krug.fY = evY - dragY;
                         // перерисовываем экран
                         invalidate();
                     }
                     if (drag2) {
                         // определеяем новые координаты для рисования
                         kvad.fX = evX - dragX;
                         kvad.fY = evY - dragY;

                         kvad.fX2 = evX - dragX + kvad.sideX;
                         kvad.fY2 = evY - dragY + kvad.sideY;
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



