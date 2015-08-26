package com.wzt.sun.infanteducation.utils;

import com.squareup.picasso.Transformation;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

/**
 * 设置圆角属性
 * @author sun.ml
 *
 */
public class FilletTransform implements Transformation {
	
	@Override
	public String key() {
		// TODO Auto-generated method stub
		return "fillet";
	}

	@Override
	public Bitmap transform(Bitmap source) {
		final Paint paint = new Paint();  
        paint.setAntiAlias(true);  
        Bitmap target = Bitmap.createBitmap(75, 75, Config.ARGB_8888);  
        Canvas canvas = new Canvas(target);  
        RectF rect = new RectF(0, 0, source.getWidth(), source.getHeight());  
        canvas.drawRoundRect(rect, 10, 10, paint);  
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));  
        canvas.drawBitmap(source, 0, 0, paint);  
        return target;
	}

}
