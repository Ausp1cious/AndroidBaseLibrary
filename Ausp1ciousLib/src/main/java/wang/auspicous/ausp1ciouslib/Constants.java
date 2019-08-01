package wang.auspicous.ausp1ciouslib;

/**
 * Created by Ausp1cious on 2019/1/31.
 * 常量类
 */
public interface Constants {
    /**
     * 权限相关的常量
     * 权限控制编码
     * 10表示权限
     * 权限类别：00表示一般权限 01表示危险权限
     * 权限组：00 表示一般权限 01 表示危险权限组
     * 具体的权限：001开始
     */
    interface Permission {
        /**
         * 一般权限
         */
        //网络状态权限
        int PERMISSION_ACCESS_NETWORK_STATE = 100000001;
        int PERMISSION_INTERNET = 100000002;
        int PERMISSION_ACCESS_ACCESS_NOTIFICATION_POLICY = 100000003;
        int PERMISSION_ACCESS_WIFI_STATE = 100000004;
        int PERMISSION_BLUETOOTH = 100000005;
        int PERMISSION_BLUETOOTH_ADMIN = 100000006;
        int PERMISSION_BROADCAST_STICKY = 100000007;
        int PERMISSION_CHANGE_NETWORK_STATE = 100000008;
        int PERMISSION_CHANGE_WIFI_MULTICAST_STATE = 100000009;
        int PERMISSION_CHANGE_WIFI_STATE = 100000010;
        int PERMISSION_DISABLE_KEYGUARD = 100000011;
        int PERMISSION_EXPAND_STATUS_BAR = 100000012;
        int PERMISSION_GET_PACKAGE_SIZE = 100000013;
        int PERMISSION_ACCESS_LOCATION_EXTRA_COMMANDS = 100000014;
        int PERMISSION_KILL_BACKGROUND_PROCESSES = 100000015;
        int PERMISSION_MODIFY_AUDIO_SETTINGS = 100000016;
        int PERMISSION_NFC = 100000017;
        int PERMISSION_READ_SYNC_SETTINGS = 100000018;
        int PERMISSION_READ_SYNC_STATS = 100000019;
        int PERMISSION_RECEIVE_BOOT_COMPLETED = 100000020;
        int PERMISSION_REORDER_TASKS = 100000021;
        int PERMISSION_REQUEST_INSTALL_PACKAGES = 100000022;
        int PERMISSION_SET_TIME_ZONE = 100000023;
        int PERMISSION_SET_WALLPAPER = 100000024;
        int PERMISSION_SET_WALLPAPER_HINTS = 100000025;
        int PERMISSION_TRANSMIT_IR = 100000026;
        int PERMISSION_USE_FINGERPRINT = 100000027;
        int PERMISSION_VIBRATE = 100000028;
        int PERMISSION_WAKE_LOCK = 100000029;
        int PERMISSION_WRITE_SYNC_SETTINGS = 100000030;
        int PERMISSION_SET_ALARM = 100000031;
        int PERMISSION_INSTALL_SHORTCUT = 100000032;
        int PERMISSION_UNINSTALL_SHORTCUT = 100000033;
        /**
         * 危险权限
         */
        //存储权限(STORAGE)
        int PERMISSION_READ_EXTERNAL_STORAGE = 100101001;
        int PERMISSION_WRITE_EXTERNAL_STORAGE = 100101002;
        //PHONE
        int PERMISSION_READ_PHONE_STATE = 100102001;
        int PERMISSION_CALL_PHONE = 100102002;
        int PERMISSION_READ_CALL_LOG = 100102003;
        int PERMISSION_WRITE_CALL_LOG = 100102004;
        int PERMISSION_ADD_VOICEMAIL = 100102005;
        int PERMISSION_USE_SIP = 100102006;
        int PERMISSION_PROCESS_OUTGOING_CALLS = 100102007;
        //SMS
        int PERMISSION_SEND_SMS = 100103001;
        int PERMISSION_RECEIVE_SMS = 100103002;
        int PERMISSION_READ_SMS = 100103003;
        int PERMISSION_RECEIVE_WAP_PUSH = 100103004;
        int PERMISSION_RECEIVE_MMS = 100103005;
        //CONTACTS
        int PERMISSION_READ_CONTACTS = 100104001;
        int PERMISSION_WRITE_CONTACTS = 100104002;
        int PERMISSION_GET_ACCOUNTS = 100104003;
        //CALENDAR
        int PERMISSION_READ_CALENDAR = 100105001;
        int PERMISSION_WRITE_CALENDAR = 100105002;
        //CAMERA
        int PERMISSION_CAMERA = 100106001;
        //MICROPHONE
        int PERMISSION_RECORD_AUDIO = 100106002;
        //SENSORS
        int PERMISSION_BODY_SENSORS = 100107001;
        //LOCATION
        int PERMISSION_ACCESS_FINE_LOCATION = 100108001;
        int PERMISSION_ACCESS_COARSE_LOCATION = 100108001;
    }

