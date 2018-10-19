package com.andy.opengl.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 滤镜组
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/21 16:00
 */
public class FilterGroup implements IFilterGroup, IFilter{
    private int mWidth;
    private int mHeight;

    private Queue<IFilter> mFilterQueue = new ConcurrentLinkedQueue<IFilter>();

    private List<IFilter> mFilters = new CopyOnWriteArrayList<IFilter>();

    @Override
    public void create() {
        for (IFilter filter : mFilters) {
            filter.create();
        }
    }

    @Override
    public void changeSize(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
        updateFilter();
        for (IFilter filter : mFilters) {
            filter.changeSize(width, height);
        }
    }

    @Override
    public void draw() {
        updateFilter();
        for (IFilter filter : mFilters) {
            filter.draw();
        }
    }

    @Override
    public void addFilter(IFilter filter) {
        this.mFilterQueue.add(filter);
    }

    @Override
    public void removeFilter(IFilter filter) {
        this.mFilterQueue.remove(filter);
        this.mFilters.remove(filter);
    }

    @Override
    public boolean containsFilter(IFilter filter) {
        return mFilters.contains(filter) || mFilterQueue.contains(filter);
    }

    @Override
    public List<IFilter> getFilterList() {
        updateFilter();
        return new ArrayList<IFilter>(mFilters);
    }

    @Override
    public void clear() {
        this.mFilterQueue.clear();
        this.mFilters.clear();
    }

    private void updateFilter() {
        IFilter f;
        while ((f=mFilterQueue.poll())!=null){
            f.create();
            f.changeSize(mWidth,mHeight);
            mFilters.add(f);
        }
    }
}
