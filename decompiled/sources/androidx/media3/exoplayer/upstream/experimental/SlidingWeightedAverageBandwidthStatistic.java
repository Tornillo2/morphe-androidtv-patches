package androidx.media3.exoplayer.upstream.experimental;

import androidx.annotation.VisibleForTesting;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import java.util.ArrayDeque;
import java.util.Deque;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class SlidingWeightedAverageBandwidthStatistic implements BandwidthStatistic {
    public static final int DEFAULT_MAX_SAMPLES_COUNT = 10;
    public double bitrateWeightProductSum;
    public final Clock clock;
    public final SampleEvictionFunction sampleEvictionFunction;
    public final ArrayDeque<Sample> samples;
    public double weightSum;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Sample {
        public final long bitrate;
        public final long timeAddedMs;
        public final double weight;

        public Sample(long j, double d, long j2) {
            this.bitrate = j;
            this.weight = d;
            this.timeAddedMs = j2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface SampleEvictionFunction {
        boolean shouldEvictSample(Deque<Sample> deque);
    }

    /* JADX INFO: renamed from: $r8$lambda$8eutH22PzMxQNGj16eC-8HloLp4, reason: not valid java name */
    public static /* synthetic */ boolean m167$r8$lambda$8eutH22PzMxQNGj16eC8HloLp4(long j, Clock clock, Deque deque) {
        if (deque.isEmpty()) {
            return false;
        }
        Sample sample = (Sample) deque.peek();
        Util.castNonNull(sample);
        return sample.timeAddedMs + j < clock.elapsedRealtime();
    }

    /* JADX INFO: renamed from: $r8$lambda$CFfkmaxJhQH3fs9DGWS652MKg-I, reason: not valid java name */
    public static /* synthetic */ boolean m168$r8$lambda$CFfkmaxJhQH3fs9DGWS652MKgI(long j, Deque deque) {
        return ((long) deque.size()) >= j;
    }

    public SlidingWeightedAverageBandwidthStatistic() {
        this(new SlidingWeightedAverageBandwidthStatistic$$ExternalSyntheticLambda1(10L));
    }

    public static SampleEvictionFunction getAgeBasedEvictionFunction(long j) {
        return new SlidingWeightedAverageBandwidthStatistic$$ExternalSyntheticLambda0(j, Clock.DEFAULT);
    }

    public static SampleEvictionFunction getMaxCountEvictionFunction(long j) {
        return new SlidingWeightedAverageBandwidthStatistic$$ExternalSyntheticLambda1(j);
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public void addSample(long j, long j2) {
        while (this.sampleEvictionFunction.shouldEvictSample(this.samples)) {
            Sample sampleRemove = this.samples.remove();
            double d = this.bitrateWeightProductSum;
            double d2 = sampleRemove.bitrate;
            double d3 = sampleRemove.weight;
            this.bitrateWeightProductSum = d - (d2 * d3);
            this.weightSum -= d3;
        }
        double dSqrt = Math.sqrt(j);
        long j3 = (j * 8000000) / j2;
        this.samples.add(new Sample(j3, dSqrt, this.clock.elapsedRealtime()));
        this.bitrateWeightProductSum = (j3 * dSqrt) + this.bitrateWeightProductSum;
        this.weightSum += dSqrt;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public long getBandwidthEstimate() {
        if (this.samples.isEmpty()) {
            return Long.MIN_VALUE;
        }
        return (long) (this.bitrateWeightProductSum / this.weightSum);
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public void reset() {
        this.samples.clear();
        this.bitrateWeightProductSum = 0.0d;
        this.weightSum = 0.0d;
    }

    public SlidingWeightedAverageBandwidthStatistic(SampleEvictionFunction sampleEvictionFunction) {
        this(sampleEvictionFunction, Clock.DEFAULT);
    }

    @VisibleForTesting
    public static SampleEvictionFunction getAgeBasedEvictionFunction(long j, Clock clock) {
        return new SlidingWeightedAverageBandwidthStatistic$$ExternalSyntheticLambda0(j, clock);
    }

    @VisibleForTesting
    public SlidingWeightedAverageBandwidthStatistic(SampleEvictionFunction sampleEvictionFunction, Clock clock) {
        this.samples = new ArrayDeque<>();
        this.sampleEvictionFunction = sampleEvictionFunction;
        this.clock = clock;
    }
}
