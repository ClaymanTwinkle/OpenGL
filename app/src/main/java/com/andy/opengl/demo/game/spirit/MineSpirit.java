package com.andy.opengl.demo.game.spirit;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.andy.opengl.demo.game.base.LifeSpirit;
import com.andy.opengl.demo.game.base.SpiriteContainer;

/**
 * MineSpirit
 *
 * @author andyqtchen <br/>
 * 我方
 * 创建日期：2018/6/25 19:35
 */
public class MineSpirit extends LifeSpirit{

    public MineSpirit(Context context, SpiriteContainer container, Bitmap bitmap) {
        super(context, container, bitmap, 10);
        setCamp(CAMP_FRIENDLY);
    }

    private float mTouchStartX = 0;
    private float mTouchStartY = 0;

    @Override
    public boolean onTouch(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchStartX = x;
                mTouchStartY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                translateX((int) (x - mTouchStartX));
                translateY((int) (y - mTouchStartY));
                mTouchStartX = x;
                mTouchStartY = y;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }
}
