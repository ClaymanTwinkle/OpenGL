package com.andy.opengl.demo.triangle;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import com.andy.opengl.renderer.FilterRenderer;

import javax.microedition.khronos.opengles.GL10;

/**
 * 三角形
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/23 15:31
 */
public class TriangleRenderer extends FilterRenderer {
    private float[] mMVPMatrix = new float[16]; // Model View Projection Matrix
    private float[] mViewMatrix = new float[16];
    private float[] mProjectionMatrix = new float[16];

    public TriangleRenderer(Context context) {
        initFilters();
    }

    protected void initFilters() {
        addFilter(new Triangle());
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7); // 投影矩阵->mProjectionMatrix

        Matrix.setLookAtM(mViewMatrix, 0,
                0, 0, 0,
                0, 0, 0,
                0, 0, 0); // 设置相机->mViewMatrix

        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0); // 转换矩阵->mMVPMatrix
    }
}
