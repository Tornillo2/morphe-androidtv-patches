package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class FallbackZipEncoding implements ZipEncoding {
    public final String charset;

    public FallbackZipEncoding() {
        this.charset = null;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public boolean canEncode(String str) {
        return true;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public String decode(byte[] bArr) throws IOException {
        String str = this.charset;
        return str == null ? new String(bArr) : new String(bArr, str);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public ByteBuffer encode(String str) throws IOException {
        String str2 = this.charset;
        return str2 == null ? ByteBuffer.wrap(str.getBytes()) : ByteBuffer.wrap(str.getBytes(str2));
    }

    public FallbackZipEncoding(String str) {
        this.charset = str;
    }
}
