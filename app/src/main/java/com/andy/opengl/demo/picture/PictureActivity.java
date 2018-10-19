package com.andy.opengl.demo.picture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.view.Display;

import com.andy.opengl.R;
import com.andy.opengl.demo.GLDemoActivity;
import com.andy.opengl.filters.GaussianBlurImageFilter;
import com.andy.opengl.renderer.FilterRenderer;
import com.andy.opengl.util.DisplayUtil;

import javax.microedition.khronos.opengles.GL10;

/**
 * PictureActivity
 *
 * @author andyqtchen <br/>
 * 图片demo
 * 创建日期：2018/6/8 18:10
 */
public class PictureActivity extends GLDemoActivity {

    @Override
    public GLSurfaceView.Renderer createRenderer() {
        return new PictureRenderer(this);
    }

    public final static class PictureRenderer extends FilterRenderer {
        private float[] mMVPMatrix = new float[16]; // Model View Projection Matrix
        private float[] mViewMatrix = new float[16];
        private float[] mProjectionMatrix = new float[16];

        public PictureRenderer(Context context) {
            Bitmap wmBmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.test);
            GaussianBlurImageFilter filter = new GaussianBlurImageFilter(context.getResources());
            filter.setBitmap(wmBmp);
            Display display = DisplayUtil.getDisplay(context);
            int width = wmBmp.getWidth();
            int height = wmBmp.getHeight();
            int x = 0;
            int y = 0;
            if (display != null) {
                x = display.getWidth()/2 - width/2;
                y = display.getHeight()/2 - height/2;
            }
            filter.setPosition(x, y, width, height);
            addFilter(filter);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            super.onSurfaceChanged(gl, width, height);
            GLES20.glViewport(0, 0, width, height);
            float ratio = (float) width / height;

            Matrix.orthoM(mProjectionMatrix, 0, -1, 1, -ratio, ratio, 3, 7);
            Matrix.setLookAtM(mViewMatrix, 0, 0, 0, 3, 0, 0, 0, 0, 1, 0);
            Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0); // 转换矩阵->mMVPMatrix
        }
    }
}
