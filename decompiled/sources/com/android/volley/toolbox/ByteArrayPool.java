package com.android.volley.toolbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ByteArrayPool {
    public static final Comparator<byte[]> BUF_COMPARATOR = new AnonymousClass1();
    public final List<byte[]> mBuffersByLastUse = new ArrayList();
    public final List<byte[]> mBuffersBySize = new ArrayList(64);
    public int mCurrentSize = 0;
    public final int mSizeLimit;

    /* JADX INFO: renamed from: com.android.volley.toolbox.ByteArrayPool$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements Comparator<byte[]> {
        @Override // java.util.Comparator
        public int compare(byte[] bArr, byte[] bArr2) {
            return bArr.length - bArr2.length;
        }
    }

    public ByteArrayPool(int i) {
        this.mSizeLimit = i;
    }

    public synchronized byte[] getBuf(int i) {
        for (int i2 = 0; i2 < this.mBuffersBySize.size(); i2++) {
            byte[] bArr = this.mBuffersBySize.get(i2);
            if (bArr.length >= i) {
                this.mCurrentSize -= bArr.length;
                this.mBuffersBySize.remove(i2);
                this.mBuffersByLastUse.remove(bArr);
                return bArr;
            }
        }
        return new byte[i];
    }

    public synchronized void returnBuf(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.mSizeLimit) {
                this.mBuffersByLastUse.add(bArr);
                int iBinarySearch = Collections.binarySearch(this.mBuffersBySize, bArr, BUF_COMPARATOR);
                if (iBinarySearch < 0) {
                    iBinarySearch = (-iBinarySearch) - 1;
                }
                this.mBuffersBySize.add(iBinarySearch, bArr);
                this.mCurrentSize += bArr.length;
                trim();
            }
        }
    }

    public final synchronized void trim() {
        while (this.mCurrentSize > this.mSizeLimit) {
            byte[] bArrRemove = this.mBuffersByLastUse.remove(0);
            this.mBuffersBySize.remove(bArrRemove);
            this.mCurrentSize -= bArrRemove.length;
        }
    }
}
