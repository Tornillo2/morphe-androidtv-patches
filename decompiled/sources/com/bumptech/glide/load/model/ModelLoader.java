package com.bumptech.glide.load.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.util.Preconditions;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface ModelLoader<Model, Data> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class LoadData<Data> {
        public final List<Key> alternateKeys;
        public final DataFetcher<Data> fetcher;
        public final Key sourceKey;

        public LoadData(@NonNull Key key, @NonNull DataFetcher<Data> dataFetcher) {
            this(key, Collections.EMPTY_LIST, dataFetcher);
        }

        public LoadData(@NonNull Key key, @NonNull List<Key> list, @NonNull DataFetcher<Data> dataFetcher) {
            Preconditions.checkNotNull(key, "Argument must not be null");
            this.sourceKey = key;
            Preconditions.checkNotNull(list, "Argument must not be null");
            this.alternateKeys = list;
            Preconditions.checkNotNull(dataFetcher, "Argument must not be null");
            this.fetcher = dataFetcher;
        }
    }

    @Nullable
    LoadData<Data> buildLoadData(@NonNull Model model, int i, int i2, @NonNull Options options);

    boolean handles(@NonNull Model model);
}
