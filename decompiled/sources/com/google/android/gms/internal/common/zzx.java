package com.google.android.gms.internal.common;

import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jspecify.nullness.NullMarked;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@NullMarked
public final class zzx {
    public final zzo zza;
    public final boolean zzb;
    public final zzu zzc;

    public zzx(zzu zzuVar, boolean z, zzo zzoVar, int i) {
        this.zzc = zzuVar;
        this.zzb = z;
        this.zza = zzoVar;
    }

    public static zzx zzc(zzo zzoVar) {
        return new zzx(new zzu(zzoVar), false, zzn.zza, Integer.MAX_VALUE);
    }

    public final zzx zzb() {
        return new zzx(this.zzc, true, this.zza, Integer.MAX_VALUE);
    }

    public final Iterable zzd(CharSequence charSequence) {
        return new zzv(this, charSequence);
    }

    public final List zzf(CharSequence charSequence) {
        charSequence.getClass();
        Iterator itZzh = zzh(charSequence);
        ArrayList arrayList = new ArrayList();
        while (true) {
            zzj zzjVar = (zzj) itZzh;
            if (!zzjVar.hasNext()) {
                return DesugarCollections.unmodifiableList(arrayList);
            }
            arrayList.add((String) zzjVar.next());
        }
    }

    public final Iterator zzh(CharSequence charSequence) {
        return new zzt(this.zzc, this, charSequence);
    }
}
