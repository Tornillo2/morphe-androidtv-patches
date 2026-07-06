package com.google.android.gms.tasks;

import android.app.Activity;
import androidx.annotation.MainThread;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzv extends LifecycleCallback {
    public final List zza;

    public zzv(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.zza = new ArrayList();
        lifecycleFragment.addCallback("TaskOnStopCallback", this);
    }

    public static zzv zza(Activity activity) {
        zzv zzvVar;
        LifecycleFragment fragment = LifecycleCallback.getFragment(activity);
        synchronized (fragment) {
            try {
                zzvVar = (zzv) fragment.getCallbackOrNull("TaskOnStopCallback", zzv.class);
                if (zzvVar == null) {
                    zzvVar = new zzv(fragment);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzvVar;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    @MainThread
    public final void onStop() {
        synchronized (this.zza) {
            try {
                Iterator it = this.zza.iterator();
                while (it.hasNext()) {
                    zzq zzqVar = (zzq) ((WeakReference) it.next()).get();
                    if (zzqVar != null) {
                        zzqVar.zzc();
                    }
                }
                this.zza.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zzb(zzq zzqVar) {
        synchronized (this.zza) {
            this.zza.add(new WeakReference(zzqVar));
        }
    }
}
