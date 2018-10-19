package com.andy.opengl.demo.game.ability;

import com.andy.opengl.demo.game.base.Ability;
import com.andy.opengl.demo.game.base.Spirit;
import com.andy.opengl.demo.game.factory.BulletFactory;
import com.andy.opengl.demo.game.spirit.BulletSpirit;

/**
 * ShotBulletAbility
 *
 * @author andyqtchen <br/>
 * 发射子弹的能力
 * 创建日期：2018/7/2 18:35
 */
public class ShotBulletAbility implements Ability {
    private final static int SHOT_TIME_INTERVAL = 200;

    private Spirit mBulletShot;
    private BulletFactory mBulletFactory;

    private long mLastShotTime;

    public ShotBulletAbility(Spirit bulletShot, BulletFactory bulletFactory) {
        this.mBulletShot = bulletShot;
        this.mBulletFactory = bulletFactory;
    }

    @Override
    public void onAction(long timestamps) {
        if(timestamps - mLastShotTime> SHOT_TIME_INTERVAL) {
            mLastShotTime = timestamps;
            BulletSpirit bullet = mBulletFactory.acquire();
            bullet.setX(mBulletShot.getX() + (mBulletShot.getWidth() - bullet.getWidth())/2);
            bullet.setY(mBulletShot.getY()-bullet.getHeight());
            bullet.setCamp(mBulletShot.getCamp());
            bullet.addToContainer();
        }
    }
}
