package com.amazon.ion.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class GzipOrRawInputStream extends FilterInputStream {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final byte[] GZIP_HEADER = {31, -117};

    public GzipOrRawInputStream(InputStream inputStream) throws IOException {
        this(inputStream, 512);
    }

    public GzipOrRawInputStream(InputStream inputStream, int i) throws IOException {
        super(null);
        int length = GZIP_HEADER.length;
        byte[] bArr = new byte[length];
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, 2);
        int i2 = 0;
        while (i2 < length) {
            int i3 = pushbackInputStream.read();
            if (i3 == -1) {
                break;
            }
            bArr[i2] = (byte) i3;
            i2++;
        }
        pushbackInputStream.unread(bArr, 0, i2);
        if (i2 == 2 && Arrays.equals(bArr, GZIP_HEADER)) {
            ((FilterInputStream) this).in = new GZIPInputStream(pushbackInputStream, i);
        } else {
            ((FilterInputStream) this).in = pushbackInputStream;
        }
    }
}
