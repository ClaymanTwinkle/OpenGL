package com.andy.opengl.demo.triangle;

import android.opengl.GLES20;

import com.andy.opengl.filters.AbsFilter;
import com.andy.opengl.util.MatrixUtil;
import com.andy.opengl.util.OpenGLUtil;

import java.nio.FloatBuffer;

/**
 * Triangle
 *
 * @author andyqtchen <br/>
 * 三角形
 * 创建日期：2018/5/8 14:51
 */
public class Triangle extends AbsFilter {
    private final static String vertexShaderCode =
            "attribute vec4 aPosition;  \n" +
                    "uniform mat4 uMatrix; \n" +
                    "void main(){               \n" +
                    " gl_Position = uMatrix * aPosition; \n" +
                    "}  \n";

    private final static String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 uColor;" +
                    "void main() {" +
                    "  gl_FragColor = uColor;" +
                    "}";

    private static final int COORDS_PER_VERTEX = 2; // xyz 3个值
    private static final float triangleCoords[] = {
            0f, 0.5f,      // top
            -0.5f, 0f,  // left bottom
            0.5f, 0f    // right bottom
    };
    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX; // 顶点个数

    private final FloatBuffer vertexBuffer;

    private int mPositionHandle; // vertexShaderCode里的字段vPosition的句柄，有点像是内存地址
    private int mColorHandle;    // 同mPositionHandle
    private int mMatrixHandle;   // 矩阵的handle

    protected float[] mMatrix = MatrixUtil.getOriginalMatrix();
    private float color[] = {0.2f, 0.2f, 0.2f, 0.0f};

    public Triangle() {
        vertexBuffer = OpenGLUtil.fBuffer(triangleCoords);

        int vertexShader = OpenGLUtil.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = OpenGLUtil.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        mProgram = OpenGLUtil.createProgram(vertexShader, fragmentShader);

        GLES20.glDeleteShader(fragmentShader);
        GLES20.glDeleteShader(vertexShader);
    }

    @Override
    protected void onCreate(int program) {
        mPositionHandle = GLES20.glGetAttribLocation(program, "aPosition");
        mColorHandle = GLES20.glGetUniformLocation(program, "uColor");
        mMatrixHandle = GLES20.glGetUniformLocation(program, "uMatrix");
    }

    @Override
    protected String getVertexShaderCode() {
        return vertexShaderCode;
    }

    @Override
    protected String getFragmentShaderCode() {
        return fragmentShaderCode;
    }

    @Override
    protected void onSizeChange(int width, int height) {

    }

    @Override
    protected void onClear() {
    }

    @Override
    protected void onSetExpandData() {
        GLES20.glUniformMatrix4fv(mMatrixHandle, 1, false, mMatrix, 0);
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
    }

    @Override
    protected void onBindTexture() {

    }

    @Override
    protected void onDraw() {
        GLES20.glEnableVertexAttribArray(mPositionHandle); // 必须启动才能写，绘制后才关闭
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, 0, vertexBuffer);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount); // 绘制

        GLES20.glDisableVertexAttribArray(mPositionHandle); // 绘制后，记得最后还要关闭哦
    }
}
