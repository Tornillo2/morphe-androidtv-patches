package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public abstract class ByteSink {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class AsCharSink extends CharSink {
        public final Charset charset;

        @Override // com.google.common.io.CharSink
        public Writer openStream() throws IOException {
            return new OutputStreamWriter(ByteSink.this.openStream(), this.charset);
        }

        public String toString() {
            return ByteSink.this.toString() + ".asCharSink(" + this.charset + ")";
        }

        public AsCharSink(Charset charset) {
            charset.getClass();
            this.charset = charset;
        }
    }

    public CharSink asCharSink(Charset charset) {
        return new AsCharSink(charset);
    }

    public OutputStream openBufferedStream() throws IOException {
        OutputStream outputStreamOpenStream = openStream();
        return outputStreamOpenStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStreamOpenStream : new BufferedOutputStream(outputStreamOpenStream);
    }

    public abstract OutputStream openStream() throws IOException;

    public void write(byte[] bytes) throws IOException {
        bytes.getClass();
        OutputStream outputStreamOpenStream = openStream();
        try {
            outputStreamOpenStream.write(bytes);
            outputStreamOpenStream.close();
        } catch (Throwable th) {
            if (outputStreamOpenStream != null) {
                try {
                    outputStreamOpenStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @CanIgnoreReturnValue
    public long writeFrom(InputStream input) throws IOException {
        input.getClass();
        OutputStream outputStreamOpenStream = openStream();
        try {
            long jCopy = ByteStreams.copy(input, outputStreamOpenStream);
            outputStreamOpenStream.close();
            return jCopy;
        } catch (Throwable th) {
            if (outputStreamOpenStream != null) {
                try {
                    outputStreamOpenStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
