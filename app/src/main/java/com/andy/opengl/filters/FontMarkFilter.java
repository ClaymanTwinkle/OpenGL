package com.andy.opengl.filters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

import com.andy.opengl.util.FontUtil;

/**
 * 文字水印
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/23 10:24
 */
public class FontMarkFilter extends WaterMarkFilter {
    public FontMarkFilter(Resources res) {
        super(res);
    }

    public void setWord(Context context, String word, float textSize, int color) {
        Bitmap fontBitmap = FontUtil.createFontBitmap(context, word, textSize, color);
        setBitmap(fontBitmap);
    }
}