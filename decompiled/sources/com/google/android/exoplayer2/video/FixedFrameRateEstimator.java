package com.google.android.exoplayer2.video;

import androidx.annotation.VisibleForTesting;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FixedFrameRateEstimator {
    public static final int CONSECUTIVE_MATCHING_FRAME_DURATIONS_FOR_SYNC = 15;

    @VisibleForTesting
    public static final long MAX_MATCHING_FRAME_DIFFERENCE_NS = 1000000;
    public boolean candidateMatcherActive;
    public int framesWithoutSyncCount;
    public boolean switchToCandidateMatcherWhenSynced;
    public Matcher currentMatcher = new Matcher();
    public Matcher candidateMatcher = new Matcher();
    public long lastFramePresentationTimeNs = -9223372036854775807L;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Matcher {
        public long firstFrameDurationNs;
        public long firstFramePresentationTimeNs;
        public long frameCount;
        public long lastFramePresentationTimeNs;
        public long matchingFrameCount;
        public long matchingFrameDurationSumNs;
        public int recentFrameOutlierCount;
        public final boolean[] recentFrameOutlierFlags = new boolean[15];

        public static int getRecentFrameOutlierIndex(long j) {
            return (int) (j % 15);
        }

        public long getFrameDurationNs() {
            long j = this.matchingFrameCount;
            if (j == 0) {
                return 0L;
            }
            return this.matchingFrameDurationSumNs / j;
        }

        public long getMatchingFrameDurationSumNs() {
            return this.matchingFrameDurationSumNs;
        }

        public boolean isLastFrameOutlier() {
            long j = this.frameCount;
            if (j == 0) {
                return false;
            }
            return this.recentFrameOutlierFlags[(int) ((j - 1) % 15)];
        }

        public boolean isSynced() {
            return this.frameCount > 15 && this.recentFrameOutlierCount == 0;
        }

        public void onNextFrame(long j) {
            long j2 = this.frameCount;
            if (j2 == 0) {
                this.firstFramePresentationTimeNs = j;
            } else if (j2 == 1) {
                long j3 = j - this.firstFramePresentationTimeNs;
                this.firstFrameDurationNs = j3;
                this.matchingFrameDurationSumNs = j3;
                this.matchingFrameCount = 1L;
            } else {
                long j4 = j - this.lastFramePresentationTimeNs;
                int i = (int) (j2 % 15);
                if (Math.abs(j4 - this.firstFrameDurationNs) <= 1000000) {
                    this.matchingFrameCount++;
                    this.matchingFrameDurationSumNs += j4;
                    boolean[] zArr = this.recentFrameOutlierFlags;
                    if (zArr[i]) {
                        zArr[i] = false;
                        this.recentFrameOutlierCount--;
                    }
                } else {
                    boolean[] zArr2 = this.recentFrameOutlierFlags;
                    if (!zArr2[i]) {
                        zArr2[i] = true;
                        this.recentFrameOutlierCount++;
                    }
                }
            }
            this.frameCount++;
            this.lastFramePresentationTimeNs = j;
        }

        public void reset() {
            this.frameCount = 0L;
            this.matchingFrameCount = 0L;
            this.matchingFrameDurationSumNs = 0L;
            this.recentFrameOutlierCount = 0;
            Arrays.fill(this.recentFrameOutlierFlags, false);
        }
    }

    public long getFrameDurationNs() {
        if (this.currentMatcher.isSynced()) {
            return this.currentMatcher.getFrameDurationNs();
        }
        return -9223372036854775807L;
    }

    public float getFrameRate() {
        if (this.currentMatcher.isSynced()) {
            return (float) (1.0E9d / this.currentMatcher.getFrameDurationNs());
        }
        return -1.0f;
    }

    public int getFramesWithoutSyncCount() {
        return this.framesWithoutSyncCount;
    }

    public long getMatchingFrameDurationSumNs() {
        if (this.currentMatcher.isSynced()) {
            return this.currentMatcher.matchingFrameDurationSumNs;
        }
        return -9223372036854775807L;
    }

    public boolean isSynced() {
        return this.currentMatcher.isSynced();
    }

    public void onNextFrame(long j) {
        this.currentMatcher.onNextFrame(j);
        if (this.currentMatcher.isSynced() && !this.switchToCandidateMatcherWhenSynced) {
            this.candidateMatcherActive = false;
        } else if (this.lastFramePresentationTimeNs != -9223372036854775807L) {
            if (!this.candidateMatcherActive || this.candidateMatcher.isLastFrameOutlier()) {
                this.candidateMatcher.reset();
                this.candidateMatcher.onNextFrame(this.lastFramePresentationTimeNs);
            }
            this.candidateMatcherActive = true;
            this.candidateMatcher.onNextFrame(j);
        }
        if (this.candidateMatcherActive && this.candidateMatcher.isSynced()) {
            Matcher matcher = this.currentMatcher;
            this.currentMatcher = this.candidateMatcher;
            this.candidateMatcher = matcher;
            this.candidateMatcherActive = false;
            this.switchToCandidateMatcherWhenSynced = false;
        }
        this.lastFramePresentationTimeNs = j;
        this.framesWithoutSyncCount = this.currentMatcher.isSynced() ? 0 : this.framesWithoutSyncCount + 1;
    }

    public void reset() {
        this.currentMatcher.reset();
        this.candidateMatcher.reset();
        this.candidateMatcherActive = false;
        this.lastFramePresentationTimeNs = -9223372036854775807L;
        this.framesWithoutSyncCount = 0;
    }
}
