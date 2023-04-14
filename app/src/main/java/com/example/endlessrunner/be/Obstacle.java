package com.example.endlessrunner.be;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.endlessrunner.R;

import java.util.Random;

public class Obstacle implements IGameObject {

    private Rect mRect;
    private int mColor;

    private Bitmap mImage;

    public Obstacle(int rectHeight, int obstacleWidth, int startX, int startY, int color) {
        mRect = new Rect(startX, startY, startX + obstacleWidth, startY + rectHeight);
        mColor = color;

        BitmapFactory bf = new BitmapFactory();

        int[] array = {R.drawable.snow, R.drawable.cookie, R.drawable.candy};
        Random random = new Random();

        int randomIndex = random.nextInt(array.length);
        int randomValue = array[randomIndex];

        mImage = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), randomValue);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(mImage, null, mRect, new Paint());
    }

    @Override
    public void update() {

    }

    public void move(float speed) {
        mRect.left -= speed;
        mRect.right -= speed;
    }

    public Rect getRect() {
        return mRect;
    }

    public boolean collisionWithPlayer(Rect playerRect) {
        return mRect.intersects(playerRect.left, playerRect.top, playerRect.right, playerRect.bottom);
    }
}
