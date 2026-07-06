package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class TempFileCachingStreamBridge extends StreamBridge {
    public final File f;

    public TempFileCachingStreamBridge() throws IOException {
        super(null);
        File fileCreateTempFile = File.createTempFile("commons-compress", "packtemp");
        this.f = fileCreateTempFile;
        fileCreateTempFile.deleteOnExit();
        ((FilterOutputStream) this).out = new FileOutputStream(fileCreateTempFile);
    }

    @Override // org.apache.commons.compress.compressors.pack200.StreamBridge
    public InputStream getInputView() throws IOException {
        ((FilterOutputStream) this).out.close();
        return new FileInputStream(this.f) { // from class: org.apache.commons.compress.compressors.pack200.TempFileCachingStreamBridge.1
            @Override // java.io.FileInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                super.close();
                TempFileCachingStreamBridge.this.f.delete();
            }
        };
    }
}
