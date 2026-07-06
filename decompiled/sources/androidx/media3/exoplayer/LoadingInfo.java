package androidx.media3.exoplayer;

import androidx.annotation.Nullable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class LoadingInfo {
    public final long lastRebufferRealtimeMs;
    public final long playbackPositionUs;
    public final float playbackSpeed;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public long lastRebufferRealtimeMs;
        public long playbackPositionUs;
        public float playbackSpeed;

        public LoadingInfo build() {
            return new LoadingInfo(this);
        }

        @CanIgnoreReturnValue
        public Builder setLastRebufferRealtimeMs(long j) {
            Assertions.checkArgument(j >= 0 || j == -9223372036854775807L);
            this.lastRebufferRealtimeMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPlaybackPositionUs(long j) {
            this.playbackPositionUs = j;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPlaybackSpeed(float f) {
            Assertions.checkArgument(f > 0.0f || f == -3.4028235E38f);
            this.playbackSpeed = f;
            return this;
        }

        public Builder() {
            this.playbackPositionUs = -9223372036854775807L;
            this.playbackSpeed = -3.4028235E38f;
            this.lastRebufferRealtimeMs = -9223372036854775807L;
        }

        public Builder(LoadingInfo loadingInfo) {
            this.playbackPositionUs = loadingInfo.playbackPositionUs;
            this.playbackSpeed = loadingInfo.playbackSpeed;
            this.lastRebufferRealtimeMs = loadingInfo.lastRebufferRealtimeMs;
        }
    }

    public Builder buildUpon() {
        return new Builder(this);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LoadingInfo)) {
            return false;
        }
        LoadingInfo loadingInfo = (LoadingInfo) obj;
        return this.playbackPositionUs == loadingInfo.playbackPositionUs && this.playbackSpeed == loadingInfo.playbackSpeed && this.lastRebufferRealtimeMs == loadingInfo.lastRebufferRealtimeMs;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.playbackPositionUs), Float.valueOf(this.playbackSpeed), Long.valueOf(this.lastRebufferRealtimeMs)});
    }

    public boolean rebufferedSince(long j) {
        long j2 = this.lastRebufferRealtimeMs;
        return (j2 == -9223372036854775807L || j == -9223372036854775807L || j2 < j) ? false : true;
    }

    public LoadingInfo(Builder builder) {
        this.playbackPositionUs = builder.playbackPositionUs;
        this.playbackSpeed = builder.playbackSpeed;
        this.lastRebufferRealtimeMs = builder.lastRebufferRealtimeMs;
    }
}
