package com.bumptech.glide.load.data;

import android.content.res.AssetManager;
import android.util.Log;
import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AssetPathFetcher<T> implements DataFetcher<T> {
    public static final String TAG = "AssetPathFetcher";
    public final AssetManager assetManager;
    public final String assetPath;
    public T data;

    public AssetPathFetcher(AssetManager assetManager, String str) {
        this.assetManager = assetManager;
        this.assetPath = str;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cleanup() {
        T t = this.data;
        if (t == null) {
            return;
        }
        try {
            close(t);
        } catch (IOException unused) {
        }
    }

    public abstract void close(T t) throws IOException;

    @Override // com.bumptech.glide.load.data.DataFetcher
    @NonNull
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void loadData(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super T> dataCallback) {
        try {
            T tLoadResource = loadResource(this.assetManager, this.assetPath);
            this.data = tLoadResource;
            dataCallback.onDataReady(tLoadResource);
        } catch (IOException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to load data from asset manager", e);
            }
            dataCallback.onLoadFailed(e);
        }
    }

    public abstract T loadResource(AssetManager assetManager, String str) throws IOException;

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cancel() {
    }
}
