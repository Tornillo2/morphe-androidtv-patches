package org.apache.commons.compress.archivers.jar;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.JarMarker;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class JarArchiveOutputStream extends ZipArchiveOutputStream {
    public boolean jarMarkerAdded;

    public JarArchiveOutputStream(OutputStream outputStream) {
        super(outputStream);
        this.jarMarkerAdded = false;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream, org.apache.commons.compress.archivers.ArchiveOutputStream
    public void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException {
        if (!this.jarMarkerAdded) {
            ((ZipArchiveEntry) archiveEntry).addAsFirstExtraField(JarMarker.DEFAULT);
            this.jarMarkerAdded = true;
        }
        super.putArchiveEntry(archiveEntry);
    }
}
