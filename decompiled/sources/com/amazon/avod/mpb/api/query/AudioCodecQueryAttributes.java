package com.amazon.avod.mpb.api.query;

import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.amazon.avod.mpb.media.playback.util.CodecString;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AudioCodecQueryAttributes {

    @NotNull
    public static final Factory Factory = new Factory();

    @Nullable
    public final Integer bitrate;
    public final int channels;

    @Nullable
    public final Integer level;

    @NotNull
    public final String mimeType;

    @Nullable
    public final Integer profile;
    public final int samplerate;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nCodecQueryAttributes.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CodecQueryAttributes.kt\ncom/amazon/avod/mpb/api/query/AudioCodecQueryAttributes$Factory\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,112:1\n1#2:113\n*E\n"})
    public static final class Factory {
        public Factory() {
        }

        public static final int from$lambda$1(String channelConfig) {
            Intrinsics.checkNotNullParameter(channelConfig, "channelConfig");
            Iterator it = StringsKt__StringsKt.split$default((CharSequence) channelConfig, new String[]{ExternalFourCCMapper.CODEC_NAME_SPLITTER}, false, 2, 2, (Object) null).iterator();
            int i = 0;
            while (it.hasNext()) {
                i += Integer.parseInt((String) it.next());
            }
            return i;
        }

        @NotNull
        public final AudioCodecQueryAttributes from(@NotNull String mimeType, @NotNull CodecQuery codecQuery) throws MediaPipelineBackendException {
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            Intrinsics.checkNotNullParameter(codecQuery, "codecQuery");
            int iIntValue = ((Number) CodecQueryAttributesKt.convertRequiredAttribute(codecQuery, CodecQueryAttributeKey.SAMPLERATE, AudioCodecQueryAttributes$Factory$from$samplerate$1.INSTANCE)).intValue();
            int iIntValue2 = ((Number) CodecQueryAttributesKt.convertRequiredAttribute(codecQuery, CodecQueryAttributeKey.CHANNELS, new AudioCodecQueryAttributes$Factory$$ExternalSyntheticLambda0())).intValue();
            Integer num = (Integer) CodecQueryAttributesKt.convertAttribute(codecQuery, CodecQueryAttributeKey.BITRATE, AudioCodecQueryAttributes$Factory$from$bitrate$1.INSTANCE);
            CodecString codecString = (CodecString) CodecQueryAttributesKt.convertAttribute(codecQuery, CodecQueryAttributeKey.CODECS, AudioCodecQueryAttributes$Factory$from$codecString$1.INSTANCE);
            return new AudioCodecQueryAttributes(mimeType, iIntValue, iIntValue2, num, codecString != null ? codecString.getProfile() : null, codecString != null ? codecString.getLevel() : null);
        }

        public Factory(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public AudioCodecQueryAttributes(@NotNull String mimeType, int i, int i2, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        this.mimeType = mimeType;
        this.samplerate = i;
        this.channels = i2;
        this.bitrate = num;
        this.profile = num2;
        this.level = num3;
    }

    public static /* synthetic */ AudioCodecQueryAttributes copy$default(AudioCodecQueryAttributes audioCodecQueryAttributes, String str, int i, int i2, Integer num, Integer num2, Integer num3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = audioCodecQueryAttributes.mimeType;
        }
        if ((i3 & 2) != 0) {
            i = audioCodecQueryAttributes.samplerate;
        }
        if ((i3 & 4) != 0) {
            i2 = audioCodecQueryAttributes.channels;
        }
        if ((i3 & 8) != 0) {
            num = audioCodecQueryAttributes.bitrate;
        }
        if ((i3 & 16) != 0) {
            num2 = audioCodecQueryAttributes.profile;
        }
        if ((i3 & 32) != 0) {
            num3 = audioCodecQueryAttributes.level;
        }
        Integer num4 = num2;
        Integer num5 = num3;
        return audioCodecQueryAttributes.copy(str, i, i2, num, num4, num5);
    }

    @NotNull
    public final String component1() {
        return this.mimeType;
    }

    public final int component2() {
        return this.samplerate;
    }

    public final int component3() {
        return this.channels;
    }

    @Nullable
    public final Integer component4() {
        return this.bitrate;
    }

    @Nullable
    public final Integer component5() {
        return this.profile;
    }

    @Nullable
    public final Integer component6() {
        return this.level;
    }

    @NotNull
    public final AudioCodecQueryAttributes copy(@NotNull String mimeType, int i, int i2, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        return new AudioCodecQueryAttributes(mimeType, i, i2, num, num2, num3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioCodecQueryAttributes)) {
            return false;
        }
        AudioCodecQueryAttributes audioCodecQueryAttributes = (AudioCodecQueryAttributes) obj;
        return Intrinsics.areEqual(this.mimeType, audioCodecQueryAttributes.mimeType) && this.samplerate == audioCodecQueryAttributes.samplerate && this.channels == audioCodecQueryAttributes.channels && Intrinsics.areEqual(this.bitrate, audioCodecQueryAttributes.bitrate) && Intrinsics.areEqual(this.profile, audioCodecQueryAttributes.profile) && Intrinsics.areEqual(this.level, audioCodecQueryAttributes.level);
    }

    @Nullable
    public final Integer getBitrate() {
        return this.bitrate;
    }

    public final int getChannels() {
        return this.channels;
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

    public final int getSamplerate() {
        return this.samplerate;
    }

    public int hashCode() {
        int iHashCode = ((((this.mimeType.hashCode() * 31) + this.samplerate) * 31) + this.channels) * 31;
        Integer num = this.bitrate;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.profile;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.level;
        return iHashCode3 + (num3 != null ? num3.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "AudioCodecQueryAttributes(mimeType=" + this.mimeType + ", samplerate=" + this.samplerate + ", channels=" + this.channels + ", bitrate=" + this.bitrate + ", profile=" + this.profile + ", level=" + this.level + ")";
    }

    public /* synthetic */ AudioCodecQueryAttributes(String str, int i, int i2, Integer num, Integer num2, Integer num3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, i2, (i3 & 8) != 0 ? null : num, (i3 & 16) != 0 ? null : num2, (i3 & 32) != 0 ? null : num3);
    }
}
