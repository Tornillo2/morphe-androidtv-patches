package com.google.android.exoplayer2.source;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ads.AdPlaybackState;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Util;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MaskingMediaSource extends WrappingMediaSource {
    public boolean hasRealTimeline;
    public boolean hasStartedPreparing;
    public boolean isPrepared;
    public final Timeline.Period period;
    public MaskingTimeline timeline;

    @Nullable
    public MaskingMediaPeriod unpreparedMaskingMediaPeriod;
    public final boolean useLazyPreparation;
    public final Timeline.Window window;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MaskingTimeline extends ForwardingTimeline {
        public static final Object MASKING_EXTERNAL_PERIOD_UID = new Object();

        @Nullable
        public final Object replacedInternalPeriodUid;

        @Nullable
        public final Object replacedInternalWindowUid;

        public MaskingTimeline(Timeline timeline, @Nullable Object obj, @Nullable Object obj2) {
            super(timeline);
            this.replacedInternalWindowUid = obj;
            this.replacedInternalPeriodUid = obj2;
        }

        public static MaskingTimeline createWithPlaceholderTimeline(MediaItem mediaItem) {
            return new MaskingTimeline(new PlaceholderTimeline(mediaItem), Timeline.Window.SINGLE_WINDOW_UID, MASKING_EXTERNAL_PERIOD_UID);
        }

        public static MaskingTimeline createWithRealTimeline(Timeline timeline, @Nullable Object obj, @Nullable Object obj2) {
            return new MaskingTimeline(timeline, obj, obj2);
        }

        public MaskingTimeline cloneWithUpdatedTimeline(Timeline timeline) {
            return new MaskingTimeline(timeline, this.replacedInternalWindowUid, this.replacedInternalPeriodUid);
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public int getIndexOfPeriod(Object obj) {
            Object obj2;
            Timeline timeline = this.timeline;
            if (MASKING_EXTERNAL_PERIOD_UID.equals(obj) && (obj2 = this.replacedInternalPeriodUid) != null) {
                obj = obj2;
            }
            return timeline.getIndexOfPeriod(obj);
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            this.timeline.getPeriod(i, period, z);
            if (Util.areEqual(period.uid, this.replacedInternalPeriodUid) && z) {
                period.uid = MASKING_EXTERNAL_PERIOD_UID;
            }
            return period;
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public Object getUidOfPeriod(int i) {
            Object uidOfPeriod = this.timeline.getUidOfPeriod(i);
            return Util.areEqual(uidOfPeriod, this.replacedInternalPeriodUid) ? MASKING_EXTERNAL_PERIOD_UID : uidOfPeriod;
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            this.timeline.getWindow(i, window, j);
            if (Util.areEqual(window.uid, this.replacedInternalWindowUid)) {
                window.uid = Timeline.Window.SINGLE_WINDOW_UID;
            }
            return window;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static final class PlaceholderTimeline extends Timeline {
        public final MediaItem mediaItem;

        public PlaceholderTimeline(MediaItem mediaItem) {
            this.mediaItem = mediaItem;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getIndexOfPeriod(Object obj) {
            return obj == MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID ? 0 : -1;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            period.set(z ? 0 : null, z ? MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID : null, 0, -9223372036854775807L, 0L, AdPlaybackState.NONE, true);
            return period;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return 1;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Object getUidOfPeriod(int i) {
            return MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            window.set(Timeline.Window.SINGLE_WINDOW_UID, this.mediaItem, null, -9223372036854775807L, -9223372036854775807L, -9223372036854775807L, false, true, null, 0L, -9223372036854775807L, 0, 0, 0L);
            window.isPlaceholder = true;
            return window;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getWindowCount() {
            return 1;
        }
    }

    public MaskingMediaSource(MediaSource mediaSource, boolean z) {
        super(mediaSource);
        this.useLazyPreparation = z && mediaSource.isSingleWindow();
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        Timeline initialTimeline = mediaSource.getInitialTimeline();
        if (initialTimeline == null) {
            this.timeline = MaskingTimeline.createWithPlaceholderTimeline(mediaSource.getMediaItem());
        } else {
            this.timeline = MaskingTimeline.createWithRealTimeline(initialTimeline, null, null);
            this.hasRealTimeline = true;
        }
    }

    public final Object getExternalPeriodUid(Object obj) {
        return (this.timeline.replacedInternalPeriodUid == null || !this.timeline.replacedInternalPeriodUid.equals(obj)) ? obj : MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
    }

    public final Object getInternalPeriodUid(Object obj) {
        return (this.timeline.replacedInternalPeriodUid == null || !obj.equals(MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID)) ? obj : this.timeline.replacedInternalPeriodUid;
    }

    @Override // com.google.android.exoplayer2.source.WrappingMediaSource
    @Nullable
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        return mediaPeriodId.copyWithPeriodUid(getExternalPeriodUid(mediaPeriodId.periodUid));
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    @Override // com.google.android.exoplayer2.source.WrappingMediaSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onChildSourceInfoRefreshed(com.google.android.exoplayer2.Timeline r12) {
        /*
            r11 = this;
            boolean r1 = r11.isPrepared
            if (r1 == 0) goto L17
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = r11.timeline
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r0 = r1.cloneWithUpdatedTimeline(r12)
            r11.timeline = r0
            com.google.android.exoplayer2.source.MaskingMediaPeriod r0 = r11.unpreparedMaskingMediaPeriod
            if (r0 == 0) goto La3
            long r0 = r0.preparePositionOverrideUs
            r11.setPreparePositionOverrideToUnpreparedMaskingPeriod(r0)
            goto La3
        L17:
            boolean r1 = r12.isEmpty()
            if (r1 == 0) goto L36
            boolean r1 = r11.hasRealTimeline
            if (r1 == 0) goto L28
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = r11.timeline
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r0 = r1.cloneWithUpdatedTimeline(r12)
            goto L32
        L28:
            java.lang.Object r1 = com.google.android.exoplayer2.Timeline.Window.SINGLE_WINDOW_UID
            java.lang.Object r2 = com.google.android.exoplayer2.source.MaskingMediaSource.MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r3 = new com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline
            r3.<init>(r12, r1, r2)
            r0 = r3
        L32:
            r11.timeline = r0
            goto La3
        L36:
            com.google.android.exoplayer2.Timeline$Window r1 = r11.window
            r2 = 0
            r12.getWindow(r2, r1)
            com.google.android.exoplayer2.Timeline$Window r1 = r11.window
            long r3 = r1.defaultPositionUs
            java.lang.Object r6 = r1.uid
            com.google.android.exoplayer2.source.MaskingMediaPeriod r1 = r11.unpreparedMaskingMediaPeriod
            if (r1 == 0) goto L69
            long r7 = r1.preparePositionUs
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r5 = r11.timeline
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r1.id
            java.lang.Object r1 = r1.periodUid
            com.google.android.exoplayer2.Timeline$Period r9 = r11.period
            r5.getPeriodByUid(r1, r9)
            com.google.android.exoplayer2.Timeline$Period r1 = r11.period
            long r9 = r1.positionInWindowUs
            long r9 = r9 + r7
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = r11.timeline
            com.google.android.exoplayer2.Timeline$Window r5 = r11.window
            r7 = 0
            r1.getWindow(r2, r5, r7)
            long r1 = r5.defaultPositionUs
            int r5 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r5 == 0) goto L69
            r4 = r9
            goto L6a
        L69:
            r4 = r3
        L6a:
            com.google.android.exoplayer2.Timeline$Window r1 = r11.window
            com.google.android.exoplayer2.Timeline$Period r2 = r11.period
            r3 = 0
            r0 = r12
            android.util.Pair r1 = r0.getPeriodPositionUs(r1, r2, r3, r4)
            java.lang.Object r2 = r1.first
            java.lang.Object r1 = r1.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r3 = r1.longValue()
            boolean r1 = r11.hasRealTimeline
            if (r1 == 0) goto L89
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = r11.timeline
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r0 = r1.cloneWithUpdatedTimeline(r12)
            goto L8d
        L89:
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r0 = com.google.android.exoplayer2.source.MaskingMediaSource.MaskingTimeline.createWithRealTimeline(r12, r6, r2)
        L8d:
            r11.timeline = r0
            com.google.android.exoplayer2.source.MaskingMediaPeriod r0 = r11.unpreparedMaskingMediaPeriod
            if (r0 == 0) goto La3
            r11.setPreparePositionOverrideToUnpreparedMaskingPeriod(r3)
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r0 = r0.id
            java.lang.Object r1 = r0.periodUid
            java.lang.Object r1 = r11.getInternalPeriodUid(r1)
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r0 = r0.copyWithPeriodUid(r1)
            goto La4
        La3:
            r0 = 0
        La4:
            r1 = 1
            r11.hasRealTimeline = r1
            r11.isPrepared = r1
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r1 = r11.timeline
            r11.refreshSourceInfo(r1)
            if (r0 == 0) goto Lb8
            com.google.android.exoplayer2.source.MaskingMediaPeriod r1 = r11.unpreparedMaskingMediaPeriod
            r1.getClass()
            r1.createPeriod(r0)
        Lb8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.MaskingMediaSource.onChildSourceInfoRefreshed(com.google.android.exoplayer2.Timeline):void");
    }

    @Override // com.google.android.exoplayer2.source.WrappingMediaSource
    public void prepareSourceInternal() {
        if (this.useLazyPreparation) {
            return;
        }
        this.hasStartedPreparing = true;
        prepareChildSource();
    }

    @Override // com.google.android.exoplayer2.source.WrappingMediaSource, com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((MaskingMediaPeriod) mediaPeriod).releasePeriod();
        if (mediaPeriod == this.unpreparedMaskingMediaPeriod) {
            this.unpreparedMaskingMediaPeriod = null;
        }
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.BaseMediaSource
    public void releaseSourceInternal() {
        this.isPrepared = false;
        this.hasStartedPreparing = false;
        super.releaseSourceInternal();
    }

    @RequiresNonNull({"unpreparedMaskingMediaPeriod"})
    public final void setPreparePositionOverrideToUnpreparedMaskingPeriod(long j) {
        MaskingMediaPeriod maskingMediaPeriod = this.unpreparedMaskingMediaPeriod;
        int indexOfPeriod = this.timeline.getIndexOfPeriod(maskingMediaPeriod.id.periodUid);
        if (indexOfPeriod == -1) {
            return;
        }
        MaskingTimeline maskingTimeline = this.timeline;
        Timeline.Period period = this.period;
        maskingTimeline.getPeriod(indexOfPeriod, period, false);
        long j2 = period.durationUs;
        if (j2 != -9223372036854775807L && j >= j2) {
            j = Math.max(0L, j2 - 1);
        }
        maskingMediaPeriod.preparePositionOverrideUs = j;
    }

    @Override // com.google.android.exoplayer2.source.WrappingMediaSource, com.google.android.exoplayer2.source.MediaSource
    public MaskingMediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MaskingMediaPeriod maskingMediaPeriod = new MaskingMediaPeriod(mediaPeriodId, allocator, j);
        maskingMediaPeriod.setMediaSource(this.mediaSource);
        if (this.isPrepared) {
            maskingMediaPeriod.createPeriod(mediaPeriodId.copyWithPeriodUid(getInternalPeriodUid(mediaPeriodId.periodUid)));
            return maskingMediaPeriod;
        }
        this.unpreparedMaskingMediaPeriod = maskingMediaPeriod;
        if (!this.hasStartedPreparing) {
            this.hasStartedPreparing = true;
            prepareChildSource();
        }
        return maskingMediaPeriod;
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
    }
}
