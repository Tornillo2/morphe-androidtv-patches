package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzas extends zzap implements zzau {
    public zzas(IBinder iBinder) {
        super(iBinder, "com.google.android.apps.play.billingtestcompanion.aidl.IBillingOverrideService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzau
    public final void zza(String str, String str2, zzaw zzawVar) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeString(str);
        parcelZzs.writeString(str2);
        int i = zzar.zza;
        parcelZzs.writeStrongBinder(zzawVar);
        zzv(1, parcelZzs);
    }
}
