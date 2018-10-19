package com.andy.opengl.demo.game.base;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.view.MotionEvent;
import android.view.View;

import com.andy.opengl.filters.IFilter;
import com.andy.opengl.renderer.FilterRenderer;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * SpiritRanderer
 *
 * @author andyqtchen <br/>
 * SpiritRanderer
 * 创建日期：2018/6/25 19:29
 */
public class SpiritRanderer extends FilterRenderer implements View.OnTouchListener {
    private float[] mProjectMatrix = new float[16];
    private float[] mCameraMatrix = new float[16];
    private float[] mMVPMatrix = new float[16];

    private GLSurfaceView mGLView;

    public SpiritRanderer(GLSurfaceView glView) {
        this.mGLView = glView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean isOnTouch = false;

        List<IFilter> filterList = getFilterList();
        if (!filterList.isEmpty()) {
            for (IFilter filter : filterList) {
                if (filter instanceof Spirit) {
                    Spirit spirit = (Spirit) filter;
                    if (spirit.isTouchEnable() && spirit.isInTouch(event) && spirit.onTouch(event)) {
                        isOnTouch = true;
                    }
                }
            }
        }
        //((GLSurfaceView) v).requestRender();
        return isOnTouch;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);

        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width/height;
        Matrix.orthoM(mProjectMatrix, 0, -1, 1, -ratio, ratio, 3, 7);
        Matrix.setLookAtM(mCameraMatrix, 0, 0, 0, 3, 0, 0, 0, 0, 1, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectMatrix, 0, mCameraMatrix, 0);
    }

    public GLSurfaceView getGLView() {
        return mGLView;
    }

    public Context getContext() {
        return mGLView.getContext();
    }
}
