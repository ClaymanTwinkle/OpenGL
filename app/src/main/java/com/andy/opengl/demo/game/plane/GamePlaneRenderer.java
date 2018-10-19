package com.andy.opengl.demo.game.plane;

import android.opengl.GLSurfaceView;

import com.andy.opengl.demo.game.base.SpiritRanderer;

import javax.microedition.khronos.opengles.GL10;

/**
 * GamePlaneRenderer
 *
 * @author andyqtchen <br/>
 * GameRender
 * 创建日期：2018/6/25 19:00
 */
public class GamePlaneRenderer extends SpiritRanderer {
    private final static String TAG = "GamePlaneRenderer";

    private GamePlaneManager mGamePlaneManager;
    private boolean mIsStartGame = false;

    public GamePlaneRenderer(GLSurfaceView glView) {
        super(glView);
        mGamePlaneManager = new GamePlaneManager(this);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);
        if(!mIsStartGame) {
            mIsStartGame = true;
            mGamePlaneManager.start();
        }
    }
}
