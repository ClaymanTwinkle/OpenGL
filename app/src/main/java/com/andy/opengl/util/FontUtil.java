package com.andy.opengl.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * FontUtil
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/16 10:11
 */
public final class FontUtil {
    /**
     * 创建文字bitmap
     *
     * @param word     字符串
     * @param textSize 统一sp
     * @param color 颜色
     * @return bitmap
     */
    public static Bitmap createFontBitmap(Context context, String word, float textSize, int color) {

        Paint p = new Paint();
        //字体设置
        String fontType = "宋体";
        Typeface typeface = Typeface.create(fontType, Typeface.BOLD);
        //消除锯齿
        p.setAntiAlias(true);
        //字体为红色
        p.setColor(color);
        p.setTypeface(typeface);
        p.setTextSize(textSize);

        int width = (int)Math.ceil(p.measureText(word));
        int height = DisplayUtil.sp2px(context, textSize);

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //背景颜色
        canvas.drawColor(Color.TRANSPARENT);

        Paint.FontMetricsInt fontMetrics = p.getFontMetricsInt();
        float y = fontMetrics.descent - fontMetrics.ascent + fontMetrics.leading; // 这里在算基线的y坐标值，很麻烦
        //绘制字体
        canvas.drawText(word, 0, y, p);

        return bitmap;
    }
}
