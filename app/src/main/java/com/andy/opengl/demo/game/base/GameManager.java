package com.andy.opengl.demo.game.base;

import java.util.TimerTask;

/**
 * GameManager
 *
 * @author andyqtchen <br/>
 * todo 实现的主要功能。
 * 创建日期：2018/7/3 19:26
 */
public abstract class GameManager extends TimerTask implements SpiriteContainer {
    public abstract void start();
    public abstract void stop();
}
