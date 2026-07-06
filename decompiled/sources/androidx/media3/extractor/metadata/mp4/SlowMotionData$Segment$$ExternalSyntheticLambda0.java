package androidx.media3.extractor.metadata.mp4;

import androidx.media3.extractor.metadata.mp4.SlowMotionData;
import com.google.common.collect.ComparisonChain;
import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class SlowMotionData$Segment$$ExternalSyntheticLambda0 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        SlowMotionData.Segment segment = (SlowMotionData.Segment) obj;
        SlowMotionData.Segment segment2 = (SlowMotionData.Segment) obj2;
        return ComparisonChain.ACTIVE.compare(segment.startTimeMs, segment2.startTimeMs).compare(segment.endTimeMs, segment2.endTimeMs).compare(segment.speedDivisor, segment2.speedDivisor).result();
    }
}
