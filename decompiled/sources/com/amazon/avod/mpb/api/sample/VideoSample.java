package com.amazon.avod.mpb.api.sample;

import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class VideoSample extends MediaSample {

    @NotNull
    public final ByteBuffer data;
    public final long durationMs;

    @Nullable
    public final EncryptionInfo encryptionInfo;
    public final int height;
    public final boolean isEncrypted;
    public final boolean isParameterSet;
    public final long ptsMs;
    public final long size;
    public final int width;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoSample(@NotNull ByteBuffer data, long j, long j2, long j3, boolean z, @Nullable EncryptionInfo encryptionInfo, int i, int i2, boolean z2) {
        super(data, j, j2, j3, z, encryptionInfo);
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        this.size = j;
        this.ptsMs = j2;
        this.durationMs = j3;
        this.isEncrypted = z;
        this.encryptionInfo = encryptionInfo;
        this.width = i;
        this.height = i2;
        this.isParameterSet = z2;
    }

    public static /* synthetic */ VideoSample copy$default(VideoSample videoSample, ByteBuffer byteBuffer, long j, long j2, long j3, boolean z, EncryptionInfo encryptionInfo, int i, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            byteBuffer = videoSample.data;
        }
        if ((i3 & 2) != 0) {
            j = videoSample.size;
        }
        if ((i3 & 4) != 0) {
            j2 = videoSample.ptsMs;
        }
        if ((i3 & 8) != 0) {
            j3 = videoSample.durationMs;
        }
        if ((i3 & 16) != 0) {
            z = videoSample.isEncrypted;
        }
        if ((i3 & 32) != 0) {
            encryptionInfo = videoSample.encryptionInfo;
        }
        if ((i3 & 64) != 0) {
            i = videoSample.width;
        }
        if ((i3 & 128) != 0) {
            i2 = videoSample.height;
        }
        if ((i3 & 256) != 0) {
            z2 = videoSample.isParameterSet;
        }
        boolean z3 = z2;
        int i4 = i;
        boolean z4 = z;
        long j4 = j3;
        long j5 = j2;
        return videoSample.copy(byteBuffer, j, j5, j4, z4, encryptionInfo, i4, i2, z3);
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

    public final int component7() {
        return this.width;
    }

    public final int component8() {
        return this.height;
    }

    public final boolean component9() {
        return this.isParameterSet;
    }

    @NotNull
    public final VideoSample copy(@NotNull ByteBuffer data, long j, long j2, long j3, boolean z, @Nullable EncryptionInfo encryptionInfo, int i, int i2, boolean z2) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new VideoSample(data, j, j2, j3, z, encryptionInfo, i, i2, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoSample)) {
            return false;
        }
        VideoSample videoSample = (VideoSample) obj;
        return Intrinsics.areEqual(this.data, videoSample.data) && this.size == videoSample.size && this.ptsMs == videoSample.ptsMs && this.durationMs == videoSample.durationMs && this.isEncrypted == videoSample.isEncrypted && Intrinsics.areEqual(this.encryptionInfo, videoSample.encryptionInfo) && this.width == videoSample.width && this.height == videoSample.height && this.isParameterSet == videoSample.isParameterSet;
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

    public final int getHeight() {
        return this.height;
    }

    @Override // com.amazon.avod.mpb.api.sample.MediaSample
    public long getPtsMs() {
        return this.ptsMs;
    }

    @Override // com.amazon.avod.mpb.api.sample.MediaSample
    public long getSize() {
        return this.size;
    }

    public final int getWidth() {
        return this.width;
    }

    public int hashCode() {
        int iM = (MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.isEncrypted) + ((FloatFloatPair$$ExternalSyntheticBackport0.m(this.durationMs) + ((FloatFloatPair$$ExternalSyntheticBackport0.m(this.ptsMs) + ((FloatFloatPair$$ExternalSyntheticBackport0.m(this.size) + (this.data.hashCode() * 31)) * 31)) * 31)) * 31)) * 31;
        EncryptionInfo encryptionInfo = this.encryptionInfo;
        return MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.isParameterSet) + ((((((iM + (encryptionInfo == null ? 0 : encryptionInfo.hashCode())) * 31) + this.width) * 31) + this.height) * 31);
    }

    @Override // com.amazon.avod.mpb.api.sample.MediaSample
    public boolean isEncrypted() {
        return this.isEncrypted;
    }

    public final boolean isParameterSet() {
        return this.isParameterSet;
    }

    @Override // com.amazon.avod.mpb.api.sample.MediaSample
    @NotNull
    public String toString() {
        String strRemoveSuffix = StringsKt__StringsKt.removeSuffix(StringsKt__StringsKt.removePrefix(super.toString(), (CharSequence) "{"), (CharSequence) "}");
        int i = this.width;
        int i2 = this.height;
        boolean z = this.isParameterSet;
        StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("{\"width\":", i, ",\"height\":", i2, ",\"isParameterSet\":");
        sbM.append(z);
        sbM.append(",");
        sbM.append(strRemoveSuffix);
        sbM.append("}");
        return sbM.toString();
    }
}
