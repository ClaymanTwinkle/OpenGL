package com.andy.opengl.demo.game.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.opengl.GLES20;
import android.view.Display;
import android.view.MotionEvent;

import com.andy.opengl.filters.WaterMarkFilter;
import com.andy.opengl.util.DisplayUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Spirit
 *
 * @author andyqtchen <br/>
 * 游戏精灵
 * 创建日期：2018/6/25 19:10
 */
public abstract class Spirit extends WaterMarkFilter {
    public final static int CAMP_NEUTRAL = 0;
    public final static int CAMP_FRIENDLY = 1;
    public final static int CAMP_ENEMY = 2;

    private int mCamp = CAMP_NEUTRAL;

    public static int sScreenWidth = 0;
    public static int sScreenHeight = 0;

    protected SpiriteContainer mContainer;
    private List<Ability> mAbilityList = new CopyOnWriteArrayList<Ability>();

    private boolean mIsPressed = false;
    private boolean mIsTouchEnable = true;

    public Spirit(Context context, SpiriteContainer container, Bitmap bitmap) {
        super(context.getResources());
        this.mContainer = container;
        setBitmap(bitmap);
        if (sScreenWidth == 0 || sScreenHeight == 0) {
            Display display = DisplayUtil.getDisplay(context);
            if (display != null) {
                Point outSize = new Point();
                display.getSize(outSize);
                sScreenWidth = outSize.x;
                sScreenHeight = outSize.y;
            }
        }
    }

    public abstract boolean onTouch(MotionEvent event);

    public boolean isInTouch(MotionEvent event) {
        float width = getWidth();
        float height = getHeight();
        float currentX = getX();
        float currentY = getY();

        float touchX = event.getX();
        float touchY = event.getY();

        if (mIsPressed) {
            if (isTouchUp(event)) {
                mIsPressed = false;
            }
            return true;
        } else {
            boolean isTouchMe = touchX >= currentX && touchX <= currentX + width && touchY >= currentY && touchY <= currentY + height;
            if (isTouchMe && isTouchDown(event)) {
                mIsPressed = true;
                return true;
            }
        }
        return false;
    }

    protected boolean isTouchDown(MotionEvent event) {
        return event.getAction() == MotionEvent.ACTION_DOWN;
    }

    protected boolean isTouchUp(MotionEvent event) {
        return event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL;
    }

    /**
     * 判断是否在屏幕外
     *
     * @return
     */
    public boolean isOutScreen() {
        float width = getWidth();
        float height = getHeight();
        float currentX = getX();
        float currentY = getY();
        return currentX + width <= 0 || currentX >= sScreenWidth || currentY + height <= 0 || currentY >= sScreenHeight;
    }

    @Override
    protected void onLayout() {
        GLES20.glViewport(getX(), sScreenHeight - getY() - getHeight(), getWidth(), getHeight());
    }

    public void translateX(int translate) {
        setX(getX() + translate);
    }

    public void translateY(int translate) {
        setY(getY() + translate);
    }

    public void setPressed(boolean isPressed) {
        this.mIsPressed = isPressed;
    }

    public boolean isPressed() {
        return mIsPressed;
    }

    public boolean isTouchEnable() {
        return mIsTouchEnable;
    }

    public void setTouchEnable(boolean enable) {
        this.mIsTouchEnable = enable;
    }

    public void addAbility(Ability ability) {
        this.mAbilityList.add(ability);
    }

    public void removeAbility(Ability ability) {
        this.mAbilityList.remove(ability);
    }

    public void clearAbility() {
        this.mAbilityList.clear();
    }

    public void onAction(long timestamps) {
        for (Ability ability : mAbilityList) {
            ability.onAction(timestamps);
        }
    }

    public SpiriteContainer getContainer() {
        return mContainer;
    }

    public void addToContainer() {
        mContainer.addSpirit(this);
    }

    public void removeFromContainer() {
        mContainer.removeSpirit(this);
    }

    public void setCamp(int camp) {
        this.mCamp = camp;
    }

    /**
     * 获取精灵阵营
     * @return {@link #CAMP_NEUTRAL} {@link #CAMP_FRIENDLY} {@link #CAMP_ENEMY}
     */
    public int getCamp() {
        return mCamp;
    }

    public void relese() {
        mCamp = CAMP_NEUTRAL;
        clearAbility();
    }
}
