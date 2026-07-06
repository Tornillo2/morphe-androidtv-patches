package com.android.volley.toolbox;

import com.android.volley.Cache;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class NoCache implements Cache {
    @Override // com.android.volley.Cache
    public Cache.Entry get(String str) {
        return null;
    }

    @Override // com.android.volley.Cache
    public void clear() {
    }

    @Override // com.android.volley.Cache
    public void initialize() {
    }

    @Override // com.android.volley.Cache
    public void remove(String str) {
    }

    @Override // com.android.volley.Cache
    public void invalidate(String str, boolean z) {
    }

    @Override // com.android.volley.Cache
    public void put(String str, Cache.Entry entry) {
    }
}
