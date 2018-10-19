package com.andy.opengl.demo.shot;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.andy.opengl.R;
import com.andy.opengl.camera.CameraController;
import com.andy.opengl.demo.GLDemoActivity;
import com.andy.opengl.demo.triangle.Triangle;
import com.andy.opengl.filters.FilterGroup;
import com.andy.opengl.filters.FontMarkFilter;
import com.andy.opengl.filters.WaterMarkFilter;

/**
 * ShotActivity
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/11 10:26
 */
public class ShotActivity extends GLDemoActivity {
    private final String TAG = "ShotActivity";

    private CameraController mCameraController;

    @Override
    public GLSurfaceView.Renderer createRenderer() {
        mCameraController = new CameraController(mGLView);
        return new ShotRenderer(this, mCameraController);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCameraController != null) {
            mCameraController.release();        // release the mCamera for other applications
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_shot, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ShotRenderer shotRenderer = getRenderer();
        if (shotRenderer != null) {
            FilterGroup extraFilterGroup = shotRenderer.getExtraFilterGroup();
            switch (item.getItemId()) {
                case R.id.normal:
                    extraFilterGroup.clear();
                    Log.i(TAG, "素颜");
                    break;
                case R.id.add_triangle:
                    extraFilterGroup.clear();
                    extraFilterGroup.addFilter(new Triangle());
                    Log.i(TAG, "添加一个三角形");
                    break;
                case R.id.add_water_mark:
                    extraFilterGroup.clear();
                    WaterMarkFilter waterMarkFilter = new WaterMarkFilter(getResources());
                    Bitmap wmBmp = BitmapFactory.decodeResource(getResources(), R.drawable.test);
                    waterMarkFilter.setBitmap(wmBmp);
                    extraFilterGroup.addFilter(waterMarkFilter);
                    Log.i(TAG, "添加一个图片水印");
                    break;
                case R.id.add_font_mark:
                    extraFilterGroup.clear();
                    FontMarkFilter fontMakFilter = new FontMarkFilter(getResources());
                    fontMakFilter.setWord(this, "QQ日迹", 50, Color.WHITE);
                    extraFilterGroup.addFilter(fontMakFilter);
                    Log.i(TAG, "添加一个文字水印");
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
