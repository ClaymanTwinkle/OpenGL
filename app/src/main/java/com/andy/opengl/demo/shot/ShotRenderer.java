package com.andy.opengl.demo.shot;

import android.content.Context;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import com.andy.opengl.camera.CameraController;
import com.andy.opengl.filters.CameraFilter;
import com.andy.opengl.filters.FilterGroup;
import com.andy.opengl.renderer.FilterRenderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * ShotRenderer
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/11 10:58
 */
public class ShotRenderer extends FilterRenderer {
    private final static String TAG = "ShotRenderer";

    private CameraFilter mCameraFilter;

    private float[] mProjectMatrix = new float[16];
    private float[] mCameraMatrix = new float[16];
    private float[] mMVPMatrix = new float[16];

    private CameraController mCameraController;

    private int mTextureId;

    private FilterGroup mExtraFilterGroup = new FilterGroup(); // 给外部用，添加各种滤镜

    public ShotRenderer(Context context, CameraController mCameraController) {
        this.mCameraController = mCameraController;

        Matrix.setIdentityM(mProjectMatrix, 0); // 初始化为单位矩阵
        Matrix.setIdentityM(mCameraMatrix, 0);
        Matrix.setIdentityM(mMVPMatrix, 0);

        mCameraFilter = new CameraFilter(context.getResources());
        addFilter(mCameraFilter); // 加入滤镜组
        // addFilter(new Triangle());
        addFilter(mExtraFilterGroup);
        // addFilter(new WordFilter(context.getResources()));
        mCameraFilter.setCameraType(0);
        mCameraController.setCameraType(0);
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);
        mTextureId = createOESTexture();
        mCameraFilter.setTextureId(mTextureId);
        mCameraController.startPreview(mTextureId);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);
        Log.i(TAG, "onSurfaceChanged");

        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width/height;
        Matrix.orthoM(mProjectMatrix, 0, -1, 1, -ratio, ratio, 3, 7);
        Matrix.setLookAtM(mCameraMatrix, 0, 0, 0, 3, 0, 0, 0, 0, 1, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectMatrix, 0, mCameraMatrix, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);
        Log.i(TAG, "onDrawFrame");
        mCameraController.draw();
    }

    /**
     * 创建OES texture
     * @return texture id
     */
    private int createOESTexture() {
        int[] texture = new int[1];
        GLES20.glGenTextures(1, texture, 0);
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, texture[0]);
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
        return texture[0];
    }

    public FilterGroup getExtraFilterGroup() {
        return mExtraFilterGroup;
    }
}
