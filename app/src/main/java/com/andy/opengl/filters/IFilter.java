package com.andy.opengl.filters;

/**
 * 滤镜接口
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/21 16:01
 */
public interface IFilter {
    void create();
    void changeSize(int width, int height);
    void draw();
}
