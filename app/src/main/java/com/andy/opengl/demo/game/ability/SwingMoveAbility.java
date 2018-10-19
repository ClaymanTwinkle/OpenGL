package com.andy.opengl.demo.game.ability;

import com.andy.opengl.demo.game.base.Ability;
import com.andy.opengl.demo.game.base.Spirit;

/**
 * SwingMoveAbility
 *
 * @author andyqtchen <br/>
 * 左摇右摆的移动能力
 * 创建日期：2018/7/2 21:33
 */
public class SwingMoveAbility implements Ability {

    private final static int FORWARD_LEFT = 0;
    private final static int FORWARD_RIGHT = 1;
    private int mForward = FORWARD_LEFT;

    private Spirit mSpirit;
    private int mSwingSpeed = 20;

    public SwingMoveAbility(Spirit spirit, int speed) {
        this.mSpirit = spirit;
        this.mSwingSpeed = speed;
    }

    @Override
    public void onAction(long timestamps) {
        int moveX = mSpirit.getX();
        if (mForward == FORWARD_LEFT) {
            moveX = Math.max(mSpirit.getX() - mSwingSpeed, 0);
            if (moveX == 0) {
                mForward = FORWARD_RIGHT;
            }
        } else if (mForward == FORWARD_RIGHT) {
            int maxX = Spirit.sScreenWidth - mSpirit.getWidth();
            moveX = Math.min(mSpirit.getX() + mSwingSpeed, maxX);
            if (moveX == maxX) {
                mForward = FORWARD_LEFT;
            }
        }
        mSpirit.setX(moveX);
    }
}
