package com.google.android.gms.internal.measurement;

import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzit implements Comparator {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzjb zzjbVar = (zzjb) obj;
        zzjb zzjbVar2 = (zzjb) obj2;
        zzis zzisVar = new zzis(zzjbVar);
        zzis zzisVar2 = new zzis(zzjbVar2);
        while (zzisVar.hasNext() && zzisVar2.hasNext()) {
            int iCompareTo = Integer.valueOf(zzisVar.zza() & 255).compareTo(Integer.valueOf(zzisVar2.zza() & 255));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return Integer.valueOf(zzjbVar.zzd()).compareTo(Integer.valueOf(zzjbVar2.zzd()));
    }
}
