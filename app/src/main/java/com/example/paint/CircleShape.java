package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CircleShape extends TwoDShape  {
    private float cx;
    private float cy;
    private float radius;
    private double surface;

    public CircleShape(int x, int y, String color)
    {
        super(x, y,color);
        this.cx=x;
        this.cy=y;
    }

    @Override
    public void updatePoint(int xe, int ye) {
        radius= (float)(Math.sqrt((Math.pow((xe-cx), 2))+(Math.pow((ye-cy), 2))));
        cx = x;
        cy = y;

    }

        @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas, paint);
        canvas.drawCircle(cx, cy, radius, paint);
    }

    public double getSurface()
    {
        this.surface=Math.pow(radius,2)*Math.PI;
        return this.surface;
    }
}



