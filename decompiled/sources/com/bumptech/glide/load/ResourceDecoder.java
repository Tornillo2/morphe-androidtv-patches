package com.bumptech.glide.load;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface ResourceDecoder<T, Z> {
    @Nullable
    Resource<Z> decode(@NonNull T t, int i, int i2, @NonNull Options options) throws IOException;

    boolean handles(@NonNull T t, @NonNull Options options) throws IOException;
}
