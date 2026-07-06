package com.amazon.livingroom.mediapipelinebackend;

import android.content.Context;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class SystemProperties {
    public static String get(Context context, String str, String str2) throws IllegalArgumentException {
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) clsLoadClass.getMethod("get", String.class, String.class).invoke(clsLoadClass, str, str2);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e2) {
            MpbLog.e("Failed to get system property '" + str + "'", e2);
            return str2;
        }
    }
}
