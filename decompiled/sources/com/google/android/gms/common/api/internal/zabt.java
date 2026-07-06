package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zabt implements Runnable {
    public final /* synthetic */ ConnectionResult zaa;
    public final /* synthetic */ zabu zab;

    public zabt(zabu zabuVar, ConnectionResult connectionResult) {
        this.zab = zabuVar;
        this.zaa = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zabu zabuVar = this.zab;
        zabq zabqVar = (zabq) zabuVar.zaa.zan.get(zabuVar.zac);
        if (zabqVar == null) {
            return;
        }
        if (!this.zaa.isSuccess()) {
            zabqVar.zar(this.zaa, null);
            return;
        }
        zabu zabuVar2 = this.zab;
        zabuVar2.zaf = true;
        if (zabuVar2.zab.requiresSignIn()) {
            this.zab.zah();
            return;
        }
        try {
            Api.Client client = this.zab.zab;
            client.getRemoteService(null, client.getScopesForConnectionlessNonSignIn());
        } catch (SecurityException e) {
            Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
            this.zab.zab.disconnect("Failed to get service from broker.");
            zabqVar.zar(new ConnectionResult(10), null);
        }
    }
}
