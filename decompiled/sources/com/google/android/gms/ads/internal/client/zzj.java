package com.google.android.gms.ads.internal.client;

import android.os.IInterface;
import com.google.android.gms.internal.ads.zzbzv;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class zzj implements zzbzv {
    public static final /* synthetic */ zzj zza = new zzj();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzbzv
    public final Object zza(Object obj) {
        if (obj == 0) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        return iInterfaceQueryLocalInterface instanceof zzbv ? (zzbv) iInterfaceQueryLocalInterface : new zzbv(obj);
    }
}
