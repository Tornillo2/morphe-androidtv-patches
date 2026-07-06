package com.amazon.livingroom.mediapipelinebackend;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.annotation.OptIn;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.audio.DefaultAudioTrackBufferSizeProvider;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@OptIn(markerClass = {UnstableApi.class})
public final class AvAudioTrackBufferSizeProvider extends DefaultAudioTrackBufferSizeProvider {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int PRIME_VIDEO_E_AC3_MAX_RATE_BYTES_PER_SECOND = 56000;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AvAudioTrackBufferSizeProvider() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // androidx.media3.exoplayer.audio.DefaultAudioTrackBufferSizeProvider
    public int getPassthroughBufferSizeInBytes(int i, int i2) {
        if (i2 != -1) {
            return super.getPassthroughBufferSizeInBytes(i, i2);
        }
        if (i == 6) {
            return (int) ((((long) this.passthroughBufferDurationUs) * ((long) 56000)) / 1000000);
        }
        throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unable to calculate AudioTrack buffer size for unknown passthrough encoding: ", i));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AvAudioTrackBufferSizeProvider(@NotNull DefaultAudioTrackBufferSizeProvider.Builder builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
    }

    public /* synthetic */ AvAudioTrackBufferSizeProvider(DefaultAudioTrackBufferSizeProvider.Builder builder, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new DefaultAudioTrackBufferSizeProvider.Builder() : builder);
    }
}
