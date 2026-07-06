package com.bumptech.glide.load.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface DataFetcher<T> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface DataCallback<T> {
        void onDataReady(@Nullable T t);

        void onLoadFailed(@NonNull Exception exc);
    }

    void cancel();

    void cleanup();

    @NonNull
    Class<T> getDataClass();

    @NonNull
    DataSource getDataSource();

    void loadData(@NonNull Priority priority, @NonNull DataCallback<? super T> dataCallback);
}
