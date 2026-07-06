package com.amazon.livingroom.mediapipelinebackend;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.minerva.identifiers.schemaid.MetricSchemaUUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ErrorManager {
    public static final int MAX_STACK_FRAMES_FIRST_EXCEPTION = 4;
    public static final int MAX_STACK_FRAMES_OTHER_EXCEPTIONS = 2;
    public final MediaPipelineListener listener;
    public final NativeMediaPipelineBackend nativeMpb;

    public ErrorManager(NativeMediaPipelineBackend nativeMediaPipelineBackend, MediaPipelineListener mediaPipelineListener) {
        this.nativeMpb = nativeMediaPipelineBackend;
        this.listener = mediaPipelineListener;
    }

    public static void append(@NonNull StringBuilder sb, @NonNull Throwable th) {
        int i;
        Throwable cause = th.getCause();
        if (cause != null) {
            append(sb, cause);
            i = 2;
        } else {
            i = 4;
        }
        sb.append(MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER);
        sb.append(th.getClass().getName());
        String message = th.getMessage();
        if (!TextUtils.isEmpty(message) && (cause == null || !message.equals(cause.toString()))) {
            sb.append(": ");
            sb.append(message);
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        int iMin = Math.min(i, stackTrace.length);
        for (int i2 = 0; i2 < iMin; i2++) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            sb.append(", at ");
            sb.append(stackTraceElement.getClassName());
            sb.append('.');
            sb.append(stackTraceElement.getMethodName());
            sb.append('(');
            sb.append(stackTraceElement.getFileName());
            sb.append(':');
            sb.append(stackTraceElement.getLineNumber());
            sb.append(')');
        }
    }

    public int onError(int i, @NonNull String str) {
        return onError(i, str, true, null);
    }

    public int onError(int i, @NonNull String str, Throwable th) {
        return onError(i, str, true, th);
    }

    public int onError(int i, @NonNull String str, boolean z) {
        return onError(i, str, z, null);
    }

    public int onError(int i, @NonNull String str, boolean z, @Nullable Throwable th) throws Throwable {
        MpbLog.e(str, th);
        StringBuilder sb = new StringBuilder(str);
        if (th != null) {
            append(sb, th);
        }
        this.nativeMpb.onError(i, sb.toString(), z);
        this.listener.onError(i, str, z, th);
        return i;
    }
}
