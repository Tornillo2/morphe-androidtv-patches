package androidx.media3.decoder;

import androidx.annotation.CallSuper;
import androidx.media3.common.util.UnstableApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public abstract class DecoderOutputBuffer extends Buffer {
    public boolean shouldBeSkipped;
    public int skippedOutputBufferCount;
    public long timeUs;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Owner<S extends DecoderOutputBuffer> {
        void releaseOutputBuffer(S s);
    }

    @Override // androidx.media3.decoder.Buffer
    @CallSuper
    public void clear() {
        this.flags = 0;
        this.timeUs = 0L;
        this.skippedOutputBufferCount = 0;
        this.shouldBeSkipped = false;
    }

    public abstract void release();
}
