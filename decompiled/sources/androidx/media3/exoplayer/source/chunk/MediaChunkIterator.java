package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSpec;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface MediaChunkIterator {
    public static final MediaChunkIterator EMPTY = new AnonymousClass1();

    long getChunkEndTimeUs();

    long getChunkStartTimeUs();

    DataSpec getDataSpec();

    boolean isEnded();

    boolean next();

    void reset();

    /* JADX INFO: renamed from: androidx.media3.exoplayer.source.chunk.MediaChunkIterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements MediaChunkIterator {
        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkEndTimeUs() {
            throw new NoSuchElementException();
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkStartTimeUs() {
            throw new NoSuchElementException();
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public DataSpec getDataSpec() {
            throw new NoSuchElementException();
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public boolean isEnded() {
            return true;
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public boolean next() {
            return false;
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public void reset() {
        }
    }
}
