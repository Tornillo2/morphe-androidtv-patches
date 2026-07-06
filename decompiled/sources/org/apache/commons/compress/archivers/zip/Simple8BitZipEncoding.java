package org.apache.commons.compress.archivers.zip;

import j$.util.DesugarCollections;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class Simple8BitZipEncoding implements ZipEncoding {
    public final char[] highChars;
    public final List<Simple8BitChar> reverseMapping;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Simple8BitChar implements Comparable<Simple8BitChar> {
        public final byte code;
        public final char unicode;

        public Simple8BitChar(byte b, char c) {
            this.code = b;
            this.unicode = c;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Simple8BitChar) {
                Simple8BitChar simple8BitChar = (Simple8BitChar) obj;
                if (this.unicode == simple8BitChar.unicode && this.code == simple8BitChar.code) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.unicode;
        }

        public String toString() {
            return "0x" + Integer.toHexString(65535 & this.unicode) + "->0x" + Integer.toHexString(this.code & 255);
        }

        @Override // java.lang.Comparable
        public int compareTo(Simple8BitChar simple8BitChar) {
            return this.unicode - simple8BitChar.unicode;
        }
    }

    public Simple8BitZipEncoding(char[] cArr) {
        char[] cArr2 = (char[]) cArr.clone();
        this.highChars = cArr2;
        ArrayList arrayList = new ArrayList(cArr2.length);
        byte b = 127;
        int i = 0;
        while (true) {
            char[] cArr3 = this.highChars;
            if (i >= cArr3.length) {
                Collections.sort(arrayList);
                this.reverseMapping = DesugarCollections.unmodifiableList(arrayList);
                return;
            } else {
                b = (byte) (b + 1);
                arrayList.add(new Simple8BitChar(b, cArr3[i]));
                i++;
            }
        }
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public boolean canEncode(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!canEncodeChar(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean canEncodeChar(char c) {
        return (c >= 0 && c < 128) || encodeHighChar(c) != null;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public String decode(byte[] bArr) throws IOException {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            cArr[i] = decodeByte(bArr[i]);
        }
        return new String(cArr);
    }

    public char decodeByte(byte b) {
        return b >= 0 ? (char) b : this.highChars[b + 128];
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public ByteBuffer encode(String str) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(((str.length() + 1) / 2) + str.length() + 6);
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (byteBufferAllocate.remaining() < 6) {
                byteBufferAllocate = ZipEncodingHelper.growBuffer(byteBufferAllocate, byteBufferAllocate.position() + 6);
            }
            if (!pushEncodedChar(byteBufferAllocate, cCharAt)) {
                ZipEncodingHelper.appendSurrogate(byteBufferAllocate, cCharAt);
            }
        }
        byteBufferAllocate.limit(byteBufferAllocate.position());
        byteBufferAllocate.rewind();
        return byteBufferAllocate;
    }

    public final Simple8BitChar encodeHighChar(char c) {
        int size = this.reverseMapping.size();
        int i = 0;
        while (size > i) {
            int i2 = ((size - i) / 2) + i;
            Simple8BitChar simple8BitChar = this.reverseMapping.get(i2);
            char c2 = simple8BitChar.unicode;
            if (c2 == c) {
                return simple8BitChar;
            }
            if (c2 < c) {
                i = i2 + 1;
            } else {
                size = i2;
            }
        }
        if (i >= this.reverseMapping.size()) {
            return null;
        }
        Simple8BitChar simple8BitChar2 = this.reverseMapping.get(i);
        if (simple8BitChar2.unicode != c) {
            return null;
        }
        return simple8BitChar2;
    }

    public boolean pushEncodedChar(ByteBuffer byteBuffer, char c) {
        if (c >= 0 && c < 128) {
            byteBuffer.put((byte) c);
            return true;
        }
        Simple8BitChar simple8BitCharEncodeHighChar = encodeHighChar(c);
        if (simple8BitCharEncodeHighChar == null) {
            return false;
        }
        byteBuffer.put(simple8BitCharEncodeHighChar.code);
        return true;
    }
}
