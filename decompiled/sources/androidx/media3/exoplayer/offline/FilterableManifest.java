package androidx.media3.exoplayer.offline;

import androidx.media3.common.StreamKey;
import androidx.media3.common.util.UnstableApi;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface FilterableManifest<T> {
    T copy(List<StreamKey> list);
}
