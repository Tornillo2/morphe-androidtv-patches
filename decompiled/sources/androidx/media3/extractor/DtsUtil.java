package androidx.media3.extractor;

import androidx.annotation.Nullable;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import com.amazon.avod.mpb.api.query.MediaCodecQuerier;
import com.amazon.livingroom.deviceproperties.VideoCapabilitiesProvider;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class DtsUtil {
    public static final int DTS_EXPRESS_MAX_RATE_BITS_PER_SECOND = 768000;
    public static final int DTS_HD_MAX_RATE_BYTES_PER_SECOND = 2250000;
    public static final int DTS_MAX_RATE_BYTES_PER_SECOND = 192000;
    public static final byte FIRST_BYTE_14B_BE = 31;
    public static final byte FIRST_BYTE_14B_LE = -1;
    public static final byte FIRST_BYTE_BE = 127;
    public static final byte FIRST_BYTE_EXTSS_BE = 100;
    public static final byte FIRST_BYTE_EXTSS_LE = 37;
    public static final byte FIRST_BYTE_LE = -2;
    public static final byte FIRST_BYTE_UHD_FTOC_NONSYNC_BE = 113;
    public static final byte FIRST_BYTE_UHD_FTOC_NONSYNC_LE = -24;
    public static final byte FIRST_BYTE_UHD_FTOC_SYNC_BE = 64;
    public static final byte FIRST_BYTE_UHD_FTOC_SYNC_LE = -14;
    public static final int FRAME_TYPE_CORE = 1;
    public static final int FRAME_TYPE_EXTENSION_SUBSTREAM = 2;
    public static final int FRAME_TYPE_UHD_NON_SYNC = 4;
    public static final int FRAME_TYPE_UHD_SYNC = 3;
    public static final int FRAME_TYPE_UNKNOWN = 0;
    public static final int SYNC_VALUE_14B_BE = 536864768;
    public static final int SYNC_VALUE_14B_LE = -14745368;
    public static final int SYNC_VALUE_BE = 2147385345;
    public static final int SYNC_VALUE_EXTSS_BE = 1683496997;
    public static final int SYNC_VALUE_EXTSS_LE = 622876772;
    public static final int SYNC_VALUE_LE = -25230976;
    public static final int SYNC_VALUE_UHD_FTOC_NONSYNC_BE = 1908687592;
    public static final int SYNC_VALUE_UHD_FTOC_NONSYNC_LE = -398277519;
    public static final int SYNC_VALUE_UHD_FTOC_SYNC_BE = 1078008818;
    public static final int SYNC_VALUE_UHD_FTOC_SYNC_LE = -233094848;
    public static final int[] CHANNELS_BY_AMODE = {1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    public static final int[] SAMPLE_RATE_BY_SFREQ = {-1, 8000, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
    public static final int[] TWICE_BITRATE_KBPS_BY_RATE = {64, 112, 128, 192, 224, 256, 384, 448, 512, 640, 768, 896, 1024, 1152, MediaCodecQuerier.HD_MIN_WIDTH, 1536, VideoCapabilitiesProvider.FULL_HD_WIDTH, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};
    public static final int[] SAMPLE_RATE_BY_INDEX = {8000, 16000, 32000, 64000, 128000, 22050, 44100, 88200, 176400, 352800, 12000, 24000, 48000, 96000, 192000, 384000};
    public static final int[] UHD_FTOC_PAYLOAD_LENGTH_TABLE = {5, 8, 10, 12};
    public static final int[] UHD_METADATA_CHUNK_SIZE_LENGTH_TABLE = {6, 9, 12, 15};
    public static final int[] UHD_AUDIO_CHUNK_ID_LENGTH_TABLE = {2, 4, 6, 8};
    public static final int[] UHD_AUDIO_CHUNK_SIZE_LENGTH_TABLE = {9, 11, 13, 16};
    public static final int[] UHD_HEADER_SIZE_LENGTH_TABLE = {5, 8, 10, 12};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface DtsAudioMimeType {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DtsHeader {
        public final int bitrate;
        public final int channelCount;
        public final long frameDurationUs;
        public final int frameSize;
        public final String mimeType;
        public final int sampleRate;

        public DtsHeader(String str, int i, int i2, int i3, long j, int i4) {
            this.mimeType = str;
            this.channelCount = i;
            this.sampleRate = i2;
            this.frameSize = i3;
            this.frameDurationUs = j;
            this.bitrate = i4;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface FrameType {
    }

    public static void checkCrc(byte[] bArr, int i) throws ParserException {
        int i2 = i - 2;
        if (((bArr[i - 1] & 255) | ((bArr[i2] << 8) & 65535)) != Util.crc16(bArr, 0, i2, 65535)) {
            throw ParserException.createForMalformedContainer("CRC check failed", null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getDtsFrameSize(byte[] r7) {
        /*
            r0 = 0
            r1 = r7[r0]
            r2 = -2
            r3 = 7
            r4 = 6
            r5 = 1
            r6 = 4
            if (r1 == r2) goto L4f
            r2 = -1
            if (r1 == r2) goto L3e
            r2 = 31
            if (r1 == r2) goto L26
            r1 = 5
            r1 = r7[r1]
            r1 = r1 & 3
            int r1 = r1 << 12
            r2 = r7[r4]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r6
            r1 = r1 | r2
            r7 = r7[r3]
        L20:
            r7 = r7 & 240(0xf0, float:3.36E-43)
            int r7 = r7 >> r6
            r7 = r7 | r1
            int r7 = r7 + r5
            goto L5e
        L26:
            r0 = r7[r4]
            r0 = r0 & 3
            int r0 = r0 << 12
            r1 = r7[r3]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r6
            r0 = r0 | r1
            r1 = 8
            r7 = r7[r1]
        L36:
            r7 = r7 & 60
            int r7 = r7 >> 2
            r7 = r7 | r0
            int r7 = r7 + r5
            r0 = 1
            goto L5e
        L3e:
            r0 = r7[r3]
            r0 = r0 & 3
            int r0 = r0 << 12
            r1 = r7[r4]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r6
            r0 = r0 | r1
            r1 = 9
            r7 = r7[r1]
            goto L36
        L4f:
            r1 = r7[r6]
            r1 = r1 & 3
            int r1 = r1 << 12
            r2 = r7[r3]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r6
            r1 = r1 | r2
            r7 = r7[r4]
            goto L20
        L5e:
            if (r0 == 0) goto L64
            int r7 = r7 * 16
            int r7 = r7 / 14
        L64:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.DtsUtil.getDtsFrameSize(byte[]):int");
    }

    public static int getFrameType(int i) {
        if (i == 2147385345 || i == -25230976 || i == 536864768 || i == -14745368) {
            return 1;
        }
        if (i == 1683496997 || i == 622876772) {
            return 2;
        }
        if (i == 1078008818 || i == -233094848) {
            return 3;
        }
        return (i == 1908687592 || i == -398277519) ? 4 : 0;
    }

    public static ParsableBitArray getNormalizedFrame(byte[] bArr) {
        byte b = bArr[0];
        if (b == 127 || b == 100 || b == 64 || b == 113) {
            return new ParsableBitArray(bArr, bArr.length);
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        if (isLittleEndianFrameHeader(bArrCopyOf)) {
            for (int i = 0; i < bArrCopyOf.length - 1; i += 2) {
                byte b2 = bArrCopyOf[i];
                int i2 = i + 1;
                bArrCopyOf[i] = bArrCopyOf[i2];
                bArrCopyOf[i2] = b2;
            }
        }
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArrCopyOf, bArrCopyOf.length);
        if (bArrCopyOf[0] == 31) {
            ParsableBitArray parsableBitArray2 = new ParsableBitArray(bArrCopyOf, bArrCopyOf.length);
            while (parsableBitArray2.bitsLeft() >= 16) {
                parsableBitArray2.skipBits(2);
                parsableBitArray.putInt(parsableBitArray2.readBits(14), 14);
            }
        }
        parsableBitArray.reset(bArrCopyOf, bArrCopyOf.length);
        return parsableBitArray;
    }

    public static boolean isLittleEndianFrameHeader(byte[] bArr) {
        byte b = bArr[0];
        return b == -2 || b == -1 || b == 37 || b == -14 || b == -24;
    }

    public static int parseDtsAudioSampleCount(byte[] bArr) {
        int i;
        byte b;
        int i2;
        byte b2;
        byte b3 = bArr[0];
        if (b3 != -2) {
            if (b3 == -1) {
                i = (bArr[4] & 7) << 4;
                b2 = bArr[7];
            } else if (b3 != 31) {
                i = (bArr[4] & 1) << 6;
                b = bArr[5];
            } else {
                i = (bArr[5] & 7) << 4;
                b2 = bArr[6];
            }
            i2 = b2 & 60;
            return (((i2 >> 2) | i) + 1) * 32;
        }
        i = (bArr[5] & 1) << 6;
        b = bArr[4];
        i2 = b & 252;
        return (((i2 >> 2) | i) + 1) * 32;
    }

    public static Format parseDtsFormat(byte[] bArr, @Nullable String str, @Nullable String str2, int i, @Nullable DrmInitData drmInitData) {
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(60);
        int i2 = CHANNELS_BY_AMODE[normalizedFrame.readBits(6)];
        int i3 = SAMPLE_RATE_BY_SFREQ[normalizedFrame.readBits(4)];
        int bits = normalizedFrame.readBits(5);
        int[] iArr = TWICE_BITRATE_KBPS_BY_RATE;
        int i4 = bits >= iArr.length ? -1 : (iArr[bits] * 1000) / 2;
        normalizedFrame.skipBits(10);
        int i5 = i2 + (normalizedFrame.readBits(2) > 0 ? 1 : 0);
        Format.Builder builder = new Format.Builder();
        builder.id = str;
        builder.sampleMimeType = MimeTypes.normalizeMimeType("audio/vnd.dts");
        builder.averageBitrate = i4;
        builder.channelCount = i5;
        builder.sampleRate = i3;
        builder.drmInitData = drmInitData;
        builder.language = str2;
        builder.roleFlags = i;
        return new Format(builder);
    }

    public static DtsHeader parseDtsHdHeader(byte[] bArr) throws ParserException {
        int i;
        int i2;
        int bits;
        int i3;
        long jScaleLargeTimestamp;
        int i4;
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(40);
        int bits2 = normalizedFrame.readBits(2);
        if (normalizedFrame.readBit()) {
            i = 20;
            i2 = 12;
        } else {
            i = 16;
            i2 = 8;
        }
        normalizedFrame.skipBits(i2);
        int bits3 = normalizedFrame.readBits(i) + 1;
        boolean bit = normalizedFrame.readBit();
        int bits4 = -1;
        int i5 = 0;
        if (bit) {
            bits = normalizedFrame.readBits(2);
            int bits5 = (normalizedFrame.readBits(3) + 1) * 512;
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(36);
            }
            int bits6 = normalizedFrame.readBits(3) + 1;
            int bits7 = normalizedFrame.readBits(3) + 1;
            if (bits6 != 1 || bits7 != 1) {
                throw ParserException.createForUnsupportedContainerFeature("Multiple audio presentations or assets not supported");
            }
            int i6 = bits2 + 1;
            int bits8 = normalizedFrame.readBits(i6);
            for (int i7 = 0; i7 < i6; i7++) {
                if (((bits8 >> i7) & 1) == 1) {
                    normalizedFrame.skipBits(8);
                }
            }
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(2);
                int bits9 = (normalizedFrame.readBits(2) + 1) << 2;
                int bits10 = normalizedFrame.readBits(2) + 1;
                while (i5 < bits10) {
                    normalizedFrame.skipBits(bits9);
                    i5++;
                }
            }
            i5 = bits5;
        } else {
            bits = -1;
        }
        normalizedFrame.skipBits(i);
        normalizedFrame.skipBits(12);
        if (bit) {
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(4);
            }
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(24);
            }
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBytes(normalizedFrame.readBits(10) + 1);
            }
            normalizedFrame.skipBits(5);
            int i8 = SAMPLE_RATE_BY_INDEX[normalizedFrame.readBits(4)];
            bits4 = normalizedFrame.readBits(8) + 1;
            i3 = i8;
        } else {
            i3 = -2147483647;
        }
        if (bit) {
            if (bits == 0) {
                i4 = 32000;
            } else if (bits == 1) {
                i4 = 44100;
            } else {
                if (bits != 2) {
                    throw ParserException.createForMalformedContainer("Unsupported reference clock code in DTS HD header: " + bits, null);
                }
                i4 = 48000;
            }
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(i5, 1000000L, i4);
        } else {
            jScaleLargeTimestamp = -9223372036854775807L;
        }
        return new DtsHeader("audio/vnd.dts.hd;profile=lbr", bits4, i3, bits3, jScaleLargeTimestamp, 0);
    }

    public static int parseDtsHdHeaderSize(byte[] bArr) {
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(42);
        return normalizedFrame.readBits(normalizedFrame.readBit() ? 12 : 8) + 1;
    }

    public static DtsHeader parseDtsUhdHeader(byte[] bArr, AtomicInteger atomicInteger) throws ParserException {
        long jScaleLargeValue;
        int i;
        AtomicInteger atomicInteger2;
        int i2;
        int i3;
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        int i4 = normalizedFrame.readBits(32) == 1078008818 ? 1 : 0;
        int unsignedVarInt = parseUnsignedVarInt(normalizedFrame, UHD_FTOC_PAYLOAD_LENGTH_TABLE, true) + 1;
        if (i4 == 0) {
            jScaleLargeValue = -9223372036854775807L;
            i = -2147483647;
        } else {
            if (!normalizedFrame.readBit()) {
                throw ParserException.createForUnsupportedContainerFeature("Only supports full channel mask-based audio presentation");
            }
            checkCrc(bArr, unsignedVarInt);
            int bits = normalizedFrame.readBits(2);
            if (bits == 0) {
                i2 = 512;
            } else if (bits == 1) {
                i2 = 480;
            } else {
                if (bits != 2) {
                    throw ParserException.createForMalformedContainer("Unsupported base duration index in DTS UHD header: " + bits, null);
                }
                i2 = 384;
            }
            int bits2 = (normalizedFrame.readBits(3) + 1) * i2;
            int bits3 = normalizedFrame.readBits(2);
            if (bits3 == 0) {
                i3 = 32000;
            } else if (bits3 == 1) {
                i3 = 44100;
            } else {
                if (bits3 != 2) {
                    throw ParserException.createForMalformedContainer("Unsupported clock rate index in DTS UHD header: " + bits3, null);
                }
                i3 = 48000;
            }
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(36);
            }
            int bits4 = (1 << normalizedFrame.readBits(2)) * i3;
            jScaleLargeValue = Util.scaleLargeValue(bits2, 1000000L, i3, RoundingMode.FLOOR);
            i = bits4;
        }
        long j = jScaleLargeValue;
        int unsignedVarInt2 = 0;
        for (int i5 = 0; i5 < i4; i5++) {
            unsignedVarInt2 += parseUnsignedVarInt(normalizedFrame, UHD_METADATA_CHUNK_SIZE_LENGTH_TABLE, true);
        }
        if (i4 != 0) {
            atomicInteger2 = atomicInteger;
            atomicInteger2.set(parseUnsignedVarInt(normalizedFrame, UHD_AUDIO_CHUNK_ID_LENGTH_TABLE, true));
        } else {
            atomicInteger2 = atomicInteger;
        }
        return new DtsHeader("audio/vnd.dts.uhd;profile=p2", 2, i, unsignedVarInt2 + (atomicInteger2.get() != 0 ? parseUnsignedVarInt(normalizedFrame, UHD_AUDIO_CHUNK_SIZE_LENGTH_TABLE, true) : 0) + unsignedVarInt, j, 0);
    }

    public static int parseDtsUhdHeaderSize(byte[] bArr) {
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(32);
        return parseUnsignedVarInt(normalizedFrame, UHD_HEADER_SIZE_LENGTH_TABLE, true) + 1;
    }

    public static int parseUnsignedVarInt(ParsableBitArray parsableBitArray, int[] iArr, boolean z) {
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 3 && parsableBitArray.readBit(); i3++) {
            i2++;
        }
        if (z) {
            int i4 = 0;
            while (i < i2) {
                i4 += 1 << iArr[i];
                i++;
            }
            i = i4;
        }
        return parsableBitArray.readBits(iArr[i2]) + i;
    }

    public static int parseDtsAudioSampleCount(ByteBuffer byteBuffer) {
        int i;
        byte b;
        int i2;
        byte b2;
        if (byteBuffer.getInt(0) == -233094848 || byteBuffer.getInt(0) == -398277519) {
            return 1024;
        }
        if (byteBuffer.getInt(0) == 622876772) {
            return 4096;
        }
        int iPosition = byteBuffer.position();
        byte b3 = byteBuffer.get(iPosition);
        if (b3 != -2) {
            if (b3 == -1) {
                i = (byteBuffer.get(iPosition + 4) & 7) << 4;
                b2 = byteBuffer.get(iPosition + 7);
            } else if (b3 != 31) {
                i = (byteBuffer.get(iPosition + 4) & 1) << 6;
                b = byteBuffer.get(iPosition + 5);
            } else {
                i = (byteBuffer.get(iPosition + 5) & 7) << 4;
                b2 = byteBuffer.get(iPosition + 6);
            }
            i2 = b2 & 60;
            return (((i2 >> 2) | i) + 1) * 32;
        }
        i = (byteBuffer.get(iPosition + 5) & 1) << 6;
        b = byteBuffer.get(iPosition + 4);
        i2 = b & 252;
        return (((i2 >> 2) | i) + 1) * 32;
    }
}
