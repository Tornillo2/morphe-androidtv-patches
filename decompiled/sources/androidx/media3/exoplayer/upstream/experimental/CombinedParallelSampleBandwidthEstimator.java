package androidx.media3.exoplayer.upstream.experimental;

import android.os.Handler;
import androidx.annotation.VisibleForTesting;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSource;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class CombinedParallelSampleBandwidthEstimator implements BandwidthEstimator {
    public long bandwidthEstimate;
    public final BandwidthStatistic bandwidthStatistic;
    public final Clock clock;
    public final BandwidthMeter.EventListener.EventDispatcher eventDispatcher;
    public long lastReportedBandwidthEstimate;
    public final long minBytesTransferred;
    public final int minSamples;
    public long sampleBytesTransferred;
    public long sampleStartTimeMs;
    public int streamCount;
    public long totalBytesTransferred;
    public int totalSamplesAdded;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public BandwidthStatistic bandwidthStatistic = new SlidingWeightedAverageBandwidthStatistic();
        public Clock clock = Clock.DEFAULT;
        public long minBytesTransferred;
        public int minSamples;

        public CombinedParallelSampleBandwidthEstimator build() {
            return new CombinedParallelSampleBandwidthEstimator(this);
        }

        @CanIgnoreReturnValue
        public Builder setBandwidthStatistic(BandwidthStatistic bandwidthStatistic) {
            bandwidthStatistic.getClass();
            this.bandwidthStatistic = bandwidthStatistic;
            return this;
        }

        @CanIgnoreReturnValue
        @VisibleForTesting
        public Builder setClock(Clock clock) {
            this.clock = clock;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMinBytesTransferred(long j) {
            Assertions.checkArgument(j >= 0);
            this.minBytesTransferred = j;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMinSamples(int i) {
            Assertions.checkArgument(i >= 0);
            this.minSamples = i;
            return this;
        }
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public void addEventListener(Handler handler, BandwidthMeter.EventListener eventListener) {
        this.eventDispatcher.addListener(handler, eventListener);
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public long getBandwidthEstimate() {
        return this.bandwidthEstimate;
    }

    public final void maybeNotifyBandwidthSample(int i, long j, long j2) {
        if (j2 != Long.MIN_VALUE) {
            if (i == 0 && j == 0 && j2 == this.lastReportedBandwidthEstimate) {
                return;
            }
            this.lastReportedBandwidthEstimate = j2;
            this.eventDispatcher.bandwidthSample(i, j, j2);
        }
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public void onBytesTransferred(DataSource dataSource, int i) {
        long j = i;
        this.sampleBytesTransferred += j;
        this.totalBytesTransferred += j;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public void onNetworkTypeChange(long j) {
        long jElapsedRealtime = this.clock.elapsedRealtime();
        maybeNotifyBandwidthSample(this.streamCount > 0 ? (int) (jElapsedRealtime - this.sampleStartTimeMs) : 0, this.sampleBytesTransferred, j);
        this.bandwidthStatistic.reset();
        this.bandwidthEstimate = Long.MIN_VALUE;
        this.sampleStartTimeMs = jElapsedRealtime;
        this.sampleBytesTransferred = 0L;
        this.totalSamplesAdded = 0;
        this.totalBytesTransferred = 0L;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public void onTransferEnd(DataSource dataSource) {
        Assertions.checkState(this.streamCount > 0);
        int i = this.streamCount - 1;
        this.streamCount = i;
        if (i <= 0) {
            long jElapsedRealtime = (int) (this.clock.elapsedRealtime() - this.sampleStartTimeMs);
            if (jElapsedRealtime > 0) {
                this.bandwidthStatistic.addSample(this.sampleBytesTransferred, 1000 * jElapsedRealtime);
                int i2 = this.totalSamplesAdded + 1;
                this.totalSamplesAdded = i2;
                if (i2 > this.minSamples && this.totalBytesTransferred > this.minBytesTransferred) {
                    this.bandwidthEstimate = this.bandwidthStatistic.getBandwidthEstimate();
                }
                maybeNotifyBandwidthSample((int) jElapsedRealtime, this.sampleBytesTransferred, this.bandwidthEstimate);
                this.sampleBytesTransferred = 0L;
            }
        }
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public void onTransferStart(DataSource dataSource) {
        if (this.streamCount == 0) {
            this.sampleStartTimeMs = this.clock.elapsedRealtime();
        }
        this.streamCount++;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public void removeEventListener(BandwidthMeter.EventListener eventListener) {
        this.eventDispatcher.removeListener(eventListener);
    }

    public CombinedParallelSampleBandwidthEstimator(Builder builder) {
        this.bandwidthStatistic = builder.bandwidthStatistic;
        this.minSamples = builder.minSamples;
        this.minBytesTransferred = builder.minBytesTransferred;
        this.clock = builder.clock;
        this.eventDispatcher = new BandwidthMeter.EventListener.EventDispatcher();
        this.bandwidthEstimate = Long.MIN_VALUE;
        this.lastReportedBandwidthEstimate = Long.MIN_VALUE;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public void onTransferInitializing(DataSource dataSource) {
    }
}
