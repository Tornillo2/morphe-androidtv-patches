package com.google.android.exoplayer2.util;

import androidx.annotation.GuardedBy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TimestampAdjuster {
    public static final long MAX_PTS_PLUS_ONE = 8589934592L;
    public static final long MODE_NO_OFFSET = Long.MAX_VALUE;
    public static final long MODE_SHARED = 9223372036854775806L;

    @GuardedBy("this")
    public long firstSampleTimestampUs;

    @GuardedBy("this")
    public long lastUnadjustedTimestampUs;
    public final ThreadLocal<Long> nextSampleTimestampUs = new ThreadLocal<>();

    @GuardedBy("this")
    public long timestampOffsetUs;

    public TimestampAdjuster(long j) {
        reset(j);
    }

    public static long ptsToUs(long j) {
        return (j * 1000000) / 90000;
    }

    public static long usToNonWrappedPts(long j) {
        return (j * 90000) / 1000000;
    }

    public static long usToWrappedPts(long j) {
        return usToNonWrappedPts(j) % 8589934592L;
    }

    public synchronized long adjustSampleTimestamp(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        try {
            if (this.timestampOffsetUs == -9223372036854775807L) {
                long jLongValue = this.firstSampleTimestampUs;
                if (jLongValue == 9223372036854775806L) {
                    Long l = this.nextSampleTimestampUs.get();
                    l.getClass();
                    jLongValue = l.longValue();
                }
                this.timestampOffsetUs = jLongValue - j;
                notifyAll();
            }
            this.lastUnadjustedTimestampUs = j;
            return j + this.timestampOffsetUs;
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized long adjustTsTimestamp(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        try {
            long j2 = this.lastUnadjustedTimestampUs;
            if (j2 != -9223372036854775807L) {
                long jUsToNonWrappedPts = usToNonWrappedPts(j2);
                long j3 = (4294967296L + jUsToNonWrappedPts) / 8589934592L;
                long j4 = ((j3 - 1) * 8589934592L) + j;
                long j5 = (j3 * 8589934592L) + j;
                j = Math.abs(j4 - jUsToNonWrappedPts) < Math.abs(j5 - jUsToNonWrappedPts) ? j4 : j5;
            }
            return adjustSampleTimestamp(ptsToUs(j));
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized long getFirstSampleTimestampUs() {
        long j;
        j = this.firstSampleTimestampUs;
        if (j == Long.MAX_VALUE || j == 9223372036854775806L) {
            j = -9223372036854775807L;
        }
        return j;
    }

    public synchronized long getLastAdjustedTimestampUs() {
        long j;
        try {
            j = this.lastUnadjustedTimestampUs;
        } catch (Throwable th) {
            throw th;
        }
        return j != -9223372036854775807L ? j + this.timestampOffsetUs : getFirstSampleTimestampUs();
    }

    public synchronized long getTimestampOffsetUs() {
        return this.timestampOffsetUs;
    }

    public synchronized void reset(long j) {
        this.firstSampleTimestampUs = j;
        this.timestampOffsetUs = j == Long.MAX_VALUE ? 0L : -9223372036854775807L;
        this.lastUnadjustedTimestampUs = -9223372036854775807L;
    }

    public synchronized void sharedInitializeOrWait(boolean z, long j) throws InterruptedException {
        try {
            Assertions.checkState(this.firstSampleTimestampUs == 9223372036854775806L);
            if (this.timestampOffsetUs != -9223372036854775807L) {
                return;
            }
            if (z) {
                this.nextSampleTimestampUs.set(Long.valueOf(j));
            } else {
                while (this.timestampOffsetUs == -9223372036854775807L) {
                    wait();
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
