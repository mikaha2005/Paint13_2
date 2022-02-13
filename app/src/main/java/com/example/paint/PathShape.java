package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class PathShape extends Shape {

    private int xEnd;
    private int yEnd;
    private Path path;

    public PathShape(int x, int y, String color) {
        super(x, y, color);
        xEnd = x;
        yEnd = y;
        path=new Path();
    }


    @Override
    public void updatePoint(int xe, int ye) {
        path.moveTo(xEnd, yEnd);
        path.lineTo(xe, ye);
        xEnd = xe;
        yEnd = ye;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        canvas.drawPath(path,paint);

    }
}
