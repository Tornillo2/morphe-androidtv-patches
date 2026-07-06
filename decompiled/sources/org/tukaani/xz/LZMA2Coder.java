package org.tukaani.xz;

/* JADX INFO: loaded from: classes4.dex */
public abstract class LZMA2Coder implements FilterCoder {
    public static final long FILTER_ID = 33;

    @Override // org.tukaani.xz.FilterCoder
    public boolean changesSize() {
        return true;
    }

    @Override // org.tukaani.xz.FilterCoder
    public boolean lastOK() {
        return true;
    }

    @Override // org.tukaani.xz.FilterCoder
    public boolean nonLastOK() {
        return false;
    }
}
