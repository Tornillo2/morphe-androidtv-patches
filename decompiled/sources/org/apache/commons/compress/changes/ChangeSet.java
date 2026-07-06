package org.apache.commons.compress.changes;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveEntry;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ChangeSet {
    public final Set<Change> changes = new LinkedHashSet();

    public void add(ArchiveEntry archiveEntry, InputStream inputStream) {
        add(archiveEntry, inputStream, true);
    }

    public final void addAddition(Change change) {
        if (2 != change.type() || change.getInput() == null) {
            return;
        }
        if (!this.changes.isEmpty()) {
            Iterator<Change> it = this.changes.iterator();
            while (it.hasNext()) {
                Change next = it.next();
                if (next.type() == 2 && next.getEntry() != null && next.getEntry().equals(change.getEntry())) {
                    if (change.isReplaceMode()) {
                        it.remove();
                        this.changes.add(change);
                        return;
                    }
                    return;
                }
            }
        }
        this.changes.add(change);
    }

    public final void addDeletion(Change change) {
        if ((1 == change.type() || 4 == change.type()) && change.targetFile() != null) {
            String strTargetFile = change.targetFile();
            if (!this.changes.isEmpty()) {
                Iterator<Change> it = this.changes.iterator();
                while (it.hasNext()) {
                    Change next = it.next();
                    if (next.type() == 2 && next.getEntry() != null) {
                        String name = next.getEntry().getName();
                        if (1 == change.type() && strTargetFile.equals(name)) {
                            it.remove();
                        } else if (4 == change.type()) {
                            if (name.matches(strTargetFile + "/.*")) {
                                it.remove();
                            }
                        }
                    }
                }
            }
            this.changes.add(change);
        }
    }

    public void delete(String str) {
        addDeletion(new Change(str, 1));
    }

    public void deleteDir(String str) {
        addDeletion(new Change(str, 4));
    }

    public Set<Change> getChanges() {
        return new LinkedHashSet(this.changes);
    }

    public void add(ArchiveEntry archiveEntry, InputStream inputStream, boolean z) {
        addAddition(new Change(archiveEntry, inputStream, z));
    }
}
