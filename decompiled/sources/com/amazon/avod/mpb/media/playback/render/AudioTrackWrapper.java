package com.amazon.avod.mpb.media.playback.render;

import android.media.AudioTrack;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AudioTrackWrapper {

    @NotNull
    public final AudioTrack audioTrack;
    public final int audioTrackBufferSizeBytes;
    public final int channelCount;
    public final int outputEncoding;

    public AudioTrackWrapper(@NotNull AudioTrack audioTrack, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(audioTrack, "audioTrack");
        this.audioTrack = audioTrack;
        this.audioTrackBufferSizeBytes = i;
        this.outputEncoding = i2;
        this.channelCount = i3;
    }

    @NotNull
    public final AudioTrack getAudioTrack() {
        return this.audioTrack;
    }

    public final int getAudioTrackBufferSizeBytes() {
        return this.audioTrackBufferSizeBytes;
    }

    public final int getChannelCount() {
        return this.channelCount;
    }

    public final int getOutputEncoding() {
        return this.outputEncoding;
    }
}
