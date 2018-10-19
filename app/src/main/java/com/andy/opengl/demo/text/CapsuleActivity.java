package com.andy.opengl.demo.text;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.andy.opengl.demo.GLDemoActivity;
import com.andy.opengl.renderer.FilterRenderer;

/**
 * CapsuleActivity
 *
 * @author andyqtchen <br/>
 * 胶囊动画
 * 创建日期：2018/7/23 15:44
 */
public class CapsuleActivity extends GLDemoActivity {
    @Override
    public GLSurfaceView.Renderer createRenderer() {
        return new FilterRenderer();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FilterRenderer renderer = getRenderer();

    }
}
