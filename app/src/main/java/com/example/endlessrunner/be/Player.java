package com.example.endlessrunner.be;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.endlessrunner.R;
import com.example.endlessrunner.bll.animation.Animation;
import com.example.endlessrunner.bll.animation.AnimationManager;

public class Player implements IGameObject {

    private Rect mRect;
    private int mColor;

    private Animation mRunAnimation;
    private Animation mJumpAnimation;

    private AnimationManager mAnimationManager;

    public Player(Rect rect, int color){
        mRect = rect;
        mColor = color;
        createStickAnimations();

        mAnimationManager = new AnimationManager(new Animation[]{mRunAnimation, mJumpAnimation});
    }

    @Override
    public void draw(Canvas canvas) {
        mAnimationManager.draw(canvas, mRect);
    }

    @Override
    public void update() {
        mAnimationManager.update();
    }

    public void update(Point point, boolean isJumping){
        //Left, Top, Right, Back
        mRect.set(point.x - mRect.width()/2,
                point.y - mRect.height()/2,
                point.x + mRect.width()/2,
                point.y + mRect.height()/2);

        if(isJumping){
            mAnimationManager.playAnimation(1);
            mAnimationManager.update();
        }else{
            mAnimationManager.playAnimation(0);
            mAnimationManager.update();
        }
    }

    public Rect getRect(){
        return mRect;
    }

    private void createStickAnimations(){
        BitmapFactory bf = new BitmapFactory();
        Bitmap img1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.santa);
        Bitmap img2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.santa);
        Bitmap img3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.santa);
        Bitmap img4 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.santa);
        Bitmap img5 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.santa);
        Bitmap img6 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.santa);
        Bitmap img7 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.santa);
        Bitmap img8 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.santa);
        Bitmap img9 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.santa);
        Bitmap img10 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.santa);
        Bitmap img11 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.santa);
        Bitmap img12 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.santa);

        mRunAnimation = new Animation(new Bitmap[]{img1, img2, img3, img4, img5, img6,
                img7, img8, img9, img10, img11, img12},
                0.5f);

        mJumpAnimation = new Animation(new Bitmap[]{img12}, 2);
    }

}

