package org.tukaani.xz;

import java.io.InputStream;

/* JADX INFO: loaded from: classes4.dex */
public class DeltaDecoder extends DeltaCoder implements FilterDecoder {
    public final int distance;

    public DeltaDecoder(byte[] bArr) throws UnsupportedOptionsException {
        if (bArr.length != 1) {
            throw new UnsupportedOptionsException("Unsupported Delta filter properties");
        }
        this.distance = (bArr[0] & 255) + 1;
    }

    @Override // org.tukaani.xz.FilterDecoder
    public InputStream getInputStream(InputStream inputStream) {
        return new DeltaInputStream(inputStream, this.distance);
    }

    @Override // org.tukaani.xz.FilterDecoder
    public int getMemoryUsage() {
        return 1;
    }
}
