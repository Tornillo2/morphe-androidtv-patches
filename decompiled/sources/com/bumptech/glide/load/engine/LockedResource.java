package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LockedResource<Z> implements Resource<Z>, FactoryPools.Poolable {
    public static final Pools.Pool<LockedResource<?>> POOL = FactoryPools.threadSafe(20, new AnonymousClass1());
    public boolean isLocked;
    public boolean isRecycled;
    public final StateVerifier stateVerifier = new StateVerifier.DefaultStateVerifier();
    public Resource<Z> toWrap;

    /* JADX INFO: renamed from: com.bumptech.glide.load.engine.LockedResource$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements FactoryPools.Factory<LockedResource<?>> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.util.pool.FactoryPools.Factory
        public LockedResource<?> create() {
            return new LockedResource<>();
        }
    }

    @NonNull
    public static <Z> LockedResource<Z> obtain(Resource<Z> resource) {
        LockedResource<Z> lockedResource = (LockedResource) POOL.acquire();
        Preconditions.checkNotNull(lockedResource, "Argument must not be null");
        lockedResource.init(resource);
        return lockedResource;
    }

    private void release() {
        this.toWrap = null;
        POOL.release(this);
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public Z get() {
        return this.toWrap.get();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public Class<Z> getResourceClass() {
        return this.toWrap.getResourceClass();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public int getSize() {
        return this.toWrap.getSize();
    }

    @Override // com.bumptech.glide.util.pool.FactoryPools.Poolable
    @NonNull
    public StateVerifier getVerifier() {
        return this.stateVerifier;
    }

    public final void init(Resource<Z> resource) {
        this.isRecycled = false;
        this.isLocked = true;
        this.toWrap = resource;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public synchronized void recycle() {
        this.stateVerifier.throwIfRecycled();
        this.isRecycled = true;
        if (!this.isLocked) {
            this.toWrap.recycle();
            release();
        }
    }

    public synchronized void unlock() {
        this.stateVerifier.throwIfRecycled();
        if (!this.isLocked) {
            throw new IllegalStateException("Already unlocked");
        }
        this.isLocked = false;
        if (this.isRecycled) {
            recycle();
        }
    }
}
