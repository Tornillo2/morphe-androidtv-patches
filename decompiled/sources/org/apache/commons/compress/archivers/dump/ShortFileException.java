package org.apache.commons.compress.archivers.dump;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ShortFileException extends DumpArchiveException {
    public static final long serialVersionUID = 1;

    public ShortFileException() {
        super("unexpected EOF");
    }
}
