package com.andy.opengl.demo.game.plane;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.andy.opengl.R;
import com.andy.opengl.demo.game.ability.BornEnemyAbility;
import com.andy.opengl.demo.game.ability.ShotBulletAbility;
import com.andy.opengl.demo.game.base.GameManager;
import com.andy.opengl.demo.game.base.IColliable;
import com.andy.opengl.demo.game.base.Spirit;
import com.andy.opengl.demo.game.factory.BulletFactory;
import com.andy.opengl.demo.game.factory.EnemyFactory;
import com.andy.opengl.demo.game.spirit.MineSpirit;
import com.andy.opengl.demo.game.spirit.StageSpirit;
import com.andy.opengl.demo.game.util.GameUtil;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * GamePlaneManager
 *
 * @author andyqtchen <br/>
 * 游戏舞台
 * 创建日期：2018/6/28 19:11
 */
public class GamePlaneManager extends GameManager{
    private GamePlaneRenderer mSpiritRanderer;

    private EnemyFactory mEnemyFactory;
    private BulletFactory mBulletFactory;

    private List<Spirit> mSpiritList = new CopyOnWriteArrayList<Spirit>();
    private List<IColliable> mColliableSpiritList = new CopyOnWriteArrayList<IColliable>();


    private Timer mTimer;

    private AtomicBoolean mIsRunning = new AtomicBoolean(false);

    public GamePlaneManager(GamePlaneRenderer spiritRanderer) {
        this.mSpiritRanderer = spiritRanderer;

        Context context = spiritRanderer.getContext();

        mEnemyFactory = new EnemyFactory(context, this);
        mBulletFactory = new BulletFactory(context, this);

        mTimer = new Timer();

        StageSpirit stageSpirit = new StageSpirit(context, this);
        stageSpirit.addAbility(new BornEnemyAbility(stageSpirit, mEnemyFactory));
        stageSpirit.addToContainer();

        MineSpirit minePlaneSpirit = new MineSpirit(context, this, BitmapFactory.decodeResource(context.getResources(), R.drawable.mine_plane));
        minePlaneSpirit.addAbility(new ShotBulletAbility(minePlaneSpirit, mBulletFactory));
        minePlaneSpirit.setX(Spirit.sScreenWidth/2);
        minePlaneSpirit.setY(Spirit.sScreenHeight - minePlaneSpirit.getHeight());
        minePlaneSpirit.addToContainer();
    }

    @Override
    public void start() {
        mIsRunning.set(true);
        mTimer.schedule(this, 0, 16);
    }

    @Override
    public void stop() {
        mIsRunning.set(false);
        cancel();
    }

    @Override
    public void run() {
        mSpiritRanderer.getGLView().queueEvent(new Runnable() {
            @Override
            public void run() {
                if(!mIsRunning.get()) {
                    return;
                }
                for(IColliable colliable : mColliableSpiritList) {
                    for(Spirit spirit : mSpiritList) {
                        if(colliable == spirit) {
                            continue;
                        }
                        if(GameUtil.isCollide((Spirit) colliable, spirit)) {
                            colliable.onCollide(spirit);
                        }
                    }
                }
                for (Spirit spirit : mSpiritList) {
                    spirit.onAction(System.currentTimeMillis());
                }
                mSpiritRanderer.getGLView().requestRender();
            }
        });
    }

    @Override
    public void addSpirit(Spirit spirit) {
        mSpiritList.add(spirit);
        mSpiritRanderer.addFilter(spirit);
        if(spirit instanceof IColliable) {
            mColliableSpiritList.add((IColliable)spirit);
        }
    }

    @Override
    public void removeSpirit(Spirit spirit) {
        mSpiritList.remove(spirit);
        mSpiritRanderer.removeFilter(spirit);
        if(spirit instanceof IColliable) {
            mColliableSpiritList.remove((IColliable)spirit);
        }
    }
}
