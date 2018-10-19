package com.andy.opengl.demo.game.ability;

import com.andy.opengl.demo.game.base.Ability;
import com.andy.opengl.demo.game.base.Spirit;
import com.andy.opengl.demo.game.factory.EnemyFactory;
import com.andy.opengl.demo.game.spirit.EnemySpirit;

/**
 * BornEnemyAbility
 *
 * @author andyqtchen <br/>
 * 生敌人的能力
 * 创建日期：2018/7/2 21:25
 */
public class BornEnemyAbility implements Ability {
    private final static int BORN_TIME_INTERVAL = 60*60*1000;

    private Spirit mEnemyMother;
    private EnemyFactory mEnemyFactory;

    private long mLastBornTime;

    public BornEnemyAbility(Spirit enemyMother, EnemyFactory enemyFactory) {
        this.mEnemyMother = enemyMother;
        this.mEnemyFactory = enemyFactory;
    }

    @Override
    public void onAction(long timestamps) {
        if(timestamps - mLastBornTime> BORN_TIME_INTERVAL) {
            mLastBornTime = timestamps;
            EnemySpirit enemyPlane = mEnemyFactory.acquire();
            enemyPlane.setX(Spirit.sScreenWidth/2 - enemyPlane.getWidth()/2);
            enemyPlane.setY(0);
            SwingMoveAbility swingMoveAbility = new SwingMoveAbility(enemyPlane, 20);
            enemyPlane.addAbility(swingMoveAbility);
            enemyPlane.addToContainer();
        }
    }
}
