package com.andy.opengl.filters;

import java.util.List;

/**
 * 滤镜组接口
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/22 11:08
 */
public interface IFilterGroup {
    void addFilter(IFilter filter);

    void removeFilter(IFilter filter);

    boolean containsFilter(IFilter filter);

    List<IFilter> getFilterList();

    void clear();
}
