package com.android.volley.toolbox;

import com.android.volley.AsyncCache;
import com.android.volley.Cache;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class NoAsyncCache extends AsyncCache {
    @Override // com.android.volley.AsyncCache
    public void clear(AsyncCache.OnWriteCompleteCallback onWriteCompleteCallback) {
        onWriteCompleteCallback.onWriteComplete();
    }

    @Override // com.android.volley.AsyncCache
    public void get(String str, AsyncCache.OnGetCompleteCallback onGetCompleteCallback) {
        onGetCompleteCallback.onGetComplete(null);
    }

    @Override // com.android.volley.AsyncCache
    public void initialize(AsyncCache.OnWriteCompleteCallback onWriteCompleteCallback) {
        onWriteCompleteCallback.onWriteComplete();
    }

    @Override // com.android.volley.AsyncCache
    public void invalidate(String str, boolean z, AsyncCache.OnWriteCompleteCallback onWriteCompleteCallback) {
        onWriteCompleteCallback.onWriteComplete();
    }

    @Override // com.android.volley.AsyncCache
    public void put(String str, Cache.Entry entry, AsyncCache.OnWriteCompleteCallback onWriteCompleteCallback) {
        onWriteCompleteCallback.onWriteComplete();
    }

    @Override // com.android.volley.AsyncCache
    public void remove(String str, AsyncCache.OnWriteCompleteCallback onWriteCompleteCallback) {
        onWriteCompleteCallback.onWriteComplete();
    }
}
