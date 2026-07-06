package com.google.common.io;

import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Absent;
import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Present;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.io.CharSource;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public abstract class ByteSource {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AsCharSource extends CharSource {
        public final Charset charset;

        public AsCharSource(Charset charset) {
            charset.getClass();
            this.charset = charset;
        }

        @Override // com.google.common.io.CharSource
        public ByteSource asByteSource(Charset charset) {
            return charset.equals(this.charset) ? ByteSource.this : new CharSource.AsByteSource(charset);
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() throws IOException {
            return new InputStreamReader(ByteSource.this.openStream(), this.charset);
        }

        @Override // com.google.common.io.CharSource
        public String read() throws IOException {
            return new String(ByteSource.this.read(), this.charset);
        }

        public String toString() {
            return ByteSource.this.toString() + ".asCharSource(" + this.charset + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ByteArrayByteSource extends ByteSource {
        public final byte[] bytes;
        public final int length;
        public final int offset;

        public ByteArrayByteSource(byte[] bytes, int offset, int length) {
            this.bytes = bytes;
            this.offset = offset;
            this.length = length;
        }

        @Override // com.google.common.io.ByteSource
        public long copyTo(OutputStream output) throws IOException {
            output.write(this.bytes, this.offset, this.length);
            return this.length;
        }

        @Override // com.google.common.io.ByteSource
        public HashCode hash(HashFunction hashFunction) throws IOException {
            return hashFunction.hashBytes(this.bytes, this.offset, this.length);
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() {
            return this.length == 0;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openBufferedStream() {
            return openStream();
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() {
            return new ByteArrayInputStream(this.bytes, this.offset, this.length);
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() {
            byte[] bArr = this.bytes;
            int i = this.offset;
            return Arrays.copyOfRange(bArr, i, this.length + i);
        }

        @Override // com.google.common.io.ByteSource
        public long size() {
            return this.length;
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            return new Present(Long.valueOf(this.length));
        }

        @Override // com.google.common.io.ByteSource
        public ByteSource slice(long offset, long length) {
            Preconditions.checkArgument(offset >= 0, "offset (%s) may not be negative", offset);
            Preconditions.checkArgument(length >= 0, "length (%s) may not be negative", length);
            long jMin = Math.min(offset, this.length);
            return new ByteArrayByteSource(this.bytes, this.offset + ((int) jMin), (int) Math.min(length, ((long) this.length) - jMin));
        }

        public String toString() {
            return "ByteSource.wrap(" + Ascii.truncate(BaseEncoding.base16().encode(this.bytes, this.offset, this.length), 30, "...") + ")";
        }

        @Override // com.google.common.io.ByteSource
        @ParametricNullness
        public <T> T read(ByteProcessor<T> processor) throws IOException {
            processor.processBytes(this.bytes, this.offset, this.length);
            return processor.getResult();
        }

        public ByteArrayByteSource(byte[] bytes) {
            this(bytes, 0, bytes.length);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ConcatenatedByteSource extends ByteSource {
        public final Iterable<? extends ByteSource> sources;

        public ConcatenatedByteSource(Iterable<? extends ByteSource> sources) {
            sources.getClass();
            this.sources = sources;
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() throws IOException {
            Iterator<? extends ByteSource> it = this.sources.iterator();
            while (it.hasNext()) {
                if (!it.next().isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return new MultiInputStream(this.sources.iterator());
        }

        @Override // com.google.common.io.ByteSource
        public long size() throws IOException {
            Iterator<? extends ByteSource> it = this.sources.iterator();
            long size = 0;
            while (it.hasNext()) {
                size += it.next().size();
                if (size < 0) {
                    return Long.MAX_VALUE;
                }
            }
            return size;
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            Iterable<? extends ByteSource> iterable = this.sources;
            if (!(iterable instanceof Collection)) {
                return Absent.INSTANCE;
            }
            Iterator<? extends ByteSource> it = iterable.iterator();
            long jLongValue = 0;
            while (it.hasNext()) {
                Optional<Long> optionalSizeIfKnown = it.next().sizeIfKnown();
                if (!optionalSizeIfKnown.isPresent()) {
                    return Absent.INSTANCE;
                }
                jLongValue += optionalSizeIfKnown.get().longValue();
                if (jLongValue < 0) {
                    return new Present(Long.MAX_VALUE);
                }
            }
            return new Present(Long.valueOf(jLongValue));
        }

        public String toString() {
            return "ByteSource.concat(" + this.sources + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class EmptyByteSource extends ByteArrayByteSource {
        public static final EmptyByteSource INSTANCE = new EmptyByteSource();

        public EmptyByteSource() {
            super(new byte[0], 0, 0);
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset) {
            charset.getClass();
            return CharSource.EmptyCharSource.INSTANCE;
        }

        @Override // com.google.common.io.ByteSource.ByteArrayByteSource, com.google.common.io.ByteSource
        public byte[] read() {
            return this.bytes;
        }

        @Override // com.google.common.io.ByteSource.ByteArrayByteSource
        public String toString() {
            return "ByteSource.empty()";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class SlicedByteSource extends ByteSource {
        public final long length;
        public final long offset;

        public SlicedByteSource(long offset, long length) {
            Preconditions.checkArgument(offset >= 0, "offset (%s) may not be negative", offset);
            Preconditions.checkArgument(length >= 0, "length (%s) may not be negative", length);
            this.offset = offset;
            this.length = length;
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() throws IOException {
            return this.length == 0 || super.isEmpty();
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openBufferedStream() throws IOException {
            return sliceStream(ByteSource.this.openBufferedStream());
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return sliceStream(ByteSource.this.openStream());
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            Optional<Long> optionalSizeIfKnown = ByteSource.this.sizeIfKnown();
            if (!optionalSizeIfKnown.isPresent()) {
                return Absent.INSTANCE;
            }
            long jLongValue = optionalSizeIfKnown.get().longValue();
            return new Present(Long.valueOf(Math.min(this.length, jLongValue - Math.min(this.offset, jLongValue))));
        }

        @Override // com.google.common.io.ByteSource
        public ByteSource slice(long offset, long length) {
            Preconditions.checkArgument(offset >= 0, "offset (%s) may not be negative", offset);
            Preconditions.checkArgument(length >= 0, "length (%s) may not be negative", length);
            long j = this.length - offset;
            return j <= 0 ? EmptyByteSource.INSTANCE : ByteSource.this.slice(this.offset + offset, Math.min(length, j));
        }

        public final InputStream sliceStream(InputStream in) throws Throwable {
            long j = this.offset;
            if (j > 0) {
                try {
                    if (ByteStreams.skipUpTo(in, j) < this.offset) {
                        in.close();
                        return new ByteArrayInputStream(new byte[0]);
                    }
                } finally {
                }
            }
            return ByteStreams.limit(in, this.length);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(ByteSource.this.toString());
            sb.append(".slice(");
            sb.append(this.offset);
            sb.append(", ");
            return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(sb, this.length, ")");
        }
    }

    public static ByteSource concat(Iterable<? extends ByteSource> sources) {
        return new ConcatenatedByteSource(sources);
    }

    public static ByteSource empty() {
        return EmptyByteSource.INSTANCE;
    }

    public static ByteSource wrap(byte[] b) {
        return new ByteArrayByteSource(b);
    }

    public CharSource asCharSource(Charset charset) {
        return new AsCharSource(charset);
    }

    public boolean contentEquals(ByteSource other) throws Throwable {
        int i;
        other.getClass();
        byte[] bArrCreateBuffer = ByteStreams.createBuffer();
        byte[] bArr = new byte[8192];
        Closer closerCreate = Closer.create();
        try {
            InputStream inputStreamOpenStream = openStream();
            closerCreate.register(inputStreamOpenStream);
            InputStream inputStreamOpenStream2 = other.openStream();
            closerCreate.register(inputStreamOpenStream2);
            do {
                i = ByteStreams.read(inputStreamOpenStream, bArrCreateBuffer, 0, 8192);
                if (i != ByteStreams.read(inputStreamOpenStream2, bArr, 0, 8192) || !Arrays.equals(bArrCreateBuffer, bArr)) {
                    return false;
                }
            } while (i == 8192);
            closerCreate.close();
            return true;
        } finally {
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(ByteSink sink) throws Throwable {
        sink.getClass();
        Closer closerCreate = Closer.create();
        try {
            InputStream inputStreamOpenStream = openStream();
            closerCreate.register(inputStreamOpenStream);
            OutputStream outputStreamOpenStream = sink.openStream();
            closerCreate.register(outputStreamOpenStream);
            return ByteStreams.copy(inputStreamOpenStream, outputStreamOpenStream);
        } finally {
        }
    }

    public final long countBySkipping(InputStream in) throws IOException {
        long j = 0;
        while (true) {
            long jSkipUpTo = ByteStreams.skipUpTo(in, 2147483647L);
            if (jSkipUpTo <= 0) {
                return j;
            }
            j += jSkipUpTo;
        }
    }

    public HashCode hash(HashFunction hashFunction) throws Throwable {
        Hasher hasherNewHasher = hashFunction.newHasher();
        copyTo(new Funnels.SinkAsStream(hasherNewHasher));
        return hasherNewHasher.hash();
    }

    public boolean isEmpty() throws Throwable {
        Optional<Long> optionalSizeIfKnown = sizeIfKnown();
        if (optionalSizeIfKnown.isPresent()) {
            return optionalSizeIfKnown.get().longValue() == 0;
        }
        Closer closerCreate = Closer.create();
        try {
            InputStream inputStreamOpenStream = openStream();
            closerCreate.register(inputStreamOpenStream);
            return inputStreamOpenStream.read() == -1;
        } finally {
        }
    }

    public InputStream openBufferedStream() throws IOException {
        InputStream inputStreamOpenStream = openStream();
        return inputStreamOpenStream instanceof BufferedInputStream ? (BufferedInputStream) inputStreamOpenStream : new BufferedInputStream(inputStreamOpenStream);
    }

    public abstract InputStream openStream() throws IOException;

    public byte[] read() throws Throwable {
        Closer closerCreate = Closer.create();
        try {
            InputStream inputStreamOpenStream = openStream();
            closerCreate.register(inputStreamOpenStream);
            Optional<Long> optionalSizeIfKnown = sizeIfKnown();
            return optionalSizeIfKnown.isPresent() ? ByteStreams.toByteArray(inputStreamOpenStream, optionalSizeIfKnown.get().longValue()) : ByteStreams.toByteArray(inputStreamOpenStream);
        } catch (Throwable th) {
            try {
                closerCreate.rethrow(th);
                throw null;
            } finally {
                closerCreate.close();
            }
        }
    }

    public long size() throws Throwable {
        Optional<Long> optionalSizeIfKnown = sizeIfKnown();
        if (optionalSizeIfKnown.isPresent()) {
            return optionalSizeIfKnown.get().longValue();
        }
        Closer closerCreate = Closer.create();
        try {
            InputStream inputStreamOpenStream = openStream();
            closerCreate.register(inputStreamOpenStream);
            return countBySkipping(inputStreamOpenStream);
        } catch (IOException unused) {
            closerCreate.close();
            closerCreate = Closer.create();
            try {
                InputStream inputStreamOpenStream2 = openStream();
                closerCreate.register(inputStreamOpenStream2);
                return ByteStreams.exhaust(inputStreamOpenStream2);
            } finally {
            }
        } finally {
        }
    }

    public Optional<Long> sizeIfKnown() {
        return Absent.INSTANCE;
    }

    public ByteSource slice(long offset, long length) {
        return new SlicedByteSource(offset, length);
    }

    public static ByteSource concat(Iterator<? extends ByteSource> sources) {
        return new ConcatenatedByteSource(ImmutableList.copyOf(sources));
    }

    public static ByteSource concat(ByteSource... sources) {
        return new ConcatenatedByteSource(ImmutableList.copyOf(sources));
    }

    @CanIgnoreReturnValue
    public long copyTo(OutputStream output) throws Throwable {
        output.getClass();
        Closer closerCreate = Closer.create();
        try {
            InputStream inputStreamOpenStream = openStream();
            closerCreate.register(inputStreamOpenStream);
            return ByteStreams.copy(inputStreamOpenStream, output);
        } finally {
        }
    }

    @CanIgnoreReturnValue
    @ParametricNullness
    public <T> T read(ByteProcessor<T> byteProcessor) throws Throwable {
        byteProcessor.getClass();
        Closer closerCreate = Closer.create();
        try {
            InputStream inputStreamOpenStream = openStream();
            closerCreate.register(inputStreamOpenStream);
            return (T) ByteStreams.readBytes(inputStreamOpenStream, byteProcessor);
        } finally {
        }
    }
}
