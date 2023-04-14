package com.example.endlessrunner.be;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.endlessrunner.R;

import java.util.ArrayList;
import java.util.List;

public class Floor implements IGameObject{
    private final int WIDTH = 100;
    private final int Y_COORDINATE = Constants.SCREEN_HEIGHT - 50;

    private Rect mRect;

    private List<Rect> mRectList;
    private Bitmap mFloorImage;

    public Floor(Rect rect){
        mRect = rect;
        mRect.set(0, Y_COORDINATE, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        BitmapFactory bf = new BitmapFactory();
        mFloorImage = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bgplay);

        mRectList = new ArrayList<>();
        for(int i = 0; i < Constants.SCREEN_WIDTH + (Constants.SCREEN_WIDTH/ 2); i += WIDTH){
            mRectList.add(new Rect(i, Y_COORDINATE, i + WIDTH, Constants.SCREEN_HEIGHT));
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for(Rect rect : mRectList){
            canvas.drawBitmap(mFloorImage, null, rect, new Paint());
        }
    }

    @Override
    public void update() {
        for(Rect rect : mRectList){
            rect.set(rect.left - (int)Constants.SPEED, Y_COORDINATE, rect.right - (int)Constants.SPEED, Constants.SCREEN_HEIGHT);
        }
        if(mRectList.get(0).right <= 0){
            Rect rect = mRectList.remove(0);
            int x = mRectList.get(mRectList.size() - 1).right;
            rect.set(x, Y_COORDINATE, x + WIDTH, Constants.SCREEN_HEIGHT);
            mRectList.add(rect);

        }
    }

    public Rect getRect(){
        return mRect;
    }
}
