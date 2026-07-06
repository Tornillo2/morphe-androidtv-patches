package androidx.media3.exoplayer.upstream.experimental;

import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.upstream.experimental.SlidingWeightedAverageBandwidthStatistic;
import java.util.Deque;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class SlidingWeightedAverageBandwidthStatistic$$ExternalSyntheticLambda0 implements SlidingWeightedAverageBandwidthStatistic.SampleEvictionFunction {
    public final /* synthetic */ long f$0;
    public final /* synthetic */ Clock f$1;

    public /* synthetic */ SlidingWeightedAverageBandwidthStatistic$$ExternalSyntheticLambda0(long j, Clock clock) {
        this.f$0 = j;
        this.f$1 = clock;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.SlidingWeightedAverageBandwidthStatistic.SampleEvictionFunction
    public final boolean shouldEvictSample(Deque deque) {
        return SlidingWeightedAverageBandwidthStatistic.m167$r8$lambda$8eutH22PzMxQNGj16eC8HloLp4(this.f$0, this.f$1, deque);
    }
}
