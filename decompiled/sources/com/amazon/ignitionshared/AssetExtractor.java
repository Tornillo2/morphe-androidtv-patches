package com.amazon.ignitionshared;

import android.content.res.AssetManager;
import android.system.ErrnoException;
import android.system.Os;
import com.amazon.reporting.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AssetExtractor {
    public static final String TAG = "AssetExtractor";

    public static boolean extractGzAsset(AssetManager assetManager, String str, String str2) {
        try {
            InputStream inputStreamOpen = assetManager.open(str);
            try {
                GzipCompressorInputStream gzipCompressorInputStream = new GzipCompressorInputStream(new BufferedInputStream(inputStreamOpen), false);
                try {
                    unTarStreamToFilesystem(gzipCompressorInputStream, str2);
                    gzipCompressorInputStream.close();
                    if (inputStreamOpen == null) {
                        return true;
                    }
                    inputStreamOpen.close();
                    return true;
                } finally {
                }
            } finally {
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception extracting assets: " + e.getMessage());
            return false;
        }
    }

    public static void unTarStreamToFilesystem(InputStream inputStream, String str) throws IOException, ErrnoException {
        byte[] bArr = new byte[4096];
        File file = new File(str);
        TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(inputStream);
        while (true) {
            try {
                TarArchiveEntry nextTarEntry = tarArchiveInputStream.getNextTarEntry();
                if (nextTarEntry == null) {
                    tarArchiveInputStream.close();
                    return;
                }
                if (nextTarEntry.isSymbolicLink()) {
                    File file2 = new File(file, nextTarEntry.getName());
                    if (file2.exists()) {
                        file2.delete();
                    }
                    Os.symlink(nextTarEntry.getLinkName(), file2.getPath());
                } else {
                    File file3 = new File(file, nextTarEntry.getName());
                    if (nextTarEntry.isDirectory()) {
                        Log.v(TAG, "MKDIR " + file3.getPath());
                        if (!file3.exists() && !file3.mkdirs()) {
                            throw new IllegalStateException(String.format("Couldn't create directory %s.", file3.getAbsolutePath()));
                        }
                    } else {
                        Log.v(TAG, "COPY " + file3.getPath());
                        FileOutputStream fileOutputStream = new FileOutputStream(file3);
                        while (true) {
                            try {
                                int i = tarArchiveInputStream.read(bArr);
                                if (i == -1) {
                                    break;
                                } else {
                                    fileOutputStream.write(bArr, 0, i);
                                }
                            } finally {
                            }
                        }
                        fileOutputStream.close();
                    }
                }
            } catch (Throwable th) {
                try {
                    tarArchiveInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }
}
