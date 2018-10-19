package com.andy.opengl.camera;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLSurfaceView;

import java.io.IOException;

/**
 * CameraController
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/16 14:46
 */
public class CameraController implements SurfaceTexture.OnFrameAvailableListener {
    private int mCameraType = Camera.CameraInfo.CAMERA_FACING_BACK;
    private Camera mCamera;
    private SurfaceTexture mSurfaceTexture;
    private GLSurfaceView mGLSurfaceView;

    public CameraController(GLSurfaceView glSurfaceView) {
        this.mGLSurfaceView = glSurfaceView;
    }

    /**
     * 设置摄像头前后置
     * @param cameraType {@link Camera.CameraInfo#CAMERA_FACING_BACK} or {@link Camera.CameraInfo#CAMERA_FACING_FRONT}
     */
    public void setCameraType(int cameraType) {
        this.mCameraType = cameraType;
    }

    public void startPreview(int texture) {
        this.mSurfaceTexture = new SurfaceTexture(texture);
        this.mSurfaceTexture.setOnFrameAvailableListener(this);
        try {
            this.mCamera = Camera.open(mCameraType);
            mCamera.setPreviewTexture(mSurfaceTexture);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        mSurfaceTexture.updateTexImage();
    }

    public void release() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();        // release the mCamera for other applications
            mCamera = null;
        }
    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        if (mGLSurfaceView != null) {
            mGLSurfaceView.requestRender();
        }
    }

    public Camera.Size getPreferredPreviewCameraSize() {
        Camera.Parameters parms = mCamera.getParameters();
        return parms.getPreferredPreviewSizeForVideo();
    }
}
