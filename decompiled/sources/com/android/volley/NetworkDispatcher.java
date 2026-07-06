package com.android.volley;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.BlockingQueue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class NetworkDispatcher extends Thread {
    public final Cache mCache;
    public final ResponseDelivery mDelivery;
    public final Network mNetwork;
    public final BlockingQueue<Request<?>> mQueue;
    public volatile boolean mQuit = false;

    public NetworkDispatcher(BlockingQueue<Request<?>> blockingQueue, Network network, Cache cache, ResponseDelivery responseDelivery) {
        this.mQueue = blockingQueue;
        this.mNetwork = network;
        this.mCache = cache;
        this.mDelivery = responseDelivery;
    }

    private void processRequest() throws InterruptedException {
        processRequest(this.mQueue.take());
    }

    @TargetApi(14)
    public final void addTrafficStatsTag(Request<?> request) {
        TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
    }

    public final void parseAndDeliverNetworkError(Request<?> request, VolleyError volleyError) {
        this.mDelivery.postError(request, request.parseNetworkError(volleyError));
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                processRequest();
            } catch (InterruptedException unused) {
                if (this.mQuit) {
                    Thread.currentThread().interrupt();
                    return;
                }
                VolleyLog.e("Ignoring spurious interrupt of NetworkDispatcher thread; use quit() to terminate it", new Object[0]);
            }
        }
    }

    @VisibleForTesting
    public void processRequest(Request<?> request) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        request.sendEvent(3);
        try {
            try {
                try {
                    request.addMarker("network-queue-take");
                    if (request.isCanceled()) {
                        request.finish("network-discard-cancelled");
                        request.notifyListenerResponseNotUsable();
                        return;
                    }
                    TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
                    NetworkResponse networkResponsePerformRequest = this.mNetwork.performRequest(request);
                    request.addMarker("network-http-complete");
                    if (networkResponsePerformRequest.notModified && request.hasHadResponseDelivered()) {
                        request.finish("not-modified");
                        request.notifyListenerResponseNotUsable();
                        return;
                    }
                    Response<?> networkResponse = request.parseNetworkResponse(networkResponsePerformRequest);
                    request.addMarker("network-parse-complete");
                    if (request.mShouldCache && networkResponse.cacheEntry != null) {
                        this.mCache.put(request.getCacheKey(), networkResponse.cacheEntry);
                        request.addMarker("network-cache-written");
                    }
                    request.markDelivered();
                    this.mDelivery.postResponse(request, networkResponse);
                    request.notifyListenerResponseReceived(networkResponse);
                } catch (VolleyError e) {
                    e.setNetworkTimeMs(SystemClock.elapsedRealtime() - jElapsedRealtime);
                    this.mDelivery.postError(request, request.parseNetworkError(e));
                    request.notifyListenerResponseNotUsable();
                }
            } catch (Exception e2) {
                VolleyLog.e(e2, "Unhandled exception %s", e2.toString());
                VolleyError volleyError = new VolleyError(e2);
                volleyError.networkTimeMs = SystemClock.elapsedRealtime() - jElapsedRealtime;
                this.mDelivery.postError(request, volleyError);
                request.notifyListenerResponseNotUsable();
            }
        } finally {
            request.sendEvent(4);
        }
    }
}
