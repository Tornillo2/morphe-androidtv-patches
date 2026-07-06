package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.avod.mpb.api.callback.LogCallback;
import com.amazon.livingroom.mediapipelinebackend.MpbLog;
import java.util.Arrays;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class FtvMpbApiLogCallbackImpl implements LogCallback {
    public final long logCb;
    public final long userdata;

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

    public FtvMpbApiLogCallbackImpl(long j, long j2) {
        this.logCb = j;
        this.userdata = j2;
    }

    public final MpbLog.Level convertLevel(LogCallback.LogLevel logLevel) {
        int i = WhenMappings.$EnumSwitchMapping$0[logLevel.ordinal()];
        if (i == 1) {
            return MpbLog.Level.VERBOSE;
        }
        if (i == 2) {
            return MpbLog.Level.TRACE;
        }
        if (i == 3) {
            return MpbLog.Level.INFO;
        }
        if (i == 4) {
            return MpbLog.Level.WARN;
        }
        if (i == 5) {
            return MpbLog.Level.ERROR;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // com.amazon.avod.mpb.api.callback.LogCallback
    public void onLog(@NotNull LogCallback.LogLevel logLevel, @Nullable Throwable th, @NotNull String format, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(logLevel, "logLevel");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        MpbLog.Level levelConvertLevel = convertLevel(logLevel);
        String strConcat = "[FtvMpb Internal] ".concat(format);
        Object[] objArrCopyOf = Arrays.copyOf(args, args.length);
        MpbLog.print(levelConvertLevel, String.format(strConcat, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length)), th);
    }
}
