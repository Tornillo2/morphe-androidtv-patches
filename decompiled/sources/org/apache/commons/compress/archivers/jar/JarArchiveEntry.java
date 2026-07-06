package org.apache.commons.compress.archivers.jar;

import java.security.cert.Certificate;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class JarArchiveEntry extends ZipArchiveEntry {
    public final Certificate[] certificates;
    public final Attributes manifestAttributes;

    public JarArchiveEntry(ZipEntry zipEntry) throws ZipException {
        super(zipEntry);
        this.manifestAttributes = null;
        this.certificates = null;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipArchiveEntry
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Deprecated
    public Certificate[] getCertificates() {
        Certificate[] certificateArr = this.certificates;
        if (certificateArr == null) {
            return null;
        }
        int length = certificateArr.length;
        Certificate[] certificateArr2 = new Certificate[length];
        System.arraycopy(certificateArr, 0, certificateArr2, 0, length);
        return certificateArr2;
    }

    @Deprecated
    public Attributes getManifestAttributes() {
        return this.manifestAttributes;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipArchiveEntry, java.util.zip.ZipEntry
    public int hashCode() {
        return super.hashCode();
    }

    public JarArchiveEntry(String str) {
        super(str);
        this.manifestAttributes = null;
        this.certificates = null;
    }

    public JarArchiveEntry(ZipArchiveEntry zipArchiveEntry) throws ZipException {
        super(zipArchiveEntry);
        this.manifestAttributes = null;
        this.certificates = null;
    }

    public JarArchiveEntry(JarEntry jarEntry) throws ZipException {
        super(jarEntry);
        this.manifestAttributes = null;
        this.certificates = null;
    }
}
