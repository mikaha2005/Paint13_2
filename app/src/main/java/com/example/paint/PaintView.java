package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Stack;

public class PaintView extends View {

    private Paint paint;
    private Paint bgPaint;
    private String currentShape = "Rect";
    private String currentColor = "#FFFFFFFF";


    public Stack<Shape> shapes;
    public Stack<Shape> helper;
    public Stack<TwoDShape> helper2;
    private int width=12;

    public PaintView(Context context) {
        super(context);
        shapes = new Stack<>();
        helper2=new Stack<>();
        helper=new Stack<>();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        bgPaint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(width);
        bgPaint.setColor(Color.rgb(255,255,255));

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(bgPaint);
        for (int i = 0; i < shapes.size(); i++)
            shapes.get(i).draw(canvas, paint);
    }

    Shape shape;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(currentShape.equals("Rect"))
            {
                shape = new RectShape((int)event.getX(),(int)event.getY(),currentColor);
            }

            if(currentShape.equals("Circle"))
            {
                shape = new CircleShape((int)event.getX(),(int)event.getY(),currentColor);
            }

            if(currentShape.equals("Line"))
            {
                shape = new LineShape((int)event.getX(),(int)event.getY(),currentColor);
            }
            if(currentShape.equals("Path"))
            {
                shape = new PathShape((int)event.getX(),(int)event.getY(),currentColor);
            }
            shapes.push(shape);
            invalidate();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            shape.updatePoint((int)event.getX(),(int)event.getY());
            invalidate();
        }
        return true;
    }

    public void addLine() {
        currentShape = "Line";
    }
    public void clearPath()
    {
        while(!shapes.isEmpty()) {
            if (!(shapes.peek() instanceof PathShape)) //אם  הוא לא מסוג path
                helper.push(shapes.pop());
            else
                shapes.pop();
        }
        while(!helper.isEmpty())
            shapes.push(helper.pop());
        invalidate();
    }
    public void showMax()
    {
        double maxSur=0.0;
        while(!shapes.isEmpty()){
            if(!(shapes.peek() instanceof TwoDShape))
                shapes.pop();
            else
                helper2.push((TwoDShape) shapes.pop());
        }
        while(!helper2.isEmpty())
        {
            double curSur=helper2.peek().getSurface();
            if(curSur>maxSur) {
                maxSur = curSur;
                if(helper.isEmpty())
                    helper.push(helper2.pop());
                else {
                        helper.pop();
                    helper.push(helper2.pop());
                }
            }
            else
                helper2.pop();
        }
        shapes.push(helper.pop());
        invalidate();
        }




    public void addRect() {
        currentShape = "Rect";
    }

    public void addCircle() {
        currentShape = "Circle";
    }
    public void addPath() {
        currentShape = "Path";
    }

    public void setColor(String color)
    {
        currentColor = color;
    }
    public void fillShape()
    {
        shape.setFilled(true);
        shape.ifFilled();

    }

    public void unFillShape() {
        shape.setFilled(false);
        shape.ifFilled();
    }

    public void growWidth()
    {
        this.width++;
        this.paint.setStrokeWidth(width);
    }
    public void lowerWidth()
    {
        this.width--;
        this.paint.setStrokeWidth(width);
    }


    public void undo()
    {
        if(!shapes.isEmpty()) {
            shapes.pop();
            invalidate();
        }
    }


}
