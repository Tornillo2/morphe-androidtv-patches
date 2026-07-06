package com.google.android.exoplayer2;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.source.MediaPeriodId;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ExoPlaybackException extends PlaybackException {
    public static final int TYPE_REMOTE = 3;
    public static final int TYPE_RENDERER = 1;
    public static final int TYPE_SOURCE = 0;
    public static final int TYPE_UNEXPECTED = 2;
    public final boolean isRecoverable;

    @Nullable
    public final MediaPeriodId mediaPeriodId;

    @Nullable
    public final Format rendererFormat;
    public final int rendererFormatSupport;
    public final int rendererIndex;

    @Nullable
    public final String rendererName;
    public final int type;
    public static final Bundleable.Creator<ExoPlaybackException> CREATOR = new ExoPlaybackException$$ExternalSyntheticLambda0();
    public static final String FIELD_TYPE = Util.intToStringMaxRadix(1001);
    public static final String FIELD_RENDERER_NAME = Integer.toString(1002, 36);
    public static final String FIELD_RENDERER_INDEX = Integer.toString(1003, 36);
    public static final String FIELD_RENDERER_FORMAT = Integer.toString(1004, 36);
    public static final String FIELD_RENDERER_FORMAT_SUPPORT = Integer.toString(1005, 36);
    public static final String FIELD_IS_RECOVERABLE = Integer.toString(1006, 36);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    /* JADX INFO: renamed from: $r8$lambda$1XKy3W7SelM_FKalWARGEC-f72M, reason: not valid java name */
    public static /* synthetic */ ExoPlaybackException m366$r8$lambda$1XKy3W7SelM_FKalWARGECf72M(Bundle bundle) {
        return new ExoPlaybackException(bundle);
    }

    public ExoPlaybackException(int i, Throwable th, int i2) {
        this(i, th, null, i2, null, -1, null, 4, false);
    }

    public static ExoPlaybackException createForRemote(String str) {
        return new ExoPlaybackException(3, null, str, 1001, null, -1, null, 4, false);
    }

    public static ExoPlaybackException createForRenderer(Throwable th, String str, int i, @Nullable Format format, int i2, boolean z, int i3) {
        return new ExoPlaybackException(1, th, null, i3, str, i, format, format == null ? 4 : i2, z);
    }

    public static ExoPlaybackException createForSource(IOException iOException, int i) {
        return new ExoPlaybackException(0, iOException, i);
    }

    @Deprecated
    public static ExoPlaybackException createForUnexpected(RuntimeException runtimeException) {
        return createForUnexpected(runtimeException, 1000);
    }

    public static String deriveMessage(int i, @Nullable String str, @Nullable String str2, int i2, @Nullable Format format, int i3) {
        String str3;
        if (i == 0) {
            str3 = "Source error";
        } else if (i != 1) {
            str3 = i != 3 ? "Unexpected runtime error" : "Remote error";
        } else {
            str3 = str2 + " error, index=" + i2 + ", format=" + format + ", format_supported=" + Util.getFormatSupportString(i3);
        }
        return !TextUtils.isEmpty(str) ? AbstractResolvableFuture$$ExternalSyntheticOutline1.m(str3, ": ", str) : str3;
    }

    @CheckResult
    public ExoPlaybackException copyWithMediaPeriodId(@Nullable MediaPeriodId mediaPeriodId) {
        return new ExoPlaybackException(getMessage(), getCause(), this.errorCode, this.type, this.rendererName, this.rendererIndex, this.rendererFormat, this.rendererFormatSupport, mediaPeriodId, this.timestampMs, this.isRecoverable);
    }

    @Override // com.google.android.exoplayer2.PlaybackException
    public boolean errorInfoEquals(@Nullable PlaybackException playbackException) {
        if (!super.errorInfoEquals(playbackException)) {
            return false;
        }
        ExoPlaybackException exoPlaybackException = (ExoPlaybackException) playbackException;
        return this.type == exoPlaybackException.type && Util.areEqual(this.rendererName, exoPlaybackException.rendererName) && this.rendererIndex == exoPlaybackException.rendererIndex && Util.areEqual(this.rendererFormat, exoPlaybackException.rendererFormat) && this.rendererFormatSupport == exoPlaybackException.rendererFormatSupport && Util.areEqual(this.mediaPeriodId, exoPlaybackException.mediaPeriodId) && this.isRecoverable == exoPlaybackException.isRecoverable;
    }

    public Exception getRendererException() {
        Assertions.checkState(this.type == 1);
        Throwable cause = getCause();
        cause.getClass();
        return (Exception) cause;
    }

    public IOException getSourceException() {
        Assertions.checkState(this.type == 0);
        Throwable cause = getCause();
        cause.getClass();
        return (IOException) cause;
    }

    public RuntimeException getUnexpectedException() {
        Assertions.checkState(this.type == 2);
        Throwable cause = getCause();
        cause.getClass();
        return (RuntimeException) cause;
    }

    @Override // com.google.android.exoplayer2.PlaybackException, com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        bundle.putInt(FIELD_TYPE, this.type);
        bundle.putString(FIELD_RENDERER_NAME, this.rendererName);
        bundle.putInt(FIELD_RENDERER_INDEX, this.rendererIndex);
        Format format = this.rendererFormat;
        if (format != null) {
            bundle.putBundle(FIELD_RENDERER_FORMAT, format.toBundle(false));
        }
        bundle.putInt(FIELD_RENDERER_FORMAT_SUPPORT, this.rendererFormatSupport);
        bundle.putBoolean(FIELD_IS_RECOVERABLE, this.isRecoverable);
        return bundle;
    }

    public ExoPlaybackException(int i, @Nullable Throwable th, @Nullable String str, int i2, @Nullable String str2, int i3, @Nullable Format format, int i4, boolean z) {
        this(deriveMessage(i, str, str2, i3, format, i4), th, i2, i, str2, i3, format, i4, null, SystemClock.elapsedRealtime(), z);
    }

    public static ExoPlaybackException createForUnexpected(RuntimeException runtimeException, int i) {
        return new ExoPlaybackException(2, runtimeException, i);
    }

    public ExoPlaybackException(Bundle bundle) {
        super(bundle);
        this.type = bundle.getInt(FIELD_TYPE, 2);
        this.rendererName = bundle.getString(FIELD_RENDERER_NAME);
        this.rendererIndex = bundle.getInt(FIELD_RENDERER_INDEX, -1);
        Bundle bundle2 = bundle.getBundle(FIELD_RENDERER_FORMAT);
        this.rendererFormat = bundle2 == null ? null : (Format) Format.CREATOR.fromBundle(bundle2);
        this.rendererFormatSupport = bundle.getInt(FIELD_RENDERER_FORMAT_SUPPORT, 4);
        this.isRecoverable = bundle.getBoolean(FIELD_IS_RECOVERABLE, false);
        this.mediaPeriodId = null;
    }

    public ExoPlaybackException(String str, @Nullable Throwable th, int i, int i2, @Nullable String str2, int i3, @Nullable Format format, int i4, @Nullable MediaPeriodId mediaPeriodId, long j, boolean z) {
        super(str, th, i, j);
        Assertions.checkArgument(!z || i2 == 1);
        Assertions.checkArgument(th != null || i2 == 3);
        this.type = i2;
        this.rendererName = str2;
        this.rendererIndex = i3;
        this.rendererFormat = format;
        this.rendererFormatSupport = i4;
        this.mediaPeriodId = mediaPeriodId;
        this.isRecoverable = z;
    }
}
