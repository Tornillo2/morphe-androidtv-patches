package com.amazon.avod.mpb.api.sample;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AudioMetadata {

    @NotNull
    public final AudioCodecType codecType;
    public final int numChannels;
    public final int sampleRate;

    public AudioMetadata(@NotNull AudioCodecType codecType, int i, int i2) {
        Intrinsics.checkNotNullParameter(codecType, "codecType");
        this.codecType = codecType;
        this.sampleRate = i;
        this.numChannels = i2;
    }

    public static /* synthetic */ AudioMetadata copy$default(AudioMetadata audioMetadata, AudioCodecType audioCodecType, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            audioCodecType = audioMetadata.codecType;
        }
        if ((i3 & 2) != 0) {
            i = audioMetadata.sampleRate;
        }
        if ((i3 & 4) != 0) {
            i2 = audioMetadata.numChannels;
        }
        return audioMetadata.copy(audioCodecType, i, i2);
    }

    @NotNull
    public final AudioCodecType component1() {
        return this.codecType;
    }

    public final int component2() {
        return this.sampleRate;
    }

    public final int component3() {
        return this.numChannels;
    }

    @NotNull
    public final AudioMetadata copy(@NotNull AudioCodecType codecType, int i, int i2) {
        Intrinsics.checkNotNullParameter(codecType, "codecType");
        return new AudioMetadata(codecType, i, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioMetadata)) {
            return false;
        }
        AudioMetadata audioMetadata = (AudioMetadata) obj;
        return this.codecType == audioMetadata.codecType && this.sampleRate == audioMetadata.sampleRate && this.numChannels == audioMetadata.numChannels;
    }

    @NotNull
    public final AudioCodecType getCodecType() {
        return this.codecType;
    }

    public final int getNumChannels() {
        return this.numChannels;
    }

    public final int getSampleRate() {
        return this.sampleRate;
    }

    public int hashCode() {
        return (((this.codecType.hashCode() * 31) + this.sampleRate) * 31) + this.numChannels;
    }

    @NotNull
    public String toString() {
        AudioCodecType audioCodecType = this.codecType;
        int i = this.sampleRate;
        int i2 = this.numChannels;
        StringBuilder sb = new StringBuilder("AudioMetadata(codecType=");
        sb.append(audioCodecType);
        sb.append(", sampleRate=");
        sb.append(i);
        sb.append(", numChannels=");
        return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, i2, ")");
    }
}
