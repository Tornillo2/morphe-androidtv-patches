package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zax implements zabz {
    public final /* synthetic */ zaaa zaa;

    public /* synthetic */ zax(zaaa zaaaVar, zaw zawVar) {
        this.zaa = zaaaVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zaa(@NonNull ConnectionResult connectionResult) {
        this.zaa.zam.lock();
        try {
            zaaa zaaaVar = this.zaa;
            zaaaVar.zaj = connectionResult;
            zaaa.zap(zaaaVar);
        } finally {
            this.zaa.zam.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zab(@Nullable Bundle bundle) {
        this.zaa.zam.lock();
        try {
            zaaa.zao(this.zaa, bundle);
            zaaa zaaaVar = this.zaa;
            zaaaVar.zaj = ConnectionResult.RESULT_SUCCESS;
            zaaa.zap(zaaaVar);
        } finally {
            this.zaa.zam.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabz
    public final void zac(int i, boolean z) {
        ConnectionResult connectionResult;
        this.zaa.zam.lock();
        try {
            zaaa zaaaVar = this.zaa;
            if (zaaaVar.zal || (connectionResult = zaaaVar.zak) == null || !connectionResult.isSuccess()) {
                zaaa zaaaVar2 = this.zaa;
                zaaaVar2.zal = false;
                zaaa.zan(zaaaVar2, i, z);
            } else {
                zaaa zaaaVar3 = this.zaa;
                zaaaVar3.zal = true;
                zaaaVar3.zae.onConnectionSuspended(i);
            }
            this.zaa.zam.unlock();
        } catch (Throwable th) {
            this.zaa.zam.unlock();
            throw th;
        }
    }
}
