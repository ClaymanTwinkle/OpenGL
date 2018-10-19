package com.andy.opengl.demo.text;

import android.content.res.Resources;

import com.andy.opengl.filters.WaterMarkFilter;

/**
 * CapsuleFilter
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/7/23 15:49
 */
public class CapsuleFilter extends WaterMarkFilter {
    private Capsule capsule;

    public CapsuleFilter(Resources res, Capsule capsule) {
        super(res);
        this.capsule = capsule;
    }

    @Override
    protected void onLayout() {
        setX(capsule.cX);
        setY(capsule.cY);
        super.onLayout();
    }
}
