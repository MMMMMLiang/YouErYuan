package com.wzt.sun.infanteducation.utils;

import java.io.File;

import android.os.Environment;

import com.wzt.sun.infanteducation.constans.ConstantsConfig;

public class FileUtils {
	/**
     * 创建应用程序的根目录
     * data/
     */
    public static String getAppRootCache() {
        String root = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            root = Environment.getExternalStorageDirectory() + File.separator + ConstantsConfig.APP_CACHE;
        } else {
            root = Environment.getDataDirectory() + File.separator + ConstantsConfig.SYS_CACHE;
        }
        if (!new File(root).exists()) {
            new File(root).mkdirs();
        }
        return root;
    }

    /**
     * sd卡/wzt/image
     * @return
     */
    public static String getImageCache() {
        String imageCache = getAppRootCache() + File.separator + ConstantsConfig.IMAGE_CACHE;
        if (!new File(imageCache).exists()) {
            new File(imageCache).mkdirs();
        }
        return imageCache;
    }
}
