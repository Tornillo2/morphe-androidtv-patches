package androidx.tracing;

import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Trace {
    public static final int MAX_TRACE_LABEL_LENGTH = 127;
    public static final String TAG = "Trace";
    public static Method sAsyncTraceBeginMethod;
    public static Method sAsyncTraceEndMethod;
    public static boolean sHasAppTracingEnabled;
    public static Method sIsTagEnabledMethod;
    public static Method sTraceCounterMethod;
    public static long sTraceTagApp;

    public static void beginAsyncSection(@NonNull String str, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            TraceApi29Impl.beginAsyncSection(truncatedTraceSectionLabel(str), i);
        } else {
            beginAsyncSectionFallback(truncatedTraceSectionLabel(str), i);
        }
    }

    public static void beginAsyncSectionFallback(@NonNull String str, int i) {
        try {
            if (sAsyncTraceBeginMethod == null) {
                sAsyncTraceBeginMethod = android.os.Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
            }
            sAsyncTraceBeginMethod.invoke(null, Long.valueOf(sTraceTagApp), str, Integer.valueOf(i));
        } catch (Exception e) {
            handleException("asyncTraceBegin", e);
        }
    }

    public static void beginSection(@NonNull String str) {
        android.os.Trace.beginSection(truncatedTraceSectionLabel(str));
    }

    public static void endAsyncSection(@NonNull String str, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            TraceApi29Impl.endAsyncSection(truncatedTraceSectionLabel(str), i);
        } else {
            endAsyncSectionFallback(truncatedTraceSectionLabel(str), i);
        }
    }

    public static void endAsyncSectionFallback(@NonNull String str, int i) {
        try {
            if (sAsyncTraceEndMethod == null) {
                sAsyncTraceEndMethod = android.os.Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
            }
            sAsyncTraceEndMethod.invoke(null, Long.valueOf(sTraceTagApp), str, Integer.valueOf(i));
        } catch (Exception e) {
            handleException("asyncTraceEnd", e);
        }
    }

    public static void endSection() {
        android.os.Trace.endSection();
    }

    public static void forceEnableAppTracing() {
        if (Build.VERSION.SDK_INT < 31) {
            try {
                if (sHasAppTracingEnabled) {
                    return;
                }
                sHasAppTracingEnabled = true;
                android.os.Trace.class.getMethod("setAppTracingAllowed", Boolean.TYPE).invoke(null, Boolean.TRUE);
            } catch (Exception e) {
                handleException("setAppTracingAllowed", e);
            }
        }
    }

    public static void handleException(@NonNull String str, @NonNull Exception exc) {
        if (exc instanceof InvocationTargetException) {
            Throwable cause = exc.getCause();
            if (!(cause instanceof RuntimeException)) {
                throw new RuntimeException(cause);
            }
            throw ((RuntimeException) cause);
        }
        Log.v(TAG, "Unable to call " + str + " via reflection", exc);
    }

    public static boolean isEnabled() {
        return Build.VERSION.SDK_INT >= 29 ? TraceApi29Impl.isEnabled() : isEnabledFallback();
    }

    public static boolean isEnabledFallback() {
        try {
            if (sIsTagEnabledMethod == null) {
                sTraceTagApp = android.os.Trace.class.getField("TRACE_TAG_APP").getLong(null);
                sIsTagEnabledMethod = android.os.Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) sIsTagEnabledMethod.invoke(null, Long.valueOf(sTraceTagApp))).booleanValue();
        } catch (Exception e) {
            handleException("isTagEnabled", e);
            return false;
        }
    }

    public static void setCounter(@NonNull String str, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            TraceApi29Impl.setCounter(truncatedTraceSectionLabel(str), i);
        } else {
            setCounterFallback(truncatedTraceSectionLabel(str), i);
        }
    }

    public static void setCounterFallback(@NonNull String str, int i) {
        try {
            if (sTraceCounterMethod == null) {
                sTraceCounterMethod = android.os.Trace.class.getMethod("traceCounter", Long.TYPE, String.class, Integer.TYPE);
            }
            sTraceCounterMethod.invoke(null, Long.valueOf(sTraceTagApp), str, Integer.valueOf(i));
        } catch (Exception e) {
            handleException("traceCounter", e);
        }
    }

    @NonNull
    public static String truncatedTraceSectionLabel(@NonNull String str) {
        return str.length() <= 127 ? str : str.substring(0, 127);
    }
}
