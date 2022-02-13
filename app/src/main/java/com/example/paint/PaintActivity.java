package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class PaintActivity extends AppCompatActivity {
    private static final String TAG = "PaintActivity";
    private FrameLayout frame;
    private PaintView paintView;
    private Button clearUndo;
    private Button btnThick;
    private Button btnThin;
    private Button max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        frame = findViewById(R.id.frm);
        paintView = new PaintView(this);
        frame.addView(paintView);
        clearUndo=findViewById(R.id.btnPoint);
        btnThick=findViewById(R.id.btnThicker);
        btnThin=findViewById(R.id.btnThinner);
        max=findViewById(R.id.max);
        clearUndo.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                paintView.clearPath();
                Toast.makeText(PaintActivity.this, "hey", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);

        int id=item.getItemId();

        if(id==R.id.filled)
            paintView.fillShape();
        else if(id==R.id.unfilled)
            paintView.unFillShape();
        return true;
    }

    public void addLine(View view) {
        paintView.addLine();
    }
    public void addRect(View view) {
        paintView.addRect();
    }
    public void addPath(View view) {
        paintView.addPath();
    }
    public void addCircle(View view) {
        paintView.addCircle();
    }
    public void showMax(View view){paintView.showMax();}


    public void thickLine(View view){
        paintView.growWidth();
    }

    public void thinLine(View view){
        paintView.lowerWidth();
    }

    public void changeColor(View view)
    {
        String color = view.getTag().toString();
        paintView.setColor(color);
    }
    
    public void clear(View view) {
        paintView.undo();
    }
}
