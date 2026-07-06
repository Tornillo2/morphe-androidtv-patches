package androidx.media3.common.util;

import android.annotation.SuppressLint;
import android.media.MediaFormat;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.annotation.Nullable;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class MediaFormatUtil {
    public static final String KEY_MAX_BIT_RATE = "max-bitrate";
    public static final String KEY_PCM_ENCODING_EXTENDED = "exo-pcm-encoding-int";
    public static final String KEY_PIXEL_WIDTH_HEIGHT_RATIO_FLOAT = "exo-pixel-width-height-ratio-float";
    public static final int MAX_POWER_OF_TWO_INT = 1073741824;

    @SuppressLint({"InlinedApi"})
    public static Format createFormatFromMediaFormat(MediaFormat mediaFormat) {
        Format.Builder builder = new Format.Builder();
        builder.sampleMimeType = MimeTypes.normalizeMimeType(mediaFormat.getString("mime"));
        builder.language = mediaFormat.getString("language");
        builder.peakBitrate = mediaFormat.containsKey("max-bitrate") ? mediaFormat.getInteger("max-bitrate") : -1;
        builder.averageBitrate = mediaFormat.containsKey("bitrate") ? mediaFormat.getInteger("bitrate") : -1;
        builder.codecs = mediaFormat.getString("codecs-string");
        builder.frameRate = getFrameRate(mediaFormat, -1.0f);
        builder.width = mediaFormat.containsKey("width") ? mediaFormat.getInteger("width") : -1;
        builder.height = mediaFormat.containsKey("height") ? mediaFormat.getInteger("height") : -1;
        builder.pixelWidthHeightRatio = getPixelWidthHeightRatio(mediaFormat, 1.0f);
        builder.maxInputSize = mediaFormat.containsKey("max-input-size") ? mediaFormat.getInteger("max-input-size") : -1;
        int i = 0;
        builder.rotationDegrees = mediaFormat.containsKey("rotation-degrees") ? mediaFormat.getInteger("rotation-degrees") : 0;
        builder.colorInfo = getColorInfo(mediaFormat, true);
        builder.sampleRate = mediaFormat.containsKey("sample-rate") ? mediaFormat.getInteger("sample-rate") : -1;
        builder.channelCount = mediaFormat.containsKey("channel-count") ? mediaFormat.getInteger("channel-count") : -1;
        builder.pcmEncoding = mediaFormat.containsKey("pcm-encoding") ? mediaFormat.getInteger("pcm-encoding") : -1;
        ImmutableList.Builder builder2 = new ImmutableList.Builder(4);
        while (true) {
            ByteBuffer byteBuffer = mediaFormat.getByteBuffer("csd-" + i);
            if (byteBuffer == null) {
                builder.initializationData = builder2.build();
                return new Format(builder);
            }
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            byteBuffer.rewind();
            builder2.add(bArr);
            i++;
        }
    }

    @SuppressLint({"InlinedApi"})
    public static MediaFormat createMediaFormatFromFormat(Format format) {
        MediaFormat mediaFormat = new MediaFormat();
        maybeSetInteger(mediaFormat, "bitrate", format.bitrate);
        maybeSetInteger(mediaFormat, "max-bitrate", format.peakBitrate);
        maybeSetInteger(mediaFormat, "channel-count", format.channelCount);
        maybeSetColorInfo(mediaFormat, format.colorInfo);
        maybeSetString(mediaFormat, "mime", format.sampleMimeType);
        maybeSetString(mediaFormat, "codecs-string", format.codecs);
        maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        maybeSetInteger(mediaFormat, "width", format.width);
        maybeSetInteger(mediaFormat, "height", format.height);
        setCsdBuffers(mediaFormat, format.initializationData);
        maybeSetPcmEncoding(mediaFormat, format.pcmEncoding);
        maybeSetString(mediaFormat, "language", format.language);
        maybeSetInteger(mediaFormat, "max-input-size", format.maxInputSize);
        maybeSetInteger(mediaFormat, "sample-rate", format.sampleRate);
        maybeSetInteger(mediaFormat, "caption-service-number", format.accessibilityChannel);
        mediaFormat.setInteger("rotation-degrees", format.rotationDegrees);
        int i = format.selectionFlags;
        setBooleanAsInt(mediaFormat, "is-autoselect", i & 4);
        setBooleanAsInt(mediaFormat, "is-default", i & 1);
        setBooleanAsInt(mediaFormat, "is-forced-subtitle", i & 2);
        mediaFormat.setInteger("encoder-delay", format.encoderDelay);
        mediaFormat.setInteger("encoder-padding", format.encoderPadding);
        maybeSetPixelAspectRatio(mediaFormat, format.pixelWidthHeightRatio);
        return mediaFormat;
    }

    public static byte[] getArray(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return bArr;
    }

    @Nullable
    public static ColorInfo getColorInfo(MediaFormat mediaFormat) {
        return getColorInfo(mediaFormat, false);
    }

    public static float getFloat(MediaFormat mediaFormat, String str, float f) {
        return mediaFormat.containsKey(str) ? mediaFormat.getFloat(str) : f;
    }

    public static float getFrameRate(MediaFormat mediaFormat, float f) {
        if (!mediaFormat.containsKey("frame-rate")) {
            return f;
        }
        try {
            return mediaFormat.getFloat("frame-rate");
        } catch (ClassCastException unused) {
            return mediaFormat.getInteger("frame-rate");
        }
    }

    public static int getInteger(MediaFormat mediaFormat, String str, int i) {
        return mediaFormat.containsKey(str) ? mediaFormat.getInteger(str) : i;
    }

    @SuppressLint({"InlinedApi"})
    public static float getPixelWidthHeightRatio(MediaFormat mediaFormat, float f) {
        return (mediaFormat.containsKey("sar-width") && mediaFormat.containsKey("sar-height")) ? mediaFormat.getInteger("sar-width") / mediaFormat.getInteger("sar-height") : f;
    }

    @Nullable
    public static Integer getTimeLapseFrameRate(MediaFormat mediaFormat) {
        if (mediaFormat.containsKey("time-lapse-enable") && mediaFormat.getInteger("time-lapse-enable") > 0 && mediaFormat.containsKey("time-lapse-fps")) {
            return Integer.valueOf(mediaFormat.getInteger("time-lapse-fps"));
        }
        return null;
    }

    public static boolean isAudioFormat(MediaFormat mediaFormat) {
        return MimeTypes.isAudio(mediaFormat.getString("mime"));
    }

    public static boolean isValidColorRange(int i) {
        return i == 2 || i == 1 || i == -1;
    }

    public static boolean isValidColorSpace(int i) {
        return i == 2 || i == 1 || i == 6 || i == -1;
    }

    public static boolean isValidColorTransfer(int i) {
        return i == 1 || i == 3 || i == 6 || i == 7 || i == -1;
    }

    public static boolean isVideoFormat(MediaFormat mediaFormat) {
        return MimeTypes.isVideo(mediaFormat.getString("mime"));
    }

    public static void maybeSetByteBuffer(MediaFormat mediaFormat, String str, @Nullable byte[] bArr) {
        if (bArr != null) {
            mediaFormat.setByteBuffer(str, ByteBuffer.wrap(bArr));
        }
    }

    public static void maybeSetColorInfo(MediaFormat mediaFormat, @Nullable ColorInfo colorInfo) {
        if (colorInfo != null) {
            maybeSetInteger(mediaFormat, "color-transfer", colorInfo.colorTransfer);
            maybeSetInteger(mediaFormat, "color-standard", colorInfo.colorSpace);
            maybeSetInteger(mediaFormat, "color-range", colorInfo.colorRange);
            maybeSetByteBuffer(mediaFormat, "hdr-static-info", colorInfo.hdrStaticInfo);
        }
    }

    public static void maybeSetFloat(MediaFormat mediaFormat, String str, float f) {
        if (f != -1.0f) {
            mediaFormat.setFloat(str, f);
        }
    }

    public static void maybeSetInteger(MediaFormat mediaFormat, String str, int i) {
        if (i != -1) {
            mediaFormat.setInteger(str, i);
        }
    }

    @SuppressLint({"InlinedApi"})
    public static void maybeSetPcmEncoding(MediaFormat mediaFormat, int i) {
        int i2;
        if (i == -1) {
            return;
        }
        maybeSetInteger(mediaFormat, "exo-pcm-encoding-int", i);
        if (i != 0) {
            i2 = 2;
            if (i != 2) {
                i2 = 3;
                if (i != 3) {
                    i2 = 4;
                    if (i != 4) {
                        i2 = 21;
                        if (i != 21) {
                            i2 = 22;
                            if (i != 22) {
                                return;
                            }
                        }
                    }
                }
            }
        } else {
            i2 = 0;
        }
        mediaFormat.setInteger("pcm-encoding", i2);
    }

    @SuppressLint({"InlinedApi"})
    public static void maybeSetPixelAspectRatio(MediaFormat mediaFormat, float f) {
        int i;
        mediaFormat.setFloat("exo-pixel-width-height-ratio-float", f);
        int i2 = 1073741824;
        if (f < 1.0f) {
            i2 = (int) (f * 1073741824);
            i = 1073741824;
        } else if (f > 1.0f) {
            i = (int) (1073741824 / f);
        } else {
            i2 = 1;
            i = 1;
        }
        mediaFormat.setInteger("sar-width", i2);
        mediaFormat.setInteger("sar-height", i);
    }

    public static void maybeSetString(MediaFormat mediaFormat, String str, @Nullable String str2) {
        if (str2 != null) {
            mediaFormat.setString(str, str2);
        }
    }

    public static void setBooleanAsInt(MediaFormat mediaFormat, String str, int i) {
        mediaFormat.setInteger(str, i != 0 ? 1 : 0);
    }

    public static void setCsdBuffers(MediaFormat mediaFormat, List<byte[]> list) {
        for (int i = 0; i < list.size(); i++) {
            mediaFormat.setByteBuffer(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("csd-", i), ByteBuffer.wrap(list.get(i)));
        }
    }

    @Nullable
    public static ColorInfo getColorInfo(MediaFormat mediaFormat, boolean z) {
        byte[] bArr;
        if (Util.SDK_INT < 24) {
            return null;
        }
        int integer = getInteger(mediaFormat, "color-standard", -1);
        int integer2 = mediaFormat.containsKey("color-range") ? mediaFormat.getInteger("color-range") : -1;
        int integer3 = mediaFormat.containsKey("color-transfer") ? mediaFormat.getInteger("color-transfer") : -1;
        ByteBuffer byteBuffer = mediaFormat.getByteBuffer("hdr-static-info");
        if (byteBuffer != null) {
            bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
        } else {
            bArr = null;
        }
        if (!z) {
            if (!isValidColorSpace(integer)) {
                integer = -1;
            }
            if (!isValidColorRange(integer2)) {
                integer2 = -1;
            }
            if (!isValidColorTransfer(integer3)) {
                integer3 = -1;
            }
        }
        if (integer == -1 && integer2 == -1 && integer3 == -1 && bArr == null) {
            return null;
        }
        ColorInfo.Builder builder = new ColorInfo.Builder();
        builder.colorSpace = integer;
        builder.colorRange = integer2;
        builder.colorTransfer = integer3;
        builder.hdrStaticInfo = bArr;
        return builder.build();
    }
}
