package com.google.android.exoplayer2;

import android.util.Pair;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ads.AdPlaybackState;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.common.collect.ImmutableList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MediaPeriodQueue {
    public static final long INITIAL_RENDERER_POSITION_OFFSET_US = 1000000000000L;
    public static final int MAXIMUM_BUFFER_AHEAD_PERIODS = 100;
    public final AnalyticsCollector analyticsCollector;
    public final HandlerWrapper analyticsCollectorHandler;
    public int length;

    @Nullable
    public MediaPeriodHolder loading;
    public long nextWindowSequenceNumber;

    @Nullable
    public Object oldFrontPeriodUid;
    public long oldFrontPeriodWindowSequenceNumber;

    @Nullable
    public MediaPeriodHolder playing;

    @Nullable
    public MediaPeriodHolder reading;
    public int repeatMode;
    public boolean shuffleModeEnabled;
    public final Timeline.Period period = new Timeline.Period();
    public final Timeline.Window window = new Timeline.Window();

    public MediaPeriodQueue(AnalyticsCollector analyticsCollector, HandlerWrapper handlerWrapper) {
        this.analyticsCollector = analyticsCollector;
        this.analyticsCollectorHandler = handlerWrapper;
    }

    @Nullable
    public MediaPeriodHolder advancePlayingPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.playing;
        if (mediaPeriodHolder == null) {
            return null;
        }
        if (mediaPeriodHolder == this.reading) {
            this.reading = mediaPeriodHolder.next;
        }
        mediaPeriodHolder.release();
        int i = this.length - 1;
        this.length = i;
        if (i == 0) {
            this.loading = null;
            MediaPeriodHolder mediaPeriodHolder2 = this.playing;
            this.oldFrontPeriodUid = mediaPeriodHolder2.uid;
            this.oldFrontPeriodWindowSequenceNumber = mediaPeriodHolder2.info.id.windowSequenceNumber;
        }
        this.playing = this.playing.next;
        notifyQueueUpdate();
        return this.playing;
    }

    public MediaPeriodHolder advanceReadingPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.reading;
        Assertions.checkState((mediaPeriodHolder == null || mediaPeriodHolder.next == null) ? false : true);
        this.reading = this.reading.next;
        notifyQueueUpdate();
        return this.reading;
    }

    public final boolean areDurationsCompatible(long j, long j2) {
        return j == -9223372036854775807L || j == j2;
    }

    public final boolean canKeepMediaPeriodHolder(MediaPeriodInfo mediaPeriodInfo, MediaPeriodInfo mediaPeriodInfo2) {
        return mediaPeriodInfo.startPositionUs == mediaPeriodInfo2.startPositionUs && mediaPeriodInfo.id.equals(mediaPeriodInfo2.id);
    }

    public void clear() {
        if (this.length == 0) {
            return;
        }
        MediaPeriodHolder mediaPeriodHolder = this.playing;
        Assertions.checkStateNotNull(mediaPeriodHolder);
        this.oldFrontPeriodUid = mediaPeriodHolder.uid;
        this.oldFrontPeriodWindowSequenceNumber = mediaPeriodHolder.info.id.windowSequenceNumber;
        while (mediaPeriodHolder != null) {
            mediaPeriodHolder.release();
            mediaPeriodHolder = mediaPeriodHolder.next;
        }
        this.playing = null;
        this.loading = null;
        this.reading = null;
        this.length = 0;
        notifyQueueUpdate();
    }

    public MediaPeriodHolder enqueueNextMediaPeriodHolder(RendererCapabilities[] rendererCapabilitiesArr, TrackSelector trackSelector, Allocator allocator, MediaSourceList mediaSourceList, MediaPeriodInfo mediaPeriodInfo, TrackSelectorResult trackSelectorResult) {
        MediaPeriodInfo mediaPeriodInfo2;
        long j;
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        if (mediaPeriodHolder == null) {
            mediaPeriodInfo2 = mediaPeriodInfo;
            j = 1000000000000L;
        } else {
            mediaPeriodInfo2 = mediaPeriodInfo;
            j = (mediaPeriodHolder.rendererPositionOffsetUs + mediaPeriodHolder.info.durationUs) - mediaPeriodInfo2.startPositionUs;
        }
        MediaPeriodHolder mediaPeriodHolder2 = new MediaPeriodHolder(rendererCapabilitiesArr, j, trackSelector, allocator, mediaSourceList, mediaPeriodInfo2, trackSelectorResult);
        MediaPeriodHolder mediaPeriodHolder3 = this.loading;
        if (mediaPeriodHolder3 != null) {
            mediaPeriodHolder3.setNext(mediaPeriodHolder2);
        } else {
            this.playing = mediaPeriodHolder2;
            this.reading = mediaPeriodHolder2;
        }
        this.oldFrontPeriodUid = null;
        this.loading = mediaPeriodHolder2;
        this.length++;
        notifyQueueUpdate();
        return mediaPeriodHolder2;
    }

    @Nullable
    public final MediaPeriodInfo getFirstMediaPeriodInfo(PlaybackInfo playbackInfo) {
        return getMediaPeriodInfo(playbackInfo.timeline, playbackInfo.periodId, playbackInfo.requestedContentPositionUs, playbackInfo.positionUs);
    }

    @Nullable
    public final MediaPeriodInfo getFollowingMediaPeriodInfo(Timeline timeline, MediaPeriodHolder mediaPeriodHolder, long j) {
        Timeline timeline2;
        Object obj;
        long j2;
        MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder.info;
        long j3 = (mediaPeriodHolder.rendererPositionOffsetUs + mediaPeriodInfo.durationUs) - j;
        boolean z = false;
        if (mediaPeriodInfo.isLastInTimelinePeriod) {
            long j4 = 0;
            int nextPeriodIndex = timeline.getNextPeriodIndex(timeline.getIndexOfPeriod(mediaPeriodInfo.id.periodUid), this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            if (nextPeriodIndex != -1) {
                int i = timeline.getPeriod(nextPeriodIndex, this.period, true).windowIndex;
                Object obj2 = this.period.uid;
                obj2.getClass();
                long j5 = mediaPeriodInfo.id.windowSequenceNumber;
                if (timeline.getWindow(i, this.window, 0L).firstPeriodIndex == nextPeriodIndex) {
                    Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(this.window, this.period, i, -9223372036854775807L, Math.max(0L, j3));
                    if (periodPositionUs != null) {
                        Object obj3 = periodPositionUs.first;
                        long jLongValue = ((Long) periodPositionUs.second).longValue();
                        MediaPeriodHolder mediaPeriodHolder2 = mediaPeriodHolder.next;
                        if (mediaPeriodHolder2 == null || !mediaPeriodHolder2.uid.equals(obj3)) {
                            j5 = this.nextWindowSequenceNumber;
                            this.nextWindowSequenceNumber = 1 + j5;
                        } else {
                            j5 = mediaPeriodHolder2.info.id.windowSequenceNumber;
                        }
                        obj = obj3;
                        j2 = jLongValue;
                        j4 = -9223372036854775807L;
                    }
                } else {
                    obj = obj2;
                    j2 = 0;
                }
                MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAds = resolveMediaPeriodIdForAds(timeline, obj, j2, j5, this.window, this.period);
                if (j4 != -9223372036854775807L && mediaPeriodInfo.requestedContentPositionUs != -9223372036854775807L) {
                    if (timeline.getPeriodByUid(mediaPeriodInfo.id.periodUid, this.period).adPlaybackState.adGroupCount > 0) {
                        Timeline.Period period = this.period;
                        if (period.isServerSideInsertedAdGroup(period.adPlaybackState.removedAdGroupCount)) {
                            z = true;
                        }
                    }
                    if (mediaPeriodIdResolveMediaPeriodIdForAds.isAd() && z) {
                        j4 = mediaPeriodInfo.requestedContentPositionUs;
                    } else if (z) {
                        j2 = mediaPeriodInfo.requestedContentPositionUs;
                    }
                }
                return getMediaPeriodInfo(timeline, mediaPeriodIdResolveMediaPeriodIdForAds, j4, j2);
            }
        } else {
            MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodInfo.id;
            timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
            if (!mediaPeriodId.isAd()) {
                int firstAdIndexToPlay = this.period.getFirstAdIndexToPlay(mediaPeriodId.nextAdGroupIndex);
                if (this.period.isServerSideInsertedAdGroup(mediaPeriodId.nextAdGroupIndex) && this.period.getAdState(mediaPeriodId.nextAdGroupIndex, firstAdIndexToPlay) == 3) {
                    z = true;
                }
                if (firstAdIndexToPlay != this.period.getAdCountInAdGroup(mediaPeriodId.nextAdGroupIndex) && !z) {
                    return getMediaPeriodInfoForAd(timeline, mediaPeriodId.periodUid, mediaPeriodId.nextAdGroupIndex, firstAdIndexToPlay, mediaPeriodInfo.durationUs, mediaPeriodId.windowSequenceNumber);
                }
                return getMediaPeriodInfoForContent(timeline, mediaPeriodId.periodUid, getMinStartPositionAfterAdGroupUs(timeline, mediaPeriodId.periodUid, mediaPeriodId.nextAdGroupIndex), mediaPeriodInfo.durationUs, mediaPeriodId.windowSequenceNumber);
            }
            int i2 = mediaPeriodId.adGroupIndex;
            int adCountInAdGroup = this.period.getAdCountInAdGroup(i2);
            if (adCountInAdGroup != -1) {
                int nextAdIndexToPlay = this.period.getNextAdIndexToPlay(i2, mediaPeriodId.adIndexInAdGroup);
                if (nextAdIndexToPlay < adCountInAdGroup) {
                    return getMediaPeriodInfoForAd(timeline, mediaPeriodId.periodUid, i2, nextAdIndexToPlay, mediaPeriodInfo.requestedContentPositionUs, mediaPeriodId.windowSequenceNumber);
                }
                long jLongValue2 = mediaPeriodInfo.requestedContentPositionUs;
                if (jLongValue2 == -9223372036854775807L) {
                    Timeline.Window window = this.window;
                    Timeline.Period period2 = this.period;
                    Pair<Object, Long> periodPositionUs2 = timeline.getPeriodPositionUs(window, period2, period2.windowIndex, -9223372036854775807L, Math.max(0L, j3));
                    timeline2 = timeline;
                    if (periodPositionUs2 == null) {
                        return null;
                    }
                    jLongValue2 = ((Long) periodPositionUs2.second).longValue();
                } else {
                    timeline2 = timeline;
                }
                return getMediaPeriodInfoForContent(timeline, mediaPeriodId.periodUid, Math.max(getMinStartPositionAfterAdGroupUs(timeline2, mediaPeriodId.periodUid, mediaPeriodId.adGroupIndex), jLongValue2), mediaPeriodInfo.requestedContentPositionUs, mediaPeriodId.windowSequenceNumber);
            }
        }
        return null;
    }

    @Nullable
    public MediaPeriodHolder getLoadingPeriod() {
        return this.loading;
    }

    @Nullable
    public final MediaPeriodInfo getMediaPeriodInfo(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j, long j2) {
        timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        return mediaPeriodId.isAd() ? getMediaPeriodInfoForAd(timeline, mediaPeriodId.periodUid, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, j, mediaPeriodId.windowSequenceNumber) : getMediaPeriodInfoForContent(timeline, mediaPeriodId.periodUid, j2, j, mediaPeriodId.windowSequenceNumber);
    }

    public final MediaPeriodInfo getMediaPeriodInfoForAd(Timeline timeline, Object obj, int i, int i2, long j, long j2) {
        MediaSource.MediaPeriodId mediaPeriodId = new MediaSource.MediaPeriodId(obj, i, i2, j2, -1);
        long adDurationUs = timeline.getPeriodByUid(obj, this.period).getAdDurationUs(i, i2);
        long jMax = i2 == this.period.getFirstAdIndexToPlay(i) ? this.period.adPlaybackState.adResumePositionUs : 0L;
        boolean zIsServerSideInsertedAdGroup = this.period.isServerSideInsertedAdGroup(i);
        if (adDurationUs != -9223372036854775807L && jMax >= adDurationUs) {
            jMax = Math.max(0L, adDurationUs - 1);
        }
        return new MediaPeriodInfo(mediaPeriodId, jMax, j, -9223372036854775807L, adDurationUs, zIsServerSideInsertedAdGroup, false, false, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.exoplayer2.MediaPeriodInfo getMediaPeriodInfoForContent(com.google.android.exoplayer2.Timeline r25, java.lang.Object r26, long r27, long r29, long r31) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r26
            r3 = r27
            com.google.android.exoplayer2.Timeline$Period r5 = r0.period
            r1.getPeriodByUid(r2, r5)
            com.google.android.exoplayer2.Timeline$Period r5 = r0.period
            int r5 = r5.getAdGroupIndexAfterPositionUs(r3)
            r6 = 1
            r7 = 0
            r8 = -1
            if (r5 != r8) goto L29
            com.google.android.exoplayer2.Timeline$Period r9 = r0.period
            com.google.android.exoplayer2.source.ads.AdPlaybackState r10 = r9.adPlaybackState
            int r11 = r10.adGroupCount
            if (r11 <= 0) goto L48
            int r10 = r10.removedAdGroupCount
            boolean r9 = r9.isServerSideInsertedAdGroup(r10)
            if (r9 == 0) goto L48
            goto L46
        L29:
            com.google.android.exoplayer2.Timeline$Period r9 = r0.period
            boolean r9 = r9.isServerSideInsertedAdGroup(r5)
            if (r9 == 0) goto L48
            com.google.android.exoplayer2.Timeline$Period r9 = r0.period
            long r9 = r9.getAdGroupTimeUs(r5)
            com.google.android.exoplayer2.Timeline$Period r11 = r0.period
            long r12 = r11.durationUs
            int r14 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r14 != 0) goto L48
            boolean r9 = r11.hasPlayedAdGroup(r5)
            if (r9 == 0) goto L48
            r5 = -1
        L46:
            r9 = 1
            goto L49
        L48:
            r9 = 0
        L49:
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r11 = new com.google.android.exoplayer2.source.MediaSource$MediaPeriodId
            r12 = r31
            r11.<init>(r2, r12, r5)
            boolean r2 = r0.isLastInPeriod(r11)
            boolean r22 = r0.isLastInWindow(r1, r11)
            boolean r23 = r0.isLastInTimeline(r1, r11, r2)
            if (r5 == r8) goto L69
            com.google.android.exoplayer2.Timeline$Period r1 = r0.period
            boolean r1 = r1.isServerSideInsertedAdGroup(r5)
            if (r1 == 0) goto L69
            r20 = 1
            goto L6b
        L69:
            r20 = 0
        L6b:
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r5 == r8) goto L7b
            com.google.android.exoplayer2.Timeline$Period r1 = r0.period
            long r14 = r1.getAdGroupTimeUs(r5)
        L78:
            r16 = r14
            goto L84
        L7b:
            if (r9 == 0) goto L82
            com.google.android.exoplayer2.Timeline$Period r1 = r0.period
            long r14 = r1.durationUs
            goto L78
        L82:
            r16 = r12
        L84:
            int r1 = (r16 > r12 ? 1 : (r16 == r12 ? 0 : -1))
            if (r1 == 0) goto L92
            r14 = -9223372036854775808
            int r1 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1))
            if (r1 != 0) goto L8f
            goto L92
        L8f:
            r18 = r16
            goto L98
        L92:
            com.google.android.exoplayer2.Timeline$Period r1 = r0.period
            long r14 = r1.durationUs
            r18 = r14
        L98:
            int r1 = (r18 > r12 ? 1 : (r18 == r12 ? 0 : -1))
            if (r1 == 0) goto Laf
            int r1 = (r3 > r18 ? 1 : (r3 == r18 ? 0 : -1))
            if (r1 < 0) goto Laf
            if (r23 != 0) goto La6
            if (r9 != 0) goto La5
            goto La6
        La5:
            r6 = 0
        La6:
            long r3 = (long) r6
            long r3 = r18 - r3
            r5 = 0
            long r3 = java.lang.Math.max(r5, r3)
        Laf:
            r12 = r3
            com.google.android.exoplayer2.MediaPeriodInfo r10 = new com.google.android.exoplayer2.MediaPeriodInfo
            r14 = r29
            r21 = r2
            r10.<init>(r11, r12, r14, r16, r18, r20, r21, r22, r23)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.MediaPeriodQueue.getMediaPeriodInfoForContent(com.google.android.exoplayer2.Timeline, java.lang.Object, long, long, long):com.google.android.exoplayer2.MediaPeriodInfo");
    }

    public final long getMinStartPositionAfterAdGroupUs(Timeline timeline, Object obj, int i) {
        timeline.getPeriodByUid(obj, this.period);
        long adGroupTimeUs = this.period.getAdGroupTimeUs(i);
        return adGroupTimeUs == Long.MIN_VALUE ? this.period.durationUs : this.period.getContentResumeOffsetUs(i) + adGroupTimeUs;
    }

    @Nullable
    public MediaPeriodInfo getNextMediaPeriodInfo(long j, PlaybackInfo playbackInfo) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        return mediaPeriodHolder == null ? getFirstMediaPeriodInfo(playbackInfo) : getFollowingMediaPeriodInfo(playbackInfo.timeline, mediaPeriodHolder, j);
    }

    @Nullable
    public MediaPeriodHolder getPlayingPeriod() {
        return this.playing;
    }

    @Nullable
    public MediaPeriodHolder getReadingPeriod() {
        return this.reading;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.google.android.exoplayer2.MediaPeriodInfo getUpdatedMediaPeriodInfo(com.google.android.exoplayer2.Timeline r16, com.google.android.exoplayer2.MediaPeriodInfo r17) {
        /*
            r15 = this;
            r1 = r16
            r2 = r17
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r3 = r2.id
            boolean r12 = r15.isLastInPeriod(r3)
            boolean r13 = r15.isLastInWindow(r1, r3)
            boolean r14 = r15.isLastInTimeline(r1, r3, r12)
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r4 = r2.id
            java.lang.Object r4 = r4.periodUid
            com.google.android.exoplayer2.Timeline$Period r5 = r15.period
            r1.getPeriodByUid(r4, r5)
            boolean r1 = r3.isAd()
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6 = -1
            if (r1 != 0) goto L33
            int r1 = r3.nextAdGroupIndex
            if (r1 != r6) goto L2c
            goto L33
        L2c:
            com.google.android.exoplayer2.Timeline$Period r7 = r15.period
            long r7 = r7.getAdGroupTimeUs(r1)
            goto L34
        L33:
            r7 = r4
        L34:
            boolean r1 = r3.isAd()
            if (r1 == 0) goto L46
            com.google.android.exoplayer2.Timeline$Period r1 = r15.period
            int r4 = r3.adGroupIndex
            int r5 = r3.adIndexInAdGroup
            long r4 = r1.getAdDurationUs(r4, r5)
        L44:
            r9 = r4
            goto L58
        L46:
            int r1 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r1 == 0) goto L53
            r4 = -9223372036854775808
            int r1 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r1 != 0) goto L51
            goto L53
        L51:
            r9 = r7
            goto L58
        L53:
            com.google.android.exoplayer2.Timeline$Period r1 = r15.period
            long r4 = r1.durationUs
            goto L44
        L58:
            boolean r1 = r3.isAd()
            if (r1 == 0) goto L68
            com.google.android.exoplayer2.Timeline$Period r1 = r15.period
            int r4 = r3.adGroupIndex
            boolean r1 = r1.isServerSideInsertedAdGroup(r4)
            r11 = r1
            goto L79
        L68:
            int r1 = r3.nextAdGroupIndex
            if (r1 == r6) goto L77
            com.google.android.exoplayer2.Timeline$Period r4 = r15.period
            boolean r1 = r4.isServerSideInsertedAdGroup(r1)
            if (r1 == 0) goto L77
            r1 = 1
            r11 = 1
            goto L79
        L77:
            r1 = 0
            r11 = 0
        L79:
            com.google.android.exoplayer2.MediaPeriodInfo r1 = new com.google.android.exoplayer2.MediaPeriodInfo
            r5 = r3
            long r3 = r2.startPositionUs
            r16 = r1
            long r0 = r2.requestedContentPositionUs
            r2 = r5
            r5 = r0
            r1 = r16
            r1.<init>(r2, r3, r5, r7, r9, r11, r12, r13, r14)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.MediaPeriodQueue.getUpdatedMediaPeriodInfo(com.google.android.exoplayer2.Timeline, com.google.android.exoplayer2.MediaPeriodInfo):com.google.android.exoplayer2.MediaPeriodInfo");
    }

    public final boolean isLastInPeriod(MediaSource.MediaPeriodId mediaPeriodId) {
        return !mediaPeriodId.isAd() && mediaPeriodId.nextAdGroupIndex == -1;
    }

    public final boolean isLastInTimeline(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, boolean z) {
        int indexOfPeriod = timeline.getIndexOfPeriod(mediaPeriodId.periodUid);
        return !timeline.getWindow(timeline.getPeriod(indexOfPeriod, this.period, false).windowIndex, this.window, 0L).isDynamic && timeline.isLastPeriod(indexOfPeriod, this.period, this.window, this.repeatMode, this.shuffleModeEnabled) && z;
    }

    public final boolean isLastInWindow(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        if (isLastInPeriod(mediaPeriodId)) {
            return timeline.getWindow(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, this.window, 0L).lastPeriodIndex == timeline.getIndexOfPeriod(mediaPeriodId.periodUid);
        }
        return false;
    }

    public boolean isLoading(MediaPeriod mediaPeriod) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        return mediaPeriodHolder != null && mediaPeriodHolder.mediaPeriod == mediaPeriod;
    }

    public final void notifyQueueUpdate() {
        final ImmutableList.Builder builder = ImmutableList.builder();
        for (MediaPeriodHolder mediaPeriodHolder = this.playing; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.next) {
            builder.add(mediaPeriodHolder.info.id);
        }
        MediaPeriodHolder mediaPeriodHolder2 = this.reading;
        final MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodHolder2 == null ? null : mediaPeriodHolder2.info.id;
        this.analyticsCollectorHandler.post(new Runnable() { // from class: com.google.android.exoplayer2.MediaPeriodQueue$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.analyticsCollector.updateMediaPeriodQueueInfo(builder.build(), mediaPeriodId);
            }
        });
    }

    public void reevaluateBuffer(long j) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        if (mediaPeriodHolder != null) {
            mediaPeriodHolder.reevaluateBuffer(j);
        }
    }

    public boolean removeAfter(MediaPeriodHolder mediaPeriodHolder) {
        boolean z = false;
        Assertions.checkState(mediaPeriodHolder != null);
        if (mediaPeriodHolder.equals(this.loading)) {
            return false;
        }
        this.loading = mediaPeriodHolder;
        while (true) {
            mediaPeriodHolder = mediaPeriodHolder.next;
            if (mediaPeriodHolder == null) {
                this.loading.setNext(null);
                notifyQueueUpdate();
                return z;
            }
            if (mediaPeriodHolder == this.reading) {
                this.reading = this.playing;
                z = true;
            }
            mediaPeriodHolder.release();
            this.length--;
        }
    }

    public MediaSource.MediaPeriodId resolveMediaPeriodIdForAds(Timeline timeline, Object obj, long j) {
        return resolveMediaPeriodIdForAds(timeline, obj, j, resolvePeriodIndexToWindowSequenceNumber(timeline, obj), this.window, this.period);
    }

    public MediaSource.MediaPeriodId resolveMediaPeriodIdForAdsAfterPeriodPositionChange(Timeline timeline, Object obj, long j) {
        long jResolvePeriodIndexToWindowSequenceNumber = resolvePeriodIndexToWindowSequenceNumber(timeline, obj);
        timeline.getPeriodByUid(obj, this.period);
        timeline.getWindow(this.period.windowIndex, this.window);
        boolean z = false;
        for (int indexOfPeriod = timeline.getIndexOfPeriod(obj); indexOfPeriod >= this.window.firstPeriodIndex; indexOfPeriod--) {
            timeline.getPeriod(indexOfPeriod, this.period, true);
            Timeline.Period period = this.period;
            boolean z2 = period.adPlaybackState.adGroupCount > 0;
            z |= z2;
            if (period.getAdGroupIndexForPositionUs(period.durationUs) != -1) {
                obj = this.period.uid;
                obj.getClass();
            }
            if (z && (!z2 || this.period.durationUs != 0)) {
                break;
            }
        }
        return resolveMediaPeriodIdForAds(timeline, obj, j, jResolvePeriodIndexToWindowSequenceNumber, this.window, this.period);
    }

    public final long resolvePeriodIndexToWindowSequenceNumber(Timeline timeline, Object obj) {
        int indexOfPeriod;
        int i = timeline.getPeriodByUid(obj, this.period).windowIndex;
        Object obj2 = this.oldFrontPeriodUid;
        if (obj2 != null && (indexOfPeriod = timeline.getIndexOfPeriod(obj2)) != -1 && timeline.getPeriod(indexOfPeriod, this.period, false).windowIndex == i) {
            return this.oldFrontPeriodWindowSequenceNumber;
        }
        for (MediaPeriodHolder mediaPeriodHolder = this.playing; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.next) {
            if (mediaPeriodHolder.uid.equals(obj)) {
                return mediaPeriodHolder.info.id.windowSequenceNumber;
            }
        }
        for (MediaPeriodHolder mediaPeriodHolder2 = this.playing; mediaPeriodHolder2 != null; mediaPeriodHolder2 = mediaPeriodHolder2.next) {
            int indexOfPeriod2 = timeline.getIndexOfPeriod(mediaPeriodHolder2.uid);
            if (indexOfPeriod2 != -1 && timeline.getPeriod(indexOfPeriod2, this.period, false).windowIndex == i) {
                return mediaPeriodHolder2.info.id.windowSequenceNumber;
            }
        }
        long j = this.nextWindowSequenceNumber;
        this.nextWindowSequenceNumber = 1 + j;
        if (this.playing == null) {
            this.oldFrontPeriodUid = obj;
            this.oldFrontPeriodWindowSequenceNumber = j;
        }
        return j;
    }

    public boolean shouldLoadNextMediaPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        if (mediaPeriodHolder != null) {
            return !mediaPeriodHolder.info.isFinal && mediaPeriodHolder.isFullyBuffered() && this.loading.info.durationUs != -9223372036854775807L && this.length < 100;
        }
        return true;
    }

    public final boolean updateForPlaybackModeChange(Timeline timeline) {
        Timeline timeline2;
        MediaPeriodHolder mediaPeriodHolder;
        MediaPeriodHolder mediaPeriodHolder2 = this.playing;
        if (mediaPeriodHolder2 == null) {
            return true;
        }
        int indexOfPeriod = timeline.getIndexOfPeriod(mediaPeriodHolder2.uid);
        while (true) {
            timeline2 = timeline;
            indexOfPeriod = timeline2.getNextPeriodIndex(indexOfPeriod, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            while (true) {
                mediaPeriodHolder = mediaPeriodHolder2.next;
                if (mediaPeriodHolder == null || mediaPeriodHolder2.info.isLastInTimelinePeriod) {
                    break;
                }
                mediaPeriodHolder2 = mediaPeriodHolder;
            }
            if (indexOfPeriod == -1 || mediaPeriodHolder == null || timeline2.getIndexOfPeriod(mediaPeriodHolder.uid) != indexOfPeriod) {
                break;
            }
            mediaPeriodHolder2 = mediaPeriodHolder;
            timeline = timeline2;
        }
        boolean zRemoveAfter = removeAfter(mediaPeriodHolder2);
        mediaPeriodHolder2.info = getUpdatedMediaPeriodInfo(timeline2, mediaPeriodHolder2.info);
        return !zRemoveAfter;
    }

    public boolean updateQueuedPeriods(Timeline timeline, long j, long j2) {
        boolean zRemoveAfter;
        MediaPeriodInfo updatedMediaPeriodInfo;
        MediaPeriodHolder mediaPeriodHolder = this.playing;
        MediaPeriodHolder mediaPeriodHolder2 = null;
        while (mediaPeriodHolder != null) {
            MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder.info;
            if (mediaPeriodHolder2 != null) {
                MediaPeriodInfo followingMediaPeriodInfo = getFollowingMediaPeriodInfo(timeline, mediaPeriodHolder2, j);
                if (followingMediaPeriodInfo == null) {
                    zRemoveAfter = removeAfter(mediaPeriodHolder2);
                } else if (canKeepMediaPeriodHolder(mediaPeriodInfo, followingMediaPeriodInfo)) {
                    updatedMediaPeriodInfo = followingMediaPeriodInfo;
                } else {
                    zRemoveAfter = removeAfter(mediaPeriodHolder2);
                }
                return !zRemoveAfter;
            }
            updatedMediaPeriodInfo = getUpdatedMediaPeriodInfo(timeline, mediaPeriodInfo);
            mediaPeriodHolder.info = updatedMediaPeriodInfo.copyWithRequestedContentPositionUs(mediaPeriodInfo.requestedContentPositionUs);
            if (!areDurationsCompatible(mediaPeriodInfo.durationUs, updatedMediaPeriodInfo.durationUs)) {
                mediaPeriodHolder.updateClipping();
                long j3 = updatedMediaPeriodInfo.durationUs;
                return (removeAfter(mediaPeriodHolder) || (mediaPeriodHolder == this.reading && !mediaPeriodHolder.info.isFollowedByTransitionToSameStream && ((j2 > Long.MIN_VALUE ? 1 : (j2 == Long.MIN_VALUE ? 0 : -1)) == 0 || (j2 > ((j3 > (-9223372036854775807L) ? 1 : (j3 == (-9223372036854775807L) ? 0 : -1)) == 0 ? Long.MAX_VALUE : j3 + mediaPeriodHolder.rendererPositionOffsetUs) ? 1 : (j2 == ((j3 > (-9223372036854775807L) ? 1 : (j3 == (-9223372036854775807L) ? 0 : -1)) == 0 ? Long.MAX_VALUE : j3 + mediaPeriodHolder.rendererPositionOffsetUs) ? 0 : -1)) >= 0))) ? false : true;
            }
            mediaPeriodHolder2 = mediaPeriodHolder;
            mediaPeriodHolder = mediaPeriodHolder.next;
        }
        return true;
    }

    public boolean updateRepeatMode(Timeline timeline, int i) {
        this.repeatMode = i;
        return updateForPlaybackModeChange(timeline);
    }

    public boolean updateShuffleModeEnabled(Timeline timeline, boolean z) {
        this.shuffleModeEnabled = z;
        return updateForPlaybackModeChange(timeline);
    }

    public static MediaSource.MediaPeriodId resolveMediaPeriodIdForAds(Timeline timeline, Object obj, long j, long j2, Timeline.Window window, Timeline.Period period) {
        timeline.getPeriodByUid(obj, period);
        timeline.getWindow(period.windowIndex, window);
        int indexOfPeriod = timeline.getIndexOfPeriod(obj);
        while (period.durationUs == 0) {
            AdPlaybackState adPlaybackState = period.adPlaybackState;
            if (adPlaybackState.adGroupCount <= 0 || !period.isServerSideInsertedAdGroup(adPlaybackState.removedAdGroupCount) || period.getAdGroupIndexForPositionUs(0L) != -1) {
                break;
            }
            int i = indexOfPeriod + 1;
            if (indexOfPeriod >= window.lastPeriodIndex) {
                break;
            }
            timeline.getPeriod(i, period, true);
            obj = period.uid;
            obj.getClass();
            indexOfPeriod = i;
        }
        timeline.getPeriodByUid(obj, period);
        int adGroupIndexForPositionUs = period.getAdGroupIndexForPositionUs(j);
        if (adGroupIndexForPositionUs == -1) {
            return new MediaSource.MediaPeriodId(obj, j2, period.getAdGroupIndexAfterPositionUs(j));
        }
        return new MediaSource.MediaPeriodId(obj, adGroupIndexForPositionUs, period.getFirstAdIndexToPlay(adGroupIndexForPositionUs), j2, -1);
    }
}
