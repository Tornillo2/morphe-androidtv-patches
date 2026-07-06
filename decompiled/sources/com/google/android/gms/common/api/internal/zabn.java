package com.google.android.gms.common.api.internal;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zabn implements Runnable {
    public final /* synthetic */ int zaa;
    public final /* synthetic */ zabq zab;

    public zabn(zabq zabqVar, int i) {
        this.zab = zabqVar;
        this.zaa = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zab.zaI(this.zaa);
    }
}
