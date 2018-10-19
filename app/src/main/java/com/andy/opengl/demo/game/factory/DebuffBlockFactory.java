package com.andy.opengl.demo.game.factory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.andy.opengl.R;
import com.andy.opengl.demo.game.base.SpiritFactory;
import com.andy.opengl.demo.game.base.SpiriteContainer;
import com.andy.opengl.demo.game.spirit.DebuffBlockSpirit;

/**
 * DebuffBlockFactory
 *
 * @author andyqtchen <br/>
 * todo 实现的主要功能。
 * 创建日期：2018/7/3 19:40
 */
public class DebuffBlockFactory extends SpiritFactory<DebuffBlockSpirit> {
    private final static int MAX_POOL_SIZE = 5;
    private final Context mContext;
    private final SpiriteContainer mContainer;
    private Bitmap mBitmap;


    public DebuffBlockFactory(Context context, SpiriteContainer container) {
        super(MAX_POOL_SIZE);
        this.mContext = context;
        this.mContainer = container;
    }

    @Override
    protected DebuffBlockSpirit createSpirit() {
        if (mBitmap == null) {
            mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.energy2);
        }

        DebuffBlockSpirit spirit = null;

        spirit = new DebuffBlockSpirit(mContext, this, mContainer, mBitmap);
        spirit.setIsRecyleBitmap(false);
        return spirit;
    }

    @Override
    protected void recycle() {
        if (mBitmap != null && !mBitmap.isRecycled()) {
            mBitmap.recycle();
        }
    }
}
