package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbt {
    public Object[] zza = new Object[8];
    public int zzb = 0;
    public zzbs zzc;

    public final zzbt zza(Object obj, Object obj2) {
        int i = this.zzb + 1;
        Object[] objArr = this.zza;
        int length = objArr.length;
        int i2 = i + i;
        if (i2 > length) {
            if (i2 > length) {
                length = length + (length >> 1) + 1;
                if (length < i2) {
                    int iHighestOneBit = Integer.highestOneBit(i2 - 1);
                    length = iHighestOneBit + iHighestOneBit;
                }
                if (length < 0) {
                    length = Integer.MAX_VALUE;
                }
            }
            this.zza = Arrays.copyOf(objArr, length);
        }
        zzbm.zza(obj, obj2);
        Object[] objArr2 = this.zza;
        int i3 = this.zzb;
        int i4 = i3 + i3;
        objArr2[i4] = obj;
        objArr2[i4 + 1] = obj2;
        this.zzb = i3 + 1;
        return this;
    }

    public final zzbu zzb() {
        zzbs zzbsVar = this.zzc;
        if (zzbsVar != null) {
            throw zzbsVar.zza();
        }
        zzcd zzcdVarZzg = zzcd.zzg(this.zzb, this.zza, this);
        zzbs zzbsVar2 = this.zzc;
        if (zzbsVar2 == null) {
            return zzcdVarZzg;
        }
        throw zzbsVar2.zza();
    }
}
