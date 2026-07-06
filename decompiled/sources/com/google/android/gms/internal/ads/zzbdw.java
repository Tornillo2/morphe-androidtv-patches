package com.google.android.gms.internal.ads;

import android.os.IInterface;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class zzbdw implements zzbzv {
    public static final /* synthetic */ zzbdw zza = new zzbdw();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzbzv
    public final Object zza(Object obj) {
        if (obj == 0) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface("com.google.android.gms.ads.internal.flags.IFlagRetrieverSupplierProxy");
        return iInterfaceQueryLocalInterface instanceof zzbdy ? (zzbdy) iInterfaceQueryLocalInterface : new zzbdy(obj);
    }
}
