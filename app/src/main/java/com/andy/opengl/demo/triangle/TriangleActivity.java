package com.andy.opengl.demo.triangle;

import android.opengl.GLSurfaceView;

import com.andy.opengl.demo.GLDemoActivity;

/**
 * TriangleActivity
 *
 * @author andyqtchen <br/>
 * 就画了个三角形
 * 创建日期：2018/5/10 16:04
 */
public class TriangleActivity extends GLDemoActivity {

    @Override
    public GLSurfaceView.Renderer createRenderer() {
        return new TriangleRenderer(this);
    }
}
