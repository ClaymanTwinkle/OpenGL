package com.andy.opengl.demo.game.spirit;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.andy.opengl.demo.game.base.LifeSpirit;
import com.andy.opengl.demo.game.base.SpiritFactory;
import com.andy.opengl.demo.game.base.SpiriteContainer;

/**
 * BlockSpirit
 *
 * @author andyqtchen <br/>
 * 能量方块
 * 创建日期：2018/7/3 19:39
 */
public class BuffBlockSpirit extends LifeSpirit {
    private SpiritFactory<BuffBlockSpirit> mBlockFactory;

    private int mMoveSpeed = 15; // 移动速度

    public BuffBlockSpirit(Context context, SpiritFactory<BuffBlockSpirit> blockFactory, SpiriteContainer container, Bitmap bitmap) {
        super(context, container, bitmap, 1);
        this.mBlockFactory = blockFactory;
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        return false;
    }

    @Override
    public void onAction(long timestamps) {
        if(isOutScreen() || !isLife()) {
            removeFromContainer();
        } else {
            super.onAction(timestamps);
            setY(getY() + mMoveSpeed);
        }
    }

    @Override
    public void removeFromContainer() {
        super.removeFromContainer();
        mBlockFactory.release(this);
    }
}
