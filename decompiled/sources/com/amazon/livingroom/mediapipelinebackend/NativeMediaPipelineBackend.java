package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.livingroom.mediapipelinebackend.AvSampleStream;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class NativeMediaPipelineBackend {
    public long nativeMpbHandle = 0;

    private native void onBufferUnderrun(long j);

    private native void onError(long j, int i, String str, boolean z);

    private native void onFrameDropped(long j);

    public static native void onLogMessage(int i, String str);

    private native int onPictureModeChanged(long j, int i);

    private native void onPlaybackStarted(long j);

    private native void onReadyToPlay(long j);

    private native void onStreamFinished(long j);

    private native int readAudioAccessUnit(long j, AvSampleStream.BufferHolder bufferHolder, ByteBuffer byteBuffer, boolean z, boolean z2);

    private native int readVideoAccessUnit(long j, AvSampleStream.BufferHolder bufferHolder, ByteBuffer byteBuffer, boolean z, boolean z2);

    public synchronized void close() {
        this.nativeMpbHandle = 0L;
    }

    public synchronized void init(long j) {
        this.nativeMpbHandle = j;
    }

    public final boolean isClosed() {
        return this.nativeMpbHandle == 0;
    }

    public synchronized void onBufferUnderrun() {
        if (isClosed()) {
            MpbLog.e("Attempted to call onBufferUnderrun on an closed native instance", null);
        } else {
            onBufferUnderrun(this.nativeMpbHandle);
        }
    }

    public synchronized void onError(int i, String str, boolean z) throws Throwable {
        Throwable th;
        try {
            try {
                if (!isClosed()) {
                    onError(this.nativeMpbHandle, i, str, z);
                    return;
                }
                try {
                    MpbLog.e("Attempted to call onError on an closed native instance", null);
                    return;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                th = th;
            }
        } catch (Throwable th4) {
            th = th4;
        }
        throw th;
    }

    public synchronized void onFrameDropped() {
        if (isClosed()) {
            MpbLog.e("Attempted to call onFrameDropped on an closed native instance", null);
        } else {
            onFrameDropped(this.nativeMpbHandle);
        }
    }

    public synchronized void onPictureModeChanged(int i) {
        if (isClosed()) {
            MpbLog.e("Attempted to call onPictureModeChanged on an closed native instance", null);
        } else {
            onPictureModeChanged(this.nativeMpbHandle, i);
        }
    }

    public synchronized void onPlaybackStarted() {
        if (isClosed()) {
            MpbLog.e("Attempted to call onPlaybackStarted on an closed native instance", null);
        } else {
            onPlaybackStarted(this.nativeMpbHandle);
        }
    }

    public synchronized void onReadyToPlay() {
        if (isClosed()) {
            MpbLog.e("Attempted to call onReadyToPlay on an closed native instance", null);
        } else {
            onReadyToPlay(this.nativeMpbHandle);
        }
    }

    public synchronized void onStreamFinished() {
        if (isClosed()) {
            MpbLog.e("Attempted to call onStreamFinished on an closed native instance", null);
        } else {
            onStreamFinished(this.nativeMpbHandle);
        }
    }

    public synchronized int readAudioAccessUnit(AvSampleStream.BufferHolder bufferHolder, ByteBuffer byteBuffer, boolean z, boolean z2) throws Throwable {
        Throwable th;
        try {
            try {
                if (!isClosed()) {
                    return readAudioAccessUnit(this.nativeMpbHandle, bufferHolder, byteBuffer, z, z2);
                }
                try {
                    MpbLog.e("Attempted to call readAudioAccessUnit on an closed native instance", null);
                    return -1;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
        }
        th = th;
        throw th;
    }

    public synchronized int readVideoAccessUnit(AvSampleStream.BufferHolder bufferHolder, ByteBuffer byteBuffer, boolean z, boolean z2) throws Throwable {
        Throwable th;
        try {
            try {
                if (!isClosed()) {
                    return readVideoAccessUnit(this.nativeMpbHandle, bufferHolder, byteBuffer, z, z2);
                }
                try {
                    MpbLog.e("Attempted to call readVideoAccessUnit on an closed native instance", null);
                    return -1;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
        }
        th = th;
        throw th;
    }
}
