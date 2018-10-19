package com.andy.opengl.demo.game.base;

import android.support.annotation.NonNull;
import android.support.v4.util.Pools;

/**
 * SpiritFactory
 *
 * @author andyqtchen <br/>
 * 精灵池
 * 创建日期：2018/6/28 18:28
 */
public abstract class SpiritFactory<T extends Spirit> extends Pools.SimplePool<T> {
    private final Object mLock = new Object();

    public SpiritFactory(int maxPoolSize) {
        super(maxPoolSize);
    }

    @Override
    public T acquire() {
        synchronized (mLock) {
            T object = super.acquire();
            if(object == null) {
                object = createSpirit();
            }
            return object;
        }
    }

    @Override
    public boolean release(@NonNull T element) {
        synchronized (mLock) {
            element.relese();
            return super.release(element);
        }
    }

    protected abstract T createSpirit();

    protected abstract void recycle();
}
