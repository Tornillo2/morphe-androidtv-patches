package com.amazon.ion.impl;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.BufferOverflowException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ResizingPipedInputStream extends InputStream {
    public static final NotificationConsumer NO_OP_NOTIFICATION_CONSUMER = new AnonymousClass1();
    public static final int SINGLE_BYTE_MASK = 255;
    public int available;
    public int boundary;
    public byte[] buffer;
    public java.nio.ByteBuffer byteBuffer;
    public int capacity;
    public final int initialBufferSize;
    public final int maximumBufferSize;
    public NotificationConsumer notificationConsumer;
    public int readIndex;
    public int size;
    public final boolean useBoundary;
    public int writeIndex;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface NotificationConsumer {
        void bytesConsolidatedToStartOfBuffer(int i);
    }

    public ResizingPipedInputStream(int i) {
        this(i, Integer.MAX_VALUE, false);
    }

    @Override // java.io.InputStream
    public int available() {
        return this.available;
    }

    public int availableBeyondBoundary() {
        return this.size - this.available;
    }

    public int capacity() {
        return this.capacity;
    }

    public void clear() {
        this.readIndex = 0;
        this.writeIndex = 0;
        this.available = 0;
        this.boundary = 0;
        this.size = 0;
    }

    public void consolidate(int i, int i2) {
        int i3 = this.writeIndex;
        if (i > i3 || i > this.boundary || i2 < this.readIndex) {
            throw new IllegalArgumentException("Tried to consolidate using an index that violates the constraints.");
        }
        int i4 = i - i2;
        byte[] bArr = this.buffer;
        System.arraycopy(bArr, i, bArr, i2, i3 - i);
        this.size -= i4;
        this.available -= i4;
        this.writeIndex -= i4;
        this.boundary -= i4;
    }

    public void copyBytes(int i, byte[] bArr, int i2, int i3) {
        System.arraycopy(this.buffer, i, bArr, i2, i3);
    }

    public void copyTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.buffer, this.readIndex, this.available);
    }

    public final void ensureSpaceInBuffer(int i) {
        if (this.size < 1 || freeSpaceAtEndOfBuffer() < i) {
            int iFreeSpaceAtEndOfBuffer = (i - freeSpaceAtEndOfBuffer()) - this.readIndex;
            if (iFreeSpaceAtEndOfBuffer <= 0) {
                moveBytesToStartOfBuffer(this.buffer);
                return;
            }
            int iMax = Math.max(this.initialBufferSize, iFreeSpaceAtEndOfBuffer);
            int i2 = this.capacity;
            int i3 = i2 + iMax;
            int i4 = this.maximumBufferSize;
            if (i3 <= i4) {
                iFreeSpaceAtEndOfBuffer = iMax;
            } else if (i2 + iFreeSpaceAtEndOfBuffer > i4) {
                throw new BufferOverflowException();
            }
            byte[] bArr = new byte[this.buffer.length + iFreeSpaceAtEndOfBuffer];
            moveBytesToStartOfBuffer(bArr);
            int i5 = this.capacity + iFreeSpaceAtEndOfBuffer;
            this.capacity = i5;
            this.buffer = bArr;
            this.byteBuffer = java.nio.ByteBuffer.wrap(bArr, this.readIndex, i5);
        }
    }

    public void extendBoundary(int i) {
        this.boundary += i;
        this.available += i;
    }

    public final int freeSpaceAtEndOfBuffer() {
        return this.capacity - this.writeIndex;
    }

    public int getBoundary() {
        return this.boundary;
    }

    public java.nio.ByteBuffer getByteBuffer(int i, int i2) {
        this.byteBuffer.limit(this.capacity);
        this.byteBuffer.position(i);
        this.byteBuffer.limit(i2);
        return this.byteBuffer;
    }

    public int getInitialBufferSize() {
        return this.initialBufferSize;
    }

    public int getReadIndex() {
        return this.readIndex;
    }

    public int getWriteIndex() {
        return this.writeIndex;
    }

    public final void moveBytesToStartOfBuffer(byte[] bArr) {
        int i = this.size;
        if (i > 0) {
            System.arraycopy(this.buffer, this.readIndex, bArr, 0, i);
        }
        int i2 = this.readIndex;
        if (i2 > 0) {
            this.notificationConsumer.bytesConsolidatedToStartOfBuffer(i2);
        }
        this.readIndex = 0;
        this.boundary = this.available;
        this.writeIndex = this.size;
    }

    public int peek(int i) {
        return this.buffer[i] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (bArr.length == 0 || i2 == 0) {
            return 0;
        }
        int i3 = this.available;
        if (i3 < 1) {
            return -1;
        }
        int iMin = Math.min(i3, i2);
        System.arraycopy(this.buffer, this.readIndex, bArr, i, iMin);
        this.readIndex += iMin;
        this.available -= iMin;
        this.size -= iMin;
        return iMin;
    }

    public void receive(int i) {
        ensureSpaceInBuffer(1);
        byte[] bArr = this.buffer;
        int i2 = this.writeIndex;
        bArr[i2] = (byte) i;
        this.writeIndex = i2 + 1;
        this.size++;
        if (this.useBoundary) {
            return;
        }
        extendBoundary(1);
    }

    public void registerNotificationConsumer(NotificationConsumer notificationConsumer) {
        this.notificationConsumer = notificationConsumer;
    }

    public void rewind(int i, int i2) {
        this.readIndex = i;
        this.available = i2;
        this.boundary = i2 + i;
        this.size = this.writeIndex - i;
    }

    public void seekTo(int i) {
        int i2 = i - this.readIndex;
        this.available -= i2;
        this.size -= i2;
        this.readIndex = i;
    }

    public int size() {
        return this.size;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        int i;
        if (j < 1 || (i = this.available) < 1) {
            return 0L;
        }
        int iMin = (int) Math.min(i, j);
        this.readIndex += iMin;
        this.available -= iMin;
        this.size -= iMin;
        return iMin;
    }

    public void truncate(int i, int i2) {
        this.writeIndex = i;
        this.available = i2;
        this.boundary = i;
        this.size = i2;
    }

    public ResizingPipedInputStream(int i, int i2, boolean z) {
        this.notificationConsumer = NO_OP_NOTIFICATION_CONSUMER;
        this.readIndex = 0;
        this.writeIndex = 0;
        this.available = 0;
        this.size = 0;
        this.boundary = 0;
        if (i < 1) {
            throw new IllegalArgumentException("Initial buffer size must be at least 1.");
        }
        if (i2 < i) {
            throw new IllegalArgumentException("Maximum buffer size cannot be less than the initial buffer size.");
        }
        this.initialBufferSize = i;
        this.maximumBufferSize = i2;
        this.capacity = i;
        byte[] bArr = new byte[i];
        this.buffer = bArr;
        this.byteBuffer = java.nio.ByteBuffer.wrap(bArr, 0, i);
        this.useBoundary = z;
    }

    public void receive(byte[] bArr, int i, int i2) {
        ensureSpaceInBuffer(i2);
        System.arraycopy(bArr, i, this.buffer, this.writeIndex, i2);
        this.writeIndex += i2;
        this.size += i2;
        if (this.useBoundary) {
            return;
        }
        extendBoundary(i2);
    }

    @Override // java.io.InputStream
    public int read() {
        int i = this.available;
        if (i < 1) {
            return -1;
        }
        byte[] bArr = this.buffer;
        int i2 = this.readIndex;
        byte b = bArr[i2];
        this.readIndex = i2 + 1;
        this.available = i - 1;
        this.size--;
        return b & 255;
    }

    public void receive(byte[] bArr) {
        receive(bArr, 0, bArr.length);
    }

    public int receive(InputStream inputStream, int i) throws IOException {
        int i2;
        ensureSpaceInBuffer(i);
        try {
            i2 = inputStream.read(this.buffer, this.writeIndex, i);
        } catch (EOFException unused) {
            i2 = -1;
        }
        if (i2 > 0) {
            this.writeIndex += i2;
            this.size += i2;
        } else {
            i2 = 0;
        }
        if (!this.useBoundary) {
            extendBoundary(i2);
        }
        return i2;
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.ResizingPipedInputStream$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass1 implements NotificationConsumer {
        @Override // com.amazon.ion.impl.ResizingPipedInputStream.NotificationConsumer
        public void bytesConsolidatedToStartOfBuffer(int i) {
        }
    }
}
