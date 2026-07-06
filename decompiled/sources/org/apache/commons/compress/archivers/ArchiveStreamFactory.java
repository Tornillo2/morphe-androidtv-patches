package org.apache.commons.compress.archivers;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.ar.ArArchiveInputStream;
import org.apache.commons.compress.archivers.ar.ArArchiveOutputStream;
import org.apache.commons.compress.archivers.cpio.CpioArchiveInputStream;
import org.apache.commons.compress.archivers.cpio.CpioArchiveOutputStream;
import org.apache.commons.compress.archivers.dump.DumpArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ArchiveStreamFactory {
    public static final String AR = "ar";
    public static final String CPIO = "cpio";
    public static final String DUMP = "dump";
    public static final String JAR = "jar";
    public static final String TAR = "tar";
    public static final String ZIP = "zip";
    public String entryEncoding = null;

    public ArchiveInputStream createArchiveInputStream(String str, InputStream inputStream) throws ArchiveException {
        if (str == null) {
            throw new IllegalArgumentException("Archivername must not be null.");
        }
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream must not be null.");
        }
        if (AR.equalsIgnoreCase(str)) {
            return new ArArchiveInputStream(inputStream);
        }
        if (ZIP.equalsIgnoreCase(str)) {
            return this.entryEncoding != null ? new ZipArchiveInputStream(inputStream, this.entryEncoding) : new ZipArchiveInputStream(inputStream);
        }
        if (TAR.equalsIgnoreCase(str)) {
            return this.entryEncoding != null ? new TarArchiveInputStream(inputStream, this.entryEncoding) : new TarArchiveInputStream(inputStream);
        }
        if (JAR.equalsIgnoreCase(str)) {
            return new JarArchiveInputStream(inputStream);
        }
        if (CPIO.equalsIgnoreCase(str)) {
            return new CpioArchiveInputStream(inputStream);
        }
        if (DUMP.equalsIgnoreCase(str)) {
            return new DumpArchiveInputStream(inputStream);
        }
        throw new ArchiveException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Archiver: ", str, " not found."));
    }

    public ArchiveOutputStream createArchiveOutputStream(String str, OutputStream outputStream) throws ArchiveException {
        if (str == null) {
            throw new IllegalArgumentException("Archivername must not be null.");
        }
        if (outputStream == null) {
            throw new IllegalArgumentException("OutputStream must not be null.");
        }
        if (AR.equalsIgnoreCase(str)) {
            return new ArArchiveOutputStream(outputStream);
        }
        if (ZIP.equalsIgnoreCase(str)) {
            ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream(outputStream);
            String str2 = this.entryEncoding;
            if (str2 != null) {
                zipArchiveOutputStream.setEncoding(str2);
            }
            return zipArchiveOutputStream;
        }
        if (TAR.equalsIgnoreCase(str)) {
            return this.entryEncoding != null ? new TarArchiveOutputStream(outputStream, this.entryEncoding) : new TarArchiveOutputStream(outputStream);
        }
        if (JAR.equalsIgnoreCase(str)) {
            return new JarArchiveOutputStream(outputStream);
        }
        if (CPIO.equalsIgnoreCase(str)) {
            return new CpioArchiveOutputStream(outputStream, (short) 1);
        }
        throw new ArchiveException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Archiver: ", str, " not found."));
    }

    public String getEntryEncoding() {
        return this.entryEncoding;
    }

    public void setEntryEncoding(String str) {
        this.entryEncoding = str;
    }

    public ArchiveInputStream createArchiveInputStream(InputStream inputStream) throws Throwable {
        TarArchiveInputStream tarArchiveInputStream;
        if (inputStream != null) {
            if (inputStream.markSupported()) {
                byte[] bArr = new byte[12];
                inputStream.mark(12);
                try {
                    int i = inputStream.read(bArr);
                    inputStream.reset();
                    if (ZipArchiveInputStream.matches(bArr, i)) {
                        if (this.entryEncoding != null) {
                            return new ZipArchiveInputStream(inputStream, this.entryEncoding);
                        }
                        return new ZipArchiveInputStream(inputStream);
                    }
                    if (JarArchiveInputStream.matches(bArr, i)) {
                        return new JarArchiveInputStream(inputStream);
                    }
                    if (ArArchiveInputStream.matches(bArr, i)) {
                        return new ArArchiveInputStream(inputStream);
                    }
                    if (CpioArchiveInputStream.matches(bArr, i)) {
                        return new CpioArchiveInputStream(inputStream);
                    }
                    byte[] bArr2 = new byte[32];
                    inputStream.mark(32);
                    int i2 = inputStream.read(bArr2);
                    inputStream.reset();
                    if (DumpArchiveInputStream.matches(bArr2, i2)) {
                        return new DumpArchiveInputStream(inputStream);
                    }
                    byte[] bArr3 = new byte[512];
                    inputStream.mark(512);
                    int i3 = inputStream.read(bArr3);
                    inputStream.reset();
                    if (TarArchiveInputStream.matches(bArr3, i3)) {
                        if (this.entryEncoding != null) {
                            return new TarArchiveInputStream(inputStream, this.entryEncoding);
                        }
                        return new TarArchiveInputStream(inputStream);
                    }
                    if (i3 >= 512) {
                        TarArchiveInputStream tarArchiveInputStream2 = null;
                        try {
                            try {
                                tarArchiveInputStream = new TarArchiveInputStream(new ByteArrayInputStream(bArr3));
                            } catch (IOException unused) {
                            }
                            try {
                                if (tarArchiveInputStream.getNextTarEntry().isCheckSumOK()) {
                                    TarArchiveInputStream tarArchiveInputStream3 = new TarArchiveInputStream(inputStream);
                                    try {
                                        tarArchiveInputStream.close();
                                    } catch (IOException unused2) {
                                    }
                                    return tarArchiveInputStream3;
                                }
                                tarArchiveInputStream.close();
                            } catch (Exception unused3) {
                                tarArchiveInputStream2 = tarArchiveInputStream;
                                if (tarArchiveInputStream2 != null) {
                                    tarArchiveInputStream2.close();
                                }
                                throw new ArchiveException("No Archiver found for the stream signature");
                            } catch (Throwable th) {
                                th = th;
                                tarArchiveInputStream2 = tarArchiveInputStream;
                                if (tarArchiveInputStream2 != null) {
                                    try {
                                        tarArchiveInputStream2.close();
                                    } catch (IOException unused4) {
                                    }
                                }
                                throw th;
                            }
                        } catch (Exception unused5) {
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    throw new ArchiveException("No Archiver found for the stream signature");
                } catch (IOException e) {
                    throw new ArchiveException("Could not use reset and mark operations.", e);
                }
            }
            throw new IllegalArgumentException("Mark is not supported.");
        }
        throw new IllegalArgumentException("Stream must not be null.");
    }
}
