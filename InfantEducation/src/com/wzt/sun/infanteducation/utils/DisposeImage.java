package com.wzt.sun.infanteducation.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;

public class DisposeImage {

	/** 
	 * 根据图片字节数组，对图片可能进行二次采样，不致于加载过大图片出现内存溢出 
	 * @param bytes 
	 * @return 
	 */  
	public static Bitmap getBitmapByBytes(byte[] bytes){  
	      
	    //对于图片的二次采样,主要得到图片的宽与高  
	    int width = 0;  
	    int height = 0;  
	    int sampleSize = 1; //默认缩放为1  
	    BitmapFactory.Options options = new BitmapFactory.Options();  
	    options.inJustDecodeBounds = true;  //仅仅解码边缘区域  
	    //如果指定了inJustDecodeBounds，decodeByteArray将返回为空  
	    BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);  
	    //得到宽与高  
	    height = options.outHeight;  
	    width = options.outWidth;  
	  
	    //图片实际的宽与高，根据默认最大大小值，得到图片实际的缩放比例  
	    while ((height / sampleSize > 480)  
	            || (width / sampleSize > 480)) {  
	        sampleSize *= 2;  
	    }  
	  
	    //不再只加载图片实际边缘  
	    options.inJustDecodeBounds = false;  
	    //并且制定缩放比例  
	    options.inSampleSize = sampleSize;  
	    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);  
	}  
	
	/**
	 * 将图片从本地读到内存时,进行压缩 ,即图片从File形式变为Bitmap形式
	 * @param image
	 * @return
	 */
	public static Bitmap compressBmpFromBmp(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int options = 100;
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		while (baos.toByteArray().length / 1024 > 100) { 
			baos.reset();
			options -= 10;
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
		return bitmap;
	}
	
	/**
	 * 对Bitmap形式的图片进行压缩, 减少Bitmap的像素, 从而减少了它所占用的内存
	 * @param srcPath
	 * @return
	 */
	public static Bitmap compressImageFromFile(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;//只读边,不读内容
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		float hh = 800f;//
		float ww = 480f;//
		int be = 1;
		if (w > h && w > ww) {
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//设置采样率
		
		newOpts.inPreferredConfig = Config.ARGB_8888;//该模式是默认的,可不设
		newOpts.inPurgeable = true;// 同时设置才会有效
		newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收
		
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
//		return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
									//其实是无效的,大家尽管尝试
		return bitmap;
	}
}
