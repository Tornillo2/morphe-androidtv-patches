package com.amazon.avod.mpb.api.query;

import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.media.playback.util.CodecString;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class VideoCodecQueryAttributes {

    @NotNull
    public static final Factory Factory = new Factory();

    @Nullable
    public final Integer bitrate;

    @Nullable
    public final String colorGamut;

    @Nullable
    public final Float framerate;
    public final int height;

    @Nullable
    public final Integer level;

    @NotNull
    public final String mimeType;

    @Nullable
    public final Integer profile;

    @Nullable
    public final String transferFunction;
    public final int width;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory {
        public Factory() {
        }

        @NotNull
        public final VideoCodecQueryAttributes from(@NotNull String mimeType, @NotNull CodecQuery codecQuery) throws MediaPipelineBackendException {
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            Intrinsics.checkNotNullParameter(codecQuery, "codecQuery");
            int iIntValue = ((Number) CodecQueryAttributesKt.convertRequiredAttribute(codecQuery, CodecQueryAttributeKey.WIDTH, VideoCodecQueryAttributes$Factory$from$width$1.INSTANCE)).intValue();
            int iIntValue2 = ((Number) CodecQueryAttributesKt.convertRequiredAttribute(codecQuery, CodecQueryAttributeKey.HEIGHT, VideoCodecQueryAttributes$Factory$from$height$1.INSTANCE)).intValue();
            Integer num = (Integer) CodecQueryAttributesKt.convertAttribute(codecQuery, CodecQueryAttributeKey.BITRATE, VideoCodecQueryAttributes$Factory$from$bitrate$1.INSTANCE);
            Float f = (Float) CodecQueryAttributesKt.convertAttribute(codecQuery, CodecQueryAttributeKey.FRAMERATE, VideoCodecQueryAttributes$Factory$from$framerate$1.INSTANCE);
            CodecString codecString = (CodecString) CodecQueryAttributesKt.convertAttribute(codecQuery, CodecQueryAttributeKey.CODECS, VideoCodecQueryAttributes$Factory$from$codecString$1.INSTANCE);
            return new VideoCodecQueryAttributes(mimeType, iIntValue, iIntValue2, num, f, codecString != null ? codecString.getProfile() : null, codecString != null ? codecString.getLevel() : null, codecQuery.attributes.get(CodecQueryAttributeKey.TRANSFER_FUNCTION), codecQuery.attributes.get(CodecQueryAttributeKey.COLOR_GAMUT));
        }

        public Factory(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public VideoCodecQueryAttributes(@NotNull String mimeType, int i, int i2, @Nullable Integer num, @Nullable Float f, @Nullable Integer num2, @Nullable Integer num3, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        this.mimeType = mimeType;
        this.width = i;
        this.height = i2;
        this.bitrate = num;
        this.framerate = f;
        this.profile = num2;
        this.level = num3;
        this.transferFunction = str;
        this.colorGamut = str2;
    }

    public static /* synthetic */ VideoCodecQueryAttributes copy$default(VideoCodecQueryAttributes videoCodecQueryAttributes, String str, int i, int i2, Integer num, Float f, Integer num2, Integer num3, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = videoCodecQueryAttributes.mimeType;
        }
        if ((i3 & 2) != 0) {
            i = videoCodecQueryAttributes.width;
        }
        if ((i3 & 4) != 0) {
            i2 = videoCodecQueryAttributes.height;
        }
        if ((i3 & 8) != 0) {
            num = videoCodecQueryAttributes.bitrate;
        }
        if ((i3 & 16) != 0) {
            f = videoCodecQueryAttributes.framerate;
        }
        if ((i3 & 32) != 0) {
            num2 = videoCodecQueryAttributes.profile;
        }
        if ((i3 & 64) != 0) {
            num3 = videoCodecQueryAttributes.level;
        }
        if ((i3 & 128) != 0) {
            str2 = videoCodecQueryAttributes.transferFunction;
        }
        if ((i3 & 256) != 0) {
            str3 = videoCodecQueryAttributes.colorGamut;
        }
        String str4 = str2;
        String str5 = str3;
        Integer num4 = num2;
        Integer num5 = num3;
        Float f2 = f;
        int i4 = i2;
        return videoCodecQueryAttributes.copy(str, i, i4, num, f2, num4, num5, str4, str5);
    }

    @NotNull
    public final String component1() {
        return this.mimeType;
    }

    public final int component2() {
        return this.width;
    }

    public final int component3() {
        return this.height;
    }

    @Nullable
    public final Integer component4() {
        return this.bitrate;
    }

    @Nullable
    public final Float component5() {
        return this.framerate;
    }

    @Nullable
    public final Integer component6() {
        return this.profile;
    }

    @Nullable
    public final Integer component7() {
        return this.level;
    }

    @Nullable
    public final String component8() {
        return this.transferFunction;
    }

    @Nullable
    public final String component9() {
        return this.colorGamut;
    }

    @NotNull
    public final VideoCodecQueryAttributes copy(@NotNull String mimeType, int i, int i2, @Nullable Integer num, @Nullable Float f, @Nullable Integer num2, @Nullable Integer num3, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        return new VideoCodecQueryAttributes(mimeType, i, i2, num, f, num2, num3, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoCodecQueryAttributes)) {
            return false;
        }
        VideoCodecQueryAttributes videoCodecQueryAttributes = (VideoCodecQueryAttributes) obj;
        return Intrinsics.areEqual(this.mimeType, videoCodecQueryAttributes.mimeType) && this.width == videoCodecQueryAttributes.width && this.height == videoCodecQueryAttributes.height && Intrinsics.areEqual(this.bitrate, videoCodecQueryAttributes.bitrate) && Intrinsics.areEqual((Object) this.framerate, (Object) videoCodecQueryAttributes.framerate) && Intrinsics.areEqual(this.profile, videoCodecQueryAttributes.profile) && Intrinsics.areEqual(this.level, videoCodecQueryAttributes.level) && Intrinsics.areEqual(this.transferFunction, videoCodecQueryAttributes.transferFunction) && Intrinsics.areEqual(this.colorGamut, videoCodecQueryAttributes.colorGamut);
    }

    @Nullable
    public final Integer getBitrate() {
        return this.bitrate;
    }

    @Nullable
    public final String getColorGamut() {
        return this.colorGamut;
    }

    @Nullable
    public final Float getFramerate() {
        return this.framerate;
    }

    public final int getHeight() {
        return this.height;
    }

    @Nullable
    public final Integer getLevel() {
        return this.level;
    }

    @NotNull
    public final String getMimeType() {
        return this.mimeType;
    }

    @Nullable
    public final Integer getProfile() {
        return this.profile;
    }

    @Nullable
    public final String getTransferFunction() {
        return this.transferFunction;
    }

    public final int getWidth() {
        return this.width;
    }

    public int hashCode() {
        int iHashCode = ((((this.mimeType.hashCode() * 31) + this.width) * 31) + this.height) * 31;
        Integer num = this.bitrate;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Float f = this.framerate;
        int iHashCode3 = (iHashCode2 + (f == null ? 0 : f.hashCode())) * 31;
        Integer num2 = this.profile;
        int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.level;
        int iHashCode5 = (iHashCode4 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str = this.transferFunction;
        int iHashCode6 = (iHashCode5 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.colorGamut;
        return iHashCode6 + (str2 != null ? str2.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        String str = this.mimeType;
        int i = this.width;
        int i2 = this.height;
        Integer num = this.bitrate;
        Float f = this.framerate;
        Integer num2 = this.profile;
        Integer num3 = this.level;
        String str2 = this.transferFunction;
        String str3 = this.colorGamut;
        StringBuilder sb = new StringBuilder("VideoCodecQueryAttributes(mimeType=");
        sb.append(str);
        sb.append(", width=");
        sb.append(i);
        sb.append(", height=");
        sb.append(i2);
        sb.append(", bitrate=");
        sb.append(num);
        sb.append(", framerate=");
        sb.append(f);
        sb.append(", profile=");
        sb.append(num2);
        sb.append(", level=");
        sb.append(num3);
        sb.append(", transferFunction=");
        sb.append(str2);
        sb.append(", colorGamut=");
        return ActivityCompat$$ExternalSyntheticOutline0.m(sb, str3, ")");
    }

    public /* synthetic */ VideoCodecQueryAttributes(String str, int i, int i2, Integer num, Float f, Integer num2, Integer num3, String str2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, i2, (i3 & 8) != 0 ? null : num, (i3 & 16) != 0 ? null : f, (i3 & 32) != 0 ? null : num2, (i3 & 64) != 0 ? null : num3, (i3 & 128) != 0 ? null : str2, (i3 & 256) != 0 ? null : str3);
    }
}
