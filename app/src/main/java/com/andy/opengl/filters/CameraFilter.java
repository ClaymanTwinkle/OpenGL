package com.andy.opengl.filters;

import android.content.res.Resources;
import android.hardware.Camera;

/**
 * 相机滤镜
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/21 15:04
 */
public class CameraFilter extends OesFilter {
    private int mCameraType = Camera.CameraInfo.CAMERA_FACING_BACK;

    public CameraFilter(Resources res) {
        super(res);
        setCameraType(mCameraType);
    }

    /**
     * 设置摄像头前后置
     * @param type {@link Camera.CameraInfo#CAMERA_FACING_BACK} or {@link Camera.CameraInfo#CAMERA_FACING_FRONT}
     */
    public void setCameraType(int type) {
        this.mCameraType = type;
        float[] coord;
        if(mCameraType == Camera.CameraInfo.CAMERA_FACING_BACK) {
            //后置摄像头 上下颠倒后顺时针旋转90
            coord=new float[]{
                    1, 1,
                    0, 1,
                    1, 0,
                    0, 0,
            };
        } else {
            //前置摄像头 顺时针旋转90度
            coord = new float[]{
                    0.0f,  1.0f,
                    1.0f, 1.0f,
                    0.0f, 0.0f,
                    1.0f,  0.0f,
            };
        }
        setTexBufferData(coord);
    }
}
