package com.amazon.livingroom.mediapipelinebackend;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import android.util.Log;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MpbLog {
    public static final String TAG = "MPB_DRM";
    public static boolean allLevelsAsWarning;
    public static boolean logToNative;

    /* JADX INFO: renamed from: com.amazon.livingroom.mediapipelinebackend.MpbLog$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$livingroom$mediapipelinebackend$MpbLog$Level;

        static {
            int[] iArr = new int[Level.values().length];
            $SwitchMap$com$amazon$livingroom$mediapipelinebackend$MpbLog$Level = iArr;
            try {
                iArr[Level.VERBOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$livingroom$mediapipelinebackend$MpbLog$Level[Level.TRACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$livingroom$mediapipelinebackend$MpbLog$Level[Level.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$livingroom$mediapipelinebackend$MpbLog$Level[Level.WARN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$livingroom$mediapipelinebackend$MpbLog$Level[Level.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Level {
        VERBOSE,
        TRACE,
        INFO,
        WARN,
        ERROR
    }

    public static void e(String str) {
        print(Level.ERROR, str, null);
    }

    public static String getStackTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        try {
            PrintWriter printWriter = new PrintWriter(stringWriter);
            try {
                th.printStackTrace(printWriter);
                String string = stringWriter.toString();
                printWriter.close();
                try {
                    stringWriter.close();
                    return string;
                } catch (IOException e) {
                    throw new AssertionError("StringWriter.close is a no-op so it should never throw exceptions", e);
                }
            } finally {
            }
        } catch (Throwable th2) {
            try {
                stringWriter.close();
                throw th2;
            } catch (IOException e2) {
                throw new AssertionError("StringWriter.close is a no-op so it should never throw exceptions", e2);
            }
        }
    }

    public static void i(String str) {
        print(Level.INFO, str, null);
    }

    public static void print(Level level, String str) {
        print(level, str, null);
    }

    public static void printIgnite(Level level, String str, Throwable th) {
        if (allLevelsAsWarning) {
            int iOrdinal = level.ordinal();
            Level level2 = Level.WARN;
            if (iOrdinal < level2.ordinal()) {
                level = level2;
            }
        }
        if (th == null) {
            NativeMediaPipelineBackend.onLogMessage(level.ordinal(), str);
            return;
        }
        int iOrdinal2 = level.ordinal();
        StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(str, StringUtils.LF);
        sbM.append(getStackTrace(th));
        NativeMediaPipelineBackend.onLogMessage(iOrdinal2, sbM.toString());
    }

    public static void printLogcat(Level level, String str, Throwable th) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$livingroom$mediapipelinebackend$MpbLog$Level[level.ordinal()];
        if (i == 1) {
            Log.v(TAG, str, th);
            return;
        }
        if (i == 2) {
            Log.d(TAG, str, th);
            return;
        }
        if (i == 3) {
            Log.i(TAG, str, th);
        } else if (i == 4) {
            Log.w(TAG, str, th);
        } else {
            if (i != 5) {
                return;
            }
            Log.e(TAG, str, th);
        }
    }

    public static void setLogAllLevelsAsWarning(boolean z) {
        allLevelsAsWarning = z;
    }

    public static void setLogToNative(boolean z) {
        logToNative = z;
    }

    public static void t(String str) {
        print(Level.TRACE, str, null);
    }

    public static void v(String str) {
        print(Level.VERBOSE, str, null);
    }

    public static void w(String str) {
        print(Level.WARN, str, null);
    }

    public static void print(Level level, String str, Throwable th) {
        if (logToNative) {
            printIgnite(level, str, th);
        } else {
            printLogcat(level, str, th);
        }
    }

    public static void e(String str, Throwable th) {
        print(Level.ERROR, str, th);
    }

    public static void i(String str, Throwable th) {
        print(Level.INFO, str, th);
    }

    public static void t(String str, Throwable th) {
        print(Level.TRACE, str, th);
    }

    public static void v(String str, Throwable th) {
        print(Level.VERBOSE, str, th);
    }

    public static void w(String str, Throwable th) {
        print(Level.WARN, str, th);
    }
}
