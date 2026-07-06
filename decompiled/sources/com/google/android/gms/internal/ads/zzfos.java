package com.google.android.gms.internal.ads;

import java.util.Arrays;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzfos {
    public final String zza;
    public final zzfoq zzb;
    public zzfoq zzc;

    public /* synthetic */ zzfos(String str, zzfor zzforVar) {
        zzfoq zzfoqVar = new zzfoq();
        this.zzb = zzfoqVar;
        this.zzc = zzfoqVar;
        str.getClass();
        this.zza = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append('{');
        zzfoq zzfoqVar = this.zzb.zzb;
        String str = "";
        while (zzfoqVar != null) {
            Object obj = zzfoqVar.zza;
            sb.append(str);
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                sb.append((CharSequence) Arrays.deepToString(new Object[]{obj}), 1, r3.length() - 1);
            }
            zzfoqVar = zzfoqVar.zzb;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzfos zza(@CheckForNull Object obj) {
        zzfoq zzfoqVar = new zzfoq();
        this.zzc.zzb = zzfoqVar;
        this.zzc = zzfoqVar;
        zzfoqVar.zza = obj;
        return this;
    }
}
