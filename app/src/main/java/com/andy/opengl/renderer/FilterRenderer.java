package com.andy.opengl.renderer;

import android.opengl.GLSurfaceView;

import com.andy.opengl.filters.FilterGroup;
import com.andy.opengl.filters.IFilter;
import com.andy.opengl.filters.IFilterGroup;

import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 可以组合多种滤镜一起使用的 Renderer
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/21 15:56
 */
public class FilterRenderer implements GLSurfaceView.Renderer, IFilterGroup{
    private FilterGroup mDefaultFilterGroup = new FilterGroup();

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mDefaultFilterGroup.create();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mDefaultFilterGroup.changeSize(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        mDefaultFilterGroup.draw();
    }

    @Override
    public void addFilter(IFilter filter) {
        mDefaultFilterGroup.addFilter(filter);
    }

    @Override
    public void removeFilter(IFilter filter) {
        mDefaultFilterGroup.removeFilter(filter);
    }

    @Override
    public boolean containsFilter(IFilter filter) {
        return mDefaultFilterGroup.containsFilter(filter);
    }

    @Override
    public List<IFilter> getFilterList() {
        return mDefaultFilterGroup.getFilterList();
    }

    @Override
    public void clear() {
        mDefaultFilterGroup.clear();
    }
}
