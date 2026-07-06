package com.google.common.io;

import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline0;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public final class ByteStreams {
    public static final int BUFFER_SIZE = 8192;
    public static final int MAX_ARRAY_LEN = 2147483639;
    public static final OutputStream NULL_OUTPUT_STREAM = new AnonymousClass1();
    public static final int TO_BYTE_ARRAY_DEQUE_SIZE = 20;
    public static final int ZERO_COPY_CHUNK_SIZE = 524288;

    /* JADX INFO: renamed from: com.google.common.io.ByteStreams$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends OutputStream {
        public String toString() {
            return "ByteStreams.nullOutputStream()";
        }

        @Override // java.io.OutputStream
        public void write(int b) {
        }

        @Override // java.io.OutputStream
        public void write(byte[] b) {
            b.getClass();
        }

        @Override // java.io.OutputStream
        public void write(byte[] b, int off, int len) {
            b.getClass();
            Preconditions.checkPositionIndexes(off, len + off, b.length);
        }
    }

    public static byte[] combineBuffers(Queue<byte[]> bufs, int totalLen) {
        if (bufs.isEmpty()) {
            return new byte[0];
        }
        byte[] bArrRemove = bufs.remove();
        if (bArrRemove.length == totalLen) {
            return bArrRemove;
        }
        int length = totalLen - bArrRemove.length;
        byte[] bArrCopyOf = Arrays.copyOf(bArrRemove, totalLen);
        while (length > 0) {
            byte[] bArrRemove2 = bufs.remove();
            int iMin = Math.min(length, bArrRemove2.length);
            System.arraycopy(bArrRemove2, 0, bArrCopyOf, totalLen - length, iMin);
            length -= iMin;
        }
        return bArrCopyOf;
    }

    @CanIgnoreReturnValue
    public static long copy(InputStream from, OutputStream to) throws IOException {
        from.getClass();
        to.getClass();
        byte[] bArr = new byte[8192];
        long j = 0;
        while (true) {
            int i = from.read(bArr);
            if (i == -1) {
                return j;
            }
            to.write(bArr, 0, i);
            j += (long) i;
        }
    }

    public static byte[] createBuffer() {
        return new byte[8192];
    }

    @CanIgnoreReturnValue
    public static long exhaust(InputStream in) throws IOException {
        byte[] bArr = new byte[8192];
        long j = 0;
        while (true) {
            long j2 = in.read(bArr);
            if (j2 == -1) {
                return j;
            }
            j += j2;
        }
    }

    @J2ktIncompatible
    public static InputStream limit(InputStream in, long limit) {
        return new LimitedInputStream(in, limit);
    }

    @J2ktIncompatible
    public static ByteArrayDataInput newDataInput(byte[] bytes) {
        return new ByteArrayDataInputStream(new ByteArrayInputStream(bytes));
    }

    @J2ktIncompatible
    public static ByteArrayDataOutput newDataOutput() {
        return new ByteArrayDataOutputStream(new ByteArrayOutputStream());
    }

    public static OutputStream nullOutputStream() {
        return NULL_OUTPUT_STREAM;
    }

    @CanIgnoreReturnValue
    public static int read(InputStream in, byte[] b, int off, int len) throws IOException {
        in.getClass();
        b.getClass();
        int i = 0;
        if (len < 0) {
            throw new IndexOutOfBoundsException(String.format("len (%s) cannot be negative", Integer.valueOf(len)));
        }
        Preconditions.checkPositionIndexes(off, off + len, b.length);
        while (i < len) {
            int i2 = in.read(b, off + i, len - i);
            if (i2 == -1) {
                break;
            }
            i += i2;
        }
        return i;
    }

    @CanIgnoreReturnValue
    @J2ktIncompatible
    @ParametricNullness
    public static <T> T readBytes(InputStream input, ByteProcessor<T> processor) throws IOException {
        int i;
        input.getClass();
        processor.getClass();
        byte[] bArr = new byte[8192];
        do {
            i = input.read(bArr);
            if (i == -1) {
                break;
            }
        } while (processor.processBytes(bArr, 0, i));
        return processor.getResult();
    }

    public static void readFully(InputStream in, byte[] b) throws IOException {
        readFully(in, b, 0, b.length);
    }

    public static void skipFully(InputStream in, long n) throws IOException {
        long jSkipUpTo = skipUpTo(in, n);
        if (jSkipUpTo < n) {
            throw new EOFException(MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(AbstractResolvableFuture$$ExternalSyntheticOutline2.m("reached end of stream after skipping ", jSkipUpTo, " bytes; "), n, " bytes expected"));
        }
    }

    public static long skipSafely(InputStream in, long n) throws IOException {
        int iAvailable = in.available();
        if (iAvailable == 0) {
            return 0L;
        }
        return in.skip(Math.min(iAvailable, n));
    }

    public static long skipUpTo(InputStream in, long n) throws IOException {
        byte[] bArr = null;
        long j = 0;
        while (j < n) {
            long j2 = n - j;
            long jSkipSafely = skipSafely(in, j2);
            if (jSkipSafely == 0) {
                int iMin = (int) Math.min(j2, 8192L);
                if (bArr == null) {
                    bArr = new byte[iMin];
                }
                jSkipSafely = in.read(bArr, 0, iMin);
                if (jSkipSafely == -1) {
                    break;
                }
            }
            j += jSkipSafely;
        }
        return j;
    }

    public static byte[] toByteArray(InputStream in, long expectedSize) throws IOException {
        Preconditions.checkArgument(expectedSize >= 0, "expectedSize (%s) must be non-negative", expectedSize);
        if (expectedSize > 2147483639) {
            throw new OutOfMemoryError(expectedSize + " bytes is too large to fit in a byte array");
        }
        int i = (int) expectedSize;
        byte[] bArr = new byte[i];
        int i2 = i;
        while (i2 > 0) {
            int i3 = i - i2;
            int i4 = in.read(bArr, i3, i2);
            if (i4 == -1) {
                return Arrays.copyOf(bArr, i3);
            }
            i2 -= i4;
        }
        int i5 = in.read();
        if (i5 == -1) {
            return bArr;
        }
        ArrayDeque arrayDeque = new ArrayDeque(22);
        arrayDeque.add(bArr);
        arrayDeque.add(new byte[]{(byte) i5});
        return toByteArrayInternal(in, arrayDeque, i + 1);
    }

    public static byte[] toByteArrayInternal(InputStream in, Queue<byte[]> bufs, int totalLen) throws IOException {
        int iMin = Math.min(8192, Math.max(128, Integer.highestOneBit(totalLen) * 2));
        while (totalLen < 2147483639) {
            int iMin2 = Math.min(iMin, 2147483639 - totalLen);
            byte[] bArr = new byte[iMin2];
            bufs.add(bArr);
            int i = 0;
            while (i < iMin2) {
                int i2 = in.read(bArr, i, iMin2 - i);
                if (i2 == -1) {
                    return combineBuffers(bufs, totalLen);
                }
                i += i2;
                totalLen += i2;
            }
            iMin = IntMath.saturatedMultiply(iMin, iMin < 4096 ? 4 : 2);
        }
        if (in.read() == -1) {
            return combineBuffers(bufs, 2147483639);
        }
        throw new OutOfMemoryError("input is too large to fit in a byte array");
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @J2ktIncompatible
    public static class ByteArrayDataInputStream implements ByteArrayDataInput {
        public final DataInput input;

        public ByteArrayDataInputStream(ByteArrayInputStream byteArrayInputStream) {
            this.input = new DataInputStream(byteArrayInputStream);
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public boolean readBoolean() {
            try {
                return this.input.readBoolean();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public byte readByte() {
            try {
                return this.input.readByte();
            } catch (EOFException e) {
                throw new IllegalStateException(e);
            } catch (IOException e2) {
                throw new AssertionError(e2);
            }
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public char readChar() {
            try {
                return this.input.readChar();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public double readDouble() {
            try {
                return this.input.readDouble();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public float readFloat() {
            try {
                return this.input.readFloat();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public void readFully(byte[] b) {
            try {
                this.input.readFully(b);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public int readInt() {
            try {
                return this.input.readInt();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public String readLine() {
            try {
                return this.input.readLine();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public long readLong() {
            try {
                return this.input.readLong();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public short readShort() {
            try {
                return this.input.readShort();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public String readUTF() {
            try {
                return this.input.readUTF();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public int readUnsignedByte() {
            try {
                return this.input.readUnsignedByte();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public int readUnsignedShort() {
            try {
                return this.input.readUnsignedShort();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public int skipBytes(int n) {
            try {
                return this.input.skipBytes(n);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataInput, java.io.DataInput
        public void readFully(byte[] b, int off, int len) {
            try {
                this.input.readFully(b, off, len);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @J2ktIncompatible
    public static class ByteArrayDataOutputStream implements ByteArrayDataOutput {
        public final ByteArrayOutputStream byteArrayOutputStream;
        public final DataOutput output;

        public ByteArrayDataOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
            this.byteArrayOutputStream = byteArrayOutputStream;
            this.output = new DataOutputStream(byteArrayOutputStream);
        }

        @Override // com.google.common.io.ByteArrayDataOutput
        public byte[] toByteArray() {
            return this.byteArrayOutputStream.toByteArray();
        }

        @Override // com.google.common.io.ByteArrayDataOutput, java.io.DataOutput
        public void write(int b) {
            try {
                this.output.write(b);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataOutput, java.io.DataOutput
        public void writeBoolean(boolean v) {
            try {
                this.output.writeBoolean(v);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataOutput, java.io.DataOutput
        public void writeByte(int v) {
            try {
                this.output.writeByte(v);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataOutput, java.io.DataOutput
        public void writeBytes(String s) {
            try {
                this.output.writeBytes(s);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataOutput, java.io.DataOutput
        public void writeChar(int v) {
            try {
                this.output.writeChar(v);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataOutput, java.io.DataOutput
        public void writeChars(String s) {
            try {
                this.output.writeChars(s);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataOutput, java.io.DataOutput
        public void writeDouble(double v) {
            try {
                this.output.writeDouble(v);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataOutput, java.io.DataOutput
        public void writeFloat(float v) {
            try {
                this.output.writeFloat(v);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataOutput, java.io.DataOutput
        public void writeInt(int v) {
            try {
                this.output.writeInt(v);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataOutput, java.io.DataOutput
        public void writeLong(long v) {
            try {
                this.output.writeLong(v);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataOutput, java.io.DataOutput
        public void writeShort(int v) {
            try {
                this.output.writeShort(v);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataOutput, java.io.DataOutput
        public void writeUTF(String s) {
            try {
                this.output.writeUTF(s);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataOutput, java.io.DataOutput
        public void write(byte[] b) {
            try {
                this.output.write(b);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.io.ByteArrayDataOutput, java.io.DataOutput
        public void write(byte[] b, int off, int len) {
            try {
                this.output.write(b, off, len);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
    }

    public static void readFully(InputStream in, byte[] b, int off, int len) throws IOException {
        int i = read(in, b, off, len);
        if (i != len) {
            throw new EOFException(ObjectListKt$$ExternalSyntheticOutline0.m("reached end of stream after reading ", i, " bytes; ", len, " bytes expected"));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @J2ktIncompatible
    public static final class LimitedInputStream extends FilterInputStream {
        public long left;
        public long mark;

        public LimitedInputStream(InputStream in, long limit) {
            super(in);
            this.mark = -1L;
            in.getClass();
            Preconditions.checkArgument(limit >= 0, "limit must be non-negative");
            this.left = limit;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int available() throws IOException {
            return (int) Math.min(((FilterInputStream) this).in.available(), this.left);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void mark(int readLimit) {
            ((FilterInputStream) this).in.mark(readLimit);
            this.mark = this.left;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            if (this.left == 0) {
                return -1;
            }
            int i = ((FilterInputStream) this).in.read();
            if (i != -1) {
                this.left--;
            }
            return i;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void reset() throws IOException {
            if (!((FilterInputStream) this).in.markSupported()) {
                throw new IOException("Mark not supported");
            }
            if (this.mark == -1) {
                throw new IOException("Mark not set");
            }
            ((FilterInputStream) this).in.reset();
            this.left = this.mark;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long n) throws IOException {
            long jSkip = ((FilterInputStream) this).in.skip(Math.min(n, this.left));
            this.left -= jSkip;
            return jSkip;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] b, int off, int len) throws IOException {
            long j = this.left;
            if (j == 0) {
                return -1;
            }
            int i = ((FilterInputStream) this).in.read(b, off, (int) Math.min(len, j));
            if (i != -1) {
                this.left -= (long) i;
            }
            return i;
        }
    }

    @J2ktIncompatible
    public static ByteArrayDataInput newDataInput(byte[] bytes, int start) {
        Preconditions.checkPositionIndex(start, bytes.length);
        return new ByteArrayDataInputStream(new ByteArrayInputStream(bytes, start, bytes.length - start));
    }

    @J2ktIncompatible
    public static ByteArrayDataOutput newDataOutput(int size) {
        if (size >= 0) {
            return new ByteArrayDataOutputStream(new ByteArrayOutputStream(size));
        }
        throw new IllegalArgumentException(String.format("Invalid size: %s", Integer.valueOf(size)));
    }

    @CanIgnoreReturnValue
    @J2ktIncompatible
    public static long copy(ReadableByteChannel from, WritableByteChannel to) throws IOException {
        from.getClass();
        to.getClass();
        long jWrite = 0;
        if (from instanceof FileChannel) {
            FileChannel fileChannel = (FileChannel) from;
            long jPosition = fileChannel.position();
            long j = jPosition;
            while (true) {
                WritableByteChannel writableByteChannel = to;
                long jTransferTo = fileChannel.transferTo(j, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED, writableByteChannel);
                j += jTransferTo;
                fileChannel.position(j);
                if (jTransferTo <= 0 && j >= fileChannel.size()) {
                    return j - jPosition;
                }
                to = writableByteChannel;
            }
        } else {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[8192]);
            while (from.read(byteBufferWrap) != -1) {
                byteBufferWrap.flip();
                while (byteBufferWrap.hasRemaining()) {
                    jWrite += (long) to.write(byteBufferWrap);
                }
                byteBufferWrap.clear();
            }
            return jWrite;
        }
    }

    @J2ktIncompatible
    public static ByteArrayDataInput newDataInput(ByteArrayInputStream byteArrayInputStream) {
        byteArrayInputStream.getClass();
        return new ByteArrayDataInputStream(byteArrayInputStream);
    }

    @J2ktIncompatible
    public static ByteArrayDataOutput newDataOutput(ByteArrayOutputStream byteArrayOutputStream) {
        byteArrayOutputStream.getClass();
        return new ByteArrayDataOutputStream(byteArrayOutputStream);
    }

    public static byte[] toByteArray(InputStream in) throws IOException {
        in.getClass();
        return toByteArrayInternal(in, new ArrayDeque(20), 0);
    }
}
