package androidx.media3.extractor.avi;

import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil$$ExternalSyntheticOutline0;
import com.google.common.collect.ImmutableList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class StreamFormatChunk implements AviChunk {
    public static final String TAG = "StreamFormatChunk";
    public final Format format;

    public StreamFormatChunk(Format format) {
        this.format = format;
    }

    @Nullable
    public static String getMimeTypeFromCompression(int i) {
        switch (i) {
            case 808802372:
            case 877677894:
            case 1145656883:
            case 1145656920:
            case 1482049860:
            case 1684633208:
            case 2021026148:
                return "video/mp4v-es";
            case 826496577:
            case 828601953:
            case 875967048:
                return "video/avc";
            case 842289229:
                return "video/mp42";
            case 859066445:
                return "video/mp43";
            case 1196444237:
            case 1735420525:
                return "video/mjpeg";
            default:
                return null;
        }
    }

    @Nullable
    public static String getMimeTypeFromTag(int i) {
        if (i == 1) {
            return "audio/raw";
        }
        if (i == 85) {
            return "audio/mpeg";
        }
        if (i == 255) {
            return "audio/mp4a-latm";
        }
        if (i == 8192) {
            return "audio/ac3";
        }
        if (i != 8193) {
            return null;
        }
        return "audio/vnd.dts";
    }

    @Nullable
    public static AviChunk parseBitmapInfoHeader(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        int littleEndianInt = parsableByteArray.readLittleEndianInt();
        int littleEndianInt2 = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(4);
        int littleEndianInt3 = parsableByteArray.readLittleEndianInt();
        String mimeTypeFromCompression = getMimeTypeFromCompression(littleEndianInt3);
        if (mimeTypeFromCompression == null) {
            NalUnitUtil$$ExternalSyntheticOutline0.m("Ignoring track with unsupported compression ", littleEndianInt3, "StreamFormatChunk");
            return null;
        }
        Format.Builder builder = new Format.Builder();
        builder.width = littleEndianInt;
        builder.height = littleEndianInt2;
        builder.setSampleMimeType(mimeTypeFromCompression);
        return new StreamFormatChunk(new Format(builder));
    }

    @Nullable
    public static AviChunk parseFrom(int i, ParsableByteArray parsableByteArray) {
        if (i == 2) {
            return parseBitmapInfoHeader(parsableByteArray);
        }
        if (i == 1) {
            return parseWaveFormatEx(parsableByteArray);
        }
        Log.w("StreamFormatChunk", "Ignoring strf box for unsupported track type: " + Util.getTrackTypeString(i));
        return null;
    }

    @Nullable
    public static AviChunk parseWaveFormatEx(ParsableByteArray parsableByteArray) {
        int littleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
        String mimeTypeFromTag = getMimeTypeFromTag(littleEndianUnsignedShort);
        if (mimeTypeFromTag == null) {
            NalUnitUtil$$ExternalSyntheticOutline0.m("Ignoring track with unsupported format tag ", littleEndianUnsignedShort, "StreamFormatChunk");
            return null;
        }
        int littleEndianUnsignedShort2 = parsableByteArray.readLittleEndianUnsignedShort();
        int littleEndianInt = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(6);
        int pcmEncoding = Util.getPcmEncoding(parsableByteArray.readUnsignedShort());
        int littleEndianUnsignedShort3 = parsableByteArray.readLittleEndianUnsignedShort();
        byte[] bArr = new byte[littleEndianUnsignedShort3];
        parsableByteArray.readBytes(bArr, 0, littleEndianUnsignedShort3);
        Format.Builder builder = new Format.Builder();
        builder.sampleMimeType = MimeTypes.normalizeMimeType(mimeTypeFromTag);
        builder.channelCount = littleEndianUnsignedShort2;
        builder.sampleRate = littleEndianInt;
        if ("audio/raw".equals(mimeTypeFromTag) && pcmEncoding != 0) {
            builder.pcmEncoding = pcmEncoding;
        }
        if ("audio/mp4a-latm".equals(mimeTypeFromTag) && littleEndianUnsignedShort3 > 0) {
            builder.initializationData = ImmutableList.of(bArr);
        }
        return new StreamFormatChunk(new Format(builder));
    }

    @Override // androidx.media3.extractor.avi.AviChunk
    public int getType() {
        return 1718776947;
    }
}
