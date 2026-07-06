package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Timeline;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ForwardingTimeline extends Timeline {
    public final Timeline timeline;

    public ForwardingTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getFirstWindowIndex(boolean z) {
        return this.timeline.getFirstWindowIndex(z);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getIndexOfPeriod(Object obj) {
        return this.timeline.getIndexOfPeriod(obj);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getLastWindowIndex(boolean z) {
        return this.timeline.getLastWindowIndex(z);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getNextWindowIndex(int i, int i2, boolean z) {
        return this.timeline.getNextWindowIndex(i, i2, z);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
        return this.timeline.getPeriod(i, period, z);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getPeriodCount() {
        return this.timeline.getPeriodCount();
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getPreviousWindowIndex(int i, int i2, boolean z) {
        return this.timeline.getPreviousWindowIndex(i, i2, z);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public Object getUidOfPeriod(int i) {
        return this.timeline.getUidOfPeriod(i);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
        return this.timeline.getWindow(i, window, j);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getWindowCount() {
        return this.timeline.getWindowCount();
    }
}
