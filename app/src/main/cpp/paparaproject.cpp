#include <jni.h>
#include <string>
extern "C"
JNIEXPORT jstring JNICALL
Java_com_enons_paparaproject_MainActivityKt_getApiKeyFromNdk(JNIEnv *env, jclass clazz) {
    std::string apiKey = "Bearer YOUR API KEY";
    return env->NewStringUTF(apiKey.c_str());
}

