package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzw extends zzaq implements zzx {
    public zzw() {
        super("com.android.vending.billing.IInAppBillingCreateAlternativeBillingOnlyTokenCallback");
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzaq
    public final boolean zzb(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        Bundle bundle = (Bundle) zzar.zza(parcel, Bundle.CREATOR);
        zzar.zzb(parcel);
        zza(bundle);
        return true;
    }
}
