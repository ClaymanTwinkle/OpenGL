package com.andy.opengl.filters;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import com.andy.opengl.util.MatrixUtil;

/**
 * ImageFilter
 *
 * @author andyqtchen <br/>
 * 图片滤镜
 * 创建日期：2018/6/8 18:41
 */
public class ImageFilter extends NoFilter {
    private int mX;
    private int mY;
    private int mImgWidth = -1;
    private int mImgHeight = -1;

    private Bitmap mSrcBitmap;
    private boolean mIsRecyleBitmap = true;

    public ImageFilter(Resources res) {
        super(res);
    }

    @Override
    protected void onCreate(int program) {
        super.onCreate(program);
        initTexture();
    }

    @Override
    protected void onSetExpandData() {
        MatrixUtil.flip(mMatrix, false, true);
        super.onSetExpandData();
        MatrixUtil.flip(mMatrix, false, true);
    }

    @Override
    protected void onClear() {
    }

    protected void onLayout() {
        GLES20.glViewport(mX, mY, mImgWidth == -1 ? mWidth : mImgWidth, mImgHeight == -1 ? mHeight : mImgHeight);
    }

    @Override
    protected void onDraw() {
        onLayout();
        if (isBlend()) {
            GLES20.glDisable(GLES20.GL_DEPTH_TEST);
            GLES20.glEnable(GLES20.GL_BLEND);
            GLES20.glBlendFunc(getBlendSfactor(), getBlendDfactor()); // 不用这个背景会嘿嘿嘿
            super.onDraw();
            GLES20.glDisable(GLES20.GL_BLEND);
        } else {
            super.onDraw();
        }
        GLES20.glViewport(0, 0, mWidth, mHeight);
    }

    private void initTexture() {
        if (mSrcBitmap == null) return;

        int[] textures = new int[1];
        //生成纹理
        GLES20.glGenTextures(1, textures, 0);
        //生成纹理
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textures[0]);
        //设置缩小过滤为使用纹理中坐标最接近的一个像素的颜色作为需要绘制的像素颜色
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        //设置放大过滤为使用纹理中坐标最接近的若干个颜色，通过加权平均算法得到需要绘制的像素颜色
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        //设置环绕方向S，截取纹理坐标到[1/2n,1-1/2n]。将导致永远不会与border融合
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        //设置环绕方向T，截取纹理坐标到[1/2n,1-1/2n]。将导致永远不会与border融合
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);

        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, mSrcBitmap, 0);

        setTextureId(textures[0]);

        if (mIsRecyleBitmap) {
            if (!mSrcBitmap.isRecycled()) {
                mSrcBitmap.recycle();
            }
            mSrcBitmap = null;
        }
    }

    /**
     * 是否用完bitmap就回收bitmap
     * @param recyle boolean
     */
    public void setIsRecyleBitmap(boolean recyle) {
        this.mIsRecyleBitmap = recyle;
    }

    protected boolean isBlend() {
        return false;
    }

    protected int getBlendSfactor() {
        return GLES20.GL_SRC_ALPHA;
    }

    protected int getBlendDfactor() {
        return GLES20.GL_ONE_MINUS_SRC_ALPHA;
    }

    /**
     * 以左下角为原点，设置水印的位置、大小
     *
     * @param x      x，默认0
     * @param y      y，默认0
     * @param width  宽度，默认是用bitmap的width
     * @param height 高度，默认是用bitmap的height
     */
    public void setPosition(int x, int y, int width, int height) {
        this.mX = x;
        this.mY = y;
        this.mImgWidth = width;
        this.mImgHeight = height;
    }

    public void setBitmap(Bitmap bitmap) {
        if (this.mSrcBitmap != null) {
            this.mSrcBitmap.recycle();
        }
        this.mSrcBitmap = bitmap;
        if (mSrcBitmap != null) {
            if (mImgWidth == -1 && mImgHeight == -1) {
                mImgWidth = mSrcBitmap.getWidth();
                mImgHeight = mSrcBitmap.getHeight();
            }
        }
    }

    public void setX(int x) {
        this.mX = x;
    }

    public void setY(int y) {
        this.mY = y;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public void setImgWidth(int imgWidth) {
        this.mImgWidth = imgWidth;
    }

    public void setImgHeight(int imgHeight) {
        this.mImgHeight = imgHeight;
    }

    public int getWidth() {
        return mImgWidth;
    }

    public int getHeight() {
        return mImgHeight;
    }
}
