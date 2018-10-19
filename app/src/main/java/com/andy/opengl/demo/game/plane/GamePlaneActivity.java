package com.andy.opengl.demo.game.plane;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.andy.opengl.demo.GLDemoActivity;

/**
 * GamePlaneActivity
 *
 * @author andyqtchen <br/>
 * 游戏
 * 创建日期：2018/6/25 18:59
 */
public class GamePlaneActivity extends GLDemoActivity {

    @Override
    public GLSurfaceView.Renderer createRenderer() {
        return new GamePlaneRenderer(mGLView);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        mGLView.setOnTouchListener((View.OnTouchListener) mRenderer);
    }
}
