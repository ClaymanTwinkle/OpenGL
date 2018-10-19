package com.andy.opengl.demo.game.factory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.andy.opengl.R;
import com.andy.opengl.demo.game.base.SpiritFactory;
import com.andy.opengl.demo.game.base.SpiriteContainer;
import com.andy.opengl.demo.game.spirit.EnemySpirit;

/**
 * EnemyFactory
 *
 * @author andyqtchen <br/>
 * 敌机工厂
 * 创建日期：2018/6/28 18:21
 */
public class EnemyFactory extends SpiritFactory<EnemySpirit> {
    private final static int MAX_POOL_SIZE = 10;
    private final Context mContext;
    private final SpiriteContainer mContainer;
    private Bitmap mSpiritBitmap;

    public EnemyFactory(Context context, SpiriteContainer container) {
        super(MAX_POOL_SIZE);
        this.mContext = context;
        this.mContainer = container;
    }

    @Override
    protected EnemySpirit createSpirit() {
        if(mSpiritBitmap == null) {
            mSpiritBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.enemy);
        }
        EnemySpirit bulletSpirit = new EnemySpirit(mContext,this, mContainer, mSpiritBitmap);
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
