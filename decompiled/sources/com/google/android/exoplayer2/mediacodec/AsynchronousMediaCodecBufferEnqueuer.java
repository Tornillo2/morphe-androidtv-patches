package com.google.android.exoplayer2.mediacodec;

import android.media.MediaCodec;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import androidx.media3.decoder.CryptoInfo$PatternHolderV24$$ExternalSyntheticApiModelOutline0;
import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticApiModelOutline0;
import com.google.android.exoplayer2.decoder.CryptoInfo;
import com.google.android.exoplayer2.util.ConditionVariable;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(23)
public class AsynchronousMediaCodecBufferEnqueuer {
    public static final int MSG_OPEN_CV = 2;
    public static final int MSG_QUEUE_INPUT_BUFFER = 0;
    public static final int MSG_QUEUE_SECURE_INPUT_BUFFER = 1;
    public final MediaCodec codec;
    public final ConditionVariable conditionVariable;
    public Handler handler;
    public final HandlerThread handlerThread;
    public final AtomicReference<RuntimeException> pendingRuntimeException;
    public boolean started;

    @GuardedBy("MESSAGE_PARAMS_INSTANCE_POOL")
    public static final ArrayDeque<MessageParams> MESSAGE_PARAMS_INSTANCE_POOL = new ArrayDeque<>();
    public static final Object QUEUE_SECURE_LOCK = new Object();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MessageParams {
        public final MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
        public int flags;
        public int index;
        public int offset;
        public long presentationTimeUs;
        public int size;

        public void setQueueParams(int i, int i2, int i3, long j, int i4) {
            this.index = i;
            this.offset = i2;
            this.size = i3;
            this.presentationTimeUs = j;
            this.flags = i4;
        }
    }

    public AsynchronousMediaCodecBufferEnqueuer(MediaCodec mediaCodec, HandlerThread handlerThread) {
        this(mediaCodec, handlerThread, new ConditionVariable());
    }

    public static void copy(CryptoInfo cryptoInfo, MediaCodec.CryptoInfo cryptoInfo2) {
        cryptoInfo2.numSubSamples = cryptoInfo.numSubSamples;
        cryptoInfo2.numBytesOfClearData = copy(cryptoInfo.numBytesOfClearData, cryptoInfo2.numBytesOfClearData);
        cryptoInfo2.numBytesOfEncryptedData = copy(cryptoInfo.numBytesOfEncryptedData, cryptoInfo2.numBytesOfEncryptedData);
        byte[] bArrCopy = copy(cryptoInfo.key, cryptoInfo2.key);
        bArrCopy.getClass();
        cryptoInfo2.key = bArrCopy;
        byte[] bArrCopy2 = copy(cryptoInfo.iv, cryptoInfo2.iv);
        bArrCopy2.getClass();
        cryptoInfo2.iv = bArrCopy2;
        cryptoInfo2.mode = cryptoInfo.mode;
        if (Util.SDK_INT >= 24) {
            AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticApiModelOutline0.m();
            cryptoInfo2.setPattern(CryptoInfo$PatternHolderV24$$ExternalSyntheticApiModelOutline0.m(cryptoInfo.encryptedBlocks, cryptoInfo.clearBlocks));
        }
    }

