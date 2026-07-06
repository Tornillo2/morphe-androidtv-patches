package org.apache.commons.compress.compressors;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.compressors.pack200.Pack200CompressorInputStream;
import org.apache.commons.compress.compressors.pack200.Pack200CompressorOutputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;
import org.apache.commons.compress.compressors.xz.XZUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class CompressorStreamFactory {
    public static final String BZIP2 = "bzip2";
    public static final String GZIP = "gz";
    public static final String PACK200 = "pack200";
    public static final String XZ = "xz";
    public boolean decompressConcatenated = false;

    public CompressorInputStream createCompressorInputStream(InputStream inputStream) throws CompressorException {
        if (inputStream == null) {
            throw new IllegalArgumentException("Stream must not be null.");
        }
        if (!inputStream.markSupported()) {
            throw new IllegalArgumentException("Mark is not supported.");
        }
        byte[] bArr = new byte[12];
        inputStream.mark(12);
        try {
            int i = inputStream.read(bArr);
            inputStream.reset();
            if (BZip2CompressorInputStream.matches(bArr, i)) {
                return new BZip2CompressorInputStream(inputStream, this.decompressConcatenated);
            }
            if (GzipCompressorInputStream.matches(bArr, i)) {
                return new GzipCompressorInputStream(inputStream, this.decompressConcatenated);
            }
            if (XZUtils.isXZCompressionAvailable() && XZCompressorInputStream.matches(bArr, i)) {
                return new XZCompressorInputStream(inputStream, this.decompressConcatenated);
            }
            if (Pack200CompressorInputStream.matches(bArr, i)) {
                return new Pack200CompressorInputStream(inputStream);
            }
            throw new CompressorException("No Compressor found for the stream signature.");
        } catch (IOException e) {
            throw new CompressorException("Failed to detect Compressor from InputStream.", e);
        }
    }

    public CompressorOutputStream createCompressorOutputStream(String str, OutputStream outputStream) throws CompressorException {
        if (str == null || outputStream == null) {
            throw new IllegalArgumentException("Compressor name and stream must not be null.");
        }
        try {
            if (GZIP.equalsIgnoreCase(str)) {
                return new GzipCompressorOutputStream(outputStream);
            }
            if (BZIP2.equalsIgnoreCase(str)) {
                return new BZip2CompressorOutputStream(outputStream);
            }
            if (XZ.equalsIgnoreCase(str)) {
                return new XZCompressorOutputStream(outputStream);
            }
            if (PACK200.equalsIgnoreCase(str)) {
                return new Pack200CompressorOutputStream(outputStream);
            }
            throw new CompressorException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Compressor: ", str, " not found."));
        } catch (IOException e) {
            throw new CompressorException("Could not create CompressorOutputStream", e);
        }
    }

    public void setDecompressConcatenated(boolean z) {
        this.decompressConcatenated = z;
    }

    public CompressorInputStream createCompressorInputStream(String str, InputStream inputStream) throws CompressorException {
        if (str != null && inputStream != null) {
            try {
                if (GZIP.equalsIgnoreCase(str)) {
                    return new GzipCompressorInputStream(inputStream, false);
                }
                if (BZIP2.equalsIgnoreCase(str)) {
                    return new BZip2CompressorInputStream(inputStream, false);
                }
                if (XZ.equalsIgnoreCase(str)) {
                    return new XZCompressorInputStream(inputStream, false);
                }
                if (PACK200.equalsIgnoreCase(str)) {
                    return new Pack200CompressorInputStream(inputStream);
                }
                throw new CompressorException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Compressor: ", str, " not found."));
            } catch (IOException e) {
                throw new CompressorException("Could not create CompressorInputStream.", e);
            }
        }
        throw new IllegalArgumentException("Compressor name and stream must not be null.");
    }
}
