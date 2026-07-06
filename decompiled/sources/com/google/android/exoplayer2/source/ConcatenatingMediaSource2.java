package com.google.android.exoplayer2.source;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MaskingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.IdentityHashMap;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ConcatenatingMediaSource2 extends CompositeMediaSource<Integer> {
    public static final int MSG_UPDATE_TIMELINE = 0;
    public final MediaItem mediaItem;
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

        @Override // com.google.android.exoplayer2.Timeline
        public final int getIndexOfPeriod(Object obj) {
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

        @Override // com.google.android.exoplayer2.Timeline
        public final Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i);
            this.timelines.get(childIndexByPeriodIndex).getPeriod(i - this.firstPeriodIndices.get(childIndexByPeriodIndex).intValue(), period, z);
            period.windowIndex = 0;
            period.positionInWindowUs = this.periodOffsetsInWindowUs.get(i).longValue();
            if (z) {
                Object obj = period.uid;
                obj.getClass();
                period.uid = ConcatenatingMediaSource2.getPeriodUid(childIndexByPeriodIndex, obj);
            }
            return period;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public final Timeline.Period getPeriodByUid(Object obj, Timeline.Period period) {
            int childIndex = ConcatenatingMediaSource2.getChildIndex(obj);
            Object obj2 = ((Pair) obj).second;
            Timeline timeline = this.timelines.get(childIndex);
            int indexOfPeriod = timeline.getIndexOfPeriod(obj2) + this.firstPeriodIndices.get(childIndex).intValue();
            timeline.getPeriodByUid(obj2, period);
            period.windowIndex = 0;
            period.positionInWindowUs = this.periodOffsetsInWindowUs.get(indexOfPeriod).longValue();
            period.uid = obj;
            return period;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return this.periodOffsetsInWindowUs.size();
        }

        @Override // com.google.android.exoplayer2.Timeline
        public final Object getUidOfPeriod(int i) {
            int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i);
            return ConcatenatingMediaSource2.getPeriodUid(childIndexByPeriodIndex, this.timelines.get(childIndexByPeriodIndex).getUidOfPeriod(i - this.firstPeriodIndices.get(childIndexByPeriodIndex).intValue()));
        }

        @Override // com.google.android.exoplayer2.Timeline
        public final Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            window.set(Timeline.Window.SINGLE_WINDOW_UID, this.mediaItem, this.manifest, -9223372036854775807L, -9223372036854775807L, -9223372036854775807L, this.isSeekable, this.isDynamic, null, this.defaultPositionUs, this.durationUs, 0, this.periodOffsetsInWindowUs.size() - 1, -this.periodOffsetsInWindowUs.get(0).longValue());
            return window;
        }

        @Override // com.google.android.exoplayer2.Timeline
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

        public MediaSourceHolder(MediaSource mediaSource, int i, long j) {
            this.mediaSource = new MaskingMediaSource(mediaSource, false);
            this.index = i;
            this.initialPlaceholderDurationUs = j;
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$bz7rkzcWEFyY52lUiU5I-6mPRSs, reason: not valid java name */
    public static /* synthetic */ boolean m471$r8$lambda$bz7rkzcWEFyY52lUiU5I6mPRSs(ConcatenatingMediaSource2 concatenatingMediaSource2, Message message) {
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

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(getChildIndex(mediaPeriodId.periodUid));
        MediaSource.MediaPeriodId mediaPeriodIdCopyWithWindowSequenceNumber = mediaPeriodId.copyWithPeriodUid(((Pair) mediaPeriodId.periodUid).second).copyWithWindowSequenceNumber(getChildWindowSequenceNumber(mediaPeriodId.windowSequenceNumber, this.mediaSourceHolders.size(), mediaSourceHolder.index));
        enableChildSource(Integer.valueOf(mediaSourceHolder.index));
        mediaSourceHolder.activeMediaPeriods++;
        MaskingMediaPeriod maskingMediaPeriodCreatePeriod = mediaSourceHolder.mediaSource.createPeriod(mediaPeriodIdCopyWithWindowSequenceNumber, allocator, j);
        this.mediaSourceByMediaPeriod.put(maskingMediaPeriodCreatePeriod, mediaSourceHolder);
        disableUnusedMediaSources();
        return maskingMediaPeriodCreatePeriod;
    }

    public final void disableUnusedMediaSources() {
        for (int i = 0; i < this.mediaSourceHolders.size(); i++) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i);
            if (mediaSourceHolder.activeMediaPeriods == 0) {
                disableChildSource(Integer.valueOf(mediaSourceHolder.index));
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.BaseMediaSource, com.google.android.exoplayer2.source.MediaSource
    @Nullable
    public Timeline getInitialTimeline() {
        return maybeCreateConcatenatedTimeline();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaItem getMediaItem() {
        return this.mediaItem;
    }

    /* JADX INFO: renamed from: getWindowIndexForChildWindowIndex, reason: avoid collision after fix types in other method */
    public int getWindowIndexForChildWindowIndex2(Integer num, int i) {
        return 0;
    }

    @Nullable
    public final ConcatenatedTimeline maybeCreateConcatenatedTimeline() {
        Timeline.Period period;
        ImmutableList.Builder builder;
        int i;
        Timeline.Window window = new Timeline.Window();
        Timeline.Period period2 = new Timeline.Period();
        ImmutableList.Builder builder2 = ImmutableList.builder();
        ImmutableList.Builder builder3 = ImmutableList.builder();
        ImmutableList.Builder builder4 = ImmutableList.builder();
        int i2 = 0;
        boolean z = true;
        Object obj = null;
        int periodCount = 0;
        long j = 0;
        boolean z2 = true;
        boolean z3 = false;
        long j2 = 0;
        long j3 = 0;
        boolean z4 = false;
        while (i2 < this.mediaSourceHolders.size()) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i2);
            MaskingMediaSource.MaskingTimeline maskingTimeline = mediaSourceHolder.mediaSource.timeline;
            Assertions.checkArgument(!maskingTimeline.isEmpty(), "Can't concatenate empty child Timeline.");
            builder2.add(maskingTimeline);
            builder3.add(Integer.valueOf(periodCount));
            periodCount += maskingTimeline.timeline.getPeriodCount();
            int i3 = 0;
            while (i3 < maskingTimeline.timeline.getWindowCount()) {
                maskingTimeline.getWindow(i3, window);
                if (!z4) {
                    obj = window.manifest;
                    z4 = true;
                }
                z = z && Util.areEqual(obj, window.manifest);
                int i4 = i3;
                long j4 = window.durationUs;
                if (j4 == -9223372036854775807L) {
                    j4 = mediaSourceHolder.initialPlaceholderDurationUs;
                    if (j4 == -9223372036854775807L) {
                        return null;
                    }
                }
                j2 += j4;
                if (mediaSourceHolder.index == 0 && i4 == 0) {
                    i = i2;
                    j3 = window.defaultPositionUs;
                    j = -window.positionInFirstPeriodUs;
                } else {
                    i = i2;
                    Assertions.checkArgument(window.positionInFirstPeriodUs == 0, "Can't concatenate windows. A window has a non-zero offset in a period.");
                }
                z2 &= window.isSeekable || window.isPlaceholder;
                z3 |= window.isDynamic;
                i3 = i4 + 1;
                i2 = i;
            }
            int i5 = i2;
            int periodCount2 = maskingTimeline.timeline.getPeriodCount();
            int i6 = 0;
            while (i6 < periodCount2) {
                builder4.add(Long.valueOf(j));
                maskingTimeline.getPeriod(i6, period2, false);
                long j5 = period2.durationUs;
                if (j5 == -9223372036854775807L) {
                    period = period2;
                    Assertions.checkArgument(periodCount2 == 1, "Can't concatenate multiple periods with unknown duration in one window.");
                    long j6 = window.durationUs;
                    if (j6 == -9223372036854775807L) {
                        j6 = mediaSourceHolder.initialPlaceholderDurationUs;
                    }
                    builder = builder2;
                    j5 = j6 + window.positionInFirstPeriodUs;
                } else {
                    period = period2;
                    builder = builder2;
                }
                j += j5;
                i6++;
                builder2 = builder;
                period2 = period;
            }
            i2 = i5 + 1;
        }
        return new ConcatenatedTimeline(this.mediaItem, builder2.build(), builder3.build(), builder4.build(), z2, z3, j2, j3, z ? obj : null);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    public void onChildSourceInfoRefreshed(Integer num, MediaSource mediaSource, Timeline timeline) {
        scheduleTimelineUpdate();
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.BaseMediaSource
    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        this.playbackThreadHandler = new Handler(new Handler.Callback() { // from class: com.google.android.exoplayer2.source.ConcatenatingMediaSource2$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                ConcatenatingMediaSource2.m471$r8$lambda$bz7rkzcWEFyY52lUiU5I6mPRSs(this.f$0, message);
                return true;
            }
        });
        for (int i = 0; i < this.mediaSourceHolders.size(); i++) {
            prepareChildSource(Integer.valueOf(i), this.mediaSourceHolders.get(i).mediaSource);
        }
        scheduleTimelineUpdate();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        MediaSourceHolder mediaSourceHolderRemove = this.mediaSourceByMediaPeriod.remove(mediaPeriod);
        mediaSourceHolderRemove.getClass();
        mediaSourceHolderRemove.mediaSource.releasePeriod(mediaPeriod);
        mediaSourceHolderRemove.activeMediaPeriods--;
        if (this.mediaSourceByMediaPeriod.isEmpty()) {
            return;
        }
        disableUnusedMediaSources();
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.BaseMediaSource
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

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    @Nullable
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Integer num, MediaSource.MediaPeriodId mediaPeriodId) {
        if (num.intValue() != ((int) (mediaPeriodId.windowSequenceNumber % ((long) this.mediaSourceHolders.size())))) {
            return null;
        }
        return mediaPeriodId.copyWithPeriodUid((Object) Pair.create(num, mediaPeriodId.periodUid)).copyWithWindowSequenceNumber(mediaPeriodId.windowSequenceNumber / ((long) this.mediaSourceHolders.size()));
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    public /* bridge */ /* synthetic */ int getWindowIndexForChildWindowIndex(Integer num, int i) {
        return 0;
    }

    /* JADX INFO: renamed from: onChildSourceInfoRefreshed, reason: avoid collision after fix types in other method */
    public void onChildSourceInfoRefreshed2(Integer num, MediaSource mediaSource, Timeline timeline) {
        scheduleTimelineUpdate();
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.BaseMediaSource
    public void enableInternal() {
    }
}
