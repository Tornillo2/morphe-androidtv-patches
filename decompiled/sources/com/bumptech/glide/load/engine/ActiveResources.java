package com.bumptech.glide.load.engine;

import android.os.Process;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.EngineResource;
import com.bumptech.glide.util.Preconditions;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ActiveResources {

    @VisibleForTesting
    public final Map<Key, ResourceWeakReference> activeEngineResources;

    @Nullable
    public volatile DequeuedResourceCallback cb;
    public final boolean isActiveResourceRetentionAllowed;
    public volatile boolean isShutdown;
    public EngineResource.ResourceListener listener;
    public final Executor monitorClearedResourcesExecutor;
    public final ReferenceQueue<EngineResource<?>> resourceReferenceQueue;

    /* JADX INFO: renamed from: com.bumptech.glide.load.engine.ActiveResources$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements ThreadFactory {
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull final Runnable runnable) {
            return new Thread(new Runnable() { // from class: com.bumptech.glide.load.engine.ActiveResources.1.1
                @Override // java.lang.Runnable
                public void run() {
                    Process.setThreadPriority(10);
                    runnable.run();
                }
            }, "glide-active-resources");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public interface DequeuedResourceCallback {
        void onResourceDequeued();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static final class ResourceWeakReference extends WeakReference<EngineResource<?>> {
        public final boolean isCacheable;
        public final Key key;

        @Nullable
        public Resource<?> resource;

        public ResourceWeakReference(@NonNull Key key, @NonNull EngineResource<?> engineResource, @NonNull ReferenceQueue<? super EngineResource<?>> referenceQueue, boolean z) {
            Resource<?> resource;
            super(engineResource, referenceQueue);
            Preconditions.checkNotNull(key, "Argument must not be null");
            this.key = key;
            if (engineResource.isMemoryCacheable() && z) {
                resource = engineResource.getResource();
                Preconditions.checkNotNull(resource, "Argument must not be null");
            } else {
                resource = null;
            }
            this.resource = resource;
            this.isCacheable = engineResource.isMemoryCacheable();
        }

        public void reset() {
            this.resource = null;
            clear();
        }
    }

    public ActiveResources(boolean z) {
        this(z, Executors.newSingleThreadExecutor(new AnonymousClass1()));
    }

    public synchronized void activate(Key key, EngineResource<?> engineResource) {
        ResourceWeakReference resourceWeakReferencePut = this.activeEngineResources.put(key, new ResourceWeakReference(key, engineResource, this.resourceReferenceQueue, this.isActiveResourceRetentionAllowed));
        if (resourceWeakReferencePut != null) {
            resourceWeakReferencePut.reset();
        }
    }

    public void cleanReferenceQueue() {
        while (!this.isShutdown) {
            try {
                cleanupActiveReference((ResourceWeakReference) this.resourceReferenceQueue.remove());
                DequeuedResourceCallback dequeuedResourceCallback = this.cb;
                if (dequeuedResourceCallback != null) {
                    dequeuedResourceCallback.onResourceDequeued();
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void cleanupActiveReference(@NonNull ResourceWeakReference resourceWeakReference) {
        Resource<?> resource;
        synchronized (this) {
            this.activeEngineResources.remove(resourceWeakReference.key);
            if (resourceWeakReference.isCacheable && (resource = resourceWeakReference.resource) != null) {
                this.listener.onResourceReleased(resourceWeakReference.key, new EngineResource<>(resource, true, false, resourceWeakReference.key, this.listener));
            }
        }
    }

    public synchronized void deactivate(Key key) {
        ResourceWeakReference resourceWeakReferenceRemove = this.activeEngineResources.remove(key);
        if (resourceWeakReferenceRemove != null) {
            resourceWeakReferenceRemove.reset();
        }
    }

    @Nullable
    public synchronized EngineResource<?> get(Key key) {
        ResourceWeakReference resourceWeakReference = this.activeEngineResources.get(key);
        if (resourceWeakReference == null) {
            return null;
        }
        EngineResource<?> engineResource = resourceWeakReference.get();
        if (engineResource == null) {
            cleanupActiveReference(resourceWeakReference);
        }
        return engineResource;
    }

    @VisibleForTesting
    public void setDequeuedResourceCallback(DequeuedResourceCallback dequeuedResourceCallback) {
        this.cb = dequeuedResourceCallback;
    }

    public void setListener(EngineResource.ResourceListener resourceListener) {
        synchronized (resourceListener) {
            synchronized (this) {
                this.listener = resourceListener;
            }
        }
    }

    @VisibleForTesting
    public void shutdown() {
        this.isShutdown = true;
        Executor executor = this.monitorClearedResourcesExecutor;
        if (executor instanceof ExecutorService) {
            com.bumptech.glide.util.Executors.shutdownAndAwaitTermination((ExecutorService) executor);
        }
    }

    @VisibleForTesting
    public ActiveResources(boolean z, Executor executor) {
        this.activeEngineResources = new HashMap();
        this.resourceReferenceQueue = new ReferenceQueue<>();
        this.isActiveResourceRetentionAllowed = z;
        this.monitorClearedResourcesExecutor = executor;
        executor.execute(new Runnable() { // from class: com.bumptech.glide.load.engine.ActiveResources.2
            @Override // java.lang.Runnable
            public void run() {
                ActiveResources.this.cleanReferenceQueue();
            }
        });
    }
}
