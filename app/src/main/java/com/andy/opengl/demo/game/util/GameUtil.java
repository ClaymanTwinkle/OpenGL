package com.andy.opengl.demo.game.util;

import com.andy.opengl.demo.game.base.Spirit;

/**
 * GameUtil
 *
 * @author andyqtchen <br/>
 * 游戏工具
 * 创建日期：2018/7/2 17:37
 */
public final class GameUtil {
    public static boolean isCollide(Spirit s1, Spirit s2) {
        int x1 = s1.getX();
        int y1 = s1.getY();
        int w1 = s1.getWidth();
        int h1 = s1.getHeight();

        int x2 = s2.getX();
        int y2 = s2.getY();
        int w2 = s2.getWidth();
        int h2 = s2.getHeight();

        return  Math.abs(x1 - x2) < w1/2 + w2/2 //横向判断
                &&
                Math.abs(y1 - y2) < h1/2 + h2/2; //纵向判断;
    }
}
