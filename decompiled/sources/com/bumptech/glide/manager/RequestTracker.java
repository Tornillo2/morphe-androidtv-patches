package com.bumptech.glide.manager;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class RequestTracker {
    public static final String TAG = "RequestTracker";
    public boolean isPaused;
    public final Set<Request> requests = Collections.newSetFromMap(new WeakHashMap());
    public final List<Request> pendingRequests = new ArrayList();

    @VisibleForTesting
    public void addRequest(Request request) {
        this.requests.add(request);
    }

    public boolean clearAndRemove(@Nullable Request request) {
        boolean z = true;
        if (request == null) {
            return true;
        }
        boolean zRemove = this.requests.remove(request);
        if (!this.pendingRequests.remove(request) && !zRemove) {
            z = false;
        }
        if (z) {
            request.clear();
        }
        return z;
    }

    public void clearRequests() {
        ArrayList arrayList = (ArrayList) Util.getSnapshot(this.requests);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            clearAndRemove((Request) obj);
        }
        this.pendingRequests.clear();
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public void pauseAllRequests() {
        this.isPaused = true;
        ArrayList arrayList = (ArrayList) Util.getSnapshot(this.requests);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            Request request = (Request) obj;
            if (request.isRunning() || request.isComplete()) {
                request.clear();
                this.pendingRequests.add(request);
            }
        }
    }

    public void pauseRequests() {
        this.isPaused = true;
        ArrayList arrayList = (ArrayList) Util.getSnapshot(this.requests);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            Request request = (Request) obj;
            if (request.isRunning()) {
                request.pause();
                this.pendingRequests.add(request);
            }
        }
    }

    public void restartRequests() {
        ArrayList arrayList = (ArrayList) Util.getSnapshot(this.requests);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            Request request = (Request) obj;
            if (!request.isComplete() && !request.isCleared()) {
                request.clear();
                if (this.isPaused) {
                    this.pendingRequests.add(request);
                } else {
                    request.begin();
                }
            }
        }
    }

    public void resumeRequests() {
        int i = 0;
        this.isPaused = false;
        ArrayList arrayList = (ArrayList) Util.getSnapshot(this.requests);
        int size = arrayList.size();
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            Request request = (Request) obj;
            if (!request.isComplete() && !request.isRunning()) {
                request.begin();
            }
        }
        this.pendingRequests.clear();
    }

    public void runRequest(@NonNull Request request) {
        this.requests.add(request);
        if (!this.isPaused) {
            request.begin();
            return;
        }
        request.clear();
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Paused, delaying request");
        }
        this.pendingRequests.add(request);
    }

    public String toString() {
        return super.toString() + "{numRequests=" + this.requests.size() + ", isPaused=" + this.isPaused + "}";
    }
}
