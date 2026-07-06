package com.google.android.exoplayer2;

import android.os.Bundle;
import androidx.annotation.CheckResult;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class PlaybackParameters implements Bundleable {
    public final float pitch;
    public final int scaledUsPerMs;
    public final float speed;
    public static final PlaybackParameters DEFAULT = new PlaybackParameters(1.0f);
    public static final String FIELD_SPEED = Util.intToStringMaxRadix(0);
    public static final String FIELD_PITCH = Integer.toString(1, 36);
    public static final Bundleable.Creator<PlaybackParameters> CREATOR = new PlaybackParameters$$ExternalSyntheticLambda0();

    /* JADX INFO: renamed from: $r8$lambda$Emm-7RfmlhRqcQf5RbiZCuXgDco, reason: not valid java name */
    public static /* synthetic */ PlaybackParameters m395$r8$lambda$Emm7RfmlhRqcQf5RbiZCuXgDco(Bundle bundle) {
        return new PlaybackParameters(bundle.getFloat(FIELD_SPEED, 1.0f), bundle.getFloat(FIELD_PITCH, 1.0f));
    }

    public PlaybackParameters(float f) {
        this(f, 1.0f);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && PlaybackParameters.class == obj.getClass()) {
            PlaybackParameters playbackParameters = (PlaybackParameters) obj;
            if (this.speed == playbackParameters.speed && this.pitch == playbackParameters.pitch) {
                return true;
            }
        }
        return false;
    }

    public long getMediaTimeUsForPlayoutTimeMs(long j) {
        return j * ((long) this.scaledUsPerMs);
    }

    public int hashCode() {
        return Float.floatToRawIntBits(this.pitch) + ((Float.floatToRawIntBits(this.speed) + 527) * 31);
    }

    @Override // com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putFloat(FIELD_SPEED, this.speed);
        bundle.putFloat(FIELD_PITCH, this.pitch);
        return bundle;
    }

    public String toString() {
        return Util.formatInvariant("PlaybackParameters(speed=%.2f, pitch=%.2f)", Float.valueOf(this.speed), Float.valueOf(this.pitch));
    }

    @CheckResult
    public PlaybackParameters withSpeed(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        return new PlaybackParameters(f, this.pitch);
    }

    public PlaybackParameters(@FloatRange(from = 0.0d, fromInclusive = false) float f, @FloatRange(from = 0.0d, fromInclusive = false) float f2) {
        Assertions.checkArgument(f > 0.0f);
        Assertions.checkArgument(f2 > 0.0f);
        this.speed = f;
        this.pitch = f2;
        this.scaledUsPerMs = Math.round(f * 1000.0f);
    }
}
