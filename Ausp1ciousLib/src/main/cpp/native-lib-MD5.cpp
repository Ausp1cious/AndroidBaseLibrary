//
// Created by ausp1cious on 7/25/19.
//
#include "jni.h"
#include "MD5.h"

extern "C"
JNIEXPORT void JNICALL
Java_wang_auspicous_ausp1ciouslib_utils_cryptology_Md5NativeUtils_test(JNIEnv *env, jobject obj) {

}

extern "C"
JNIEXPORT jstring JNICALL
Java_wang_auspicous_ausp1ciouslib_utils_cryptology_Md5NativeUtils_encryptMD5(JNIEnv *env,
                                                                             jobject instance,
                                                                             jstring str) {
    const char *originStr;
    //将jstring转化成char *类型
    originStr = env->GetStringUTFChars(str, 0);
    MD5 md5 = MD5(originStr);
    std::string md5Result = md5.hexdigest();
    //将char *类型转化成jstring返回给Java层
    return env->NewStringUTF(md5Result.c_str());
}
