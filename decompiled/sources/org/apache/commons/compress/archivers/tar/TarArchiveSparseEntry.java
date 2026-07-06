package org.apache.commons.compress.archivers.tar;

import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class TarArchiveSparseEntry implements TarConstants {
    public final boolean isExtended;

    public TarArchiveSparseEntry(byte[] bArr) throws IOException {
        this.isExtended = TarUtils.parseBoolean(bArr, TarConstants.SPARSELEN_GNU_SPARSE);
    }

    public boolean isExtended() {
        return this.isExtended;
    }
}
