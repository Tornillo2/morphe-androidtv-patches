package com.amazon.avod.mpb.api.core;

import android.util.Log;
import com.amazon.avod.mpb.api.callback.LogCallback;
import java.util.Arrays;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MpbLogKt {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LogCallback.LogLevel.values().length];
            try {
                iArr[LogCallback.LogLevel.LEVEL_VERBOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LogCallback.LogLevel.LEVEL_TRACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[LogCallback.LogLevel.LEVEL_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[LogCallback.LogLevel.LEVEL_WARN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[LogCallback.LogLevel.LEVEL_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @NotNull
    public static final LogCallback getDefaultLogginCallback() {
        return new MpbLogKt$$ExternalSyntheticLambda0();
    }

    public static final void getDefaultLogginCallback$lambda$0(LogCallback.LogLevel logLevel, Throwable th, String format, Object[] args) {
        Intrinsics.checkNotNullParameter(logLevel, "logLevel");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        String strConcat = "[NoCallback] ".concat(format);
        Object[] objArrCopyOf = Arrays.copyOf(args, args.length);
        String str = String.format(strConcat, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
        int i = WhenMappings.$EnumSwitchMapping$0[logLevel.ordinal()];
        if (i == 1) {
            Log.v(com.amazon.livingroom.mediapipelinebackend.MpbLog.TAG, str, th);
            return;
        }
        if (i == 2) {
            Log.d(com.amazon.livingroom.mediapipelinebackend.MpbLog.TAG, str, th);
            return;
        }
        if (i == 3) {
            Log.i(com.amazon.livingroom.mediapipelinebackend.MpbLog.TAG, str, th);
        } else if (i == 4) {
            Log.w(com.amazon.livingroom.mediapipelinebackend.MpbLog.TAG, str, th);
        } else {
            if (i != 5) {
                throw new NoWhenBranchMatchedException();
            }
            Log.e(com.amazon.livingroom.mediapipelinebackend.MpbLog.TAG, str, th);
        }
    }

    public static final void getDefaultLogginCallback$lambda$1(LogCallback.LogLevel logLevel, Throwable th, String str, Object[] objArr) {
        Intrinsics.checkNotNullParameter(logLevel, "<unused var>");
        Intrinsics.checkNotNullParameter(str, "<unused var>");
        Intrinsics.checkNotNullParameter(objArr, "<unused var>");
    }
}
