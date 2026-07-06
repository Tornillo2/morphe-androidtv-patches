package com.amazon.avod.mpb.api.sample;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import com.google.common.io.BaseEncoding;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nBufferedMediaSample.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BufferedMediaSample.kt\ncom/amazon/avod/mpb/api/sample/BufferedMediaSample\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,41:1\n1#2:42\n*E\n"})
public final class BufferedMediaSample {

    @Nullable
    public final byte[] codecData;
    public final int flags;

    @NotNull
    public final MediaSample mediaSample;

    public BufferedMediaSample(@NotNull MediaSample mediaSample, @Nullable byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(mediaSample, "mediaSample");
        this.mediaSample = mediaSample;
        this.codecData = bArr;
        this.flags = i;
    }

    public static /* synthetic */ BufferedMediaSample copy$default(BufferedMediaSample bufferedMediaSample, MediaSample mediaSample, byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            mediaSample = bufferedMediaSample.mediaSample;
        }
        if ((i2 & 2) != 0) {
            bArr = bufferedMediaSample.codecData;
        }
        if ((i2 & 4) != 0) {
            i = bufferedMediaSample.flags;
        }
        return bufferedMediaSample.copy(mediaSample, bArr, i);
    }

    @NotNull
    public final MediaSample component1() {
        return this.mediaSample;
    }

    @Nullable
    public final byte[] component2() {
        return this.codecData;
    }

    public final int component3() {
        return this.flags;
    }

    @NotNull
    public final BufferedMediaSample copy(@NotNull MediaSample mediaSample, @Nullable byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(mediaSample, "mediaSample");
        return new BufferedMediaSample(mediaSample, bArr, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!BufferedMediaSample.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.amazon.avod.mpb.api.sample.BufferedMediaSample");
        BufferedMediaSample bufferedMediaSample = (BufferedMediaSample) obj;
        if (!Intrinsics.areEqual(this.mediaSample, bufferedMediaSample.mediaSample)) {
            return false;
        }
        byte[] bArr = this.codecData;
        if (bArr != null) {
            byte[] bArr2 = bufferedMediaSample.codecData;
            if (bArr2 == null || !Arrays.equals(bArr, bArr2)) {
                return false;
            }
        } else if (bufferedMediaSample.codecData != null) {
            return false;
        }
        return this.flags == bufferedMediaSample.flags;
    }

    @Nullable
    public final byte[] getCodecData() {
        return this.codecData;
    }

    public final int getFlags() {
        return this.flags;
    }

    @NotNull
    public final MediaSample getMediaSample() {
        return this.mediaSample;
    }

    public int hashCode() {
        int iHashCode = this.mediaSample.hashCode() * 31;
        byte[] bArr = this.codecData;
        return ((iHashCode + (bArr != null ? Arrays.hashCode(bArr) : 0)) * 31) + this.flags;
    }

    @NotNull
    public String toString() {
        String strM;
        byte[] bArr = this.codecData;
        if (bArr == null || (strM = MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE, BaseEncoding.base16().upperCase().encode(bArr), ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE)) == null) {
            strM = AbstractJsonLexerKt.NULL;
        }
        MediaSample mediaSample = this.mediaSample;
        int i = this.flags;
        StringBuilder sb = new StringBuilder("{\"mediaSample\":");
        sb.append(mediaSample);
        sb.append(",\"codecData\":");
        sb.append(strM);
        sb.append(",\"flags\":");
        return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, i, "}");
    }
}
