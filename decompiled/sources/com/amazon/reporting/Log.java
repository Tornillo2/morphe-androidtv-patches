package com.amazon.reporting;

import android.content.Context;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Log {
    public static boolean LOGGING_ENABLED = true;

    public static void configure(Context context) {
        LOGGING_ENABLED = (context.getApplicationInfo().flags & 2) != 0;
    }

    public static void d(String str, String str2) {
        if (LOGGING_ENABLED) {
            android.util.Log.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (LOGGING_ENABLED) {
            android.util.Log.e(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (LOGGING_ENABLED) {
            android.util.Log.i(str, str2);
        }
    }

    public static boolean isDebugLoggingEnabled() {
        return LOGGING_ENABLED;
    }

    public static boolean isLoggable(String str, int i) {
        return LOGGING_ENABLED && android.util.Log.isLoggable(str, i);
    }

    public static void v(String str, String str2) {
        if (LOGGING_ENABLED) {
            android.util.Log.v(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (LOGGING_ENABLED) {
            android.util.Log.w(str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (LOGGING_ENABLED) {
            android.util.Log.d(str, str2, th);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (LOGGING_ENABLED) {
            android.util.Log.e(str, str2, th);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (LOGGING_ENABLED) {
            android.util.Log.i(str, str2, th);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (LOGGING_ENABLED) {
            android.util.Log.v(str, str2, th);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (LOGGING_ENABLED) {
            android.util.Log.w(str, str2, th);
        }
    }
}