    interface SystemInfo {
        //电池相关
        int SYSTEM_BATTERY_CHANGED = 200100001; //电源变化
        int SYSTEM_BATTERY_LOW = 200100002; //电源过低
        int SYSTEM_BATTERY_OKAY = 200100003; //电源正常
        int SYSTEM_BATTERY_CONNECTED = 200100004; //插上外接电源
        int SYSTEM_BATTERY_DISCONNECTED = 200100005; //已断开外部电源连接时发出的广播
        //屏幕相关
        int SYSTEM_SCREEN_OFF = 200200001;//屏幕被关闭之后的广播
        int SYSTEM_SCREEN_ON = 200200001;//	屏幕被打开之后的广播
    }

    /**
     * SharedPreference 相关名字
     */
    interface SharedPreference {
        String NAME_OF_SHARED_PREFERENCE = "Auspicious";
    }

    /**
     * EventBus状态码
     * xx 模块 xxx 功能  xxxx 事件
     */
    interface EventCode {

    }

    //屏幕方向类型
    interface ScreenOrientationType {
        //随系统
        int ORIENTATION_LANDSCAPE_UNSPECIFIED = -1;
        //横屏
        int ORIENTATION_LANDSCAPE = 0;
        //竖屏
        int ORIENTATION_PORTRAIT = 1;
        //用户当前的首选方向
        int SCREEN_ORIENTATION_USER = 2;
        //继承Activity堆栈中当前Activity下面的那个Activity的方向
        int SCREEN_ORIENTATION_BEHIND = 3;
        //根据传感器控制方向（没有竖向的反转方向）
        int SCREEN_ORIENTATION_SENSOR = 4;
        //忽略物理方向传感器（设置后，如果用户横屏拿着手机进行播放，界面也是横屏的，一旦设置了这个属性之后，手机界面会先变换到竖屏，然后才会锁死方向传感器）
        int SCREEN_ORIENTATION_NOSENSOR = 5;
        //在横屏方向使用传感器
        int SCREEN_ORIENTATION_SENSOR_LANDSCAPE = 6;
        //在竖屏方向使用
        int SCREEN_ORIENTATION_SENSOR_PPRTRAIT = 7;
        //反向横屏
        int SCREEN_ORIENTATION_REVERSE_LANDSCAPE = 8;
        //反向竖屏
        int SCREEN_ORIENTATION_REVERSE_PORTRAIT = 9;
        //根据传感器控制方向（全部方向）
        int SCREEN_ORIENTATION_FULL_SENSOR = 10;
        //使用用户指定的横屏
        int SCREEN_ORIENTATION_USER_LANDSCAPE = 11;
        //使用用户指定的竖屏
        int SCREEN_ORIENTATION_USER_PORTRAIT = 12;
        //根据用户指定设置屏幕
        int SCREEN_ORIENTATION_FULL_USER = 13;
        //锁死当前用户屏幕，方向传感器不生效
        int SCREEN_ORIENTATION_LOCKED = 14;
    }
}
