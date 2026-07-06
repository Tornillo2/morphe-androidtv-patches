package com.google.android.exoplayer2.util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FrameProcessingException extends Exception {
    public final long presentationTimeUs;

    public FrameProcessingException(String str) {
        this(str, -9223372036854775807L);
    }

    public static FrameProcessingException from(Exception exc) {
        return from(exc, -9223372036854775807L);
    }

    public FrameProcessingException(String str, long j) {
        super(str);
        this.presentationTimeUs = j;
    }

    public static FrameProcessingException from(Exception exc, long j) {
        return exc instanceof FrameProcessingException ? (FrameProcessingException) exc : new FrameProcessingException(exc, j);
    }

    public FrameProcessingException(String str, Throwable th) {
        this(str, th, -9223372036854775807L);
    }

    public FrameProcessingException(String str, Throwable th, long j) {
        super(str, th);
        this.presentationTimeUs = j;
    }

    public FrameProcessingException(Throwable th) {
        this(th, -9223372036854775807L);
    }

    public FrameProcessingException(Throwable th, long j) {
        super(th);
        this.presentationTimeUs = j;
    }
}
