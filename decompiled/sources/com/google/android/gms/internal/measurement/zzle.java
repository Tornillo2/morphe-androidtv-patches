package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzle {
    public static final int zza(int i, Object obj, Object obj2) {
        zzld zzldVar = (zzld) obj;
        if (zzldVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzldVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }

    public static final Object zzb(Object obj, Object obj2) {
        zzld zzldVarZzb = (zzld) obj;
        zzld zzldVar = (zzld) obj2;
        if (!zzldVar.isEmpty()) {
            if (!zzldVarZzb.zzb) {
                zzldVarZzb = zzldVarZzb.zzb();
            }
            zzldVarZzb.zzd(zzldVar);
        }
        return zzldVarZzb;
    }
}
