package com.amazon.livingroom.deviceproperties;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.amazon.livingroom.di.ApplicationScope;
import java.util.Iterator;
import javax.inject.Inject;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.ArrayIterator;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class DecoderCapabilitiesProvider {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String MIMETYPE_VIDEO_DOLBY_VISION = "video/dolby-vision";

    @Nullable
    public DecoderCapabilities capabilities;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DecoderCapabilities {

        @JvmField
        public final boolean supportsAv1Main10L4;

        @JvmField
        public final boolean supportsAv1Main10L5;

        @JvmField
        public final boolean supportsDolbyVisionDvheStnFhd30;

        @JvmField
        public final boolean supportsDolbyVisionDvheStnUhd30;

        @JvmField
        public final boolean supportsHevcMain10L4;

        @JvmField
        public final boolean supportsHevcMain10L5;

        @JvmField
        public final boolean supportsHevcMainL4;

        @JvmField
        public final boolean supportsHevcMainL5;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {
            public boolean supportsAv1Main10L4;
            public boolean supportsAv1Main10L5;
            public boolean supportsDolbyVisionDvheStnFhd30;
            public boolean supportsDolbyVisionDvheStnUhd30;
            public boolean supportsHevcMain10L4;
            public boolean supportsHevcMain10L5;
            public boolean supportsHevcMainL4;
            public boolean supportsHevcMainL5;

            @NotNull
            public final DecoderCapabilities build() {
                return new DecoderCapabilities(this.supportsHevcMainL4, this.supportsHevcMainL5, this.supportsHevcMain10L4, this.supportsHevcMain10L5, this.supportsDolbyVisionDvheStnFhd30, this.supportsDolbyVisionDvheStnUhd30, this.supportsAv1Main10L4, this.supportsAv1Main10L5);
            }

            @NotNull
            public final Builder setSupportsAv1Main10L4(boolean z) {
                this.supportsAv1Main10L4 = z;
                return this;
            }

            @NotNull
            public final Builder setSupportsAv1Main10L5(boolean z) {
                this.supportsAv1Main10L5 = z;
                return this;
            }

            @NotNull
            public final Builder setSupportsDolbyVisionDvheStnFhd30(boolean z) {
                this.supportsDolbyVisionDvheStnFhd30 = z;
                return this;
            }

            @NotNull
            public final Builder setSupportsDolbyVisionDvheStnUhd30(boolean z) {
                this.supportsDolbyVisionDvheStnUhd30 = z;
                return this;
            }

            @NotNull
            public final Builder setSupportsHevcMain10L4(boolean z) {
                this.supportsHevcMain10L4 = z;
                return this;
            }

            @NotNull
            public final Builder setSupportsHevcMain10L5(boolean z) {
                this.supportsHevcMain10L5 = z;
                return this;
            }

            @NotNull
            public final Builder setSupportsHevcMainL4(boolean z) {
                this.supportsHevcMainL4 = z;
                return this;
            }

            @NotNull
            public final Builder setSupportsHevcMainL5(boolean z) {
                this.supportsHevcMainL5 = z;
                return this;
            }
        }

        public DecoderCapabilities(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
            this.supportsHevcMainL4 = z;
            this.supportsHevcMainL5 = z2;
            this.supportsHevcMain10L4 = z3;
            this.supportsHevcMain10L5 = z4;
            this.supportsDolbyVisionDvheStnFhd30 = z5;
            this.supportsDolbyVisionDvheStnUhd30 = z6;
            this.supportsAv1Main10L4 = z7;
            this.supportsAv1Main10L5 = z8;
        }
    }

    @Inject
    public DecoderCapabilitiesProvider() {
    }

    @RequiresApi(api = 29)
    public final void checkAv1Support(DecoderCapabilities.Builder builder, MediaCodecInfo mediaCodecInfo, DeviceProperties deviceProperties) {
        MediaCodecInfo.CodecCapabilities videoCodecCapabilities = getVideoCodecCapabilities(mediaCodecInfo, "video/av01", deviceProperties);
        if (videoCodecCapabilities == null) {
            return;
        }
        Iterator it = ArrayIteratorKt.iterator(videoCodecCapabilities.profileLevels);
        while (true) {
            ArrayIterator arrayIterator = (ArrayIterator) it;
            if (!arrayIterator.hasNext()) {
                return;
            }
            MediaCodecInfo.CodecProfileLevel codecProfileLevel = (MediaCodecInfo.CodecProfileLevel) arrayIterator.next();
            if (codecProfileLevel.profile == 2) {
                int i = codecProfileLevel.level;
                if (i >= 4096) {
                    builder.supportsAv1Main10L5 = true;
                }
                if (i >= 256) {
                    builder.supportsAv1Main10L4 = true;
                }
            }
        }
    }

    public final void checkDolbyVisionSupport(DecoderCapabilities.Builder builder, MediaCodecInfo mediaCodecInfo, DeviceProperties deviceProperties) {
        MediaCodecInfo.CodecCapabilities videoCodecCapabilities = getVideoCodecCapabilities(mediaCodecInfo, "video/dolby-vision", deviceProperties);
        if ((videoCodecCapabilities != null ? videoCodecCapabilities.profileLevels : null) == null) {
            return;
        }
        Iterator it = ArrayIteratorKt.iterator(videoCodecCapabilities.profileLevels);
        while (true) {
            ArrayIterator arrayIterator = (ArrayIterator) it;
            if (!arrayIterator.hasNext()) {
                return;
            }
            MediaCodecInfo.CodecProfileLevel codecProfileLevel = (MediaCodecInfo.CodecProfileLevel) arrayIterator.next();
            if (codecProfileLevel.profile == 32) {
                int i = codecProfileLevel.level;
                if (i >= 64) {
                    builder.supportsDolbyVisionDvheStnUhd30 = true;
                }
                if (i >= 8) {
                    builder.supportsDolbyVisionDvheStnFhd30 = true;
                }
            }
        }
    }

    public final void checkHevcSupport(DecoderCapabilities.Builder builder, MediaCodecInfo mediaCodecInfo, DeviceProperties deviceProperties) {
        MediaCodecInfo.CodecCapabilities videoCodecCapabilities = getVideoCodecCapabilities(mediaCodecInfo, "video/hevc", deviceProperties);
        if ((videoCodecCapabilities != null ? videoCodecCapabilities.profileLevels : null) == null) {
            return;
        }
        Iterator it = ArrayIteratorKt.iterator(videoCodecCapabilities.profileLevels);
        while (true) {
            ArrayIterator arrayIterator = (ArrayIterator) it;
            if (!arrayIterator.hasNext()) {
                return;
            }
            MediaCodecInfo.CodecProfileLevel codecProfileLevel = (MediaCodecInfo.CodecProfileLevel) arrayIterator.next();
            int i = codecProfileLevel.profile;
            if (i == 1) {
                int i2 = codecProfileLevel.level;
                if (i2 >= 16384) {
                    builder.supportsHevcMainL5 = true;
                }
                if (i2 >= 1024) {
                    builder.supportsHevcMainL4 = true;
                }
            } else if (i == 2) {
                int i3 = codecProfileLevel.level;
                if (i3 >= 16384) {
                    builder.supportsHevcMain10L5 = true;
                }
                if (i3 >= 1024) {
                    builder.supportsHevcMain10L4 = true;
                }
            }
        }
    }

    @Nullable
    public final DecoderCapabilities getCapabilities() {
        return this.capabilities;
    }

    @NotNull
    public final synchronized DecoderCapabilities getDecoderCapabilities(@NotNull DeviceProperties otherProperties) {
        DecoderCapabilities decoderCapabilities;
        try {
            Intrinsics.checkNotNullParameter(otherProperties, "otherProperties");
            if (this.capabilities == null) {
                this.capabilities = initCapabilities(otherProperties);
            }
            decoderCapabilities = this.capabilities;
            Intrinsics.checkNotNull(decoderCapabilities);
        } catch (Throwable th) {
            throw th;
        }
        return decoderCapabilities;
    }

    public final MediaCodecInfo.CodecCapabilities getVideoCodecCapabilities(MediaCodecInfo mediaCodecInfo, String str, DeviceProperties deviceProperties) {
        try {
            MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
            if (capabilitiesForType != null && capabilitiesForType.isFeatureSupported("adaptive-playback") && capabilitiesForType.isFeatureSupported("secure-playback") && (((Boolean) deviceProperties.get(DeviceProperties.TUNNELED_VIDEO_PLAYBACK_ENABLED)).booleanValue() || !capabilitiesForType.isFeatureRequired("tunneled-playback"))) {
                return capabilitiesForType;
            }
            return null;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public final DecoderCapabilities initCapabilities(DeviceProperties deviceProperties) {
        DecoderCapabilities.Builder builder = new DecoderCapabilities.Builder();
        MediaCodecInfo[] codecInfos = new MediaCodecList(1).getCodecInfos();
        if (codecInfos != null) {
            Iterator it = ArrayIteratorKt.iterator(codecInfos);
            while (true) {
                ArrayIterator arrayIterator = (ArrayIterator) it;
                if (!arrayIterator.hasNext()) {
                    break;
                }
                MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) arrayIterator.next();
                if (!mediaCodecInfo.isEncoder()) {
                    checkHevcSupport(builder, mediaCodecInfo, deviceProperties);
                    checkDolbyVisionSupport(builder, mediaCodecInfo, deviceProperties);
                    if (Build.VERSION.SDK_INT >= 29) {
                        checkAv1Support(builder, mediaCodecInfo, deviceProperties);
                    }
                }
            }
        }
        return builder.build();
    }

    public final void setCapabilities(@Nullable DecoderCapabilities decoderCapabilities) {
        this.capabilities = decoderCapabilities;
    }
}
