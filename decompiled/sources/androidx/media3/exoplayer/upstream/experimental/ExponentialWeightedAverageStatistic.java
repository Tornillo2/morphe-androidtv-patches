package androidx.media3.exoplayer.upstream.experimental;

import androidx.media3.common.util.UnstableApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class ExponentialWeightedAverageStatistic implements BandwidthStatistic {
    public static final double DEFAULT_SMOOTHING_FACTOR = 0.9999d;
    public long bitrateEstimate;
    public final double smoothingFactor;

    public ExponentialWeightedAverageStatistic() {
        this(0.9999d);
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public void addSample(long j, long j2) {
        long j3 = (8000000 * j) / j2;
        if (this.bitrateEstimate == Long.MIN_VALUE) {
            this.bitrateEstimate = j3;
            return;
        }
        double dPow = Math.pow(this.smoothingFactor, Math.sqrt(j));
        this.bitrateEstimate = (long) (((1.0d - dPow) * j3) + (this.bitrateEstimate * dPow));
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public long getBandwidthEstimate() {
        return this.bitrateEstimate;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public void reset() {
        this.bitrateEstimate = Long.MIN_VALUE;
    }

    public ExponentialWeightedAverageStatistic(double d) {
        this.smoothingFactor = d;
        this.bitrateEstimate = Long.MIN_VALUE;
    }
}
