package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Absent;
import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.base.Present;
import com.google.common.base.Splitter;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Streams;
import com.google.common.io.ByteSource;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.MustBeClosed;
import j$.io.BufferedReaderRetargetClass;
import j$.util.stream.Stream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public abstract class CharSource {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class AsByteSource extends ByteSource {
        public final Charset charset;

        public AsByteSource(Charset charset) {
            charset.getClass();
            this.charset = charset;
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset) {
            return charset.equals(this.charset) ? CharSource.this : new ByteSource.AsCharSource(charset);
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return new ReaderInputStream(CharSource.this.openStream(), this.charset, 8192);
        }

        public String toString() {
            return CharSource.this.toString() + ".asByteSource(" + this.charset + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ConcatenatedCharSource extends CharSource {
        public final Iterable<? extends CharSource> sources;

        public ConcatenatedCharSource(Iterable<? extends CharSource> sources) {
            sources.getClass();
            this.sources = sources;
        }

        @Override // com.google.common.io.CharSource
        public boolean isEmpty() throws IOException {
            Iterator<? extends CharSource> it = this.sources.iterator();
            while (it.hasNext()) {
                if (!it.next().isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.CharSource
        public long length() throws IOException {
            Iterator<? extends CharSource> it = this.sources.iterator();
            long length = 0;
            while (it.hasNext()) {
                length += it.next().length();
            }
            return length;
        }

        @Override // com.google.common.io.CharSource
        public Optional<Long> lengthIfKnown() {
            Iterator<? extends CharSource> it = this.sources.iterator();
            long jLongValue = 0;
            while (it.hasNext()) {
                Optional<Long> optionalLengthIfKnown = it.next().lengthIfKnown();
                if (!optionalLengthIfKnown.isPresent()) {
                    return Absent.INSTANCE;
                }
                jLongValue += optionalLengthIfKnown.get().longValue();
            }
            return new Present(Long.valueOf(jLongValue));
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() throws IOException {
            return new MultiReader(this.sources.iterator());
        }

        public String toString() {
            return "CharSource.concat(" + this.sources + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class EmptyCharSource extends StringCharSource {
        public static final EmptyCharSource INSTANCE = new EmptyCharSource();

        public EmptyCharSource() {
            super((CharSequence) "");
        }

        @Override // com.google.common.io.CharSource.CharSequenceCharSource
        public String toString() {
            return "CharSource.empty()";
        }
    }

    @IgnoreJRERequirement
    public static void closeUnchecked(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static CharSource concat(Iterable<? extends CharSource> sources) {
        return new ConcatenatedCharSource(sources);
    }

    public static CharSource empty() {
        return EmptyCharSource.INSTANCE;
    }

    public static CharSource wrap(CharSequence charSequence) {
        return charSequence instanceof String ? new StringCharSource(charSequence) : new CharSequenceCharSource(charSequence);
    }

    public ByteSource asByteSource(Charset charset) {
        return new AsByteSource(charset);
    }

    @CanIgnoreReturnValue
    public long copyTo(CharSink sink) throws Throwable {
        sink.getClass();
        Closer closerCreate = Closer.create();
        try {
            Reader readerOpenStream = openStream();
            closerCreate.register(readerOpenStream);
            Writer writerOpenStream = sink.openStream();
            closerCreate.register(writerOpenStream);
            return CharStreams.copy(readerOpenStream, writerOpenStream);
        } finally {
        }
    }

    public final long countBySkipping(Reader reader) throws IOException {
        long j = 0;
        while (true) {
            long jSkip = reader.skip(Long.MAX_VALUE);
            if (jSkip == 0) {
                return j;
            }
            j += jSkip;
        }
    }

    @IgnoreJRERequirement
    public void forEachLine(Consumer<? super String> action) throws IOException {
        try {
            Stream<String> streamLines = lines();
            try {
                streamLines.forEachOrdered(action);
                streamLines.close();
            } finally {
            }
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }

    public boolean isEmpty() throws Throwable {
        Optional<Long> optionalLengthIfKnown = lengthIfKnown();
        if (optionalLengthIfKnown.isPresent()) {
            return optionalLengthIfKnown.get().longValue() == 0;
        }
        Closer closerCreate = Closer.create();
        try {
            Reader readerOpenStream = openStream();
            closerCreate.register(readerOpenStream);
            return readerOpenStream.read() == -1;
        } finally {
        }
    }

    public long length() throws Throwable {
        Optional<Long> optionalLengthIfKnown = lengthIfKnown();
        if (optionalLengthIfKnown.isPresent()) {
            return optionalLengthIfKnown.get().longValue();
        }
        Closer closerCreate = Closer.create();
        try {
            Reader readerOpenStream = openStream();
            closerCreate.register(readerOpenStream);
            return countBySkipping(readerOpenStream);
        } finally {
        }
    }

    public Optional<Long> lengthIfKnown() {
        return Absent.INSTANCE;
    }

    @IgnoreJRERequirement
    @MustBeClosed
    public Stream<String> lines() throws IOException {
        final BufferedReader bufferedReaderOpenBufferedStream = openBufferedStream();
        return (Stream) BufferedReaderRetargetClass.lines(bufferedReaderOpenBufferedStream).onClose(new Runnable() { // from class: com.google.common.io.CharSource$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CharSource.closeUnchecked(bufferedReaderOpenBufferedStream);
            }
        });
    }

    public BufferedReader openBufferedStream() throws IOException {
        Reader readerOpenStream = openStream();
        return readerOpenStream instanceof BufferedReader ? (BufferedReader) readerOpenStream : new BufferedReader(readerOpenStream);
    }

    public abstract Reader openStream() throws IOException;

    public String read() throws Throwable {
        Closer closerCreate = Closer.create();
        try {
            Reader readerOpenStream = openStream();
            closerCreate.register(readerOpenStream);
            return CharStreams.toString(readerOpenStream);
        } finally {
        }
    }

    public String readFirstLine() throws Throwable {
        Closer closerCreate = Closer.create();
        try {
            BufferedReader bufferedReaderOpenBufferedStream = openBufferedStream();
            closerCreate.register(bufferedReaderOpenBufferedStream);
            return bufferedReaderOpenBufferedStream.readLine();
        } finally {
        }
    }

    public ImmutableList<String> readLines() throws Throwable {
        Closer closerCreate = Closer.create();
        try {
            BufferedReader bufferedReaderOpenBufferedStream = openBufferedStream();
            closerCreate.register(bufferedReaderOpenBufferedStream);
            ArrayList arrayList = new ArrayList();
            while (true) {
                String line = bufferedReaderOpenBufferedStream.readLine();
                if (line == null) {
                    return ImmutableList.copyOf((Collection) arrayList);
                }
                arrayList.add(line);
            }
        } finally {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CharSequenceCharSource extends CharSource {
        public static final Splitter LINE_SPLITTER = Splitter.onPatternInternal(Platform.patternCompiler.compile("\r\n|\n|\r"));
        public final CharSequence seq;

        /* JADX INFO: renamed from: com.google.common.io.CharSource$CharSequenceCharSource$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 extends AbstractIterator<String> {
            public Iterator<String> lines;

            public AnonymousClass1() {
                this.lines = ((Splitter.AnonymousClass5) CharSequenceCharSource.LINE_SPLITTER.split(CharSequenceCharSource.this.seq)).iterator();
            }

            @Override // com.google.common.collect.AbstractIterator
            public String computeNext() {
                if (this.lines.hasNext()) {
                    String next = this.lines.next();
                    if (this.lines.hasNext() || !next.isEmpty()) {
                        return next;
                    }
                }
                endOfData();
                return null;
            }
        }

        public CharSequenceCharSource(CharSequence seq) {
            seq.getClass();
            this.seq = seq;
        }

        @Override // com.google.common.io.CharSource
        public boolean isEmpty() {
            return this.seq.length() == 0;
        }

        @Override // com.google.common.io.CharSource
        public long length() {
            return this.seq.length();
        }

        @Override // com.google.common.io.CharSource
        public Optional<Long> lengthIfKnown() {
            return new Present(Long.valueOf(this.seq.length()));
        }

        @Override // com.google.common.io.CharSource
        @IgnoreJRERequirement
        public Stream<String> lines() {
            return Streams.stream(new AnonymousClass1());
        }

        public final Iterator<String> linesIterator() {
            return new AnonymousClass1();
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() {
            return new CharSequenceReader(this.seq);
        }

        @Override // com.google.common.io.CharSource
        public String read() {
            return this.seq.toString();
        }

        @Override // com.google.common.io.CharSource
        public String readFirstLine() {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            if (anonymousClass1.hasNext()) {
                return anonymousClass1.next();
            }
            return null;
        }

        @Override // com.google.common.io.CharSource
        public ImmutableList<String> readLines() {
            return ImmutableList.copyOf(new AnonymousClass1());
        }

        public String toString() {
            return "CharSource.wrap(" + Ascii.truncate(this.seq, 30, "...") + ")";
        }

        @Override // com.google.common.io.CharSource
        @ParametricNullness
        public <T> T readLines(LineProcessor<T> processor) throws IOException {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            while (anonymousClass1.hasNext()) {
                processor.processLine(anonymousClass1.next());
            }
            return processor.getResult();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class StringCharSource extends CharSequenceCharSource {
        public StringCharSource(String seq) {
            super(seq);
        }

        @Override // com.google.common.io.CharSource
        public long copyTo(Appendable appendable) throws IOException {
            appendable.append(this.seq);
            return this.seq.length();
        }

        @Override // com.google.common.io.CharSource.CharSequenceCharSource, com.google.common.io.CharSource
        public Reader openStream() {
            return new StringReader((String) this.seq);
        }

        @Override // com.google.common.io.CharSource
        public long copyTo(CharSink sink) throws Throwable {
            sink.getClass();
            Closer closerCreate = Closer.create();
            try {
                Writer writerOpenStream = sink.openStream();
                closerCreate.register(writerOpenStream);
                writerOpenStream.write((String) this.seq);
                return this.seq.length();
            } finally {
            }
        }
    }

    public static CharSource concat(Iterator<? extends CharSource> sources) {
        return new ConcatenatedCharSource(ImmutableList.copyOf(sources));
    }

    public static CharSource concat(CharSource... sources) {
        return new ConcatenatedCharSource(ImmutableList.copyOf(sources));
    }

    @CanIgnoreReturnValue
    public long copyTo(Appendable appendable) throws Throwable {
        appendable.getClass();
        Closer closerCreate = Closer.create();
        try {
            Reader readerOpenStream = openStream();
            closerCreate.register(readerOpenStream);
            return CharStreams.copy(readerOpenStream, appendable);
        } finally {
        }
    }

    @CanIgnoreReturnValue
    @ParametricNullness
    public <T> T readLines(LineProcessor<T> lineProcessor) throws Throwable {
        lineProcessor.getClass();
        Closer closerCreate = Closer.create();
        try {
            Reader readerOpenStream = openStream();
            closerCreate.register(readerOpenStream);
            return (T) CharStreams.readLines(readerOpenStream, lineProcessor);
        } finally {
        }
    }
}
