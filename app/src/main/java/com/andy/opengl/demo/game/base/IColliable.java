package com.andy.opengl.demo.game.base;

/**
 * IColliable
 *
 * @author andyqtchen <br/>
 * 可以被其他spirit碰撞触发碰撞事件的spirit需要实现的接口
 * 创建日期：2018/6/28 19:59
 */
public interface IColliable {
    void onCollide(Spirit fromSpirit);
}
