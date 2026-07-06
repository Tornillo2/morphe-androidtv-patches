package org.apache.commons.compress.archivers.ar;

import java.io.File;
import java.util.Date;
import org.apache.commons.compress.archivers.ArchiveEntry;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ArArchiveEntry implements ArchiveEntry {
    public static final int DEFAULT_MODE = 33188;
    public static final String HEADER = "!<arch>\n";
    public static final String TRAILER = "`\n";
    public final int groupId;
    public final long lastModified;
    public final long length;
    public final int mode;
    public final String name;
    public final int userId;

    public ArArchiveEntry(String str, long j) {
        this(str, j, 0, 0, 33188, System.currentTimeMillis() / 1000);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ArArchiveEntry arArchiveEntry = (ArArchiveEntry) obj;
        String str = this.name;
        if (str == null) {
            if (arArchiveEntry.name != null) {
                return false;
            }
        } else if (!str.equals(arArchiveEntry.name)) {
            return false;
        }
        return true;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public long getLastModified() {
        return this.lastModified;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public Date getLastModifiedDate() {
        return new Date(getLastModified() * 1000);
    }

    public long getLength() {
        return this.length;
    }

    public int getMode() {
        return this.mode;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public String getName() {
        return this.name;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public long getSize() {
        return getLength();
    }

    public int getUserId() {
        return this.userId;
    }

    public int hashCode() {
        String str = this.name;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public boolean isDirectory() {
        return false;
    }

    public ArArchiveEntry(String str, long j, int i, int i2, int i3, long j2) {
        this.name = str;
        this.length = j;
        this.userId = i;
        this.groupId = i2;
        this.mode = i3;
        this.lastModified = j2;
    }

    public ArArchiveEntry(File file, String str) {
        this(str, file.isFile() ? file.length() : 0L, 0, 0, 33188, file.lastModified() / 1000);
    }
}
