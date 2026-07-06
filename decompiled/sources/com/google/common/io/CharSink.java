package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.StandardSystemProperty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.stream.Stream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public abstract class CharSink {
    public Writer openBufferedStream() throws IOException {
        Writer writerOpenStream = openStream();
        return writerOpenStream instanceof BufferedWriter ? (BufferedWriter) writerOpenStream : new BufferedWriter(writerOpenStream);
    }

    public abstract Writer openStream() throws IOException;

    public void write(CharSequence charSequence) throws IOException {
        charSequence.getClass();
        Writer writerOpenStream = openStream();
        try {
            writerOpenStream.append(charSequence);
            writerOpenStream.close();
        } catch (Throwable th) {
            try {
                writerOpenStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @CanIgnoreReturnValue
    public long writeFrom(Readable readable) throws IOException {
        readable.getClass();
        Writer writerOpenStream = openStream();
        try {
            long jCopy = CharStreams.copy(readable, writerOpenStream);
            writerOpenStream.close();
            return jCopy;
        } catch (Throwable th) {
            try {
                writerOpenStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void writeLines(Iterable<? extends CharSequence> lines) throws IOException {
        writeLines(lines, System.getProperty("line.separator"));
    }

    public void writeLines(Iterable<? extends CharSequence> lines, String lineSeparator) throws IOException {
        writeLines(lines.iterator(), lineSeparator);
    }

    @IgnoreJRERequirement
    public void writeLines(Stream<? extends CharSequence> lines) throws IOException {
        writeLines(lines, System.getProperty(StandardSystemProperty.LINE_SEPARATOR.key));
    }

    @IgnoreJRERequirement
    public void writeLines(Stream<? extends CharSequence> lines, String lineSeparator) throws IOException {
        writeLines(lines.iterator(), lineSeparator);
    }

    public final void writeLines(Iterator<? extends CharSequence> lines, String lineSeparator) throws IOException {
        lineSeparator.getClass();
        Writer writerOpenBufferedStream = openBufferedStream();
        while (lines.hasNext()) {
            try {
                writerOpenBufferedStream.append(lines.next()).append((CharSequence) lineSeparator);
            } catch (Throwable th) {
                try {
                    writerOpenBufferedStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        writerOpenBufferedStream.close();
    }
}
