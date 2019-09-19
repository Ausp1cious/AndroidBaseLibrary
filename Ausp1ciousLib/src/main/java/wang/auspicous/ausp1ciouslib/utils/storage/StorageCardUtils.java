package wang.auspicous.ausp1ciouslib.utils.storage;

import android.os.Environment;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;


/**
 * "/Android/data/" + context.getPackageName() + "/cache/"
 */

public class StorageCardUtils {
    public static final String IMAGE_DIR = "images";//图片存储目录
    public static final String DOWNLOAD_DIR = "downloads";//apk版本更新存放目录
    public static final String HTTP_DIR = "https";//http请求
    public static final String VIDEO_DIR = "video";//视频存放目录
    public static final String H5_ASSET_PREFIX = "file:///android_asset/";
    public static final String H5_SDCARD_PREFIX = "content://com.android.htmlfileprovider/";

    public static File getAppCacheDir() {
        File dir = isExistSDCard() ? getExternalCacheDir() : getInnerCacheDir();
        if (null != dir && !dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public static File getAppFilesDir() {
        File dir = isExistSDCard() ? getExternalFilesDir() : getInnerFilesDir();
        if (null != dir && !dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }


    /**
     * 获取文件地址
     * @param fileName
     * @return
     */
    @Nullable
    public static File getFileDir(String fileName) {
        if (isExistSDCard()) {
            String filePath = getExternalFilesDir().getAbsolutePath() + "/" + fileName;
            File file = new File(filePath);
            if (file != null && !file.exists()) {
                file.mkdirs();
            }
            return file;
        }
        return null;
    }

    public static File createFile(String filePath,String fileName,String fileFormat) {
        String f = filePath + "/" + fileName + fileFormat;
        File file = new File(f);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * sd card
     */
    public static File getExternalCacheDir() {
        return ContextProvider.getContext().getExternalCacheDir();
        //return new File(Environment.getExternalStorageDirectory(), "/Android/data/" + BaseApp.getInstance().getPackageName() + "/cache/");
    }

    /**
     * RAM
     */
    public static File getInnerCacheDir() {
        return ContextProvider.getContext().getCacheDir();
    }

    public static File getExternalFilesDir() {
        return ContextProvider.getContext().getExternalFilesDir(null);
        //return new File(Environment.getExternalStorageDirectory(), "/Android/data/" + BaseApp.getInstance().getPackageName() + "/files/");
    }

    public static File getInnerFilesDir() {
        return ContextProvider.getContext().getFilesDir();
    }


    public static boolean isExistSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
