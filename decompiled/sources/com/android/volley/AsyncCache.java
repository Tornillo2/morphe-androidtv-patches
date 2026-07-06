package com.android.volley;

import androidx.annotation.Nullable;
import com.android.volley.Cache;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AsyncCache {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface OnGetCompleteCallback {
        void onGetComplete(@Nullable Cache.Entry entry);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface OnWriteCompleteCallback {
        void onWriteComplete();
    }

    public abstract void clear(OnWriteCompleteCallback onWriteCompleteCallback);

    public abstract void get(String str, OnGetCompleteCallback onGetCompleteCallback);

    public abstract void initialize(OnWriteCompleteCallback onWriteCompleteCallback);

    public abstract void invalidate(String str, boolean z, OnWriteCompleteCallback onWriteCompleteCallback);

    public abstract void put(String str, Cache.Entry entry, OnWriteCompleteCallback onWriteCompleteCallback);

    public abstract void remove(String str, OnWriteCompleteCallback onWriteCompleteCallback);
}