    public static MessageParams getMessageParams() {
        ArrayDeque<MessageParams> arrayDeque = MESSAGE_PARAMS_INSTANCE_POOL;
        synchronized (arrayDeque) {
            try {
                if (arrayDeque.isEmpty()) {
                    return new MessageParams();
                }
                return arrayDeque.removeFirst();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void recycleMessageParams(MessageParams messageParams) {
        ArrayDeque<MessageParams> arrayDeque = MESSAGE_PARAMS_INSTANCE_POOL;
        synchronized (arrayDeque) {
            arrayDeque.add(messageParams);
        }
    }

    public final void blockUntilHandlerThreadIsIdle() throws InterruptedException {
        this.conditionVariable.close();
        Handler handler = this.handler;
        handler.getClass();
        handler.obtainMessage(2).sendToTarget();
        this.conditionVariable.block();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void doHandleMessage(android.os.Message r9) {
        /*
            r8 = this;
            int r2 = r9.what
            if (r2 == 0) goto L37
            r3 = 1
            if (r2 == r3) goto L22
            r3 = 2
            r4 = 0
            if (r2 == r3) goto L1c
            java.util.concurrent.atomic.AtomicReference<java.lang.RuntimeException> r2 = r8.pendingRuntimeException
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            int r1 = r9.what
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r3.<init>(r1)
            androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(r2, r4, r3)
            goto L4b
        L1c:
            com.google.android.exoplayer2.util.ConditionVariable r1 = r8.conditionVariable
            r1.open()
            goto L4b
        L22:
            java.lang.Object r1 = r9.obj
            r7 = r1
            com.google.android.exoplayer2.mediacodec.AsynchronousMediaCodecBufferEnqueuer$MessageParams r7 = (com.google.android.exoplayer2.mediacodec.AsynchronousMediaCodecBufferEnqueuer.MessageParams) r7
            int r1 = r7.index
            int r2 = r7.offset
            android.media.MediaCodec$CryptoInfo r3 = r7.cryptoInfo
            long r4 = r7.presentationTimeUs
            int r6 = r7.flags
            r0 = r8
            r0.doQueueSecureInputBuffer(r1, r2, r3, r4, r6)
        L35:
            r4 = r7
            goto L4b
        L37:
            java.lang.Object r0 = r9.obj
            r7 = r0
            com.google.android.exoplayer2.mediacodec.AsynchronousMediaCodecBufferEnqueuer$MessageParams r7 = (com.google.android.exoplayer2.mediacodec.AsynchronousMediaCodecBufferEnqueuer.MessageParams) r7
            int r1 = r7.index
            int r2 = r7.offset
            int r3 = r7.size
            long r4 = r7.presentationTimeUs
            int r6 = r7.flags
            r0 = r8
            r0.doQueueInputBuffer(r1, r2, r3, r4, r6)
            goto L35
        L4b:
            if (r4 == 0) goto L50
            recycleMessageParams(r4)
        L50:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.AsynchronousMediaCodecBufferEnqueuer.doHandleMessage(android.os.Message):void");
    }

    public final void doQueueInputBuffer(int i, int i2, int i3, long j, int i4) {
        try {
            this.codec.queueInputBuffer(i, i2, i3, j, i4);
        } catch (RuntimeException e) {
            LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.pendingRuntimeException, null, e);
        }
    }

    public final void doQueueSecureInputBuffer(int i, int i2, MediaCodec.CryptoInfo cryptoInfo, long j, int i3) {
        try {
            synchronized (QUEUE_SECURE_LOCK) {
                this.codec.queueSecureInputBuffer(i, i2, cryptoInfo, j, i3);
            }
        } catch (RuntimeException e) {
            LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.pendingRuntimeException, null, e);
        }
    }

    public void flush() {
        if (this.started) {
            try {
                flushHandlerThread();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException(e);
            }
        }
    }

    public final void flushHandlerThread() throws InterruptedException {
        Handler handler = this.handler;
        handler.getClass();
        handler.removeCallbacksAndMessages(null);
        blockUntilHandlerThreadIsIdle();
    }

    public void maybeThrowException() {
        RuntimeException andSet = this.pendingRuntimeException.getAndSet(null);
        if (andSet != null) {
            throw andSet;
        }
    }

    public void queueInputBuffer(int i, int i2, int i3, long j, int i4) {
        maybeThrowException();
        MessageParams messageParams = getMessageParams();
        messageParams.setQueueParams(i, i2, i3, j, i4);
        Handler handler = this.handler;
        Util.castNonNull(handler);
        handler.obtainMessage(0, messageParams).sendToTarget();
    }

    public void queueSecureInputBuffer(int i, int i2, CryptoInfo cryptoInfo, long j, int i3) {
        maybeThrowException();
        MessageParams messageParams = getMessageParams();
        messageParams.setQueueParams(i, i2, 0, j, i3);
        copy(cryptoInfo, messageParams.cryptoInfo);
        this.handler.obtainMessage(1, messageParams).sendToTarget();
    }

    @VisibleForTesting(otherwise = 5)
    public void setPendingRuntimeException(RuntimeException runtimeException) {
        this.pendingRuntimeException.set(runtimeException);
    }

    public void shutdown() {
        if (this.started) {
            flush();
            this.handlerThread.quit();
        }
        this.started = false;
    }

    public void start() {
        if (this.started) {
            return;
        }
        this.handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper()) { // from class: com.google.android.exoplayer2.mediacodec.AsynchronousMediaCodecBufferEnqueuer.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                AsynchronousMediaCodecBufferEnqueuer.this.doHandleMessage(message);
            }
        };
        this.started = true;
    }

    public void waitUntilQueueingComplete() throws InterruptedException {
        blockUntilHandlerThreadIsIdle();
    }

    @VisibleForTesting
    public AsynchronousMediaCodecBufferEnqueuer(MediaCodec mediaCodec, HandlerThread handlerThread, ConditionVariable conditionVariable) {
        this.codec = mediaCodec;
        this.handlerThread = handlerThread;
        this.conditionVariable = conditionVariable;
        this.pendingRuntimeException = new AtomicReference<>();
    }

    @Nullable
    public static int[] copy(@Nullable int[] iArr, @Nullable int[] iArr2) {
        if (iArr == null) {
            return iArr2;
        }
        if (iArr2 != null && iArr2.length >= iArr.length) {
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            return iArr2;
        }
        return Arrays.copyOf(iArr, iArr.length);
    }

    @Nullable
    public static byte[] copy(@Nullable byte[] bArr, @Nullable byte[] bArr2) {
        if (bArr == null) {
            return bArr2;
        }
        if (bArr2 != null && bArr2.length >= bArr.length) {
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }
}
