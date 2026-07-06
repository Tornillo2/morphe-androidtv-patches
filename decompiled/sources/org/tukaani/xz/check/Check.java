package org.tukaani.xz.check;

import java.security.NoSuchAlgorithmException;
import org.tukaani.xz.UnsupportedOptionsException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Check {
    public String name;
    public int size;

    public static Check getInstance(int i) throws UnsupportedOptionsException {
        if (i == 0) {
            return new None();
        }
        if (i == 1) {
            return new CRC32();
        }
        if (i == 4) {
            return new CRC64();
        }
        if (i == 10) {
            try {
                return new SHA256();
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        StringBuffer stringBuffer = new StringBuffer("Unsupported Check ID ");
        stringBuffer.append(i);
        throw new UnsupportedOptionsException(stringBuffer.toString());
    }

    public abstract byte[] finish();

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }

    public void update(byte[] bArr) {
        update(bArr, 0, bArr.length);
    }

    public abstract void update(byte[] bArr, int i, int i2);
}
