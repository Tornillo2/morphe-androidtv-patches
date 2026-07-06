package androidx.media3.common.util;

import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class ConstantRateTimestampIterator implements TimestampIterator {
    public double currentTimestampUs;
    public final long durationUs;
    public final float frameRate;
    public final double framesDurationUs;
    public int framesToAdd;
    public final long startingTimestampUs;

    public ConstantRateTimestampIterator(@IntRange(from = 1) long j, @FloatRange(from = 0.0d, fromInclusive = false) float f) {
        this(j, f, 0L);
    }

    @Override // androidx.media3.common.util.TimestampIterator
    public boolean hasNext() {
        return this.framesToAdd != 0;
    }

    @Override // androidx.media3.common.util.TimestampIterator
    public long next() {
        Assertions.checkState(hasNext());
        this.framesToAdd--;
        long jRound = Math.round(this.currentTimestampUs);
        this.currentTimestampUs += this.framesDurationUs;
        return jRound;
    }

    public ConstantRateTimestampIterator(@IntRange(from = 1) long j, @FloatRange(from = 0.0d, fromInclusive = false) float f, @IntRange(from = 0) long j2) {
        Assertions.checkArgument(j > 0);
        Assertions.checkArgument(f > 0.0f);
        Assertions.checkArgument(j2 >= 0);
        this.durationUs = j;
        this.frameRate = f;
        this.startingTimestampUs = j2;
        this.currentTimestampUs = j2;
        this.framesToAdd = Math.round((j / 1000000.0f) * f);
        this.framesDurationUs = 1000000.0f / f;
    }

    @Override // androidx.media3.common.util.TimestampIterator
    public ConstantRateTimestampIterator copyOf() {
        return new ConstantRateTimestampIterator(this.durationUs, this.frameRate, this.startingTimestampUs);
    }
}
