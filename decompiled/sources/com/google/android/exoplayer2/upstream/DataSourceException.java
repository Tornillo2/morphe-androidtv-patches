package com.google.android.exoplayer2.upstream;

import androidx.annotation.Nullable;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class DataSourceException extends IOException {

    @Deprecated
    public static final int POSITION_OUT_OF_RANGE = 2008;
    public final int reason;

    public DataSourceException(int i) {
        this.reason = i;
    }

    public static boolean isCausedByPositionOutOfRange(IOException iOException) {
        for (Throwable cause = iOException; cause != null; cause = cause.getCause()) {
            if ((cause instanceof DataSourceException) && ((DataSourceException) cause).reason == 2008) {
                return true;
            }
        }
        return false;
    }

    public DataSourceException(@Nullable Throwable th, int i) {
        super(th);
        this.reason = i;
    }

    public DataSourceException(@Nullable String str, int i) {
        super(str);
        this.reason = i;
    }

    public DataSourceException(@Nullable String str, @Nullable Throwable th, int i) {
        super(str, th);
        this.reason = i;
    }
}
