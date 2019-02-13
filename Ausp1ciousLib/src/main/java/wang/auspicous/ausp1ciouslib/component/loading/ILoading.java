package wang.auspicous.ausp1ciouslib.component.loading;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

/**
 * Created by Ausp1cious on 2019/2/13.
 * 接口，定义Loading的行为，以及返回具体的Loading
 */
public interface ILoading {
  /**
   * 获取LoadingView
   * @return LoadingView
   */
  View getLoadingView(Context context);

  /**
   * 获取LoadingDialog
   * @return LoadingDialog
   */
  Dialog getLoadingDialog(Context context);

  /**
   * 显示Loading
   */
  void showLoading();

  /**
   * 隐藏Loading
   */
  void hideLoading();
}
