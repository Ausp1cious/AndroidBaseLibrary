package wang.auspicous.ausp1ciouslib.utils.cryptology;

public class Md5NativeUtils {
    static {
        System.loadLibrary("md5");
    }

    public static native void test();

    public static native String encryptMD5(String origin);

    public static native String decryptMD5(String md5);
}
