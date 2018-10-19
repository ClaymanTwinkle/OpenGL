package com.andy.opengl.demo.videoplayer;

import android.opengl.GLSurfaceView;

import com.andy.opengl.demo.GLDemoActivity;

/**
 * VideoPlayerActivity
 *
 * @author andyqtchen <br/>
 * 播放视频
 * 创建日期：2018/5/11 09:53
 */
public class VideoPlayerActivity extends GLDemoActivity {

    @Override
    public GLSurfaceView.Renderer createRenderer() {
        return new VideoRenderer();
    }
}
