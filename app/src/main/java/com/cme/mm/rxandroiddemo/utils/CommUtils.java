package com.cme.mm.rxandroiddemo.utils;

import android.content.Context;

import com.cme.mm.rxandroiddemo.R;
import com.cme.mm.rxandroiddemo.constants.CommConstants;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Descriptions：基本工具类
 * <p>
 * Author：ChenME
 * Date：10/20/2016
 * Email：ibelieve1210@163.com
 */
public class CommUtils {

    /**
     * 初始化默认的ImageLoader
     *
     * @param context
     * @return
     */
    public static DisplayImageOptions initDefaultImageLoader(Context context) {
        return initImageLoader(context, 8, R.mipmap.ic_launcher);
    }

    /**
     * 初始化ImageLoader
     *
     * @param context
     * @param cornerRadiusPixels 圆角的半径（单位|：px）
     * @param defaultOrErrImg    错误时或默认图片
     * @return
     */
    public static DisplayImageOptions initImageLoader(Context context, int cornerRadiusPixels, int defaultOrErrImg) {
        setImageLoaderCache(context);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultOrErrImg)// 设置图片在下载期间显示的图片
                .showImageForEmptyUri(defaultOrErrImg)// 设置图片URI为空或是错误的时候显示的图片
                .showImageOnFail(defaultOrErrImg)// 设置图片加载错误时显示的图片
                .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)// 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels))
                .build();
        return options;
    }


    /**
     * 设置图片缓存的目录
     */
    private static void setImageLoaderCache(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, CommConstants.APP_BASE_FOLDER_PATH + "/imageloader/Cache");
        // 配置ImageLoaderConfiguration
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .discCache(new UnlimitedDiskCache(cacheDir))
                .threadPoolSize(3)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .writeDebugLogs()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))// 内存缓存
                .build();
        ImageLoader.getInstance().init(configuration);// 全局初始化此配置
    }
}
