package androidx.media3.extractor.text;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import com.google.common.collect.ByFunctionOrdering;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.Ordering;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class CuesWithTimingSubtitle implements Subtitle {
    public static final Ordering<CuesWithTiming> CUES_BY_START_TIME_ASCENDING;
    public static final String TAG = "CuesWithTimingSubtitle";
    public final ImmutableList<ImmutableList<Cue>> eventCues;
    public final long[] eventTimesUs;

    static {
        NaturalOrdering naturalOrdering = NaturalOrdering.INSTANCE;
        CuesWithTimingSubtitle$$ExternalSyntheticLambda0 cuesWithTimingSubtitle$$ExternalSyntheticLambda0 = new CuesWithTimingSubtitle$$ExternalSyntheticLambda0();
        naturalOrdering.getClass();
        CUES_BY_START_TIME_ASCENDING = new ByFunctionOrdering(cuesWithTimingSubtitle$$ExternalSyntheticLambda0, naturalOrdering);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public CuesWithTimingSubtitle(java.util.List<androidx.media3.extractor.text.CuesWithTiming> r15) {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.CuesWithTimingSubtitle.<init>(java.util.List):void");
    }

    public static long normalizeUnsetStartTimeToZero(long j) {
        if (j == -9223372036854775807L) {
            return 0L;
        }
        return j;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public long getEventTime(int i) {
        Assertions.checkArgument(i < this.eventCues.size());
        return this.eventTimesUs[i];
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getEventTimeCount() {
        return this.eventCues.size();
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getNextEventTimeIndex(long j) {
        int iBinarySearchCeil = Util.binarySearchCeil(this.eventTimesUs, j, false, false);
        if (iBinarySearchCeil < this.eventCues.size()) {
            return iBinarySearchCeil;
        }
        return -1;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public ImmutableList<Cue> getCues(long j) {
        int iBinarySearchFloor = Util.binarySearchFloor(this.eventTimesUs, j, true, false);
        return iBinarySearchFloor == -1 ? ImmutableList.of() : this.eventCues.get(iBinarySearchFloor);
    }
}
