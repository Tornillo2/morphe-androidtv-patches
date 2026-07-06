package com.amazonaws.mobileconnectors.remoteconfiguration.internal;

import android.os.SystemClock;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ArcusThrottler {
    public static final int CAUSE_FAILED_SYNC = 20;
    public static final int CAUSE_NONE = 0;
    public static final int CAUSE_SUCCESSFUL_SYNC = 10;
    public static final int CAUSE_THROTTLED_SYNC = 30;
    public static final long DEFAULT_SYNC_JITTER_IN_MS = 5000;
    public static final long DEFAULT_TIME_BETWEEN_SYNCS_IN_MS = 900000;
    public static final long MAX_EXP_BACKOFF_WINDOW_SIZE_IN_MS = 900000;
    public static final long ONE_SECOND_MS = 1000;
    public int mCause = 0;
    public long mNextSyncAttemptTime;
    public int mSyncAttempts;

    public int getCause() {
        return this.mCause;
    }

    public long getCurrentWindowSizeInMS() {
        long j = 1000 << (this.mSyncAttempts + 1);
        if (j <= 0 || j > 900000) {
            return 900000L;
        }
        return j;
    }

    public long getJitterInMS() {
        return (long) (Math.random() * 5000.0d);
    }

    public long getRandomTimeInsideCurrentWindowInMS() {
        return (long) (Math.random() * getCurrentWindowSizeInMS());
    }

    public int getSyncAttempts() {
        return this.mSyncAttempts;
    }

    public long getTimeToNextSyncInMS() {
        return Math.max(0L, this.mNextSyncAttemptTime - SystemClock.elapsedRealtime());
    }

    public boolean isSyncAllowedRightNow() {
        return getTimeToNextSyncInMS() == 0;
    }

    public void updateSyncTimeAfterFailure() {
        if (getCurrentWindowSizeInMS() < 900000) {
            this.mSyncAttempts++;
        }
        this.mNextSyncAttemptTime = getRandomTimeInsideCurrentWindowInMS() + SystemClock.elapsedRealtime();
        this.mCause = 20;
    }

    public void updateSyncTimeAfterSuccess() {
        this.mSyncAttempts = 0;
        this.mNextSyncAttemptTime = SystemClock.elapsedRealtime() + 900000;
        this.mCause = 10;
    }

    public void updateSyncTimeAfterThrottle(long j) {
        if (j <= 0) {
            j = 900000;
        }
        this.mSyncAttempts = 0;
        this.mNextSyncAttemptTime = getJitterInMS() + Math.min(j, 900000L) + SystemClock.elapsedRealtime();
        this.mCause = 30;
    }
}
