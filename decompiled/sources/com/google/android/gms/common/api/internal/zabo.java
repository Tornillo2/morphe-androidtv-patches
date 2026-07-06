package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zabo implements Runnable {
    public final /* synthetic */ zabp zaa;

    public zabo(zabp zabpVar) {
        this.zaa = zabpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Api.Client client = this.zaa.zaa.zac;
        client.disconnect(client.getClass().getName().concat(" disconnecting because it was signed out."));
    }
}
