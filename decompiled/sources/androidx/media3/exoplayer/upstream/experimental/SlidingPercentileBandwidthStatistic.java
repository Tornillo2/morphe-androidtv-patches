package androidx.media3.exoplayer.upstream.experimental;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import java.util.ArrayDeque;
import java.util.TreeSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class SlidingPercentileBandwidthStatistic implements BandwidthStatistic {
    public static final int DEFAULT_MAX_SAMPLES_COUNT = 10;
    public static final double DEFAULT_PERCENTILE = 0.5d;
    public long bitrateEstimate;
    public final int maxSampleCount;
    public final double percentile;
    public final ArrayDeque<Sample> samples;
    public final TreeSet<Sample> sortedSamples;
    public double weightSum;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Sample implements Comparable<Sample> {
        public final long bitrate;
        public final double weight;

        public Sample(long j, double d) {
            this.bitrate = j;
            this.weight = d;
        }

        @Override // java.lang.Comparable
        public int compareTo(Sample sample) {
            return Util.compareLong(this.bitrate, sample.bitrate);
        }
    }

    public SlidingPercentileBandwidthStatistic() {
        this(10, 0.5d);
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public void addSample(long j, long j2) {
        while (this.samples.size() >= this.maxSampleCount) {
            Sample sampleRemove = this.samples.remove();
            this.sortedSamples.remove(sampleRemove);
            this.weightSum -= sampleRemove.weight;
        }
        double dSqrt = Math.sqrt(j);
        Sample sample = new Sample((j * 8000000) / j2, dSqrt);
        this.samples.add(sample);
        this.sortedSamples.add(sample);
        this.weightSum += dSqrt;
        this.bitrateEstimate = calculateBitrateEstimate();
    }

    public final long calculateBitrateEstimate() {
        if (this.samples.isEmpty()) {
            return Long.MIN_VALUE;
        }
        double d = this.weightSum * this.percentile;
        double d2 = 0.0d;
        long j = 0;
        double d3 = 0.0d;
        for (Sample sample : this.sortedSamples) {
            double d4 = sample.weight;
            double d5 = (d4 / 2.0d) + d2;
            if (d5 >= d) {
                if (j == 0) {
                    return sample.bitrate;
                }
                return j + ((long) (((d - d3) * (sample.bitrate - j)) / (d5 - d3)));
            }
            j = sample.bitrate;
            d3 = d5;
            d2 = (d4 / 2.0d) + d5;
        }
        return j;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public long getBandwidthEstimate() {
        return this.bitrateEstimate;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public void reset() {
        this.samples.clear();
        this.sortedSamples.clear();
        this.weightSum = 0.0d;
        this.bitrateEstimate = Long.MIN_VALUE;
    }

    public SlidingPercentileBandwidthStatistic(int i, double d) {
        Assertions.checkArgument(d >= 0.0d && d <= 1.0d);
        this.maxSampleCount = i;
        this.percentile = d;
        this.samples = new ArrayDeque<>();
        this.sortedSamples = new TreeSet<>();
        this.bitrateEstimate = Long.MIN_VALUE;
    }
}
