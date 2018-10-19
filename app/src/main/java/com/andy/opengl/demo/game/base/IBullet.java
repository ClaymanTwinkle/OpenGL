package com.andy.opengl.demo.game.base;

/**
 * IBullet
 *
 * @author andyqtchen <br/>
 * 子弹接口，是子弹都需要实现这个接口
 * 创建日期：2018/6/28 19:32
 */
public interface IBullet {
    /**
     * 攻击力
     * @return 获取攻击力
     */
    int getAggressivity();
}