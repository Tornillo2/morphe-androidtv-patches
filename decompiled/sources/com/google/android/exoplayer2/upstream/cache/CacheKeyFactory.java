package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSpec;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface CacheKeyFactory {
    public static final CacheKeyFactory DEFAULT = new CacheKeyFactory$$ExternalSyntheticLambda0();

    /* JADX INFO: renamed from: com.google.android.exoplayer2.upstream.cache.CacheKeyFactory$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static /* synthetic */ String lambda$static$0(DataSpec dataSpec) {
            String str = dataSpec.key;
            return str != null ? str : dataSpec.uri.toString();
        }
    }

    String buildCacheKey(DataSpec dataSpec);
}
