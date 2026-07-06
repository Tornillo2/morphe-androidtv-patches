package org.apache.commons.compress.compressors.pack200;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class StreamBridge extends FilterOutputStream {
    public final Object INPUT_LOCK;
    public InputStream input;

    public StreamBridge(OutputStream outputStream) {
        super(outputStream);
        this.INPUT_LOCK = new Object();
    }

    public InputStream getInput() throws IOException {
        synchronized (this.INPUT_LOCK) {
            try {
                if (this.input == null) {
                    this.input = getInputView();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return this.input;
    }

    public abstract InputStream getInputView() throws IOException;

    public void stop() throws IOException {
        close();
        synchronized (this.INPUT_LOCK) {
            try {
                InputStream inputStream = this.input;
                if (inputStream != null) {
                    inputStream.close();
                    this.input = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public StreamBridge() {
        this(null);
    }
}
