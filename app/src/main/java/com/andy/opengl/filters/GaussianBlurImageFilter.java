package com.andy.opengl.filters;

import android.content.res.Resources;

import com.andy.opengl.util.OpenGLUtil;

/**
 * GaussianBlurImageFilter
 *
 * @author andyqtchen <br/>
 * 高斯模糊图片滤镜
 * 创建日期：2018/6/11 12:02
 */
public class GaussianBlurImageFilter extends ImageFilter {
    public GaussianBlurImageFilter(Resources res) {
        super(res);
    }

    @Override
    protected String getVertexShaderCode() {
        return OpenGLUtil.readAssetFile(mRes, "shader/gaussian_blur_vertex.shader");
    }

    @Override
    protected String getFragmentShaderCode() {
        return OpenGLUtil.readAssetFile(mRes, "shader/gaussian_blur_fragment.shader");
    }
}
