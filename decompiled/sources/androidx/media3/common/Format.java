package androidx.media3.common;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.core.accessibilityservice.AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0;
import androidx.media3.common.Bundleable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.Collection;
import j$.util.function.Predicate$CC;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Format implements Bundleable {

    @UnstableApi
    public static final int CUE_REPLACEMENT_BEHAVIOR_MERGE = 1;

    @UnstableApi
    public static final int CUE_REPLACEMENT_BEHAVIOR_REPLACE = 2;
    public static final int NO_VALUE = -1;

    @UnstableApi
    public static final long OFFSET_SAMPLE_RELATIVE = Long.MAX_VALUE;

    @UnstableApi
    public final int accessibilityChannel;

    @UnstableApi
    public final int averageBitrate;

    @UnstableApi
    public final int bitrate;
    public final int channelCount;

    @Nullable
    public final String codecs;

    @Nullable
    @UnstableApi
    public final ColorInfo colorInfo;

    @Nullable
    public final String containerMimeType;

    @UnstableApi
    public final int cryptoType;

    @UnstableApi
    public final int cueReplacementBehavior;

    @Nullable
    @UnstableApi
    public final DrmInitData drmInitData;

    @UnstableApi
    public final int encoderDelay;

    @UnstableApi
    public final int encoderPadding;
    public final float frameRate;
    public int hashCode;
    public final int height;

    @Nullable
    public final String id;

    @UnstableApi
    public final List<byte[]> initializationData;

    @Nullable
    public final String label;

    @UnstableApi
    public final List<Label> labels;

    @Nullable
    public final String language;

    @UnstableApi
    public final int maxInputSize;

    @Nullable
    @UnstableApi
    public final Metadata metadata;

    @UnstableApi
    public final int pcmEncoding;

    @UnstableApi
    public final int peakBitrate;
    public final float pixelWidthHeightRatio;

    @Nullable
    @UnstableApi
    public final byte[] projectionData;
    public final int roleFlags;

    @UnstableApi
    public final int rotationDegrees;

    @Nullable
    public final String sampleMimeType;
    public final int sampleRate;
    public final int selectionFlags;

    @UnstableApi
    public final int stereoMode;

    @UnstableApi
    public final long subsampleOffsetUs;

    @UnstableApi
    public final int tileCountHorizontal;

    @UnstableApi
    public final int tileCountVertical;
    public final int width;
    public static final Format DEFAULT = new Format(new Builder());
    public static final String FIELD_ID = Integer.toString(0, 36);
    public static final String FIELD_LABEL = Integer.toString(1, 36);
    public static final String FIELD_LANGUAGE = Integer.toString(2, 36);
    public static final String FIELD_SELECTION_FLAGS = Integer.toString(3, 36);
    public static final String FIELD_ROLE_FLAGS = Integer.toString(4, 36);
    public static final String FIELD_AVERAGE_BITRATE = Integer.toString(5, 36);
    public static final String FIELD_PEAK_BITRATE = Integer.toString(6, 36);
    public static final String FIELD_CODECS = Integer.toString(7, 36);
    public static final String FIELD_METADATA = Integer.toString(8, 36);
    public static final String FIELD_CONTAINER_MIME_TYPE = Integer.toString(9, 36);
    public static final String FIELD_SAMPLE_MIME_TYPE = Integer.toString(10, 36);
    public static final String FIELD_MAX_INPUT_SIZE = Integer.toString(11, 36);
    public static final String FIELD_INITIALIZATION_DATA = Integer.toString(12, 36);
    public static final String FIELD_DRM_INIT_DATA = Integer.toString(13, 36);
    public static final String FIELD_SUBSAMPLE_OFFSET_US = Integer.toString(14, 36);
    public static final String FIELD_WIDTH = Integer.toString(15, 36);
    public static final String FIELD_HEIGHT = Integer.toString(16, 36);
    public static final String FIELD_FRAME_RATE = Integer.toString(17, 36);
    public static final String FIELD_ROTATION_DEGREES = Integer.toString(18, 36);
    public static final String FIELD_PIXEL_WIDTH_HEIGHT_RATIO = Integer.toString(19, 36);
    public static final String FIELD_PROJECTION_DATA = Integer.toString(20, 36);
    public static final String FIELD_STEREO_MODE = Integer.toString(21, 36);
    public static final String FIELD_COLOR_INFO = Integer.toString(22, 36);
    public static final String FIELD_CHANNEL_COUNT = Integer.toString(23, 36);
    public static final String FIELD_SAMPLE_RATE = Integer.toString(24, 36);
    public static final String FIELD_PCM_ENCODING = Integer.toString(25, 36);
    public static final String FIELD_ENCODER_DELAY = Integer.toString(26, 36);
    public static final String FIELD_ENCODER_PADDING = Integer.toString(27, 36);
    public static final String FIELD_ACCESSIBILITY_CHANNEL = Integer.toString(28, 36);
    public static final String FIELD_CRYPTO_TYPE = Integer.toString(29, 36);
    public static final String FIELD_TILE_COUNT_HORIZONTAL = Integer.toString(30, 36);
    public static final String FIELD_TILE_COUNT_VERTICAL = Integer.toString(31, 36);
    public static final String FIELD_LABELS = Integer.toString(32, 36);

    @UnstableApi
    @Deprecated
    public static final Bundleable.Creator<Format> CREATOR = new Format$$ExternalSyntheticLambda1();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @UnstableApi
    public static final class Builder {
        public int accessibilityChannel;
        public int averageBitrate;
        public int channelCount;

        @Nullable
        public String codecs;

        @Nullable
        public ColorInfo colorInfo;

        @Nullable
        public String containerMimeType;
        public int cryptoType;

        @UnstableApi
        public int cueReplacementBehavior;

        @Nullable
        public DrmInitData drmInitData;
        public int encoderDelay;
        public int encoderPadding;
        public float frameRate;
        public int height;

        @Nullable
        public String id;

        @Nullable
        public List<byte[]> initializationData;

        @Nullable
        public String label;
        public List<Label> labels;

        @Nullable
        public String language;
        public int maxInputSize;

        @Nullable
        public Metadata metadata;
        public int pcmEncoding;
        public int peakBitrate;
        public float pixelWidthHeightRatio;

        @Nullable
        public byte[] projectionData;
        public int roleFlags;
        public int rotationDegrees;

        @Nullable
        public String sampleMimeType;
        public int sampleRate;
        public int selectionFlags;
        public int stereoMode;
        public long subsampleOffsetUs;
        public int tileCountHorizontal;
        public int tileCountVertical;
        public int width;

        public Format build() {
            return new Format(this);
        }

        @CanIgnoreReturnValue
        public Builder setAccessibilityChannel(int i) {
            this.accessibilityChannel = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setAverageBitrate(int i) {
            this.averageBitrate = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setChannelCount(int i) {
            this.channelCount = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setCodecs(@Nullable String str) {
            this.codecs = str;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setColorInfo(@Nullable ColorInfo colorInfo) {
            this.colorInfo = colorInfo;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setContainerMimeType(@Nullable String str) {
            this.containerMimeType = MimeTypes.normalizeMimeType(str);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setCryptoType(int i) {
            this.cryptoType = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setCueReplacementBehavior(int i) {
            this.cueReplacementBehavior = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setDrmInitData(@Nullable DrmInitData drmInitData) {
            this.drmInitData = drmInitData;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setEncoderDelay(int i) {
            this.encoderDelay = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setEncoderPadding(int i) {
            this.encoderPadding = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setFrameRate(float f) {
            this.frameRate = f;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setHeight(int i) {
            this.height = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setId(@Nullable String str) {
            this.id = str;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setInitializationData(@Nullable List<byte[]> list) {
            this.initializationData = list;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setLabel(@Nullable String str) {
            this.label = str;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setLabels(List<Label> list) {
            this.labels = ImmutableList.copyOf((Collection) list);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setLanguage(@Nullable String str) {
            this.language = str;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMaxInputSize(int i) {
            this.maxInputSize = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMetadata(@Nullable Metadata metadata) {
            this.metadata = metadata;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPcmEncoding(int i) {
            this.pcmEncoding = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPeakBitrate(int i) {
            this.peakBitrate = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPixelWidthHeightRatio(float f) {
            this.pixelWidthHeightRatio = f;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setProjectionData(@Nullable byte[] bArr) {
            this.projectionData = bArr;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setRoleFlags(int i) {
            this.roleFlags = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setRotationDegrees(int i) {
            this.rotationDegrees = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSampleMimeType(@Nullable String str) {
            this.sampleMimeType = MimeTypes.normalizeMimeType(str);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSampleRate(int i) {
            this.sampleRate = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSelectionFlags(int i) {
            this.selectionFlags = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setStereoMode(int i) {
            this.stereoMode = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSubsampleOffsetUs(long j) {
            this.subsampleOffsetUs = j;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTileCountHorizontal(int i) {
            this.tileCountHorizontal = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTileCountVertical(int i) {
            this.tileCountVertical = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setWidth(int i) {
            this.width = i;
            return this;
        }

        public Builder() {
            this.labels = ImmutableList.of();
            this.averageBitrate = -1;
            this.peakBitrate = -1;
            this.maxInputSize = -1;
            this.subsampleOffsetUs = Long.MAX_VALUE;
            this.width = -1;
            this.height = -1;
            this.frameRate = -1.0f;
            this.pixelWidthHeightRatio = 1.0f;
            this.stereoMode = -1;
            this.channelCount = -1;
            this.sampleRate = -1;
            this.pcmEncoding = -1;
            this.accessibilityChannel = -1;
            this.cueReplacementBehavior = 1;
            this.tileCountHorizontal = -1;
            this.tileCountVertical = -1;
            this.cryptoType = 0;
        }

        @CanIgnoreReturnValue
        public Builder setId(int i) {
            this.id = Integer.toString(i);
            return this;
        }

        public Builder(Format format) {
            this.id = format.id;
            this.label = format.label;
            this.labels = format.labels;
            this.language = format.language;
            this.selectionFlags = format.selectionFlags;
            this.roleFlags = format.roleFlags;
            this.averageBitrate = format.averageBitrate;
            this.peakBitrate = format.peakBitrate;
            this.codecs = format.codecs;
            this.metadata = format.metadata;
            this.containerMimeType = format.containerMimeType;
            this.sampleMimeType = format.sampleMimeType;
            this.maxInputSize = format.maxInputSize;
            this.initializationData = format.initializationData;
            this.drmInitData = format.drmInitData;
            this.subsampleOffsetUs = format.subsampleOffsetUs;
            this.width = format.width;
            this.height = format.height;
            this.frameRate = format.frameRate;
            this.rotationDegrees = format.rotationDegrees;
            this.pixelWidthHeightRatio = format.pixelWidthHeightRatio;
            this.projectionData = format.projectionData;
            this.stereoMode = format.stereoMode;
            this.colorInfo = format.colorInfo;
            this.channelCount = format.channelCount;
            this.sampleRate = format.sampleRate;
            this.pcmEncoding = format.pcmEncoding;
            this.encoderDelay = format.encoderDelay;
            this.encoderPadding = format.encoderPadding;
            this.accessibilityChannel = format.accessibilityChannel;
            this.cueReplacementBehavior = format.cueReplacementBehavior;
            this.tileCountHorizontal = format.tileCountHorizontal;
            this.tileCountVertical = format.tileCountVertical;
            this.cryptoType = format.cryptoType;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    @UnstableApi
    public @interface CueReplacementBehavior {
    }

    @Nullable
    public static <T> T defaultIfNull(@Nullable T t, @Nullable T t2) {
        return t != null ? t : t2;
    }

    @UnstableApi
    public static Format fromBundle(Bundle bundle) {
        Builder builder = new Builder();
        BundleCollectionUtil.ensureClassLoader(bundle);
        String string = bundle.getString(FIELD_ID);
        Format format = DEFAULT;
        String str = format.id;
        if (string == null) {
            string = str;
        }
        builder.id = string;
        String string2 = bundle.getString(FIELD_LABEL);
        String str2 = format.label;
        if (string2 == null) {
            string2 = str2;
        }
        builder.label = string2;
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_LABELS);
        builder.labels = ImmutableList.copyOf((Collection) (parcelableArrayList == null ? RegularImmutableList.EMPTY : BundleCollectionUtil.fromBundleList(new Format$$ExternalSyntheticLambda0(), parcelableArrayList)));
        String string3 = bundle.getString(FIELD_LANGUAGE);
        String str3 = format.language;
        if (string3 == null) {
            string3 = str3;
        }
        builder.language = string3;
        builder.selectionFlags = bundle.getInt(FIELD_SELECTION_FLAGS, format.selectionFlags);
        builder.roleFlags = bundle.getInt(FIELD_ROLE_FLAGS, format.roleFlags);
        builder.averageBitrate = bundle.getInt(FIELD_AVERAGE_BITRATE, format.averageBitrate);
        builder.peakBitrate = bundle.getInt(FIELD_PEAK_BITRATE, format.peakBitrate);
        String string4 = bundle.getString(FIELD_CODECS);
        String str4 = format.codecs;
        if (string4 == null) {
            string4 = str4;
        }
        builder.codecs = string4;
        Metadata metadata = (Metadata) bundle.getParcelable(FIELD_METADATA);
        Metadata metadata2 = format.metadata;
        if (metadata == null) {
            metadata = metadata2;
        }
        builder.metadata = metadata;
        String string5 = bundle.getString(FIELD_CONTAINER_MIME_TYPE);
        String str5 = format.containerMimeType;
        if (string5 == null) {
            string5 = str5;
        }
        builder.containerMimeType = MimeTypes.normalizeMimeType(string5);
        String string6 = bundle.getString(FIELD_SAMPLE_MIME_TYPE);
        String str6 = format.sampleMimeType;
        if (string6 == null) {
            string6 = str6;
        }
        builder.sampleMimeType = MimeTypes.normalizeMimeType(string6);
        builder.maxInputSize = bundle.getInt(FIELD_MAX_INPUT_SIZE, format.maxInputSize);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            byte[] byteArray = bundle.getByteArray(keyForInitializationData(i));
            if (byteArray == null) {
                break;
            }
            arrayList.add(byteArray);
            i++;
        }
        builder.initializationData = arrayList;
        builder.drmInitData = (DrmInitData) bundle.getParcelable(FIELD_DRM_INIT_DATA);
        String str7 = FIELD_SUBSAMPLE_OFFSET_US;
        Format format2 = DEFAULT;
        builder.subsampleOffsetUs = bundle.getLong(str7, format2.subsampleOffsetUs);
        builder.width = bundle.getInt(FIELD_WIDTH, format2.width);
        builder.height = bundle.getInt(FIELD_HEIGHT, format2.height);
        builder.frameRate = bundle.getFloat(FIELD_FRAME_RATE, format2.frameRate);
        builder.rotationDegrees = bundle.getInt(FIELD_ROTATION_DEGREES, format2.rotationDegrees);
        builder.pixelWidthHeightRatio = bundle.getFloat(FIELD_PIXEL_WIDTH_HEIGHT_RATIO, format2.pixelWidthHeightRatio);
        builder.projectionData = bundle.getByteArray(FIELD_PROJECTION_DATA);
        builder.stereoMode = bundle.getInt(FIELD_STEREO_MODE, format2.stereoMode);
        Bundle bundle2 = bundle.getBundle(FIELD_COLOR_INFO);
        if (bundle2 != null) {
            builder.colorInfo = ColorInfo.fromBundle(bundle2);
        }
        builder.channelCount = bundle.getInt(FIELD_CHANNEL_COUNT, format2.channelCount);
        builder.sampleRate = bundle.getInt(FIELD_SAMPLE_RATE, format2.sampleRate);
        builder.pcmEncoding = bundle.getInt(FIELD_PCM_ENCODING, format2.pcmEncoding);
        builder.encoderDelay = bundle.getInt(FIELD_ENCODER_DELAY, format2.encoderDelay);
        builder.encoderPadding = bundle.getInt(FIELD_ENCODER_PADDING, format2.encoderPadding);
        builder.accessibilityChannel = bundle.getInt(FIELD_ACCESSIBILITY_CHANNEL, format2.accessibilityChannel);
        builder.tileCountHorizontal = bundle.getInt(FIELD_TILE_COUNT_HORIZONTAL, format2.tileCountHorizontal);
        builder.tileCountVertical = bundle.getInt(FIELD_TILE_COUNT_VERTICAL, format2.tileCountVertical);
        builder.cryptoType = bundle.getInt(FIELD_CRYPTO_TYPE, format2.cryptoType);
        return new Format(builder);
    }

    public static String getDefaultLabel(List<Label> list, @Nullable String str) {
        for (Label label : list) {
            if (TextUtils.equals(label.language, str)) {
                return label.value;
            }
        }
        return list.get(0).value;
    }

    public static String keyForInitializationData(int i) {
        return FIELD_INITIALIZATION_DATA + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + Integer.toString(i, 36);
    }

    @UnstableApi
    public static String toLogString(@Nullable Format format) {
        if (format == null) {
            return AbstractJsonLexerKt.NULL;
        }
        StringBuilder sbM = AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0.m("id=");
        sbM.append(format.id);
        sbM.append(", mimeType=");
        sbM.append(format.sampleMimeType);
        if (format.containerMimeType != null) {
            sbM.append(", container=");
            sbM.append(format.containerMimeType);
        }
        if (format.bitrate != -1) {
            sbM.append(", bitrate=");
            sbM.append(format.bitrate);
        }
        if (format.codecs != null) {
            sbM.append(", codecs=");
            sbM.append(format.codecs);
        }
        if (format.drmInitData != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i = 0;
            while (true) {
                DrmInitData drmInitData = format.drmInitData;
                if (i >= drmInitData.schemeDataCount) {
                    break;
                }
                UUID uuid = drmInitData.schemeDatas[i].uuid;
                if (uuid.equals(C.COMMON_PSSH_UUID)) {
                    linkedHashSet.add("cenc");
                } else if (uuid.equals(C.CLEARKEY_UUID)) {
                    linkedHashSet.add("clearkey");
                } else if (uuid.equals(C.PLAYREADY_UUID)) {
                    linkedHashSet.add("playready");
                } else if (uuid.equals(C.WIDEVINE_UUID)) {
                    linkedHashSet.add("widevine");
                } else if (uuid.equals(C.UUID_NIL)) {
                    linkedHashSet.add("universal");
                } else {
                    linkedHashSet.add("unknown (" + uuid + ")");
                }
                i++;
            }
            sbM.append(", drm=[");
            Joiner.on(',').appendTo(sbM, linkedHashSet.iterator());
            sbM.append(AbstractJsonLexerKt.END_LIST);
        }
        if (format.width != -1 && format.height != -1) {
            sbM.append(", res=");
            sbM.append(format.width);
            sbM.append("x");
            sbM.append(format.height);
        }
        ColorInfo colorInfo = format.colorInfo;
        if (colorInfo != null && colorInfo.isValid()) {
            sbM.append(", color=");
            sbM.append(format.colorInfo.toLogString());
        }
        if (format.frameRate != -1.0f) {
            sbM.append(", fps=");
            sbM.append(format.frameRate);
        }
        if (format.channelCount != -1) {
            sbM.append(", channels=");
            sbM.append(format.channelCount);
        }
        if (format.sampleRate != -1) {
            sbM.append(", sample_rate=");
            sbM.append(format.sampleRate);
        }
        if (format.language != null) {
            sbM.append(", language=");
            sbM.append(format.language);
        }
        if (!format.labels.isEmpty()) {
            sbM.append(", labels=[");
            Joiner.on(',').appendTo(sbM, (Iterable<?>) format.labels);
            sbM.append("]");
        }
        if (format.selectionFlags != 0) {
            sbM.append(", selectionFlags=[");
            Joiner.on(',').appendTo(sbM, ((ArrayList) Util.getSelectionFlagStrings(format.selectionFlags)).iterator());
            sbM.append("]");
        }
        if (format.roleFlags != 0) {
            sbM.append(", roleFlags=[");
            Joiner.on(',').appendTo(sbM, ((ArrayList) Util.getRoleFlagStrings(format.roleFlags)).iterator());
            sbM.append("]");
        }
        return sbM.toString();
    }

    @UnstableApi
    public Builder buildUpon() {
        return new Builder(this);
    }

    @UnstableApi
    public Format copyWithCryptoType(int i) {
        Builder builder = new Builder(this);
        builder.cryptoType = i;
        return new Format(builder);
    }

    public boolean equals(@Nullable Object obj) {
        int i;
        if (this == obj) {
            return true;
        }
        if (obj != null && Format.class == obj.getClass()) {
            Format format = (Format) obj;
            int i2 = this.hashCode;
            if ((i2 == 0 || (i = format.hashCode) == 0 || i2 == i) && this.selectionFlags == format.selectionFlags && this.roleFlags == format.roleFlags && this.averageBitrate == format.averageBitrate && this.peakBitrate == format.peakBitrate && this.maxInputSize == format.maxInputSize && this.subsampleOffsetUs == format.subsampleOffsetUs && this.width == format.width && this.height == format.height && this.rotationDegrees == format.rotationDegrees && this.stereoMode == format.stereoMode && this.channelCount == format.channelCount && this.sampleRate == format.sampleRate && this.pcmEncoding == format.pcmEncoding && this.encoderDelay == format.encoderDelay && this.encoderPadding == format.encoderPadding && this.accessibilityChannel == format.accessibilityChannel && this.tileCountHorizontal == format.tileCountHorizontal && this.tileCountVertical == format.tileCountVertical && this.cryptoType == format.cryptoType && Float.compare(this.frameRate, format.frameRate) == 0 && Float.compare(this.pixelWidthHeightRatio, format.pixelWidthHeightRatio) == 0 && Util.areEqual(this.id, format.id) && Util.areEqual(this.label, format.label) && this.labels.equals(format.labels) && Util.areEqual(this.codecs, format.codecs) && Util.areEqual(this.containerMimeType, format.containerMimeType) && Util.areEqual(this.sampleMimeType, format.sampleMimeType) && Util.areEqual(this.language, format.language) && Arrays.equals(this.projectionData, format.projectionData) && Util.areEqual(this.metadata, format.metadata) && Util.areEqual(this.colorInfo, format.colorInfo) && Util.areEqual(this.drmInitData, format.drmInitData) && initializationDataEquals(format)) {
                return true;
            }
        }
        return false;
    }

    @UnstableApi
    public int getPixelCount() {
        int i;
        int i2 = this.width;
        if (i2 == -1 || (i = this.height) == -1) {
            return -1;
        }
        return i2 * i;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.id;
            int iHashCode = (527 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.label;
            int iHashCode2 = (this.labels.hashCode() + ((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31)) * 31;
            String str3 = this.language;
            int iHashCode3 = (((((((((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.selectionFlags) * 31) + this.roleFlags) * 31) + this.averageBitrate) * 31) + this.peakBitrate) * 31;
            String str4 = this.codecs;
            int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Metadata metadata = this.metadata;
            int iHashCode5 = (iHashCode4 + (metadata == null ? 0 : metadata.hashCode())) * 31;
            String str5 = this.containerMimeType;
            int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.sampleMimeType;
            this.hashCode = ((((((((((((((((((((Float.floatToIntBits(this.pixelWidthHeightRatio) + ((((Float.floatToIntBits(this.frameRate) + ((((((((((iHashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31) + this.maxInputSize) * 31) + ((int) this.subsampleOffsetUs)) * 31) + this.width) * 31) + this.height) * 31)) * 31) + this.rotationDegrees) * 31)) * 31) + this.stereoMode) * 31) + this.channelCount) * 31) + this.sampleRate) * 31) + this.pcmEncoding) * 31) + this.encoderDelay) * 31) + this.encoderPadding) * 31) + this.accessibilityChannel) * 31) + this.tileCountHorizontal) * 31) + this.tileCountVertical) * 31) + this.cryptoType;
        }
        return this.hashCode;
    }

    @UnstableApi
    public boolean initializationDataEquals(Format format) {
        if (this.initializationData.size() != format.initializationData.size()) {
            return false;
        }
        for (int i = 0; i < this.initializationData.size(); i++) {
            if (!Arrays.equals(this.initializationData.get(i), format.initializationData.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.media3.common.Bundleable
    @UnstableApi
    public Bundle toBundle() {
        return toBundle(false);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Format(");
        sb.append(this.id);
        sb.append(", ");
        sb.append(this.label);
        sb.append(", ");
        sb.append(this.containerMimeType);
        sb.append(", ");
        sb.append(this.sampleMimeType);
        sb.append(", ");
        sb.append(this.codecs);
        sb.append(", ");
        sb.append(this.bitrate);
        sb.append(", ");
        sb.append(this.language);
        sb.append(", [");
        sb.append(this.width);
        sb.append(", ");
        sb.append(this.height);
        sb.append(", ");
        sb.append(this.frameRate);
        sb.append(", ");
        sb.append(this.colorInfo);
        sb.append("], [");
        sb.append(this.channelCount);
        sb.append(", ");
        return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, this.sampleRate, "])");
    }

    @UnstableApi
    public Format withManifestFormatInfo(Format format) {
        String str;
        if (this == format) {
            return this;
        }
        int trackType = MimeTypes.getTrackType(this.sampleMimeType);
        String str2 = format.id;
        int i = format.tileCountHorizontal;
        int i2 = format.tileCountVertical;
        String str3 = format.label;
        if (str3 == null) {
            str3 = this.label;
        }
        List<Label> list = !format.labels.isEmpty() ? format.labels : this.labels;
        String str4 = this.language;
        if ((trackType == 3 || trackType == 1) && (str = format.language) != null) {
            str4 = str;
        }
        int i3 = this.averageBitrate;
        if (i3 == -1) {
            i3 = format.averageBitrate;
        }
        int i4 = this.peakBitrate;
        if (i4 == -1) {
            i4 = format.peakBitrate;
        }
        String str5 = this.codecs;
        if (str5 == null) {
            String codecsOfType = Util.getCodecsOfType(format.codecs, trackType);
            if (Util.splitCodecs(codecsOfType).length == 1) {
                str5 = codecsOfType;
            }
        }
        Metadata metadata = this.metadata;
        Metadata metadataCopyWithAppendedEntriesFrom = metadata == null ? format.metadata : metadata.copyWithAppendedEntriesFrom(format.metadata);
        float f = this.frameRate;
        if (f == -1.0f && trackType == 2) {
            f = format.frameRate;
        }
        int i5 = this.selectionFlags | format.selectionFlags;
        int i6 = this.roleFlags | format.roleFlags;
        DrmInitData drmInitDataCreateSessionCreationData = DrmInitData.createSessionCreationData(format.drmInitData, this.drmInitData);
        Builder builder = new Builder(this);
        builder.id = str2;
        builder.label = str3;
        builder.labels = ImmutableList.copyOf((Collection) list);
        builder.language = str4;
        builder.selectionFlags = i5;
        builder.roleFlags = i6;
        builder.averageBitrate = i3;
        builder.peakBitrate = i4;
        builder.codecs = str5;
        builder.metadata = metadataCopyWithAppendedEntriesFrom;
        builder.drmInitData = drmInitDataCreateSessionCreationData;
        builder.frameRate = f;
        builder.tileCountHorizontal = i;
        builder.tileCountVertical = i2;
        return new Format(builder);
    }

    public Format(final Builder builder) {
        this.id = builder.id;
        String strNormalizeLanguageCode = Util.normalizeLanguageCode(builder.language);
        this.language = strNormalizeLanguageCode;
        if (builder.labels.isEmpty() && builder.label != null) {
            this.labels = ImmutableList.of(new Label(strNormalizeLanguageCode, builder.label));
            this.label = builder.label;
        } else if (builder.labels.isEmpty() || builder.label != null) {
            Assertions.checkState((builder.labels.isEmpty() && builder.label == null) || Collection.EL.stream(builder.labels).anyMatch(new Predicate() { // from class: androidx.media3.common.Format$$ExternalSyntheticLambda3
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate$CC.$default$and(this, predicate);
                }

                public /* synthetic */ Predicate negate() {
                    return Predicate$CC.$default$negate(this);
                }

                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate$CC.$default$or(this, predicate);
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((Label) obj).value.equals(builder.label);
                }
            }));
            this.labels = builder.labels;
            this.label = builder.label;
        } else {
            List<Label> list = builder.labels;
            this.labels = list;
            this.label = getDefaultLabel(list, strNormalizeLanguageCode);
        }
        this.selectionFlags = builder.selectionFlags;
        this.roleFlags = builder.roleFlags;
        int i = builder.averageBitrate;
        this.averageBitrate = i;
        int i2 = builder.peakBitrate;
        this.peakBitrate = i2;
        this.bitrate = i2 != -1 ? i2 : i;
        this.codecs = builder.codecs;
        this.metadata = builder.metadata;
        this.containerMimeType = builder.containerMimeType;
        this.sampleMimeType = builder.sampleMimeType;
        this.maxInputSize = builder.maxInputSize;
        List<byte[]> list2 = builder.initializationData;
        this.initializationData = list2 == null ? Collections.EMPTY_LIST : list2;
        DrmInitData drmInitData = builder.drmInitData;
        this.drmInitData = drmInitData;
        this.subsampleOffsetUs = builder.subsampleOffsetUs;
        this.width = builder.width;
        this.height = builder.height;
        this.frameRate = builder.frameRate;
        int i3 = builder.rotationDegrees;
        this.rotationDegrees = i3 == -1 ? 0 : i3;
        float f = builder.pixelWidthHeightRatio;
        this.pixelWidthHeightRatio = f == -1.0f ? 1.0f : f;
        this.projectionData = builder.projectionData;
        this.stereoMode = builder.stereoMode;
        this.colorInfo = builder.colorInfo;
        this.channelCount = builder.channelCount;
        this.sampleRate = builder.sampleRate;
        this.pcmEncoding = builder.pcmEncoding;
        int i4 = builder.encoderDelay;
        this.encoderDelay = i4 == -1 ? 0 : i4;
        int i5 = builder.encoderPadding;
        this.encoderPadding = i5 != -1 ? i5 : 0;
        this.accessibilityChannel = builder.accessibilityChannel;
        this.cueReplacementBehavior = builder.cueReplacementBehavior;
        this.tileCountHorizontal = builder.tileCountHorizontal;
        this.tileCountVertical = builder.tileCountVertical;
        int i6 = builder.cryptoType;
        if (i6 != 0 || drmInitData == null) {
            this.cryptoType = i6;
        } else {
            this.cryptoType = 1;
        }
    }

    @UnstableApi
    public Bundle toBundle(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(FIELD_ID, this.id);
        bundle.putString(FIELD_LABEL, this.label);
        bundle.putParcelableArrayList(FIELD_LABELS, BundleCollectionUtil.toBundleArrayList(this.labels, new Format$$ExternalSyntheticLambda2()));
        bundle.putString(FIELD_LANGUAGE, this.language);
        bundle.putInt(FIELD_SELECTION_FLAGS, this.selectionFlags);
        bundle.putInt(FIELD_ROLE_FLAGS, this.roleFlags);
        bundle.putInt(FIELD_AVERAGE_BITRATE, this.averageBitrate);
        bundle.putInt(FIELD_PEAK_BITRATE, this.peakBitrate);
        bundle.putString(FIELD_CODECS, this.codecs);
        if (!z) {
            bundle.putParcelable(FIELD_METADATA, this.metadata);
        }
        bundle.putString(FIELD_CONTAINER_MIME_TYPE, this.containerMimeType);
        bundle.putString(FIELD_SAMPLE_MIME_TYPE, this.sampleMimeType);
        bundle.putInt(FIELD_MAX_INPUT_SIZE, this.maxInputSize);
        for (int i = 0; i < this.initializationData.size(); i++) {
            bundle.putByteArray(keyForInitializationData(i), this.initializationData.get(i));
        }
        bundle.putParcelable(FIELD_DRM_INIT_DATA, this.drmInitData);
        bundle.putLong(FIELD_SUBSAMPLE_OFFSET_US, this.subsampleOffsetUs);
        bundle.putInt(FIELD_WIDTH, this.width);
        bundle.putInt(FIELD_HEIGHT, this.height);
        bundle.putFloat(FIELD_FRAME_RATE, this.frameRate);
        bundle.putInt(FIELD_ROTATION_DEGREES, this.rotationDegrees);
        bundle.putFloat(FIELD_PIXEL_WIDTH_HEIGHT_RATIO, this.pixelWidthHeightRatio);
        bundle.putByteArray(FIELD_PROJECTION_DATA, this.projectionData);
        bundle.putInt(FIELD_STEREO_MODE, this.stereoMode);
        ColorInfo colorInfo = this.colorInfo;
        if (colorInfo != null) {
            bundle.putBundle(FIELD_COLOR_INFO, colorInfo.toBundle());
        }
        bundle.putInt(FIELD_CHANNEL_COUNT, this.channelCount);
        bundle.putInt(FIELD_SAMPLE_RATE, this.sampleRate);
        bundle.putInt(FIELD_PCM_ENCODING, this.pcmEncoding);
        bundle.putInt(FIELD_ENCODER_DELAY, this.encoderDelay);
        bundle.putInt(FIELD_ENCODER_PADDING, this.encoderPadding);
        bundle.putInt(FIELD_ACCESSIBILITY_CHANNEL, this.accessibilityChannel);
        bundle.putInt(FIELD_TILE_COUNT_HORIZONTAL, this.tileCountHorizontal);
        bundle.putInt(FIELD_TILE_COUNT_VERTICAL, this.tileCountVertical);
        bundle.putInt(FIELD_CRYPTO_TYPE, this.cryptoType);
        return bundle;
    }
}
