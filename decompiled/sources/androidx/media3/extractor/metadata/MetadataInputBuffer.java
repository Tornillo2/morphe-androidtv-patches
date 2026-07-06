package androidx.media3.extractor.metadata;

import androidx.media3.common.util.UnstableApi;
import androidx.media3.decoder.DecoderInputBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class MetadataInputBuffer extends DecoderInputBuffer {
    public long subsampleOffsetUs;

    public MetadataInputBuffer() {
        super(1, 0);
    }
}
