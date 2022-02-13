package com.example.paint;

public class TwoDShape  extends Shape{
    protected int x;
    protected int y;
    protected String color;
    private double surface;

    public TwoDShape(int x, int y, String color) {
        super(x, y, color);
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void updatePoint(int xe, int ye) {

    }

    public double getSurface() {
        return surface;
    }

}
