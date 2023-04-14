package com.example.endlessrunner.bll.scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.endlessrunner.be.Constants;
import com.example.endlessrunner.be.Floor;
import com.example.endlessrunner.bll.IScene;
import com.example.endlessrunner.bll.managers.SceneManager;

public class Menu implements IScene {

    private Floor mFloor;

    private Rect mTextRect;

    public Menu(){
        mFloor = new Floor(new Rect());
        mTextRect = new Rect();
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#314A82"));
        mFloor.draw(canvas);
        drawText(canvas, "Noel Run", "Nhấn vào bất cứ đâu để bắt đầu");
    }

    @Override
    public void terminate() {

    }

    @Override
    public void recieveTouch(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                SceneManager.ACTIVE_SCENE = Constants.GAME_SCENE;
                break;
            }
        }
    }

    private void drawText(Canvas canvas, String headLine, String text){
        Paint paint = new Paint();
        paint.setTextSize(200);
        paint.setColor(Color.WHITE);
        paint.setShadowLayer(5, 0, 0, Color.BLACK);

        paint.setTextAlign(Paint.Align.CENTER);
        canvas.getClipBounds(mTextRect);
        int cHeight = mTextRect.height();
        int cWidth = mTextRect.width();
        paint.getTextBounds(headLine, 0, headLine.length(), mTextRect);
        float x = cWidth / 2f;
        float y = cHeight / 4f;
        canvas.drawText(headLine, x, y, paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(75);
        y = cHeight / 2f;
        canvas.drawText(text, x, y, paint);
    }
}

