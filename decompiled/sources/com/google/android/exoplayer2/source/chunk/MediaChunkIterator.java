package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.upstream.DataSpec;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface MediaChunkIterator {
    public static final MediaChunkIterator EMPTY = new AnonymousClass1();

    long getChunkEndTimeUs();

    long getChunkStartTimeUs();

    DataSpec getDataSpec();

    boolean isEnded();

    boolean next();

    void reset();

    /* JADX INFO: renamed from: com.google.android.exoplayer2.source.chunk.MediaChunkIterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements MediaChunkIterator {
        @Override // com.google.android.exoplayer2.source.chunk.MediaChunkIterator
        public long getChunkEndTimeUs() {
            throw new NoSuchElementException();
        }

        @Override // com.google.android.exoplayer2.source.chunk.MediaChunkIterator
        public long getChunkStartTimeUs() {
            throw new NoSuchElementException();
        }

        @Override // com.google.android.exoplayer2.source.chunk.MediaChunkIterator
        public DataSpec getDataSpec() {
            throw new NoSuchElementException();
        }

        @Override // com.google.android.exoplayer2.source.chunk.MediaChunkIterator
        public boolean isEnded() {
            return true;
        }

        @Override // com.google.android.exoplayer2.source.chunk.MediaChunkIterator
        public boolean next() {
            return false;
        }

        @Override // com.google.android.exoplayer2.source.chunk.MediaChunkIterator
        public void reset() {
        }
    }
}
