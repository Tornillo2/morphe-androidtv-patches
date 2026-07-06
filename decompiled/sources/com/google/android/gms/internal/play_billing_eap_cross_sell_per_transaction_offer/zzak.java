package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzak extends zzap implements zzam {
    public zzak(IBinder iBinder) {
        super(iBinder, "com.android.vending.billing.IInAppBillingService");
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final int zza(int i, String str, String str2) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(3);
        parcelZzs.writeString(str);
        parcelZzs.writeString(str2);
        Parcel parcelZzt = zzt(5, parcelZzs);
        int i2 = parcelZzt.readInt();
        parcelZzt.recycle();
        return i2;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final int zzc(int i, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(i);
        parcelZzs.writeString(str);
        parcelZzs.writeString(str2);
        zzar.zzc(parcelZzs, bundle);
        Parcel parcelZzt = zzt(10, parcelZzs);
        int i2 = parcelZzt.readInt();
        parcelZzt.recycle();
        return i2;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final Bundle zzd(int i, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(9);
        parcelZzs.writeString(str);
        parcelZzs.writeString(str2);
        zzar.zzc(parcelZzs, bundle);
        Parcel parcelZzt = zzt(902, parcelZzs);
        Bundle bundle2 = (Bundle) zzar.zza(parcelZzt, Bundle.CREATOR);
        parcelZzt.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final Bundle zze(int i, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(9);
        parcelZzs.writeString(str);
        parcelZzs.writeString(str2);
        zzar.zzc(parcelZzs, bundle);
        Parcel parcelZzt = zzt(12, parcelZzs);
        Bundle bundle2 = (Bundle) zzar.zza(parcelZzt, Bundle.CREATOR);
        parcelZzt.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final Bundle zzf(int i, String str, String str2, String str3, String str4) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(3);
        parcelZzs.writeString(str);
        parcelZzs.writeString(str2);
        parcelZzs.writeString(str3);
        parcelZzs.writeString(null);
        Parcel parcelZzt = zzt(3, parcelZzs);
        Bundle bundle = (Bundle) zzar.zza(parcelZzt, Bundle.CREATOR);
        parcelZzt.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final Bundle zzg(int i, String str, String str2, String str3, String str4, Bundle bundle) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(i);
        parcelZzs.writeString(str);
        parcelZzs.writeString(str2);
        parcelZzs.writeString(str3);
        parcelZzs.writeString(null);
        zzar.zzc(parcelZzs, bundle);
        Parcel parcelZzt = zzt(8, parcelZzs);
        Bundle bundle2 = (Bundle) zzar.zza(parcelZzt, Bundle.CREATOR);
        parcelZzt.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final Bundle zzh(int i, String str, String str2, String str3) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(3);
        parcelZzs.writeString(str);
        parcelZzs.writeString(str2);
        parcelZzs.writeString(str3);
        Parcel parcelZzt = zzt(4, parcelZzs);
        Bundle bundle = (Bundle) zzar.zza(parcelZzt, Bundle.CREATOR);
        parcelZzt.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final Bundle zzi(int i, String str, String str2, String str3, Bundle bundle) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(i);
        parcelZzs.writeString(str);
        parcelZzs.writeString(str2);
        parcelZzs.writeString(str3);
        zzar.zzc(parcelZzs, bundle);
        Parcel parcelZzt = zzt(11, parcelZzs);
        Bundle bundle2 = (Bundle) zzar.zza(parcelZzt, Bundle.CREATOR);
        parcelZzt.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final Bundle zzj(int i, String str, String str2, Bundle bundle, Bundle bundle2) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(i);
        parcelZzs.writeString(str);
        parcelZzs.writeString(str2);
        zzar.zzc(parcelZzs, bundle);
        parcelZzs.writeInt(1);
        bundle2.writeToParcel(parcelZzs, 0);
        Parcel parcelZzt = zzt(901, parcelZzs);
        Bundle bundle3 = (Bundle) zzar.zza(parcelZzt, Bundle.CREATOR);
        parcelZzt.recycle();
        return bundle3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final void zzk(int i, String str, Bundle bundle, zzx zzxVar) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(21);
        parcelZzs.writeString(str);
        zzar.zzc(parcelZzs, bundle);
        parcelZzs.writeStrongBinder(zzxVar);
        zzv(1501, parcelZzs);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final void zzl(int i, String str, Bundle bundle, zzz zzzVar) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(22);
        parcelZzs.writeString(str);
        zzar.zzc(parcelZzs, bundle);
        parcelZzs.writeStrongBinder(zzzVar);
        zzv(1801, parcelZzs);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final void zzm(int i, String str, Bundle bundle, zzab zzabVar) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(21);
        parcelZzs.writeString(str);
        zzar.zzc(parcelZzs, bundle);
        parcelZzs.writeStrongBinder(zzabVar);
        zzv(1601, parcelZzs);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final void zzn(int i, String str, Bundle bundle, zzad zzadVar) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(18);
        parcelZzs.writeString(str);
        zzar.zzc(parcelZzs, bundle);
        parcelZzs.writeStrongBinder(zzadVar);
        zzu(1301, parcelZzs);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final void zzo(int i, String str, Bundle bundle, zzaf zzafVar) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(22);
        parcelZzs.writeString(str);
        zzar.zzc(parcelZzs, bundle);
        parcelZzs.writeStrongBinder(zzafVar);
        zzv(1901, parcelZzs);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final void zzp(int i, String str, Bundle bundle, zzah zzahVar) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(21);
        parcelZzs.writeString(str);
        zzar.zzc(parcelZzs, bundle);
        parcelZzs.writeStrongBinder(zzahVar);
        zzv(1401, parcelZzs);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final void zzq(int i, String str, Bundle bundle, zzaj zzajVar) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(24);
        parcelZzs.writeString(str);
        zzar.zzc(parcelZzs, bundle);
        parcelZzs.writeStrongBinder(zzajVar);
        zzv(1701, parcelZzs);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final void zzr(int i, String str, Bundle bundle, zzao zzaoVar) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(12);
        parcelZzs.writeString(str);
        zzar.zzc(parcelZzs, bundle);
        parcelZzs.writeStrongBinder(zzaoVar);
        zzu(1201, parcelZzs);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam
    public final int zzw(int i, String str, String str2) throws RemoteException {
        Parcel parcelZzs = zzs();
        parcelZzs.writeInt(i);
        parcelZzs.writeString(str);
        parcelZzs.writeString(str2);
        Parcel parcelZzt = zzt(1, parcelZzs);
        int i2 = parcelZzt.readInt();
        parcelZzt.recycle();
        return i2;
    }
}
