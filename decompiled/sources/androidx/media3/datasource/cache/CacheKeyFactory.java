package androidx.media3.datasource.cache;

import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSpec;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface CacheKeyFactory {
    public static final CacheKeyFactory DEFAULT = new CacheKeyFactory$$ExternalSyntheticLambda0();

    /* JADX INFO: renamed from: androidx.media3.datasource.cache.CacheKeyFactory$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static /* synthetic */ String lambda$static$0(DataSpec dataSpec) {
            String str = dataSpec.key;
            return str != null ? str : dataSpec.uri.toString();
        }
    }

    String buildCacheKey(DataSpec dataSpec);
}
