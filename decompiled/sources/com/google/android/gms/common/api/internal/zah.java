package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zah extends zad {
    public final ListenerHolder.ListenerKey zab;

    public zah(ListenerHolder.ListenerKey listenerKey, TaskCompletionSource taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zab = listenerKey;
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final boolean zaa(zabq zabqVar) {
        zaci zaciVar = (zaci) zabqVar.zag.get(this.zab);
        return zaciVar != null && zaciVar.zaa.zac;
    }

    @Override // com.google.android.gms.common.api.internal.zac
    @Nullable
    public final Feature[] zab(zabq zabqVar) {
        zaci zaciVar = (zaci) zabqVar.zag.get(this.zab);
        if (zaciVar == null) {
            return null;
        }
        return zaciVar.zaa.getRequiredFeatures();
    }

    @Override // com.google.android.gms.common.api.internal.zad
    public final void zac(zabq zabqVar) throws RemoteException {
        zaci zaciVar = (zaci) zabqVar.zag.remove(this.zab);
        if (zaciVar == null) {
            this.zaa.trySetResult(Boolean.FALSE);
            return;
        }
        zaciVar.zab.unregisterListener(zabqVar.zac, this.zaa);
        zaciVar.zaa.clearListener();
    }

    @Override // com.google.android.gms.common.api.internal.zad, com.google.android.gms.common.api.internal.zai
    public final /* bridge */ /* synthetic */ void zag(@NonNull zaad zaadVar, boolean z) {
    }
}
