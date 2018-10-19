package com.andy.opengl.filters;

import android.content.res.Resources;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;

import com.andy.opengl.util.OpenGLUtil;

/**
 * Oes 滤镜
 * @author andyqtchen <br/>
 * 创建日期：2018/5/21 14:38
 */
public class OesFilter extends NoFilter {
    public OesFilter(Resources res) {
        super(res);
    }

    @Override
    protected String getVertexShaderCode() {
        return OpenGLUtil.readAssetFile(mRes, "shader/oes_base_vertex.shader");
    }

    @Override
    protected String getFragmentShaderCode() {
        return OpenGLUtil.readAssetFile(mRes, "shader/oes_base_fragment.shader");
    }

    @Override
    protected void onBindTexture() {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + mTextureType);
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mTextureId);
        GLES20.glUniform1i(mHTexture, mTextureType);
    }
}
