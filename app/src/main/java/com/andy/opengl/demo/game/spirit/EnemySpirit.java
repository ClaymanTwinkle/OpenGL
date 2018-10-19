package com.andy.opengl.demo.game.spirit;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.andy.opengl.demo.game.base.IBullet;
import com.andy.opengl.demo.game.base.LifeSpirit;
import com.andy.opengl.demo.game.base.SpiritFactory;
import com.andy.opengl.demo.game.base.SpiriteContainer;

/**
 * EnemyPlane
 *
 * @author andyqtchen <br/>
 * 敌对精灵
 * 创建日期：2018/6/28 12:05
 */
public class EnemySpirit extends LifeSpirit implements IBullet {
    private SpiritFactory<EnemySpirit> mEnemyFactory;

    public EnemySpirit(Context context, SpiritFactory<EnemySpirit> enemyFactory, SpiriteContainer container, Bitmap bitmap) {
        super(context, container, bitmap, 10);
        this.mEnemyFactory = enemyFactory;
        setCamp(CAMP_ENEMY);
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
        if(isLife()) {
            super.onAction(timestamps);
        } else {
            removeFromContainer();
        }
    }

    @Override
    public void removeFromContainer() {
        super.removeFromContainer();
        mEnemyFactory.release(this);
    }
}
