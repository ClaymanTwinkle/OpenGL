package com.andy.opengl.demo.rotate;

import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.andy.opengl.demo.GLDemoActivity;
import com.andy.opengl.demo.triangle.Triangle;
import com.andy.opengl.demo.triangle.TriangleRenderer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 旋转三角形
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/23 15:32
 */
public class RotateTriangleActivity extends GLDemoActivity {
    private final static String TAG = "RotateTriangleActivity";

    private Timer mTimer;
    private TimerTask mTimeTask;

    @Override
    public GLSurfaceView.Renderer createRenderer() {
        return new TriangleRenderer(this) {
            private int count = 0;

            @Override
            protected void initFilters() {
                addFilter(new Triangle() {
                    @Override
                    protected void onDraw() {
                        Log.i(TAG, "heard jump..." + count);
                        count++;
                        float rad = (float) ((count = (count % 360)) * Math.PI / 180);
                        Matrix.rotateM(mMatrix, 0, rad, 0, 1, 0);
                        super.onDraw();
                    }
                });
            }
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTimeTask = new TimerTask() {
            @Override
            public void run() {
                mGLView.requestRender();
            }
        };

        mTimer = new Timer();
        mTimer.schedule(mTimeTask, 0, 16);
    }

    @Override
    protected void onDestroy() {
        mTimer.cancel();
        super.onDestroy();
    }
}
