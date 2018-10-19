package com.andy.opengl.util;

import android.content.res.Resources;
import android.opengl.GLES20;
import android.util.Log;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * OpenGLUtil
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/8 15:15
 */
public final class OpenGLUtil {

    /**
     *
     * @param type GL_VERTEX_SHADER or GL_FRAGMENT_SHADER
     * @param shaderCode shader code
     * @return shader
     */
    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    /**
     * 创建 OpenGL ES程序
     * @param vertexShader 定点着色器
     * @param fragmentShader 片段着色器
     * @return program
     */
    public static int createProgram(int vertexShader, int fragmentShader) {
        int program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);
        return program;
    }


    public static void checkGLError(String glOperation) {
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e("", glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

    /**
     * 从assert文件夹中读取文件
     * @param mRes Resources
     * @param path filePath
     * @return 文件内容
     */
    public static String readAssetFile(Resources mRes, String path){
        StringBuilder result=new StringBuilder();
        try{
            InputStream is=mRes.getAssets().open(path);
            int ch;
            byte[] buffer=new byte[1024];
            while (-1!=(ch=is.read(buffer))){
                result.append(new String(buffer,0,ch));
            }
        }catch (Exception e){
            return null;
        }
        return result.toString().replaceAll("\\r\\n","\n");
    }

    /**
     * float src array to FloatBuffer
     * 后面什么IntBuffer等，自己加吧（后面这句话翻译不了，装逼到此结束，逃~）
     * @param src float[]
     * @return FloatBuffer
     */
    public static FloatBuffer fBuffer(float[] src) {
        ByteBuffer bb = ByteBuffer.allocateDirect(src.length * 4); // float 4 bytes
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(src);
        vertexBuffer.position(0);
        return vertexBuffer;
    }
}
