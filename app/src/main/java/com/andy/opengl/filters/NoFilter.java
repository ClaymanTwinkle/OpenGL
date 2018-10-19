package com.andy.opengl.filters;

import android.content.res.Resources;
import android.opengl.GLES20;

import com.andy.opengl.util.MatrixUtil;
import com.andy.opengl.util.OpenGLUtil;

import java.nio.FloatBuffer;

/**
 * 无滤镜
 * 正常显示
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/21 10:21
 */
public class NoFilter extends AbsFilter{
    protected final Resources mRes;

    // 顶点坐标Buffer
    protected FloatBuffer mVerBuffer;

    // 纹理坐标Buffer
    protected FloatBuffer mTexBuffer;

    //顶点坐标
    private final float[] mVerCoordSrc = {
            -1, -1,
            -1, 1,
            1, -1,
            1, 1
    };

    //纹理坐标
    private final float[] mTexCoordSrc ={
            0.0f, 0.0f,
            0.0f,  1.0f,
            1.0f,  0.0f,
            1.0f, 1.0f,
    };

    protected float[] mMatrix = MatrixUtil.getOriginalMatrix();

    protected int mTextureId = 0; // 纹理id
    protected int mTextureType = 0; // 纹理单元 from GLES20.GL_TEXTURE0 ~ GLES20.GL_TEXTURE31

    protected int mHVerPosition;
    protected int mHTexCoord;
    protected int mHMatrix;
    protected int mHTexture;

    protected int mWidth;
    protected int mHeight;


    public NoFilter(Resources res) {
        mRes = res;
        initBuffer();
    }

    @Override
    protected void onCreate(int program) {
        mHVerPosition= GLES20.glGetAttribLocation(program, "aPosition");
        mHTexCoord= GLES20.glGetAttribLocation(program,"aTextureCoord");
        mHMatrix= GLES20.glGetUniformLocation(program,"uMatrix");
        mHTexture= GLES20.glGetUniformLocation(program,"uTexture");
    }

    @Override
    protected String getVertexShaderCode() {
        return OpenGLUtil.readAssetFile(mRes, "shader/base_vertex.shader");
    }

    @Override
    protected String getFragmentShaderCode() {
        return OpenGLUtil.readAssetFile(mRes, "shader/base_fragment.shader");
    }

    @Override
    protected void onSizeChange(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
    }

    @Override
    protected void onClear() {
//        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // 背景涂黑
//        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    }

    @Override
    protected void onSetExpandData() {
        GLES20.glUniformMatrix4fv(mHMatrix, 1, false, mMatrix, 0);
    }

    @Override
    protected void onBindTexture() {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + mTextureType);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureId);
        GLES20.glUniform1i(mHTexture, mTextureType);
    }

    @Override
    protected void onDraw() {
        GLES20.glEnableVertexAttribArray(mHVerPosition);
        GLES20.glEnableVertexAttribArray(mHTexCoord);

        GLES20.glVertexAttribPointer(mHVerPosition, 2, GLES20.GL_FLOAT, false, 0, mVerBuffer);
        GLES20.glVertexAttribPointer(mHTexCoord, 2, GLES20.GL_FLOAT, false, 0, mTexBuffer);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, mVerCoordSrc.length / 2);

        GLES20.glDisableVertexAttribArray(mHTexCoord);
        GLES20.glDisableVertexAttribArray(mHVerPosition);
    }

    protected void initBuffer() {
        mVerBuffer = OpenGLUtil.fBuffer(mVerCoordSrc);
        mTexBuffer = OpenGLUtil.fBuffer(mTexCoordSrc);
    }

    public void setTextureId(int textureId) {
        this.mTextureId = textureId;
    }

    public void setTextureType(int textureType) {
        this.mTextureType = textureType;
    }

    public void setVerBufferData(float[] verCoordSrc) {
        mVerBuffer.clear();
        mVerBuffer.put(verCoordSrc);
        mVerBuffer.position(0);
    }

    public void setTexBufferData(float[] texCoordSrc) {
        mTexBuffer.clear();
        mTexBuffer.put(texCoordSrc);
        mTexBuffer.position(0);
    }
}
