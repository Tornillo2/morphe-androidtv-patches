package com.bumptech.glide.load;

import androidx.annotation.NonNull;
import java.io.File;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface Encoder<T> {
    boolean encode(@NonNull T t, @NonNull File file, @NonNull Options options);
}
