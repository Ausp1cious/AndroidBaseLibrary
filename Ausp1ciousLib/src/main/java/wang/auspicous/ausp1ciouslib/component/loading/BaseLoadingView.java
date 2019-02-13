package wang.auspicous.ausp1ciouslib.component.loading;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import wang.auspicous.ausp1ciouslib.R;

/**
 * Created by Ausp1cious on 2019/2/13.
 */
public class BaseLoadingView implements ILoading {

  private View mView;
  private RelativeLayout mLoadingView;
  @Override
  public View getLoadingView(Context context) {
    return inflateView(context);
  }

  @Override
  public Dialog getLoadingDialog() {
    return null;
  }

  @Override
  public void showLoading() {
    mLoadingView.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoading() {
    mLoadingView.setVisibility(View.GONE);
  }

  private View inflateView(Context context) {
    mView = LayoutInflater.from(context).inflate(R.layout.layout_loading, null);
    mLoadingView = mView.findViewById(R.id.rl_loading);
    return mView;
  }
}
