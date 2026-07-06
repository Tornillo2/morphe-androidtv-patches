package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.collection.CircularIntArray;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.ArrayDeque;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(23)
public final class AsynchronousMediaCodecCallback extends MediaCodec.Callback {
    public final HandlerThread callbackThread;

    @Nullable
    @GuardedBy("lock")
    public MediaFormat currentFormat;
    public Handler handler;

    @Nullable
    @GuardedBy("lock")
    public IllegalStateException internalException;

    @Nullable
    @GuardedBy("lock")
    public MediaCodec.CryptoException mediaCodecCryptoException;

    @Nullable
    @GuardedBy("lock")
    public MediaCodec.CodecException mediaCodecException;

    @GuardedBy("lock")
    public long pendingFlushCount;

    @Nullable
    @GuardedBy("lock")
    public MediaFormat pendingOutputFormat;

    @GuardedBy("lock")
    public boolean shutDown;
    public final Object lock = new Object();

    @GuardedBy("lock")
    public final CircularIntArray availableInputBuffers = new CircularIntArray();

    @GuardedBy("lock")
    public final CircularIntArray availableOutputBuffers = new CircularIntArray();

    @GuardedBy("lock")
    public final ArrayDeque<MediaCodec.BufferInfo> bufferInfos = new ArrayDeque<>();

    @GuardedBy("lock")
    public final ArrayDeque<MediaFormat> formats = new ArrayDeque<>();

    public AsynchronousMediaCodecCallback(HandlerThread handlerThread) {
        this.callbackThread = handlerThread;
    }

    @GuardedBy("lock")
    public final void addOutputFormat(MediaFormat mediaFormat) {
        this.availableOutputBuffers.addLast(-2);
        this.formats.add(mediaFormat);
    }

    public int dequeueInputBufferIndex() {
        synchronized (this.lock) {
            try {
                maybeThrowException();
                int iPopFirst = -1;
                if (isFlushingOrShutdown()) {
                    return -1;
                }
                if (!this.availableInputBuffers.isEmpty()) {
                    iPopFirst = this.availableInputBuffers.popFirst();
                }
                return iPopFirst;
            } finally {
            }
        }
    }

    public int dequeueOutputBufferIndex(MediaCodec.BufferInfo bufferInfo) {
        synchronized (this.lock) {
            try {
                maybeThrowException();
                if (isFlushingOrShutdown()) {
                    return -1;
                }
                if (this.availableOutputBuffers.isEmpty()) {
                    return -1;
                }
                int iPopFirst = this.availableOutputBuffers.popFirst();
                if (iPopFirst >= 0) {
                    Assertions.checkStateNotNull(this.currentFormat);
                    MediaCodec.BufferInfo bufferInfoRemove = this.bufferInfos.remove();
                    bufferInfo.set(bufferInfoRemove.offset, bufferInfoRemove.size, bufferInfoRemove.presentationTimeUs, bufferInfoRemove.flags);
                } else if (iPopFirst == -2) {
                    this.currentFormat = this.formats.remove();
                }
                return iPopFirst;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void flush() {
        synchronized (this.lock) {
            this.pendingFlushCount++;
            Handler handler = this.handler;
            Util.castNonNull(handler);
            handler.post(new Runnable() { // from class: androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecCallback$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.onFlushCompleted();
                }
            });
        }
    }

    @GuardedBy("lock")
    public final void flushInternal() {
        if (!this.formats.isEmpty()) {
            this.pendingOutputFormat = this.formats.getLast();
        }
        CircularIntArray circularIntArray = this.availableInputBuffers;
        circularIntArray.tail = circularIntArray.head;
        CircularIntArray circularIntArray2 = this.availableOutputBuffers;
        circularIntArray2.tail = circularIntArray2.head;
        this.bufferInfos.clear();
        this.formats.clear();
    }

    public MediaFormat getOutputFormat() {
        MediaFormat mediaFormat;
        synchronized (this.lock) {
            try {
                mediaFormat = this.currentFormat;
                if (mediaFormat == null) {
                    throw new IllegalStateException();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return mediaFormat;
    }

    public void initialize(MediaCodec mediaCodec) {
        Assertions.checkState(this.handler == null);
        this.callbackThread.start();
        Handler handler = new Handler(this.callbackThread.getLooper());
        mediaCodec.setCallback(this, handler);
        this.handler = handler;
    }

    @GuardedBy("lock")
    public final boolean isFlushingOrShutdown() {
        return this.pendingFlushCount > 0 || this.shutDown;
    }

    @GuardedBy("lock")
    public final void maybeThrowException() {
        maybeThrowInternalException();
        maybeThrowMediaCodecException();
        maybeThrowMediaCodecCryptoException();
    }

    @GuardedBy("lock")
    public final void maybeThrowInternalException() {
        IllegalStateException illegalStateException = this.internalException;
        if (illegalStateException == null) {
            return;
        }
        this.internalException = null;
        throw illegalStateException;
    }

    @GuardedBy("lock")
    public final void maybeThrowMediaCodecCryptoException() {
        MediaCodec.CryptoException cryptoException = this.mediaCodecCryptoException;
        if (cryptoException == null) {
            return;
        }
        this.mediaCodecCryptoException = null;
        throw cryptoException;
    }

    @GuardedBy("lock")
    public final void maybeThrowMediaCodecException() {
        MediaCodec.CodecException codecException = this.mediaCodecException;
        if (codecException == null) {
            return;
        }
        this.mediaCodecException = null;
        throw codecException;
    }

    @Override // android.media.MediaCodec.Callback
    public void onCryptoError(MediaCodec mediaCodec, MediaCodec.CryptoException cryptoException) {
        synchronized (this.lock) {
            this.mediaCodecCryptoException = cryptoException;
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
        synchronized (this.lock) {
            this.mediaCodecException = codecException;
        }
    }

    public final void onFlushCompleted() {
        synchronized (this.lock) {
            try {
                if (this.shutDown) {
                    return;
                }
                long j = this.pendingFlushCount - 1;
                this.pendingFlushCount = j;
                if (j > 0) {
                    return;
                }
                if (j < 0) {
                    setInternalException(new IllegalStateException());
                } else {
                    flushInternal();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onInputBufferAvailable(MediaCodec mediaCodec, int i) {
        synchronized (this.lock) {
            this.availableInputBuffers.addLast(i);
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputBufferAvailable(MediaCodec mediaCodec, int i, MediaCodec.BufferInfo bufferInfo) {
        synchronized (this.lock) {
            try {
                MediaFormat mediaFormat = this.pendingOutputFormat;
                if (mediaFormat != null) {
                    addOutputFormat(mediaFormat);
                    this.pendingOutputFormat = null;
                }
                this.availableOutputBuffers.addLast(i);
                this.bufferInfos.add(bufferInfo);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        synchronized (this.lock) {
            addOutputFormat(mediaFormat);
            this.pendingOutputFormat = null;
        }
    }

    public final void setInternalException(IllegalStateException illegalStateException) {
        synchronized (this.lock) {
            this.internalException = illegalStateException;
        }
    }

    public void shutdown() {
        synchronized (this.lock) {
            this.shutDown = true;
            this.callbackThread.quit();
            flushInternal();
        }
    }
}
