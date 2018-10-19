package com.andy.opengl.filters;

import android.content.res.Resources;

/**
 * 水印滤镜
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/22 11:01
 */
public class WaterMarkFilter extends ImageFilter {
    public WaterMarkFilter(Resources res) {
        super(res);
    }

    @Override
    protected boolean isBlend() {
        return true;
    }
}
