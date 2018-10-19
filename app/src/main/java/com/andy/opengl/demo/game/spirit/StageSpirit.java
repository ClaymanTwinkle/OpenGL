package com.andy.opengl.demo.game.spirit;

import android.content.Context;
import android.opengl.GLES20;
import android.view.MotionEvent;

import com.andy.opengl.demo.game.base.Spirit;
import com.andy.opengl.demo.game.base.SpiriteContainer;

/**
 * ScreenSpirit
 *
 * @author andyqtchen <br/>
 * 屏幕
 * 创建日期：2018/6/28 16:58
 */
public class StageSpirit extends Spirit {
    public StageSpirit(Context context, SpiriteContainer container) {
        super(context,container, null);
    }

    @Override
    protected void onClear() {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // 背景涂黑
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        return false;
    }
}
