package com.andy.opengl.demo.game.ability;

import com.andy.opengl.demo.game.base.Ability;
import com.andy.opengl.demo.game.base.Spirit;
import com.andy.opengl.demo.game.factory.BuffBlockFactory;
import com.andy.opengl.demo.game.factory.DebuffBlockFactory;

import java.util.Random;

/**
 * BornBlockAbility
 *
 * @author andyqtchen <br/>
 * 方块出生能力
 * 创建日期：2018/7/3 19:36
 */
public class BornBlockAbility implements Ability {
    private final static int BORN_TIME_INTERVAL = 100;

    private BuffBlockFactory mBuffBlockFactory;

    private DebuffBlockFactory mDebuffBlockFactory;

    private long mLastBornTime;

    private Random mRandom;

    public BornBlockAbility(BuffBlockFactory buffBlockFactory, DebuffBlockFactory debuffBlockFactory) {
        this.mBuffBlockFactory = buffBlockFactory;
        this.mDebuffBlockFactory = debuffBlockFactory;

        mRandom = new Random();
    }

    @Override
    public void onAction(long timestamps) {
        if (timestamps - mLastBornTime > BORN_TIME_INTERVAL) {
            mLastBornTime = timestamps;
            Spirit blockSpirit;
            if (isBuffBlockBorn()) {
                blockSpirit = mBuffBlockFactory.acquire();
            } else {
                blockSpirit = mDebuffBlockFactory.acquire();
            }
            if (blockSpirit != null) {
                mRandom.setSeed(System.currentTimeMillis());
                int randomX = mRandom.nextInt(Spirit.sScreenWidth - blockSpirit.getWidth());
                blockSpirit.setX(randomX);
                blockSpirit.setY(0);
                blockSpirit.addToContainer();
            }
        }
    }

    private boolean isBuffBlockBorn() {
        mRandom.setSeed(System.currentTimeMillis());
        int randomInt = mRandom.nextInt(10);
        return randomInt > 2;
    }
}
