package org.apache.commons.compress.archivers.zip;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public interface UnixStat {
    public static final int DEFAULT_DIR_PERM = 493;
    public static final int DEFAULT_FILE_PERM = 420;
    public static final int DEFAULT_LINK_PERM = 511;
    public static final int DIR_FLAG = 16384;
    public static final int FILE_FLAG = 32768;
    public static final int LINK_FLAG = 40960;
    public static final int PERM_MASK = 4095;
}
