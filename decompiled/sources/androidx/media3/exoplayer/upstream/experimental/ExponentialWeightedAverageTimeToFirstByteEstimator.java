package androidx.media3.exoplayer.upstream.experimental;

import androidx.annotation.VisibleForTesting;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.upstream.TimeToFirstByteEstimator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class ExponentialWeightedAverageTimeToFirstByteEstimator implements TimeToFirstByteEstimator {
    public static final double DEFAULT_SMOOTHING_FACTOR = 0.85d;
    public static final int MAX_DATA_SPECS = 10;
    public final Clock clock;
    public long estimateUs;
    public final LinkedHashMap<DataSpec, Long> initializedDataSpecs;
    public final double smoothingFactor;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FixedSizeLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
        public final int maxSize;

        public FixedSizeLinkedHashMap(int i) {
            this.maxSize = i;
        }

        @Override // java.util.LinkedHashMap
        public boolean removeEldestEntry(Map.Entry<K, V> entry) {
            return size() > this.maxSize;
        }
    }

    public ExponentialWeightedAverageTimeToFirstByteEstimator() {
        this(0.85d, Clock.DEFAULT);
    }

    @Override // androidx.media3.exoplayer.upstream.TimeToFirstByteEstimator
    public long getTimeToFirstByteEstimateUs() {
        return this.estimateUs;
    }

    @Override // androidx.media3.exoplayer.upstream.TimeToFirstByteEstimator
    public void onTransferInitializing(DataSpec dataSpec) {
        this.initializedDataSpecs.remove(dataSpec);
        this.initializedDataSpecs.put(dataSpec, Long.valueOf(Util.msToUs(this.clock.elapsedRealtime())));
    }

    @Override // androidx.media3.exoplayer.upstream.TimeToFirstByteEstimator
    public void onTransferStart(DataSpec dataSpec) {
        Long lRemove = this.initializedDataSpecs.remove(dataSpec);
        if (lRemove == null) {
            return;
        }
        long jMsToUs = Util.msToUs(this.clock.elapsedRealtime()) - lRemove.longValue();
        long j = this.estimateUs;
        if (j == -9223372036854775807L) {
            this.estimateUs = jMsToUs;
            return;
        }
        double d = this.smoothingFactor;
        this.estimateUs = (long) (((1.0d - d) * jMsToUs) + (j * d));
    }

    @Override // androidx.media3.exoplayer.upstream.TimeToFirstByteEstimator
    public void reset() {
        this.estimateUs = -9223372036854775807L;
    }

    public ExponentialWeightedAverageTimeToFirstByteEstimator(double d) {
        this(d, Clock.DEFAULT);
    }

    @VisibleForTesting
    public ExponentialWeightedAverageTimeToFirstByteEstimator(double d, Clock clock) {
        this.smoothingFactor = d;
        this.clock = clock;
        this.initializedDataSpecs = new FixedSizeLinkedHashMap(10);
        this.estimateUs = -9223372036854775807L;
    }
}
