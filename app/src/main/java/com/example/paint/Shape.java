package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class Shape {
    protected int x;
    protected int y;
    protected String color;
    protected boolean filled;
    private Paint paint;

    public Shape(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.filled=false;

    }

    public abstract void updatePoint(int xe,int ye);

    public void setFilled(boolean filled) {
        this.filled=filled;
    }

    public void ifFilled()
    {
        if(this.filled==true)
            paint.setStyle(Paint.Style.FILL);
        else
            paint.setStyle(Paint.Style.STROKE);
    }

    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(Color.parseColor(color));
    }
}