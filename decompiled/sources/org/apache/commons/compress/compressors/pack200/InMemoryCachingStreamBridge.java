package org.apache.commons.compress.compressors.pack200;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class InMemoryCachingStreamBridge extends StreamBridge {
    public InMemoryCachingStreamBridge() {
        super(new ByteArrayOutputStream());
    }

    @Override // org.apache.commons.compress.compressors.pack200.StreamBridge
    public InputStream getInputView() throws IOException {
        return new ByteArrayInputStream(((ByteArrayOutputStream) ((FilterOutputStream) this).out).toByteArray());
    }
}
