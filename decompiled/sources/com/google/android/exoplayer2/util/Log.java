package com.google.android.exoplayer2.util;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.UnknownHostException;
import kotlinx.serialization.json.JsonKt;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.dataflow.qual.Pure;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Log {
    public static final int LOG_LEVEL_ALL = 0;
    public static final int LOG_LEVEL_ERROR = 3;
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_OFF = Integer.MAX_VALUE;
    public static final int LOG_LEVEL_WARNING = 2;

    @GuardedBy("lock")
    public static int logLevel = 0;

    @GuardedBy("lock")
    public static boolean logStackTraces = true;
    public static final Object lock = new Object();

    @GuardedBy("lock")
    public static Logger logger = Logger.DEFAULT;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface LogLevel {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Logger {
        public static final Logger DEFAULT = new AnonymousClass1();

        /* JADX INFO: renamed from: com.google.android.exoplayer2.util.Log$Logger$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 implements Logger {
            @Override // com.google.android.exoplayer2.util.Log.Logger
            public void d(String str, String str2) {
                android.util.Log.d(str, str2);
            }

            @Override // com.google.android.exoplayer2.util.Log.Logger
            public void e(String str, String str2) {
                android.util.Log.e(str, str2);
            }

            @Override // com.google.android.exoplayer2.util.Log.Logger
            public void i(String str, String str2) {
                android.util.Log.i(str, str2);
            }

            @Override // com.google.android.exoplayer2.util.Log.Logger
            public void w(String str, String str2) {
                android.util.Log.w(str, str2);
            }
        }

        void d(String str, String str2);

        void e(String str, String str2);

        void i(String str, String str2);

        void w(String str, String str2);
    }

    @Pure
    public static String appendThrowableString(String str, @Nullable Throwable th) {
        String throwableString = getThrowableString(th);
        if (TextUtils.isEmpty(throwableString)) {
            return str;
        }
        StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(str, "\n  ");
        sbM.append(throwableString.replace(StringUtils.LF, "\n  "));
        sbM.append('\n');
        return sbM.toString();
    }

    @Pure
    public static void d(@androidx.annotation.Size(max = 23) String str, String str2) {
        synchronized (lock) {
            try {
                if (logLevel == 0) {
                    logger.d(str, str2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Pure
    public static void e(@androidx.annotation.Size(max = 23) String str, String str2) {
        synchronized (lock) {
            try {
                if (logLevel <= 3) {
                    logger.e(str, str2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Pure
    public static int getLogLevel() {
        int i;
        synchronized (lock) {
            i = logLevel;
        }
        return i;
    }

    @Nullable
    @Pure
    public static String getThrowableString(@Nullable Throwable th) {
        synchronized (lock) {
            try {
                if (th == null) {
                    return null;
                }
                if (isCausedByUnknownHostException(th)) {
                    return "UnknownHostException (no network)";
                }
                if (logStackTraces) {
                    return android.util.Log.getStackTraceString(th).trim().replace("\t", JsonKt.defaultIndent);
                }
                return th.getMessage();
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Pure
    public static void i(@androidx.annotation.Size(max = 23) String str, String str2) {
        synchronized (lock) {
            try {
                if (logLevel <= 1) {
                    logger.i(str, str2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Pure
    public static boolean isCausedByUnknownHostException(@Nullable Throwable th) {
        while (th != null) {
            if (th instanceof UnknownHostException) {
                return true;
            }
            th = th.getCause();
        }
        return false;
    }

    public static void setLogLevel(int i) {
        synchronized (lock) {
            logLevel = i;
        }
    }

    public static void setLogStackTraces(boolean z) {
        synchronized (lock) {
            logStackTraces = z;
        }
    }

    public static void setLogger(Logger logger2) {
        synchronized (lock) {
            logger = logger2;
        }
    }

    @Pure
    public static void w(@androidx.annotation.Size(max = 23) String str, String str2) {
        synchronized (lock) {
            try {
                if (logLevel <= 2) {
                    logger.w(str, str2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Pure
    public static void d(@androidx.annotation.Size(max = 23) String str, String str2, @Nullable Throwable th) {
        d(str, appendThrowableString(str2, th));
    }

    @Pure
    public static void e(@androidx.annotation.Size(max = 23) String str, String str2, @Nullable Throwable th) {
        e(str, appendThrowableString(str2, th));
    }

    @Pure
    public static void i(@androidx.annotation.Size(max = 23) String str, String str2, @Nullable Throwable th) {
        i(str, appendThrowableString(str2, th));
    }

    @Pure
    public static void w(@androidx.annotation.Size(max = 23) String str, String str2, @Nullable Throwable th) {
        w(str, appendThrowableString(str2, th));
    }
}
