package androidx.media3.exoplayer.mediacodec;

import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.media3.decoder.CryptoInfo;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface MediaCodecBufferEnqueuer {
    void flush();

    void maybeThrowException();

    void queueInputBuffer(int i, int i2, int i3, long j, int i4);

    void queueSecureInputBuffer(int i, int i2, CryptoInfo cryptoInfo, long j, int i3);

    @RequiresApi(19)
    void setParameters(Bundle bundle);

    void shutdown();

    void start();

    void waitUntilQueueingComplete() throws InterruptedException;
}
