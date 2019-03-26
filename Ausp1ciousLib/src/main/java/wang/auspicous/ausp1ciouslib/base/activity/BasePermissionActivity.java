package wang.auspicous.ausp1ciouslib.base.activity;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import java.util.List;

import androidx.annotation.NonNull;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Ausp1cious on 2019/1/30.
 */
public abstract class BasePermissionActivity extends BaseSystemSettingActivity implements EasyPermissions.PermissionCallbacks {
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
          @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
  }

  @Override
  public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

  }

  @Override
  public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

  }
}
