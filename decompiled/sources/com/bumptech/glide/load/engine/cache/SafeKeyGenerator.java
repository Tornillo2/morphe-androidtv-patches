package com.bumptech.glide.load.engine.cache;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class SafeKeyGenerator {
    public final LruCache<Key, String> loadIdToSafeHash = new LruCache<>(1000);
    public final Pools.Pool<PoolableDigestContainer> digestPool = FactoryPools.threadSafe(10, new FactoryPools.Factory<PoolableDigestContainer>() { // from class: com.bumptech.glide.load.engine.cache.SafeKeyGenerator.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.util.pool.FactoryPools.Factory
        public PoolableDigestContainer create() {
            try {
                return new PoolableDigestContainer(MessageDigest.getInstance("SHA-256"));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    });

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PoolableDigestContainer implements FactoryPools.Poolable {
        public final MessageDigest messageDigest;
        public final StateVerifier stateVerifier = new StateVerifier.DefaultStateVerifier();

        public PoolableDigestContainer(MessageDigest messageDigest) {
            this.messageDigest = messageDigest;
        }

        @Override // com.bumptech.glide.util.pool.FactoryPools.Poolable
        @NonNull
        public StateVerifier getVerifier() {
            return this.stateVerifier;
        }
    }

    public final String calculateHexStringDigest(Key key) {
        PoolableDigestContainer poolableDigestContainerAcquire = this.digestPool.acquire();
        Preconditions.checkNotNull(poolableDigestContainerAcquire, "Argument must not be null");
        PoolableDigestContainer poolableDigestContainer = poolableDigestContainerAcquire;
        try {
            key.updateDiskCacheKey(poolableDigestContainer.messageDigest);
            return Util.sha256BytesToHex(poolableDigestContainer.messageDigest.digest());
        } finally {
            this.digestPool.release(poolableDigestContainer);
        }
    }

    public String getSafeKey(Key key) {
        String strCalculateHexStringDigest;
        synchronized (this.loadIdToSafeHash) {
            strCalculateHexStringDigest = this.loadIdToSafeHash.get(key);
        }
        if (strCalculateHexStringDigest == null) {
            strCalculateHexStringDigest = calculateHexStringDigest(key);
        }
        synchronized (this.loadIdToSafeHash) {
            this.loadIdToSafeHash.put(key, strCalculateHexStringDigest);
        }
        return strCalculateHexStringDigest;
    }
}
