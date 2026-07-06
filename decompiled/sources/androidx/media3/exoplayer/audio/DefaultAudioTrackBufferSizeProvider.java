package androidx.media3.exoplayer.audio;

import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class DefaultAudioTrackBufferSizeProvider implements DefaultAudioSink.AudioTrackBufferSizeProvider {
    public static final int AC3_BUFFER_MULTIPLICATION_FACTOR = 2;
    public static final int DTSHD_BUFFER_MULTIPLICATION_FACTOR = 4;
    public static final int MAX_PCM_BUFFER_DURATION_US = 750000;
    public static final int MIN_PCM_BUFFER_DURATION_US = 250000;
    public static final int OFFLOAD_BUFFER_DURATION_US = 50000000;
    public static final int PASSTHROUGH_BUFFER_DURATION_US = 250000;
    public static final int PCM_BUFFER_MULTIPLICATION_FACTOR = 4;
    public final int ac3BufferMultiplicationFactor;
    public final int dtshdBufferMultiplicationFactor;
    public final int maxPcmBufferDurationUs;
    public final int minPcmBufferDurationUs;
    public final int offloadBufferDurationUs;
    public final int passthroughBufferDurationUs;
    public final int pcmBufferMultiplicationFactor;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public int minPcmBufferDurationUs = 250000;
        public int maxPcmBufferDurationUs = 750000;
        public int pcmBufferMultiplicationFactor = 4;
        public int passthroughBufferDurationUs = 250000;
        public int offloadBufferDurationUs = 50000000;
        public int ac3BufferMultiplicationFactor = 2;
        public int dtshdBufferMultiplicationFactor = 4;

        public DefaultAudioTrackBufferSizeProvider build() {
            return new DefaultAudioTrackBufferSizeProvider(this);
        }

        @CanIgnoreReturnValue
        public Builder setAc3BufferMultiplicationFactor(int i) {
            this.ac3BufferMultiplicationFactor = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setDtshdBufferMultiplicationFactor(int i) {
            this.dtshdBufferMultiplicationFactor = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMaxPcmBufferDurationUs(int i) {
            this.maxPcmBufferDurationUs = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMinPcmBufferDurationUs(int i) {
            this.minPcmBufferDurationUs = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setOffloadBufferDurationUs(int i) {
            this.offloadBufferDurationUs = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPassthroughBufferDurationUs(int i) {
            this.passthroughBufferDurationUs = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPcmBufferMultiplicationFactor(int i) {
            this.pcmBufferMultiplicationFactor = i;
            return this;
        }
    }

    public DefaultAudioTrackBufferSizeProvider(Builder builder) {
        this.minPcmBufferDurationUs = builder.minPcmBufferDurationUs;
        this.maxPcmBufferDurationUs = builder.maxPcmBufferDurationUs;
        this.pcmBufferMultiplicationFactor = builder.pcmBufferMultiplicationFactor;
        this.passthroughBufferDurationUs = builder.passthroughBufferDurationUs;
        this.offloadBufferDurationUs = builder.offloadBufferDurationUs;
        this.ac3BufferMultiplicationFactor = builder.ac3BufferMultiplicationFactor;
        this.dtshdBufferMultiplicationFactor = builder.dtshdBufferMultiplicationFactor;
    }

    public static int durationUsToBytes(int i, int i2, int i3) {
        return Ints.checkedCast(((((long) i) * ((long) i2)) * ((long) i3)) / 1000000);
    }

    public static int getMaximumEncodedRateBytesPerSecond(int i) {
        switch (i) {
            case 5:
                return 80000;
            case 6:
            case 18:
                return 768000;
            case 7:
                return 192000;
            case 8:
                return 2250000;
            case 9:
                return 40000;
            case 10:
                return 100000;
            case 11:
                return 16000;
            case 12:
                return 7000;
            case 13:
            case 19:
            default:
                throw new IllegalArgumentException();
            case 14:
                return 3062500;
            case 15:
                return 8000;
            case 16:
                return 256000;
            case 17:
                return 336000;
            case 20:
                return 63750;
        }
    }

    public int get1xBufferSizeInBytes(int i, int i2, int i3, int i4, int i5, int i6) {
        if (i3 == 0) {
            return getPcmBufferSizeInBytes(i, i5, i4);
        }
        if (i3 == 1) {
            return getOffloadBufferSizeInBytes(i2);
        }
        if (i3 == 2) {
            return getPassthroughBufferSizeInBytes(i2, i6);
        }
        throw new IllegalArgumentException();
    }

    @Override // androidx.media3.exoplayer.audio.DefaultAudioSink.AudioTrackBufferSizeProvider
    public int getBufferSizeInBytes(int i, int i2, int i3, int i4, int i5, int i6, double d) {
        return (((Math.max(i, (int) (((double) get1xBufferSizeInBytes(i, i2, i3, i4, i5, i6)) * d)) + i4) - 1) / i4) * i4;
    }

    public int getOffloadBufferSizeInBytes(int i) {
        return Ints.checkedCast((((long) this.offloadBufferDurationUs) * ((long) getMaximumEncodedRateBytesPerSecond(i))) / 1000000);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0014  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getPassthroughBufferSizeInBytes(int r4, int r5) {
        /*
            r3 = this;
            int r0 = r3.passthroughBufferDurationUs
            r1 = 5
            r2 = 8
            if (r4 != r1) goto Lc
            int r1 = r3.ac3BufferMultiplicationFactor
        L9:
            int r0 = r0 * r1
            goto L11
        Lc:
            if (r4 != r2) goto L11
            int r1 = r3.dtshdBufferMultiplicationFactor
            goto L9
        L11:
            r1 = -1
            if (r5 == r1) goto L1b
            java.math.RoundingMode r4 = java.math.RoundingMode.CEILING
            int r4 = com.google.common.math.IntMath.divide(r5, r2, r4)
            goto L1f
        L1b:
            int r4 = getMaximumEncodedRateBytesPerSecond(r4)
        L1f:
            long r0 = (long) r0
            long r4 = (long) r4
            long r0 = r0 * r4
            r4 = 1000000(0xf4240, double:4.940656E-318)
            long r0 = r0 / r4
            int r4 = com.google.common.primitives.Ints.checkedCast(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.audio.DefaultAudioTrackBufferSizeProvider.getPassthroughBufferSizeInBytes(int, int):int");
    }

    public int getPcmBufferSizeInBytes(int i, int i2, int i3) {
        return Util.constrainValue(i * this.pcmBufferMultiplicationFactor, durationUsToBytes(this.minPcmBufferDurationUs, i2, i3), durationUsToBytes(this.maxPcmBufferDurationUs, i2, i3));
    }
}
