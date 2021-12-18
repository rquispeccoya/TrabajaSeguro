package com.proyectofinal.trabajoseguro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GraficoBarras extends View {
    int [] cantidad;
    String[] tags;
    int color;


    public GraficoBarras(Context context, int []cantidad, String []tags,int color) {
        super(context);
        this.cantidad=cantidad;
        this.tags=tags;
        this.color=color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint= new Paint();

        pintarEjes(canvas);

        paint.setColor(color);
        paint.setTextSize(10);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        int totalDatos=cantidad.length;

        int totalEspacios=(totalDatos*2)+1;
        int tamEspacios= 1000/totalEspacios;
        int inicio;
        int cont=0;
        paint.setTextSize(tamEspacios/4);
        for (int i=0;i<totalDatos;i++){
            inicio=100+tamEspacios*(i+1+cont);
            Rect rect = new Rect(inicio,500+1000-10*cantidad[i],inicio+tamEspacios,1495);

            canvas.drawRect(rect,paint);
            canvas.drawText(tags[i],inicio,1575,paint);
            cont++;
        }
    }

    private void pintarEjes (Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(),R.color.dark));
        paint.setTextSize(40);
        paint.setStrokeWidth(5);

        canvas.drawLine(100,500,100,1500,paint);
        canvas.drawLine(100,1500,1100,1500,paint);
        for(int i=0;i<=10;i++){
            canvas.drawText(""+(i*10),5,1500-100*i,paint);
        }

    }
}
