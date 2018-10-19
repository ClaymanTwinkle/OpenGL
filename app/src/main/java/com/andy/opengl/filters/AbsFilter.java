package com.andy.opengl.filters;

import android.opengl.GLES20;

import com.andy.opengl.util.OpenGLUtil;

/**
 * 抽象滤镜
 * @author andyqtchen <br/>
 * 创建日期：2018/5/21 10:01
 */
public abstract class AbsFilter implements IFilter{
    protected int mProgram;

    @Override
    public final void create(){
        onCreateProgram();
        onCreate(mProgram);
    }

    @Override
    public final void changeSize(int width, int height){
        onSizeChange(width, height);
    }

    @Override
    public final void draw() {
        onClear();
        onUseProgram();
        onSetExpandData();
        onBindTexture();
        onDraw();
    }

    protected void onCreateProgram() {
        int vertexShader = OpenGLUtil.loadShader(GLES20.GL_VERTEX_SHADER,
                getVertexShaderCode());
        int fragmentShader = OpenGLUtil.loadShader(GLES20.GL_FRAGMENT_SHADER,
                getFragmentShaderCode());

        mProgram = OpenGLUtil.createProgram(vertexShader, fragmentShader);

        GLES20.glDeleteShader(fragmentShader);
        GLES20.glDeleteShader(vertexShader);
    }

    protected void onUseProgram() {
        GLES20.glUseProgram(mProgram);
    }

    protected abstract void onCreate(int program);

    protected abstract String getVertexShaderCode();

    protected abstract String getFragmentShaderCode();

    protected abstract void onSizeChange(int width, int height);

    protected abstract void onClear();

    protected abstract void onSetExpandData();

    protected abstract void onBindTexture();

    protected abstract void onDraw();
}
