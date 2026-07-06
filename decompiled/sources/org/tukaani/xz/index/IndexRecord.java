package org.tukaani.xz.index;

/* JADX INFO: loaded from: classes4.dex */
public class IndexRecord {
    public final long uncompressed;
    public final long unpadded;

    public IndexRecord(long j, long j2) {
        this.unpadded = j;
        this.uncompressed = j2;
    }
}
