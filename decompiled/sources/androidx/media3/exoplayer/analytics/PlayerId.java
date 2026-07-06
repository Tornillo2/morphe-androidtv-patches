package androidx.media3.exoplayer.analytics;

import android.media.metrics.LogSessionId;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class PlayerId {
    public static final PlayerId UNSET;

    @Nullable
    public final LogSessionIdApi31 logSessionIdApi31;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(31)
    public static final class LogSessionIdApi31 {
        public static final LogSessionIdApi31 UNSET = new LogSessionIdApi31(LogSessionId.LOG_SESSION_ID_NONE);
        public final LogSessionId logSessionId;

        public LogSessionIdApi31(LogSessionId logSessionId) {
            this.logSessionId = logSessionId;
        }
    }

    static {
        UNSET = Util.SDK_INT < 31 ? new PlayerId() : new PlayerId(LogSessionIdApi31.UNSET);
    }

    public PlayerId() {
        Assertions.checkState(Util.SDK_INT < 31);
        this.logSessionIdApi31 = null;
    }

    @RequiresApi(31)
    public LogSessionId getLogSessionId() {
        LogSessionIdApi31 logSessionIdApi31 = this.logSessionIdApi31;
        logSessionIdApi31.getClass();
        return logSessionIdApi31.logSessionId;
    }

    @RequiresApi(31)
    public PlayerId(LogSessionId logSessionId) {
        this(new LogSessionIdApi31(logSessionId));
    }

    public PlayerId(LogSessionIdApi31 logSessionIdApi31) {
        this.logSessionIdApi31 = logSessionIdApi31;
    }
}
