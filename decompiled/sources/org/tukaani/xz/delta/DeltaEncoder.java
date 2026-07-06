package org.tukaani.xz.delta;

/* JADX INFO: loaded from: classes4.dex */
public class DeltaEncoder extends DeltaCoder {
    public DeltaEncoder(int i) {
        super(i);
    }

    public void encode(byte[] bArr, int i, int i2, byte[] bArr2) {
        for (int i3 = 0; i3 < i2; i3++) {
            byte[] bArr3 = this.history;
            int i4 = this.distance;
            int i5 = this.pos;
            byte b = bArr3[(i4 + i5) & 255];
            this.pos = i5 - 1;
            int i6 = i + i3;
            bArr3[i5 & 255] = bArr[i6];
            bArr2[i3] = (byte) (bArr[i6] - b);
        }
    }
}
