package com.example.endlessrunner.bll.managers;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.endlessrunner.be.Constants;
import com.example.endlessrunner.be.IGameObject;
import com.example.endlessrunner.be.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class ObstacleManager implements IGameObject {

    private List<Obstacle> mObstacles;
    private int mObstacleGap;
    private int mObstacleHeight;
    private int mObstacleWidth;
    private int mColor;

    public ObstacleManager(int obstacleGap, int obstacleHeight, int obstacleWidth, int color){
        mObstacleGap = obstacleGap;
        mObstacleHeight = obstacleHeight;
        mObstacleWidth = obstacleWidth;
        mColor = color;

        mObstacles = new ArrayList<>();

        populateObstacles();
    }

    private void populateObstacles() {
        int currX = 5 * Constants.SCREEN_WIDTH / 3;
        while(currX > Constants.SCREEN_WIDTH){
            mObstacles.add(new Obstacle(mObstacleHeight, mObstacleWidth,
                    (int)(Math.random() * (currX) + Constants.SCREEN_WIDTH),
                    Constants.SCREEN_HEIGHT - 150, mColor));
            currX -= mObstacleGap;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for(Obstacle obj : mObstacles){
            obj.draw(canvas);
        }
    }

    @Override
    public void update() {
        for(Obstacle obj : mObstacles){
            obj.move(Constants.SPEED);
        }

        if(mObstacles.get(mObstacles.size()-1).getRect().right <= 0){
            mObstacles.remove(mObstacles.size() - 1);
            int xStart = (int)(Math.random() * (mObstacleGap + mObstacles.get(0).getRect().right));
            mObstacles.add(0, new Obstacle(mObstacleHeight, mObstacleWidth,
                    Constants.SCREEN_WIDTH + xStart,
                    Constants.SCREEN_HEIGHT - 150, mColor));
        }

    }

    public boolean collisionWithPlayer(Rect playerRect){
        boolean collision = false;
        for (Obstacle obj : mObstacles) {
            if(obj.collisionWithPlayer(playerRect)){
                collision = true;
            }
        }
        return collision;
    }
}
