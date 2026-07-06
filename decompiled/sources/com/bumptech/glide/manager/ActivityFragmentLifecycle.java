package com.bumptech.glide.manager;

import androidx.annotation.NonNull;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ActivityFragmentLifecycle implements Lifecycle {
    public boolean isDestroyed;
    public boolean isStarted;
    public final Set<LifecycleListener> lifecycleListeners = Collections.newSetFromMap(new WeakHashMap());

    @Override // com.bumptech.glide.manager.Lifecycle
    public void addListener(@NonNull LifecycleListener lifecycleListener) {
        this.lifecycleListeners.add(lifecycleListener);
        if (this.isDestroyed) {
            lifecycleListener.onDestroy();
        } else if (this.isStarted) {
            lifecycleListener.onStart();
        } else {
            lifecycleListener.onStop();
        }
    }

    public void onDestroy() {
        this.isDestroyed = true;
        ArrayList arrayList = (ArrayList) Util.getSnapshot(this.lifecycleListeners);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((LifecycleListener) obj).onDestroy();
        }
    }

    public void onStart() {
        this.isStarted = true;
        ArrayList arrayList = (ArrayList) Util.getSnapshot(this.lifecycleListeners);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((LifecycleListener) obj).onStart();
        }
    }

    public void onStop() {
        int i = 0;
        this.isStarted = false;
        ArrayList arrayList = (ArrayList) Util.getSnapshot(this.lifecycleListeners);
        int size = arrayList.size();
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((LifecycleListener) obj).onStop();
        }
    }

    @Override // com.bumptech.glide.manager.Lifecycle
    public void removeListener(@NonNull LifecycleListener lifecycleListener) {
        this.lifecycleListeners.remove(lifecycleListener);
    }
}
