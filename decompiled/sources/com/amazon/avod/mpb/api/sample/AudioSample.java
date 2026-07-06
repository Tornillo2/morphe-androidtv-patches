package com.amazon.avod.mpb.api.sample;

import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AudioSample extends MediaSample {

    @NotNull
    public final ByteBuffer data;
    public final long durationMs;

    @Nullable
    public final EncryptionInfo encryptionInfo;
    public final boolean isEncrypted;
    public final long ptsMs;
    public final long size;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioSample(@NotNull ByteBuffer data, long j, long j2, long j3, boolean z, @Nullable EncryptionInfo encryptionInfo) {
        super(data, j, j2, j3, z, encryptionInfo);
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        this.size = j;
        this.ptsMs = j2;
        this.durationMs = j3;
        this.isEncrypted = z;
        this.encryptionInfo = encryptionInfo;
    }

    public static /* synthetic */ AudioSample copy$default(AudioSample audioSample, ByteBuffer byteBuffer, long j, long j2, long j3, boolean z, EncryptionInfo encryptionInfo, int i, Object obj) {
        if ((i & 1) != 0) {
            byteBuffer = audioSample.data;
        }
        if ((i & 2) != 0) {
            j = audioSample.size;
        }
        if ((i & 4) != 0) {
            j2 = audioSample.ptsMs;
        }
        if ((i & 8) != 0) {
            j3 = audioSample.durationMs;
        }
        if ((i & 16) != 0) {
            z = audioSample.isEncrypted;
        }
        if ((i & 32) != 0) {
            encryptionInfo = audioSample.encryptionInfo;
        }
        long j4 = j3;
        long j5 = j2;
        return audioSample.copy(byteBuffer, j, j5, j4, z, encryptionInfo);
    }

    @NotNull
    public final ByteBuffer component1() {
        return this.data;
    }

    public final long component2() {
        return this.size;
    }

    public final long component3() {
        return this.ptsMs;
    }

    public final long component4() {
        return this.durationMs;
    }

    public final boolean component5() {
        return this.isEncrypted;
    }

    @Nullable
    public final EncryptionInfo component6() {
        return this.encryptionInfo;
    }

    @NotNull
    public final AudioSample copy(@NotNull ByteBuffer data, long j, long j2, long j3, boolean z, @Nullable EncryptionInfo encryptionInfo) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new AudioSample(data, j, j2, j3, z, encryptionInfo);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioSample)) {
            return false;
        }
        AudioSample audioSample = (AudioSample) obj;
        return Intrinsics.areEqual(this.data, audioSample.data) && this.size == audioSample.size && this.ptsMs == audioSample.ptsMs && this.durationMs == audioSample.durationMs && this.isEncrypted == audioSample.isEncrypted && Intrinsics.areEqual(this.encryptionInfo, audioSample.encryptionInfo);
    }

    @Override // com.amazon.avod.mpb.api.sample.MediaSample
    @NotNull
    public ByteBuffer getData() {
        return this.data;
    }

    @Override // com.amazon.avod.mpb.api.sample.MediaSample
    public long getDurationMs() {
        return this.durationMs;
    }

    @Override // com.amazon.avod.mpb.api.sample.MediaSample
    @Nullable
    public EncryptionInfo getEncryptionInfo() {
        return this.encryptionInfo;
    }

    @Override // com.amazon.avod.mpb.api.sample.MediaSample
    public long getPtsMs() {
        return this.ptsMs;
    }

    @Override // com.amazon.avod.mpb.api.sample.MediaSample
    public long getSize() {
        return this.size;
    }

    public int hashCode() {
        int iM = (MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.isEncrypted) + ((FloatFloatPair$$ExternalSyntheticBackport0.m(this.durationMs) + ((FloatFloatPair$$ExternalSyntheticBackport0.m(this.ptsMs) + ((FloatFloatPair$$ExternalSyntheticBackport0.m(this.size) + (this.data.hashCode() * 31)) * 31)) * 31)) * 31)) * 31;
        EncryptionInfo encryptionInfo = this.encryptionInfo;
        return iM + (encryptionInfo == null ? 0 : encryptionInfo.hashCode());
    }

    @Override // com.amazon.avod.mpb.api.sample.MediaSample
    public boolean isEncrypted() {
        return this.isEncrypted;
    }

    @Override // com.amazon.avod.mpb.api.sample.MediaSample
    @NotNull
    public String toString() {
        return super.toString();
    }
}
