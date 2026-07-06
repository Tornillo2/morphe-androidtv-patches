package org.apache.commons.compress.changes;

import com.amazon.minerva.identifiers.schemaid.SchemaId;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ChangeSetPerformer {
    public final Set<Change> changes;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface ArchiveEntryIterator {
        InputStream getInputStream() throws IOException;

        boolean hasNext() throws IOException;

        ArchiveEntry next();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ArchiveInputStreamIterator implements ArchiveEntryIterator {
        public final ArchiveInputStream in;
        public ArchiveEntry next;

        public ArchiveInputStreamIterator(ArchiveInputStream archiveInputStream) {
            this.in = archiveInputStream;
        }

        @Override // org.apache.commons.compress.changes.ChangeSetPerformer.ArchiveEntryIterator
        public InputStream getInputStream() {
            return this.in;
        }

        @Override // org.apache.commons.compress.changes.ChangeSetPerformer.ArchiveEntryIterator
        public boolean hasNext() throws IOException {
            ArchiveEntry nextEntry = this.in.getNextEntry();
            this.next = nextEntry;
            return nextEntry != null;
        }

        @Override // org.apache.commons.compress.changes.ChangeSetPerformer.ArchiveEntryIterator
        public ArchiveEntry next() {
            return this.next;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ZipFileIterator implements ArchiveEntryIterator {
        public ZipArchiveEntry current;
        public final ZipFile in;
        public final Enumeration<ZipArchiveEntry> nestedEnum;

        public ZipFileIterator(ZipFile zipFile) {
            this.in = zipFile;
            this.nestedEnum = zipFile.getEntriesInPhysicalOrder();
        }

        @Override // org.apache.commons.compress.changes.ChangeSetPerformer.ArchiveEntryIterator
        public InputStream getInputStream() throws IOException {
            return this.in.getInputStream(this.current);
        }

        @Override // org.apache.commons.compress.changes.ChangeSetPerformer.ArchiveEntryIterator
        public boolean hasNext() {
            return this.nestedEnum.hasMoreElements();
        }

        @Override // org.apache.commons.compress.changes.ChangeSetPerformer.ArchiveEntryIterator
        public ArchiveEntry next() {
            ZipArchiveEntry zipArchiveEntryNextElement = this.nestedEnum.nextElement();
            this.current = zipArchiveEntryNextElement;
            return zipArchiveEntryNextElement;
        }
    }

    public ChangeSetPerformer(ChangeSet changeSet) {
        this.changes = changeSet.getChanges();
    }

    public final void copyStream(InputStream inputStream, ArchiveOutputStream archiveOutputStream, ArchiveEntry archiveEntry) throws IOException {
        archiveOutputStream.putArchiveEntry(archiveEntry);
        IOUtils.copy(inputStream, archiveOutputStream);
        archiveOutputStream.closeArchiveEntry();
    }

    public final boolean isDeletedLater(Set<Change> set, ArchiveEntry archiveEntry) {
        String name = archiveEntry.getName();
        if (set.isEmpty()) {
            return false;
        }
        for (Change change : set) {
            int iType = change.type();
            String strTargetFile = change.targetFile();
            if (iType != 1 || !name.equals(strTargetFile)) {
                if (iType == 4) {
                    if (name.startsWith(strTargetFile + SchemaId.METRIC_SCHEMA_ID_DELIMITER)) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    public ChangeSetResults perform(ArchiveInputStream archiveInputStream, ArchiveOutputStream archiveOutputStream) throws IOException {
        return perform(new ArchiveInputStreamIterator(archiveInputStream), archiveOutputStream);
    }

    public ChangeSetResults perform(ZipFile zipFile, ArchiveOutputStream archiveOutputStream) throws IOException {
        return perform(new ZipFileIterator(zipFile), archiveOutputStream);
    }

    public final ChangeSetResults perform(ArchiveEntryIterator archiveEntryIterator, ArchiveOutputStream archiveOutputStream) throws IOException {
        ChangeSetResults changeSetResults = new ChangeSetResults();
        LinkedHashSet linkedHashSet = new LinkedHashSet(this.changes);
        Iterator<Change> it = linkedHashSet.iterator();
        while (it.hasNext()) {
            Change next = it.next();
            if (next.type() == 2 && next.isReplaceMode()) {
                copyStream(next.getInput(), archiveOutputStream, next.getEntry());
                it.remove();
                changeSetResults.addedFromChangeSet(next.getEntry().getName());
            }
        }
        while (archiveEntryIterator.hasNext()) {
            ArchiveEntry next2 = archiveEntryIterator.next();
            Iterator<Change> it2 = linkedHashSet.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Change next3 = it2.next();
                    int iType = next3.type();
                    String name = next2.getName();
                    if (iType != 1 || name == null) {
                        if (iType == 4 && name != null) {
                            if (name.startsWith(next3.targetFile() + SchemaId.METRIC_SCHEMA_ID_DELIMITER)) {
                                changeSetResults.deleted(name);
                                break;
                            }
                        }
                    } else if (name.equals(next3.targetFile())) {
                        it2.remove();
                        changeSetResults.deleted(name);
                        break;
                    }
                } else if (!isDeletedLater(linkedHashSet, next2) && !changeSetResults.hasBeenAdded(next2.getName())) {
                    copyStream(archiveEntryIterator.getInputStream(), archiveOutputStream, next2);
                    changeSetResults.addedFromStream(next2.getName());
                }
            }
        }
        Iterator<Change> it3 = linkedHashSet.iterator();
        while (it3.hasNext()) {
            Change next4 = it3.next();
            if (next4.type() == 2 && !next4.isReplaceMode() && !changeSetResults.hasBeenAdded(next4.getEntry().getName())) {
                copyStream(next4.getInput(), archiveOutputStream, next4.getEntry());
                it3.remove();
                changeSetResults.addedFromChangeSet(next4.getEntry().getName());
            }
        }
        archiveOutputStream.finish();
        return changeSetResults;
    }
}
