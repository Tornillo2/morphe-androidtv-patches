package androidx.emoji2.text.flatbuffer;

import com.amazon.ion.impl.bin.IonRawBinaryWriter;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import kotlinx.serialization.json.internal.JsonStreamsKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Utf8 {
    public static Utf8 DEFAULT;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class DecodeUtil {
        public static void handleFourBytes(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) throws IllegalArgumentException {
            if (!isNotTrailingByte(b2)) {
                if ((((b2 + IonRawBinaryWriter.SYMBOL_TYPE) + (b << Ascii.FS)) >> 30) == 0 && !isNotTrailingByte(b3) && !isNotTrailingByte(b4)) {
                    int i2 = ((b & 7) << 18) | ((b2 & 63) << 12) | ((b3 & 63) << 6) | (b4 & 63);
                    cArr[i] = highSurrogate(i2);
                    cArr[i + 1] = lowSurrogate(i2);
                    return;
                }
            }
            throw new IllegalArgumentException("Invalid UTF-8");
        }

        public static void handleOneByte(byte b, char[] cArr, int i) {
            cArr[i] = (char) b;
        }

        public static void handleThreeBytes(byte b, byte b2, byte b3, char[] cArr, int i) throws IllegalArgumentException {
            if (isNotTrailingByte(b2) || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || isNotTrailingByte(b3)))) {
                throw new IllegalArgumentException("Invalid UTF-8");
            }
            cArr[i] = (char) (((b & Ascii.SI) << 12) | ((b2 & 63) << 6) | (b3 & 63));
        }

        public static void handleTwoBytes(byte b, byte b2, char[] cArr, int i) throws IllegalArgumentException {
            if (b < -62) {
                throw new IllegalArgumentException("Invalid UTF-8: Illegal leading byte in 2 bytes utf");
            }
            if (isNotTrailingByte(b2)) {
                throw new IllegalArgumentException("Invalid UTF-8: Illegal trailing byte in 2 bytes utf");
            }
            cArr[i] = (char) (((b & 31) << 6) | (b2 & 63));
        }

        public static char highSurrogate(int i) {
            return (char) ((i >>> 10) + JsonStreamsKt.HIGH_SURROGATE_HEADER);
        }

        public static boolean isNotTrailingByte(byte b) {
            return b > -65;
        }

        public static boolean isOneByte(byte b) {
            return b >= 0;
        }

        public static boolean isThreeBytes(byte b) {
            return b < -16;
        }

        public static boolean isTwoBytes(byte b) {
            return b < -32;
        }

        public static char lowSurrogate(int i) {
            return (char) ((i & 1023) + 56320);
        }

        public static int trailingByteValue(byte b) {
            return b & 63;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class UnpairedSurrogateException extends IllegalArgumentException {
        public UnpairedSurrogateException(int i, int i2) {
            super(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Unpaired surrogate at index ", i, " of ", i2));
        }
    }

    public static Utf8 getDefault() {
        if (DEFAULT == null) {
            DEFAULT = new Utf8Safe();
        }
        return DEFAULT;
    }

    public static void setDefault(Utf8 utf8) {
        DEFAULT = utf8;
    }

    public abstract String decodeUtf8(ByteBuffer byteBuffer, int i, int i2);

    public abstract void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer);

    public abstract int encodedLength(CharSequence charSequence);
}
