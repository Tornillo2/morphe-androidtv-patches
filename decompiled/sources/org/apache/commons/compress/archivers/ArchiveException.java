package org.apache.commons.compress.archivers;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ArchiveException extends Exception {
    public static final long serialVersionUID = 2772690708123267100L;

    public ArchiveException(String str) {
        super(str);
    }

    public ArchiveException(String str, Exception exc) {
        super(str);
        initCause(exc);
    }
}
