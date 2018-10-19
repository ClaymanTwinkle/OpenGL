package com.andy.opengl.demo.text;

import java.util.Date;

/**
 * Capsule
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/7/23 15:47
 */
public class Capsule {
    public String text;

    public int angle;
    public int sX;
    public int sY;

    public int moveX;
    public int moveY;

    public int startTime;
    public int costTime;

    public int cX;
    public int cY;

    private Shake shake = new Shake();

    public void onAction() {
        if (startTime >= costTime) {
            this.wiggle();
        } else {
            cY = this.easeOut(startTime, sY, moveY, costTime);
            startTime += 1;
        }
    }

    private int easeOut(int startTime, int startY, int moveY, int costTime) {
        return moveY * ((startTime = startTime / costTime - 1) * startTime * startTime + 1) + startY;
    }

    private void wiggle() {
        long oldTime = shake.time;
        long nowTime = new Date().getTime();
        if (nowTime - oldTime < shake.shakeFrequency) {
            return;
        }
        shake.time = nowTime;

        int freq = 400, amp = 1;

        shake.wiggleX = (int)(Math.cos(shake.countX / freq) * amp);
        shake.wiggleY = (int)(Math.cos(shake.countY / freq) * amp);
        cX += shake.wiggleX;
        cY += shake.wiggleY;

        shake.countX += shake.shakeStep * shake.invert;
        shake.countY += shake.shakeStep * shake.invert;
    }

    private class Shake {
        int shakeStep = 100; // 摆动每次的步长随机范围
        int shakeFrequency = 50; // 摆动时间间隔
        long countX = Math.round(Math.random() * 100);
        long countY = Math.round(Math.random() * 100);
        int wiggleX = 0;
        int wiggleY = 0;
        int invert = Math.random() > 0.5 ? 1 : -1;
        long time = 0;
    }
}