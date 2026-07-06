package androidx.media3.exoplayer.upstream.experimental;

import androidx.media3.exoplayer.upstream.experimental.SlidingWeightedAverageBandwidthStatistic;
import java.util.Deque;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class SlidingWeightedAverageBandwidthStatistic$$ExternalSyntheticLambda1 implements SlidingWeightedAverageBandwidthStatistic.SampleEvictionFunction {
    public final /* synthetic */ long f$0;

    public /* synthetic */ SlidingWeightedAverageBandwidthStatistic$$ExternalSyntheticLambda1(long j) {
        this.f$0 = j;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.SlidingWeightedAverageBandwidthStatistic.SampleEvictionFunction
    public final boolean shouldEvictSample(Deque deque) {
        return SlidingWeightedAverageBandwidthStatistic.m168$r8$lambda$CFfkmaxJhQH3fs9DGWS652MKgI(this.f$0, deque);
    }
}
