package androidx.media3.exoplayer.text;

import androidx.media3.common.text.Cue;
import androidx.media3.extractor.text.CuesWithTiming;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ReplacingCuesResolver implements CuesResolver {
    public final ArrayList<CuesWithTiming> cuesWithTimingList = new ArrayList<>();

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    @Override // androidx.media3.exoplayer.text.CuesResolver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean addCues(androidx.media3.extractor.text.CuesWithTiming r10, long r11) {
        /*
            r9 = this;
            long r0 = r10.startTimeUs
            r2 = 0
            r3 = 1
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 == 0) goto Lf
            r0 = 1
            goto L10
        Lf:
            r0 = 0
        L10:
            androidx.media3.common.util.Assertions.checkArgument(r0)
            long r0 = r10.startTimeUs
            int r6 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r6 > 0) goto L25
            long r0 = r10.endTimeUs
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 == 0) goto L23
            int r4 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r4 >= 0) goto L25
        L23:
            r0 = 1
            goto L26
        L25:
            r0 = 0
        L26:
            java.util.ArrayList<androidx.media3.extractor.text.CuesWithTiming> r1 = r9.cuesWithTimingList
            int r1 = r1.size()
            int r1 = r1 - r3
        L2d:
            if (r1 < 0) goto L58
            long r4 = r10.startTimeUs
            java.util.ArrayList<androidx.media3.extractor.text.CuesWithTiming> r6 = r9.cuesWithTimingList
            java.lang.Object r6 = r6.get(r1)
            androidx.media3.extractor.text.CuesWithTiming r6 = (androidx.media3.extractor.text.CuesWithTiming) r6
            long r6 = r6.startTimeUs
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 < 0) goto L46
            java.util.ArrayList<androidx.media3.extractor.text.CuesWithTiming> r11 = r9.cuesWithTimingList
            int r1 = r1 + r3
            r11.add(r1, r10)
            return r0
        L46:
            java.util.ArrayList<androidx.media3.extractor.text.CuesWithTiming> r4 = r9.cuesWithTimingList
            java.lang.Object r4 = r4.get(r1)
            androidx.media3.extractor.text.CuesWithTiming r4 = (androidx.media3.extractor.text.CuesWithTiming) r4
            long r4 = r4.startTimeUs
            int r6 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r6 > 0) goto L55
            r0 = 0
        L55:
            int r1 = r1 + (-1)
            goto L2d
        L58:
            java.util.ArrayList<androidx.media3.extractor.text.CuesWithTiming> r11 = r9.cuesWithTimingList
            r11.add(r2, r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.text.ReplacingCuesResolver.addCues(androidx.media3.extractor.text.CuesWithTiming, long):boolean");
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public void clear() {
        this.cuesWithTimingList.clear();
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public void discardCuesBeforeTimeUs(long j) {
        int indexOfCuesStartingAfter = getIndexOfCuesStartingAfter(j);
        if (indexOfCuesStartingAfter > 0) {
            this.cuesWithTimingList.subList(0, indexOfCuesStartingAfter).clear();
        }
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public ImmutableList<Cue> getCuesAtTimeUs(long j) {
        int indexOfCuesStartingAfter = getIndexOfCuesStartingAfter(j);
        if (indexOfCuesStartingAfter == 0) {
            return ImmutableList.of();
        }
        CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(indexOfCuesStartingAfter - 1);
        long j2 = cuesWithTiming.endTimeUs;
        return (j2 == -9223372036854775807L || j < j2) ? cuesWithTiming.cues : ImmutableList.of();
    }

    public final int getIndexOfCuesStartingAfter(long j) {
        for (int i = 0; i < this.cuesWithTimingList.size(); i++) {
            if (j < this.cuesWithTimingList.get(i).startTimeUs) {
                return i;
            }
        }
        return this.cuesWithTimingList.size();
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public long getNextCueChangeTimeUs(long j) {
        if (this.cuesWithTimingList.isEmpty()) {
            return Long.MIN_VALUE;
        }
        if (j < this.cuesWithTimingList.get(0).startTimeUs) {
            return this.cuesWithTimingList.get(0).startTimeUs;
        }
        for (int i = 1; i < this.cuesWithTimingList.size(); i++) {
            CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(i);
            if (j < cuesWithTiming.startTimeUs) {
                long j2 = this.cuesWithTimingList.get(i - 1).endTimeUs;
                return (j2 == -9223372036854775807L || j2 <= j || j2 >= cuesWithTiming.startTimeUs) ? cuesWithTiming.startTimeUs : j2;
            }
        }
        long j3 = ((CuesWithTiming) Iterables.getLast(this.cuesWithTimingList)).endTimeUs;
        if (j3 == -9223372036854775807L || j >= j3) {
            return Long.MIN_VALUE;
        }
        return j3;
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public long getPreviousCueChangeTimeUs(long j) {
        if (this.cuesWithTimingList.isEmpty() || j < this.cuesWithTimingList.get(0).startTimeUs) {
            return -9223372036854775807L;
        }
        for (int i = 1; i < this.cuesWithTimingList.size(); i++) {
            long j2 = this.cuesWithTimingList.get(i).startTimeUs;
            if (j == j2) {
                return j2;
            }
            if (j < j2) {
                CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(i - 1);
                long j3 = cuesWithTiming.endTimeUs;
                return (j3 == -9223372036854775807L || j3 > j) ? cuesWithTiming.startTimeUs : j3;
            }
        }
        CuesWithTiming cuesWithTiming2 = (CuesWithTiming) Iterables.getLast(this.cuesWithTimingList);
        long j4 = cuesWithTiming2.endTimeUs;
        return (j4 == -9223372036854775807L || j < j4) ? cuesWithTiming2.startTimeUs : j4;
    }
}
