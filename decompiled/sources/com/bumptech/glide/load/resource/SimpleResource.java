package com.bumptech.glide.load.resource;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class SimpleResource<T> implements Resource<T> {
    public final T data;

    public SimpleResource(@NonNull T t) {
        Preconditions.checkNotNull(t, "Argument must not be null");
        this.data = t;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public final T get() {
        return this.data;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public Class<T> getResourceClass() {
        return (Class<T>) this.data.getClass();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public final int getSize() {
        return 1;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public void recycle() {
    }
}
