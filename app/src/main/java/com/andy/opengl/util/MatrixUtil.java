package com.andy.opengl.util;

import android.opengl.Matrix;

/**
 * 操作矩阵的工具类
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/22 14:59
 */
public final class MatrixUtil {
    /**
     * 矩阵翻转
     * @param m 原矩阵
     * @param x x轴方向翻转
     * @param y y轴方向翻转
     * @return 翻转后的矩阵
     */
    public static float[] flip(float[] m,boolean x,boolean y){
        if(x||y){
            Matrix.scaleM(m,0,x?-1:1,y?-1:1,1);
        }
        return m;
    }

    /**
     * 得到一个单位矩阵
     * @return 单位矩阵
     */
    public static float[] getOriginalMatrix() {
        return new float[]{
                1,0,0,0,
                0,1,0,0,
                0,0,1,0,
                0,0,0,1
        };
    }
}
