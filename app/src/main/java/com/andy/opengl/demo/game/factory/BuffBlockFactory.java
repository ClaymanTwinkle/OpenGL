package com.andy.opengl.demo.game.factory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.andy.opengl.R;
import com.andy.opengl.demo.game.base.SpiritFactory;
import com.andy.opengl.demo.game.base.SpiriteContainer;
import com.andy.opengl.demo.game.spirit.BuffBlockSpirit;

/**
 * BlockFactory
 *
 * @author andyqtchen <br/>
 * todo 实现的主要功能。
 * 创建日期：2018/7/3 19:40
 */
public class BuffBlockFactory extends SpiritFactory<BuffBlockSpirit> {
    private final static int MAX_POOL_SIZE = 5;
    private final Context mContext;
    private final SpiriteContainer mContainer;
    private Bitmap mBitmap;


    public BuffBlockFactory(Context context, SpiriteContainer container) {
        super(MAX_POOL_SIZE);
        this.mContext = context;
        this.mContainer = container;
    }

    @Override
    protected BuffBlockSpirit createSpirit() {
        if (mBitmap == null) {
            mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.energy1);
        }
//        if (mDebuffBitmap == null) {
//            mDebuffBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.energy2);
//        }

        BuffBlockSpirit spirit = null;

        spirit = new BuffBlockSpirit(mContext, this, mContainer, mBitmap);
        spirit.setIsRecyleBitmap(false);
        spirit.setLift(1);
        return spirit;
    }

    @Override
    protected void recycle() {
        if (mBitmap != null && !mBitmap.isRecycled()) {
            mBitmap.recycle();
        }
    }
}
