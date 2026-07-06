package com.google.android.gms.internal.ads;

import android.os.IInterface;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class zzbwa implements zzbzv {
    public static final /* synthetic */ zzbwa zza = new zzbwa();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzbzv
    public final Object zza(Object obj) {
        if (obj == 0) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdCreator");
        return iInterfaceQueryLocalInterface instanceof zzbvt ? (zzbvt) iInterfaceQueryLocalInterface : new zzbvt(obj);
    }
}
