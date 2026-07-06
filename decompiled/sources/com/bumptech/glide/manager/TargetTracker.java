package com.bumptech.glide.manager;

import androidx.annotation.NonNull;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TargetTracker implements LifecycleListener {
    public final Set<Target<?>> targets = Collections.newSetFromMap(new WeakHashMap());

    public void clear() {
        this.targets.clear();
    }

    @NonNull
    public List<Target<?>> getAll() {
        return Util.getSnapshot(this.targets);
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onDestroy() {
        ArrayList arrayList = (ArrayList) Util.getSnapshot(this.targets);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((Target) obj).onDestroy();
        }
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStart() {
        ArrayList arrayList = (ArrayList) Util.getSnapshot(this.targets);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((Target) obj).onStart();
        }
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStop() {
        ArrayList arrayList = (ArrayList) Util.getSnapshot(this.targets);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((Target) obj).onStop();
        }
    }

    public void track(@NonNull Target<?> target) {
        this.targets.add(target);
    }

    public void untrack(@NonNull Target<?> target) {
        this.targets.remove(target);
    }
}
