package com.andy.opengl.demo;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.andy.opengl.BaseActivity;
import com.andy.opengl.R;

/**
 * 自带了layout，demo专用的Activity
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/23 15:07
 */
public abstract class GLDemoActivity extends BaseActivity {
    protected GLSurfaceView mGLView;
    protected GLSurfaceView.Renderer mRenderer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl_demo);
        initView();
    }

    protected void initView() {
        mGLView = (GLSurfaceView)findViewById(R.id.glsv);
        mGLView.setEGLContextClientVersion(2);
        mRenderer = createRenderer();
        mGLView.setRenderer(mRenderer);
        mGLView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected boolean isShowBackIcon() {
        return true;
    }

    public abstract GLSurfaceView.Renderer createRenderer();

    public <T extends GLSurfaceView.Renderer> T getRenderer() {
        return (T) mRenderer;
    }
}
