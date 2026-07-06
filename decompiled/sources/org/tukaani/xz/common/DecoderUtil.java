package org.tukaani.xz.common;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import org.tukaani.xz.CorruptedInputException;
import org.tukaani.xz.UnsupportedOptionsException;
import org.tukaani.xz.XZ;
import org.tukaani.xz.XZFormatException;

/* JADX INFO: loaded from: classes4.dex */
public class DecoderUtil extends Util {
    public static boolean areStreamFlagsEqual(StreamFlags streamFlags, StreamFlags streamFlags2) {
        return streamFlags.checkType == streamFlags2.checkType;
    }

    public static StreamFlags decodeStreamFlags(byte[] bArr, int i) throws UnsupportedOptionsException {
        if (bArr[i] == 0) {
            byte b = bArr[i + 1];
            if ((b & 255) < 16) {
                StreamFlags streamFlags = new StreamFlags();
                streamFlags.checkType = b;
                return streamFlags;
            }
        }
        throw new UnsupportedOptionsException();
    }

    public static StreamFlags decodeStreamFooter(byte[] bArr) throws IOException {
        byte b = bArr[10];
        byte[] bArr2 = XZ.FOOTER_MAGIC;
        if (b != bArr2[0] || bArr[11] != bArr2[1]) {
            throw new CorruptedInputException("XZ Stream Footer is corrupt");
        }
        if (!isCRC32Valid(bArr, 4, 6, 0)) {
            throw new CorruptedInputException("XZ Stream Footer is corrupt");
        }
        try {
            StreamFlags streamFlagsDecodeStreamFlags = decodeStreamFlags(bArr, 8);
            streamFlagsDecodeStreamFlags.backwardSize = 0L;
            for (int i = 0; i < 4; i++) {
                streamFlagsDecodeStreamFlags.backwardSize |= (long) ((bArr[i + 4] & 255) << (i * 8));
            }
            streamFlagsDecodeStreamFlags.backwardSize = (streamFlagsDecodeStreamFlags.backwardSize + 1) * 4;
            return streamFlagsDecodeStreamFlags;
        } catch (UnsupportedOptionsException unused) {
            throw new UnsupportedOptionsException("Unsupported options in XZ Stream Footer");
        }
    }

    public static StreamFlags decodeStreamHeader(byte[] bArr) throws IOException {
        int i = 0;
        while (true) {
            byte[] bArr2 = XZ.HEADER_MAGIC;
            if (i >= bArr2.length) {
                if (!isCRC32Valid(bArr, bArr2.length, 2, bArr2.length + 2)) {
                    throw new CorruptedInputException("XZ Stream Header is corrupt");
                }
                try {
                    return decodeStreamFlags(bArr, bArr2.length);
                } catch (UnsupportedOptionsException unused) {
                    throw new UnsupportedOptionsException("Unsupported options in XZ Stream Header");
                }
            }
            if (bArr[i] != bArr2[i]) {
                throw new XZFormatException();
            }
            i++;
        }
    }

    public static long decodeVLI(InputStream inputStream) throws IOException {
        int i = inputStream.read();
        if (i == -1) {
            throw new EOFException();
        }
        long j = i & 127;
        int i2 = 0;
        while ((i & 128) != 0) {
            i2++;
            if (i2 >= 9) {
                throw new CorruptedInputException();
            }
            i = inputStream.read();
            if (i == -1) {
                throw new EOFException();
            }
            if (i == 0) {
                throw new CorruptedInputException();
            }
            j |= ((long) (i & 127)) << (i2 * 7);
        }
        return j;
    }

    public static boolean isCRC32Valid(byte[] bArr, int i, int i2, int i3) {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr, i, i2);
        long value = crc32.getValue();
        for (int i4 = 0; i4 < 4; i4++) {
            if (((byte) (value >>> (i4 * 8))) != bArr[i3 + i4]) {
                return false;
            }
        }
        return true;
    }
}
