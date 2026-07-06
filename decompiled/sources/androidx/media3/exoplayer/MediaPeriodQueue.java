package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.exoplayer.MediaPeriodHolder;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.collect.ImmutableList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaPeriodQueue {
    public static final long INITIAL_RENDERER_POSITION_OFFSET_US = 1000000000000L;
    public static final int MAXIMUM_BUFFER_AHEAD_PERIODS = 100;
    public final AnalyticsCollector analyticsCollector;
    public final HandlerWrapper analyticsCollectorHandler;
    public int length;

    @Nullable
    public MediaPeriodHolder loading;
    public final MediaPeriodHolder.Factory mediaPeriodHolderFactory;
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

    public MediaPeriodQueue(AnalyticsCollector analyticsCollector, HandlerWrapper handlerWrapper, MediaPeriodHolder.Factory factory) {
        this.analyticsCollector = analyticsCollector;
        this.analyticsCollectorHandler = handlerWrapper;
        this.mediaPeriodHolderFactory = factory;
    }

    public static boolean isSkippableAdPeriod(Timeline.Period period) {
        int i = period.adPlaybackState.adGroupCount;
        if (i != 0 && ((i != 1 || !period.isLivePostrollPlaceholder(0)) && period.isServerSideInsertedAdGroup(period.adPlaybackState.removedAdGroupCount))) {
            long contentResumeOffsetUs = 0;
            if (period.getAdGroupIndexForPositionUs(0L) == -1) {
                if (period.durationUs == 0) {
                    return true;
                }
                int i2 = i - (period.isLivePostrollPlaceholder(i + (-1)) ? 2 : 1);
                for (int i3 = 0; i3 <= i2; i3++) {
                    contentResumeOffsetUs += period.getContentResumeOffsetUs(i3);
                }
                if (period.durationUs <= contentResumeOffsetUs) {
                    return true;
                }
            }
        }
        return false;
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
        Assertions.checkStateNotNull(mediaPeriodHolder);
        this.reading = mediaPeriodHolder.next;
        notifyQueueUpdate();
        MediaPeriodHolder mediaPeriodHolder2 = this.reading;
        Assertions.checkStateNotNull(mediaPeriodHolder2);
        return mediaPeriodHolder2;
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

    public MediaPeriodHolder enqueueNextMediaPeriodHolder(MediaPeriodInfo mediaPeriodInfo) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        MediaPeriodHolder mediaPeriodHolderCreate = this.mediaPeriodHolderFactory.create(mediaPeriodInfo, mediaPeriodHolder == null ? 1000000000000L : (mediaPeriodHolder.rendererPositionOffsetUs + mediaPeriodHolder.info.durationUs) - mediaPeriodInfo.startPositionUs);
        MediaPeriodHolder mediaPeriodHolder2 = this.loading;
        if (mediaPeriodHolder2 != null) {
            mediaPeriodHolder2.setNext(mediaPeriodHolderCreate);
        } else {
            this.playing = mediaPeriodHolderCreate;
            this.reading = mediaPeriodHolderCreate;
        }
        this.oldFrontPeriodUid = null;
        this.loading = mediaPeriodHolderCreate;
        this.length++;
        notifyQueueUpdate();
        return mediaPeriodHolderCreate;
    }

    @Nullable
    public final MediaPeriodInfo getFirstMediaPeriodInfo(PlaybackInfo playbackInfo) {
        return getMediaPeriodInfo(playbackInfo.timeline, playbackInfo.periodId, playbackInfo.requestedContentPositionUs, playbackInfo.positionUs);
    }

    @Nullable
    public final MediaPeriodInfo getFirstMediaPeriodInfoOfNextPeriod(Timeline timeline, MediaPeriodHolder mediaPeriodHolder, long j) {
        Object obj;
        long j2;
        MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder.info;
        int nextPeriodIndex = timeline.getNextPeriodIndex(timeline.getIndexOfPeriod(mediaPeriodInfo.id.periodUid), this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
        if (nextPeriodIndex == -1) {
            return null;
        }
        int i = timeline.getPeriod(nextPeriodIndex, this.period, true).windowIndex;
        Object obj2 = this.period.uid;
        obj2.getClass();
        long j3 = mediaPeriodInfo.id.windowSequenceNumber;
        long j4 = 0;
        if (timeline.getWindow(i, this.window, 0L).firstPeriodIndex == nextPeriodIndex) {
            Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(this.window, this.period, i, -9223372036854775807L, Math.max(0L, j));
            if (periodPositionUs == null) {
                return null;
            }
            Object obj3 = periodPositionUs.first;
            long jLongValue = ((Long) periodPositionUs.second).longValue();
            MediaPeriodHolder mediaPeriodHolder2 = mediaPeriodHolder.next;
            if (mediaPeriodHolder2 == null || !mediaPeriodHolder2.uid.equals(obj3)) {
                j3 = this.nextWindowSequenceNumber;
                this.nextWindowSequenceNumber = 1 + j3;
            } else {
                j3 = mediaPeriodHolder2.info.id.windowSequenceNumber;
            }
            obj = obj3;
            j2 = jLongValue;
            j4 = -9223372036854775807L;
        } else {
            obj = obj2;
            j2 = 0;
        }
        MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAds = resolveMediaPeriodIdForAds(timeline, obj, j2, j3, this.window, this.period);
        if (j4 != -9223372036854775807L && mediaPeriodInfo.requestedContentPositionUs != -9223372036854775807L) {
            boolean zHasServerSideInsertedAds = hasServerSideInsertedAds(mediaPeriodInfo.id.periodUid, timeline);
            if (mediaPeriodIdResolveMediaPeriodIdForAds.isAd() && zHasServerSideInsertedAds) {
                j4 = mediaPeriodInfo.requestedContentPositionUs;
            } else if (zHasServerSideInsertedAds) {
                j2 = mediaPeriodInfo.requestedContentPositionUs;
            }
        }
        return getMediaPeriodInfo(timeline, mediaPeriodIdResolveMediaPeriodIdForAds, j4, j2);
    }

    @Nullable
    public final MediaPeriodInfo getFollowingMediaPeriodInfo(Timeline timeline, MediaPeriodHolder mediaPeriodHolder, long j) {
        MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder.info;
        long j2 = (mediaPeriodHolder.rendererPositionOffsetUs + mediaPeriodInfo.durationUs) - j;
        return mediaPeriodInfo.isLastInTimelinePeriod ? getFirstMediaPeriodInfoOfNextPeriod(timeline, mediaPeriodHolder, j2) : getFollowingMediaPeriodInfoOfCurrentPeriod(timeline, mediaPeriodHolder, j2);
    }

    @Nullable
    public final MediaPeriodInfo getFollowingMediaPeriodInfoOfCurrentPeriod(Timeline timeline, MediaPeriodHolder mediaPeriodHolder, long j) {
        MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder.info;
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodInfo.id;
        timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        if (!mediaPeriodId.isAd()) {
            int i = mediaPeriodId.nextAdGroupIndex;
            if (i != -1 && this.period.isLivePostrollPlaceholder(i)) {
                return getFirstMediaPeriodInfoOfNextPeriod(timeline, mediaPeriodHolder, j);
            }
            int firstAdIndexToPlay = this.period.getFirstAdIndexToPlay(mediaPeriodId.nextAdGroupIndex);
            boolean z = this.period.isServerSideInsertedAdGroup(mediaPeriodId.nextAdGroupIndex) && this.period.getAdState(mediaPeriodId.nextAdGroupIndex, firstAdIndexToPlay) == 3;
            if (firstAdIndexToPlay == this.period.getAdCountInAdGroup(mediaPeriodId.nextAdGroupIndex) || z) {
                return getMediaPeriodInfoForContent(timeline, mediaPeriodId.periodUid, getMinStartPositionAfterAdGroupUs(timeline, mediaPeriodId.periodUid, mediaPeriodId.nextAdGroupIndex), mediaPeriodInfo.durationUs, mediaPeriodId.windowSequenceNumber);
            }
            return getMediaPeriodInfoForAd(timeline, mediaPeriodId.periodUid, mediaPeriodId.nextAdGroupIndex, firstAdIndexToPlay, mediaPeriodInfo.durationUs, mediaPeriodId.windowSequenceNumber);
        }
        int i2 = mediaPeriodId.adGroupIndex;
        int adCountInAdGroup = this.period.getAdCountInAdGroup(i2);
        if (adCountInAdGroup == -1) {
            return null;
        }
        int nextAdIndexToPlay = this.period.getNextAdIndexToPlay(i2, mediaPeriodId.adIndexInAdGroup);
        if (nextAdIndexToPlay < adCountInAdGroup) {
            return getMediaPeriodInfoForAd(timeline, mediaPeriodId.periodUid, i2, nextAdIndexToPlay, mediaPeriodInfo.requestedContentPositionUs, mediaPeriodId.windowSequenceNumber);
        }
        long jLongValue = mediaPeriodInfo.requestedContentPositionUs;
        if (jLongValue == -9223372036854775807L) {
            Timeline.Window window = this.window;
            Timeline.Period period = this.period;
            Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(window, period, period.windowIndex, -9223372036854775807L, Math.max(0L, j));
            if (periodPositionUs == null) {
                return null;
            }
            jLongValue = ((Long) periodPositionUs.second).longValue();
        }
        return getMediaPeriodInfoForContent(timeline, mediaPeriodId.periodUid, Math.max(getMinStartPositionAfterAdGroupUs(timeline, mediaPeriodId.periodUid, mediaPeriodId.adGroupIndex), jLongValue), mediaPeriodInfo.requestedContentPositionUs, mediaPeriodId.windowSequenceNumber);
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

    /* JADX WARN: Removed duplicated region for block: B:44:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.media3.exoplayer.MediaPeriodInfo getMediaPeriodInfoForContent(androidx.media3.common.Timeline r26, java.lang.Object r27, long r28, long r30, long r32) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.MediaPeriodQueue.getMediaPeriodInfoForContent(androidx.media3.common.Timeline, java.lang.Object, long, long, long):androidx.media3.exoplayer.MediaPeriodInfo");
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
    public androidx.media3.exoplayer.MediaPeriodInfo getUpdatedMediaPeriodInfo(androidx.media3.common.Timeline r16, androidx.media3.exoplayer.MediaPeriodInfo r17) {
        /*
            r15 = this;
            r1 = r16
            r2 = r17
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r3 = r2.id
            boolean r12 = r15.isLastInPeriod(r3)
            boolean r13 = r15.isLastInWindow(r1, r3)
            boolean r14 = r15.isLastInTimeline(r1, r3, r12)
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r4 = r2.id
            java.lang.Object r4 = r4.periodUid
            androidx.media3.common.Timeline$Period r5 = r15.period
            r1.getPeriodByUid(r4, r5)
            boolean r1 = r3.isAd()
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6 = -1
            if (r1 != 0) goto L33
            int r1 = r3.nextAdGroupIndex
            if (r1 != r6) goto L2c
            goto L33
        L2c:
            androidx.media3.common.Timeline$Period r7 = r15.period
            long r7 = r7.getAdGroupTimeUs(r1)
            goto L34
        L33:
            r7 = r4
        L34:
            boolean r1 = r3.isAd()
            if (r1 == 0) goto L46
            androidx.media3.common.Timeline$Period r1 = r15.period
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
            androidx.media3.common.Timeline$Period r1 = r15.period
            long r4 = r1.durationUs
            goto L44
        L58:
            boolean r1 = r3.isAd()
            if (r1 == 0) goto L68
            androidx.media3.common.Timeline$Period r1 = r15.period
            int r4 = r3.adGroupIndex
            boolean r1 = r1.isServerSideInsertedAdGroup(r4)
            r11 = r1
            goto L79
        L68:
            int r1 = r3.nextAdGroupIndex
            if (r1 == r6) goto L77
            androidx.media3.common.Timeline$Period r4 = r15.period
            boolean r1 = r4.isServerSideInsertedAdGroup(r1)
            if (r1 == 0) goto L77
            r1 = 1
            r11 = 1
            goto L79
        L77:
            r1 = 0
            r11 = 0
        L79:
            androidx.media3.exoplayer.MediaPeriodInfo r1 = new androidx.media3.exoplayer.MediaPeriodInfo
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.MediaPeriodQueue.getUpdatedMediaPeriodInfo(androidx.media3.common.Timeline, androidx.media3.exoplayer.MediaPeriodInfo):androidx.media3.exoplayer.MediaPeriodInfo");
    }

    public final boolean hasServerSideInsertedAds(Object obj, Timeline timeline) {
        int i = timeline.getPeriodByUid(obj, this.period).adPlaybackState.adGroupCount;
        Timeline.Period period = this.period;
        int i2 = period.adPlaybackState.removedAdGroupCount;
        if (i <= 0 || !period.isServerSideInsertedAdGroup(i2)) {
            return false;
        }
        return i > 1 || this.period.getAdGroupTimeUs(i2) != Long.MIN_VALUE;
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
        this.analyticsCollectorHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.MediaPeriodQueue$$ExternalSyntheticLambda0
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
        Assertions.checkStateNotNull(mediaPeriodHolder);
        boolean z = false;
        if (mediaPeriodHolder.equals(this.loading)) {
            return false;
        }
        this.loading = mediaPeriodHolder;
        while (true) {
            mediaPeriodHolder = mediaPeriodHolder.next;
            if (mediaPeriodHolder == null) {
                MediaPeriodHolder mediaPeriodHolder2 = this.loading;
                mediaPeriodHolder2.getClass();
                mediaPeriodHolder2.setNext(null);
                notifyQueueUpdate();
                return z;
            }
            mediaPeriodHolder.getClass();
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
                mediaPeriodHolder2.getClass();
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
        for (int indexOfPeriod = timeline.getIndexOfPeriod(obj); isSkippableAdPeriod(period) && indexOfPeriod <= window.lastPeriodIndex; indexOfPeriod++) {
            timeline.getPeriod(indexOfPeriod, period, true);
            obj = period.uid;
            obj.getClass();
        }
        timeline.getPeriodByUid(obj, period);
        int adGroupIndexForPositionUs = period.getAdGroupIndexForPositionUs(j);
        if (adGroupIndexForPositionUs == -1) {
            return new MediaSource.MediaPeriodId(obj, j2, period.getAdGroupIndexAfterPositionUs(j));
        }
        return new MediaSource.MediaPeriodId(obj, adGroupIndexForPositionUs, period.getFirstAdIndexToPlay(adGroupIndexForPositionUs), j2, -1);
    }
}
