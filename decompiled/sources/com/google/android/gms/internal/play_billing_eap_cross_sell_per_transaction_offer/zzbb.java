package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbb {
    public final String zza;
    public final zzba zzb;
    public zzba zzc;

    public zzbb(String str, zzbc zzbcVar) {
        zzba zzbaVar = new zzba();
        this.zzb = zzbaVar;
        this.zzc = zzbaVar;
        str.getClass();
        this.zza = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append('{');
        zzba zzbaVar = this.zzb.zzb;
        String str = "";
        while (zzbaVar != null) {
            Object obj = zzbaVar.zza;
            sb.append(str);
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                sb.append((CharSequence) Arrays.deepToString(new Object[]{obj}), 1, r3.length() - 1);
            }
            zzbaVar = zzbaVar.zzb;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzbb zza(Object obj) {
        zzba zzbaVar = new zzba();
        this.zzc.zzb = zzbaVar;
        this.zzc = zzbaVar;
        zzbaVar.zza = obj;
        return this;
    }
}
