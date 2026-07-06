package org.tukaani.xz.delta;

/* JADX INFO: loaded from: classes4.dex */
public abstract class DeltaCoder {
    public static final int DISTANCE_MASK = 255;
    public static final int DISTANCE_MAX = 256;
    public static final int DISTANCE_MIN = 1;
    public final int distance;
    public final byte[] history = new byte[256];
    public int pos = 0;

    public DeltaCoder(int i) {
        if (i < 1 || i > 256) {
            throw new IllegalArgumentException();
        }
        this.distance = i;
    }
}
