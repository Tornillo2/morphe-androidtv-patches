package androidx.media3.exoplayer.source;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.source.MaskingMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class ConcatenatingMediaSource2 extends CompositeMediaSource<Integer> {
    public static final int MSG_UPDATE_TIMELINE = 0;

    @GuardedBy("this")
    public MediaItem mediaItem;
    public final IdentityHashMap<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod;
    public final ImmutableList<MediaSourceHolder> mediaSourceHolders;

    @Nullable
    public Handler playbackThreadHandler;
    public boolean timelineUpdateScheduled;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public int index;

        @Nullable
        public MediaItem mediaItem;

        @Nullable
        public MediaSource.Factory mediaSourceFactory;
        public final ImmutableList.Builder<MediaSourceHolder> mediaSourceHoldersBuilder = ImmutableList.builder();

        @CanIgnoreReturnValue
        public Builder add(MediaItem mediaItem) {
            add(mediaItem, -9223372036854775807L);
            return this;
        }

        public ConcatenatingMediaSource2 build() {
            Assertions.checkArgument(this.index > 0, "Must add at least one source to the concatenation.");
            if (this.mediaItem == null) {
                this.mediaItem = MediaItem.fromUri(Uri.EMPTY);
            }
            return new ConcatenatingMediaSource2(this.mediaItem, this.mediaSourceHoldersBuilder.build());
        }

        @CanIgnoreReturnValue
        public Builder setMediaItem(MediaItem mediaItem) {
            this.mediaItem = mediaItem;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMediaSourceFactory(MediaSource.Factory factory) {
            factory.getClass();
            this.mediaSourceFactory = factory;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder useDefaultMediaSourceFactory(Context context) {
            this.mediaSourceFactory = new DefaultMediaSourceFactory(context);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder add(MediaItem mediaItem, long j) {
            mediaItem.getClass();
            if (j == -9223372036854775807L) {
                MediaItem.ClippingConfiguration clippingConfiguration = mediaItem.clippingConfiguration;
                if (clippingConfiguration.endPositionMs != Long.MIN_VALUE) {
                    j = Util.usToMs(clippingConfiguration.endPositionUs - clippingConfiguration.startPositionUs);
                }
            }
            Assertions.checkStateNotNull(this.mediaSourceFactory, "Must use useDefaultMediaSourceFactory or setMediaSourceFactory first.");
            add(this.mediaSourceFactory.createMediaSource(mediaItem), j);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder add(MediaSource mediaSource, long j) {
            mediaSource.getClass();
            Assertions.checkState(((mediaSource instanceof ProgressiveMediaSource) && j == -9223372036854775807L) ? false : true, "Progressive media source must define an initial placeholder duration.");
            ImmutableList.Builder<MediaSourceHolder> builder = this.mediaSourceHoldersBuilder;
            int i = this.index;
            this.index = i + 1;
            builder.add(new MediaSourceHolder(mediaSource, i, Util.msToUs(j)));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder add(MediaSource mediaSource) {
            add(mediaSource, -9223372036854775807L);
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ConcatenatedTimeline extends Timeline {
        public final long defaultPositionUs;
        public final long durationUs;
        public final ImmutableList<Integer> firstPeriodIndices;
        public final boolean isDynamic;
        public final boolean isSeekable;

        @Nullable
        public final Object manifest;
        public final MediaItem mediaItem;
        public final ImmutableList<Long> periodOffsetsInWindowUs;
        public final ImmutableList<Timeline> timelines;

        public ConcatenatedTimeline(MediaItem mediaItem, ImmutableList<Timeline> immutableList, ImmutableList<Integer> immutableList2, ImmutableList<Long> immutableList3, boolean z, boolean z2, long j, long j2, @Nullable Object obj) {
            this.mediaItem = mediaItem;
            this.timelines = immutableList;
            this.firstPeriodIndices = immutableList2;
            this.periodOffsetsInWindowUs = immutableList3;
            this.isSeekable = z;
            this.isDynamic = z2;
            this.durationUs = j;
            this.defaultPositionUs = j2;
            this.manifest = obj;
        }

        private int getChildIndexByPeriodIndex(int i) {
            return Util.binarySearchFloor((List<? extends Comparable<? super Integer>>) this.firstPeriodIndices, Integer.valueOf(i + 1), false, false);
        }

        @Override // androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object obj) {
            if (obj instanceof Pair) {
                Pair pair = (Pair) obj;
                if (pair.first instanceof Integer) {
                    int childIndex = ConcatenatingMediaSource2.getChildIndex(obj);
                    int indexOfPeriod = this.timelines.get(childIndex).getIndexOfPeriod(pair.second);
                    if (indexOfPeriod == -1) {
                        return -1;
                    }
                    return this.firstPeriodIndices.get(childIndex).intValue() + indexOfPeriod;
                }
            }
            return -1;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i);
            this.timelines.get(childIndexByPeriodIndex).getPeriod(i - this.firstPeriodIndices.get(childIndexByPeriodIndex).intValue(), period, z);
            period.windowIndex = 0;
            period.positionInWindowUs = this.periodOffsetsInWindowUs.get(i).longValue();
            period.durationUs = getPeriodDurationUs(period, i);
            if (z) {
                Object obj = period.uid;
                obj.getClass();
                period.uid = ConcatenatingMediaSource2.getPeriodUid(childIndexByPeriodIndex, obj);
            }
            return period;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriodByUid(Object obj, Timeline.Period period) {
            int childIndex = ConcatenatingMediaSource2.getChildIndex(obj);
            Object obj2 = ((Pair) obj).second;
            Timeline timeline = this.timelines.get(childIndex);
            int indexOfPeriod = timeline.getIndexOfPeriod(obj2) + this.firstPeriodIndices.get(childIndex).intValue();
            timeline.getPeriodByUid(obj2, period);
            period.windowIndex = 0;
            period.positionInWindowUs = this.periodOffsetsInWindowUs.get(indexOfPeriod).longValue();
            period.durationUs = getPeriodDurationUs(period, indexOfPeriod);
            period.uid = obj;
            return period;
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return this.periodOffsetsInWindowUs.size();
        }

        public final long getPeriodDurationUs(Timeline.Period period, int i) {
            if (period.durationUs == -9223372036854775807L) {
                return -9223372036854775807L;
            }
            return (i == this.periodOffsetsInWindowUs.size() + (-1) ? this.durationUs : this.periodOffsetsInWindowUs.get(i + 1).longValue()) - this.periodOffsetsInWindowUs.get(i).longValue();
        }

        @Override // androidx.media3.common.Timeline
        public Object getUidOfPeriod(int i) {
            int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i);
            return ConcatenatingMediaSource2.getPeriodUid(childIndexByPeriodIndex, this.timelines.get(childIndexByPeriodIndex).getUidOfPeriod(i - this.firstPeriodIndices.get(childIndexByPeriodIndex).intValue()));
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            window.set(Timeline.Window.SINGLE_WINDOW_UID, this.mediaItem, this.manifest, -9223372036854775807L, -9223372036854775807L, -9223372036854775807L, this.isSeekable, this.isDynamic, null, this.defaultPositionUs, this.durationUs, 0, this.periodOffsetsInWindowUs.size() - 1, -this.periodOffsetsInWindowUs.get(0).longValue());
            return window;
        }

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return 1;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MediaSourceHolder {
        public int activeMediaPeriods;
        public final int index;
        public final long initialPlaceholderDurationUs;
        public final MaskingMediaSource mediaSource;
        public final HashMap<Object, Long> periodTimeOffsetsByUid = new HashMap<>();

        public MediaSourceHolder(MediaSource mediaSource, int i, long j) {
            this.mediaSource = new MaskingMediaSource(mediaSource, false);
            this.index = i;
            this.initialPlaceholderDurationUs = j;
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$bXRn9-o-6gWY7wzcXHo5E0cUSmI, reason: not valid java name */
    public static /* synthetic */ boolean m150$r8$lambda$bXRn9o6gWY7wzcXHo5E0cUSmI(ConcatenatingMediaSource2 concatenatingMediaSource2, Message message) {
        concatenatingMediaSource2.handleMessage(message);
        return true;
    }

    public static int getChildIndex(Object obj) {
        return ((Integer) ((Pair) obj).first).intValue();
    }

    public static int getChildIndexFromChildWindowSequenceNumber(long j, int i) {
        return (int) (j % ((long) i));
    }

    public static Object getChildPeriodUid(Object obj) {
        return ((Pair) obj).second;
    }

    public static long getChildWindowSequenceNumber(long j, int i, int i2) {
        return (j * ((long) i)) + ((long) i2);
    }

    public static Object getPeriodUid(int i, Object obj) {
        return Pair.create(Integer.valueOf(i), obj);
    }

    public static long getWindowSequenceNumberFromChildWindowSequenceNumber(long j, int i) {
        return j / ((long) i);
    }

    private boolean handleMessage(Message message) {
        if (message.what != 0) {
            return true;
        }
        updateTimeline();
        return true;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem mediaItem) {
        return true;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        long jLongValue;
        MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(getChildIndex(mediaPeriodId.periodUid));
        MediaSource.MediaPeriodId mediaPeriodIdCopyWithWindowSequenceNumber = mediaPeriodId.copyWithPeriodUid(((Pair) mediaPeriodId.periodUid).second).copyWithWindowSequenceNumber(getChildWindowSequenceNumber(mediaPeriodId.windowSequenceNumber, this.mediaSourceHolders.size(), mediaSourceHolder.index));
        enableChildSource(Integer.valueOf(mediaSourceHolder.index));
        mediaSourceHolder.activeMediaPeriods++;
        if (mediaPeriodId.isAd()) {
            jLongValue = 0;
        } else {
            Long l = mediaSourceHolder.periodTimeOffsetsByUid.get(mediaPeriodIdCopyWithWindowSequenceNumber.periodUid);
            l.getClass();
            jLongValue = l.longValue();
        }
        TimeOffsetMediaPeriod timeOffsetMediaPeriod = new TimeOffsetMediaPeriod(mediaSourceHolder.mediaSource.createPeriod(mediaPeriodIdCopyWithWindowSequenceNumber, allocator, j - jLongValue), jLongValue);
        this.mediaSourceByMediaPeriod.put(timeOffsetMediaPeriod, mediaSourceHolder);
        disableUnusedMediaSources();
        return timeOffsetMediaPeriod;
    }

    public final void disableUnusedMediaSources() {
        for (int i = 0; i < this.mediaSourceHolders.size(); i++) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i);
            if (mediaSourceHolder.activeMediaPeriods == 0) {
                disableChildSource(Integer.valueOf(mediaSourceHolder.index));
            }
        }
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    @Nullable
    public Timeline getInitialTimeline() {
        return maybeCreateConcatenatedTimeline();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public synchronized MediaItem getMediaItem() {
        return this.mediaItem;
    }

    /* JADX INFO: renamed from: getWindowIndexForChildWindowIndex, reason: avoid collision after fix types in other method */
    public int getWindowIndexForChildWindowIndex2(Integer num, int i) {
        return 0;
    }

    @Nullable
    public final ConcatenatedTimeline maybeCreateConcatenatedTimeline() {
        ImmutableList.Builder builder;
        ImmutableList.Builder builder2;
        int i;
        long j;
        long j2;
        Timeline.Window window;
        boolean z;
        ConcatenatingMediaSource2 concatenatingMediaSource2 = this;
        Timeline.Window window2 = new Timeline.Window();
        Timeline.Period period = new Timeline.Period();
        ImmutableList.Builder builder3 = ImmutableList.builder();
        ImmutableList.Builder builder4 = ImmutableList.builder();
        ImmutableList.Builder builder5 = ImmutableList.builder();
        int size = concatenatingMediaSource2.mediaSourceHolders.size();
        boolean z2 = true;
        int i2 = 0;
        boolean z3 = true;
        Object obj = null;
        int periodCount = 0;
        boolean z4 = false;
        boolean z5 = true;
        boolean z6 = false;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        while (i2 < size) {
            MediaSourceHolder mediaSourceHolder = concatenatingMediaSource2.mediaSourceHolders.get(i2);
            MaskingMediaSource.MaskingTimeline maskingTimeline = mediaSourceHolder.mediaSource.timeline;
            Assertions.checkArgument(maskingTimeline.isEmpty() ^ z2, "Can't concatenate empty child Timeline.");
            builder3.add(maskingTimeline);
            builder4.add(Integer.valueOf(periodCount));
            periodCount += maskingTimeline.timeline.getPeriodCount();
            int i3 = 0;
            while (i3 < maskingTimeline.timeline.getWindowCount()) {
                maskingTimeline.getWindow(i3, window2);
                if (!z4) {
                    obj = window2.manifest;
                    z4 = true;
                }
                z3 = z3 && Util.areEqual(obj, window2.manifest);
                MaskingMediaSource.MaskingTimeline maskingTimeline2 = maskingTimeline;
                long j6 = window2.durationUs;
                if (j6 == -9223372036854775807L) {
                    j6 = mediaSourceHolder.initialPlaceholderDurationUs;
                    if (j6 == -9223372036854775807L) {
                        return null;
                    }
                }
                j3 += j6;
                if (mediaSourceHolder.index == 0 && i3 == 0) {
                    builder = builder3;
                    builder2 = builder4;
                    j4 = window2.defaultPositionUs;
                    j5 = -window2.positionInFirstPeriodUs;
                } else {
                    builder = builder3;
                    builder2 = builder4;
                }
                z5 &= window2.isSeekable || window2.isPlaceholder;
                z6 |= window2.isDynamic;
                int i4 = window2.firstPeriodIndex;
                while (i4 <= window2.lastPeriodIndex) {
                    builder5.add(Long.valueOf(j5));
                    MaskingMediaSource.MaskingTimeline maskingTimeline3 = maskingTimeline2;
                    maskingTimeline3.getPeriod(i4, period, true);
                    ImmutableList.Builder builder6 = builder5;
                    long j7 = period.durationUs;
                    if (j7 == -9223372036854775807L) {
                        Assertions.checkArgument(window2.firstPeriodIndex == window2.lastPeriodIndex, "Can't apply placeholder duration to multiple periods with unknown duration in a single window.");
                        j7 = window2.positionInFirstPeriodUs + j6;
                    }
                    if (i4 != window2.firstPeriodIndex || ((mediaSourceHolder.index == 0 && i3 == 0) || j7 == -9223372036854775807L)) {
                        i = i4;
                        j = j7;
                        j2 = 0;
                    } else {
                        i = i4;
                        j2 = -window2.positionInFirstPeriodUs;
                        j = j7 + j2;
                    }
                    Object obj2 = period.uid;
                    obj2.getClass();
                    int i5 = i;
                    if (mediaSourceHolder.activeMediaPeriods == 0 || !mediaSourceHolder.periodTimeOffsetsByUid.containsKey(obj2)) {
                        window = window2;
                    } else {
                        window = window2;
                        if (!mediaSourceHolder.periodTimeOffsetsByUid.get(obj2).equals(Long.valueOf(j2))) {
                            z = false;
                        }
                        Assertions.checkArgument(z, "Can't handle windows with changing offset in first period.");
                        mediaSourceHolder.periodTimeOffsetsByUid.put(obj2, Long.valueOf(j2));
                        j5 += j;
                        i4 = i5 + 1;
                        builder5 = builder6;
                        maskingTimeline2 = maskingTimeline3;
                        window2 = window;
                    }
                    z = true;
                    Assertions.checkArgument(z, "Can't handle windows with changing offset in first period.");
                    mediaSourceHolder.periodTimeOffsetsByUid.put(obj2, Long.valueOf(j2));
                    j5 += j;
                    i4 = i5 + 1;
                    builder5 = builder6;
                    maskingTimeline2 = maskingTimeline3;
                    window2 = window;
                }
                i3++;
                builder3 = builder;
                builder4 = builder2;
                maskingTimeline = maskingTimeline2;
            }
            i2++;
            concatenatingMediaSource2 = this;
            z2 = true;
        }
        return new ConcatenatedTimeline(getMediaItem(), builder3.build(), builder4.build(), builder5.build(), z5, z6, j3, j4, z3 ? obj : null);
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public void onChildSourceInfoRefreshed(Integer num, MediaSource mediaSource, Timeline timeline) {
        scheduleTimelineUpdate();
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        this.playbackThreadHandler = new Handler(new Handler.Callback() { // from class: androidx.media3.exoplayer.source.ConcatenatingMediaSource2$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                ConcatenatingMediaSource2.m150$r8$lambda$bXRn9o6gWY7wzcXHo5E0cUSmI(this.f$0, message);
                return true;
            }
        });
        for (int i = 0; i < this.mediaSourceHolders.size(); i++) {
            prepareChildSource(Integer.valueOf(i), this.mediaSourceHolders.get(i).mediaSource);
        }
        scheduleTimelineUpdate();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        MediaSourceHolder mediaSourceHolderRemove = this.mediaSourceByMediaPeriod.remove(mediaPeriod);
        mediaSourceHolderRemove.getClass();
        mediaSourceHolderRemove.mediaSource.releasePeriod(((TimeOffsetMediaPeriod) mediaPeriod).mediaPeriod);
        mediaSourceHolderRemove.activeMediaPeriods--;
        if (this.mediaSourceByMediaPeriod.isEmpty()) {
            return;
        }
        disableUnusedMediaSources();
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        Handler handler = this.playbackThreadHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.playbackThreadHandler = null;
        }
        this.timelineUpdateScheduled = false;
    }

    public final void scheduleTimelineUpdate() {
        if (this.timelineUpdateScheduled) {
            return;
        }
        Handler handler = this.playbackThreadHandler;
        handler.getClass();
        handler.obtainMessage(0).sendToTarget();
        this.timelineUpdateScheduled = true;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public synchronized void updateMediaItem(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }

    public final void updateTimeline() {
        this.timelineUpdateScheduled = false;
        ConcatenatedTimeline concatenatedTimelineMaybeCreateConcatenatedTimeline = maybeCreateConcatenatedTimeline();
        if (concatenatedTimelineMaybeCreateConcatenatedTimeline != null) {
            refreshSourceInfo(concatenatedTimelineMaybeCreateConcatenatedTimeline);
        }
    }

    public ConcatenatingMediaSource2(MediaItem mediaItem, ImmutableList<MediaSourceHolder> immutableList) {
        this.mediaItem = mediaItem;
        this.mediaSourceHolders = immutableList;
        this.mediaSourceByMediaPeriod = new IdentityHashMap<>();
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    @Nullable
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Integer num, MediaSource.MediaPeriodId mediaPeriodId) {
        if (num.intValue() != ((int) (mediaPeriodId.windowSequenceNumber % ((long) this.mediaSourceHolders.size())))) {
            return null;
        }
        return mediaPeriodId.copyWithPeriodUid(Pair.create(num, mediaPeriodId.periodUid)).copyWithWindowSequenceNumber(mediaPeriodId.windowSequenceNumber / ((long) this.mediaSourceHolders.size()));
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public long getMediaTimeForChildMediaTime(Integer num, long j, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        Long l;
        return (j == -9223372036854775807L || mediaPeriodId == null || mediaPeriodId.isAd() || (l = this.mediaSourceHolders.get(num.intValue()).periodTimeOffsetsByUid.get(mediaPeriodId.periodUid)) == null) ? j : Util.usToMs(l.longValue()) + j;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public /* bridge */ /* synthetic */ int getWindowIndexForChildWindowIndex(Integer num, int i) {
        return 0;
    }

    /* JADX INFO: renamed from: onChildSourceInfoRefreshed, reason: avoid collision after fix types in other method */
    public void onChildSourceInfoRefreshed2(Integer num, MediaSource mediaSource, Timeline timeline) {
        scheduleTimelineUpdate();
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void enableInternal() {
    }
}
