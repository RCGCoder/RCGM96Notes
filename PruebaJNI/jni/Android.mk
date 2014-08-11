LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := PruebaJNI
LOCAL_SRC_FILES := PruebaJNI.cpp
LOCAL_LDLIBS := -llog
include $(BUILD_SHARED_LIBRARY)
