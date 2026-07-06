package org.apache.commons.compress.changes;

import java.io.InputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class Change {
    public static final int TYPE_ADD = 2;
    public static final int TYPE_DELETE = 1;
    public static final int TYPE_DELETE_DIR = 4;
    public static final int TYPE_MOVE = 3;
    public final ArchiveEntry entry;
    public final InputStream input;
    public final boolean replaceMode;
    public final String targetFile;
    public final int type;

    public Change(String str, int i) {
        str.getClass();
        this.targetFile = str;
        this.type = i;
        this.input = null;
        this.entry = null;
        this.replaceMode = true;
    }

    public ArchiveEntry getEntry() {
        return this.entry;
    }

    public InputStream getInput() {
        return this.input;
    }

    public boolean isReplaceMode() {
        return this.replaceMode;
    }

    public String targetFile() {
        return this.targetFile;
    }

    public int type() {
        return this.type;
    }

    public Change(ArchiveEntry archiveEntry, InputStream inputStream, boolean z) {
        if (archiveEntry != null && inputStream != null) {
            this.entry = archiveEntry;
            this.input = inputStream;
            this.type = 2;
            this.targetFile = null;
            this.replaceMode = z;
            return;
        }
        throw null;
    }
}
