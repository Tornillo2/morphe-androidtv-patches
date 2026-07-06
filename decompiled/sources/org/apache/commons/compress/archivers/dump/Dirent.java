package org.apache.commons.compress.archivers.dump;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class Dirent {
    public final int ino;
    public final String name;
    public final int parentIno;
    public final int type;

    public Dirent(int i, int i2, int i3, String str) {
        this.ino = i;
        this.parentIno = i2;
        this.type = i3;
        this.name = str;
    }

    public int getIno() {
        return this.ino;
    }

    public String getName() {
        return this.name;
    }

    public int getParentIno() {
        return this.parentIno;
    }

    public int getType() {
        return this.type;
    }

    public String toString() {
        return String.format("[%d]: %s", Integer.valueOf(this.ino), this.name);
    }
}
