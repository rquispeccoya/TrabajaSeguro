
package com.proyectofinal.trabajoseguro.model.customVista;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.proyectofinal.trabajoseguro.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GraficoBarras extends View {
    int [] cantidad;
    String[] tags;
    int color;
    private DisplayMetrics displayMetrics;
    private double anchoT;
    private double altoT;

    public GraficoBarras(Context context, int []cantidad, String []tags,int color) {
        super(context);
        this.cantidad=cantidad;
        this.tags=tags;
        this.color=color;

        displayMetrics =getResources().getDisplayMetrics();
        anchoT=displayMetrics.widthPixels/1080d;
        altoT=displayMetrics.heightPixels/2128d;
        //anchoT=720/1080d;
        //altoT=1280/2128d;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint= new Paint();

        pintarEjes(canvas);

        paint.setColor(color);
        paint.setTextSize((int)(10*anchoT));

        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        int totalDatos=cantidad.length;

        int totalEspacios=(totalDatos*2)+1;
        double tamEspacios= (1000d/totalEspacios)*anchoT;
        int inicio;
        int cont=0;
        paint.setTextSize((int)tamEspacios/4);
        for (int i=0;i<totalDatos;i++){
            inicio=(int)((100+tamEspacios*(i+1+cont))*anchoT);
            Rect rect = new Rect(inicio,(int)((500+1000-10*cantidad[i])*altoT),(int)((inicio+tamEspacios)*anchoT),(int)((1495)*altoT));

            canvas.drawRect(rect,paint);
            canvas.drawText(tags[i],inicio,(int)((1575)*altoT),paint);
            canvas.drawText(String.valueOf(cantidad [i]),(int)((inicio+(tamEspacios)/2)*anchoT),(int)((500+1000-10*cantidad[i]-10)*altoT),paint);
            cont++;
        }
    }

    private void pintarEjes (Canvas canvas){

        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.dark));
        paint.setTextSize((int)(40*anchoT));
        paint.setStrokeWidth(5);

        //int anchoDeseado =1080;
        //int altoDeseado = 2128;

        canvas.drawLine((int)(100*anchoT),(int)(500*altoT),(int)(100*anchoT),(int)(1500*altoT),paint);
        canvas.drawLine((int)(100*anchoT),(int)(1500*altoT),(int)(1100*anchoT),(int)(1500*altoT),paint);
        for(int i=0;i<=10;i++){
            canvas.drawText(""+(i*10),(int)(5*anchoT),(int)((1500-100*i)*altoT),paint);
        }

    }

}