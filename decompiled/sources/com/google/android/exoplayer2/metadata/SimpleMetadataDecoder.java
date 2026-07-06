package com.google.android.exoplayer2.metadata;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.util.Assertions;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class SimpleMetadataDecoder implements MetadataDecoder {
    @Override // com.google.android.exoplayer2.metadata.MetadataDecoder
    @Nullable
    public final Metadata decode(MetadataInputBuffer metadataInputBuffer) {
        ByteBuffer byteBuffer = metadataInputBuffer.data;
        byteBuffer.getClass();
        Assertions.checkArgument(byteBuffer.position() == 0 && byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0);
        if (metadataInputBuffer.getFlag(Integer.MIN_VALUE)) {
            return null;
        }
        return decode(metadataInputBuffer, byteBuffer);
    }

    @Nullable
    public abstract Metadata decode(MetadataInputBuffer metadataInputBuffer, ByteBuffer byteBuffer);
}
