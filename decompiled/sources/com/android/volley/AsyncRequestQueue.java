package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.volley.AsyncCache;
import com.android.volley.AsyncNetwork;
import com.android.volley.Cache;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AsyncRequestQueue extends RequestQueue {
    public static final int DEFAULT_BLOCKING_THREAD_POOL_SIZE = 4;

    @Nullable
    public final AsyncCache mAsyncCache;
    public ExecutorService mBlockingExecutor;
    public final Object mCacheInitializationLock;
    public ExecutorFactory mExecutorFactory;
    public volatile boolean mIsCacheInitialized;
    public final AsyncNetwork mNetwork;
    public ExecutorService mNonBlockingExecutor;
    public ScheduledExecutorService mNonBlockingScheduledExecutor;
    public final List<Request<?>> mRequestsAwaitingCacheInitialization;
    public final WaitingRequestManager mWaitingRequestManager;

    /* JADX INFO: renamed from: com.android.volley.AsyncRequestQueue$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass3 implements Comparator<Runnable> {
        @Override // java.util.Comparator
        public int compare(Runnable runnable, Runnable runnable2) {
            if (!(runnable instanceof RequestTask)) {
                return runnable2 instanceof RequestTask ? -1 : 0;
            }
            if (runnable2 instanceof RequestTask) {
                return ((RequestTask) runnable).compareTo((RequestTask) runnable2);
            }
            return 1;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public final AsyncNetwork mNetwork;

        @Nullable
        public AsyncCache mAsyncCache = null;

        @Nullable
        public Cache mCache = null;

        @Nullable
        public ExecutorFactory mExecutorFactory = null;

        @Nullable
        public ResponseDelivery mResponseDelivery = null;

        /* JADX INFO: renamed from: com.android.volley.AsyncRequestQueue$Builder$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 extends ExecutorFactory {

            /* JADX INFO: renamed from: com.android.volley.AsyncRequestQueue$Builder$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
            public class ThreadFactoryC00091 implements ThreadFactory {
                public final /* synthetic */ String val$threadNameSuffix;

                public ThreadFactoryC00091(String str) {
                    this.val$threadNameSuffix = str;
                }

                @Override // java.util.concurrent.ThreadFactory
                public Thread newThread(@NonNull Runnable runnable) {
                    Thread threadNewThread = Executors.defaultThreadFactory().newThread(runnable);
                    threadNewThread.setName("Volley-" + this.val$threadNameSuffix);
                    return threadNewThread;
                }
            }

            public AnonymousClass1() {
            }

            @Override // com.android.volley.AsyncRequestQueue.ExecutorFactory
            public ExecutorService createBlockingExecutor(BlockingQueue<Runnable> blockingQueue) {
                return getNewThreadPoolExecutor(4, "BlockingExecutor", blockingQueue);
            }

            @Override // com.android.volley.AsyncRequestQueue.ExecutorFactory
            public ExecutorService createNonBlockingExecutor(BlockingQueue<Runnable> blockingQueue) {
                return getNewThreadPoolExecutor(1, "Non-BlockingExecutor", blockingQueue);
            }

            @Override // com.android.volley.AsyncRequestQueue.ExecutorFactory
            public ScheduledExecutorService createNonBlockingScheduledExecutor() {
                return new ScheduledThreadPoolExecutor(0, new ThreadFactoryC00091("ScheduledExecutor"));
            }

            public final ThreadPoolExecutor getNewThreadPoolExecutor(int i, String str, BlockingQueue<Runnable> blockingQueue) {
                return new ThreadPoolExecutor(0, i, 60L, TimeUnit.SECONDS, blockingQueue, new ThreadFactoryC00091(str));
            }

            public final ThreadFactory getThreadFactory(String str) {
                return new ThreadFactoryC00091(str);
            }
        }

        public Builder(AsyncNetwork asyncNetwork) {
            if (asyncNetwork == null) {
                throw new IllegalArgumentException("Network cannot be null");
            }
            this.mNetwork = asyncNetwork;
        }

        public AsyncRequestQueue build() {
            Cache cache = this.mCache;
            if (cache == null && this.mAsyncCache == null) {
                throw new IllegalArgumentException("You must set one of the cache objects");
            }
            if (cache == null) {
                this.mCache = new ThrowingCache();
            }
            if (this.mResponseDelivery == null) {
                this.mResponseDelivery = new ExecutorDelivery(new Handler(Looper.getMainLooper()));
            }
            if (this.mExecutorFactory == null) {
                this.mExecutorFactory = new AnonymousClass1();
            }
            return new AsyncRequestQueue(this.mCache, this.mNetwork, this.mAsyncCache, this.mResponseDelivery, this.mExecutorFactory);
        }

        public final ExecutorFactory getDefaultExecutorFactory() {
            return new AnonymousClass1();
        }

        public Builder setAsyncCache(AsyncCache asyncCache) {
            this.mAsyncCache = asyncCache;
            return this;
        }

        public Builder setCache(Cache cache) {
            this.mCache = cache;
            return this;
        }

        public Builder setExecutorFactory(ExecutorFactory executorFactory) {
            this.mExecutorFactory = executorFactory;
            return this;
        }

        public Builder setResponseDelivery(ResponseDelivery responseDelivery) {
            this.mResponseDelivery = responseDelivery;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class CacheParseTask<T> extends RequestTask<T> {
        public Cache.Entry entry;
        public long startTimeMillis;

        public CacheParseTask(Request<T> request, Cache.Entry entry, long j) {
            super(request);
            this.entry = entry;
            this.startTimeMillis = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mRequest.addMarker("cache-hit");
            Request<T> request = this.mRequest;
            Cache.Entry entry = this.entry;
            Response<T> networkResponse = request.parseNetworkResponse(new NetworkResponse(200, entry.data, false, 0L, entry.allResponseHeaders));
            this.mRequest.addMarker("cache-hit-parsed");
            if (!this.entry.refreshNeeded(this.startTimeMillis)) {
                AsyncRequestQueue.this.getResponseDelivery().postResponse(this.mRequest, networkResponse);
                return;
            }
            this.mRequest.addMarker("cache-hit-refresh-needed");
            this.mRequest.setCacheEntry(this.entry);
            networkResponse.intermediate = true;
            if (AsyncRequestQueue.this.mWaitingRequestManager.maybeAddToWaitingRequests(this.mRequest)) {
                AsyncRequestQueue.this.getResponseDelivery().postResponse(this.mRequest, networkResponse);
            } else {
                AsyncRequestQueue.this.getResponseDelivery().postResponse(this.mRequest, networkResponse, new Runnable() { // from class: com.android.volley.AsyncRequestQueue.CacheParseTask.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CacheParseTask cacheParseTask = CacheParseTask.this;
                        AsyncRequestQueue.this.sendRequestOverNetwork(cacheParseTask.mRequest);
                    }
                });
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class CachePutTask<T> extends RequestTask<T> {
        public Response<?> response;

        public CachePutTask(Request<T> request, Response<?> response) {
            super(request);
            this.response = response;
        }

        @Override // java.lang.Runnable
        public void run() {
            AsyncRequestQueue asyncRequestQueue = AsyncRequestQueue.this;
            AsyncCache asyncCache = asyncRequestQueue.mAsyncCache;
            if (asyncCache != null) {
                asyncCache.put(this.mRequest.getCacheKey(), this.response.cacheEntry, new AsyncCache.OnWriteCompleteCallback() { // from class: com.android.volley.AsyncRequestQueue.CachePutTask.1
                    @Override // com.android.volley.AsyncCache.OnWriteCompleteCallback
                    public void onWriteComplete() {
                        CachePutTask cachePutTask = CachePutTask.this;
                        AsyncRequestQueue.this.finishRequest(cachePutTask.mRequest, cachePutTask.response, true);
                    }
                });
            } else {
                asyncRequestQueue.getCache().put(this.mRequest.getCacheKey(), this.response.cacheEntry);
                AsyncRequestQueue.this.finishRequest(this.mRequest, this.response, true);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class CacheTask<T> extends RequestTask<T> {
        public CacheTask(Request<T> request) {
            super(request);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mRequest.isCanceled()) {
                this.mRequest.finish("cache-discard-canceled");
                return;
            }
            this.mRequest.addMarker("cache-queue-take");
            AsyncRequestQueue asyncRequestQueue = AsyncRequestQueue.this;
            AsyncCache asyncCache = asyncRequestQueue.mAsyncCache;
            if (asyncCache != null) {
                asyncCache.get(this.mRequest.getCacheKey(), new AsyncCache.OnGetCompleteCallback() { // from class: com.android.volley.AsyncRequestQueue.CacheTask.1
                    @Override // com.android.volley.AsyncCache.OnGetCompleteCallback
                    public void onGetComplete(Cache.Entry entry) {
                        CacheTask cacheTask = CacheTask.this;
                        AsyncRequestQueue.this.handleEntry(entry, cacheTask.mRequest);
                    }
                });
            } else {
                AsyncRequestQueue.this.handleEntry(asyncRequestQueue.getCache().get(this.mRequest.getCacheKey()), this.mRequest);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class ExecutorFactory {
        public abstract ExecutorService createBlockingExecutor(BlockingQueue<Runnable> blockingQueue);

        public abstract ExecutorService createNonBlockingExecutor(BlockingQueue<Runnable> blockingQueue);

        public abstract ScheduledExecutorService createNonBlockingScheduledExecutor();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class NetworkParseTask<T> extends RequestTask<T> {
        public NetworkResponse networkResponse;

        public NetworkParseTask(Request<T> request, NetworkResponse networkResponse) {
            super(request);
            this.networkResponse = networkResponse;
        }

        @Override // java.lang.Runnable
        public void run() {
            Response<T> networkResponse = this.mRequest.parseNetworkResponse(this.networkResponse);
            this.mRequest.addMarker("network-parse-complete");
            Request<T> request = this.mRequest;
            if (!request.mShouldCache || networkResponse.cacheEntry == null) {
                AsyncRequestQueue.this.finishRequest(request, networkResponse, false);
                return;
            }
            AsyncRequestQueue asyncRequestQueue = AsyncRequestQueue.this;
            if (asyncRequestQueue.mAsyncCache != null) {
                asyncRequestQueue.mNonBlockingExecutor.execute(asyncRequestQueue.new CachePutTask(request, networkResponse));
            } else {
                asyncRequestQueue.mBlockingExecutor.execute(asyncRequestQueue.new CachePutTask(request, networkResponse));
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class NetworkTask<T> extends RequestTask<T> {
        public NetworkTask(Request<T> request) {
            super(request);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mRequest.isCanceled()) {
                this.mRequest.finish("network-discard-cancelled");
                this.mRequest.notifyListenerResponseNotUsable();
            } else {
                final long jElapsedRealtime = SystemClock.elapsedRealtime();
                this.mRequest.addMarker("network-queue-take");
                AsyncRequestQueue.this.mNetwork.performRequest(this.mRequest, new AsyncNetwork.OnRequestComplete() { // from class: com.android.volley.AsyncRequestQueue.NetworkTask.1
                    @Override // com.android.volley.AsyncNetwork.OnRequestComplete
                    public void onError(VolleyError volleyError) {
                        volleyError.setNetworkTimeMs(SystemClock.elapsedRealtime() - jElapsedRealtime);
                        NetworkTask networkTask = NetworkTask.this;
                        AsyncRequestQueue asyncRequestQueue = AsyncRequestQueue.this;
                        asyncRequestQueue.mBlockingExecutor.execute(asyncRequestQueue.new ParseErrorTask(networkTask.mRequest, volleyError));
                    }

                    @Override // com.android.volley.AsyncNetwork.OnRequestComplete
                    public void onSuccess(NetworkResponse networkResponse) {
                        NetworkTask.this.mRequest.addMarker("network-http-complete");
                        if (networkResponse.notModified && NetworkTask.this.mRequest.hasHadResponseDelivered()) {
                            NetworkTask.this.mRequest.finish("not-modified");
                            NetworkTask.this.mRequest.notifyListenerResponseNotUsable();
                        } else {
                            NetworkTask networkTask = NetworkTask.this;
                            AsyncRequestQueue asyncRequestQueue = AsyncRequestQueue.this;
                            asyncRequestQueue.mBlockingExecutor.execute(asyncRequestQueue.new NetworkParseTask(networkTask.mRequest, networkResponse));
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class ParseErrorTask<T> extends RequestTask<T> {
        public VolleyError volleyError;

        public ParseErrorTask(Request<T> request, VolleyError volleyError) {
            super(request);
            this.volleyError = volleyError;
        }

        @Override // java.lang.Runnable
        public void run() {
            AsyncRequestQueue.this.getResponseDelivery().postError(this.mRequest, this.mRequest.parseNetworkError(this.volleyError));
            this.mRequest.notifyListenerResponseNotUsable();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ThrowingCache implements Cache {
        public ThrowingCache() {
        }

        @Override // com.android.volley.Cache
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // com.android.volley.Cache
        public Cache.Entry get(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // com.android.volley.Cache
        public void initialize() {
            throw new UnsupportedOperationException();
        }

        @Override // com.android.volley.Cache
        public void invalidate(String str, boolean z) {
            throw new UnsupportedOperationException();
        }

        @Override // com.android.volley.Cache
        public void put(String str, Cache.Entry entry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.android.volley.Cache
        public void remove(String str) {
            throw new UnsupportedOperationException();
        }

        public ThrowingCache(AnonymousClass1 anonymousClass1) {
        }
    }

    public static PriorityBlockingQueue<Runnable> getBlockingQueue() {
        return new PriorityBlockingQueue<>(11, new AnonymousClass3());
    }

    @Override // com.android.volley.RequestQueue
    public <T> void beginRequest(Request<T> request) {
        if (!this.mIsCacheInitialized) {
            synchronized (this.mCacheInitializationLock) {
                try {
                    if (!this.mIsCacheInitialized) {
                        this.mRequestsAwaitingCacheInitialization.add(request);
                        return;
                    }
                } finally {
                }
            }
        }
        if (!request.mShouldCache) {
            sendRequestOverNetwork(request);
        } else if (this.mAsyncCache != null) {
            this.mNonBlockingExecutor.execute(new CacheTask(request));
        } else {
            this.mBlockingExecutor.execute(new CacheTask(request));
        }
    }

    public final void finishRequest(Request<?> request, Response<?> response, boolean z) {
        if (z) {
            request.addMarker("network-cache-written");
        }
        request.markDelivered();
        getResponseDelivery().postResponse(request, response);
        request.notifyListenerResponseReceived(response);
    }

    public final void handleEntry(Cache.Entry entry, Request<?> request) {
        if (entry == null) {
            request.addMarker("cache-miss");
            if (this.mWaitingRequestManager.maybeAddToWaitingRequests(request)) {
                return;
            }
            sendRequestOverNetwork(request);
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (!entry.isExpired(jCurrentTimeMillis)) {
            this.mBlockingExecutor.execute(new CacheParseTask(request, entry, jCurrentTimeMillis));
            return;
        }
        request.addMarker("cache-hit-expired");
        request.setCacheEntry(entry);
        if (this.mWaitingRequestManager.maybeAddToWaitingRequests(request)) {
            return;
        }
        sendRequestOverNetwork(request);
    }

    public final void onCacheInitializationComplete() {
        ArrayList arrayList;
        synchronized (this.mCacheInitializationLock) {
            arrayList = new ArrayList(this.mRequestsAwaitingCacheInitialization);
            this.mRequestsAwaitingCacheInitialization.clear();
            this.mIsCacheInitialized = true;
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            beginRequest((Request) obj);
        }
    }

    @Override // com.android.volley.RequestQueue
    public <T> void sendRequestOverNetwork(Request<T> request) {
        this.mNonBlockingExecutor.execute(new NetworkTask(request));
    }

    @Override // com.android.volley.RequestQueue
    public void start() {
        stop();
        this.mNonBlockingExecutor = this.mExecutorFactory.createNonBlockingExecutor(getBlockingQueue());
        this.mBlockingExecutor = this.mExecutorFactory.createBlockingExecutor(getBlockingQueue());
        this.mNonBlockingScheduledExecutor = this.mExecutorFactory.createNonBlockingScheduledExecutor();
        this.mNetwork.setBlockingExecutor(this.mBlockingExecutor);
        this.mNetwork.setNonBlockingExecutor(this.mNonBlockingExecutor);
        this.mNetwork.setNonBlockingScheduledExecutor(this.mNonBlockingScheduledExecutor);
        if (this.mAsyncCache != null) {
            this.mNonBlockingExecutor.execute(new Runnable() { // from class: com.android.volley.AsyncRequestQueue.1
                @Override // java.lang.Runnable
                public void run() {
                    AsyncRequestQueue.this.mAsyncCache.initialize(new AsyncCache.OnWriteCompleteCallback() { // from class: com.android.volley.AsyncRequestQueue.1.1
                        @Override // com.android.volley.AsyncCache.OnWriteCompleteCallback
                        public void onWriteComplete() {
                            AsyncRequestQueue.this.onCacheInitializationComplete();
                        }
                    });
                }
            });
        } else {
            this.mBlockingExecutor.execute(new Runnable() { // from class: com.android.volley.AsyncRequestQueue.2
                @Override // java.lang.Runnable
                public void run() {
                    AsyncRequestQueue.this.getCache().initialize();
                    AsyncRequestQueue.this.mNonBlockingExecutor.execute(new Runnable() { // from class: com.android.volley.AsyncRequestQueue.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AsyncRequestQueue.this.onCacheInitializationComplete();
                        }
                    });
                }
            });
        }
    }

    @Override // com.android.volley.RequestQueue
    public void stop() {
        ExecutorService executorService = this.mNonBlockingExecutor;
        if (executorService != null) {
            executorService.shutdownNow();
            this.mNonBlockingExecutor = null;
        }
        ExecutorService executorService2 = this.mBlockingExecutor;
        if (executorService2 != null) {
            executorService2.shutdownNow();
            this.mBlockingExecutor = null;
        }
        ScheduledExecutorService scheduledExecutorService = this.mNonBlockingScheduledExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.mNonBlockingScheduledExecutor = null;
        }
    }

    public AsyncRequestQueue(Cache cache, AsyncNetwork asyncNetwork, @Nullable AsyncCache asyncCache, ResponseDelivery responseDelivery, ExecutorFactory executorFactory) {
        super(cache, asyncNetwork, 0, responseDelivery);
        this.mWaitingRequestManager = new WaitingRequestManager(this);
        this.mRequestsAwaitingCacheInitialization = new ArrayList();
        this.mIsCacheInitialized = false;
        this.mCacheInitializationLock = new Object[0];
        this.mAsyncCache = asyncCache;
        this.mNetwork = asyncNetwork;
        this.mExecutorFactory = executorFactory;
    }
}
