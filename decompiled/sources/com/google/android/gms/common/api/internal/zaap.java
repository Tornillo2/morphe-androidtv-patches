package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zaap extends zaav {
    public final /* synthetic */ zaaw zaa;
    public final ArrayList zac;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaap(zaaw zaawVar, ArrayList arrayList) {
        super(zaawVar, null);
        this.zaa = zaawVar;
        this.zac = arrayList;
    }

    @Override // com.google.android.gms.common.api.internal.zaav
    @WorkerThread
    public final void zaa() {
        zaaw zaawVar = this.zaa;
        zaawVar.zaa.zag.zad = zaaw.zao(zaawVar);
        ArrayList arrayList = this.zac;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Api.Client client = (Api.Client) arrayList.get(i);
            zaaw zaawVar2 = this.zaa;
            client.getRemoteService(zaawVar2.zao, zaawVar2.zaa.zag.zad);
        }
    }
}
