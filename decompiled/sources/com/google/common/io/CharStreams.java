package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class CharStreams {
    public static final int DEFAULT_BUF_SIZE = 2048;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class NullWriter extends Writer {
        public static final NullWriter INSTANCE = new NullWriter();

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(char c) {
            return this;
        }

        public String toString() {
            return "CharStreams.nullWriter()";
        }

        @Override // java.io.Writer
        public void write(int c) {
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence csq) {
            return this;
        }

        @Override // java.io.Writer
        public void write(char[] cbuf, int off, int len) {
            Preconditions.checkPositionIndexes(off, len + off, cbuf.length);
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Appendable append(char c) throws IOException {
            return this;
        }

        @Override // java.io.Writer
        public void write(String str, int off, int len) {
            Preconditions.checkPositionIndexes(off, len + off, str.length());
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Appendable append(CharSequence csq) throws IOException {
            return this;
        }

        @Override // java.io.Writer
        public void write(String str) {
            str.getClass();
        }

        @Override // java.io.Writer, java.lang.Appendable
        public /* bridge */ /* synthetic */ Appendable append(CharSequence csq, int start, int end) throws IOException {
            append(csq, start, end);
            return this;
        }

        @Override // java.io.Writer
        public void write(char[] cbuf) {
            cbuf.getClass();
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence csq, int start, int end) {
            Preconditions.checkPositionIndexes(start, end, csq == null ? 4 : csq.length());
            return this;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }
    }

    public static Writer asWriter(Appendable target) {
        return target instanceof Writer ? (Writer) target : new AppendableWriter(target);
    }

    @CanIgnoreReturnValue
    public static long copy(Readable from, Appendable to) throws IOException {
        if (from instanceof Reader) {
            return to instanceof StringBuilder ? copyReaderToBuilder((Reader) from, (StringBuilder) to) : copyReaderToWriter((Reader) from, asWriter(to));
        }
        from.getClass();
        to.getClass();
        CharBuffer charBufferAllocate = CharBuffer.allocate(2048);
        long jRemaining = 0;
        while (from.read(charBufferAllocate) != -1) {
            charBufferAllocate.flip();
            to.append(charBufferAllocate);
            jRemaining += (long) charBufferAllocate.remaining();
            charBufferAllocate.clear();
        }
        return jRemaining;
    }

    @CanIgnoreReturnValue
    public static long copyReaderToBuilder(Reader from, StringBuilder to) throws IOException {
        from.getClass();
        to.getClass();
        char[] cArr = new char[2048];
        long j = 0;
        while (true) {
            int i = from.read(cArr);
            if (i == -1) {
                return j;
            }
            to.append(cArr, 0, i);
            j += (long) i;
        }
    }

    @CanIgnoreReturnValue
    public static long copyReaderToWriter(Reader from, Writer to) throws IOException {
        from.getClass();
        to.getClass();
        char[] cArr = new char[2048];
        long j = 0;
        while (true) {
            int i = from.read(cArr);
            if (i == -1) {
                return j;
            }
            to.write(cArr, 0, i);
            j += (long) i;
        }
    }

    public static CharBuffer createBuffer() {
        return CharBuffer.allocate(2048);
    }

    @CanIgnoreReturnValue
    public static long exhaust(Readable readable) throws IOException {
        CharBuffer charBufferAllocate = CharBuffer.allocate(2048);
        long j = 0;
        while (true) {
            long j2 = readable.read(charBufferAllocate);
            if (j2 == -1) {
                return j;
            }
            j += j2;
            charBufferAllocate.clear();
        }
    }

    public static Writer nullWriter() {
        return NullWriter.INSTANCE;
    }

    public static List<String> readLines(Readable r) throws IOException {
        ArrayList arrayList = new ArrayList();
        LineReader lineReader = new LineReader(r);
        while (true) {
            String line = lineReader.readLine();
            if (line == null) {
                return arrayList;
            }
            arrayList.add(line);
        }
    }

    public static void skipFully(Reader reader, long n) throws IOException {
        reader.getClass();
        while (n > 0) {
            long jSkip = reader.skip(n);
            if (jSkip == 0) {
                throw new EOFException();
            }
            n -= jSkip;
        }
    }

    public static String toString(Readable r) throws IOException {
        return toStringBuilder(r).toString();
    }

    public static StringBuilder toStringBuilder(Readable r) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (r instanceof Reader) {
            copyReaderToBuilder((Reader) r, sb);
            return sb;
        }
        copy(r, sb);
        return sb;
    }

    @CanIgnoreReturnValue
    @ParametricNullness
    public static <T> T readLines(Readable readable, LineProcessor<T> processor) throws IOException {
        readable.getClass();
        processor.getClass();
        LineReader lineReader = new LineReader(readable);
        while (true) {
            String line = lineReader.readLine();
            if (line != null) {
                processor.processLine(line);
            } else {
                return processor.getResult();
            }
        }
    }
}
