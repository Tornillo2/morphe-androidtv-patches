package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzn implements Parcelable.Creator {
    public static void zza(GetServiceRequest getServiceRequest, Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int i2 = getServiceRequest.zzc;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        int i3 = getServiceRequest.zzd;
        SafeParcelWriter.zzc(parcel, 2, 4);
        parcel.writeInt(i3);
        int i4 = getServiceRequest.zze;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(i4);
        SafeParcelWriter.writeString(parcel, 4, getServiceRequest.zzf, false);
        SafeParcelWriter.writeIBinder(parcel, 5, getServiceRequest.zzg, false);
        SafeParcelWriter.writeTypedArray(parcel, 6, getServiceRequest.zzh, i, false);
        SafeParcelWriter.writeBundle(parcel, 7, getServiceRequest.zzi, false);
        SafeParcelWriter.writeParcelable(parcel, 8, getServiceRequest.zzj, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 10, getServiceRequest.zzk, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 11, getServiceRequest.zzl, i, false);
        boolean z = getServiceRequest.zzm;
        SafeParcelWriter.zzc(parcel, 12, 4);
        parcel.writeInt(z ? 1 : 0);
        int i5 = getServiceRequest.zzn;
        SafeParcelWriter.zzc(parcel, 13, 4);
        parcel.writeInt(i5);
        boolean z2 = getServiceRequest.zzo;
        SafeParcelWriter.zzc(parcel, 14, 4);
        parcel.writeInt(z2 ? 1 : 0);
        SafeParcelWriter.writeString(parcel, 15, getServiceRequest.zzp, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Scope[] scopeArr = GetServiceRequest.zza;
        Bundle bundle = new Bundle();
        Feature[] featureArr = GetServiceRequest.zzb;
        Feature[] featureArr2 = featureArr;
        String strCreateString = null;
        IBinder iBinder = null;
        Account account = null;
        String strCreateString2 = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        int i4 = 0;
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i5 = parcel.readInt();
            switch ((char) i5) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 3:
                    i3 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 4:
                    strCreateString = SafeParcelReader.createString(parcel, i5);
                    break;
                case 5:
                    iBinder = SafeParcelReader.readIBinder(parcel, i5);
                    break;
                case 6:
                    scopeArr = (Scope[]) SafeParcelReader.createTypedArray(parcel, i5, Scope.CREATOR);
                    break;
                case 7:
                    bundle = SafeParcelReader.createBundle(parcel, i5);
                    break;
                case '\b':
                    account = (Account) SafeParcelReader.createParcelable(parcel, i5, Account.CREATOR);
                    break;
                case '\t':
                default:
                    SafeParcelReader.skipUnknownField(parcel, i5);
                    break;
                case '\n':
                    featureArr = (Feature[]) SafeParcelReader.createTypedArray(parcel, i5, Feature.CREATOR);
                    break;
                case 11:
                    featureArr2 = (Feature[]) SafeParcelReader.createTypedArray(parcel, i5, Feature.CREATOR);
                    break;
                case '\f':
                    z = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                case '\r':
                    i4 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 14:
                    z2 = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                case 15:
                    strCreateString2 = SafeParcelReader.createString(parcel, i5);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new GetServiceRequest(i, i2, i3, strCreateString, iBinder, scopeArr, bundle, account, featureArr, featureArr2, z, i4, z2, strCreateString2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new GetServiceRequest[i];
    }
}
