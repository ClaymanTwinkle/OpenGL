package com.andy.opengl.demo.game.base;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * LifeSpirit
 *
 * @author andyqtchen <br/>
 * 有生命值的精灵
 * 创建日期：2018/6/28 19:55
 */
public abstract class LifeSpirit extends Spirit implements IColliable {
    private int mMaxLife = 1; // 生命值
    private int mLife = 1;

    public LifeSpirit(Context context, SpiriteContainer container, Bitmap bitmap, int life) {
        super(context, container, bitmap);
        setLift(life);
    }

    /**
     * 判断是否还活着
     *
     * @return true of false
     */
    public boolean isLife() {
        return mLife > 0;
    }

    @Override
    public void onCollide(Spirit fromSpirit) {
        if (isLife() && fromSpirit instanceof IBullet && getCamp() != fromSpirit.getCamp()) {
            mLife -= (((IBullet) fromSpirit).getAggressivity());
        }
    }

    @Override
    public void relese() {
        super.relese();
        mLife = mMaxLife;
    }

    public void setLift(int lift) {
        this.mMaxLife = lift;
        this.mLife = lift;
    }
}
