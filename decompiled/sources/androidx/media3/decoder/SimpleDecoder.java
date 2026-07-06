package androidx.media3.decoder;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.DecoderOutputBuffer;
import java.util.ArrayDeque;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public abstract class SimpleDecoder<I extends DecoderInputBuffer, O extends DecoderOutputBuffer, E extends DecoderException> implements Decoder<I, O, E> {
    public int availableInputBufferCount;
    public final I[] availableInputBuffers;
    public int availableOutputBufferCount;
    public final O[] availableOutputBuffers;
    public final Thread decodeThread;

    @Nullable
    public I dequeuedInputBuffer;

    @Nullable
    public E exception;
    public boolean flushed;
    public final Object lock = new Object();
    public long outputStartTimeUs = -9223372036854775807L;
    public final ArrayDeque<I> queuedInputBuffers = new ArrayDeque<>();
    public final ArrayDeque<O> queuedOutputBuffers = new ArrayDeque<>();
    public boolean released;
    public int skippedOutputBufferCount;

    public SimpleDecoder(I[] iArr, O[] oArr) {
        this.availableInputBuffers = iArr;
        this.availableInputBufferCount = iArr.length;
        for (int i = 0; i < this.availableInputBufferCount; i++) {
            ((I[]) this.availableInputBuffers)[i] = createInputBuffer();
        }
        this.availableOutputBuffers = oArr;
        this.availableOutputBufferCount = oArr.length;
        for (int i2 = 0; i2 < this.availableOutputBufferCount; i2++) {
            ((O[]) this.availableOutputBuffers)[i2] = createOutputBuffer();
        }
        Thread thread = new Thread("ExoPlayer:SimpleDecoder") { // from class: androidx.media3.decoder.SimpleDecoder.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                SimpleDecoder.this.run();
            }
        };
        this.decodeThread = thread;
        thread.start();
    }

    public final boolean canDecodeBuffer() {
        return !this.queuedInputBuffers.isEmpty() && this.availableOutputBufferCount > 0;
    }

    public abstract I createInputBuffer();

    public abstract O createOutputBuffer();

    public abstract E createUnexpectedDecodeException(Throwable th);

    @Nullable
    public abstract E decode(I i, O o, boolean z);

    public final boolean decode() throws InterruptedException {
        E e;
        synchronized (this.lock) {
            while (!this.released && !canDecodeBuffer()) {
                try {
                    this.lock.wait();
                } finally {
                }
            }
            if (this.released) {
                return false;
            }
            I iRemoveFirst = this.queuedInputBuffers.removeFirst();
            O[] oArr = this.availableOutputBuffers;
            int i = this.availableOutputBufferCount - 1;
            this.availableOutputBufferCount = i;
            O o = oArr[i];
            boolean z = this.flushed;
            this.flushed = false;
            if (iRemoveFirst.getFlag(4)) {
                o.addFlag(4);
            } else {
                long j = iRemoveFirst.timeUs;
                o.timeUs = j;
                if (!isAtLeastOutputStartTimeUs(j) || iRemoveFirst.getFlag(Integer.MIN_VALUE)) {
                    o.addFlag(Integer.MIN_VALUE);
                }
                if (iRemoveFirst.getFlag(134217728)) {
                    o.addFlag(134217728);
                }
                try {
                    e = (E) decode(iRemoveFirst, o, z);
                } catch (OutOfMemoryError e2) {
                    e = (E) createUnexpectedDecodeException(e2);
                } catch (RuntimeException e3) {
                    e = (E) createUnexpectedDecodeException(e3);
                }
                if (e != null) {
                    synchronized (this.lock) {
                        this.exception = e;
                    }
                    return false;
                }
            }
            synchronized (this.lock) {
                try {
                    if (this.flushed) {
                        o.release();
                    } else if ((!o.getFlag(4) && !isAtLeastOutputStartTimeUs(o.timeUs)) || o.getFlag(Integer.MIN_VALUE) || o.shouldBeSkipped) {
                        this.skippedOutputBufferCount++;
                        o.release();
                    } else {
                        o.skippedOutputBufferCount = this.skippedOutputBufferCount;
                        this.skippedOutputBufferCount = 0;
                        this.queuedOutputBuffers.addLast(o);
                    }
                    releaseInputBufferInternal(iRemoveFirst);
                } finally {
                }
            }
            return true;
        }
    }

    @Override // androidx.media3.decoder.Decoder
    public final void flush() {
        synchronized (this.lock) {
            try {
                this.flushed = true;
                this.skippedOutputBufferCount = 0;
                I i = this.dequeuedInputBuffer;
                if (i != null) {
                    releaseInputBufferInternal(i);
                    this.dequeuedInputBuffer = null;
                }
                while (!this.queuedInputBuffers.isEmpty()) {
                    releaseInputBufferInternal(this.queuedInputBuffers.removeFirst());
                }
                while (!this.queuedOutputBuffers.isEmpty()) {
                    this.queuedOutputBuffers.removeFirst().release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean isAtLeastOutputStartTimeUs(long j) {
        boolean z;
        synchronized (this.lock) {
            long j2 = this.outputStartTimeUs;
            z = j2 == -9223372036854775807L || j >= j2;
        }
        return z;
    }

    public final void maybeNotifyDecodeLoop() {
        if (canDecodeBuffer()) {
            this.lock.notify();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: E extends androidx.media3.decoder.DecoderException */
    public final void maybeThrowException() throws E, DecoderException {
        E e = this.exception;
        if (e != null) {
            throw e;
        }
    }

    @Override // androidx.media3.decoder.Decoder
    @CallSuper
    public void release() {
        synchronized (this.lock) {
            this.released = true;
            this.lock.notify();
        }
        try {
            this.decodeThread.join();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    public final void releaseInputBufferInternal(I i) {
        i.clear();
        I[] iArr = this.availableInputBuffers;
        int i2 = this.availableInputBufferCount;
        this.availableInputBufferCount = i2 + 1;
        iArr[i2] = i;
    }

    @CallSuper
    public void releaseOutputBuffer(O o) {
        synchronized (this.lock) {
            releaseOutputBufferInternal(o);
            maybeNotifyDecodeLoop();
        }
    }

    public final void releaseOutputBufferInternal(O o) {
        o.clear();
        O[] oArr = this.availableOutputBuffers;
        int i = this.availableOutputBufferCount;
        this.availableOutputBufferCount = i + 1;
        oArr[i] = o;
    }

    public final void run() {
        do {
            try {
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        } while (decode());
    }

    public final void setInitialInputBufferSize(int i) {
        Assertions.checkState(this.availableInputBufferCount == this.availableInputBuffers.length);
        for (I i2 : this.availableInputBuffers) {
            i2.ensureSpaceForWrite(i);
        }
    }

    @Override // androidx.media3.decoder.Decoder
    public final void setOutputStartTimeUs(long j) {
        synchronized (this.lock) {
            try {
                Assertions.checkState(this.availableInputBufferCount == this.availableInputBuffers.length || this.flushed);
                this.outputStartTimeUs = j;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.media3.decoder.Decoder
    @Nullable
    public final I dequeueInputBuffer() throws DecoderException {
        I i;
        synchronized (this.lock) {
            try {
                E e = this.exception;
                if (e != null) {
                    throw e;
                }
                Assertions.checkState(this.dequeuedInputBuffer == null);
                int i2 = this.availableInputBufferCount;
                if (i2 == 0) {
                    i = null;
                } else {
                    I[] iArr = this.availableInputBuffers;
                    int i3 = i2 - 1;
                    this.availableInputBufferCount = i3;
                    i = iArr[i3];
                }
                this.dequeuedInputBuffer = i;
            } catch (Throwable th) {
                throw th;
            }
        }
        return i;
    }

    @Override // androidx.media3.decoder.Decoder
    @Nullable
    public final O dequeueOutputBuffer() throws DecoderException {
        synchronized (this.lock) {
            try {
                E e = this.exception;
                if (e != null) {
                    throw e;
                }
                if (this.queuedOutputBuffers.isEmpty()) {
                    return null;
                }
                return this.queuedOutputBuffers.removeFirst();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.media3.decoder.Decoder
    public final void queueInputBuffer(I i) throws DecoderException {
        synchronized (this.lock) {
            try {
                E e = this.exception;
                if (e != null) {
                    throw e;
                }
                Assertions.checkArgument(i == this.dequeuedInputBuffer);
                this.queuedInputBuffers.addLast(i);
                maybeNotifyDecodeLoop();
                this.dequeuedInputBuffer = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
