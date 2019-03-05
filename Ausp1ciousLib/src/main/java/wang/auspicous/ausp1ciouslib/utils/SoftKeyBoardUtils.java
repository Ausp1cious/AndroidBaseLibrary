package wang.auspicous.ausp1ciouslib.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Ausp1cious on 2019/3/5.
 */
public class SoftKeyBoardUtils {
    /**
     * 隐藏软键盘
     * @param activity 当前的Activity
     * @param inputMethodManager 输入管理器，在Activity或者Fragment销毁的时候置空
     */
    public static void hideSoftKeyBoard(Activity activity,InputMethodManager inputMethodManager) {
        View localView = activity.getCurrentFocus();
        if (inputMethodManager == null) {
            inputMethodManager = ((InputMethodManager) activity.getSystemService(
                    Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (inputMethodManager != null)) {
            inputMethodManager.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }
}
