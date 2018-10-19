package com.andy.opengl.demo.game.spirit;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.andy.opengl.demo.game.base.IBullet;
import com.andy.opengl.demo.game.base.Spirit;
import com.andy.opengl.demo.game.base.SpiritFactory;
import com.andy.opengl.demo.game.base.SpiriteContainer;

/**
 * Bullet
 *
 * @author andyqtchen <br/>
 * 子弹
 * 创建日期：2018/6/28 14:39
 */
public class BulletSpirit extends Spirit implements IBullet {
    private SpiritFactory<BulletSpirit> mBulletFactory;

    private int mMoveSpeed = 50; // 移动速度

    public BulletSpirit(Context context, SpiritFactory<BulletSpirit> bulletFactory, SpiriteContainer container, Bitmap bitmap) {
        super(context, container, bitmap);
        this.mBulletFactory = bulletFactory;
        setTouchEnable(false);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        return false;
    }

    @Override
    public int getAggressivity() {
        return 1;
    }

    @Override
    public void onAction(long timestamps) {
        if(isOutScreen()) {
            removeFromContainer();
        } else {
            super.onAction(timestamps);
            setY(getY() - mMoveSpeed);
        }
    }

    public void setMoveSpeed(int moveSpeed) {
        this.mMoveSpeed = moveSpeed;
    }

    @Override
    public void removeFromContainer() {
        super.removeFromContainer();
        mBulletFactory.release(this);
    }
}
