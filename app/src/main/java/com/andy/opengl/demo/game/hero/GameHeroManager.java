package com.andy.opengl.demo.game.hero;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.andy.opengl.R;
import com.andy.opengl.demo.game.ability.BornBlockAbility;
import com.andy.opengl.demo.game.ability.ShotBulletAbility;
import com.andy.opengl.demo.game.base.GameManager;
import com.andy.opengl.demo.game.base.IColliable;
import com.andy.opengl.demo.game.base.Spirit;
import com.andy.opengl.demo.game.factory.BuffBlockFactory;
import com.andy.opengl.demo.game.factory.BulletFactory;
import com.andy.opengl.demo.game.factory.DebuffBlockFactory;
import com.andy.opengl.demo.game.spirit.MineSpirit;
import com.andy.opengl.demo.game.spirit.StageSpirit;
import com.andy.opengl.demo.game.util.GameUtil;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * GameHeroManager
 *
 * @author andyqtchen <br/>
 * todo 实现的主要功能。
 * 创建日期：2018/7/3 19:21
 */
public class GameHeroManager extends GameManager {

    private GameHeroRenderer mGameHeroRenderer;

    private BuffBlockFactory mBuffBlockFactory;

    private DebuffBlockFactory mDebuffBlockFactory;

    private BulletFactory mBulletFactory;

    private List<Spirit> mSpiritList = new CopyOnWriteArrayList<Spirit>();
    private List<IColliable> mColliableSpiritList = new CopyOnWriteArrayList<IColliable>();

    private Timer mTimer;

    private AtomicBoolean mIsRunning = new AtomicBoolean(false);

    public GameHeroManager(GameHeroRenderer gameHeroRenderer) {
        this.mGameHeroRenderer = gameHeroRenderer;

        Context context = gameHeroRenderer.getContext();

        mBuffBlockFactory = new BuffBlockFactory(context, this);

        mDebuffBlockFactory = new DebuffBlockFactory(context, this);

        mBulletFactory = new BulletFactory(context, this, BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet2));

        mTimer = new Timer();

        StageSpirit stageSpirit = new StageSpirit(context, this);
        //stageSpirit.addAbility(new BornEnemyAbility(stageSpirit, mEnemyFactory));
        stageSpirit.addAbility(new BornBlockAbility(mBuffBlockFactory, mDebuffBlockFactory));
        stageSpirit.addToContainer();

        MineSpirit minePlaneSpirit = new MineSpirit(context, this, BitmapFactory.decodeResource(context.getResources(), R.drawable.hero));
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
    public void addSpirit(Spirit spirit) {
        mSpiritList.add(spirit);
        mGameHeroRenderer.addFilter(spirit);
        if(spirit instanceof IColliable) {
            mColliableSpiritList.add((IColliable)spirit);
        }
    }

    @Override
    public void removeSpirit(Spirit spirit) {
        mSpiritList.remove(spirit);
        mGameHeroRenderer.removeFilter(spirit);
        if(spirit instanceof IColliable) {
            mColliableSpiritList.remove((IColliable)spirit);
        }
    }

    @Override
    public void run() {
        mGameHeroRenderer.getGLView().queueEvent(new Runnable() {
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
                mGameHeroRenderer.getGLView().requestRender();
            }
        });
    }
}
