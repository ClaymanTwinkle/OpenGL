package com.andy.opengl.demo.game.factory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.andy.opengl.R;
import com.andy.opengl.demo.game.base.SpiritFactory;
import com.andy.opengl.demo.game.base.SpiriteContainer;
import com.andy.opengl.demo.game.spirit.BulletSpirit;

/**
 * BulletPool
 *
 * @author andyqtchen <br/>
 * 子弹池
 * 创建日期：2018/6/28 14:51
 */
public class BulletFactory extends SpiritFactory<BulletSpirit> {
    private final static int MAX_POOL_SIZE = 50;
    private final Context mContext;
    private final SpiriteContainer mContainer;
    private Bitmap mSpiritBitmap;

    public BulletFactory(Context context, SpiriteContainer container) {
        this(context, container, BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet));
    }

    public BulletFactory(Context context, SpiriteContainer container, Bitmap bitmap) {
        super(MAX_POOL_SIZE);
        this.mContext = context;
        this.mContainer = container;
        this.mSpiritBitmap = bitmap;
    }

    @Override
    protected BulletSpirit createSpirit() {
        BulletSpirit bulletSpirit = new BulletSpirit(mContext,this, mContainer, mSpiritBitmap);
        bulletSpirit.setIsRecyleBitmap(false);
        return bulletSpirit;
    }

    @Override
    protected void recycle() {
        if (mSpiritBitmap != null && !mSpiritBitmap.isRecycled()) {
            mSpiritBitmap.recycle();
        }
    }
}
