package com.google.android.exoplayer2.analytics;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import j$.util.DesugarCollections;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class PlaybackStats {
    public static final PlaybackStats EMPTY = merge(new PlaybackStats[0]);
    public static final int PLAYBACK_STATE_ABANDONED = 15;
    public static final int PLAYBACK_STATE_BUFFERING = 6;
    public static final int PLAYBACK_STATE_COUNT = 16;
    public static final int PLAYBACK_STATE_ENDED = 11;
    public static final int PLAYBACK_STATE_FAILED = 13;
    public static final int PLAYBACK_STATE_INTERRUPTED_BY_AD = 14;
    public static final int PLAYBACK_STATE_JOINING_BACKGROUND = 1;
    public static final int PLAYBACK_STATE_JOINING_FOREGROUND = 2;
    public static final int PLAYBACK_STATE_NOT_STARTED = 0;
    public static final int PLAYBACK_STATE_PAUSED = 4;
    public static final int PLAYBACK_STATE_PAUSED_BUFFERING = 7;
    public static final int PLAYBACK_STATE_PLAYING = 3;
    public static final int PLAYBACK_STATE_SEEKING = 5;
    public static final int PLAYBACK_STATE_STOPPED = 12;
    public static final int PLAYBACK_STATE_SUPPRESSED = 9;
    public static final int PLAYBACK_STATE_SUPPRESSED_BUFFERING = 10;
    public final int abandonedBeforeReadyCount;
    public final int adPlaybackCount;
    public final List<EventTimeAndFormat> audioFormatHistory;
    public final int backgroundJoiningCount;
    public final int endedCount;
    public final int fatalErrorCount;
    public final List<EventTimeAndException> fatalErrorHistory;
    public final int fatalErrorPlaybackCount;
    public final long firstReportedTimeMs;
    public final int foregroundPlaybackCount;
    public final int initialAudioFormatBitrateCount;
    public final int initialVideoFormatBitrateCount;
    public final int initialVideoFormatHeightCount;
    public final long maxRebufferTimeMs;
    public final List<long[]> mediaTimeHistory;
    public final int nonFatalErrorCount;
    public final List<EventTimeAndException> nonFatalErrorHistory;
    public final int playbackCount;
    public final long[] playbackStateDurationsMs;
    public final List<EventTimeAndPlaybackState> playbackStateHistory;
    public final long totalAudioFormatBitrateTimeProduct;
    public final long totalAudioFormatTimeMs;
    public final long totalAudioUnderruns;
    public final long totalBandwidthBytes;
    public final long totalBandwidthTimeMs;
    public final long totalDroppedFrames;
    public final long totalInitialAudioFormatBitrate;
    public final long totalInitialVideoFormatBitrate;
    public final int totalInitialVideoFormatHeight;
    public final int totalPauseBufferCount;
    public final int totalPauseCount;
    public final int totalRebufferCount;
    public final int totalSeekCount;
    public final long totalValidJoinTimeMs;
    public final long totalVideoFormatBitrateTimeMs;
    public final long totalVideoFormatBitrateTimeProduct;
    public final long totalVideoFormatHeightTimeMs;
    public final long totalVideoFormatHeightTimeProduct;
    public final int validJoinTimeCount;
    public final List<EventTimeAndFormat> videoFormatHistory;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class EventTimeAndException {
        public final AnalyticsListener.EventTime eventTime;
        public final Exception exception;

        public EventTimeAndException(AnalyticsListener.EventTime eventTime, Exception exc) {
            this.eventTime = eventTime;
            this.exception = exc;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || EventTimeAndException.class != obj.getClass()) {
                return false;
            }
            EventTimeAndException eventTimeAndException = (EventTimeAndException) obj;
            if (this.eventTime.equals(eventTimeAndException.eventTime)) {
                return this.exception.equals(eventTimeAndException.exception);
            }
            return false;
        }

        public int hashCode() {
            return this.exception.hashCode() + (this.eventTime.hashCode() * 31);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class EventTimeAndFormat {
        public final AnalyticsListener.EventTime eventTime;

        @Nullable
        public final Format format;

        public EventTimeAndFormat(AnalyticsListener.EventTime eventTime, @Nullable Format format) {
            this.eventTime = eventTime;
            this.format = format;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && EventTimeAndFormat.class == obj.getClass()) {
                EventTimeAndFormat eventTimeAndFormat = (EventTimeAndFormat) obj;
                if (!this.eventTime.equals(eventTimeAndFormat.eventTime)) {
                    return false;
                }
                Format format = this.format;
                Format format2 = eventTimeAndFormat.format;
                if (format != null) {
                    return format.equals(format2);
                }
                if (format2 == null) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int iHashCode = this.eventTime.hashCode() * 31;
            Format format = this.format;
            return iHashCode + (format != null ? format.hashCode() : 0);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class EventTimeAndPlaybackState {
        public final AnalyticsListener.EventTime eventTime;
        public final int playbackState;

        public EventTimeAndPlaybackState(AnalyticsListener.EventTime eventTime, int i) {
            this.eventTime = eventTime;
            this.playbackState = i;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || EventTimeAndPlaybackState.class != obj.getClass()) {
                return false;
            }
            EventTimeAndPlaybackState eventTimeAndPlaybackState = (EventTimeAndPlaybackState) obj;
            if (this.playbackState != eventTimeAndPlaybackState.playbackState) {
                return false;
            }
            return this.eventTime.equals(eventTimeAndPlaybackState.eventTime);
        }

        public int hashCode() {
            return (this.eventTime.hashCode() * 31) + this.playbackState;
        }
    }

    public PlaybackStats(int i, long[] jArr, List<EventTimeAndPlaybackState> list, List<long[]> list2, long j, int i2, int i3, int i4, int i5, long j2, int i6, int i7, int i8, int i9, int i10, long j3, int i11, List<EventTimeAndFormat> list3, List<EventTimeAndFormat> list4, long j4, long j5, long j6, long j7, long j8, long j9, int i12, int i13, int i14, long j10, int i15, long j11, long j12, long j13, long j14, long j15, int i16, int i17, int i18, List<EventTimeAndException> list5, List<EventTimeAndException> list6) {
        this.playbackCount = i;
        this.playbackStateDurationsMs = jArr;
        this.playbackStateHistory = DesugarCollections.unmodifiableList(list);
        this.mediaTimeHistory = DesugarCollections.unmodifiableList(list2);
        this.firstReportedTimeMs = j;
        this.foregroundPlaybackCount = i2;
        this.abandonedBeforeReadyCount = i3;
        this.endedCount = i4;
        this.backgroundJoiningCount = i5;
        this.totalValidJoinTimeMs = j2;
        this.validJoinTimeCount = i6;
        this.totalPauseCount = i7;
        this.totalPauseBufferCount = i8;
        this.totalSeekCount = i9;
        this.totalRebufferCount = i10;
        this.maxRebufferTimeMs = j3;
        this.adPlaybackCount = i11;
        this.videoFormatHistory = DesugarCollections.unmodifiableList(list3);
        this.audioFormatHistory = DesugarCollections.unmodifiableList(list4);
        this.totalVideoFormatHeightTimeMs = j4;
        this.totalVideoFormatHeightTimeProduct = j5;
        this.totalVideoFormatBitrateTimeMs = j6;
        this.totalVideoFormatBitrateTimeProduct = j7;
        this.totalAudioFormatTimeMs = j8;
        this.totalAudioFormatBitrateTimeProduct = j9;
        this.initialVideoFormatHeightCount = i12;
        this.initialVideoFormatBitrateCount = i13;
        this.totalInitialVideoFormatHeight = i14;
        this.totalInitialVideoFormatBitrate = j10;
        this.initialAudioFormatBitrateCount = i15;
        this.totalInitialAudioFormatBitrate = j11;
        this.totalBandwidthTimeMs = j12;
        this.totalBandwidthBytes = j13;
        this.totalDroppedFrames = j14;
        this.totalAudioUnderruns = j15;
        this.fatalErrorPlaybackCount = i16;
        this.fatalErrorCount = i17;
        this.nonFatalErrorCount = i18;
        this.fatalErrorHistory = DesugarCollections.unmodifiableList(list5);
        this.nonFatalErrorHistory = DesugarCollections.unmodifiableList(list6);
    }

    public static PlaybackStats merge(PlaybackStats... playbackStatsArr) {
        int i;
        PlaybackStats[] playbackStatsArr2 = playbackStatsArr;
        long[] jArr = new long[16];
        int length = playbackStatsArr2.length;
        int i2 = 0;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        long j6 = 0;
        long j7 = 0;
        long j8 = 0;
        long j9 = 0;
        long j10 = 0;
        long j11 = -1;
        long j12 = -1;
        long jMax = -9223372036854775807L;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        long jMin = -9223372036854775807L;
        int i7 = 0;
        long j13 = -9223372036854775807L;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = -1;
        while (i3 < length) {
            PlaybackStats playbackStats = playbackStatsArr2[i3];
            i2 += playbackStats.playbackCount;
            for (int i21 = 0; i21 < 16; i21++) {
                jArr[i21] = jArr[i21] + playbackStats.playbackStateDurationsMs[i21];
            }
            if (jMin == -9223372036854775807L) {
                jMin = playbackStats.firstReportedTimeMs;
            } else {
                long j14 = playbackStats.firstReportedTimeMs;
                if (j14 != -9223372036854775807L) {
                    jMin = Math.min(jMin, j14);
                }
            }
            i4 += playbackStats.foregroundPlaybackCount;
            i5 += playbackStats.abandonedBeforeReadyCount;
            i6 += playbackStats.endedCount;
            i7 += playbackStats.backgroundJoiningCount;
            if (j13 == -9223372036854775807L) {
                j13 = playbackStats.totalValidJoinTimeMs;
            } else {
                long j15 = playbackStats.totalValidJoinTimeMs;
                if (j15 != -9223372036854775807L) {
                    j13 += j15;
                }
            }
            i8 += playbackStats.validJoinTimeCount;
            i9 += playbackStats.totalPauseCount;
            i10 += playbackStats.totalPauseBufferCount;
            i11 += playbackStats.totalSeekCount;
            i12 += playbackStats.totalRebufferCount;
            if (jMax == -9223372036854775807L) {
                jMax = playbackStats.maxRebufferTimeMs;
            } else {
                long j16 = playbackStats.maxRebufferTimeMs;
                if (j16 != -9223372036854775807L) {
                    jMax = Math.max(jMax, j16);
                }
            }
            i13 += playbackStats.adPlaybackCount;
            j += playbackStats.totalVideoFormatHeightTimeMs;
            j2 += playbackStats.totalVideoFormatHeightTimeProduct;
            j3 += playbackStats.totalVideoFormatBitrateTimeMs;
            j4 += playbackStats.totalVideoFormatBitrateTimeProduct;
            j5 += playbackStats.totalAudioFormatTimeMs;
            j6 += playbackStats.totalAudioFormatBitrateTimeProduct;
            i14 += playbackStats.initialVideoFormatHeightCount;
            i15 += playbackStats.initialVideoFormatBitrateCount;
            int i22 = i20;
            if (i22 == -1) {
                i20 = playbackStats.totalInitialVideoFormatHeight;
            } else {
                i20 = i22;
                int i23 = playbackStats.totalInitialVideoFormatHeight;
                if (i23 != -1) {
                    i20 += i23;
                }
            }
            if (j11 == -1) {
                i = length;
                j11 = playbackStats.totalInitialVideoFormatBitrate;
            } else {
                i = length;
                long j17 = playbackStats.totalInitialVideoFormatBitrate;
                if (j17 != -1) {
                    j11 += j17;
                }
            }
            i16 += playbackStats.initialAudioFormatBitrateCount;
            if (j12 == -1) {
                j12 = playbackStats.totalInitialAudioFormatBitrate;
            } else {
                long j18 = playbackStats.totalInitialAudioFormatBitrate;
                if (j18 != -1) {
                    j12 += j18;
                }
            }
            j7 += playbackStats.totalBandwidthTimeMs;
            j8 += playbackStats.totalBandwidthBytes;
            j9 += playbackStats.totalDroppedFrames;
            j10 += playbackStats.totalAudioUnderruns;
            i17 += playbackStats.fatalErrorPlaybackCount;
            i18 += playbackStats.fatalErrorCount;
            i19 += playbackStats.nonFatalErrorCount;
            i3++;
            length = i;
            playbackStatsArr2 = playbackStatsArr;
        }
        long j19 = jMin;
        long j20 = jMax;
        List list = Collections.EMPTY_LIST;
        return new PlaybackStats(i2, jArr, list, list, j19, i4, i5, i6, i7, j13, i8, i9, i10, i11, i12, j20, i13, list, list, j, j2, j3, j4, j5, j6, i14, i15, i20, j11, i16, j12, j7, j8, j9, j10, i17, i18, i19, list, list);
    }

    public float getAbandonedBeforeReadyRatio() {
        int i = this.abandonedBeforeReadyCount;
        int i2 = this.playbackCount;
        int i3 = this.foregroundPlaybackCount;
        int i4 = i - (i2 - i3);
        if (i3 == 0) {
            return 0.0f;
        }
        return i4 / i3;
    }

    public float getAudioUnderrunRate() {
        long j = this.playbackStateDurationsMs[3];
        if (j == 0) {
            return 0.0f;
        }
        return (this.totalAudioUnderruns * 1000.0f) / j;
    }

    public float getDroppedFramesRate() {
        long j = this.playbackStateDurationsMs[3];
        if (j == 0) {
            return 0.0f;
        }
        return (this.totalDroppedFrames * 1000.0f) / j;
    }

    public float getEndedRatio() {
        int i = this.foregroundPlaybackCount;
        if (i == 0) {
            return 0.0f;
        }
        return this.endedCount / i;
    }

    public float getFatalErrorRate() {
        long j = this.playbackStateDurationsMs[3];
        if (j == 0) {
            return 0.0f;
        }
        return (this.fatalErrorCount * 1000.0f) / j;
    }

    public float getFatalErrorRatio() {
        int i = this.foregroundPlaybackCount;
        if (i == 0) {
            return 0.0f;
        }
        return this.fatalErrorPlaybackCount / i;
    }

    public float getJoinTimeRatio() {
        long totalPlayAndWaitTimeMs = getTotalPlayAndWaitTimeMs();
        if (totalPlayAndWaitTimeMs == 0) {
            return 0.0f;
        }
        return this.playbackStateDurationsMs[2] / totalPlayAndWaitTimeMs;
    }

    public int getMeanAudioFormatBitrate() {
        long j = this.totalAudioFormatTimeMs;
        if (j == 0) {
            return -1;
        }
        return (int) (this.totalAudioFormatBitrateTimeProduct / j);
    }

    public int getMeanBandwidth() {
        long j = this.totalBandwidthTimeMs;
        if (j == 0) {
            return -1;
        }
        return (int) ((this.totalBandwidthBytes * 8000) / j);
    }

    public long getMeanElapsedTimeMs() {
        if (this.playbackCount == 0) {
            return -9223372036854775807L;
        }
        return getTotalElapsedTimeMs() / ((long) this.playbackCount);
    }

    public int getMeanInitialAudioFormatBitrate() {
        int i = this.initialAudioFormatBitrateCount;
        if (i == 0) {
            return -1;
        }
        return (int) (this.totalInitialAudioFormatBitrate / ((long) i));
    }

    public int getMeanInitialVideoFormatBitrate() {
        int i = this.initialVideoFormatBitrateCount;
        if (i == 0) {
            return -1;
        }
        return (int) (this.totalInitialVideoFormatBitrate / ((long) i));
    }

    public int getMeanInitialVideoFormatHeight() {
        int i = this.initialVideoFormatHeightCount;
        if (i == 0) {
            return -1;
        }
        return this.totalInitialVideoFormatHeight / i;
    }

    public long getMeanJoinTimeMs() {
        int i = this.validJoinTimeCount;
        if (i == 0) {
            return -9223372036854775807L;
        }
        return this.totalValidJoinTimeMs / ((long) i);
    }

    public float getMeanNonFatalErrorCount() {
        int i = this.foregroundPlaybackCount;
        if (i == 0) {
            return 0.0f;
        }
        return this.nonFatalErrorCount / i;
    }

    public float getMeanPauseBufferCount() {
        int i = this.foregroundPlaybackCount;
        if (i == 0) {
            return 0.0f;
        }
        return this.totalPauseBufferCount / i;
    }

    public float getMeanPauseCount() {
        int i = this.foregroundPlaybackCount;
        if (i == 0) {
            return 0.0f;
        }
        return this.totalPauseCount / i;
    }

    public long getMeanPausedTimeMs() {
        if (this.foregroundPlaybackCount == 0) {
            return -9223372036854775807L;
        }
        return getTotalPausedTimeMs() / ((long) this.foregroundPlaybackCount);
    }

    public long getMeanPlayAndWaitTimeMs() {
        if (this.foregroundPlaybackCount == 0) {
            return -9223372036854775807L;
        }
        return getTotalPlayAndWaitTimeMs() / ((long) this.foregroundPlaybackCount);
    }

    public long getMeanPlayTimeMs() {
        int i = this.foregroundPlaybackCount;
        if (i == 0) {
            return -9223372036854775807L;
        }
        return this.playbackStateDurationsMs[3] / ((long) i);
    }

    public float getMeanRebufferCount() {
        int i = this.foregroundPlaybackCount;
        if (i == 0) {
            return 0.0f;
        }
        return this.totalRebufferCount / i;
    }

    public long getMeanRebufferTimeMs() {
        if (this.foregroundPlaybackCount == 0) {
            return -9223372036854775807L;
        }
        return getTotalRebufferTimeMs() / ((long) this.foregroundPlaybackCount);
    }

    public float getMeanSeekCount() {
        int i = this.foregroundPlaybackCount;
        if (i == 0) {
            return 0.0f;
        }
        return this.totalSeekCount / i;
    }

    public long getMeanSeekTimeMs() {
        int i = this.foregroundPlaybackCount;
        if (i == 0) {
            return -9223372036854775807L;
        }
        return this.playbackStateDurationsMs[5] / ((long) i);
    }

    public long getMeanSingleRebufferTimeMs() {
        int i = this.totalRebufferCount;
        if (i == 0) {
            return -9223372036854775807L;
        }
        long[] jArr = this.playbackStateDurationsMs;
        return (jArr[6] + jArr[7]) / ((long) i);
    }

    public long getMeanSingleSeekTimeMs() {
        int i = this.totalSeekCount;
        if (i == 0) {
            return -9223372036854775807L;
        }
        return this.playbackStateDurationsMs[5] / ((long) i);
    }

    public float getMeanTimeBetweenFatalErrors() {
        return 1.0f / getFatalErrorRate();
    }

    public float getMeanTimeBetweenNonFatalErrors() {
        return 1.0f / getNonFatalErrorRate();
    }

    public float getMeanTimeBetweenRebuffers() {
        return 1.0f / getRebufferRate();
    }

    public int getMeanVideoFormatBitrate() {
        long j = this.totalVideoFormatBitrateTimeMs;
        if (j == 0) {
            return -1;
        }
        return (int) (this.totalVideoFormatBitrateTimeProduct / j);
    }

    public int getMeanVideoFormatHeight() {
        long j = this.totalVideoFormatHeightTimeMs;
        if (j == 0) {
            return -1;
        }
        return (int) (this.totalVideoFormatHeightTimeProduct / j);
    }

    public long getMeanWaitTimeMs() {
        if (this.foregroundPlaybackCount == 0) {
            return -9223372036854775807L;
        }
        return getTotalWaitTimeMs() / ((long) this.foregroundPlaybackCount);
    }

    public long getMediaTimeMsAtRealtimeMs(long j) {
        if (this.mediaTimeHistory.isEmpty()) {
            return -9223372036854775807L;
        }
        int i = 0;
        while (i < this.mediaTimeHistory.size() && this.mediaTimeHistory.get(i)[0] <= j) {
            i++;
        }
        if (i == 0) {
            return this.mediaTimeHistory.get(0)[1];
        }
        if (i == this.mediaTimeHistory.size()) {
            List<long[]> list = this.mediaTimeHistory;
            return list.get(list.size() - 1)[1];
        }
        int i2 = i - 1;
        long j2 = this.mediaTimeHistory.get(i2)[0];
        long j3 = this.mediaTimeHistory.get(i2)[1];
        long j4 = this.mediaTimeHistory.get(i)[0];
        long j5 = this.mediaTimeHistory.get(i)[1];
        long j6 = j4 - j2;
        if (j6 == 0) {
            return j3;
        }
        return j3 + ((long) ((j5 - j3) * ((j - j2) / j6)));
    }

    public float getNonFatalErrorRate() {
        long j = this.playbackStateDurationsMs[3];
        if (j == 0) {
            return 0.0f;
        }
        return (this.nonFatalErrorCount * 1000.0f) / j;
    }

    public int getPlaybackStateAtTime(long j) {
        int i = 0;
        for (EventTimeAndPlaybackState eventTimeAndPlaybackState : this.playbackStateHistory) {
            if (eventTimeAndPlaybackState.eventTime.realtimeMs > j) {
                break;
            }
            i = eventTimeAndPlaybackState.playbackState;
        }
        return i;
    }

    public long getPlaybackStateDurationMs(int i) {
        return this.playbackStateDurationsMs[i];
    }

    public float getRebufferRate() {
        long j = this.playbackStateDurationsMs[3];
        if (j == 0) {
            return 0.0f;
        }
        return (this.totalRebufferCount * 1000.0f) / j;
    }

    public float getRebufferTimeRatio() {
        long totalPlayAndWaitTimeMs = getTotalPlayAndWaitTimeMs();
        if (totalPlayAndWaitTimeMs == 0) {
            return 0.0f;
        }
        return getTotalRebufferTimeMs() / totalPlayAndWaitTimeMs;
    }

    public float getSeekTimeRatio() {
        long totalPlayAndWaitTimeMs = getTotalPlayAndWaitTimeMs();
        if (totalPlayAndWaitTimeMs == 0) {
            return 0.0f;
        }
        return this.playbackStateDurationsMs[5] / totalPlayAndWaitTimeMs;
    }

    public long getTotalElapsedTimeMs() {
        long j = 0;
        for (int i = 0; i < 16; i++) {
            j += this.playbackStateDurationsMs[i];
        }
        return j;
    }

    public long getTotalJoinTimeMs() {
        return this.playbackStateDurationsMs[2];
    }

    public long getTotalPausedTimeMs() {
        long[] jArr = this.playbackStateDurationsMs;
        return jArr[4] + jArr[7];
    }

    public long getTotalPlayAndWaitTimeMs() {
        return getTotalWaitTimeMs() + this.playbackStateDurationsMs[3];
    }

    public long getTotalPlayTimeMs() {
        return this.playbackStateDurationsMs[3];
    }

    public long getTotalRebufferTimeMs() {
        return this.playbackStateDurationsMs[6];
    }

    public long getTotalSeekTimeMs() {
        return this.playbackStateDurationsMs[5];
    }

    public long getTotalWaitTimeMs() {
        long[] jArr = this.playbackStateDurationsMs;
        return jArr[2] + jArr[6] + jArr[5];
    }

    public float getWaitTimeRatio() {
        long totalPlayAndWaitTimeMs = getTotalPlayAndWaitTimeMs();
        if (totalPlayAndWaitTimeMs == 0) {
            return 0.0f;
        }
        return getTotalWaitTimeMs() / totalPlayAndWaitTimeMs;
    }
}
