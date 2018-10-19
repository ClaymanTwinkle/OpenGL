package com.andy.opengl.demo.game.hero;

import android.opengl.GLSurfaceView;

import com.andy.opengl.demo.game.base.SpiritRanderer;

import javax.microedition.khronos.opengles.GL10;

/**
 * GameHeroRenderer
 *
 * @author andyqtchen <br/>
 * todo 实现的主要功能。
 * 创建日期：2018/7/3 19:18
 */
public class GameHeroRenderer extends SpiritRanderer {

    private GameHeroManager mGameHeroManager;
    private boolean mIsStartGame = false;

    public GameHeroRenderer(GLSurfaceView glView) {
        super(glView);
        mGameHeroManager = new GameHeroManager(this);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);
        if(!mIsStartGame) {
            mIsStartGame = true;
            mGameHeroManager.start();
        }
    }
}
