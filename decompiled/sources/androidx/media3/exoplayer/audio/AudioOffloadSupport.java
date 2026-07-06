package androidx.media3.exoplayer.audio;

import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class AudioOffloadSupport {
    public static final AudioOffloadSupport DEFAULT_UNSUPPORTED = new Builder().build();
    public final boolean isFormatSupported;
    public final boolean isGaplessSupported;
    public final boolean isSpeedChangeSupported;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public boolean isFormatSupported;
        public boolean isGaplessSupported;
        public boolean isSpeedChangeSupported;

        public Builder() {
        }

        public AudioOffloadSupport build() {
            if (this.isFormatSupported || !(this.isGaplessSupported || this.isSpeedChangeSupported)) {
                return new AudioOffloadSupport(this);
            }
            throw new IllegalStateException("Secondary offload attribute fields are true but primary isFormatSupported is false");
        }

        @CanIgnoreReturnValue
        public Builder setIsFormatSupported(boolean z) {
            this.isFormatSupported = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setIsGaplessSupported(boolean z) {
            this.isGaplessSupported = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setIsSpeedChangeSupported(boolean z) {
            this.isSpeedChangeSupported = z;
            return this;
        }

        public Builder(AudioOffloadSupport audioOffloadSupport) {
            this.isFormatSupported = audioOffloadSupport.isFormatSupported;
            this.isGaplessSupported = audioOffloadSupport.isGaplessSupported;
            this.isSpeedChangeSupported = audioOffloadSupport.isSpeedChangeSupported;
        }
    }

    public Builder buildUpon() {
        return new Builder(this);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && AudioOffloadSupport.class == obj.getClass()) {
            AudioOffloadSupport audioOffloadSupport = (AudioOffloadSupport) obj;
            if (this.isFormatSupported == audioOffloadSupport.isFormatSupported && this.isGaplessSupported == audioOffloadSupport.isGaplessSupported && this.isSpeedChangeSupported == audioOffloadSupport.isSpeedChangeSupported) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((this.isFormatSupported ? 1 : 0) << 2) + ((this.isGaplessSupported ? 1 : 0) << 1) + (this.isSpeedChangeSupported ? 1 : 0);
    }

    public AudioOffloadSupport(Builder builder) {
        this.isFormatSupported = builder.isFormatSupported;
        this.isGaplessSupported = builder.isGaplessSupported;
        this.isSpeedChangeSupported = builder.isSpeedChangeSupported;
    }
}
